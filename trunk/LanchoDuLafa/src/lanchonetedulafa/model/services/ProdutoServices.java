/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lanchonetedulafa.model.services;

import java.util.List;
import lanchonetedulafa.model.dao.ProdutoDAO;
import lanchonetedulafa.model.pojo.Produto;

/**
 *
 * @author RAFAEL
 */
public class ProdutoServices {
    
    private ProdutoServices() {
    }
    
    public static ProdutoServices getInstance() {
        return ProdutoServicesHolder.INSTANCE;
    }
    
    private static class ProdutoServicesHolder {

        private static final ProdutoServices INSTANCE = new ProdutoServices();
    }
    
    public List<Produto> lerProdutos(Produto produto) throws Exception {
        return ProdutoDAO.getInstance().lerProdutos(produto);
    }
    
    public void adicionarProduto(Produto produto) throws Exception {
        ProdutoDAO.getInstance().adicionarProduto(produto);
    }
    
    public void removerProduto(Long id) throws Exception {
        ProdutoDAO.getInstance().removerProduto(id);
    }
    
    public Produto lerProduto(Long id) throws Exception {
        return ProdutoDAO.getInstance().lerProduto(id);
    }
    
    public void atualizarProduto(Produto produto) throws Exception {
        ProdutoDAO.getInstance().atualizarProduto(produto);
    }
}
