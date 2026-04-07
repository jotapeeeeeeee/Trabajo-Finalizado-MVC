/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package productos.modelos;

import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Esteban
 */
public class ModeloComboEstado extends DefaultComboBoxModel {

    /**
     * Constructor
     */
    public ModeloComboEstado() {
        for (Estado estado : Estado.values()) {
            this.addElement(estado);
        }
    }

    /**
     * Devuelve el esatdo seleccionada
     *
     * @return Estado - estado seleccionado
     */
    public Estado obtenerEstado() {
        return (Estado) this.getSelectedItem();
    }

    /**
     * Selecciona la Categoria especificada
     *
     * @param Estado estado
     */
    public void seleccionarEstado(Estado estado) {
        this.setSelectedItem(estado);
    }
}
