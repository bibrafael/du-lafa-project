/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lanchonetedulafa.view;

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
import javax.swing.JTextField;
import lanchonetedulafa.controller.EmpresaAction;
import lanchonetedulafa.model.pojo.Empresa;
import lanchonetedulafa.utils.FixedLengthDocument;

/**
 *
 * @author RAFAEL
 */
public class DialogAtualizarEmpresa extends JDialog {

    private JLabel lEmpresa;
    private JLabel lProprietario;
    private JTextField tfEmpresa;
    private JTextField tfProprietario;
    private JPanel pBotoes;
    private JButton bSalvar;
    private JButton bCancelar;
    private Empresa empresa;

    public DialogAtualizarEmpresa(Long id) {
        this.setTitle("Lanchonete Du Lafa - Editar Empresa");
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 0, 5);
        try {
            empresa = EmpresaAction.getInstance().lerEmpresa(id);
        } catch (Exception ex) {
        }

        lEmpresa = new JLabel("Empresa");
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(lEmpresa, gbc);

        tfEmpresa = new JTextField(20);
        tfEmpresa.setDocument(new FixedLengthDocument(70));
        tfEmpresa.setText(empresa.getEmpresa());
        gbc.gridx = 1;
        gbc.gridy = 0;
        this.add(tfEmpresa, gbc);

        lProprietario = new JLabel("Proprietário");
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(lProprietario, gbc);

        tfProprietario = new JTextField(20);
        tfProprietario.setDocument(new FixedLengthDocument(50));
        tfProprietario.setText(empresa.getProprietario());
        gbc.gridx = 1;
        gbc.gridy = 1;
        this.add(tfProprietario, gbc);

        pBotoes = new JPanel(new FlowLayout());

        bSalvar = new JButton("Salvar");
        bSalvar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(!tfEmpresa.getText().trim().isEmpty() && !tfProprietario.getText().trim().isEmpty()) {
                    try {
                        EmpresaAction.getInstance().atualizarEmpresa(empresa.getId(), tfEmpresa.getText(), tfProprietario.getText());
                        JOptionPane.showMessageDialog(null, "Empresa atualizada com sucesso!", "Atualização", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Atualização não ocorreu com sucesso!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        pBotoes.add(bSalvar);

        bCancelar = new JButton("Cancelar");
        bCancelar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        pBotoes.add(bCancelar);

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        this.add(pBotoes, gbc);
    }

    public void display() {
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setModal(true);
        this.setVisible(true);
    }
}
