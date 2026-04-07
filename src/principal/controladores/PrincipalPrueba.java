/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal.controladores;

import interfaces.IControladorPrincipal;
import interfaces.IGestorUsuarios;
import usuarios.modelos.GestorUsuarios;
import usuarios.modelos.Perfil;

/**
 *
 * @author thoma
 */
public class PrincipalPrueba {

    public static void main(String[] args) {
        IControladorPrincipal cp = ControladorPrincipal.instanciar();
    }

}
