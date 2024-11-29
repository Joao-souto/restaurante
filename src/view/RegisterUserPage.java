package src.view;
import java.awt.Dimension;

import src.controller.ClientesController;
import src.model.entidades.Clientes;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;

public class RegisterUserPage extends JPanel {
  public RegisterUserPage(Restaurant restaurant) {
    JLabel nameLabel = new JLabel("Nome completo:");
    JLabel cpfLabel = new JLabel("CPF:");
    JLabel sexoLabel = new JLabel("Sexo:");
    JLabel emailLabel = new JLabel("Email:");
    JLabel idadeLabel = new JLabel("Idade:");
    JLabel enderecoLabel = new JLabel("Endereço:");
    JLabel phoneLabel = new JLabel("Telefone:");
    JLabel passwordLabel = new JLabel("Senha:");
    JTextField nameInput = new JTextField();
    JTextField cpfInput = new JTextField();
    JTextField sexoInput = new JTextField();
    JTextField idadeInput = new JTextField();
    JTextField enderecoInput = new JTextField();
    JTextField emailInput = new JTextField();
    JTextField phoneInput = new JTextField();
    JTextField passwordInput = new JTextField();
    
    // JLabel para mensagens de retorno
    JLabel retornoLabel = new JLabel("");
    retornoLabel.setForeground(java.awt.Color.RED);

    this.setPreferredSize(new Dimension(450, 500));
    this.setLayout(new GridBagLayout());

    GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.WEST, 0, new Insets(0, 0, 10, 0), 3, 3);

    this.add(nameLabel, gbc);

    gbc.gridy = 1;
    this.add(cpfLabel, gbc);

    gbc.gridy = 2;
    this.add(idadeLabel, gbc);

    gbc.gridy = 3;
    this.add(sexoLabel, gbc);

    gbc.gridy = 4;
    this.add(emailLabel, gbc);

    gbc.gridy = 5;
    this.add(phoneLabel, gbc);

    gbc.gridy = 6;
    this.add(enderecoLabel, gbc);

    gbc.gridy = 7;
    this.add(passwordLabel, gbc);

    gbc.fill = 1;
    gbc.weightx = 1;
    gbc.gridx = 1;

    gbc.gridy = 0;
    this.add(nameInput, gbc);

    gbc.gridy = 1;
    this.add(cpfInput, gbc);

    gbc.gridy = 2;
    this.add(idadeInput, gbc);

    gbc.gridy = 3;
    this.add(sexoInput, gbc);

    gbc.gridy = 4;
    this.add(emailInput, gbc);

    gbc.gridy = 5;
    this.add(phoneInput, gbc);

    gbc.gridy = 6;
    this.add(enderecoInput, gbc);

    gbc.gridy = 7;
    this.add(passwordInput, gbc);

    JButton backButton = new JButton("Voltar");
    JButton registerButton = new JButton("Cadastrar");

    gbc.fill = 0;
    gbc.gridy = 8;
    gbc.weightx = 0;

    gbc.gridx = 0;
    this.add(backButton, gbc);

    gbc.gridx = 1;
    gbc.anchor = GridBagConstraints.EAST;
    this.add(registerButton, gbc);

    // Adicionar JLabel para mensagens de retorno
    gbc.gridy = 9;
    gbc.gridx = 0;
    gbc.gridwidth = 2;
    this.add(retornoLabel, gbc);

    backButton.addActionListener(e -> {
      restaurant.goToPage(Restaurant.Page.REGISTER);
    });

    registerButton.addActionListener(e -> {
      String name = nameInput.getText();
      String cpf = cpfInput.getText();
      String sexo = sexoInput.getText();
      String email = emailInput.getText();
      String endereco = enderecoInput.getText();
      String phone = phoneInput.getText();
      String password = passwordInput.getText();
      
      int idade = 0;
      try {
        idade = Integer.parseInt(idadeInput.getText());
      } catch (NumberFormatException ex) {
        retornoLabel.setText("Idade inválida. Insira um número.");
        return;
      }

      Clientes cliente = new Clientes(cpf, name, password, sexo, idade, endereco, email, phone);
      cliente = ClientesController.validarCliente(cliente);

      if (cliente.isValid()) {
        retornoLabel.setForeground(java.awt.Color.GREEN);
        retornoLabel.setText("Cadastro realizado com sucesso!");
        //Limpar campos após cadastro
        nameInput.setText("");
        cpfInput.setText("");
        sexoInput.setText("");
        idadeInput.setText("");
        enderecoInput.setText("");
        emailInput.setText("");
        phoneInput.setText("");
        passwordInput.setText("");
      } else {
        retornoLabel.setForeground(java.awt.Color.RED);
        retornoLabel.setText(cliente.getMensagem());
      }
    });
  }
}
