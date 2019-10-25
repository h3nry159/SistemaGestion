package Datos;

import Domain.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioJDBC {

    private static final String SQL_INSERT = "INSERT INTO usuario(usuario, password) VALUES(?,?)";
    private static final String SQL_SELECT = "SELECT * from usuario";
    private static final String SQL_UPDATE = "UPDATE usuario SET usuario = ?, password = ? WHERE id_usuario = ?";
    private static final String SQL_DELETE = "DELETE FROM usuario WHERE id_usuario = ?";

    public List<Usuario> select() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        Usuario usuario = null;
        List<Usuario> usuarios = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SQL_SELECT);
            System.out.println("Ejecutando INSERT INTO usuario(usuario, password) VALUES(?,?)");
            rs = ps.executeQuery();

            while (rs.next()) {
                usuario = new Usuario();
                usuario.setId_usuario(rs.getInt("id_usuario"));
                usuario.setNombre(rs.getString("usuario"));
                usuario.setPass(rs.getString("password"));
                usuarios.add(usuario);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(ps);
            Conexion.close(rs);
            Conexion.close(conn);
        }

        return usuarios;

    }

    public int insert(Usuario usu) {
        PreparedStatement ps = null;
        Connection conn = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SQL_INSERT);
            ps.setString(1, usu.getNombre());
            ps.setString(2, usu.getPass());
            System.out.println("Ejecutando INSERT INTO usuario(usuario, password) VALUES(?,?)");
            rows = ps.executeUpdate();
            System.err.println("Filas afectadas " + rows);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {

            Conexion.close(ps);
            Conexion.close(conn);

        }
        return rows;

    }

    public int update(Usuario usu) {
        PreparedStatement ps = null;
        Connection conn = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, usu.getNombre());
            ps.setString(2, usu.getPass());
            ps.setInt(3, usu.getId_usuario());
            System.out.println("Ejecutando UPDATE usuario SET usuario = ?, password = ? WHERE id_usuario = ?");
            rows = ps.executeUpdate();
            System.err.println("Filas afectadas " + rows);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return rows;
    }

    public int delete(Usuario usu) {

        PreparedStatement ps = null;
        Connection conn = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, usu.getId_usuario());
            System.out.println("Ejecutando DELETE FROM usuario WHERE id_usuario = ?");

            rows = ps.executeUpdate();
            System.err.println("Filas afectadas " + rows);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return rows;

    }

}
