package src.view;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;

public class EmployeeHomePage extends JPanel{
  public EmployeeHomePage(Restaurant restaurant){
    String[] stockes={"Stock A","Stock B","Stock C","Stock D"};

    JLabel stockLabel=new JLabel("Stock");
    JComboBox<String> stockInput=new JComboBox<String>(stockes);

    this.setPreferredSize(new Dimension(450,500));
    this.setLayout(new GridBagLayout());

    GridBagConstraints gbc=new GridBagConstraints(0,0,1,1,0,0,GridBagConstraints.WEST,0,new Insets(0,0,10,0),3,3);

    this.add(stockLabel,gbc);

    gbc.fill=1;
    gbc.weightx=1;
    gbc.gridx=1;

    gbc.gridy=0;
    this.add(stockInput,gbc);
  }
}
