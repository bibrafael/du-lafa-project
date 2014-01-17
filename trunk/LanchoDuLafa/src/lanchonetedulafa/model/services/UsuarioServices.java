/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lanchonetedulafa.model.services;

import lanchonetedulafa.model.dao.UsuarioDAO;
import lanchonetedulafa.model.pojo.Usuario;

/**
 *
 * @author RAFAEL
 */
public class UsuarioServices {
    
    private UsuarioServices() {
    }
    
    public static UsuarioServices getInstance() {
        return UsuarioServicesHolder.INSTANCE;
    }
    
    private static class UsuarioServicesHolder {

        private static final UsuarioServices INSTANCE = new UsuarioServices();
    }
    
    public boolean login(Usuario usuario) throws Exception {
        return UsuarioDAO.getInstance().login(usuario);
    }
}
