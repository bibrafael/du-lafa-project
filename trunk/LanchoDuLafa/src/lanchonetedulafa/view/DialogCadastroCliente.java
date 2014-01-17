package lanchonetedulafa.view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import lanchonetedulafa.controller.EmpresaAction;
import lanchonetedulafa.model.pojo.Empresa;

/**
 *
 * @author RAFAEL
 */
public class DialogCadastroCliente extends JDialog {

    private JPanel pCabecalho;
    private JPanel pTable;
    private JPanel pBotoes;
    private JLabel lNome;
    private JLabel lEndereco;
    private JLabel lBairro;
    private JLabel lCidade;
    private JLabel lUf;
    private JLabel lCep;
    private JLabel lTel;
    private JLabel lEmail;
    private JLabel lEmpresa;
    private JTextField tfNome;
    private JTextField tfEndereco;
    private JTextField tfBairro;
    private JTextField tfCidade;
    private JTextField tfUf;
    private JFormattedTextField ftfCep;
    private JFormattedTextField ftfTel;
    private JTextField tfEmail;
    private JComboBox cbEmpresas;

    public DialogCadastroCliente() {
        this.setTitle("Lanchonete Du Lafa - Cadastro de Clientes");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());

        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
        }

        pCabecalho = new PCabecalho();
        this.add(pCabecalho, BorderLayout.PAGE_START);
    }

    public void display() {
        this.pack();
        this.setLocationRelativeTo(null);
//        this.setResizable(false);
        this.setModal(true);
        this.setVisible(true);
    }

    public static void main(String args[]) {
        new DialogCadastroCliente().display();
    }

    private class PCabecalho extends JPanel {

        private SpringLayout layout;

        public PCabecalho() {
            this.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

//            lNome = new JLabel("Nome");
//            this.add(lNome);
//
//            tfNome = new JTextField(20);
//            tfNome.setDocument(new FixedLengthDocument(50));
//            this.add(tfNome);
//
//            lEndereco = new JLabel("Endereço");
//            this.add(lEndereco);
//
//            tfEndereco = new JTextField(30);
//            tfEndereco.setDocument(new FixedLengthDocument(70));
//            this.add(tfEndereco);
//
//            lBairro = new JLabel("Bairro");
//            this.add(lBairro);
//
//            tfBairro = new JTextField(10);
//            tfBairro.setDocument(new FixedLengthDocument(30));
//            this.add(tfBairro);
//
//            lCidade = new JLabel("Cidade");
//            this.add(lCidade);
//
//            tfCidade = new JTextField(10);
//            tfCidade.setDocument(new FixedLengthDocument(25));
//            this.add(tfCidade);
//
//            lUf = new JLabel("UF");
//            this.add(lUf);
//
//            tfUf = new JTextField(5);
//            tfUf.setDocument(new FixedLengthDocument(2));
//            this.add(tfUf);
//
//            lCep = new JLabel("CEP");
//            this.add(lCep);
//
//            try {
//                ftfCep = new JFormattedTextField(new MaskFormatter("#####-###"));
//                this.add(ftfCep);
//            } catch (ParseException ex) {
//            }
//
//            lTel = new JLabel("Telefone");
//            this.add(lTel);
//
//            try {
//                ftfTel = new JFormattedTextField(new MaskFormatter("(##)####-####"));
//                this.add(ftfTel);
//            } catch (ParseException ex) {
//            }
//
//            lEmail = new JLabel("E-mail");
//            this.add(lEmail);
//
//            tfEmail = new JTextField(20);
//            tfEmail.setDocument(new FixedLengthDocument(60));
//            this.add(tfEmail);

            lEmpresa = new JLabel("Empresa");
            gbc.gridx = 0;
            gbc.gridy = 0;
            this.add(lEmpresa, gbc);

            try {
                ArrayList<Empresa> empresas = (ArrayList<Empresa>) EmpresaAction.getInstance().lerEmpresas("");
                Collections.sort(empresas, new compEmpresa());
                cbEmpresas = new JComboBox();
                cbEmpresas.addItem("Selecione uma empresa...");
                cbEmpresas.setSelectedIndex(0);
                for (Empresa empresa : empresas) {
                    cbEmpresas.addItem(empresa);
                }
                gbc.gridx = 1;
                gbc.gridy = 0;
                this.add(cbEmpresas, gbc);
            } catch (Exception ex) {
            }

        }
    }

    public class compEmpresa implements Comparator<Empresa> {

        @Override
        public int compare(Empresa o1, Empresa o2) {
            if (o1 == null || o2 == null) {
                return 0;
            }

            return o1.getEmpresa().compareToIgnoreCase(o2.getEmpresa());
        }
    }
}
