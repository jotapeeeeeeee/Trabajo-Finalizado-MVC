package principal.controladores;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import pedidos.modelos.Pedido;
import pedidos.modelos.ProductoDelPedido;
import productos.modelos.Categoria;
import productos.modelos.Estado;
import productos.modelos.Producto;
import usuarios.modelos.Cliente;
import usuarios.modelos.Cocinero;
import usuarios.modelos.Encargado;
import usuarios.modelos.Especialidad;
import usuarios.modelos.Mozo;
import usuarios.modelos.Perfil;
import usuarios.modelos.Usuario;

public class ControladorPrincipal_Parcial1 {

    public static void main(String[] args) {
        /*
        ***************************************************
                            Primera parte
        ***************************************************
         */

        ArrayList<Usuario> usuarios = new ArrayList<>();
        ArrayList<Producto> productos = new ArrayList<>();
        ArrayList<Pedido> pedidos = new ArrayList<>();

        Usuario enc1 = new Encargado("correo1.encargado@bar.com", "123456", "ApellidoEncargado1", "NombreEncargado1", Perfil.ENCARGADO);
        Usuario enc2 = new Encargado("correo2.encargado@bar.com", "123456", "ApellidoEncargado2", "NombreEncargado2", Perfil.ENCARGADO);
        Usuario enc3 = new Encargado("correo3.encargado@bar.com", "123456", "ApellidoEncargado3", "NombreEncargado3", Perfil.ENCARGADO);
        Usuario enc4 = new Encargado("correo1.encargado@bar.com", "123456", "ApellidoEncargado4", "NombreEncargado4", Perfil.ENCARGADO);
        //correo repetido

        if (!usuarios.contains(enc1)) {
            usuarios.add(enc1);
        }
        if (!usuarios.contains(enc2)) {
            usuarios.add(enc2);
        }
        if (!usuarios.contains(enc3)) {
            usuarios.add(enc3);
        }
        if (!usuarios.contains(enc4)) {
            usuarios.add(enc4);
        }

        Usuario cli1 = new Cliente("correo1.cliente@bar.com", "123456", "ApellidoCliente1", "NombreCliente1", Perfil.CLIENTE);
        Usuario cli2 = new Cliente("correo2.cliente@bar.com", "123456", "ApellidoCliente2", "NombreCliente2", Perfil.CLIENTE);
        Usuario cli3 = new Cliente("correo3.cliente@bar.com", "123456", "ApellidoCliente3", "NombreCliente3", Perfil.CLIENTE);
        Usuario cli4 = new Cliente("correo1.cliente@bar.com", "123456", "ApellidoCliente4", "NombreCliente4", Perfil.CLIENTE);
        //correo repetido

        if (!usuarios.contains(cli1)) {
            usuarios.add(cli1);
        }
        if (!usuarios.contains(cli2)) {
            usuarios.add(cli2);
        }
        if (!usuarios.contains(cli3)) {
            usuarios.add(cli3);
        }
        if (!usuarios.contains(cli4)) {
            usuarios.add(cli4);
        }

        Usuario mozo1 = new Mozo("correo1.mozo@bar.com", "123456", "ApellidoMozo1", "NombreMozo1", Perfil.MOZO, 3);
        Usuario mozo2 = new Mozo("correo2.mozo@bar.com", "123456", "ApellidoMozo2", "NombreMozo2", Perfil.MOZO, 4);
        Usuario mozo3 = new Mozo("correo3.mozo@bar.com", "123456", "ApellidoMozo3", "NombreMozo3", Perfil.MOZO, 5);
        Usuario mozo4 = new Mozo("correo1.mozo@bar.com", "123456", "ApellidoMozo4", "NombreMozo4", Perfil.MOZO, 5);
        //correo repetido

        if (!usuarios.contains(mozo1)) {
            usuarios.add(mozo1);
        }
        if (!usuarios.contains(mozo2)) {
            usuarios.add(mozo2);
        }
        if (!usuarios.contains(mozo3)) {
            usuarios.add(mozo3);
        }
        if (!usuarios.contains(mozo4)) {
            usuarios.add(mozo4);
        }

        Usuario coc1 = new Cocinero("correo1.cocinero@bar.com", "123456", "ApellidoCocinero1", "NombreCocinero1", Perfil.COCINERO, Especialidad.ENSALADAS);
        Usuario coc2 = new Cocinero("correo2.cocinero@bar.com", "123456", "ApellidoCocinero2", "NombreCocinero2", Perfil.COCINERO, Especialidad.PARRILLA);
        Usuario coc3 = new Cocinero("correo3.cocinero@bar.com", "123456", "ApellidoCocinero3", "NombreCocinero3", Perfil.COCINERO, Especialidad.POSTRES);
        Usuario coc4 = new Cocinero("correo1.cocinero@bar.com", "123456", "ApellidoCocinero4", "NombreCocinero4", Perfil.COCINERO, Especialidad.ENSALADAS);
        //correo repetido

        if (!usuarios.contains(coc1)) {
            usuarios.add(coc1);
        }
        if (!usuarios.contains(coc2)) {
            usuarios.add(coc2);
        }
        if (!usuarios.contains(coc3)) {
            usuarios.add(coc3);
        }
        if (!usuarios.contains(coc4)) {
            usuarios.add(coc4);
        }

        Usuario enc5 = new Encargado("correo1.cliente@bar.com", "123456", "ApellidoEncargado5", "NombreEncargado5", Perfil.ENCARGADO);
        //encargado con el mismo correo que un cliente
        Usuario enc6 = new Encargado("correo1.mozo@bar.com", "123456", "ApellidoEncargado6", "NombreEncargado6", Perfil.ENCARGADO);
        //encargado con el mismo correo que un mozo
        Usuario enc7 = new Encargado("correo1.cocinero@bar.com", "123456", "ApellidoEncargado7", "NombreEncargado7", Perfil.ENCARGADO);
        //encargado con el mismo correo que un cocinero

        if (!usuarios.contains(enc5)) {
            usuarios.add(enc5);
        }
        if (!usuarios.contains(enc6)) {
            usuarios.add(enc6);
        }
        if (!usuarios.contains(enc7)) {
            usuarios.add(enc7);
        }

        System.out.println("Usuarios");
        System.out.println("========");
        for (Usuario u : usuarios) {
            u.mostrar();
        }

        /*
        ***************************************************
                            Segunda parte
        ***************************************************
         */
        Producto unProducto1 = new Producto(1, "Producto1", Categoria.ENTRADA, Estado.DISPONIBLE, 1.0f);
        Producto unProducto2 = new Producto(2, "Producto2", Categoria.PLATO_PRINCIPAL, Estado.DISPONIBLE, 2.0f);
        Producto unProducto3 = new Producto(3, "Producto3", Categoria.POSTRE, Estado.DISPONIBLE, 3.0f);
        Producto unProducto4 = new Producto(3, "Producto4", Categoria.POSTRE, Estado.DISPONIBLE, 4.0f);
        //producto repetido

        if (!productos.contains(unProducto1)) {
            productos.add(unProducto1);
        }
        if (!productos.contains(unProducto2)) {
            productos.add(unProducto2);
        }
        if (!productos.contains(unProducto3)) {
            productos.add(unProducto3);
        }
        if (!productos.contains(unProducto4)) {
            productos.add(unProducto4);
        }

        System.out.println("\nEntradas");
        System.out.println("==========");
        for (Producto p : productos) {
            if (p.verCategoria() == Categoria.ENTRADA) {
                p.mostrar();
            }
        }

        System.out.println("\nPlatos principales");
        System.out.println("==================");
        for (Producto p : productos) {
            if (p.verCategoria() == Categoria.PLATO_PRINCIPAL) {
                p.mostrar();
            }
        }

        System.out.println("\nPostres");
        System.out.println("=======");
        for (Producto p : productos) {
            if (p.verCategoria() == Categoria.POSTRE) {
                p.mostrar();
            }
        }

        ArrayList<ProductoDelPedido> listapdp1 = new ArrayList<>();
        ProductoDelPedido pdp1 = new ProductoDelPedido(productos.get(0), 1);
        ProductoDelPedido pdp2 = new ProductoDelPedido(productos.get(1), 2);
        if (!listapdp1.contains(pdp1)) {
            listapdp1.add(pdp1);
        }
        if (!listapdp1.contains(pdp2)) {
            listapdp1.add(pdp2);
        }
        Pedido unPedido1 = new Pedido(1, LocalDateTime.now(), listapdp1, (Cliente) cli1);

        ArrayList<ProductoDelPedido> productosDelPedido2 = new ArrayList<>();
        ProductoDelPedido pdp3 = new ProductoDelPedido(productos.get(2), 10);
        ProductoDelPedido pdp4 = new ProductoDelPedido(productos.get(0), 20);
        ProductoDelPedido pdp5 = new ProductoDelPedido(productos.get(2), 30);
        //producto repetido        
        if (!productosDelPedido2.contains(pdp3)) {
            productosDelPedido2.add(pdp3);
        }
        if (!productosDelPedido2.contains(pdp4)) {
            productosDelPedido2.add(pdp4);
        }
        if (!productosDelPedido2.contains(pdp5)) {
            productosDelPedido2.add(pdp5);
        }
        Pedido unPedido2 = new Pedido(2, LocalDateTime.now(), productosDelPedido2, (Cliente) cli2);

        ArrayList<ProductoDelPedido> productosDelPedido3 = new ArrayList<>();
        ProductoDelPedido pdp6 = new ProductoDelPedido(productos.get(1), 100);
        ProductoDelPedido pdp7 = new ProductoDelPedido(productos.get(2), 200);
        if (!productosDelPedido3.contains(pdp6)) {
            productosDelPedido3.add(pdp6);
        }
        if (!productosDelPedido3.contains(pdp7)) {
            productosDelPedido3.add(pdp7);
        }
        Pedido unPedido3 = new Pedido(2, LocalDateTime.now(), productosDelPedido3, (Cliente) cli3);
        //pedido repetido

        for (Usuario u : usuarios) {

    if (u instanceof Encargado) {

        Encargado e = (Encargado) u;
        System.out.println(e.generarReporteVentas(LocalDate.now()));

    } else if (u instanceof Cliente) {

        Cliente c = (Cliente) u;
        System.out.println(c.realizarReserva(LocalDateTime.now()));

    } else if (u instanceof Mozo) {

        Mozo m = (Mozo) u;
        System.out.println(m.tomarPedido(unPedido1));

    } else if (u instanceof Cocinero) {

        Cocinero c = (Cocinero) u;
        System.out.println(c.prepararPlato(unProducto1));
    }
}
    }
}
