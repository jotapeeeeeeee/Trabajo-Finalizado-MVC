/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal.controladores;

import interfaces.IControladorAMUsuario;
import interfaces.IControladorUsuarios;
import interfaces.IGestorUsuarios;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import usuarios.modelos.GestorUsuarios;
import usuarios.modelos.ModeloTablaUsuarios;
import usuarios.modelos.Usuario;
import usuarios.vistas.VentanaUsuarios;

/**
 * Controlador encargado de la gestión de la ventana de listado de Usuarios.
 * Coordina la visualización de usuarios en la tabla, el filtrado dinámico por
 * apellido, y la navegación hacia las operaciones de Alta, Baja y Modificación.
 * Implementa la interfaz IControladorUsuarios.
 */
public class ControladorUsuarios implements IControladorUsuarios {

    private static ControladorUsuarios instancia;
    private VentanaUsuarios ventana;
    private ModeloTablaUsuarios modeloTabla;
    private int filaSeleccionada = -1;

    private ControladorUsuarios() {
        this.ventana = new VentanaUsuarios(this);
        this.ventana.setTitle(TITULO);
        this.ventana.setLocationRelativeTo(null);
        this.ventana.setResizable(false);

        this.modeloTabla = new ModeloTablaUsuarios();
        this.actualizarTabla();
        this.ventana.definirModeloTabla(this.modeloTabla);
        this.agregarListenerATabla(this.ventana.verJtableUsuarios());
    }

    public static ControladorUsuarios instanciar() {
        if (instancia == null) {
            instancia = new ControladorUsuarios();
        }

        instancia.ventana.setVisible(true);

        return instancia;
    }

    /**
     * Inicia el proceso de creación de un nuevo usuario. Abre la ventana de
     * edición (AM) en modo "Nuevo" y, al finalizar, actualiza la tabla para
     * mostrar el usuario recién creado.
     *
     * @param evt Evento de acción del botón.
     */
    @Override
    public void btnNuevoClic(ActionEvent evt) {
        IControladorAMUsuario controladorNuevoUsuario = ControladorAMUsuario.instanciar(null);
        this.actualizarTabla();
    }

    /**
     * Inicia el proceso de modificación de un usuario existente. Valida que se
     * haya seleccionado una fila, recupera el usuario correspondiente y abre la
     * ventana de edición (AM) con los datos precargados.
     *
     * @param evt Evento de acción del botón.
     */
    @Override
    public void btnModificarClic(ActionEvent evt) {
        if (this.filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this.ventana, "Seleccione un usuario en la tabla para modificar", "Atencion", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Usuario usuarioAModificar = this.modeloTabla.obtenerUsuarioEnFila(filaSeleccionada);

        IControladorAMUsuario controladorModificarUsuario = ControladorAMUsuario.instanciar(usuarioAModificar);
        this.actualizarTabla();
    }

    /**
     * Gestiona la baja física de un usuario. Verifica la selección en la tabla,
     * solicita confirmación al usuario y delega la eliminación al
     * GestorUsuarios. Nota: Si el usuario tiene pedidos asociados, el Gestor
     * impedirá el borrado.
     *
     * @param evt Evento de acción del botón.
     */
    @Override
    public void btnBorrarClic(ActionEvent evt) {
        if (this.filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this.ventana, "Seleccione un usuario en la tabla para borrar", "Atencion", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Usuario usuarioABorrar = this.modeloTabla.obtenerUsuarioEnFila(filaSeleccionada);

        int opcion = JOptionPane.showConfirmDialog(this.ventana, CONFIRMACION, "Borrar usuario", JOptionPane.YES_NO_OPTION);

        if (opcion == 0) {
            IGestorUsuarios gu = GestorUsuarios.instanciar();
            String resultado = gu.borrarUsuario(usuarioABorrar);

            if (!resultado.equals("Usuario eliminado correctamente")) {
                JOptionPane.showMessageDialog(this.ventana, resultado, "ERROR", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this.ventana, resultado, "EXITO   ", JOptionPane.INFORMATION_MESSAGE);
                this.actualizarTabla();
            }
        }
    }

    @Override
    public void ventanaObtenerFoco(WindowEvent evt) {
        // Método requerido por la interfaz pero no utilizado en este caso
    }

    @Override
    public void btnVolverClic(ActionEvent evt) {
        this.ventana.dispose();
    }

    /**
     * Implementa el filtrado dinámico (en tiempo real) por apellido. Se ejecuta
     * al soltar una tecla en el campo de búsqueda, actualizando la tabla
     * instantáneamente sin necesidad de presionar el botón "Buscar".
     *
     * @param evt Evento de teclado.
     */
    @Override
    public void txtApellidoPresionarTecla(KeyEvent evt) {
        this.btnBuscarClic(null);
    }

    /**
     * Filtra la lista de usuarios según el apellido ingresado. Si el campo de
     * búsqueda está vacío, restaura la lista completa ordenada por apellido y
     * nombre.
     *
     * @param evt Evento de acción del botón.
     */
    @Override
    public void btnBuscarClic(ActionEvent evt) {
        String apellidoBuscado = this.ventana.verTxtApellido().getText().trim();

        IGestorUsuarios gu = GestorUsuarios.instanciar();
        List<Usuario> listaUsuariosEncontrados;

        if (apellidoBuscado == null || apellidoBuscado.isBlank()) {
            listaUsuariosEncontrados = gu.verUsuarios();
        } else {
            listaUsuariosEncontrados = gu.buscarUsuarios(apellidoBuscado);
        }

        this.modeloTabla.dibujarTabla(listaUsuariosEncontrados);
    }

    /**
     * Metodo privado para actualizar la tabla cada vez que se modifique
     */
    private void actualizarTabla() {
        IGestorUsuarios gu = GestorUsuarios.instanciar();
        this.modeloTabla.dibujarTabla(gu.verUsuarios());
        this.filaSeleccionada = -1;
    }

    /**
     * Metodo privado para agregar un listener a la tabla y que esta se quede
     * escuchando
     *
     * @param tabla tabla que contiene la lista de productos
     */
    private void agregarListenerATabla(JTable tabla) {
        tabla.getSelectionModel().addListSelectionListener((e) -> {
            if (!e.getValueIsAdjusting()) {
                if (tabla.getSelectedRow() != -1) {
                    this.filaSeleccionada = tabla.getSelectedRow();
                } else {
                    this.filaSeleccionada = -1; // Vuelve a hacer -1 la variable si se deselecciona en la tabla
                }
            }
        });
    }
}
