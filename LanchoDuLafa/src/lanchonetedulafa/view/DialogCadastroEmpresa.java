/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import lanchonetedulafa.controller.EmpresaAction;
import lanchonetedulafa.model.TableModel.EmpresaTableModel;
import lanchonetedulafa.utils.FixedLengthDocument;

/**
 *
 * @author RAFAEL
 */
public class DialogCadastroEmpresa extends JDialog {

    private JPanel pBotoes;
    private JPanel pCabecalho;
    private JPanel pTable;
    private EmpresaTableModel modelo;
    private Boolean[] checks;
    private JTable tEmpresa;
    private JScrollPane scrollPane;
    private JLabel lEmpresa;
    private JLabel lProprietario;
    private JTextField tfEmpresa;
    private JTextField tfProprietario;
    private JButton bAdd;

    public DialogCadastroEmpresa() {
        this.setTitle("Lanchonete Du Lafa - Cadastro de Empresa");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout());

        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
        }

        pCabecalho = new PCabecalho();
        this.add(pCabecalho, BorderLayout.PAGE_START);

        pTable = new PTable();
        this.add(pTable, BorderLayout.CENTER);

        pBotoes = new PBotoes();
        this.add(pBotoes, BorderLayout.PAGE_END);

    }

    public static void main(String args[]) {
        new DialogCadastroEmpresa().display();
    }

    public void display() {
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private class PTable extends JPanel implements TableModelListener {

        public PTable() {
            try {
                modelo = new EmpresaTableModel();
                modelo.addTableModelListener(this);
                modelo.adicionaLista(EmpresaAction.getInstance().lerEmpresas(""));
                checks = new Boolean[modelo.getRowCount()];
                for (int i = 0; i < checks.length; i++) {
                    checks[i] = false;
                }

                tEmpresa = new JTable() {

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
                tEmpresa.setModel(modelo);
                tEmpresa.setDefaultRenderer(Object.class, new CellRenderer());

                scrollPane = new JScrollPane();
                scrollPane.setViewportView(tEmpresa);
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
                    checks[tEmpresa.getEditingRow()] = (Boolean) tEmpresa.getValueAt(tEmpresa.getEditingRow(), 0);
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

        private JButton bEditar;
        private JButton bCancelar;
        private JButton bDel;

        public PBotoes() {
            this.setLayout(new FlowLayout());

            bAdd = new JButton("Adicionar");
            bAdd.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!tfEmpresa.getText().trim().isEmpty() && !tfProprietario.getText().trim().isEmpty()) {
                        try {
                            EmpresaAction.getInstance().adicionarEmpresa(tfEmpresa.getText(), tfProprietario.getText());
                            modelo.limpaLista();
                            modelo.adicionaLista(EmpresaAction.getInstance().lerEmpresas(""));
                            tfEmpresa.setText(null);
                            tfProprietario.setText(null);
                            checks = new Boolean[modelo.getRowCount()];
                            for (int i = 0; i < checks.length; i++) {
                                checks[i] = false;
                            }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Não é possível cadastrar empresas com o mesmo nome!", "Atenção", JOptionPane.WARNING_MESSAGE);
                            tfEmpresa.setText(null);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Nome da Empresa/Proprietário DEVEM ser preenchidos!", "Atenção", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });
            this.add(bAdd);

            bDel = new JButton("Remover");
            bDel.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    for (int i = 0; i < tEmpresa.getRowCount(); i++) {
                        if (checks[i] == true) {
                            try {
                                EmpresaAction.getInstance().removerEmpresa(modelo.getIdEmpresa(i));
                            } catch (Exception ex) {
                            }
                        }
                    }
                    modelo.limpaLista();
                    try {
                        modelo.adicionaLista(EmpresaAction.getInstance().lerEmpresas(""));
                    } catch (Exception ex) {
                    }
                    tEmpresa.setModel(modelo);
                    checks = new Boolean[tEmpresa.getRowCount()];
                    for (int i = 0; i < tEmpresa.getRowCount(); i++) {
                        checks[i] = false;
                    }
                }
            });
            this.add(bDel);

            bEditar = new JButton("Editar");
            bEditar.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    int cont = 0;
                    Long id = null;
                    for (int i = 0; i < checks.length; i++) {
                        if(checks[i] == true) {
                            cont++;
                            id = modelo.getIdEmpresa(i);
                        }
                    }
                    if(cont == 1) {
                        DialogAtualizarEmpresa dialogAtualizarEmpresa = new DialogAtualizarEmpresa(id);
                        dialogAtualizarEmpresa.display();
                        modelo.limpaLista();
                        try {
                            modelo.adicionaLista(EmpresaAction.getInstance().lerEmpresas(""));
                        } catch (Exception ex) {
                        }
                        tEmpresa.setModel(modelo);
                        for (int i = 0; i < checks.length; i++) {
                            checks[i] = false;
                        }
                    } else if (cont > 1) {
                        JOptionPane.showMessageDialog(null, "Selecione apenas UMA empresa para editar!", "Atenção", JOptionPane.WARNING_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Selecione uma empresa para editar!", "Atenção", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });
            this.add(bEditar);

            bCancelar = new JButton("Cancelar");
            bCancelar.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
            this.add(bCancelar);
        }
    }

    private class PCabecalho extends JPanel {

        public PCabecalho() {
            this.setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 0, 5);

            lEmpresa = new JLabel("Empresa");
            gbc.gridx = 0;
            gbc.gridy = 0;
            this.add(lEmpresa, gbc);

            tfEmpresa = new JTextField(20);
            tfEmpresa.setDocument(new FixedLengthDocument(70));
            gbc.gridx = 1;
            gbc.gridy = 0;
            this.add(tfEmpresa, gbc);

            lProprietario = new JLabel("Proprietário");
            gbc.gridx = 0;
            gbc.gridy = 1;
            this.add(lProprietario, gbc);

            tfProprietario = new JTextField(20);
            tfProprietario.setDocument(new FixedLengthDocument(50));
            tfProprietario.addKeyListener(new KeyAdapter() {

                @Override
                public void keyPressed(KeyEvent e) {
                    int key = e.getKeyCode();
                    if (key == KeyEvent.VK_ENTER) {
                        bAdd.doClick();
                    }
                }
            });
            gbc.gridx = 1;
            gbc.gridy = 1;
            this.add(tfProprietario, gbc);
        }
    }
}
