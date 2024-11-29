package src.view;
import javax.swing.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class HomePage extends JPanel{
  public HomePage(Restaurant restaurant){
    this.setLayout(new GridBagLayout());
    
    Button registerButton=new Button("Cadastro");
    Button loginButton=new Button("Login");

    registerButton.addActionListener(e->{
      restaurant.goToPage(Restaurant.Page.REGISTER);
    });

    loginButton.addActionListener(e->{
      restaurant.goToPage(Restaurant.Page.LOGIN);
    });

    GridBagConstraints gbc=new GridBagConstraints(0,0,1,1,0,0,GridBagConstraints.WEST,1,new Insets(0,0,75,0),3,3);

    this.add(registerButton,gbc);

    gbc.gridy=1;
    this.add(loginButton,gbc);
  }
}
