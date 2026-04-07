/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal.controladores;

import interfaces.IControladorAMUsuario;
import interfaces.IGestorUsuarios;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import usuarios.modelos.GestorUsuarios;
import usuarios.modelos.ModeloComboPerfil;
import usuarios.modelos.Perfil;
import usuarios.modelos.Usuario;
import usuarios.vistas.VentanaAMUsuario;

/**
 * Controlador encargado de la lógica de Alta y Modificación (AM) de usuarios.
 * Gestiona la validación de datos (incluyendo la coincidencia de contraseñas),
 * la selección de perfiles y la persistencia a través del GestorUsuarios.
 */
public class ControladorAMUsuario implements IControladorAMUsuario {

    private static ControladorAMUsuario instancia;
    private VentanaAMUsuario ventana;
    private ModeloComboPerfil modeloPerfil;
    private Usuario usuarioModificar = null;

    private ControladorAMUsuario() {
        this.ventana = new VentanaAMUsuario(this);
        this.ventana.setLocationRelativeTo(null);
        this.ventana.setResizable(false);

        this.modeloPerfil = new ModeloComboPerfil();
        this.ventana.definirComboPerfil(modeloPerfil);
    }

    /**
     * Inicializa y configura la ventana de Usuario según el contexto (Alta o Modificación).
     * <p>
     * Si es una <b>Modificación</b>:
     * <ul>
     * <li>Carga los datos del usuario existente en los campos.</li>
     * <li>Deshabilita el campo "Correo" para impedir su modificación (Clave primaria).</li>
     * </ul>
     * Si es un <b>Alta</b>:
     * <ul>
     * <li>Limpia los campos para un nuevo ingreso.</li>
     * <li>Habilita la edición del campo "Correo".</li>
     * </ul>
     * @param usuario El usuario a modificar (null para crear uno nuevo).
     * @return La instancia configurada del controlador.
     */
    public static ControladorAMUsuario instanciar(Usuario usuario) {
        if (instancia == null) {
            instancia = new ControladorAMUsuario();
        }

        instancia.usuarioModificar = usuario;

        if (usuario == null) {
            instancia.ventana.setTitle(TITULO_NUEVO);
            instancia.ventana.LimpiarVentana();
            instancia.ventana.correoEditable(true);
        } else {
            instancia.ventana.setTitle(TITULO_MODIFICAR);

            // LLeno los datos del usuario
            instancia.ventana.verTxtNombre().setText(usuario.verNombre());
            instancia.ventana.verTxtApellido().setText(usuario.verApellido());
            instancia.ventana.verTxtCorreo().setText(usuario.verCorreo());
            instancia.ventana.verPassClave().setText(usuario.verClave());
            instancia.ventana.verPassClaveRepetida().setText(usuario.verClave());
            instancia.ventana.mostrarPerfilAModificar(usuario.getPerfil());

            instancia.ventana.correoEditable(false);
        }

        instancia.ventana.setVisible(true);

        return instancia;
    }

