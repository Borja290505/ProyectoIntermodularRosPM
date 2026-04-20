package DAO;

import CONFIGURADOR.ConexionBD;
import MODELO.Cliente;
import MODELO.Vehiculo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehiculoDAO {

    public VehiculoDAO() {

    }

    //Metodo para dar de alta a vehiculos
    public static boolean InsertarVehiculo(Vehiculo v) {

        String sqlInsercion = """
                    INSERT INTO vehiculo
                    (matricula, numBastidor, marca, modelo, anio, kmsActuales, idCliente)
                    VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sqlInsercion)) {

            ps.setString(1, v.getMatricula());
            ps.setString(2, v.getBastidor());
            ps.setString(3, v.getMarca());
            ps.setString(4, v.getModelo());
            ps.setInt(5, v.getAnio());
            ps.setInt(6, v.getKmsActuales());
            ps.setInt(7, v.getCliente());

            int filasAfect = ps.executeUpdate();

            return filasAfect > 0;

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Erro al dar de alta el vehiculo " + ex.getMessage());
        }
        return false;
    }

    //Metodo para dar de baja a vehiculos

    public static boolean EliminarVehiculo(String matricula){
        String sqlEliminacion = "DELETE FROM Vehiculo WHERE matricula = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sqlEliminacion)){

            ps.setString(1, matricula);

            int filasAfec = ps.executeUpdate();
            return filasAfec > 0;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al dar de baja el vehiculo " + e.getMessage());
        }
        return false;
    }

    public static List<Vehiculo> ListarVehiculos() {
        List<Vehiculo> lista = new ArrayList<>();
        String sql = "SELECT * FROM vehiculo";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Vehiculo v = new Vehiculo(
                        rs.getString("matricula"),
                        rs.getString("numBastidor"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getInt("anio"),
                        rs.getInt("kmsActuales"),
                        rs.getInt("idCliente")
                );
                lista.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }


}
