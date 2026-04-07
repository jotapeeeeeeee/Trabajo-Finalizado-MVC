/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal.controladores;

import interfaces.IControladorAMProducto;
import interfaces.IControladorProductos;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JOptionPane;
import productos.modelos.GestorProductos;
import productos.modelos.ModeloTablaProductos;
import productos.modelos.Producto;
import productos.vistas.VentanaProductos;

/**
 * Controlador encargado de gestionar la ventana de listado de productos.
 * Maneja la inicialización de la tabla, el filtrado en tiempo real,
 * la eliminación de registros y la navegación hacia la ventana de Alta/Modificación.
 * * Implementa la interfaz IControladorProductos.
 */
public class ControladorProductos implements IControladorProductos {

    private VentanaProductos ventana;
    private static ControladorProductos instancia;
    private ModeloTablaProductos modeloTabla;
    private int filaSeleccionada = -1;

    private ControladorProductos() {
        this.ventana = new VentanaProductos(this);
        this.ventana.setTitle(TITULO);
        this.ventana.setLocationRelativeTo(null);
        this.ventana.setResizable(false);
        this.inicializarTabla();
        this.actualizarTabla();
    }

    public static ControladorProductos instanciar() {
        if (instancia == null) {
            instancia = new ControladorProductos();
        }
        instancia.ventana.setVisible(true);
        return instancia;
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
     * Filtra los productos mostrados en la tabla según la descripción ingresada.
     * Si el campo de búsqueda está vacío, restaura la lista completa.
     * @param evt Evento de acción del botón o invocación directa.
     */
    @Override
    public void btnBuscarClic(ActionEvent evt) {
        String descripcionABuscar = this.ventana.verTxtDescripcion().getText().trim();

        List<Producto> productosAMostrar;

        if (descripcionABuscar.isEmpty()) {
            productosAMostrar = GestorProductos.instanciar().menu();
        } else {
            productosAMostrar = GestorProductos.instanciar().buscarProductos(descripcionABuscar);
        }

        this.modeloTabla = (ModeloTablaProductos) this.ventana.getTablaProductos().getModel();
        this.modeloTabla.dibujarProductosEnTabla(productosAMostrar);
    }

    /**
     * Implementa la búsqueda dinámica en tiempo real.
     * Se ejecuta cada vez que el usuario suelta una tecla en el campo de descripción,
     * disparando el filtro inmediatamente.
     * @param evt Evento de teclado.
     */
    @Override
    public void txtDescripcionPresionarTecla(KeyEvent evt) {
        this.btnBuscarClic(null);
    }

    /**
     * Abre la ventana de Alta de Producto en modo modal.
     * Al cerrarse la ventana hija, actualiza la tabla para reflejar los nuevos registros.
     * @param evt Evento de acción del botón.
     */
    @Override
    public void btnNuevoClic(ActionEvent evt) {
        IControladorAMProducto controladorNuevoProducto = ControladorAMProductos.instanciar(null);
        actualizarTabla();
    }

    /**
     * Gestiona la modificación de un producto existente.
     * Valida que haya una fila seleccionada, obtiene el objeto Producto subyacente
     * y abre la ventana de edición con los datos precargados.
     * @param evt Evento de acción del botón.
     */
    @Override
    public void btnModificarClic(ActionEvent evt) {
        if (this.filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(ventana, "Debe seleccionar un producto de la lista.");
            return;
        }
        this.modeloTabla = (ModeloTablaProductos) this.ventana.getTablaProductos().getModel();
        Producto productoSeleccionado = this.modeloTabla.obtenerProducto(this.filaSeleccionada);
        ControladorAMProductos.instanciar(productoSeleccionado);
        actualizarTabla();
    }

    /**
     * Gestiona la eliminación de un producto.
     * Valida la selección, solicita confirmación al usuario y delega el borrado al Gestor.
     * Finalmente actualiza la vista de la tabla.
     * @param evt Evento de acción del botón.
     */
    @Override
    public void btnBorrarClic(ActionEvent evt) {

        if (this.filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(ventana, "Debe seleccionar un producto para borrar.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }
        this.modeloTabla = (ModeloTablaProductos) this.ventana.getTablaProductos().getModel();
        Producto productoABorrar = this.modeloTabla.obtenerProducto(filaSeleccionada);

        int opcion = JOptionPane.showConfirmDialog(ventana, CONFIRMACION, "Borrar Producto", JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
            String resultado = GestorProductos.instanciar().borrarProducto(productoABorrar);

            JOptionPane.showMessageDialog(ventana, resultado, "Información", JOptionPane.INFORMATION_MESSAGE);
            this.filaSeleccionada = -1;
            this.actualizarTabla();
        }
    }

    /**
     * Configura la tabla visual asignándole un modelo personalizado (ModeloTablaProductos).
     * Carga la lista inicial de productos desde el Gestor y activa el listener de selección.
     */
    private void inicializarTabla() {
        List<Producto> productos = GestorProductos.instanciar().menu();
        modeloTabla = new ModeloTablaProductos(productos);
        this.ventana.definirModeloTabla(modeloTabla);
        this.agregarListenerATabla(this.ventana.getTablaProductos());
    }
    
    /**
     * Metodo privado para actualizar la tabla cada vez que se 
     * modifique
     */
    private void actualizarTabla() {
        List<Producto> listaActualizada = GestorProductos.instanciar().menu();
        ModeloTablaProductos modelo = (ModeloTablaProductos) this.ventana.getTablaProductos().getModel();
        modelo.dibujarProductosEnTabla(listaActualizada);
    }

    /**
     * Metodo privado para agregar un listener a la tabla
     * y que esta se quede escuchando
     *
     * @param tabla tabla que contiene la lista de productos
     */
    private void agregarListenerATabla(javax.swing.JTable tabla) {
        tabla.getSelectionModel().addListSelectionListener((e) -> {
            if (!e.getValueIsAdjusting()) {
                if (tabla.getSelectedRow() != -1) {
                    this.filaSeleccionada = tabla.getSelectedRow();
                } else {
                    this.filaSeleccionada = -1; // Reseteamos si no hay selección
                }
            }
        });
    }
}
