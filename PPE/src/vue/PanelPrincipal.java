package vue;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelPrincipal extends JPanel {
	
	public PanelPrincipal(String titre) {
		this.setBounds(20,80,1150,450);
		this.setLayout(null);
		this.setBackground(Color.white);
		
		JLabel lbTitre = new JLabel(titre);
		lbTitre.setBounds(450,20,400,20);
		this.add(lbTitre);
		
		Font unePolice = new Font("Arial", Font.BOLD, 25);
		lbTitre.setFont(unePolice);
		
		this.setVisible(false);
	}

}
