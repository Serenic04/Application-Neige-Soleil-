package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Activite;
import controleur.Client;
import controleur.ContratLoc;
import controleur.ContratMandatLocatif;
import controleur.Equipement;
import controleur.Habitation;
import controleur.Proprietaire;
import controleur.Region;
import controleur.Reservation;
import controleur.Station;
import controleur.TypeEquipement;
import controleur.User;
 

public class Modele {
	private static Connexion uneConnexion=new Connexion ("localhost","bdd_ppe","root","");
	
	/**************************** Gestion des clients ********************/
	public static void insertClient (Client unClient) {
		String requete ="insert into client values (null, '" + unClient.getNom()
		+"','" + unClient.getPrenom() + "','" + unClient.getAdresse() 
		+ "','" + unClient.getEmail() + "','" + unClient.getTel() +"');";
		executerRequete (requete); 
	}
	public static void deleteClient(int idclient) {
		String requete ="delete from client where idclient = " + idclient +";";
		executerRequete (requete); 
	}
	public static void updateClient (Client unClient) {
		String requete ="update client set nom = '" + unClient.getNom()
		+"', prenom = '" + unClient.getPrenom() + "', adresse = '" + unClient.getAdresse() 
		+ "', email = '" + unClient.getEmail() + "', tel = '" + unClient.getTel() 
		+ " where idclient = " +unClient.getIdclient()+";";
		executerRequete (requete); 
	}
	
	public static Client selectWhereClient(int idclient) {
		String requete = "select * from client where idclient = " + idclient +";"; 
		Client unClient = null; 
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet lesResultats = unStat.executeQuery(requete); //fetch  
			//parcours des resultats et extraction un client 
			if (lesResultats.next()) {
				//instanciation d'un Client 
				 unClient = new Client (
						lesResultats.getInt("idclient"), lesResultats.getString("nom"), 
						lesResultats.getString("prenom"), lesResultats.getString("adresse"),
						lesResultats.getString("email"),lesResultats.getString("tel")
						); 
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return unClient ;
	}
	
	public static ArrayList<Client> selectAllClients (){
		String requete = "select * from client ;"; 
		ArrayList<Client> lesClients = new ArrayList<Client>(); 
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet lesResultats = unStat.executeQuery(requete); //fetchAll 
			//parcours des resultats et extraction les client 
			while (lesResultats.next()) {
				//instanciation d'un Client 
				 Client unClient = new Client (
						lesResultats.getInt("idclient"), lesResultats.getString("nom"), 
						lesResultats.getString("prenom"), lesResultats.getString("adresse"),
						lesResultats.getString("email"),lesResultats.getString("tel")
						); 
				// ajout du client dans lesClients 
				 lesClients.add(unClient);
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return lesClients ;
	}
	
	public static ArrayList<Client> selectLikeClients (String filtre){
		String requete = "select * from client where nom like '%" + filtre +"%' or "
				+ " prenom like '%" + filtre +"%' or "
				+ " adresse like '%" + filtre +"%' or "
				+ " email like '%" + filtre +"%' or "
				+ " tel like '%" + filtre +"%' ; ";
		
		ArrayList<Client> lesClients = new ArrayList<Client>(); 
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet lesResultats = unStat.executeQuery(requete); //fetchAll 
			//parcours des resultats et extraction les client 
			while (lesResultats.next()) {
				//instanciation d'un Client 
				 Client unClient = new Client (
						lesResultats.getInt("idclient"), lesResultats.getString("nom"), 
						lesResultats.getString("prenom"), lesResultats.getString("adresse"),
						lesResultats.getString("email"),lesResultats.getString("tel")
						); 
				// ajout du client dans lesClients 
				 lesClients.add(unClient);
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return lesClients ;
	}
	
	/**************************** Gestion des reservations ********************/
	public static void insertReservation(Reservation uneReservation) {
	    String requete = "insert into reservation values (null,'" + uneReservation.getDateR()
	        + "', '" + uneReservation.getDateHeureDebut() + "', '" + uneReservation.getDateHeureFin()
	        + "', " + uneReservation.getNbPersonne() + ", " + uneReservation.getIdClient() + ");";
	    executerRequete(requete);
	}

	public static void deleteReservation(int refR) {
	    String requete = "delete from reservation where refR = " + refR + ";";
	    executerRequete(requete);
	}

	public static void updateReservation(Reservation uneReservation) {
	    String requete = "update reservation set dateR = '" + uneReservation.getDateR()
	        + "', dateHeureDebut = '" + uneReservation.getDateHeureDebut() 
	        + "', dateHeureFin = '" + uneReservation.getDateHeureFin() 
	        + "', nbPersonne = " + uneReservation.getNbPersonne() 
	        + ", idClient = " + uneReservation.getIdClient()
	        + " where refR = " + uneReservation.getRefR() + ";";
	    executerRequete(requete);
	}

	public static Reservation selectWhereReservation(int refR) {
	    String requete = "select * from reservation where refR = " + refR + ";";
	    Reservation uneReservation = null;
	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet lesResultats = unStat.executeQuery(requete);
	        if (lesResultats.next()) {
	        	 uneReservation = new Reservation(
		                lesResultats.getInt("refR"), lesResultats.getString("dateR"),
		                lesResultats.getString("dateHeureDebut"),
		                lesResultats.getString("dateHeureFin"),
		                lesResultats.getInt("nbPersonne"),
		                lesResultats.getInt("idClient")
		            );
	        }
	        unStat.close();
	        uneConnexion.seDeConnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur d'execution de la requete : " + requete);
	    }
	    return uneReservation;
	}

	public static ArrayList<Reservation> selectAllReservations() {
	    String requete = "select * from reservation;";
	    ArrayList<Reservation> lesReservations = new ArrayList<>();
	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet lesResultats = unStat.executeQuery(requete);
	        while (lesResultats.next()) {
	        	Reservation uneReservation = new Reservation(
		                lesResultats.getInt("refR"), lesResultats.getString("dateR"),
		                lesResultats.getString("dateHeureDebut"),
		                lesResultats.getString("dateHeureFin"),
		                lesResultats.getInt("nbPersonne"),
		                lesResultats.getInt("idClient")
		            );
	            lesReservations.add(uneReservation);
	        }
	        unStat.close();
	        uneConnexion.seDeConnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur d'execution de la requete : " + requete);
	    }
	    return lesReservations;
	}

	public static ArrayList<Reservation> selectLikeReservations(String filtre) {
	    String requete = "select * from reservation where dateR like '%" + filtre 
	        + "%' or dateHeureDebut like '%" + filtre 
	        + "%' or dateHeureFin like '%" + filtre 
	        + "%' or nbPersonne like '%" + filtre 
	        + "%' or idClient like '%" + filtre + "%';";
	    ArrayList<Reservation> lesReservations = new ArrayList<>();
	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet lesResultats = unStat.executeQuery(requete);
	        while (lesResultats.next()) {
	            Reservation uneReservation = new Reservation(
	                lesResultats.getInt("refR"), lesResultats.getString("dateR"),
	                lesResultats.getString("dateHeureDebut"),
	                lesResultats.getString("dateHeureFin"),
	                lesResultats.getInt("nbPersonne"),
	                lesResultats.getInt("idClient")
	            );
	            lesReservations.add(uneReservation);
	        }
	     
			
	        unStat.close();
	        uneConnexion.seDeConnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur d'execution de la requete : " + requete);
	    }
	    return lesReservations;
	}
	/**************************** Gestion des contrats locatifs ********************/
	public static void insertContratLoc(ContratLoc unContratLoc) {
	    String requete = "insert into contratloc values (null, " + unContratLoc.getDureeC()
	        + ", '" + unContratLoc.getDateDebutC() + "', '" + unContratLoc.getDateFinC()
	        + "', " + unContratLoc.getNbPersonneC() + ", " + unContratLoc.getIdClient()
	        + ", " + unContratLoc.getIdHabitation() + ");";
	    executerRequete(requete);
	}

