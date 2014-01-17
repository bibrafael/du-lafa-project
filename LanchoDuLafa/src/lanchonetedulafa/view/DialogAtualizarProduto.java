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
import lanchonetedulafa.controller.ProdutoAction;
import lanchonetedulafa.model.pojo.Produto;
import lanchonetedulafa.utils.NumberDocument;

public class DialogAtualizarProduto extends JDialog {

    private JLabel lProduto;
    private JLabel lPreco;
    private JTextField tfProduto;
    private JTextField tfPreco;
    private JPanel pBotoes;
    private JButton bSalvar;
    private JButton bCancelar;
    private Produto produto;

    public DialogAtualizarProduto(Long id) {
        this.setTitle("Lanchonete Du Lafa - Editar Produto");
        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 0, 5);

        try {
            produto = ProdutoAction.getInstance().lerProduto(id);
        } catch (Exception ex) {
        }

        lProduto = new JLabel("Produto");
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(lProduto, gbc);

        tfProduto = new JTextField(20);
        tfProduto.setText(produto.getProduto());
        gbc.gridx = 1;
        gbc.gridy = 0;
        this.add(tfProduto, gbc);

        lPreco = new JLabel("Preço");
        gbc.gridx = 2;
        gbc.gridy = 0;
        this.add(lPreco, gbc);

        tfPreco = new JTextField(7);
        tfPreco.setText(String.valueOf(produto.getPreco()));
        gbc.gridx = 3;
        gbc.gridy = 0;
        this.add(tfPreco, gbc);

        pBotoes = new JPanel(new FlowLayout());

        bSalvar = new JButton("SALVAR");
        bSalvar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!tfProduto.getText().trim().isEmpty() && !tfPreco.getText().trim().isEmpty()) {
                    try {
                        ProdutoAction.getInstance().atualizarProduto(produto.getId(), tfProduto.getText(), Float.parseFloat(tfPreco.getText().replace(',', '.')));
                        JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!", "Atualização", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Atualização não ocorreu com sucesso!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        pBotoes.add(bSalvar);

        bCancelar = new JButton("CANCELAR");
        bCancelar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        pBotoes.add(bCancelar);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(5, 5, 5, 5);
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
