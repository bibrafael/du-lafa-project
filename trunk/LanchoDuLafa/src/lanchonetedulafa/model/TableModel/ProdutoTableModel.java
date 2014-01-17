package lanchonetedulafa.model.TableModel;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import lanchonetedulafa.model.pojo.Empresa;
import lanchonetedulafa.model.pojo.Produto;

public class ProdutoTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private List<Produto> produtos;
    private Boolean[] checks;

    public ProdutoTableModel() {
        produtos = new ArrayList<>();
    }

    public ProdutoTableModel(List<Produto> lista) {
        this();
        produtos.addAll(lista);
    }

    @Override
    public Class<?> getColumnClass(int coluna) {
        Class klass = String.class; // para todas as outras colunas use String
        if (coluna == 0) {
            klass = Boolean.class;
        }
        return klass;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return "";
            case 1:
                return "Produto";
            case 2:
                return "Preço";
            default:
                return "";
        }
    }

    @Override
    public int getRowCount() {
        return produtos.size();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        DecimalFormat df = new DecimalFormat("#0.00");
        Produto c = produtos.get(linha);

        switch (coluna) {
            case 0:
                return checks[linha];
            case 1:
                return c.getProduto();
            case 2:
                return df.format(c.getPreco());
            default:
                return null; // isso nunca deve ocorrer, pois temos s� 3 colunas
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return true;
        }
        return false;
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        Produto c = produtos.get(linha);
        switch (coluna) {
            case 0:
                if (valor.toString().equals("true")) {
                    checks[linha] = true;
                } else {
                    checks[linha] = false;
                }
                break;
            case 1:
                c.setProduto(valor.toString());
                break;
            case 2:
                c.setPreco(Float.parseFloat(valor.toString()));
                break;
        }
        fireTableDataChanged();
    }

    public void adiciona(Produto c) {
        produtos.add(c);
        fireTableRowsInserted(produtos.size() - 1, produtos.size() - 1);
    }

    public void remove(int indice) {
        checks[indice] = false;
        produtos.remove(indice);
        fireTableRowsDeleted(indice, indice);
    }

    public int getIndice(Empresa c) {
        return produtos.indexOf(c);
    }

    public void adicionaLista(List<Produto> lista) {
        checks = new Boolean[lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            checks[i] = false;
        }
        int i = produtos.size();
        Collections.sort(lista, new ordenaProduto());
        produtos.addAll(lista);
        fireTableRowsInserted(i, i + lista.size());
    }

    public void limpaLista() {
        int i = produtos.size();
        produtos.clear();
        if (i > 0) {
            fireTableRowsDeleted(0, i - 1);
        }
    }

    public Long getIdProduto(int indice) {
        return produtos.get(indice).getId();
    }

    public class ordenaProduto implements Comparator<Produto> {

        public int compare(Produto o1, Produto o2) {
            int retorno = 0;
            if (o1 == null || o2 == null) {
                return retorno;
            }

            return o1.getProduto().compareToIgnoreCase(o2.getProduto());

        }
    }
}
