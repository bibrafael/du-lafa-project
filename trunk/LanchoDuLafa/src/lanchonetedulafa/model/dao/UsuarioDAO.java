/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lanchonetedulafa.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lanchonetedulafa.model.pojo.Usuario;
import lanchonetedulafa.utils.ConnectionManager;

/**
 *
 * @author RAFAEL
 */
public class UsuarioDAO {
    
    private UsuarioDAO() {
    }
    
    public static UsuarioDAO getInstance() {
        return UsuarioDAOHolder.INSTANCE;
    }
    
    private static class UsuarioDAOHolder {

        private static final UsuarioDAO INSTANCE = new UsuarioDAO();
    }
    
    public boolean login(Usuario usuario) throws Exception {
        Connection con;
        PreparedStatement stm;
        ResultSet rs;
        
        String sql = "SELECT * FROM usuarios WHERE usuario_nome = ? and usuario_senha = ?";
        
        con = ConnectionManager.getInstance().getConnection();
        stm = con.prepareStatement(sql);
        stm.setString(1, usuario.getNome());
        stm.setString(2, usuario.getSenha());
        
        rs = stm.executeQuery();
        
        if(rs.next()) {
            return true;
        }
        return false;
    }
}
