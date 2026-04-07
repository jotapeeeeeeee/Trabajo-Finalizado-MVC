/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package productos.modelos;

/**
 *
 * @author Esteban
 */
public class Producto implements Comparable<Producto> {

    //Atributos
    private int codigo;
    private String descripcion;
    private Categoria unaCategoria;
    private Estado unEstado;
    private float precio;

    //Constructor
    public Producto(int codigo, String descripcion, Categoria categoria, Estado estado, float precio) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.unaCategoria = categoria;
        this.unEstado = estado;
    }

    // Métodos
    @Override
    public String toString() {
        return "Producto{"
                + "codigo=" + codigo
                + ", descripcion='" + descripcion + '\''
                + ", categoria='" + unaCategoria.verValor() + '\''
                + ", estado='" + unEstado.verValor() + '\''
                + ", precio=" + precio
                + '}';
    }

    public void mostrar() {
    System.out.println("[" + codigo + "] "
            + descripcion + " - "
            + unaCategoria.verValor() + " - "
            + unEstado.verValor() + " - $"
            + precio);
}

    public int verCodigo() {
        return codigo;
    }

    public void asignarCodigo(int c) {
        if (c > 0) {
            codigo = c;
        }
    }

    public String verDescripcion() {
        return descripcion;
    }

    public void asignarDescripcion(String d) {
        if (d != null && !d.isBlank()) {
            descripcion = d;
        }
    }

    public Categoria verCategoria() {

        return unaCategoria;
    }

    public void asignarCategoria(Categoria c) {
        unaCategoria = c;
    }

    public Estado verEstado() {
        return unEstado;
    }

    public void asignarEstado(Estado e) {
        unEstado = e;
    }

    public float verPrecio() {
        return precio;
    }

    public void asignarPrecio(float p) {
        if (p > 0) {
            precio = p;
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.codigo;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Producto other = (Producto) obj;
        return this.codigo == other.codigo;
    }

    @Override
    public int compareTo(Producto p) {
        if (this.unaCategoria.compareTo(p.unaCategoria) == 0) {
            return this.descripcion.toLowerCase().compareTo(p.descripcion.toLowerCase());
        } else {
            return this.unaCategoria.compareTo(p.unaCategoria);
        }
    }

}
