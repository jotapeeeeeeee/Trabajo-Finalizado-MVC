package productos.modelos;

import interfaces.IGestorProductos;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import pedidos.modelos.GestorPedidos;

public class GestorProductos implements IGestorProductos {

    private List<Producto> listaProductos = new ArrayList<>();

    private static GestorProductos instancia;

    private GestorProductos() {
        this.leerArchivo();
    }

    public static GestorProductos instanciar() {
        if (instancia == null) {
            instancia = new GestorProductos();
        }
        return instancia;
    }

    // Metodos
    @Override
    public String crearProducto(int codigo, String descripcion, float precio, Categoria categoria, Estado estado) {
        String validacion = this.validacionDatos(codigo, descripcion, precio, categoria, estado);

        if (!validacion.equals(VALIDACION_EXITO)) {
            return validacion;
        }

        if (this.obtenerProducto(codigo) != null) {
            return PRODUCTOS_DUPLICADOS;
        }

        Producto nuevoProducto = new Producto(codigo, descripcion, categoria, estado, precio);
        this.listaProductos.add(nuevoProducto);
        this.escribirArchivo();

        return EXITO;
    }

    @Override
    public String modificarProducto(Producto productoAModificar, int codigo, String descripcion, float precio, Categoria categoria, Estado estado) {
        if (!this.existeEsteProducto(productoAModificar)) {
            return PRODUCTO_INEXISTENTE;
        }

        String validacion = validacionDatos(codigo, descripcion, precio, categoria, estado);

        if (!validacion.equals(VALIDACION_EXITO)) {
            return validacion;
        }

        // Solo verificar duplicados si el código cambió
        if (productoAModificar.verCodigo() != codigo) {
            if (this.obtenerProducto(codigo) != null) {
                return PRODUCTOS_DUPLICADOS;
            }
        }

        productoAModificar.asignarCodigo(codigo);
        productoAModificar.asignarDescripcion(descripcion);
        productoAModificar.asignarPrecio(precio);
        productoAModificar.asignarCategoria(categoria);
        productoAModificar.asignarEstado(estado);

        this.escribirArchivo();

        return EXITO;
    }

    @Override
    public List<Producto> menu() {
        Collections.sort(this.listaProductos);
        return this.listaProductos;
    }

    @Override
    public List<Producto> buscarProductos(String descripcion) {
        ArrayList<Producto> productosEncontrados = new ArrayList();

        //Devuelvo lista vacia si la descripcion no es valida
        if (descripcion == null || descripcion.isEmpty()) {
            return productosEncontrados;
        }

        String descripcionFormateada = descripcion.toLowerCase().trim();

        for (Producto p : this.listaProductos) {
            if (p.verDescripcion().toLowerCase().trim().contains(descripcionFormateada)) {
                productosEncontrados.add(p);
            }
        }
        Collections.sort(productosEncontrados);
        return productosEncontrados;
    }

    @Override
    public boolean existeEsteProducto(Producto producto) {
        return this.listaProductos.contains(producto);
    }

    @Override
    public List<Producto> verProductosPorCategoria(Categoria categoria) {
        ArrayList<Producto> productosPorCategoria = new ArrayList();

        if (categoria == null) {
            return productosPorCategoria;
        }

        for (Producto p : this.listaProductos) {
            if (p.verCategoria() == categoria) {
                productosPorCategoria.add(p);

            }
        }
        Collections.sort(productosPorCategoria);
        return productosPorCategoria;
    }

    @Override
    public Producto obtenerProducto(Integer codigo) {
        for (Producto p : this.listaProductos) {
            if (p.verCodigo() == codigo) {
                return p;
            }
        }
        return null;
    }

    @Override
    public String borrarProducto(Producto producto) {
        GestorPedidos g = GestorPedidos.instanciar();

        if (!g.hayPedidosConEsteProducto(producto)) {
            this.listaProductos.remove(producto);
            this.escribirArchivo();
            return "Producto eliminado con exito";
        }

        return "Hay pedidos con este producto, no es posible eliminarlo";
    }

    /**
     * Se encarga de leer el archivo donde se guarda la lista de productos Si no
     * existe lo crea, si ya existe lo lee.
     */
    private String leerArchivo() {
        String resultado = crearArchivo();
        if (resultado == CREACION_ERROR) {
            return LECTURA_ERROR;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(NOMBREARCHIVO))) {
            String cadena;
            while ((cadena = br.readLine()) != null) {
                String[] vectorCadenas = cadena.split(SEPARADOR);

                int codigo = Integer.parseInt(vectorCadenas[0]);
                String descripcion = vectorCadenas[1];
                Categoria categoria = null;
                for (Categoria c : Categoria.values()) {
                    if (vectorCadenas[2].equals(c.verValor())) {
                        categoria = c;
                    }
                }
                if (categoria == null) {
                    return LECTURA_ERROR;
                }
                Estado estado = null;
                for (Estado e : Estado.values()) {
                    if (vectorCadenas[3].equals(e.verValor())) {
                        estado = e;
                    }
                }
                if (estado == null) {
                    return LECTURA_ERROR;
                }
                float precio = Float.parseFloat(vectorCadenas[4]);

                Producto productoLeido = new Producto(codigo, descripcion, categoria, estado, precio);
                if (!this.listaProductos.contains(productoLeido)) {
                    this.listaProductos.add(productoLeido);
                }
            }
        } catch (IllegalArgumentException | IOException ex) {
            return LECTURA_ERROR;
        }
        return LECTURA_OK;
    }

    /**
     * Se encarga de la escritura y modificación del archivo donde se guarda la
     * lista de productos.
     */
    private String escribirArchivo() {
        String resultado = crearArchivo();
        if (resultado == CREACION_ERROR) {
            return ESCRITURA_ERROR;
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(NOMBREARCHIVO))) {
            for (Producto producto : this.listaProductos) {
                String linea;
                linea = Integer.toString(producto.verCodigo()) + SEPARADOR;
                linea += producto.verDescripcion() + SEPARADOR;
                linea += producto.verCategoria() + SEPARADOR;
                linea += producto.verEstado() + SEPARADOR;
                linea += Float.toString(producto.verPrecio());
                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException ioe) {
            return ESCRITURA_ERROR;
        }
        return ESCRITURA_OK;
    }

    private String crearArchivo() {

        File file = new File(NOMBREARCHIVO);
        try {
            if (file.createNewFile()) {
                return CREACION_OK;
            } else {
                return ARCHIVO_EXISTENTE;
            }
        } catch (IOException e) {
            return CREACION_ERROR;
        }
    }

    // Verificacion datos
    private String validacionDatos(int codigo, String descripcion, float precio, Categoria categoria, Estado estado) {
        if (codigo <= 0) {
            return ERROR_CODIGO;
        }
        if (descripcion == null || descripcion.isBlank()) {
            return ERROR_DESCRIPCION;
        }
        if (precio <= 0) {
            return ERROR_PRECIO;
        }
        if (categoria == null) {
            return ERROR_CATEGORIA;
        }
        if (estado == null) {
            return ERROR_ESTADO;
        }
        return VALIDACION_EXITO;
    }
}
