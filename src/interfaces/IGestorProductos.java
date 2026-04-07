/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.ArrayList;
import java.util.List;
import productos.modelos.Categoria;
import productos.modelos.Estado;
import productos.modelos.Producto;

/**
 *
 * @author thoma
 */
public interface IGestorProductos {

    // Metodos
    public String crearProducto(int codigo, String descripcion, float precio, Categoria categoria, Estado estado);

    public String modificarProducto(Producto productoAModificar, int codigo, String descripcion, float precio, Categoria categoria, Estado estado);

    public List<Producto> menu();

    public List<Producto> buscarProductos(String descripcion);

    public boolean existeEsteProducto(Producto producto);

    public List<Producto> verProductosPorCategoria(Categoria categoria);

    public Producto obtenerProducto(Integer codigo);

    public String borrarProducto(Producto producto);

    //Constantes
    public static final String EXITO = "Producto creado/modificado con exito";
    public static final String ERROR_CODIGO = "El codigo del producto es incorrecto";
    public static final String ERROR_DESCRIPCION = "La descripcion del producto es incorrecta";
    public static final String ERROR_PRECIO = "El precio del producto es incorrecto";
    public static final String ERROR_CATEGORIA = "La categoria del producto es incorrecta";
    public static final String ERROR_ESTADO = "El estado del producto es incorrecto";
    public static final String PRODUCTOS_DUPLICADOS = "Ya existe un producto con ese codigo";
    public static final String VALIDACION_EXITO = "Los datos del producto son correctos";
    public static final String PRODUCTO_INEXISTENTE = "No existe el producto especificado";
    public static final String NOMBREARCHIVO = "Productos.txt";
    public static final String SEPARADOR = "/";
    public static final String LECTURA_ERROR = "Error al leer los productos";
    public static final String CREACION_ERROR = "Error al crear el archivo de productos";
    public static final String LECTURA_OK = "Se pudieron leer los productos";
    public static final String CREACION_OK = "Se pudo crear el archivo de productos";
    public static final String ESCRITURA_OK = "Se pudieron guardar los productos";
    public static final String ESCRITURA_ERROR = "Error al guardar los productos";
    public static final String ARCHIVO_EXISTENTE = "El archivo ya existe.";
}
