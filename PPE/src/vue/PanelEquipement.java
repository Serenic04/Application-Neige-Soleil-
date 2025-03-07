package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Client;
import controleur.Controleur;
import controleur.Equipement;
import controleur.Tableau;
import controleur.TypeEquipement;

public class PanelEquipement extends PanelPrincipal implements ActionListener{
	
	private JPanel panelForm = new JPanel();
	
	private JTextField txtNomE = new JTextField();
	private JTextField intQteE = new JTextField();
	private JComboBox<String> intIdTypeE = new JComboBox<String>();	
	
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btValider = new JButton("Valider");
	private JButton btSupprimer = new JButton("Supprimer");
	
	
	private JTable uneTable ; 
	private Tableau unTableau ;  
	
	private JPanel panelFiltre = new JPanel ();
	private JTextField txtFiltre = new JTextField();
	private JButton btFiltrer = new JButton ("Filtrer");
	
	private JLabel lbNbEquipement = new JLabel();
	
	public void remplirCBXIDTypeE () {
		ArrayList<TypeEquipement> LesTypeE = Controleur.selectAllTypeEquipements();
		
		for (TypeEquipement unTypeE : LesTypeE) {
			this.intIdTypeE.addItem(unTypeE.getIdTypeEquipement() + "-" + unTypeE.getNomType());
		}
	}
	
	
	public PanelEquipement() {
		super ("Gestion des Equipements");
		
		this.remplirCBXIDTypeE();
		
		//installation du bouton supprimer 
				this.btSupprimer.setBounds(40, 340, 300, 40);
				this.add(this.btSupprimer); 
				this.btSupprimer.setVisible(false);
				this.btSupprimer.setBackground(Color.red);
				this.btSupprimer.addActionListener(this);
				
				// Ajouter une bordure autour des champs pour bien les encadrer
			    this.txtNomE.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			    this.intQteE.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			    this.intIdTypeE.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			    // Ajouter un petit padding interne pour améliorer le rendu
			    this.txtNomE.setMargin(new Insets(2, 2, 2, 2));
			    this.intQteE.setMargin(new Insets(2, 2, 2, 2));		
		//instalation du Panel Formulaire
		
	this.panelForm.setBackground(Color.white);
	this.panelForm.setBounds(40,80,300,220);
	this.panelForm.setLayout(new GridLayout(6,2));
	
	this.panelForm.add(new JLabel("Nom Equipement :"));
	this.panelForm.add(this.txtNomE);
	
	this.panelForm.add(new JLabel("Quantité Equipement : "));
	this.panelForm.add(this.intQteE);
	
	this.panelForm.add(new JLabel("ID Type Equipement : "));
	this.panelForm.add(this.intIdTypeE);
	
	this.panelForm.add(this.btAnnuler);
	this.panelForm.add(this.btValider);
	
	//on ajoute le formulaire dans la vue
	
	this.add(this.panelForm);
	
	//rendre les boutons ecoutables 
	this.btAnnuler.addActionListener(this);
	this.btValider.addActionListener(this);
	
	//installation de la JTable 
	String entetes [] = {"id Equipement","Nom Equipement", "Quantité Equipement", "ID Type Equipement"};
	this.unTableau = new Tableau (this.obtenirDonnees(""), entetes); 
	this.uneTable = new JTable(this.unTableau); 
	JScrollPane uneScroll = new JScrollPane(this.uneTable); 
	
	uneScroll.setBounds(400, 80, 500, 340);
	this.add(uneScroll); 
	
	//implementation du click sur une ligne de la table 
	this.uneTable.addMouseListener(new MouseListener() {
		
		@Override
		public void mouseReleased(MouseEvent e) {
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {	
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			int numLigne = 0 ; 
			
			if (e.getClickCount() >= 1) {
				numLigne = uneTable.getSelectedRow(); 
				txtNomE.setText(unTableau.getValueAt(numLigne, 1).toString());
				intQteE.setText(unTableau.getValueAt(numLigne,2).toString());
				btSupprimer.setVisible(true);
				btValider.setText("Modifier");
			}
			
		}
	});
	// Installation du panel filtre
    this.txtFiltre.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    this.txtFiltre.setMargin(new Insets(2, 2, 2, 2));

    this.panelFiltre.setBackground(Color.white);
    this.panelFiltre.setLayout(new GridLayout(1, 3));
    this.panelFiltre.setBounds(400, 50, 500, 20);
    this.panelFiltre.add(new JLabel("Filtrer les Regions par :"));
    this.panelFiltre.add(this.txtFiltre);
    this.panelFiltre.add(this.btFiltrer);
    this.btFiltrer.addActionListener(this);
    this.add(this.panelFiltre);
	
	//instalation du Label NB Client
	this.lbNbEquipement.setBounds(580,430,400,20);
	this.lbNbEquipement.setText("Nombre d'Equipement:" + this.unTableau.getRowCount());
	this.add(this.lbNbEquipement);
	
	}
	
	public Object[][] obtenirDonnees (String filtre){
		//récuperer les clients de la base de données 
				ArrayList<Equipement> lesEquipements;
				
				if (filtre.equals("")) {
					lesEquipements= Controleur.selectAllEquipements();
				}else {
					lesEquipements = Controleur.selectLikeEquipements(filtre);
				}
		//création d'une matrice de données 
		Object[][] matrice = new Object[lesEquipements.size()][6]; 
		int i = 0; 
		for (Equipement unEquipement: lesEquipements) {
			matrice[i][0] = unEquipement.getIdEquipement(); 
			matrice[i][1] = unEquipement.getNomEquipement(); 
			matrice[i][2] = unEquipement.getQteEquipement(); 
			matrice[i][3] = unEquipement.getIdTypeEquipement(); 
			i++; 
		}
		return matrice ; 
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		 
		if (e.getSource() == this.btAnnuler) {
			this.txtNomE.setText("");
			this.intQteE.setText("");

			
			btSupprimer.setVisible(false);
			btValider.setText("Valider");
		}
		else if (e.getSource() == this.btValider && this.btValider.getText().equals("Valider")) {
			
			//recuperer les champs saisis
			String nomE = this.txtNomE.getText(); 
			int QteE = Integer.parseInt(this.intQteE.getText());
			String tab[]= this.intIdTypeE.getSelectedItem().toString().split("-");
			int IdTypeE = Integer.parseInt(tab[0]);
			

	
			
			//instancier la classe Equipement
			Equipement unEquipement= new Equipement(nomE, QteE ,IdTypeE); 
			
			//inserer l'Equipement dans la BDD 
			Controleur.insertEquipement(unEquipement);
			
			//on affiche un message d'insertion reussie 
			JOptionPane.showMessageDialog(this, "Insertion réussie de l'Equipement.");
			
			//on actualise l'affichage du tableau 
			this.unTableau.setDonnees(this.obtenirDonnees(""));
			
			//on vide les champs 
			this.txtNomE.setText("");
			this.intQteE.setText("");
			btSupprimer.setVisible(false);
			btValider.setText("Valider");
		}
		else if (e.getSource() == this.btSupprimer) {
			//on recupere le code de l'Equipement a supprimer 
			int numLigne , idEquipement ; 
			numLigne = this.uneTable.getSelectedRow(); 
			idEquipement = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString()); 
			
			int retour = JOptionPane.showConfirmDialog(this, "Voulez Vous supprimer l'Equipement?", 
					"Suppression de l'Equipement", JOptionPane.YES_NO_OPTION);
			if (retour ==0) {
						//on supprime de la base de données 
						Controleur.deleteEquipement(idEquipement);
			
						//on actualise l'affichage 
						this.unTableau.setDonnees(this.obtenirDonnees(""));
						JOptionPane.showMessageDialog(this, "Suppression réussie de l'Equipement.");
						
						//on vide les champs 
						this.txtNomE.setText("");
						this.intQteE.setText("");
						btSupprimer.setVisible(false);
						btValider.setText("Valider");
			}
		}
		else if (e.getSource() == this.btValider && this.btValider.getText().equals("Modifier")) {
			
			//on récupère les données y compris le code 
			int numLigne , idEquipement; 
			numLigne = this.uneTable.getSelectedRow(); 
			idEquipement= Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString()); 
			String nomE = this.txtNomE.getText(); 
			int QteE = Integer.parseInt(this.intQteE.getText());
			String tab[]= this.intIdTypeE.getSelectedItem().toString().split("-");
			int IdTypeE = Integer.parseInt(tab[0]);
			
	
			
			//on modifie dans la bdd 
			Equipement unEquipement= new Equipement(idEquipement, nomE, QteE, IdTypeE); 
			Controleur.updateEquipement(unEquipement);
			
			//on actualise l'affichage du tableau 
			this.unTableau.setDonnees(this.obtenirDonnees(""));
			JOptionPane.showMessageDialog(this, "Modification réussie du Equipement.");
			
			//message de confirmation et on vide les champs 
			this.txtNomE.setText("");
			this.intQteE.setText("");
			btSupprimer.setVisible(false); 
			btValider.setText("Valider");
			
		}
	}
	}
