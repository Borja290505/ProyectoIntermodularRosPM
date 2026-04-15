package DAO;

import CONFIGURADOR.ConexionBD;
import MODELO.Cliente;

import java.sql.*;

public class ClienteDAO {

    public void insertarCliente(Cliente c) {

        String sql = """
            INSERT INTO Cliente
            (dni, nombre, apellidos, telefono, direccion, email, contraseña, fechaAlta)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;

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

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al insertar cliente: " + e.getMessage());
        }
    }

    public boolean loginCliente(String dni, String contraseña){
        String sql = """
            SELECT 1
            FROM Cliente
            WHERE dni = ? AND contraseña = ?
            """;

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
