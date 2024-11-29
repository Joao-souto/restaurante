package src.view;
import javax.swing.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class UserHomePage extends JPanel{
  public UserHomePage(Restaurant restaurant){
    this.setLayout(new GridBagLayout());
    
    Button orderButton=new Button("Order");
    Button reserveButton=new Button("Reserve");

    orderButton.addActionListener(e->{
      restaurant.goToPage(Restaurant.Page.USER_ORDER);
    });

    reserveButton.addActionListener(e->{
      restaurant.goToPage(Restaurant.Page.USER_RESERVE);
    });

    GridBagConstraints gbc=new GridBagConstraints(0,0,1,1,0,0,GridBagConstraints.WEST,1,new Insets(0,0,75,0),3,3);

    this.add(orderButton,gbc);

    gbc.gridy=1;
    this.add(reserveButton,gbc);
  }
}
