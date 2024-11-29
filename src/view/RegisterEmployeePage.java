package src.view;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;

public class RegisterEmployeePage extends JPanel{
  public RegisterEmployeePage(Restaurant restaurant){
    JLabel nameLabel=new JLabel("Nome completo");
    JLabel cpfLabel=new JLabel("CPF");
    JLabel emailLabel=new JLabel("Email");
    JLabel phoneLabel=new JLabel("Telefone");
    JLabel addressLabel=new JLabel("Endereço:");
    JLabel roleLabel=new JLabel("Cargo:");
    JLabel branchLabel=new JLabel("Filial:");
    JLabel passwordLabel=new JLabel("Senha:");
    JTextField nameInput=new JTextField();
    JTextField cpfInput=new JTextField();
    JTextField emailInput=new JTextField();
    JTextField phoneInput=new JTextField();
    JTextField addressInput=new JTextField();
    JTextField roleInput=new JTextField();
    JTextField passwordInput=new JTextField();
    
    String[] branches={"Fake Branch A","Fake Branch B","Fake Branch C"};

    JComboBox<String> branchInput=new JComboBox<String>(branches);

    this.setPreferredSize(new Dimension(450,500));
    this.setLayout(new GridBagLayout());

    GridBagConstraints gbc=new GridBagConstraints(0,0,1,1,0,0,GridBagConstraints.WEST,0,new Insets(0,0,10,0),3,3);

    this.add(nameLabel,gbc);

    gbc.gridy=1;
    this.add(cpfLabel,gbc);

    gbc.gridy=2;
    this.add(emailLabel,gbc);

    gbc.gridy=3;
    this.add(phoneLabel,gbc);

    gbc.gridy=4;
    this.add(addressLabel,gbc);

    gbc.gridy=5;
    this.add(roleLabel,gbc);

    gbc.gridy=6;
    this.add(branchLabel,gbc);

    gbc.gridy=7;
    this.add(passwordLabel,gbc);

    gbc.fill=1;
    gbc.weightx=1;
    gbc.gridx=1;

    gbc.gridy=0;
    this.add(nameInput,gbc);

    gbc.gridy=1;
    this.add(cpfInput,gbc);

    gbc.gridy=2;
    this.add(emailInput,gbc);

    gbc.gridy=3;
    this.add(phoneInput,gbc);

    gbc.gridy=4;
    this.add(addressInput,gbc);

    gbc.gridy=5;
    this.add(roleInput,gbc);

    gbc.gridy=6;
    this.add(branchInput,gbc);

    gbc.gridy=7;
    this.add(passwordInput,gbc);

    JButton backButton=new JButton("Voltar");
    JButton registerButton=new JButton("Cadastrat");

    gbc.fill=0;
    gbc.gridy=8;
    gbc.weightx=0;

    gbc.gridx=0;
    this.add(backButton,gbc);

    gbc.gridx=1;
    gbc.anchor=GridBagConstraints.EAST;
    this.add(registerButton,gbc);

    backButton.addActionListener(e->{
      restaurant.goToPage(Restaurant.Page.REGISTER);
    });

    registerButton.addActionListener(e->{
      String name=nameInput.getText();
      String cpf=cpfInput.getText();
      String email=emailInput.getText();
      String phone=phoneInput.getText();
      String address=addressInput.getText();
      String role=roleInput.getText();
      String branch=(String)branchInput.getSelectedItem();
      String password=passwordInput.getText();

      System.out.println("name:"+name);
      System.out.println("cpf:"+cpf);
      System.out.println("email:"+email);
      System.out.println("phone:"+phone);
      System.out.println("address:"+address);
      System.out.println("role:"+role);
      System.out.println("branch:"+branch);
      System.out.println("password:"+password);

      restaurant.goToPage(Restaurant.Page.EMPLOYEE_HOME);
    });
  }
}
