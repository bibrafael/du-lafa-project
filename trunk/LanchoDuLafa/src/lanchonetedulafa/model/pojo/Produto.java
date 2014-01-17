package lanchonetedulafa.model.pojo;

/**
 *
 * @author RAFAEL
 */
public class Produto {

    private Long id;
    private String produto;
    private float preco;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }
    
}
