package gui_oknaPopUp;

import javax.swing.*;

public class OknoZapis extends JFrame {
   public OknoZapis(){
       this.setTitle("Zapis");
       this.setSize(200,100);
       this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
       JLabel text = new JLabel("Wykonano zapis!");
       this.add(text);
      text.setHorizontalAlignment(JLabel.CENTER);
	   text.setVerticalAlignment(JLabel.CENTER);
       this.setVisible(true);
   }
}
