package src.view;
import javax.swing.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class RegisterPage extends JPanel{
  public RegisterPage(Restaurant restaurant){
    this.setLayout(new GridBagLayout());
    
    Button registerEmployeeButton=new Button("Funcionário");
    Button registerUserButton=new Button("Cliente");
    Button backButton=new Button("Voltar");

    registerEmployeeButton.addActionListener(e->{
      restaurant.goToPage(Restaurant.Page.REGISTER_EMPLOYEE);
    });

    registerUserButton.addActionListener(e->{
      restaurant.goToPage(Restaurant.Page.REGISTER_USER);
    });

    backButton.addActionListener(e->{
      restaurant.goToPage(Restaurant.Page.HOME);
    });

    GridBagConstraints gbc=new GridBagConstraints(0,0,1,1,0,0,GridBagConstraints.WEST,1,new Insets(0,0,75,0),3,3);

    this.add(registerEmployeeButton,gbc);

    gbc.gridy=1;
    this.add(registerUserButton,gbc);

    gbc.gridy=2;
    this.add(backButton,gbc);
  }
}
