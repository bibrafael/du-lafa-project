/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lanchonetedulafa.model.services;

import java.util.List;
import lanchonetedulafa.model.dao.EmpresaDAO;
import lanchonetedulafa.model.pojo.Empresa;

/**
 *
 * @author RAFAEL
 */
public class EmpresaServices {
    
    private EmpresaServices() {
    }
    
    public static EmpresaServices getInstance() {
        return EmpresaServicesHolder.INSTANCE;
    }
    
    private static class EmpresaServicesHolder {

        private static final EmpresaServices INSTANCE = new EmpresaServices();
    }
    
    public List<Empresa> lerEmpresas(Empresa empresa) throws Exception {
        return EmpresaDAO.getInstance().lerEmpresas(empresa);
    }
    
    public void adicionarEmpresa(Empresa empresa) throws Exception {
        EmpresaDAO.getInstance().adicionarEmpresa(empresa);
    }
    
    public void removerEmpresa(Long id) throws Exception {
        EmpresaDAO.getInstance().removerEmpresa(id);
    }
    
    public void atualizarEmpresa(Empresa empresa) throws Exception {
        EmpresaDAO.getInstance().atualizarEmpresa(empresa);
    }
    
    public Empresa lerEmpresa(Long id) throws Exception {
        return EmpresaDAO.getInstance().lerEmpresa(id);
    }
}
