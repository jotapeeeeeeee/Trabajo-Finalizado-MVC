/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package productos.modelos;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Modelo de tabla personalizado para visualizar objetos Producto en un JTable.
 * Extiende AbstractTableModel para tener control total sobre qué atributos se
 * muestran en cada columna.
 */
public class ModeloTablaProductos extends AbstractTableModel {

    private final String[] columnas = {"Categoría", "Descripción", "Precio", "Estado"};
    private List<Producto> listaProductos;

    public ModeloTablaProductos(List<Producto> lista) {
        this.listaProductos = lista;
    }

    public ModeloTablaProductos() {
        this.listaProductos = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return this.listaProductos.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnas.length;
    }

    /**
     * Determina el valor a mostrar en una celda específica (fila, columna).
     * Mapea las columnas visuales a los atributos del objeto Producto.
     *
     * * @param fila Índice de la fila.
     * @param columna Índice de la columna.
     * @return El valor del atributo correspondiente (String, Float, Enum,
     * etc.).
     */
    @Override
    public Object getValueAt(int fila, int columna) {
        Producto p = this.listaProductos.get(fila);

        switch (columna) {
            case 0:
                return p.verCategoria();    // Columna 0: Categoría
            case 1:
                return p.verDescripcion();  // Columna 1: Descripción
            case 2:
                return p.verPrecio();       // Columna 2: Precio
            case 3:
                return p.verEstado();       // Columna 3: Estado
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int i) {
        return this.columnas[i];
    }

    /**
     * Actualiza la lista de datos del modelo y notifica a la vista para que se
     * repinte. Es fundamental llamar a fireTableDataChanged() para refrescar la
     * UI.
     *
     * * @param nuevaLista La nueva lista de productos a mostrar.
     */
    public void dibujarProductosEnTabla(List<Producto> nuevaLista) {
        this.listaProductos = nuevaLista;
        this.fireTableDataChanged();
    }

    /**
     * Recupera el objeto Producto asociado a una fila visual específica. Útil
     * para operaciones de selección (Modificar/Borrar).
     *
     * * @param fila Índice de la fila seleccionada.
     * @return El objeto Producto o null si la fila no es válida.
     */
    public Producto obtenerProducto(int fila) {
        if (fila >= 0 && fila < listaProductos.size()) {
            return listaProductos.get(fila);
        }
        return null;
    }

}