    /**
     * Ejecuta la lógica de negocio para guardar un usuario.
     * Realiza las siguientes validaciones y acciones:
     * 1. Recupera los datos de la vista (Correo, Claves, Apellido, Nombre, Perfil).
     * 2. Verifica que los campos obligatorios no estén vacíos.
     * 3. Solicita al GestorUsuarios la creación o actualización del registro.
     * 4. Notifica el resultado (Éxito o Error) mediante una ventana emergente.
     * @param evt Evento de acción del botón.
     */
    @Override
    public void btnGuardarClic(ActionEvent evt) {
        String correo = this.ventana.verTxtCorreo().getText().trim();
        String clave = this.ventana.verPassClave().getText().trim();
        String claveRepetida = this.ventana.verPassClaveRepetida().getText().trim();
        String apellido = this.ventana.verTxtApellido().getText().trim();
        String nombre = this.ventana.verTxtNombre().getText().trim();
        Perfil perfil = ((ModeloComboPerfil) this.ventana.verComboPerfiles().getModel()).obetenerPerfil();

        IGestorUsuarios gu = GestorUsuarios.instanciar();
        String resultado;

        if (this.usuarioModificar == null) {
            resultado = gu.crearUsuario(correo, clave, apellido, nombre, perfil, claveRepetida);
        } else {
            resultado = gu.modificarUsuario(usuarioModificar, clave, apellido, nombre, perfil, claveRepetida);
        }

        if (!resultado.equals(IGestorUsuarios.EXITO)) {
            JOptionPane.showMessageDialog(this.ventana, resultado, "Exito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this.ventana, resultado, "Error", JOptionPane.ERROR_MESSAGE);
            this.ventana.dispose();
        }
    }

    /**
     * Cancela la operación actual.
     * Cierra la ventana de edición y retorna a la ventana anterior sin persistir ningún cambio.
     * @param evt Evento de acción del botón.
     */
    @Override
    public void btnCancelarClic(ActionEvent evt) {
        this.ventana.dispose();
    }

    /**
     * Controla la entrada de datos en el campo Apellido.
     * <ul>
     * <li><b>Navegación:</b> Si se presiona Enter, transfiere el foco al siguiente componente.</li>
     * <li><b>Validación:</b> Restringe la entrada solo a letras y espacios. Números y símbolos son ignorados.</li>
     * </ul>
     * @param evt Evento de teclado (KeyTyped).
     */
    @Override
    public void txtApellidoPresionarTecla(KeyEvent evt) {
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            evt.getComponent().transferFocus();
        }

        char c = evt.getKeyChar();

        if (!Character.isLetter(c) && !Character.isSpaceChar(c) && !Character.isISOControl(c)) {
            evt.consume();
        }
    }

    /**
     * Controla la entrada de datos en el campo Nombre.
     * <ul>
     * <li><b>Navegación:</b> Si se presiona Enter, transfiere el foco al siguiente componente.</li>
     * <li><b>Validación:</b> Restringe la entrada solo a letras y espacios. Números y símbolos son ignorados.</li>
     * </ul>
     * @param evt Evento de teclado (KeyTyped).
     */
    @Override
    public void txtNombrePresionarTecla(KeyEvent evt) {
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            evt.getComponent().transferFocus();
        }

        char c = evt.getKeyChar();

        if (!Character.isLetter(c) && !Character.isISOControl(c) && !Character.isSpaceChar(c)) {
            evt.consume();
        }
    }

    /**
     * Controla la entrada de datos en el campo Correo.
     * <ul>
     * <li><b>Navegación:</b> Si se presiona Enter, transfiere el foco al siguiente campo.</li>
     * <li><b>Validación:</b> Impide el ingreso de espacios en blanco, ya que no son caracteres válidos en una dirección de correo electrónico.</li>
     * </ul>
     * @param evt Evento de teclado.
     */
    @Override
    public void txtCorreoPresionarTecla(KeyEvent evt) {
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            evt.getComponent().transferFocus();
        }

        if (Character.isSpaceChar(evt.getKeyChar())) {
            evt.consume();
        }
    }

    /**
     * Controla la entrada en el primer campo de Contraseña.
     * Permite cambiar de campo con Enter y evita la inserción de espacios en blanco
     * para asegurar una contraseña compacta.
     * @param evt Evento de teclado.
     */
    @Override
    public void passClavePresionarTecla(KeyEvent evt) {
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            evt.getComponent().transferFocus();
        }

        if (Character.isSpaceChar(evt.getKeyChar())) {
            evt.consume();
        }
    }

    /**
     * Controla la entrada en el campo de Repetir Contraseña.
     * <ul>
     * <li><b>Acción:</b> Si se presiona Enter, se simula el clic en el botón "Guardar" para agilizar la carga de datos.</li>
     * <li><b>Validación:</b> Evita la inserción de espacios en blanco.</li>
     * </ul>
     * @param evt Evento de teclado.
     */
    @Override
    public void passClaveRepetidaPresionarTecla(KeyEvent evt) {
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            this.btnGuardarClic(null);
        }

        if (Character.isSpaceChar(evt.getKeyChar())) {
            evt.consume();
        }
    }
}
