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

import controleur.Client;
import controleur.Controleur;
import controleur.Region;
import controleur.Tableau;

public class PanelRegion extends PanelPrincipal implements ActionListener {

    private JPanel panelForm = new JPanel();

    private JTextField txtCodeR = new JTextField();
    private JTextField txtDepartement = new JTextField();
    private JTextField txtVilleR = new JTextField();

    private JButton btAnnuler = new JButton("Annuler");
    private JButton btValider = new JButton("Valider");
    private JButton btSupprimer = new JButton("Supprimer");

    private JTable uneTable;
    private Tableau unTableau; 
	
	private JPanel panelFiltre = new JPanel ();
	private JTextField txtFiltre = new JTextField();
	private JButton btFiltrer = new JButton ("Filtrer");
	
	private JLabel lbNbRegions = new JLabel();

	public PanelRegion() {
	    super("Gestion des Régions");
	    
	  //installation du bouton supprimer 
		this.btSupprimer.setBounds(40, 340, 300, 40);
		this.add(this.btSupprimer); 
		this.btSupprimer.setVisible(false);
		this.btSupprimer.setBackground(Color.red);
		this.btSupprimer.addActionListener(this);

	    // Ajouter une bordure autour des champs pour bien les encadrer
	    this.txtCodeR.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    this.txtDepartement.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    this.txtVilleR.setBorder(BorderFactory.createLineBorder(Color.BLACK));

	    // Ajouter un petit padding interne pour améliorer le rendu
	    this.txtCodeR.setMargin(new Insets(2, 2, 2, 2));
	    this.txtDepartement.setMargin(new Insets(2, 2, 2, 2));
	    this.txtVilleR.setMargin(new Insets(2, 2, 2, 2));

	    // Installation du Panel Formulaire
	    this.panelForm.setBackground(Color.white);
	    this.panelForm.setBounds(40, 80, 300, 220);
	    this.panelForm.setLayout(new GridLayout(6, 2));

	    this.panelForm.add(new JLabel("Code Région :"));
	    this.panelForm.add(this.txtCodeR);

	    this.panelForm.add(new JLabel("Département :"));
	    this.panelForm.add(this.txtDepartement);

	    this.panelForm.add(new JLabel("Ville :"));
	    this.panelForm.add(this.txtVilleR);

	    this.panelForm.add(this.btAnnuler);
	    this.panelForm.add(this.btValider);

	    this.add(this.panelForm);
	


        // Rendre les boutons écoutables
        this.btAnnuler.addActionListener(this);
        this.btValider.addActionListener(this);

        // Installation de la JTable
        String entetes[] = {"Code Région", "Département", "Ville"};
        this.unTableau = new Tableau(this.obtenirDonnees(""), entetes);
        this.uneTable = new JTable(this.unTableau);
        JScrollPane uneScroll = new JScrollPane(this.uneTable);

        uneScroll.setBounds(400, 80, 500, 340);
        this.add(uneScroll);

        // Implémentation du clic sur une ligne de la table
        this.uneTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseClicked(MouseEvent e) {
                int numLigne = uneTable.getSelectedRow();
                if (numLigne >= 0) {
                    txtCodeR.setText(unTableau.getValueAt(numLigne, 0).toString());
                    txtDepartement.setText(unTableau.getValueAt(numLigne, 1).toString());
                    txtVilleR.setText(unTableau.getValueAt(numLigne, 2).toString());
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
		this.lbNbRegions.setBounds(580,430,400,20);
		this.lbNbRegions.setText("Nombre de Regions:" + this.unTableau.getRowCount());
		this.add(this.lbNbRegions);
    }

	public Object[][] obtenirDonnees (String filtre){
		//récuperer les clients de la base de données 
				ArrayList<Region> lesRegions;
				
				if (filtre.equals("")) {
					lesRegions = Controleur.selectAllRegions();
				}else {
					lesRegions = Controleur.selectLikeRegions (filtre);
				}
        Object[][] matrice = new Object[lesRegions.size()][3];
        int i = 0;
        for (Region uneRegion : lesRegions) {
            matrice[i][0] = uneRegion.getCodeR();
            matrice[i][1] = uneRegion.getDepartement();
            matrice[i][2] = uneRegion.getVilleR();
            i++;
        }
        return matrice;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btAnnuler) {
            this.txtCodeR.setText("");
            this.txtDepartement.setText("");
            this.txtVilleR.setText("");
            btSupprimer.setVisible(false);
            btValider.setText("Valider");
            
        } else if (e.getSource() == this.btValider && this.btValider.getText().equals("Valider")) {
            String codeR = this.txtCodeR.getText();
            int departement = Integer.parseInt(this.txtDepartement.getText());
            String villeR = this.txtVilleR.getText();

            Region uneRegion = new Region(codeR, departement, villeR);
            Controleur.insertRegion(uneRegion);

            JOptionPane.showMessageDialog(this, "Insertion réussie de la Région.");
            this.unTableau.setDonnees(this.obtenirDonnees(""));

            this.txtCodeR.setText("");
            this.txtDepartement.setText("");
            this.txtVilleR.setText("");
            
            btSupprimer.setVisible(false);
            btValider.setText("Valider");
            
            
        } else if (e.getSource() == this.btSupprimer) {
            int numLigne = this.uneTable.getSelectedRow();
            String codeR = this.unTableau.getValueAt(numLigne, 0).toString();

            int retour = JOptionPane.showConfirmDialog(this, "Voulez-vous supprimer cette région ?", "Suppression Région", JOptionPane.YES_NO_OPTION);
            if (retour == 0) {
                Controleur.deleteRegion(codeR);
                this.unTableau.setDonnees(this.obtenirDonnees(""));
                JOptionPane.showMessageDialog(this, "Suppression réussie de la Région.");
                this.txtCodeR.setText("");
                this.txtDepartement.setText("");
                this.txtVilleR.setText("");
                btSupprimer.setVisible(false);
                btValider.setText("Valider");
            }
            
            
        } else if (e.getSource() == this.btValider && this.btValider.getText().equals("Modifier")) {
            int numLigne = this.uneTable.getSelectedRow();
            String codeR = this.unTableau.getValueAt(numLigne, 0).toString();
            int departement = Integer.parseInt(this.txtDepartement.getText());
            String villeR = this.txtVilleR.getText();

            Region uneRegion = new Region(codeR, departement, villeR);
            Controleur.updateRegion(uneRegion);

            this.unTableau.setDonnees(this.obtenirDonnees(""));
            JOptionPane.showMessageDialog(this, "Modification réussie de la Région.");
            this.txtCodeR.setText("");
            this.txtDepartement.setText("");
            this.txtVilleR.setText("");
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
