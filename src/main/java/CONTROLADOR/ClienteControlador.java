package CONTROLADOR;

import DAO.ClienteDAO;
import MODELO.Cliente;
import UTIL.LoginApp;
import VISTA.MenuCliente;
import VISTA.MenuVehiculo;
import VISTA.PaginaInicio;
import VISTA.ClienteVista;
import VISTA.InicioSesionCliente;
import VISTA.MenuPrincipal;

public class ClienteControlador {

    private PaginaInicio inicioVista;
    private ClienteDAO clienteDAO;

    public ClienteControlador() {

        clienteDAO = new ClienteDAO();
        inicioVista = new PaginaInicio();

        inicioVista.getBtnRegistro().addActionListener(e -> {
            inicioVista.dispose();
            abrirRegistro();
        });

        inicioVista.getBtnInicioSesion().addActionListener(e -> {
            inicioVista.dispose();
            abrirInicioSesion();
        });
    }

    // =========================
    // REGISTRO
    // =========================
    private void abrirRegistro() {

        ClienteVista.RegistroCliente registroVista = new ClienteVista.RegistroCliente();

        registroVista.getBtnRegistrar().addActionListener(e -> {
            try {
                Cliente c = registroVista.getClienteFormulario();
                c.setFechaAlta(java.time.LocalDateTime.now());

                clienteDAO.insertarCliente(c);
                registroVista.mostrarMensaje("Cliente registrado correctamente");
            } catch (Exception ex) {
                registroVista.mostrarMensaje("Error al registrar cliente");
            }
        });

        registroVista.getBtnVolver().addActionListener(e -> {
            registroVista.dispose();
            new ClienteControlador();
        });
    }

    // =========================
    // LOGIN
    // =========================
    private void abrirInicioSesion() {

        InicioSesionCliente loginVista = new InicioSesionCliente();

        loginVista.getBtnEntrar().addActionListener(e -> {

            String usuario = loginVista.getUsuario();
            String pass = loginVista.getPassword();

            boolean loginCorrecto = clienteDAO.loginCliente(usuario, pass);

            LoginApp.registrarLogin(usuario, loginCorrecto);

            if (loginCorrecto) {
                loginVista.dispose();
                abrirMenuPrincipal();   // ✅ CAMBIO CLAVE
            } else {
                loginVista.mostrarMensaje("Usuario o contraseña incorrectos");
            }
        });

        loginVista.getBtnVolver().addActionListener(e -> {
            loginVista.dispose();
            new ClienteControlador();
        });
    }

    // =========================
    // MENÚ PRINCIPAL
    // =========================
    private void abrirMenuPrincipal() {

        MenuPrincipal menu = new MenuPrincipal();

        menu.getBtnOpcionesVehiculos().addActionListener(e -> {
            menu.dispose();
            abrirMenuVehiculo();
        });

        menu.getBtnOpcionesClientes().addActionListener(e -> {
            menu.dispose();
            abrirMenuCliente();
        });
    }


    private void abrirMenuVehiculo() {

        MenuVehiculo menuVehiculo = new MenuVehiculo();

        menuVehiculo.getBtnVolver().addActionListener(e -> {
            menuVehiculo.dispose();
            abrirMenuPrincipal();
        });
    }

    private void abrirMenuCliente() {
        MenuCliente menuCliente = new MenuCliente();

        menuCliente.getBtnVolver().addActionListener(e -> {
            menuCliente.dispose();
            abrirMenuPrincipal();
        });
    }
}