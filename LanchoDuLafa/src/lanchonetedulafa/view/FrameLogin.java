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
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;

import lanchonetedulafa.controller.UsuarioAction;

/**
 * 
 * @author RAFAEL
 */
public class FrameLogin extends JFrame {

	private JPanel pBotoes;
	private JLabel lUsuario;
	private JLabel lSenha;
	private JTextField tfUsuario;
	private JPasswordField pfSenha;
	private JButton bEntrar;
	private JButton bCancelar;

	public FrameLogin() {
		super("Lanchonete Du Lafa");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		try {
			UIManager.setLookAndFeel(new MetalLookAndFeel());
		} catch (UnsupportedLookAndFeelException ex) {
		}

		this.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		montaFrame(gbc);

	}

	private void montaFrame(GridBagConstraints gbc) {
		lUsuario = new JLabel("Usuario");
		gbc.insets = new Insets(5, 5, 0, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.getContentPane().add(lUsuario, gbc);

		tfUsuario = new JTextField(20);
		gbc.gridx = 1;
		gbc.gridy = 0;
		this.getContentPane().add(tfUsuario, gbc);

		lSenha = new JLabel("Senha");
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.getContentPane().add(lSenha, gbc);

		pfSenha = new JPasswordField(20);
		gbc.gridx = 1;
		gbc.gridy = 1;
		this.getContentPane().add(pfSenha, gbc);

		panelBotoes();
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		this.getContentPane().add(pBotoes, gbc);
	}

	private void panelBotoes() {
		pBotoes = new JPanel(new FlowLayout());
		pBotoes.setOpaque(false);

		bEntrar = new JButton("Entrar");
		bEntrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					boolean usuarioExiste = UsuarioAction.getInstance().login(
							tfUsuario.getText(),
							criptografaSenha(pfSenha.getText()));
					if (usuarioExiste) {
					} else {
						JOptionPane.showMessageDialog(null,
								"Usuário/Senha incorretas!", "Erro",
								JOptionPane.ERROR_MESSAGE);
						tfUsuario.setText(null);
						pfSenha.setText(null);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		pBotoes.add(bEntrar);

		bCancelar = new JButton("Cancelar");
		bCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		pBotoes.add(bCancelar);

	}

	public void display() {
		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}

	private String criptografaSenha(String senha) {
		try {
			MessageDigest cripto = MessageDigest.getInstance("SHA-256");
			BigInteger bi = new BigInteger(1, cripto.digest(senha.getBytes()));
			return bi.toString(16);
		} catch (NoSuchAlgorithmException ex) {
			return null;
		}
	}

}
