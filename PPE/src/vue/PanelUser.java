package vue;
 
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
 
import controleur.Controleur;
import controleur.NS;
import controleur.User;
 
public class PanelUser extends PanelPrincipal implements ActionListener{


	private User techConnecte ; 
	private JTextArea txtInfos = new JTextArea();
	
	private JButton btModifier = new JButton("Modifier"); 
	
	private JPanel panelForm = new JPanel();
	
	private JTextField txtNom = new JTextField(); 
	private JTextField txtPrenom = new JTextField();
	private JTextField txtEmail = new JTextField();
	private JTextField txtRole = new JTextField();
	private JPasswordField txtMdp1 = new JPasswordField(); 
	private JPasswordField txtMdp2 = new JPasswordField(); 
	
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btValider= new JButton("Valider");
	
	public PanelUser () {
		super ("Gestion du Profil");
		
		this.techConnecte = NS.getTechConnecte(); 
		
		this.txtInfos.setText(
				  "\n __________________ INFOS PROFIL ______________\n\n"
				+ "     Nom  User   : " + this.techConnecte.getNom()+ "\n\n"
				+ "     Prénom User : " + this.techConnecte.getPrenom()+ "\n\n"
				+ "     Email  User : " + this.techConnecte.getEmail()+ "\n\n"
				+ "     ROLE  User  : " + this.techConnecte.getRole()+ "\n\n"
				+ "   ______________________________________________\n"
				);
		this.txtInfos.setBackground(Color.white);
		this.txtInfos.setBounds(100, 100, 500, 200);
		this.txtInfos.setEditable(false);
		this.add(this.txtInfos); 
		
		this.btModifier.setBounds(240, 320, 120, 40);
		this.add(this.btModifier);
		
		// Ajouter une bordure autour des champs pour bien les encadrer
	    this.txtNom.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    this.txtPrenom.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    this.txtEmail.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    this.txtRole.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    this.txtMdp1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    this.txtMdp2.setBorder(BorderFactory.createLineBorder(Color.BLACK));

	    // Ajouter un petit padding interne pour améliorer le rendu
	    this.txtNom.setMargin(new Insets(2, 2, 2, 2));
	    this.txtPrenom.setMargin(new Insets(2, 2, 2, 2));	
	    this.txtPrenom.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    this.txtEmail.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    this.txtRole.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    this.txtMdp1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    this.txtMdp2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		//installation du panel form 
		this.panelForm.setBackground(Color.white);
		this.panelForm.setBounds(650, 100, 500, 300);
		this.panelForm.setLayout(new GridLayout(8,2));
		
		this.panelForm.add(new JLabel("Nom User :")); 
		this.panelForm.add(this.txtNom); 
		this.panelForm.add(new JLabel("Prénom User :")); 
		this.panelForm.add(this.txtPrenom); 
		this.panelForm.add(new JLabel("Email User :")); 
		this.panelForm.add(this.txtEmail);
		this.panelForm.add(new JLabel("Role du User :")); 
		this.panelForm.add(this.txtRole); 
		this.panelForm.add(new JLabel("Mot de passe :")); 
		this.panelForm.add(this.txtMdp1);
		this.panelForm.add(new JLabel("Confirmation :")); 
		this.panelForm.add(this.txtMdp2); 
		this.panelForm.add(this.btAnnuler); 
		this.panelForm.add(this.btValider);
		this.add(this.panelForm);
		
		this.panelForm.setVisible(false);
		
		//rendre les boutons ecoutables 
		this.btModifier.addActionListener(this);
		this.btAnnuler.addActionListener(this);
		this.btValider.addActionListener(this);
	}
 
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btModifier) {
			this.panelForm.setVisible(true);
			this.txtNom.setText(techConnecte.getNom());
			this.txtPrenom.setText(techConnecte.getPrenom());
			this.txtEmail.setText(techConnecte.getEmail());
			this.txtRole.setText(techConnecte.getRole());
		}
		else if (e.getSource() == this.btAnnuler) {
			this.panelForm.setVisible(false);
			this.txtNom.setText("");
			this.txtPrenom.setText("");
			this.txtEmail.setText("");
			this.txtRole.setText("");
		}
		else if (e.getSource() == this.btValider) {
			String nom = this.txtNom.getText();
			String prenom = this.txtPrenom.getText();
			String email = this.txtEmail.getText();
			String role = this.txtRole.getText();
			String mdp1 = new String (this.txtMdp1.getPassword()); 
			String mdp2 = new String (this.txtMdp2.getPassword()); 
			 
			if (nom.equals("") || prenom.equals("")|| email.equals("") 
					|| role.equals("") || mdp1.equals("") || mdp2.equals("")) {
				JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.");
			}else if ( ! mdp1.equals(mdp2)) {
				JOptionPane.showMessageDialog(this, "Veuillez vérifier les mdps.");
			}else {
				//on actualise les données du technicien 
				techConnecte.setNom(nom);
				techConnecte.setPrenom(prenom);
				techConnecte.setEmail(email);
				techConnecte.setMdp(mdp1);
				techConnecte.setRole(role);
				//on réalise la modification des données dans la BDD 
				Controleur.updateUser(techConnecte);
				//on actualise le Texte Area 
				this.txtInfos.setText(
						  "\n __________________ INFOS PROFIL ______________\n\n"
						+ "     Nom  User   : " + this.techConnecte.getNom()+ "\n\n"
						+ "     Prénom User : " + this.techConnecte.getPrenom()+ "\n\n"
						+ "     Email  User : " + this.techConnecte.getEmail()+ "\n\n"
						+ "     ROLE  User  : " + this.techConnecte.getRole()+ "\n\n"
						+ "   ______________________________________________\n"
						);
				//on rend le formulaire invisible
				this.panelForm.setVisible(false);
				this.txtMdp1.setText("");
				this.txtMdp2.setText("");
			}
			 
		}
		 
	}
 
}
 
 