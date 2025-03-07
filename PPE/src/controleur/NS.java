package controleur;

import modele.Modele;
import vue.VueConnexion;
import vue.VueGenerale;

public class NS {

	private static VueConnexion uneVueConnexion ; 
	private static VueGenerale uneVueGenerale ; 
	
	private static User techConnecte;
	
	
	
	public static User getTechConnecte() {
		return techConnecte;
	}

	public static void setTechConnecte(User techConnecte) {
		NS.techConnecte = techConnecte;
	}

	public static void main(String[] args) {
		
		uneVueConnexion = new VueConnexion(); 

	}

	public static void creerVueGenerale (boolean action) {
		if (action == true) {
			uneVueGenerale = new VueGenerale(); 
			uneVueGenerale.setVisible(true);
		}else {
			uneVueGenerale.dispose();
		}
	}
	public static void rendreVisible (boolean action) {
		uneVueConnexion.setVisible(action);
	}
}
	