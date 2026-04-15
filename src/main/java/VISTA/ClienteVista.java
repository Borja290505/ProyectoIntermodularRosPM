package VISTA;

import MODELO.Cliente;
import java.util.Scanner;

public class ClienteVista {

    public Cliente pedirDatosCliente() {
        Scanner sc = new Scanner(System.in);
        Cliente c = new Cliente();

        System.out.print("ID Cliente: ");
        c.setIdCliente(sc.nextInt());
        sc.nextLine(); // LIMPIAR BUFFER

        System.out.print("DNI: ");
        c.setDni(sc.nextLine());

        System.out.print("Nombre: ");
        c.setNombre(sc.nextLine());

        System.out.print("Apellidos: ");
        c.setApellidos(sc.nextLine());

        System.out.print("Dirección: ");
        c.setDireccion(sc.nextLine());

        System.out.print("Teléfono: ");
        c.setTelefono(sc.nextLine());

        System.out.print("Email: ");
        c.setEmail(sc.nextLine());

        return c;
    }

    public void mostrarMensaje(String msg) {
        System.out.println(msg);
    }
}