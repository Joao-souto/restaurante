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

public class UserReservePage extends JPanel{
  public UserReservePage(Restaurant restaurant){
    String[] branches={"Fake Branch A","Fake Branch B","Fake Branch C"};
    JComboBox<String> branchInput=new JComboBox<String>(branches);
    JLabel branchLabel=new JLabel("Branch");

    JLabel dateLabel=new JLabel("Date");
    JTextField dateInput=new JTextField();

    String[] time={"13:00","13:30","14:00","14:30","15:00"};
    JComboBox<String> timeInput=new JComboBox<String>(time);
    JLabel timeLabel=new JLabel("Time");

    this.setPreferredSize(new Dimension(450,500));
    this.setLayout(new GridBagLayout());

    GridBagConstraints gbc=new GridBagConstraints(0,0,1,1,0,0,GridBagConstraints.WEST,0,new Insets(0,0,10,0),3,3);

    this.add(branchLabel,gbc);

    gbc.gridy=1;
    this.add(timeLabel,gbc);

    gbc.gridy=2;
    this.add(dateLabel,gbc);

    gbc.fill=1;
    gbc.weightx=1;
    gbc.gridx=1;
    gbc.gridy=0;

    this.add(branchInput,gbc);

    gbc.gridy=1;
    this.add(timeInput,gbc);

    gbc.gridy=2;
    this.add(dateInput,gbc);

    JButton backButton=new JButton("Back");
    JButton makeReservationButton=new JButton("Make reservation");

    gbc.fill=0;
    gbc.gridy=3;
    gbc.weightx=0;

    gbc.gridx=0;
    this.add(backButton,gbc);

    gbc.gridx=1;
    gbc.anchor=GridBagConstraints.EAST;
    this.add(makeReservationButton,gbc);

    backButton.addActionListener(e->{
      restaurant.goToPage(Restaurant.Page.USER_HOME);
    });

    makeReservationButton.addActionListener(e->{
      String branch=(String)branchInput.getSelectedItem();
      String reservationDate=dateInput.getText();
      String reservationTime=(String)timeInput.getSelectedItem();

      System.out.println("branch:"+branch);
      System.out.println("reservationDate:"+reservationDate);
      System.out.println("reservationTime:"+reservationTime);
    });
  }
}
