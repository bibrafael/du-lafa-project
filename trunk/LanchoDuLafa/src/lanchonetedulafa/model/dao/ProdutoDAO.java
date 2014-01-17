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
import lanchonetedulafa.model.pojo.Produto;
import lanchonetedulafa.utils.ConnectionManager;

/**
 *
 * @author RAFAEL
 */
public class ProdutoDAO {

    private ProdutoDAO() {
    }

    public static ProdutoDAO getInstance() {
        return ProdutoDAOHolder.INSTANCE;
    }

    private static class ProdutoDAOHolder {

        private static final ProdutoDAO INSTANCE = new ProdutoDAO();
    }

    public void adicionarProduto(Produto produto) throws Exception {
        Connection con;
        PreparedStatement stm;

        String sql = "INSERT INTO produtos (produto_nome, produto_preco) VALUES (?,?)";

        con = ConnectionManager.getInstance().getConnection();
        stm = con.prepareCall(sql);
        stm.setString(1, produto.getProduto());
        stm.setFloat(2, produto.getPreco());

        stm.execute();
    }

    public ArrayList<Produto> lerProdutos(Produto produto) throws Exception {
        Connection con;
        Statement stm;
        ResultSet rs;
        ArrayList<Produto> produtos = new ArrayList<>();

        String sql = "SELECT * FROM produtos WHERE produto_nome LIKE '%" + produto.getProduto() + "%'";

        con = ConnectionManager.getInstance().getConnection();
        stm = con.createStatement();
        rs = stm.executeQuery(sql);

        while (rs.next()) {
            Produto aux = new Produto();
            aux.setId(rs.getLong("produto_id"));
            aux.setProduto(rs.getString("produto_nome"));
            aux.setPreco(rs.getFloat("produto_preco"));
            produtos.add(aux);
        }
        return produtos;
    }
    
    public Produto lerProduto(Long id) throws Exception {
        Connection con;
        PreparedStatement stm;
        ResultSet rs;
        Produto produto = new Produto();
        
        String sql = "SELECT * FROM produtos WHERE produto_id = ?";
        
        con = ConnectionManager.getInstance().getConnection();
        stm = con.prepareStatement(sql);
        stm.setLong(1, id);
        
        rs = stm.executeQuery();
        
        if(rs.next()) {
            produto.setId(rs.getLong("produto_id"));
            produto.setProduto(rs.getString("produto_nome"));
            produto.setPreco(rs.getFloat("produto_preco"));
        }
        return produto;
    }

    public void removerProduto(Long id) throws Exception {
        Connection con;
        PreparedStatement stm;
        
        String sql = "DELETE FROM produtos WHERE produto_id = ?";
        
        con = ConnectionManager.getInstance().getConnection();
        stm = con.prepareStatement(sql);
        stm.setLong(1, id);
        
        stm.execute();
    }
    
    public void atualizarProduto(Produto produto) throws Exception {
        Connection con;
        PreparedStatement stm;
        
        String sql = "UPDATE produtos SET produto_nome = ?, produto_preco = ? WHERE produto_id = ?";
        
        con = ConnectionManager.getInstance().getConnection();
        stm = con.prepareStatement(sql);
        stm.setString(1, produto.getProduto());
        stm.setFloat(2, produto.getPreco());
        stm.setLong(3, produto.getId());
        
        stm.execute();
    }
}
