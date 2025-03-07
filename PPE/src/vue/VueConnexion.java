package vue;
 
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
 
import controleur.Controleur;
import controleur.NS;
import controleur.User;
 
public class VueConnexion extends JFrame implements ActionListener 
{
	private JButton btSeConnecter = new JButton("Se Connecter") ; 
	private JButton btAnnuler = new JButton("Annuler") ;
	private JTextField txtEmail = new JTextField(""); 
	private JPasswordField txtMdp = new JPasswordField(""); 
	private JPanel panelForm = new JPanel (); 
	public VueConnexion() {
		this.setTitle("Application CL Neige & Soleil");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(100, 100, 290, 400);
		this.setLayout(null);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.white);
		
		
		//ajout de l'image logo
		
		var size = 6;
		
		
		ImageIcon uneImage = new ImageIcon("src/images/logo.png");
		ImageIcon unTest = new ImageIcon(uneImage.getImage().getScaledInstance((int) 1500/size,(int)  650/size, DO_NOTHING_ON_CLOSE));
		
		JLabel unLogo = new JLabel(unTest); 
		unLogo.setBounds(17, 30, 1500/size, 650/size);
		
		this.add(unLogo);
		
		// Ajouter une bordure autour des champs pour bien les encadrer
	    this.txtEmail.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    this.txtMdp.setBorder(BorderFactory.createLineBorder(Color.BLACK));

	    // Ajouter un petit padding interne pour améliorer le rendu			
	    this.txtEmail.setMargin(new Insets(2, 2, 2, 2));				
	    this.txtMdp.setMargin(new Insets(2, 2, 2, 2));	
		
		//Construction du panel Formulaire  
		this.panelForm.setBounds(10, 155, 260, 200);
		this.panelForm.setBackground(Color.white);
		this.panelForm.setLayout(new GridLayout(3,2)); //matrice de 3 lignes et de 2 colonnes
		this.panelForm.add(new JLabel("Email")); 
		this.panelForm.add(this.txtEmail);
		this.panelForm.add(new JLabel("MDP")); 
		this.panelForm.add(this.txtMdp);
		this.panelForm.add(this.btAnnuler); 
		this.panelForm.add(this.btSeConnecter);
		this.add(this.panelForm); //ajout du panel dans la fenetre 
		//rendre les boutons ecoutables 
		this.btAnnuler.addActionListener(this);
		this.btSeConnecter.addActionListener(this);
		this.setVisible(true);
	}
 
	@Override
	public void actionPerformed(ActionEvent e) {
		 if (e.getSource() == this.btAnnuler) {
			 this.txtEmail.setText("");
			 this.txtMdp.setText("");
		 }else if (e.getSource() == this.btSeConnecter) {
			 String email = this.txtEmail.getText(); 
			 String mdp = new String (this.txtMdp.getPassword()); 
			 //on vérifie la présence du technicien dans la BDD 
			 User unUser = Controleur.selectWhereUser(email, mdp); 
			 if (unUser == null) {
				 JOptionPane.showMessageDialog(this, "Veuillez vérifier vos idenifiants !", 
						 "Erreur de Connexion", JOptionPane.ERROR_MESSAGE);
			 }else {
				 JOptionPane.showMessageDialog(this, "Bienvenue "+unUser.getNom()
				 + " "+unUser.getPrenom(), 
						 "Connexion à Orange Application", JOptionPane.INFORMATION_MESSAGE);
				 
				 NS.setTechConnecte(unUser);
				 
				 NS.rendreVisible(false); //fermeture de la connexion
				 NS.creerVueGenerale(true); //ouverture du logiciel 
			 }
		 }
	}
}
 
 
