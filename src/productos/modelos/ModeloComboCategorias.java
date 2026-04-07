package productos.modelos;

import javax.swing.DefaultComboBoxModel;

public class ModeloComboCategorias extends DefaultComboBoxModel {

    /**
     * Constructor
     */
    public ModeloComboCategorias() {
        for (Categoria categoria : Categoria.values()) {
            this.addElement(categoria);
        }
    }

    /**
     * Devuelve la categoria seleccionada
     *
     * @return Categoria - categoría seleccionada
     */
    public Categoria obtenerCategoria() {
        return (Categoria) this.getSelectedItem();
    }

    /**
     * Selecciona la Categoria especificada
     *
     * @param categoria categoría
     */
    public void seleccionarCategoria(Categoria categoria) {
        this.setSelectedItem(categoria);
    }
}
