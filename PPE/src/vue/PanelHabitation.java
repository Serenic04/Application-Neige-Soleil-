
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
import controleur.Habitation;
import controleur.Proprietaire;
import controleur.Region;
import controleur.Tableau;
import controleur.TypeEquipement;

public class PanelHabitation extends PanelPrincipal implements ActionListener {
    
    private JPanel panelForm = new JPanel();
    
    private JTextField txtAdresse = new JTextField();
    private JTextField intTaille = new JTextField();
    private JComboBox<String> intIdProprietaire = new JComboBox<String>();
    private JComboBox<String> txtCodeR = new JComboBox<String>();	
    
    private JButton btAnnuler = new JButton("Annuler");
    private JButton btValider = new JButton("Valider");
    private JButton btSupprimer = new JButton("Supprimer");
    
    private JTable uneTable;
    private Tableau unTableau; 
	
	private JPanel panelFiltre = new JPanel ();
	private JTextField txtFiltre = new JTextField();
	private JButton btFiltrer = new JButton ("Filtrer");
	
	private JLabel lbNbHabitation = new JLabel();
	
	public void remplirCBXCodeR () {
		ArrayList<Region> LesRegions = Controleur.selectAllRegions();
		
		for (Region uneRegion : LesRegions) {
			this.txtCodeR.addItem(uneRegion.getCodeR());
		}
	}
	
	
	public void remplirCBXIDProprietaire () {
		ArrayList<Proprietaire> LesProprietaires = Controleur.selectAllProprietaires();
		
		for (Proprietaire unProprietaire : LesProprietaires) {
			this.intIdProprietaire.addItem(unProprietaire.getIdProprietaire() + "-" + unProprietaire.getNom());
		}
	}
    
