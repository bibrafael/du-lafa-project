/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lanchonetedulafa.controller;

import java.util.List;
import lanchonetedulafa.model.pojo.Produto;
import lanchonetedulafa.model.services.ProdutoServices;

/**
 *
 * @author RAFAEL
 */
public class ProdutoAction {
    
    private ProdutoAction() {
    }
    
    public static ProdutoAction getInstance() {
        return ProdutoActionHolder.INSTANCE;
    }
    
    private static class ProdutoActionHolder {

        private static final ProdutoAction INSTANCE = new ProdutoAction();
    }
    
    public List<Produto> lerProdutos(String nome) throws Exception {
        Produto produto = new Produto();
        produto.setProduto(nome);
        
        return ProdutoServices.getInstance().lerProdutos(produto);
    }
    
    public void adicionarProduto(String nome, float preco) throws Exception {
        Produto produto = new Produto();
        produto.setProduto(nome);
        produto.setPreco(preco);
        
        ProdutoServices.getInstance().adicionarProduto(produto);
    }
    
    public void removerProduto(Long id) throws Exception {
        ProdutoServices.getInstance().removerProduto(id);
    }
    
    public Produto lerProduto(Long id) throws Exception {
        return ProdutoServices.getInstance().lerProduto(id);
    }
    
    public void atualizarProduto(Long id, String nome, float preco) throws Exception {
        Produto produto = new Produto();
        produto.setId(id);
        produto.setProduto(nome);
        produto.setPreco(preco);
        
        ProdutoServices.getInstance().atualizarProduto(produto);
    }
}
