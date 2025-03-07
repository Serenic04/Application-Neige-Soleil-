package vue;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import controleur.NS;

public class VueGenerale extends JFrame implements ActionListener {

    private JButton btUser = new JButton("User");
    private JButton btClients = new JButton("Clients");
    private JButton btProprietaire = new JButton("Propriétaire");
    private JButton btActivite = new JButton("Activité");
    private JButton btEquipement = new JButton("Équipement");
    private JButton btHabitation = new JButton("Habitation");
    private JButton btRegion = new JButton("Région");
    private JButton btReservation = new JButton("Réservation");
    private JButton btTypeEquipement = new JButton("Type d'Équipement");
    private JButton btStation = new JButton("Station");  // ✅ Ajout du bouton Station
    private JButton btQuitter = new JButton("Quitter");

    private JPanel panelMenu = new JPanel(null); // Null layout pour positionner les boutons

    private static PanelUser unPanelUser = new PanelUser();
    private static PanelClients unPanelClients = new PanelClients();
    private static PanelProprietaire unPanelProprietaire = new PanelProprietaire();
    private static PanelActivite unPanelActivite = new PanelActivite();
    private static PanelEquipement unPanelEquipement = new PanelEquipement();
    private static PanelHabitation unPanelHabitation = new PanelHabitation();
    private static PanelRegion unPanelRegion = new PanelRegion();
    private static PanelReservation unPanelReservation = new PanelReservation();
    private static PanelTypeEquipement unPanelTypeEquipement = new PanelTypeEquipement();
    private static PanelStation unPanelStation = new PanelStation();  // ✅ Ajout du panel Station

    public VueGenerale() {
        this.setTitle("Application Neige et Soleil");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setBounds(0, 0, 1300, 700);
        this.setLayout(null);
        this.getContentPane().setBackground(Color.white);

        // Construction du panel Menu
        this.panelMenu.setBackground(Color.white);
        this.panelMenu.setBounds(0, 10, 1300, 50); // ✅ Augmenté pour inclure "Station"

        int x = 10; // Position de départ des boutons
        int largeur = 120; // Largeur uniforme des boutons
        int hauteur = 30;
        int espace = 10; // 

        // Positionnement des boutons avec espacement uniforme
        btUser.setBounds(x, 10, 80, hauteur);
        x += 80 + espace;
        btClients.setBounds(x, 10, 80, hauteur);
        x += 80 + espace;
        btProprietaire.setBounds(x, 10, largeur, hauteur);
        x += largeur + espace;
        btActivite.setBounds(x, 10, 80, hauteur);
        x += 80 + espace;
        btEquipement.setBounds(x, 10, largeur, hauteur);
        x += largeur + espace;
        btHabitation.setBounds(x, 10, largeur, hauteur);
        x += largeur + espace;
        btRegion.setBounds(x, 10, largeur, hauteur);
        x += largeur + espace;
        btReservation.setBounds(x, 10, largeur, hauteur);
        x += largeur + espace;
        btTypeEquipement.setBounds(x, 10, largeur, hauteur);
        x += largeur + espace;
        btStation.setBounds(x, 10, 80, hauteur);  
        x += 80 + espace;
        btQuitter.setBounds(x, 10, largeur, hauteur);

        // Ajout des boutons au panel menu
        panelMenu.add(btUser);
        panelMenu.add(btClients);
        panelMenu.add(btProprietaire);
        panelMenu.add(btActivite);
        panelMenu.add(btEquipement);
        panelMenu.add(btHabitation);
        panelMenu.add(btRegion);
        panelMenu.add(btReservation);
        panelMenu.add(btTypeEquipement);
        panelMenu.add(btStation);  // ✅ Ajout du bouton Station
        panelMenu.add(btQuitter);

        this.add(panelMenu);

        // Rendre les boutons écoutables
        btUser.addActionListener(this);
        btClients.addActionListener(this);
        btProprietaire.addActionListener(this);
        btActivite.addActionListener(this);
        btEquipement.addActionListener(this);
        btHabitation.addActionListener(this);
        btRegion.addActionListener(this);
        btReservation.addActionListener(this);
        btTypeEquipement.addActionListener(this);
        btStation.addActionListener(this);  // ✅ Ajout de l'écouteur Station
        btQuitter.addActionListener(this);

        // Ajout des panels à la fenêtre
        this.add(unPanelUser);
        this.add(unPanelClients);
        this.add(unPanelProprietaire);
        this.add(unPanelActivite);
        this.add(unPanelEquipement);
        this.add(unPanelHabitation);
        this.add(unPanelRegion);
        this.add(unPanelReservation);
        this.add(unPanelTypeEquipement);
        this.add(unPanelStation);  // ✅ Ajout du panel Station

        var size = 6;
        ImageIcon uneImage = new ImageIcon("src/images/logo.png");
		ImageIcon unTest = new ImageIcon(uneImage.getImage().getScaledInstance((int) 1500/size,(int)  650/size, DO_NOTHING_ON_CLOSE));
		
		JLabel unLogo = new JLabel(unTest); 
		unLogo.setBounds(60, 540, 1500/size, 650/size);
		
		this.add(unLogo);
        
        this.setVisible(true);
    }

    public void afficherPanel(int choix) {
        // Cacher tous les panels
        unPanelUser.setVisible(false);
        unPanelClients.setVisible(false);
        unPanelProprietaire.setVisible(false);
        unPanelActivite.setVisible(false);
        unPanelEquipement.setVisible(false);
        unPanelHabitation.setVisible(false);
        unPanelRegion.setVisible(false);
        unPanelReservation.setVisible(false);
        unPanelTypeEquipement.setVisible(false);
        unPanelStation.setVisible(false);  // ✅ Cacher aussi le panel Station

        // Afficher le panel sélectionné
        switch (choix) {
            case 0 -> unPanelUser.setVisible(true);
            case 1 -> unPanelClients.setVisible(true);
            case 2 -> unPanelProprietaire.setVisible(true);
            case 3 -> unPanelActivite.setVisible(true);
            case 4 -> unPanelEquipement.setVisible(true);
            case 5 -> unPanelHabitation.setVisible(true);
            case 6 -> unPanelRegion.setVisible(true);
            case 7 -> unPanelReservation.setVisible(true);
            case 8 -> unPanelTypeEquipement.setVisible(true);
            case 9 -> unPanelStation.setVisible(true);  // ✅ Ajout de l'affichage pour Station
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btQuitter) {
            int retour = JOptionPane.showConfirmDialog(this, "Voulez-vous quitter l'application ?", 
                "Quitter l'application", JOptionPane.YES_NO_OPTION);

            if (retour == JOptionPane.YES_OPTION) {
                NS.rendreVisible(true);
                NS.creerVueGenerale(false);
            }
        } else if (e.getSource() == btUser) {
            afficherPanel(0);
        } else if (e.getSource() == btClients) {
            afficherPanel(1);
        } else if (e.getSource() == btProprietaire) {
            afficherPanel(2);
        } else if (e.getSource() == btActivite) {
            afficherPanel(3);
        } else if (e.getSource() == btEquipement) {
            afficherPanel(4);
        } else if (e.getSource() == btHabitation) {
            afficherPanel(5);
        } else if (e.getSource() == btRegion) {
            afficherPanel(6);
        } else if (e.getSource() == btReservation) {
            afficherPanel(7);
        } else if (e.getSource() == btTypeEquipement) {
            afficherPanel(8);
        } else if (e.getSource() == btStation) {  // ✅ Ajout du bouton Station
            afficherPanel(9);
        }
    }
}
