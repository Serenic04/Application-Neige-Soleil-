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

import controleur.Controleur;
import controleur.Tableau;
import controleur.TypeEquipement;

public class PanelTypeEquipement extends PanelPrincipal implements ActionListener {
    
    private JPanel panelForm = new JPanel();
    private JTextField txtNomType = new JTextField();
    private JTextField txtDescription = new JTextField();
    
    private JButton btAnnuler = new JButton("Annuler");
    private JButton btValider = new JButton("Valider");
    private JButton btSupprimer = new JButton("Supprimer");
    
    private JTable uneTable;
    private Tableau unTableau;
    
    private JPanel panelFiltre = new JPanel();
    private JTextField txtFiltre = new JTextField();
    private JButton btFiltrer = new JButton("Filtrer");
    
    private JLabel lbNbTypeEquipements = new JLabel();
    
    public PanelTypeEquipement() {
        super("Gestion des Types d'Équipements");
        
      //installation du bouton supprimer 
      		this.btSupprimer.setBounds(40, 340, 300, 40);
      		this.add(this.btSupprimer); 
      		this.btSupprimer.setVisible(false);
      		this.btSupprimer.setBackground(Color.red);
      		this.btSupprimer.addActionListener(this);
      		
      	// Ajouter une bordure autour des champs pour bien les encadrer
		    this.txtNomType.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		    this.txtDescription.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		    // Ajouter un petit padding interne pour améliorer le rendu
		    this.txtNomType.setMargin(new Insets(2, 2, 2, 2));
		    this.txtDescription.setMargin(new Insets(2, 2, 2, 2));
		    
        this.panelForm.setBackground(Color.white);
        this.panelForm.setBounds(40, 80, 300, 220);
        this.panelForm.setLayout(new GridLayout(6, 2));
        
        this.panelForm.add(new JLabel("Nom Type :"));
        this.panelForm.add(this.txtNomType);
        
        this.panelForm.add(new JLabel("Description :"));
        this.panelForm.add(this.txtDescription);
        
        this.panelForm.add(this.btAnnuler);
        this.panelForm.add(this.btValider);
        
        this.add(this.panelForm);
        
        this.btAnnuler.addActionListener(this);
        this.btValider.addActionListener(this);
        
        String entetes[] = {"ID Type", "Nom Type", "Description"};
        this.unTableau = new Tableau(this.obtenirDonnees(""), entetes);
        this.uneTable = new JTable(this.unTableau);
        JScrollPane uneScroll = new JScrollPane(this.uneTable);
        uneScroll.setBounds(400, 80, 500, 340);
        this.add(uneScroll);
        
        this.uneTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int numLigne = uneTable.getSelectedRow();
                txtNomType.setText(unTableau.getValueAt(numLigne, 1).toString());
                txtDescription.setText(unTableau.getValueAt(numLigne, 2).toString());
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
        
        this.lbNbTypeEquipements.setBounds(580, 430, 400, 20);
        this.lbNbTypeEquipements.setText("Nombre de Types: " + this.unTableau.getRowCount());
        this.add(this.lbNbTypeEquipements);
    }
    
    public Object[][] obtenirDonnees(String filtre) {
        ArrayList<TypeEquipement> lesTypes;
        lesTypes = (filtre.equals("")) ? Controleur.selectAllTypeEquipements() : Controleur.selectLikeTypeEquipements(filtre);
        Object[][] matrice = new Object[lesTypes.size()][3];
        int i = 0;
        for (TypeEquipement unType : lesTypes) {
            matrice[i][0] = unType.getIdTypeEquipement();
            matrice[i][1] = unType.getNomType();
            matrice[i][2] = unType.getDescription();
            i++;
        }
        return matrice;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btAnnuler) {
            this.txtNomType.setText("");
            this.txtDescription.setText("");
            btSupprimer.setVisible(false);
            btValider.setText("Valider");
        } else if (e.getSource() == this.btValider && this.btValider.getText().equals("Valider")) {
            String nomType = this.txtNomType.getText();
            String description = this.txtDescription.getText();
            TypeEquipement unType = new TypeEquipement(nomType, description);
            Controleur.insertTypeEquipement(unType);
            JOptionPane.showMessageDialog(this, "Insertion réussie.");
            this.unTableau.setDonnees(this.obtenirDonnees(""));
            this.txtNomType.setText("");
            this.txtDescription.setText("");
        } else if (e.getSource() == this.btSupprimer) {
            int numLigne = this.uneTable.getSelectedRow();
            int idType = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString());
            int retour = JOptionPane.showConfirmDialog(this, "Voulez-vous supprimer ?", "Suppression", JOptionPane.YES_NO_OPTION);
            if (retour == 0) {
                Controleur.deleteTypeEquipement(idType);
                this.unTableau.setDonnees(this.obtenirDonnees(""));
                JOptionPane.showMessageDialog(this, "Suppression réussie.");
                this.txtNomType.setText("");
                this.txtDescription.setText("");
            }
        } else if (e.getSource() == this.btValider && this.btValider.getText().equals("Modifier")) {
            int numLigne = this.uneTable.getSelectedRow();
            int idType = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString());
            String nomType = this.txtNomType.getText();
            String description = this.txtDescription.getText();
            TypeEquipement unType = new TypeEquipement(idType, nomType, description);
            Controleur.updateTypeEquipement(unType);
            this.unTableau.setDonnees(this.obtenirDonnees(""));
            JOptionPane.showMessageDialog(this, "Modification réussie.");
            this.txtNomType.setText("");
            this.txtDescription.setText("");
        } else if (e.getSource() == this.btFiltrer) {
            String filtre = this.txtFiltre.getText();
            this.unTableau.setDonnees(this.obtenirDonnees(filtre));
        }
    }
}
