package controleur;

import java.util.ArrayList;

import modele.Modele;

public class Controleur {
	/************************* Gestion des clients ******************/
	public static void insertClient (Client unClient) {
		//on va controler les données avant insertion 
		
		//on apelle le modele pour insertion 
		Modele.insertClient(unClient);
	}
	
	public static void deleteClient(int idclient) {
		Modele.deleteClient(idclient);
	}
	
	public static void updateClient(Client unClient) {
		Modele.updateClient(unClient);
	}
	
	public static Client selectWhereClient(int idclient) {
		return Modele.selectWhereClient(idclient);
	}
	
	public static ArrayList<Client> selectAllClients(){
		return Modele.selectAllClients(); 
	}
	public static ArrayList<Client> selectLikeClients(String filtre){
		return Modele.selectLikeClients(filtre); 
	}
	/************************* Gestion des Reservation ******************/
	public static void insertReservation(Reservation uneReservation) {
	    Modele.insertReservation(uneReservation);
	}

	public static void deleteReservation(int refR) {
	    Modele.deleteReservation(refR);
	}

	public static void updateReservation(Reservation uneReservation) {
	    Modele.updateReservation(uneReservation);
	}

	public static Reservation selectWhereReservation(int refR) {
	    return Modele.selectWhereReservation(refR);
	}

	public static ArrayList<Reservation> selectAllReservations() {
	    return Modele.selectAllReservations();
	}

	public static ArrayList<Reservation> selectLikeReservations(String filtre) {
	    return Modele.selectLikeReservations(filtre);
	}
 /************************* Gestion des ContratLoc ******************/
	
	public static void insertContratLoc(ContratLoc unContratLoc) {
	    Modele.insertContratLoc(unContratLoc);
	}

	public static void deleteContratLoc(int refC) {
	    Modele.deleteContratLoc(refC);
	}

	public static void updateContratLoc(ContratLoc unContratLoc) {
	    Modele.updateContratLoc(unContratLoc);
	}

	public static ContratLoc selectWhereContratLoc(int refC) {
	    return Modele.selectWhereContratLoc(refC);
	}

	public static ArrayList<ContratLoc> selectAllContratLocs() {
	    return Modele.selectAllContratLoc();
	}

	public static ArrayList<ContratLoc> selectLikeContratLocs(String filtre) {
	    return Modele.selectLikeContratLoc(filtre);
	}
	 /************************* Gestion des TypeEquipement ******************/
	public static void insertTypeEquipement(TypeEquipement unTypeEquipement) {
	    Modele.insertTypeEquipement(unTypeEquipement);
	}

	public static void deleteTypeEquipement(int idTypeEquipement) {
	    Modele.deleteTypeEquipement(idTypeEquipement);
	}

	public static void updateTypeEquipement(TypeEquipement unTypeEquipement) {
	    Modele.updateTypeEquipement(unTypeEquipement);
	}

	public static TypeEquipement selectWhereTypeEquipement(int idTypeEquipement) {
	    return Modele.selectWhereTypeEquipement(idTypeEquipement);
	}

	public static ArrayList<TypeEquipement> selectAllTypeEquipements() {
	    return Modele.selectAllTypeEquipements();
	}

	public static ArrayList<TypeEquipement> selectLikeTypeEquipements(String filtre) {
	    return Modele.selectLikeTypeEquipements(filtre);
	}
	/************************* Gestion des Equipement ******************/
	public static void insertEquipement(Equipement unEquipement) {
	    Modele.insertEquipement(unEquipement);
	}

	public static void deleteEquipement(int idEquipement) {
	    Modele.deleteEquipement(idEquipement);
	}

	public static void updateEquipement(Equipement unEquipement) {
	    Modele.updateEquipement(unEquipement);
	}

	public static Equipement selectWhereEquipement(int idEquipement) {
	    return Modele.selectWhereEquipement(idEquipement);
	}

	public static ArrayList<Equipement> selectAllEquipements() {
	    return Modele.selectAllEquipements();
	}

	public static ArrayList<Equipement> selectLikeEquipements(String filtre) {
	    return Modele.selectLikeEquipements(filtre);
	}
	/************************* Gestion des Region ******************/
	
	public static void insertRegion(Region uneRegion) {
	    Modele.insertRegion(uneRegion);
	}

	public static void deleteRegion(String codeR) {
	    Modele.deleteRegion(codeR);
	}

	public static void updateRegion(Region uneRegion) {
	    Modele.updateRegion(uneRegion);
	}

	public static Region selectWhereRegion(String codeR) {
	    return Modele.selectWhereRegion(codeR);
	}

	public static ArrayList<Region> selectAllRegions() {
	    return Modele.selectAllRegions();
	}

	public static ArrayList<Region> selectLikeRegions(String filtre) {
	    return Modele.selectLikeRegions(filtre);
	}
	
