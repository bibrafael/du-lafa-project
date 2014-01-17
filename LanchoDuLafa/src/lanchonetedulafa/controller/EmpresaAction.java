/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lanchonetedulafa.controller;

import java.util.List;
import lanchonetedulafa.model.pojo.Empresa;
import lanchonetedulafa.model.services.EmpresaServices;

/**
 *
 * @author RAFAEL
 */
public class EmpresaAction {

    private EmpresaAction() {
    }

    public static EmpresaAction getInstance() {
        return EmpresaActionHolder.INSTANCE;
    }

    private static class EmpresaActionHolder {

        private static final EmpresaAction INSTANCE = new EmpresaAction();
    }

    public List<Empresa> lerEmpresas(String nome) throws Exception {
        Empresa empresa = new Empresa();
        if (nome.trim().isEmpty()) {
            nome = "";
        }
        empresa.setEmpresa(nome);

        return EmpresaServices.getInstance().lerEmpresas(empresa);
    }

    public void adicionarEmpresa(String nome, String proprietario) throws Exception {
        Empresa empresa = new Empresa();
        empresa.setEmpresa(nome);
        empresa.setProprietario(proprietario);

        EmpresaServices.getInstance().adicionarEmpresa(empresa);
    }
    
    public void removerEmpresa(Long id) throws Exception {
        EmpresaServices.getInstance().removerEmpresa(id);
    }
    
    public void atualizarEmpresa(Long id, String nome, String proprietario) throws Exception {
        Empresa empresa = new Empresa();
        empresa.setId(id);
        empresa.setEmpresa(nome);
        empresa.setProprietario(proprietario);
        
        EmpresaServices.getInstance().atualizarEmpresa(empresa);
    }
    
    public Empresa lerEmpresa(Long id) throws Exception {
        return EmpresaServices.getInstance().lerEmpresa(id);
    }
}
