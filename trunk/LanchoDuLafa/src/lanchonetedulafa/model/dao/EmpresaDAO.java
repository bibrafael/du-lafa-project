/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lanchonetedulafa.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import lanchonetedulafa.model.pojo.Empresa;
import lanchonetedulafa.utils.ConnectionManager;

/**
 *
 * @author RAFAEL
 */
public class EmpresaDAO {

    private EmpresaDAO() {
    }

    public static EmpresaDAO getInstance() {
        return EmpresaDAOHolder.INSTANCE;
    }

    private static class EmpresaDAOHolder {

        private static final EmpresaDAO INSTANCE = new EmpresaDAO();
    }

    public List<Empresa> lerEmpresas(Empresa empresa) throws Exception {
        Connection con;
        Statement stm;
        ResultSet rs;
        ArrayList<Empresa> empresas = new ArrayList<>();

        String sql = "SELECT * FROM empresas WHERE empresa_nome like '%" + empresa.getEmpresa() + "%'";

        con = ConnectionManager.getInstance().getConnection();
        stm = con.createStatement();
        rs = stm.executeQuery(sql);

        while (rs.next()) {
            Empresa aux = new Empresa();
            aux.setId(rs.getLong("empresa_id"));
            aux.setEmpresa(rs.getString("empresa_nome"));
            aux.setProprietario(rs.getString("empresa_proprietario"));
            empresas.add(aux);
        }
        return empresas;
    }
    
    public void adicionarEmpresa(Empresa empresa) throws Exception {
        Connection con;
        PreparedStatement stm;
        
        String sql = "INSERT INTO empresas (empresa_nome, empresa_proprietario) VALUES (?, ?)";
        
        con = ConnectionManager.getInstance().getConnection();
        stm = con.prepareStatement(sql);
        stm.setString(1, empresa.getEmpresa());
        stm.setString(2, empresa.getProprietario());
        stm.execute();
    }
    
    public void removerEmpresa(Long id) throws Exception {
        Connection con;
        PreparedStatement stm;
        
        String sql = "DELETE FROM empresas WHERE empresa_id = ?";
        
        con = ConnectionManager.getInstance().getConnection();
        stm = con.prepareStatement(sql);
        stm.setLong(1, id);
        stm.execute();
    }
    
    public void atualizarEmpresa(Empresa empresa) throws Exception {
        Connection con;
        PreparedStatement stm;
        
        String sql = "UPDATE empresas SET empresa_nome = ?, empresa_proprietario = ? WHERE empresa_id = ?";
        
        con = ConnectionManager.getInstance().getConnection();
        stm = con.prepareStatement(sql);
        stm.setString(1, empresa.getEmpresa());
        stm.setString(2, empresa.getProprietario());
        stm.setLong(3, empresa.getId());
        stm.execute();
    }
    
    public Empresa lerEmpresa(Long id) throws Exception {
        Connection con;
        PreparedStatement stm;
        ResultSet rs;
        Empresa empresa = new Empresa();
        
        String sql = "SELECT * FROM empresas WHERE empresa_id = ?";
        
        con = ConnectionManager.getInstance().getConnection();
        stm = con.prepareStatement(sql);
        stm.setLong(1, id);
        rs = stm.executeQuery();
        
        if(rs.next()) {
            empresa.setId(rs.getLong("empresa_id"));
            empresa.setEmpresa(rs.getString("empresa_nome"));
            empresa.setProprietario(rs.getString("empresa_proprietario"));
        }
        return empresa;
    }
}
