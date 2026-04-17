package DAO;

import CONFIGURADOR.ConexionBD;
import MODELO.Cliente;
import MODELO.Vehiculo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VehiculoDAO {

    public VehiculoDAO() {

    }

    public static boolean InsertarVehiculo(Vehiculo v) {

        String sql = """
                    INSERT INTO vehiculo
                    (matricula, numBastidor, marca, modelo, anio, kmsActuales, idCliente)
                    VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

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


}
