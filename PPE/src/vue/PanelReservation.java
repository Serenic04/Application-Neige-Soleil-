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
import controleur.Reservation;
import controleur.Tableau;

public class PanelReservation extends PanelPrincipal implements ActionListener {
    
    private JPanel panelForm = new JPanel();
    
    private JTextField txtDateR = new JTextField();
    private JTextField txtDateHeureDebut = new JTextField();
    private JTextField txtDateHeureFin = new JTextField();
    private JTextField txtNbPersonne = new JTextField();
	private JComboBox<String> txtIdClient = new JComboBox<String>();	
    
    private JButton btAnnuler = new JButton("Annuler");
    private JButton btValider = new JButton("Valider");
    private JButton btSupprimer = new JButton("Supprimer");
    
    private JTable uneTable;
    private Tableau unTableau; 
	
	private JPanel panelFiltre = new JPanel ();
	private JTextField txtFiltre = new JTextField();
	private JButton btFiltrer = new JButton ("Filtrer");
	
	private JLabel lbNbReservations = new JLabel();
    
    public PanelReservation() {
        super("Gestion des Réservations");

		
		//remplir la cle etrangere client
		this.remplirCBXIDClient ();
		
      //installation du bouton supprimer 
  		this.btSupprimer.setBounds(40, 340, 300, 40);
  		this.add(this.btSupprimer); 
  		this.btSupprimer.setVisible(false);
  		this.btSupprimer.setBackground(Color.red);
  		this.btSupprimer.addActionListener(this);        
      		
      	// Ajouter une bordure autour des champs pour bien les encadrer
	    this.txtDateR.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    this.txtDateHeureDebut.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    this.txtDateHeureFin.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    this.txtNbPersonne.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    this.txtIdClient.setBorder(BorderFactory.createLineBorder(Color.BLACK));

	    // Ajouter un petit padding interne pour améliorer le rendu
	    this.txtDateR.setMargin(new Insets(2, 2, 2, 2));
	    this.txtDateHeureDebut.setMargin(new Insets(2, 2, 2, 2));
	    this.txtDateHeureFin.setMargin(new Insets(2, 2, 2, 2));				
	    this.txtNbPersonne.setMargin(new Insets(2, 2, 2, 2));				
        
        
        this.panelForm.setBackground(Color.white);
        this.panelForm.setBounds(40, 80, 300, 220);
        this.panelForm.setLayout(new GridLayout(7, 2));
        
        this.panelForm.add(new JLabel("Date Réservation :"));
        this.panelForm.add(this.txtDateR);
        
        this.panelForm.add(new JLabel("Date Heure Début :"));
        this.panelForm.add(this.txtDateHeureDebut);
        
        this.panelForm.add(new JLabel("Date Heure Fin :"));
        this.panelForm.add(this.txtDateHeureFin);
        
        this.panelForm.add(new JLabel("Nombre de Personnes :"));
        this.panelForm.add(this.txtNbPersonne);
        
        this.panelForm.add(new JLabel("ID Client :"));
        this.panelForm.add(this.txtIdClient);
        
        this.panelForm.add(this.btAnnuler);
        this.panelForm.add(this.btValider);
        
        this.add(this.panelForm);
        
        this.btAnnuler.addActionListener(this);
        this.btValider.addActionListener(this);
        
        
        String entetes[] = {"Référence", "Date", "Début", "Fin", "Personnes", "ID Client"};
        this.unTableau = new Tableau(this.obtenirDonnees(""), entetes);
        this.uneTable = new JTable(this.unTableau);
        JScrollPane uneScroll = new JScrollPane(this.uneTable);
        
        uneScroll.setBounds(400, 80, 500, 340);
        this.add(uneScroll);
        
        this.uneTable.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                int numLigne = uneTable.getSelectedRow();
                txtDateR.setText(unTableau.getValueAt(numLigne, 1).toString());
                txtDateHeureDebut.setText(unTableau.getValueAt(numLigne, 2).toString());
                txtDateHeureFin.setText(unTableau.getValueAt(numLigne, 3).toString());
                txtNbPersonne.setText(unTableau.getValueAt(numLigne, 4).toString());
                btSupprimer.setVisible(true);
                btValider.setText("Modifier");
            }
           
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        });
     // Installation du panel filtre
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
		this.lbNbReservations.setBounds(580,430,400,20);
		this.lbNbReservations.setText("Nombre de Reservation :" + this.unTableau.getRowCount());
		this.add(this.lbNbReservations);
    }
    
    public void remplirCBXIDClient () {
		ArrayList<Client> LesClients = Controleur.selectAllClients();
		
		for (Client unClient : LesClients) {
			this.txtIdClient.addItem(unClient.getIdclient() + "-" + unClient.getNom());
		}
	}
    
	public Object[][] obtenirDonnees (String filtre){
		//récuperer les clients de la base de données 
				ArrayList<Reservation> lesReservations ;
				
				if (filtre.equals("")) {
					lesReservations = Controleur.selectAllReservations();
				}else {
					lesReservations = Controleur.selectLikeReservations(filtre);
				}
        Object[][] matrice = new Object[lesReservations.size()][6];
        int i = 0;
        for (Reservation uneReservation : lesReservations) {
            matrice[i][0] = uneReservation.getRefR();
            matrice[i][1] = uneReservation.getDateR();
            matrice[i][2] = uneReservation.getDateHeureDebut();
            matrice[i][3] = uneReservation.getDateHeureFin();
            matrice[i][4] = uneReservation.getNbPersonne();
            matrice[i][5] = uneReservation.getIdClient();
            i++;
        }
        return matrice;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.btAnnuler) {
            this.txtDateR.setText("");
            this.txtDateHeureDebut.setText("");
            this.txtDateHeureFin.setText("");
            this.txtNbPersonne.setText("");
            
            btSupprimer.setVisible(false);
            btValider.setText("Valider");
            
            
        } else if (e.getSource() == this.btValider && this.btValider.getText().equals("Valider")) {
            String dateR = this.txtDateR.getText();
            String dateHeureDebut = this.txtDateHeureDebut.getText();
            String dateHeureFin = this.txtDateHeureFin.getText();
            int nbPersonne = Integer.parseInt(this.txtNbPersonne.getText());
            String tab[]= this.txtIdClient.getSelectedItem().toString().split("-");
			int idclient = Integer.parseInt(tab[0]);
            
            Reservation uneReservation = new Reservation(dateR, dateHeureDebut, dateHeureFin, nbPersonne, idclient);
            Controleur.insertReservation(uneReservation);
            
            JOptionPane.showMessageDialog(this, "Insertion réussie de la Réservation.");
            this.unTableau.setDonnees(this.obtenirDonnees(""));
            this.btAnnuler.doClick();
            
            
        } else if (e.getSource() == this.btSupprimer) {
            int numLigne = this.uneTable.getSelectedRow();
            int refR = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString());
            
            if (JOptionPane.showConfirmDialog(this, "Voulez-vous supprimer cette réservation ?", "Suppression", JOptionPane.YES_NO_OPTION) == 0) {
                Controleur.deleteReservation(refR);
                this.unTableau.setDonnees(this.obtenirDonnees(""));
                JOptionPane.showMessageDialog(this, "Suppression réussie de la Réservation.");
                this.btAnnuler.doClick();
            }
    		else if (e.getSource() == this.btFiltrer) {
    			
    			//recuperer le filtre 
    			String filtre = this.txtFiltre.getText();
    			
    			//on actualise l'affichage du tableau 
    			this.unTableau.setDonnees(this.obtenirDonnees(filtre));
    			
    		}
        }
    }

    

}
