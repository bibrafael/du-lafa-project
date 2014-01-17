package lanchonetedulafa.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import lanchonetedulafa.controller.ProdutoAction;
import lanchonetedulafa.model.TableModel.ProdutoTableModel;
import lanchonetedulafa.utils.NumberDocument;

/**
 *
 * @author RAFAEL
 */
public class DialogCadastroProduto extends JDialog {

    private JTable tProduto;
    private JScrollPane scrollPane;
    private ProdutoTableModel modelo;
    private Boolean[] checks;
    private JButton bAdd;
    private JButton bCancelar;
    private JButton bRemover;
    private JButton bEditar;
    private JLabel lProduto;
    private JTextField tfProduto;
    private JLabel lPreco;
    private JTextField tfPreco;

    public DialogCadastroProduto() {
        this.setTitle("Lanchonete Du Lafa - Cadastro de Produtos");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout());

        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
        }

        PCabecalho pCabecalho = new PCabecalho();
        this.getContentPane().add(pCabecalho, BorderLayout.PAGE_START);

        PTable pTable = new PTable();
        this.getContentPane().add(pTable, BorderLayout.CENTER);

        PBotoes pBotoes = new PBotoes();
        this.getContentPane().add(pBotoes, BorderLayout.PAGE_END);
    }

    public void display() {
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String args[]) {
        DialogCadastroProduto dcp = new DialogCadastroProduto();
        dcp.display();
    }

    public class PCabecalho extends JPanel {

        public PCabecalho() {
            this.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 0, 5);

            lProduto = new JLabel("Produto");
            gbc.gridx = 0;
            gbc.gridy = 0;
            this.add(lProduto, gbc);

            tfProduto = new JTextField(20);
            gbc.gridx = 1;
            gbc.gridy = 0;
            this.add(tfProduto, gbc);

            lPreco = new JLabel("Preço");
            gbc.gridx = 2;
            gbc.gridy = 0;
            this.add(lPreco, gbc);

            tfPreco = new JTextField(7);
            tfPreco.setDocument(new NumberDocument());
            gbc.gridx = 3;
            gbc.gridy = 0;
            this.add(tfPreco, gbc);
        }
    }

    private class PTable extends JPanel implements TableModelListener {

        public PTable() {
            try {
                modelo = new ProdutoTableModel();
                modelo.addTableModelListener(this);
                modelo.adicionaLista(ProdutoAction.getInstance().lerProdutos(""));
                checks = new Boolean[modelo.getRowCount()];
                for (int i = 0; i < checks.length; i++) {
                    checks[i] = false;
                }

                tProduto = new JTable() {

                    @Override
                    public Component prepareRenderer(TableCellRenderer renderer,
                            int rowIndex, int vColIndex) {
                        Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
                        if (rowIndex % 2 == 0 && !isCellSelected(rowIndex, vColIndex)) {
                            c.setBackground(Color.LIGHT_GRAY);
                        } else {
                            c.setBackground(getBackground());
                        }
                        return c;
                    }
                };
                tProduto.setModel(modelo);
                tProduto.setDefaultRenderer(Object.class, new CellRenderer());

                scrollPane = new JScrollPane();
                scrollPane.setViewportView(tProduto);
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
            this.add(scrollPane);
        }

        @Override
        public void tableChanged(TableModelEvent e) {
            switch (e.getType()) {
                case TableModelEvent.DELETE:
                    break;
                case TableModelEvent.INSERT:
                    break;
                case TableModelEvent.UPDATE:
                    checks[tProduto.getEditingRow()] = (Boolean) tProduto.getValueAt(tProduto.getEditingRow(), 0);
                    break;
                default:
                    break;
            }
        }
    }

    public class CellRenderer extends DefaultTableCellRenderer {

        public CellRenderer() {
            super();
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            this.setHorizontalAlignment(CENTER);

            return super.getTableCellRendererComponent(table, value, isSelected,
                    hasFocus, row, column);
        }
    }

    private class PBotoes extends JPanel {

        public PBotoes() {
            this.setLayout(new FlowLayout());

            bAdd = new JButton("ADICIONAR");
            bAdd.setMnemonic('a');
            bAdd.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!tfProduto.getText().trim().isEmpty() && !tfPreco.getText().trim().isEmpty()) {
                        float preco = Float.parseFloat(tfPreco.getText().replace(',', '.'));
                        try {
                            ProdutoAction.getInstance().adicionarProduto(tfProduto.getText(), preco);
                            modelo.limpaLista();
                            modelo.adicionaLista(ProdutoAction.getInstance().lerProdutos(""));
                            tfProduto.setText(null);
                            tfPreco.setText(null);
                            checks = new Boolean[modelo.getRowCount()];
                            for (int i = 0; i < checks.length; i++) {
                                checks[i] = false;
                            }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Não é possível cadastrar produtos com nomes iguais!", "Erro", JOptionPane.ERROR_MESSAGE);
                            tfProduto.setText(null);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Produto/preço DEVEM ser preenchidos!", "Atenção", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });
            this.add(bAdd);

            bEditar = new JButton("EDITAR");
            bEditar.setMnemonic('e');
            bEditar.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    int cont = 0;
                    Long id = null;
                    for (int i = 0; i < checks.length; i++) {
                        if (checks[i] == true) {
                            cont++;
                            id = modelo.getIdProduto(i);
                        }
                    }
                    if (cont == 1) {
                        DialogAtualizarProduto atualizarProduto = new DialogAtualizarProduto(id);
                        atualizarProduto.display();
                        modelo.limpaLista();
                        try {
                            modelo.adicionaLista(ProdutoAction.getInstance().lerProdutos(""));
                        } catch (Exception ex) {
                        }
                        tProduto.setModel(modelo);
                        for (int i = 0; i < checks.length; i++) {
                            checks[i] = false;
                        }
                    } else if (cont > 1) {
                        JOptionPane.showMessageDialog(null, "Selecione apenas UM produto para editar!", "Atenção", JOptionPane.WARNING_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Selecione um produto para editar!", "Atenção", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });
            this.add(bEditar);

            bRemover = new JButton("REMOVER");
            bRemover.setMnemonic('r');
            bRemover.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    for (int i = 0; i < tProduto.getRowCount(); i++) {
                        if (checks[i] == true) {
                            try {
                                ProdutoAction.getInstance().removerProduto(modelo.getIdProduto(i));
                            } catch (Exception ex) {
                            }
                        }
                    }
                    modelo.limpaLista();
                    try {
                        modelo.adicionaLista(ProdutoAction.getInstance().lerProdutos(""));
                    } catch (Exception ex) {
                    }
                    tProduto.setModel(modelo);
                    checks = new Boolean[tProduto.getRowCount()];
                    for (int i = 0; i < tProduto.getRowCount(); i++) {
                        checks[i] = false;
                    }
                }
            });
            this.add(bRemover);

            bCancelar = new JButton("CANCELAR");
            bCancelar.setMnemonic('c');
            bCancelar.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
            this.add(bCancelar);
        }
    }
}
