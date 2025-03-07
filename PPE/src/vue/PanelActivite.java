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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Activite;
import controleur.Client;
import controleur.Controleur;
import controleur.Tableau;

public class PanelActivite extends PanelPrincipal implements ActionListener{
	
	private JPanel panelForm = new JPanel();
	
	private JTextField txtNomA = new JTextField();
	private JTextField floatTarifA = new JTextField(); 
	
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btValider = new JButton("Valider");
	private JButton btSupprimer = new JButton("Supprimer");
	
	
	private JTable uneTable ; 
	private Tableau unTableau ;  
	
	private JPanel panelFiltre = new JPanel ();
	private JTextField txtFiltre = new JTextField();
	private JButton btFiltrer = new JButton ("Filtrer");
	
	private JLabel lbNbActivites = new JLabel();
	
	public PanelActivite() {
		super ("Gestion des Activite");
		
		//installation du bouton supprimer 
				this.btSupprimer.setBounds(40, 340, 300, 40);
				this.add(this.btSupprimer); 
				this.btSupprimer.setVisible(false);
				this.btSupprimer.setBackground(Color.red);
				this.btSupprimer.addActionListener(this);
				
				// Ajouter une bordure autour des champs pour bien les encadrer
			    this.txtNomA.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			    this.floatTarifA.setBorder(BorderFactory.createLineBorder(Color.BLACK));

			    // Ajouter un petit padding interne pour améliorer le rendu
			    this.txtNomA.setMargin(new Insets(2, 2, 2, 2));
			    this.floatTarifA.setMargin(new Insets(2, 2, 2, 2));	
		
		//instalation du Panel Formulaire
		
	this.panelForm.setBackground(Color.white);
	this.panelForm.setBounds(40,80,300,220);
	this.panelForm.setLayout(new GridLayout(6,2));
	
	this.panelForm.add(new JLabel("Nom Activite :"));
	this.panelForm.add(this.txtNomA);
	
	this.panelForm.add(new JLabel("Tarif Activite : "));
	this.panelForm.add(this.floatTarifA);
	
	this.panelForm.add(this.btAnnuler);
	this.panelForm.add(this.btValider);
	
	//on ajoute le formulaire dans la vue
	
	this.add(this.panelForm);
	
	//rendre les boutons ecoutables 
			this.btAnnuler.addActionListener(this);
			this.btValider.addActionListener(this);
			
			//installation de la JTable 
			String entetes [] = {"code Activite","Nom Activité", "Tarif Activite"};
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
						txtNomA.setText(unTableau.getValueAt(numLigne, 1).toString());
						floatTarifA.setText(unTableau.getValueAt(numLigne,2).toString());
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
			this.lbNbActivites.setBounds(580,430,400,20);
			this.lbNbActivites.setText("Nombre de Activites:" + this.unTableau.getRowCount());
			this.add(this.lbNbActivites);
			
		} 
		
	public Object[][] obtenirDonnees (String filtre){
		//récuperer les clients de la base de données 
				ArrayList<Activite> lesActivite;
				
				if (filtre.equals("")) {
					lesActivite = Controleur.selectAllActivites();
				}else {
					lesActivite = Controleur.selectLikeActivites (filtre);
				}
			//création d'une matrice de données 
			Object[][] matrice = new Object[lesActivite.size()][6]; 
			int i = 0; 
			for (Activite unActivite: lesActivite) {
				matrice[i][0] = unActivite.getCodeA(); 
				matrice[i][1] = unActivite.getNomActivite(); 
				matrice[i][2] = unActivite.getTarifA(); 
				i++; 
			}
			return matrice ; 
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			 
			if (e.getSource() == this.btAnnuler) {
				this.txtNomA.setText("");
				this.floatTarifA.setText("");
				
				btSupprimer.setVisible(false);
				btValider.setText("Valider");
			}
			else if (e.getSource() == this.btValider && this.btValider.getText().equals("Valider")) {
				
				//recuperer les champs saisis
				String nomA = this.txtNomA.getText(); 
				float Tarif = Float.parseFloat(this.floatTarifA.getText());

				
				//instancier la classe Activite
				Activite unActivite= new Activite(nomA, Tarif); 
				
				//inserer l'Activite dans la BDD 
				Controleur.insertActivite(unActivite);
				
				//on affiche un message d'insertion reussie 
				JOptionPane.showMessageDialog(this, "Insertion réussie de l'Activite.");
				
				//on actualise l'affichage du tableau 
				this.unTableau.setDonnees(this.obtenirDonnees(""));
				
				//on vide les champs 
				this.txtNomA.setText("");
				this.floatTarifA.setText("");
				btSupprimer.setVisible(false);
				btValider.setText("Valider");
			}
			else if (e.getSource() == this.btSupprimer) {
				//on recupere le code de l'activite a supprimer 
				int numLigne , codeA ; 
				numLigne = this.uneTable.getSelectedRow(); 
				codeA = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString()); 
				
				int retour = JOptionPane.showConfirmDialog(this, "Voulez Vous supprimer l'Activite?", 
						"Suppression de l'Activite", JOptionPane.YES_NO_OPTION);
				if (retour ==0) {
							//on supprime de la base de données 
							Controleur.deleteActivite(codeA);
				
							//on actualise l'affichage 
							this.unTableau.setDonnees(this.obtenirDonnees(""));
							JOptionPane.showMessageDialog(this, "Suppression réussie de l'Activite.");
							
							//on vide les champs 
							this.txtNomA.setText("");
							this.floatTarifA.setText("");
							btSupprimer.setVisible(false);
							btValider.setText("Valider");
				}
			}
			else if (e.getSource() == this.btValider && this.btValider.getText().equals("Modifier")) {
				
				//on récupère les données y compris le code 
				int numLigne , codeA; 
				numLigne = this.uneTable.getSelectedRow(); 
				codeA= Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString()); 
				String nomA = this.txtNomA.getText(); 
				float Tarif = Float.parseFloat(this.floatTarifA.getText());

				
				//on modifie dans la bdd 
				Activite unActivite= new Activite(codeA, nomA, Tarif); 
				Controleur.updateActivite(unActivite);
				
				//on actualise l'affichage du tableau 
				this.unTableau.setDonnees(this.obtenirDonnees(""));
				JOptionPane.showMessageDialog(this, "Modification réussie du Activite.");
				
				//message de confirmation et on vide les champs 
				this.txtNomA.setText("");
				this.floatTarifA.setText("");
				btSupprimer.setVisible(false);
				btValider.setText("Valider");
				
			}
			else if (e.getSource() == this.btFiltrer) {
				
				//recuperer le filtre 
				String filtre = this.txtFiltre.getText();
				
				//on actualise l'affichage du tableau 
				this.unTableau.setDonnees(this.obtenirDonnees(filtre));
				
			}
		}
	}
