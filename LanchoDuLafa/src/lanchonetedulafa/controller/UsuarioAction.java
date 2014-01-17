/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lanchonetedulafa.controller;

import lanchonetedulafa.model.pojo.Usuario;
import lanchonetedulafa.model.services.UsuarioServices;

/**
 *
 * @author RAFAEL
 */
public class UsuarioAction {
    
    private UsuarioAction() {
    }
    
    public static UsuarioAction getInstance() {
        return UsuarioActionHolder.INSTANCE;
    }
    
    private static class UsuarioActionHolder {

        private static final UsuarioAction INSTANCE = new UsuarioAction();
    }
    
    public boolean login(String nome, String senha) throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setSenha(senha);
        
        return UsuarioServices.getInstance().login(usuario);
    }
}
