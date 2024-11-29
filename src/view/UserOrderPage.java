package src.view;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class UserOrderPage extends JPanel{
  public UserOrderPage(Restaurant restaurant){
    String[] foods={"Fake Food A","Fake Food B","Fake Food C"};
    JComboBox<String> foodsInput=new JComboBox<String>(foods);
    JLabel foodsLabel=new JLabel("Foods");

    String[] drinks={"Fake Drink A","Fake Drink B","Fake Drink C"};
    JComboBox<String> drinksInput=new JComboBox<String>(drinks);
    JLabel drinksLabel=new JLabel("Drinks");

    JLabel addressLabel=new JLabel("Address");
    JTextField addressInput=new JTextField();

    this.setPreferredSize(new Dimension(450,500));
    this.setLayout(new GridBagLayout());

    GridBagConstraints gbc=new GridBagConstraints(0,0,1,1,0,0,GridBagConstraints.WEST,0,new Insets(0,0,10,0),3,3);

    this.add(foodsLabel,gbc);

    gbc.gridy=1;
    this.add(drinksLabel,gbc);

    gbc.gridy=2;
    this.add(addressLabel,gbc);

    gbc.fill=1;
    gbc.weightx=1;
    gbc.gridx=1;
    gbc.gridy=0;

    this.add(foodsInput,gbc);

    gbc.gridy=1;
    this.add(drinksInput,gbc);

    gbc.gridy=2;
    this.add(addressInput,gbc);

    JButton backButton=new JButton("Back");
    JButton orderButton=new JButton("Order");

    gbc.fill=0;
    gbc.gridy=3;
    gbc.weightx=0;

    gbc.gridx=0;
    this.add(backButton,gbc);

    gbc.gridx=1;
    gbc.anchor=GridBagConstraints.EAST;
    this.add(orderButton,gbc);

    backButton.addActionListener(e->{
      restaurant.goToPage(Restaurant.Page.USER_HOME);
    });

    orderButton.addActionListener(e->{
      String food=(String)foodsInput.getSelectedItem();
      String drink=(String)drinksInput.getSelectedItem();
      String address=addressInput.getText();

      System.out.println("food:"+food);
      System.out.println("drink:"+drink);
      System.out.println("address:"+address);
    });
  }
}