    public PanelHabitation() {
        super("Gestion des Habitations");
        
        
        this.remplirCBXIDProprietaire();
        this.remplirCBXCodeR();
        
      //installation du bouton supprimer 
      		this.btSupprimer.setBounds(40, 340, 300, 40);
      		this.add(this.btSupprimer); 
      		this.btSupprimer.setVisible(false);
      		this.btSupprimer.setBackground(Color.red);
      		this.btSupprimer.addActionListener(this);
      		
      	// Ajouter une bordure autour des champs pour bien les encadrer
		    this.txtAdresse.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		    this.intTaille.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		    this.intIdProprietaire.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		    this.txtCodeR.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		    // Ajouter un petit padding interne pour améliorer le rendu
		    this.txtAdresse.setMargin(new Insets(2, 2, 2, 2));
		    this.intTaille.setMargin(new Insets(2, 2, 2, 2));			
        
        // Installation du Panel Formulaire
        this.panelForm.setBackground(Color.white);
        this.panelForm.setBounds(40, 80, 300, 220);
        this.panelForm.setLayout(new GridLayout(6, 2));
        
        this.panelForm.add(new JLabel("Adresse :"));
        this.panelForm.add(this.txtAdresse);
        
        this.panelForm.add(new JLabel("Taille :"));
        this.panelForm.add(this.intTaille);
        
        this.panelForm.add(new JLabel("ID Propriétaire :"));
        this.panelForm.add(this.intIdProprietaire);
        
        this.panelForm.add(new JLabel("Code Région :"));
        this.panelForm.add(this.txtCodeR);
        
        this.panelForm.add(this.btAnnuler);
        this.panelForm.add(this.btValider);
        
        this.add(this.panelForm);
        
        // Rendre les boutons écoutables
        this.btAnnuler.addActionListener(this);
        this.btValider.addActionListener(this);
        
        // Installation de la JTable
        String entetes[] = {"ID Habitation", "Adresse", "Taille", "ID Propriétaire", "Code Région"};
        this.unTableau = new Tableau(this.obtenirDonnees(""), entetes);
        this.uneTable = new JTable(this.unTableau);
        JScrollPane uneScroll = new JScrollPane(this.uneTable);
        
        uneScroll.setBounds(400, 80, 500, 340);
        this.add(uneScroll);
        
        // Gestion du clic sur une ligne de la table
        this.uneTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int numLigne = uneTable.getSelectedRow();
                txtAdresse.setText(unTableau.getValueAt(numLigne, 1).toString());
                intTaille.setText(unTableau.getValueAt(numLigne, 2).toString());
                btSupprimer.setVisible(true);
                btValider.setText("Modifier");
            }
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
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
		this.lbNbHabitation.setBounds(580,430,400,20);
		this.lbNbHabitation.setText("Nombre d'Habitation :" + this.unTableau.getRowCount());
		this.add(this.lbNbHabitation);
    }
    
	public Object[][] obtenirDonnees (String filtre){
		//récuperer les clients de la base de données 
				ArrayList<Habitation> lesHabitations;
				
				if (filtre.equals("")) {
					lesHabitations = Controleur.selectAllHabitations();
				}else {
					lesHabitations= Controleur.selectLikeHabitations(filtre);
				}
        Object[][] matrice = new Object[lesHabitations.size()][5];
        int i = 0;
        for (Habitation uneHabitation : lesHabitations) {
            matrice[i][0] = uneHabitation.getIdHabitation();
            matrice[i][1] = uneHabitation.getAdresse();
            matrice[i][2] = uneHabitation.getTaille();
            matrice[i][3] = uneHabitation.getIdProprietaire();
            matrice[i][4] = uneHabitation.getCodeR();
            i++;
        }
        return matrice;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btAnnuler) {
            this.txtAdresse.setText("");
            this.intTaille.setText("");
            
            btSupprimer.setVisible(false);
            btValider.setText("Valider");
            
            
        } else if (e.getSource() == this.btValider && this.btValider.getText().equals("Valider")) {
        	
        	
        
            String adresse = this.txtAdresse.getText();
            int taille = Integer.parseInt(this.intTaille.getText());
			String tab[]= this.intIdProprietaire.getSelectedItem().toString().split("-");
			int idProprietaire = Integer.parseInt(tab[0]);
			tab = this.txtCodeR.getSelectedItem().toString().split("-");
        	String CodeR = tab[0];
            
            Habitation uneHabitation = new Habitation(adresse, taille, idProprietaire, CodeR);
            Controleur.insertHabitation(uneHabitation);
            JOptionPane.showMessageDialog(this, "Insertion réussie de l'habitation.");
            this.unTableau.setDonnees(this.obtenirDonnees(""));
            
            
        } else if (e.getSource() == this.btSupprimer) {
            int numLigne = this.uneTable.getSelectedRow();
            int idHabitation = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString());
            int retour = JOptionPane.showConfirmDialog(this, "Voulez-vous supprimer cette habitation ?", "Suppression", JOptionPane.YES_NO_OPTION);
            if (retour == 0) {
                Controleur.deleteHabitation(idHabitation);
                this.unTableau.setDonnees(this.obtenirDonnees(""));
                JOptionPane.showMessageDialog(this, "Suppression réussie.");
            }
            
            
        } else if (e.getSource() == this.btValider && this.btValider.getText().equals("Modifier")) {
            int numLigne = this.uneTable.getSelectedRow();
            int idHabitation = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString());
            String adresse = this.txtAdresse.getText();
            int taille = Integer.parseInt(this.intTaille.getText());


			String tab[]= this.intIdProprietaire.getSelectedItem().toString().split("-");
			int idProprietaire = Integer.parseInt(tab[0]);
			tab = this.txtCodeR.getSelectedItem().toString().split("-");
        	String CodeR = tab[0];
            
            Habitation uneHabitation = new Habitation(idHabitation, adresse, taille, idProprietaire, CodeR);
            Controleur.updateHabitation(uneHabitation);
            this.unTableau.setDonnees(this.obtenirDonnees(""));
            JOptionPane.showMessageDialog(this, "Modification réussie.");
        }
		else if (e.getSource() == this.btFiltrer) {
			
			//recuperer le filtre 
			String filtre = this.txtFiltre.getText();
			
			//on actualise l'affichage du tableau 
			this.unTableau.setDonnees(this.obtenirDonnees(filtre));
			
		}        
    }
}
