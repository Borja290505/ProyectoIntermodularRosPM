package DAO;

import CONFIGURADOR.ConexionBD;
import MODELO.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    // 1. INSERTAR CLIENTE
    public boolean insertarCliente(Cliente c) {
        String sql = "INSERT INTO Cliente (dni, nombre, apellidos, telefono, direccion, email, contraseña, fechaAlta) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getDni());
            ps.setString(2, c.getNombre());
            ps.setString(3, c.getApellidos());
            ps.setString(4, c.getTelefono());
            ps.setString(5, c.getDireccion());
            ps.setString(6, c.getEmail());
            ps.setString(7, c.getContraseña());
            ps.setTimestamp(8, Timestamp.valueOf(c.getFechaAlta()));

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 2. MODIFICAR CLIENTE
    public boolean modificarCliente(Cliente c) {
        String sql = "UPDATE Cliente SET nombre=?, apellidos=?, telefono=?, direccion=?, email=?, contraseña=? WHERE dni=?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getNombre());
            ps.setString(2, c.getApellidos());
            ps.setString(3, c.getTelefono());
            ps.setString(4, c.getDireccion());
            ps.setString(5, c.getEmail());
            ps.setString(6, c.getContraseña());
            ps.setString(7, c.getDni());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 3. ELIMINAR CLIENTE
    public boolean eliminarCliente(String dni) {
        String sql = "DELETE FROM Cliente WHERE dni = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dni);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 4. LISTAR TODOS LOS CLIENTES
    public List<Cliente> listarClientes() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM Cliente";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cliente c = new Cliente();
                c.setIdCliente(rs.getInt("idCliente"));
                c.setDni(rs.getString("dni"));
                c.setNombre(rs.getString("nombre"));
                c.setApellidos(rs.getString("apellidos"));
                c.setTelefono(rs.getString("telefono"));
                c.setDireccion(rs.getString("direccion"));
                c.setEmail(rs.getString("email"));
                // Cargamos la fecha si no es nula
                if (rs.getTimestamp("fechaAlta") != null) {
                    c.setFechaAlta(rs.getTimestamp("fechaAlta").toLocalDateTime());
                }
                lista.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // 5. OBTENER POR DNI
    public Cliente obtenerClientePorDni(String dni) {
        String sql = "SELECT * FROM Cliente WHERE dni = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Cliente c = new Cliente();
                c.setIdCliente(rs.getInt("idCliente"));
                c.setDni(rs.getString("dni"));
                c.setNombre(rs.getString("nombre"));
                c.setApellidos(rs.getString("apellidos"));
                c.setTelefono(rs.getString("telefono"));
                c.setDireccion(rs.getString("direccion"));
                c.setEmail(rs.getString("email"));
                c.setContraseña(rs.getString("contraseña"));
                if (rs.getTimestamp("fechaAlta") != null) {
                    c.setFechaAlta(rs.getTimestamp("fechaAlta").toLocalDateTime());
                }
                return c;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 6. LOGIN
    public boolean loginCliente(String dni, String contraseña){
        String sql = "SELECT 1 FROM Cliente WHERE dni = ? AND contraseña = ?";
        try(Connection con = ConexionBD.getConexion();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, dni);
            ps.setString(2, contraseña);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
