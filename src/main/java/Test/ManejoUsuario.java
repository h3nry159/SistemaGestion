package Test;

import Datos.Conexion;
import Datos.UsuarioJDBC;
import Domain.Usuario;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManejoUsuario {

    public static void main(String[] args) {
//INSERTA UN NUEVO USUARIO
//        Usuario usuario = new Usuario("prueba", "123");
//        UsuarioJDBC usuarioJDBC = new UsuarioJDBC();
//        usuarioJDBC.insert(usuario);


//PRUEBA TRANSACCIONES
        
        Connection conexion = null;
        try {
            conexion = Conexion.getConnection();
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            UsuarioJDBC usuarioJDBC = new UsuarioJDBC(conexion);
            Usuario actualizaUsuario = new Usuario();
            actualizaUsuario.setId_usuario(2);
            actualizaUsuario.setNombre("saul2");
            actualizaUsuario.setPass("123");
            usuarioJDBC.update(actualizaUsuario);
            
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setNombre("ingrid1");
            nuevoUsuario.setPass("123");
            usuarioJDBC.insert(nuevoUsuario);
            
            conexion.commit();
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("Estamos en el rollback");
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }
            
            
            
            
            
            
//un comentario
//LISTA TODOS LOS USUARIOS
//        UsuarioJDBC usuarioJDBC = new UsuarioJDBC();
//        List<Usuario> usuarios = new ArrayList<Usuario>();
//        usuarios = usuarioJDBC.select();
//        
//        for(Usuario usuario : usuarios){
//            System.out.println(usuario.toString());
//        }

//ACTUALIZA UN USUARIO POR SU ID
//        Usuario usuario = new Usuario(3,"nohemi","123");
//        UsuarioJDBC usuarioJDBC = new UsuarioJDBC();
//        usuarioJDBC.update(usuario);

//ELIMINA UN USUARIO POR SU ID
//        Usuario usuario = new Usuario();
//        usuario.setId_usuario(4);
//        UsuarioJDBC usuarioJDBC = new UsuarioJDBC();
//        usuarioJDBC.delete(usuario);
      
        
    }

}
