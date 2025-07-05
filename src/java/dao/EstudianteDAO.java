package dao;

import model.Estudiante;
import util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO {

    // Crear
   public boolean insertar(Estudiante estudiante) {
    String sql = "INSERT INTO estudiante (nombre, apellido, tipoDocumento, documento, telefono, correo, grado) VALUES (?, ?, ?, ?, ?, ?, ?)";

    try (Connection con = ConexionBD.obtenerConexion()) {

        if (con == null) {
            System.out.println("❌ La conexión es nula");
            return false;
        }

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, estudiante.getNombre());
        ps.setString(2, estudiante.getApellido());
        ps.setString(3, estudiante.getTipoDocumento());
        ps.setString(4, estudiante.getDocumento());
        ps.setString(5, estudiante.getTelefono());
        ps.setString(6, estudiante.getCorreo());
        ps.setString(7, estudiante.getGrado());

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        System.out.println("Error al insertar estudiante: " + e.getMessage());
        return false;
    }
}

    // Leer
    public List<Estudiante> listar() {
        List<Estudiante> estudiantes = new ArrayList<>();
        String sql = "SELECT * FROM estudiante";  // 

        try (Connection con = ConexionBD.obtenerConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Estudiante est = new Estudiante(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("tipoDocumento"),
                    rs.getString("documento"),
                    rs.getString("telefono"),
                    rs.getString("correo"),
                    rs.getString("grado")
                );
                estudiantes.add(est);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar estudiantes: " + e.getMessage());
        }

        return estudiantes;
    }

    // Actualizar
    public boolean actualizar(Estudiante estudiante) {
        String sql = "UPDATE estudiante SET nombre=?, apellido=?, tipoDocumento=?, documento=?, telefono=?, correo=?, grado=? WHERE id=?"; // CORREGIDO

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getApellido());
            ps.setString(3, estudiante.getTipoDocumento());
            ps.setString(4, estudiante.getDocumento());
            ps.setString(5, estudiante.getTelefono());
            ps.setString(6, estudiante.getCorreo());
            ps.setString(7, estudiante.getGrado());
            ps.setInt(8, estudiante.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar estudiante: " + e.getMessage());
            return false;
        }
    }

    // Eliminar
    public boolean eliminar(int id) {
        String sql = "DELETE FROM estudiante WHERE id=?"; // 

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar estudiante: " + e.getMessage());
            return false;
        }
    }
}