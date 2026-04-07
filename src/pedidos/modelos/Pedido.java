/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedidos.modelos;

/**
 *
 * @author thoma
 */
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import usuarios.modelos.Cliente;
import productos.modelos.Producto;

public class Pedido implements Comparable<Pedido> {

    //Atributos
    private int numero = 0;
    private LocalDateTime fechaYHora;
    private Estado estado;
    private Cliente cliente;
    private List<ProductoDelPedido> listaProductosdelPedido = new ArrayList();

    //Constructores
    public Pedido(int numero, LocalDateTime fechaYHora, Cliente cliente, Estado estado) {
        this(numero, fechaYHora, new ArrayList<>(), cliente, estado);
    }

    public Pedido(int numero, LocalDateTime fechaYHora, List<ProductoDelPedido> lista, Cliente cliente, Estado estado) {
        this.numero = numero;
        this.fechaYHora = fechaYHora;
        this.listaProductosdelPedido = lista;
        this.cliente = cliente;
        this.estado = estado;
    }

    public Pedido(int numero, LocalDateTime fechaYHora, ArrayList<ProductoDelPedido> lista, Cliente cliente) {
        this(numero, fechaYHora, lista, cliente, Estado.CREADO);
    }

    //Metodos
    public int verNumero() {
        return numero;
    }

    public void asignarNumero(int i) {
        this.numero = i;
    }

    public LocalDate verFecha() {
        return this.fechaYHora.toLocalDate();
    }

    public LocalTime verHora() {
        return this.fechaYHora.toLocalTime();
    }

    public Estado verEstado() {
        return estado;
    }

    public void asignarEstado(Estado estado) {
        this.estado = estado;
    }

    public Cliente verCliente() {
        return cliente;
    }

    public void agregarProductodelPedido(Producto produc, int cantidad) {
        ProductoDelPedido nuevoProducto = new ProductoDelPedido(produc, cantidad);
        if (!listaProductosdelPedido.contains(nuevoProducto)) {
            this.listaProductosdelPedido.add(nuevoProducto);
        }
    }

    public List<ProductoDelPedido> verProductoPedido() {
        return listaProductosdelPedido;
    }

    public void mostrar() {
        DateTimeFormatter Fecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter Hora = DateTimeFormatter.ofPattern("hh:mm");

        String fechaFormateada = this.fechaYHora.format(Fecha);
        String horaFormateada = this.fechaYHora.format(Hora);

        System.out.println("Nro: " + this.numero);
        System.out.println("Fecha: " + fechaFormateada + "\t\tHora: " + horaFormateada);
        System.out.println("Cliente: " + this.cliente.verApellido() + ", " + this.cliente.verNombre());
        System.out.println("Estado: " + this.estado);

        System.out.println("\t\tProducto " + "\t\tCantidad");
        System.out.println("\t\t=================================");
        for (ProductoDelPedido p : listaProductosdelPedido) {
            p.mostrar();
        }
        System.out.println("\n####################");
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.numero;
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
        final Pedido other = (Pedido) obj;
        return this.numero == other.numero;
    }

    @Override
    public int compareTo(Pedido o) {
        return this.numero - o.numero;
    }

}
