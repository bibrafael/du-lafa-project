package lanchonetedulafa.model.TableModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import lanchonetedulafa.model.pojo.Empresa;

public class EmpresaTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private List<Empresa> empresas;
    private Boolean[] checks;

    public EmpresaTableModel() {
        empresas = new ArrayList<>();
    }

    public EmpresaTableModel(List<Empresa> lista) {
        this();
        empresas.addAll(lista);
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
                return "Empresa";
            case 2:
                return "Proprietário";
            default:
                return "";
        }
    }

    @Override
    public int getRowCount() {
        return empresas.size();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Empresa c = empresas.get(linha);

        switch (coluna) {
            case 0:
                return checks[linha];
            case 1:
                return c.getEmpresa();
            case 2:
                return c.getProprietario();
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
        Empresa c = empresas.get(linha);
        switch (coluna) {
            case 0:
                if (valor.toString().equals("true")) {
                    checks[linha] = true;
                } else {
                    checks[linha] = false;
                }
                break;
            case 1:
                c.setEmpresa(valor.toString());
                break;
            case 2:
                c.setProprietario(valor.toString());
                break;
        }
        fireTableDataChanged();
    }

    public void adiciona(Empresa c) {
        empresas.add(c);
        fireTableRowsInserted(empresas.size() - 1, empresas.size() - 1);
    }

    public void remove(int indice) {
        checks[indice] = false;
        empresas.remove(indice);
        fireTableRowsDeleted(indice, indice);
    }

    public int getIndice(Empresa c) {
        return empresas.indexOf(c);
    }

    public void adicionaLista(List<Empresa> lista) {
        checks = new Boolean[lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            checks[i] = false;
        }
        int i = empresas.size();
        Collections.sort(lista, new ordenaEmpresa());
        empresas.addAll(lista);
        fireTableRowsInserted(i, i + lista.size());
    }

    public void limpaLista() {
        int i = empresas.size();
        empresas.clear();
        if (i > 0) {
            fireTableRowsDeleted(0, i - 1);
        }
    }

    public Long getIdEmpresa(int indice) {
        return empresas.get(indice).getId();
    }

    public class ordenaEmpresa implements Comparator {

        public int compare(Object o1, Object o2) {
            int retorno = 0;
            if (o1 == null || o2 == null) {
                return retorno;
            }

            Empresa empresa = (Empresa) o1;
            Empresa empresa1 = (Empresa) o2;

            return empresa.getEmpresa().compareToIgnoreCase(empresa1.getEmpresa());

        }
    }
}
