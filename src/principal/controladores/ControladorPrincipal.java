/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal.controladores;

import interfaces.IControladorPrincipal;
import interfaces.IControladorProductos;
import interfaces.IControladorUsuarios;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import principal.vistas.VentanaPrincipal;

/**
 *
 * @author thoma
 */
public class ControladorPrincipal implements IControladorPrincipal {

    private VentanaPrincipal ventana;
    private static ControladorPrincipal instancia;

    private ControladorPrincipal() {
        this.ventana = new VentanaPrincipal(this);
        this.ventana.setTitle(TITULO);
        this.ventana.setLocationRelativeTo(null);
        this.ventana.setVisible(true);
        this.ventana.setResizable(false);
    }

    public static ControladorPrincipal instanciar() {
        if (instancia == null) {
            instancia = new ControladorPrincipal();
        }
        return instancia;
    }

    @Override
    public void btnProductosClic(ActionEvent evt) {
        IControladorProductos cProd = ControladorProductos.instanciar();
    }

    @Override
    public void btnUsuariosClic(ActionEvent evt) {
        IControladorUsuarios cUsuarios = ControladorUsuarios.instanciar();
    }

    @Override
    public void btnSalirClic(ActionEvent evt) {
        String mensaje = "Desea salir de la aplicacion?";
        String titulo = "Salir";

        int opcion = JOptionPane.showConfirmDialog(this.ventana, mensaje, titulo, JOptionPane.YES_NO_OPTION);

        if (opcion == 0) {
            this.ventana.dispose();
            System.exit(0);
        }
    }
}
