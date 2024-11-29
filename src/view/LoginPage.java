package src.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import src.model.DAO.ClientesDAO;

public class LoginPage extends JPanel {
  public LoginPage(Restaurant restaurant) {
    JLabel emailLabel = new JLabel("Email");
    JLabel passwordLabel = new JLabel("Senha");
    JTextField emailInput = new JTextField();
    JTextField passwordInput = new JTextField();

    // JLabel para mensagens de retorno
    JLabel retornoLabel = new JLabel("");
    retornoLabel.setForeground(java.awt.Color.RED);

    this.setPreferredSize(new Dimension(450, 500));
    this.setLayout(new GridBagLayout());

    GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.WEST, 0,
        new Insets(0, 0, 10, 0), 3, 3);

    this.add(emailLabel, gbc);

    gbc.gridy = 1;
    this.add(passwordLabel, gbc);

    gbc.fill = 1;
    gbc.weightx = 1;
    gbc.gridx = 1;

    gbc.gridy = 0;
    this.add(emailInput, gbc);

    gbc.gridy = 1;
    this.add(passwordInput, gbc);

    JButton backButton = new JButton("Back");
    JButton loginButton = new JButton("Login");

    gbc.fill = 0;
    gbc.gridy = 2;
    gbc.weightx = 0;

    gbc.gridx = 0;
    this.add(backButton, gbc);

    gbc.gridx = 1;
    gbc.anchor = GridBagConstraints.EAST;
    this.add(loginButton, gbc);

        // Adicionar JLabel para mensagens de retorno
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        this.add(retornoLabel, gbc);

    backButton.addActionListener(e -> {
      restaurant.goToPage(Restaurant.Page.HOME);
    });

    loginButton.addActionListener(e -> {
      String email = emailInput.getText();
      String password = passwordInput.getText();

      boolean valid = ClientesDAO.validacaoLogin(email, password);

      if (valid) {
        restaurant.goToPage(Restaurant.Page.USER_HOME);
      } else if(valid == false){
        retornoLabel.setForeground(java.awt.Color.RED);
        retornoLabel.setText("Usuário ou senha incorretos!");
      }
    });
  }
}
