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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

import controleur.Station;
import controleur.Controleur;
import controleur.Proprietaire;
import controleur.Region;
import controleur.Tableau;

public class PanelStation extends PanelPrincipal implements ActionListener {
    private JPanel panelForm = new JPanel();
    private JPanel panelListe = new JPanel();
    
    private JTextField txtNomS = new JTextField();
    private JTextField txtEtoiles = new JTextField();
    private JComboBox<String> txtCodeR = new JComboBox<String>();	
    
    private JButton btAnnuler = new JButton("Annuler");
    private JButton btValider = new JButton("Valider");
    private JButton btSupprimer = new JButton("Supprimer");
    
    private JTable uneTable;
    private Tableau unTableau;
    
    private JPanel panelFiltre = new JPanel();
    private JTextField txtFiltre = new JTextField();
    private JButton btFiltrer = new JButton("Filtrer");
    
    
    public void remplirCBXCodeR () {
		ArrayList<Region> LesRegions = Controleur.selectAllRegions();
		
		for (Region uneRegion : LesRegions) {
			this.txtCodeR.addItem(uneRegion.getCodeR());
		}
	}
    
    public PanelStation() {
        super("Gestion des stations");
        
        this.remplirCBXCodeR();
        
      //installation du bouton supprimer 
      		this.btSupprimer.setBounds(40, 340, 300, 40);
      		this.add(this.btSupprimer); 
      		this.btSupprimer.setVisible(false);
      		this.btSupprimer.setBackground(Color.red);
      		this.btSupprimer.addActionListener(this);
      		
      	// Ajouter une bordure autour des champs pour bien les encadrer
		    this.txtNomS.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		    this.txtEtoiles.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		    this.txtCodeR.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		    // Ajouter un petit padding interne pour améliorer le rendu
		    this.txtNomS.setMargin(new Insets(2, 2, 2, 2));
		    this.txtEtoiles.setMargin(new Insets(2, 2, 2, 2));	

        this.btSupprimer.setBounds(40, 260, 300, 40);
        this.add(this.btSupprimer);
        this.btSupprimer.setVisible(false);
        this.btSupprimer.setBackground(Color.red);
        this.btSupprimer.addActionListener(this);
        
        this.panelForm.setBackground(Color.white);
        this.panelForm.setBounds(40, 80, 300, 170);
        this.panelForm.setLayout(new GridLayout(4, 2));
        
        this.panelForm.add(new JLabel("Nom Station :"));
        this.panelForm.add(this.txtNomS);
        
        this.panelForm.add(new JLabel("Nombre d'Étoiles :"));
        this.panelForm.add(this.txtEtoiles);
        
        this.panelForm.add(new JLabel("Code Région :"));
        this.panelForm.add(this.txtCodeR);
        
        this.panelForm.add(this.btAnnuler);
        this.panelForm.add(this.btValider);
        
        this.add(this.panelForm);
        
        this.btAnnuler.addActionListener(this);
        this.btValider.addActionListener(this);
        
        String entetes[] = {"Code Station", "Nom", "Étoiles", "Code Région"};
        this.unTableau = new Tableau(this.obtenirDonnees(""), entetes);
        this.uneTable = new JTable(this.unTableau);
        JScrollPane uneScroll = new JScrollPane(this.uneTable);
        
        uneScroll.setBounds(400, 80, 500, 300);
        this.add(uneScroll);

        this.uneTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int numLigne = uneTable.getSelectedRow();
                txtNomS.setText(unTableau.getValueAt(numLigne, 1).toString());
                txtEtoiles.setText(unTableau.getValueAt(numLigne, 2).toString());
                btSupprimer.setVisible(true);
                btValider.setText("Modifier");
            }
            public void mouseReleased(MouseEvent e) {}
            public void mousePressed(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
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
    }

    public Object[][] obtenirDonnees(String filtre) {
        ArrayList<Station> lesStations = filtre.equals("") ? Controleur.selectAllStations() : Controleur.selectLikeStations(filtre);
        Object[][] matrice = new Object[lesStations.size()][4];
        int i = 0;
        for (Station uneStation : lesStations) {
            matrice[i][0] = uneStation.getCodeS();
            matrice[i][1] = uneStation.getNomS();
            matrice[i][2] = uneStation.getEtoiles();
            matrice[i][3] = uneStation.getCodeR();
            i++;
        }
        return matrice;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btAnnuler) {
            this.txtNomS.setText("");
            this.txtEtoiles.setText("");
            btSupprimer.setVisible(false);
            btValider.setText("Valider");
            
        } else if (e.getSource() == this.btValider && this.btValider.getText().equals("Valider")) {
        	
        	
        	String tab[]= this.txtCodeR.getSelectedItem().toString().split("-");
        	String CodeR = tab[0];
        	
            Station uneStation = new Station(
            		this.txtNomS.getText(), 
            		Integer.parseInt(this.txtEtoiles.getText()),
            		CodeR);
            
            Controleur.insertStation(uneStation);
            JOptionPane.showMessageDialog(this, "Insertion réussie de la station.");
            this.unTableau.setDonnees(this.obtenirDonnees(""));
            this.btAnnuler.doClick();
            
        } else if (e.getSource() == this.btSupprimer) {
        	
            int numLigne = this.uneTable.getSelectedRow();
            int codeS = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString());
            
            if (JOptionPane.showConfirmDialog(this, "Voulez-vous supprimer la station ?", "Suppression", JOptionPane.YES_NO_OPTION) == 0) {
                Controleur.deleteStation(codeS);
                this.unTableau.setDonnees(this.obtenirDonnees(""));
                JOptionPane.showMessageDialog(this, "Suppression réussie de la station.");
                this.btAnnuler.doClick();
            }
        } else if (e.getSource() == this.btValider && this.btValider.getText().equals("Modifier")) {
        	
            int numLigne = this.uneTable.getSelectedRow();
            
            String tab[]= this.txtCodeR.getSelectedItem().toString().split("-");
        	String CodeR = tab[0];
            int codeS = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString());
            Station uneStation = new Station(codeS, 
            		this.txtNomS.getText(), 
            		Integer.parseInt(this.txtEtoiles.getText()), 
            		CodeR);
            
            
            Controleur.updateStation(uneStation);
            this.unTableau.setDonnees(this.obtenirDonnees(""));
            JOptionPane.showMessageDialog(this, "Modification réussie de la station.");
            this.btAnnuler.doClick();
        } else if (e.getSource() == this.btFiltrer) {
            this.unTableau.setDonnees(this.obtenirDonnees(this.txtFiltre.getText()));
        }
    }
}
