package src.view;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class Button extends JButton{
  public Button(String label){
    Font primaryFont=new Font("Arial",Font.PLAIN,36);

    this.setFont(primaryFont);
    this.setText(label);
    this.setBackground(Color.BLACK);
    this.setForeground(Color.WHITE);
    this.setBorder(BorderFactory.createCompoundBorder(
      BorderFactory.createLineBorder(Color.BLACK, 5),
      BorderFactory.createLineBorder(Color.BLACK, 20)
    ));
  }
}