	/************************* Gestion des Habitation ******************/
	public static void insertHabitation(Habitation uneHabitation) {
	    Modele.insertHabitation(uneHabitation);
	}

	public static void deleteHabitation(int idHabitation) {
	    Modele.deleteHabitation(idHabitation);
	}

	public static void updateHabitation(Habitation uneHabitation) {
	    Modele.updateHabitation(uneHabitation);
	}

	public static Habitation selectWhereHabitation(int idHabitation) {
	    return Modele.selectWhereHabitation(idHabitation);
	}

	public static ArrayList<Habitation> selectAllHabitations() {
	    return Modele.selectAllHabitations();
	}

	public static ArrayList<Habitation> selectLikeHabitations(String filtre) {
	    return Modele.selectLikeHabitations(filtre);
	}
	


	/************************* Gestion des Station ******************/
	
	public static void insertStation(Station uneStation) {
	    Modele.insertStation(uneStation);
	}

	public static void deleteStation(int codeS) {
	    Modele.deleteStation(codeS);
	}

	public static void updateStation(Station uneStation) {
	    Modele.updateStation(uneStation);
	}

	public static Station selectWhereStation(int codeS) {
	    return Modele.selectWhereStation(codeS);
	}

	public static ArrayList<Station> selectAllStations() {
	    return Modele.selectAllStations();
	}

	public static ArrayList<Station> selectLikeStations(String filtre) {
	    return Modele.selectLikeStations(filtre);
	}

	/************************* Gestion des Activite ******************/
	
	public static void insertActivite(Activite uneActivite) {
	    Modele.insertActivite(uneActivite);
	}

	public static void deleteActivite(int codeA) {
	    Modele.deleteActivite(codeA);
	}

	public static void updateActivite(Activite uneActivite) {
	    Modele.updateActivite(uneActivite);
	}

	public static Activite selectWhereActivite(int codeA) {
	    return Modele.selectWhereActivite(codeA);
	}

	public static ArrayList<Activite> selectAllActivites() {
	    return Modele.selectAllActivites();
	}

	public static ArrayList<Activite> selectLikeActivites(String filtre) {
	    return Modele.selectLikeActivites(filtre);
	}
	

	/************************* Gestion des Proprietaire ******************/
	
	public static void insertProprietaire(Proprietaire unProprietaire) {
	    Modele.insertProprietaire(unProprietaire);
	}

	public static void deleteProprietaire(int idProprietaire) {
	    Modele.deleteProprietaire(idProprietaire);
	}

	public static void updateProprietaire(Proprietaire unProprietaire) {
	    Modele.updateProprietaire(unProprietaire);
	}

	public static Proprietaire selectWhereProprietaire(int idProprietaire) {
	    return Modele.selectWhereProprietaire(idProprietaire);
	}

	public static ArrayList<Proprietaire> selectAllProprietaires() {
	    return Modele.selectAllProprietaires();
	}

	public static ArrayList<Proprietaire> selectLikeProprietaires(String filtre) {
	    return Modele.selectLikeProprietaires(filtre);
	}

	/************************* Gestion des ContratMandatLocatif ******************/
	
	public static void insertContratMandatLocatif(ContratMandatLocatif unContrat) {
	    Modele.insertContratMandatLocatif(unContrat);
	}

	public static void deleteContratMandatLocatif(int idContrat) {
	    Modele.deleteContratMandatLocatif(idContrat);
	}

	public static void updateContratMandatLocatif(ContratMandatLocatif unContrat) {
	    Modele.updateContratMandatLocatif(unContrat);
	}

	public static ContratMandatLocatif selectWhereContratMandatLocatif(int idContrat) {
	    return Modele.selectWhereContratMandatLocatif(idContrat);
	}

	public static ArrayList<ContratMandatLocatif> selectAllContratMandatLocatifs() {
	    return Modele.selectAllContratMandatLocatifs();
	}

	public static ArrayList<ContratMandatLocatif> selectLikeContratMandatLocatifs(String filtre) {
	    return Modele.selectLikeContratMandatLocatifs(filtre);
	}

	/************************* Gestion User ******************/
	
	public static void insertUser(User unUser) {
	    Modele.insertUser(unUser);
	}

	public static void deleteUser(int idUser) {
	    Modele.deleteContratMandatLocatif(idUser);
	}

	public static void updateUser(User unUser) {
	    Modele.updateUser(unUser);
	}
	
	public static User selectWhereUser(String email, String mdp) {
		return Modele.selectWhereUser (email, mdp);
	}
	public static ArrayList<User> selectAllUser() {
	    return Modele.selectAllUsers();
	}

	public static ArrayList<User> selectLikeUser(String filtre) {
	    return Modele.selectLikeUsers(filtre);
	}

}