	public static void deleteContratLoc(int refC) {
	    String requete = "delete from contratloc where refC = " + refC + ";";
	    executerRequete(requete);
	}

	public static void updateContratLoc(ContratLoc unContratLoc) {
	    String requete = "update contratloc set dureeC = " + unContratLoc.getDureeC()
	        + ", dateDebutC = '" + unContratLoc.getDateDebutC() 
	        + "', dateFinC = '" + unContratLoc.getDateFinC()
	        + "', nbPersonneC = " + unContratLoc.getNbPersonneC()
	        + ", idClient = " + unContratLoc.getIdClient()
	        + ", idHabitation = " + unContratLoc.getIdHabitation()
	        + " where refC = " + unContratLoc.getRefC() + ";";
	    executerRequete(requete);
	}

	public static ContratLoc selectWhereContratLoc(int refC) {
	    String requete = "select * from contratloc where refC = " + refC + ";";
	    ContratLoc unContratLoc = null;
	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet lesResultats = unStat.executeQuery(requete);
	        if (lesResultats.next()) {
	        	unContratLoc = new ContratLoc(
	                lesResultats.getInt("refC"), lesResultats.getInt("dureeC"),
	                lesResultats.getString("dateDebutC"), lesResultats.getString("dateFinC"),
	                lesResultats.getInt("nbPersonneC"), lesResultats.getInt("idClient"),
	                lesResultats.getInt("idHabitation")
	            );
	        }
	        unStat.close();
	        uneConnexion.seDeConnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur d'execution de la requete : " + requete);
	    }
	    return unContratLoc;
	}

	public static ArrayList<ContratLoc> selectAllContratLoc() {
	    String requete = "select * from contratloc;";
	    ArrayList<ContratLoc> lesContratsLoc = new ArrayList<ContratLoc>();
	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet lesResultats = unStat.executeQuery(requete);
	        while (lesResultats.next()) {
	            ContratLoc unContratLoc = new ContratLoc(
	            		lesResultats.getInt("refC"), lesResultats.getInt("dureeC"),
		                lesResultats.getString("dateDebutC"), lesResultats.getString("dateFinC"),
		                lesResultats.getInt("nbPersonneC"), lesResultats.getInt("idClient"),
		                lesResultats.getInt("idHabitation")
	            );
	            lesContratsLoc.add(unContratLoc);
	        }
	        unStat.close();
	        uneConnexion.seDeConnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur d'execution de la requete : " + requete);
	    }
	    return lesContratsLoc;
	}

	public static ArrayList<ContratLoc> selectLikeContratLoc(String filtre) {
	    String requete = "select * from contratloc where dureeC like '%" + filtre 
	        + "%' or dateDebutC like '%" + filtre 
	        + "%' or dateFinC like '%" + filtre 
	        + "%' or nbPersonneC like '%" + filtre 
	        + "%' or idClient like '%" + filtre 
	        + "%' or idHabitation like '%" + filtre + "%';";
	    ArrayList<ContratLoc> lesContratsLoc = new ArrayList<>();
	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet lesResultats = unStat.executeQuery(requete);
	        while (lesResultats.next()) {
	            ContratLoc unContratLoc = new ContratLoc(
	            		lesResultats.getInt("refC"), lesResultats.getInt("dureeC"),
		                lesResultats.getString("dateDebutC"), lesResultats.getString("dateFinC"),
		                lesResultats.getInt("nbPersonneC"), lesResultats.getInt("idClient"),
		                lesResultats.getInt("idHabitation")
		            );
	            lesContratsLoc.add(unContratLoc);
	        }
	        unStat.close();
	        uneConnexion.seDeConnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur d'execution de la requete : " + requete);
	    }
	    return lesContratsLoc;
	}
	/**************************** Gestion des types d'équipements ********************/
	public static void insertTypeEquipement(TypeEquipement unTypeEquipement) {
	    String requete = "insert into typeequipement values (null, '" + unTypeEquipement.getNomType()
	        + "', '" + unTypeEquipement.getDescription() + "');";
	    executerRequete(requete);
	}

	public static void deleteTypeEquipement(int idTypeEquipement) {
	    String requete = "delete from typeequipement where idTypeEquipement = " + idTypeEquipement + ";";
	    executerRequete(requete);
	}

	public static void updateTypeEquipement(TypeEquipement unTypeEquipement) {
	    String requete = "update typeequipement set nomType = '" + unTypeEquipement.getNomType()
	        + "', description = '" + unTypeEquipement.getDescription() 
	        + "' where idTypeEquipement = " + unTypeEquipement.getIdTypeEquipement() + ";";
	    executerRequete(requete);
	}

	public static TypeEquipement selectWhereTypeEquipement(int idTypeEquipement) {
	    String requete = "select * from typeequipement where idTypeEquipement = " + idTypeEquipement + ";";
	    TypeEquipement unTypeEquipement = null;
	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet lesResultats = unStat.executeQuery(requete);
	        if (lesResultats.next()) {
	            unTypeEquipement = new TypeEquipement(
	                lesResultats.getInt("idTypeEquipement"), 
	                lesResultats.getString("nomType"), 
	                lesResultats.getString("description")
	            );
	        }
	        unStat.close();
	        uneConnexion.seDeConnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur d'execution de la requete : " + requete);
	    }
	    return unTypeEquipement;
	}

	public static ArrayList<TypeEquipement> selectAllTypeEquipements() {
	    String requete = "select * from typeequipement;";
	    ArrayList<TypeEquipement> lesTypeEquipements = new ArrayList<>();
	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet lesResultats = unStat.executeQuery(requete);
	        while (lesResultats.next()) {
	            TypeEquipement unTypeEquipement = new TypeEquipement(
	                lesResultats.getInt("idTypeEquipement"), 
	                lesResultats.getString("nomType"), 
	                lesResultats.getString("description")
	            );
	            lesTypeEquipements.add(unTypeEquipement);
	        }
	        unStat.close();
	        uneConnexion.seDeConnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur d'execution de la requete : " + requete);
	    }
	    return lesTypeEquipements;
	}

	public static ArrayList<TypeEquipement> selectLikeTypeEquipements(String filtre) {
	    String requete = "select * from typeequipement where nomType like '%" + filtre 
	        + "%' or description like '%" + filtre + "%';";
	    ArrayList<TypeEquipement> lesTypeEquipements = new ArrayList<>();
	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet lesResultats = unStat.executeQuery(requete);
	        while (lesResultats.next()) {
	            TypeEquipement unTypeEquipement = new TypeEquipement(
	                lesResultats.getInt("idTypeEquipement"), 
	                lesResultats.getString("nomType"), 
	                lesResultats.getString("description")
	            );
	            lesTypeEquipements.add(unTypeEquipement);
	        }
	        unStat.close();
	        uneConnexion.seDeConnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur d'execution de la requete : " + requete);
	    }
	    return lesTypeEquipements;
	}
	/**************************** Gestion des équipements ********************/
	public static void insertEquipement(Equipement unEquipement) {
	    String requete = "insert into equipement values (null, '" + unEquipement.getNomEquipement()
	        + "', " + unEquipement.getQteEquipement() + ", " + unEquipement.getIdTypeEquipement() + ");";
	    executerRequete(requete);
	}

	public static void deleteEquipement(int idEquipement) {
	    String requete = "delete from equipement where idEquipement = " + idEquipement + ";";
	    executerRequete(requete);
	}

	public static void updateEquipement(Equipement unEquipement) {
	    String requete = "update equipement set nomEquipement = '" + unEquipement.getNomEquipement()
	        + "', qteEquipement = " + unEquipement.getQteEquipement()
	        + ", idTypeEquipement = " + unEquipement.getIdTypeEquipement()
	        + " where idEquipement = " + unEquipement.getIdEquipement() + ";";
	    executerRequete(requete);
	}

	public static Equipement selectWhereEquipement(int idEquipement) {
	    String requete = "select * from equipement where idEquipement = " + idEquipement + ";";
	    Equipement unEquipement = null;
	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet lesResultats = unStat.executeQuery(requete);
	        if (lesResultats.next()) {
	            unEquipement = new Equipement(
	                lesResultats.getInt("idEquipement"), 
	                lesResultats.getString("nomEquipement"), 
	                lesResultats.getInt("qteEquipement"), 
	                lesResultats.getInt("idTypeEquipement")
	            );
	        }
	        unStat.close();
	        uneConnexion.seDeConnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur d'execution de la requete : " + requete);
	    }
	    return unEquipement;
	}

	public static ArrayList<Equipement> selectAllEquipements() {
	    String requete = "select * from equipement;";
	    ArrayList<Equipement> lesEquipements = new ArrayList<>();
	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet lesResultats = unStat.executeQuery(requete);
	        while (lesResultats.next()) {
	            Equipement unEquipement = new Equipement(
	                lesResultats.getInt("idEquipement"), 
	                lesResultats.getString("nomEquipement"), 
	                lesResultats.getInt("qteEquipement"), 
	                lesResultats.getInt("idTypeEquipement")
	            );
	            lesEquipements.add(unEquipement);
	        }
	        unStat.close();
	        uneConnexion.seDeConnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur d'execution de la requete : " + requete);
	    }
	    return lesEquipements;
	}

	public static ArrayList<Equipement> selectLikeEquipements(String filtre) {
	    String requete = "select * from equipement where nomEquipement like '%" + filtre 
	        + "%' or qteEquipement like '%" + filtre + "%';";
	    ArrayList<Equipement> lesEquipements = new ArrayList<>();
	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet lesResultats = unStat.executeQuery(requete);
	        while (lesResultats.next()) {
	            Equipement unEquipement = new Equipement(
	                lesResultats.getInt("idEquipement"), 
	                lesResultats.getString("nomEquipement"), 
	                lesResultats.getInt("qteEquipement"), 
	                lesResultats.getInt("idTypeEquipement")
	            );
	            lesEquipements.add(unEquipement);
	        }
	        unStat.close();
	        uneConnexion.seDeConnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur d'execution de la requete : " + requete);
	    }
	    return lesEquipements;
	}
	/**************************** Gestion des habitations ********************/
	public static void insertHabitation(Habitation uneHabitation) {
	    String requete = "insert into habitation values (null, '" + uneHabitation.getAdresse()
	        + "', " + uneHabitation.getTaille() + ", " + uneHabitation.getIdProprietaire() 
	        + ", '" + uneHabitation.getCodeR() + "');";
	    executerRequete(requete);
	}

	public static void deleteHabitation(int idHabitation) {
	    String requete = "delete from habitation where idHabitation = " + idHabitation + ";";
	    executerRequete(requete);
	}

	public static void updateHabitation(Habitation uneHabitation) {
	    String requete = "update habitation set adresse = '" + uneHabitation.getAdresse()
	        + "', taille = " + uneHabitation.getTaille()
	        + ", idProprietaire = " + uneHabitation.getIdProprietaire()
	        + ", codeR = '" + uneHabitation.getCodeR()
	        + "' where idHabitation = " + uneHabitation.getIdHabitation() + ";";
	    executerRequete(requete);
	}

	public static Habitation selectWhereHabitation(int idHabitation) {
	    String requete = "select * from habitation where idHabitation = " + idHabitation + ";";
	    Habitation uneHabitation = null;
	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet lesResultats = unStat.executeQuery(requete);
	        if (lesResultats.next()) {
	            uneHabitation = new Habitation(
	                lesResultats.getInt("idHabitation"), 
	                lesResultats.getString("adresse"), 
	                lesResultats.getInt("taille"), 
	                lesResultats.getInt("idProprietaire"), 
	                lesResultats.getString("codeR")
	            );
	        }
	        unStat.close();
	        uneConnexion.seDeConnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur d'execution de la requete : " + requete);
	    }
	    return uneHabitation;
	}

	public static ArrayList<Habitation> selectAllHabitations() {
	    String requete = "select * from habitation;";
	    ArrayList<Habitation> lesHabitations = new ArrayList<>();
	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet lesResultats = unStat.executeQuery(requete);
	        while (lesResultats.next()) {
	            Habitation uneHabitation = new Habitation(
	                lesResultats.getInt("idHabitation"), 
	                lesResultats.getString("adresse"), 
	                lesResultats.getInt("taille"), 
	                lesResultats.getInt("idProprietaire"), 
	                lesResultats.getString("codeR")
	            );
	            lesHabitations.add(uneHabitation);
	        }
	        unStat.close();
	        uneConnexion.seDeConnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur d'execution de la requete : " + requete);
	    }
	    return lesHabitations;
	}

	public static ArrayList<Habitation> selectLikeHabitations(String filtre) {
	    String requete = "select * from habitation where adresse like '%" + filtre 
	        + "%' or taille like '%" + filtre + "%';";
	    ArrayList<Habitation> lesHabitations = new ArrayList<>();
	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet lesResultats = unStat.executeQuery(requete);
	        while (lesResultats.next()) {
	            Habitation uneHabitation = new Habitation(
	                lesResultats.getInt("idHabitation"), 
	                lesResultats.getString("adresse"), 
	                lesResultats.getInt("taille"), 
	                lesResultats.getInt("idProprietaire"), 
	                lesResultats.getString("codeR")
	            );
	            lesHabitations.add(uneHabitation);
	        }
	        unStat.close();
	        uneConnexion.seDeConnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur d'execution de la requete : " + requete);
	    }
	    return lesHabitations;
	}
	
	/**************************** Gestion des stations ********************/
	public static void insertStation(Station uneStation) {
	    String requete = "insert into station values (null, '" + uneStation.getNomS()
	        + "', " + uneStation.getEtoiles() + ", '" + uneStation.getCodeR() + "');";
	    executerRequete(requete);
	}

	public static void deleteStation(int codeS) {
	    String requete = "delete from station where codeS = " + codeS + ";";
	    executerRequete(requete);
	}

	public static void updateStation(Station uneStation) {
	    String requete = "update station set nomS = '" + uneStation.getNomS()
	        + "', etoiles = " + uneStation.getEtoiles() 
	        + ", codeR = '" + uneStation.getCodeR() 
	        + "' where codeS = " + uneStation.getCodeS() + ";";
	    executerRequete(requete);
	}

	public static Station selectWhereStation(int codeS) {
	    String requete = "select * from station where codeS = " + codeS + ";";
	    Station uneStation = null;
	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet lesResultats = unStat.executeQuery(requete);
	        if (lesResultats.next()) {
	            uneStation = new Station(
	                lesResultats.getInt("codeS"), 
	                lesResultats.getString("nomS"), 
	                lesResultats.getInt("etoiles"), 
	                lesResultats.getString("codeR")
	            );
	        }
	        unStat.close();
	        uneConnexion.seDeConnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur d'execution de la requete : " + requete);
	    }
	    return uneStation;
	}

	public static ArrayList<Station> selectAllStations() {
	    String requete = "select * from station;";
	    ArrayList<Station> lesStations = new ArrayList<>();
	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet lesResultats = unStat.executeQuery(requete);
	        while (lesResultats.next()) {
	            Station uneStation = new Station(
	                lesResultats.getInt("codeS"), 
	                lesResultats.getString("nomS"), 
	                lesResultats.getInt("etoiles"), 
	                lesResultats.getString("codeR")
	            );
	            lesStations.add(uneStation);
	        }
	        unStat.close();
	        uneConnexion.seDeConnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur d'execution de la requete : " + requete);
	    }
	    return lesStations;
	}

	public static ArrayList<Station> selectLikeStations(String filtre) {
	    String requete = "select * from station where nomS like '%" + filtre + "%';";
	    ArrayList<Station> lesStations = new ArrayList<>();
	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet lesResultats = unStat.executeQuery(requete);
	        while (lesResultats.next()) {
	            Station uneStation = new Station(
	                lesResultats.getInt("codeS"), 
	                lesResultats.getString("nomS"), 
	                lesResultats.getInt("etoiles"), 
	                lesResultats.getString("codeR")
	            );
	            lesStations.add(uneStation);
	        }
	        unStat.close();
	        uneConnexion.seDeConnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur d'execution de la requete : " + requete);
	    }
	    return lesStations;
	}


	/**************************** Gestion des régions ********************/
	public static void insertRegion(Region uneRegion) {
	    String requete = "insert into region values ('" + uneRegion.getCodeR()
	        + "', " + uneRegion.getDepartement() + ", '" + uneRegion.getVilleR() + "');";
	    executerRequete(requete);
	}

	public static void deleteRegion(String codeR) {
	    String requete = "delete from region where codeR = '" + codeR + "';";
	    executerRequete(requete);
	}

	public static void updateRegion(Region uneRegion) {
	    String requete = "update region set departement = " + uneRegion.getDepartement()
	        + ", villeR = '" + uneRegion.getVilleR()
	        + "' where codeR = '" + uneRegion.getCodeR() + "';";
	    executerRequete(requete);
	}

	public static Region selectWhereRegion(String codeR) {
	    String requete = "select * from region where codeR = '" + codeR + "';";
	    Region uneRegion = null;
	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet lesResultats = unStat.executeQuery(requete);
	        if (lesResultats.next()) {
	            uneRegion = new Region(
	                lesResultats.getString("codeR"), 
	                lesResultats.getInt("departement"), 
	                lesResultats.getString("villeR")
	            );
	        }
	        unStat.close();
	        uneConnexion.seDeConnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur d'execution de la requete : " + requete);
	    }
	    return uneRegion;
	}

	public static ArrayList<Region> selectAllRegions() {
	    String requete = "select * from region;";
	    ArrayList<Region> lesRegions = new ArrayList<>();
	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet lesResultats = unStat.executeQuery(requete);
	        while (lesResultats.next()) {
	            Region uneRegion = new Region(
	                lesResultats.getString("codeR"), 
	                lesResultats.getInt("departement"), 
	                lesResultats.getString("villeR")
	            );
	            lesRegions.add(uneRegion);
	        }
	        unStat.close();
	        uneConnexion.seDeConnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur d'execution de la requete : " + requete);
	    }
	    return lesRegions;
	}

	public static ArrayList<Region> selectLikeRegions(String filtre) {
	    String requete = "select * from region where villeR like '%" + filtre + "%';";
	    ArrayList<Region> lesRegions = new ArrayList<>();
	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet lesResultats = unStat.executeQuery(requete);
	        while (lesResultats.next()) {
	            Region uneRegion = new Region(
	                lesResultats.getString("codeR"), 
	                lesResultats.getInt("departement"), 
	                lesResultats.getString("villeR")
	            );
	            lesRegions.add(uneRegion);
	        }
	        unStat.close();
	        uneConnexion.seDeConnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur d'execution de la requete : " + requete);
	    }
	    return lesRegions;
	}
	/**************************** Gestion des contrats de mandat locatif ********************/
	public static void insertContratMandatLocatif(ContratMandatLocatif unContrat) {
	    String requete = "insert into contratmandatlocatif values (null, '" + unContrat.getEtatContrat()
	        + "', '" + unContrat.getDateSignature() + "', " + unContrat.getIdProprietaire()
	        + ", " + unContrat.getIdHabitation() + ");";
	    executerRequete(requete);
	}

	public static void deleteContratMandatLocatif(int idContrat) {
	    String requete = "delete from contratmandatlocatif where idContrat = " + idContrat + ";";
	    executerRequete(requete);
	}

	public static void updateContratMandatLocatif(ContratMandatLocatif unContrat) {
	    String requete = "update contratmandatlocatif set etatContrat = '" + unContrat.getEtatContrat()
	        + "', dateSignature = '" + unContrat.getDateSignature()
	        + "', idProprietaire = " + unContrat.getIdProprietaire()
	        + ", idHabitation = " + unContrat.getIdHabitation()
	        + " where idContrat = " + unContrat.getIdContrat() + ";";
	    executerRequete(requete);
	}

	public static ContratMandatLocatif selectWhereContratMandatLocatif(int idContrat) {
	    String requete = "select * from contratmandatlocatif where idContrat = " + idContrat + ";";
	    ContratMandatLocatif unContrat = null;
	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet lesResultats = unStat.executeQuery(requete);
	        if (lesResultats.next()) {
	            unContrat = new ContratMandatLocatif(
	                lesResultats.getInt("idContrat"), 
	                lesResultats.getString("etatContrat"), 
	                lesResultats.getInt("dateSignature"), 
	                lesResultats.getInt("idProprietaire"), 
	                lesResultats.getInt("idHabitation")
	            );
	        }
	        unStat.close();
	        uneConnexion.seDeConnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur d'execution de la requete : " + requete);
	    }
	    return unContrat;
	}

	public static ArrayList<ContratMandatLocatif> selectAllContratMandatLocatifs() {
	    String requete = "select * from contratmandatlocatif;";
	    ArrayList<ContratMandatLocatif> lesContrats = new ArrayList<>();
	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet lesResultats = unStat.executeQuery(requete);
	        while (lesResultats.next()) {
	            ContratMandatLocatif unContrat = new ContratMandatLocatif(
	                lesResultats.getInt("idContrat"), 
	                lesResultats.getString("etatContrat"), 
	                lesResultats.getInt("dateSignature"),
	                lesResultats.getInt("idProprietaire"), 
	                lesResultats.getInt("idHabitation")
	            );
	            lesContrats.add(unContrat);
	        }
	        unStat.close();
	        uneConnexion.seDeConnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur d'execution de la requete : " + requete);
	    }
	    return lesContrats;
	}

	public static ArrayList<ContratMandatLocatif> selectLikeContratMandatLocatifs(String filtre) {
	    String requete = "select * from contratmandatlocatif where etatContrat like '%" + filtre + "%';";
	    ArrayList<ContratMandatLocatif> lesContrats = new ArrayList<>();
	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet lesResultats = unStat.executeQuery(requete);
	        while (lesResultats.next()) {
	            ContratMandatLocatif unContrat = new ContratMandatLocatif(
	                lesResultats.getInt("idContrat"), 
	                lesResultats.getString("etatContrat"), 
	                lesResultats.getInt("dateSignature"),
	                lesResultats.getInt("idProprietaire"), 
	                lesResultats.getInt("idHabitation")
	            );
	            lesContrats.add(unContrat);
	        }
	        unStat.close();
	        uneConnexion.seDeConnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur d'execution de la requete : " + requete);
	    }
	    return lesContrats;
	}

	/**************************** Gestion des propriétaires ********************/
	public static void insertProprietaire(Proprietaire unProprietaire) {
	    String requete = 
	    	"insert into proprietaire values "
	    	+ "(null, '" + unProprietaire.getNom()
	        + "', '" + unProprietaire.getPrenom() 
	        + "', '" + unProprietaire.getAdresse()  
	        + "', '" + unProprietaire.getEmail() 
	        + "', '" + unProprietaire.getTel()+ "');";
	    executerRequete(requete);
	}

	public static void deleteProprietaire(int idProprietaire) {
	    String requete = "delete from proprietaire where idProprietaire = " + idProprietaire + ";";
	    executerRequete(requete);
	}

	public static void updateProprietaire(Proprietaire unProprietaire) {
	    String requete = "update proprietaire set nom = '" + unProprietaire.getNom()
	        + "', prenom = '" + unProprietaire.getPrenom()
	        + "', adresse = '" + unProprietaire.getAdresse()
	        + "', tel = '" + unProprietaire.getTel()
	        + "', email = '" + unProprietaire.getEmail()
	        + "' where idProprietaire = " + unProprietaire.getIdProprietaire() + ";";
	    executerRequete(requete);
	}

	public static Proprietaire selectWhereProprietaire(int idProprietaire) {
	    String requete = "select * from proprietaire where idProprietaire = " + idProprietaire + ";";
	    Proprietaire unProprietaire = null;
	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet lesResultats = unStat.executeQuery(requete);
	        if (lesResultats.next()) {
	            unProprietaire = new Proprietaire(
	                lesResultats.getInt("idProprietaire"),
	                lesResultats.getString("nom"),
	                lesResultats.getString("prenom"),
	                lesResultats.getString("adresse"),
	                lesResultats.getString("tel"),
	                lesResultats.getString("email")
	            );
	        }
	        unStat.close();
	        uneConnexion.seDeConnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur d'execution de la requete : " + requete);
	    }
	    return unProprietaire;
	}

	public static ArrayList<Proprietaire> selectAllProprietaires() {
	    String requete = "select * from proprietaire;";
	    ArrayList<Proprietaire> lesProprietaires = new ArrayList<>();
	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet lesResultats = unStat.executeQuery(requete);
	        while (lesResultats.next()) {
	            Proprietaire unProprietaire = new Proprietaire(
	                lesResultats.getInt("idProprietaire"),
	                lesResultats.getString("nom"),
	                lesResultats.getString("prenom"),
	                lesResultats.getString("adresse"),
	                lesResultats.getString("tel"),
	                lesResultats.getString("email")
	            );
	            lesProprietaires.add(unProprietaire);
	        }
	        unStat.close();
	        uneConnexion.seDeConnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur d'execution de la requete : " + requete);
	    }
	    return lesProprietaires;
	}

	public static ArrayList<Proprietaire> selectLikeProprietaires(String filtre) {
	    String requete = "select * from proprietaire where nom like '%" + filtre + "%' or "
	        + "prenom like '%" + filtre + "%' or adresse like '%" + filtre + "%' or "
	        + "tel like '%" + filtre + "%' or email like '%" + filtre + "%';";
	    ArrayList<Proprietaire> lesProprietaires = new ArrayList<>();
	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet lesResultats = unStat.executeQuery(requete);
	        while (lesResultats.next()) {
	            Proprietaire unProprietaire = new Proprietaire(
	                lesResultats.getInt("idProprietaire"),
	                lesResultats.getString("nom"),
	                lesResultats.getString("prenom"),
	                lesResultats.getString("adresse"),
	                lesResultats.getString("tel"),
	                lesResultats.getString("email")
	            );
	            lesProprietaires.add(unProprietaire);
	        }
	        unStat.close();
	        uneConnexion.seDeConnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur d'execution de la requete : " + requete);
	    }
	    return lesProprietaires;
	}
	/**************************** Gestion des activités ********************/
	public static void insertActivite(Activite uneActivite) {
	    String requete = "insert into activite values (null, '" + uneActivite.getNomActivite()
	        + "', " + uneActivite.getTarifA() + ");";
	    executerRequete(requete);
	}

	public static void deleteActivite(int codeA) {
	    String requete = "delete from activite where codeA = " + codeA + ";";
	    executerRequete(requete);
	}

	public static void updateActivite(Activite uneActivite) {
	    String requete = "update activite set nomActivite = '" + uneActivite.getNomActivite()
	        + "', tarifA = " + uneActivite.getTarifA()
	        + " where codeA = " + uneActivite.getCodeA() + ";";
	    executerRequete(requete);
	}

	public static Activite selectWhereActivite(int codeA) {
	    String requete = "select * from activite where codeA = " + codeA + ";";
	    Activite uneActivite = null;
	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet lesResultats = unStat.executeQuery(requete);
	        if (lesResultats.next()) {
	            uneActivite = new Activite(
	                lesResultats.getInt("codeA"),
	                lesResultats.getString("nomActivite"),
	                lesResultats.getFloat("tarifA")
	            );
	        }
	        unStat.close();
	        uneConnexion.seDeConnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur d'execution de la requete : " + requete);
	    }
	    return uneActivite;
	}

	public static ArrayList<Activite> selectAllActivites() {
	    String requete = "select * from activite;";
	    ArrayList<Activite> lesActivites = new ArrayList<>();
	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet lesResultats = unStat.executeQuery(requete);
	        while (lesResultats.next()) {
	            Activite uneActivite = new Activite(
	                lesResultats.getInt("codeA"),
	                lesResultats.getString("nomActivite"),
	                lesResultats.getFloat("tarifA")
	            );
	            lesActivites.add(uneActivite);
	        }
	        unStat.close();
	        uneConnexion.seDeConnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur d'execution de la requete : " + requete);
	    }
	    return lesActivites;
	}

	public static ArrayList<Activite> selectLikeActivites(String filtre) {
	    String requete = "select * from activite where nomActivite like '%" + filtre + "%';";
	    ArrayList<Activite> lesActivites = new ArrayList<>();
	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet lesResultats = unStat.executeQuery(requete);
	        while (lesResultats.next()) {
	            Activite uneActivite = new Activite(
	                lesResultats.getInt("codeA"),
	                lesResultats.getString("nomActivite"),
	                lesResultats.getFloat("tarifA")
	            );
	            lesActivites.add(uneActivite);
	        }
	        unStat.close();
	        uneConnexion.seDeConnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur d'execution de la requete : " + requete);
	    }
	    return lesActivites;
	}
	/**************************** Gestion des utilisateurs (User) ********************/
	public static void insertUser(User unUser) {
	    String requete = "insert into user values (null, '" + unUser.getNom()
	        + "', '" + unUser.getPrenom() + "', '" + unUser.getEmail()
	        + "', '" + unUser.getMdp() + "', '" + unUser.getRole() + "');";
	    executerRequete(requete);
	}

	public static void deleteUser(int idUser) {
	    String requete = "delete from user where id = " + idUser + ";";
	    executerRequete(requete);
	}

	public static void updateUser(User unUser) {
	    String requete = "update user set nom = '" + unUser.getNom()
	        + "', prenom = '" + unUser.getPrenom()
	        + "', email = '" + unUser.getEmail()
	        + "', mdp = '" + unUser.getMdp()
	        + "', role = '" + unUser.getRole()
	        + "' where id = " + unUser.getiduser() + ";";
	    executerRequete(requete);
	}

	public static User selectWhereUser(int idUser) {
	    String requete = "select * from user where id = " + idUser + ";";
	    User unUser = null;
	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet lesResultats = unStat.executeQuery(requete);
	        if (lesResultats.next()) {
	            unUser = new User(
	                lesResultats.getInt("id"),
	                lesResultats.getString("nom"),
	                lesResultats.getString("prenom"),
	                lesResultats.getString("email"),
	                lesResultats.getString("mdp"),
	                lesResultats.getString("role")
	            );
	        }
	        unStat.close();
	        uneConnexion.seDeConnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur d'execution de la requete : " + requete);
	    }
	    return unUser;
	}

	public static ArrayList<User> selectAllUsers() {
	    String requete = "select * from user;";
	    ArrayList<User> lesUsers = new ArrayList<>();
	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet lesResultats = unStat.executeQuery(requete);
	        while (lesResultats.next()) {
	            User unUser = new User(
	                lesResultats.getInt("id"),
	                lesResultats.getString("nom"),
	                lesResultats.getString("prenom"),
	                lesResultats.getString("email"),
	                lesResultats.getString("mdp"),
	                lesResultats.getString("role")
	            );
	            lesUsers.add(unUser);
	        }
	        unStat.close();
	        uneConnexion.seDeConnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur d'execution de la requete : " + requete);
	    }
	    return lesUsers;
	}

	public static ArrayList<User> selectLikeUsers(String filtre) {
	    String requete = "select * from user where nom like '%" + filtre + "%' or "
	        + "prenom like '%" + filtre + "%' or email like '%" + filtre + "%' or "
	        + "role like '%" + filtre + "%';";
	    ArrayList<User> lesUsers = new ArrayList<>();
	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet lesResultats = unStat.executeQuery(requete);
	        while (lesResultats.next()) {
	            User unUser = new User(
	                lesResultats.getInt("id"),
	                lesResultats.getString("nom"),
	                lesResultats.getString("prenom"),
	                lesResultats.getString("email"),
	                lesResultats.getString("mdp"),
	                lesResultats.getString("role")
	            );
	            lesUsers.add(unUser);
	        }
	        unStat.close();
	        uneConnexion.seDeConnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur d'execution de la requete : " + requete);
	    }
	    return lesUsers;
	}

	
	/******************** Autres méthodes ***************************/
	public static void executerRequete(String requete) {
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			unStat.execute(requete);
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
	}
	
	public static User selectWhereUser(String email, String mdp) {
		  String requete = "select * from user where email = '" +email + "' and mdp ='"+mdp+"';";
		User unUser = null;
	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet lesResultats = unStat.executeQuery(requete);
	        if (lesResultats.next()) {
	            unUser = new User(
	                lesResultats.getInt("id"),
	                lesResultats.getString("nom"),
	                lesResultats.getString("prenom"),
	                lesResultats.getString("email"),
	                lesResultats.getString("mdp"),
	                lesResultats.getString("role")
	            );
	        }
	        unStat.close();
	        uneConnexion.seDeConnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur d'execution de la requete : " + requete);
	        exp.printStackTrace();
	    }
	    return unUser;
	}
}
