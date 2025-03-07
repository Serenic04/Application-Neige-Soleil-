package controleur;

public class Reservation {
	private int refR ; 
	private String dateR ; 
	private String dateHeureDebut;
	private String dateHeureFin;
	private int nbPersonne;
	private int idClient;
	
	public Reservation(int refR, String dateR, String dateHeureDebut, String dateHeureFin, int nbPersonne, int idClient) {
		 
		this.refR = refR;
		this.dateR = dateR;
		this.dateHeureDebut = dateHeureDebut;
		this.dateHeureFin = dateHeureFin;
		this.nbPersonne = nbPersonne;
		this.idClient = idClient;
		
		
		
		
	} 
	
	public Reservation( String dateR, String dateHeureDebut, String dateHeureFin, int nbPersonne, int idClient) {
		 
		this.refR = 0;
		this.dateR = dateR;
		this.dateHeureDebut = dateHeureDebut;
		this.dateHeureFin = dateHeureFin;
		this.nbPersonne = nbPersonne;
		this.idClient = idClient;
	
}

	public int getRefR() {
		return refR;
	}

	public void setRefR(int refR) {
		this.refR = refR;
	}

	public String getDateR() {
		return dateR;
	}

	public void setDateR(String dateR) {
		this.dateR = dateR;
	}

	public String getDateHeureDebut() {
		return dateHeureDebut;
	}

	public void setDateHeureDebut(String dateHeureDebut) {
		this.dateHeureDebut = dateHeureDebut;
	}

	public String getDateHeureFin() {
		return dateHeureFin;
	}

	public void setDateHeureFin(String dateHeureFin) {
		this.dateHeureFin = dateHeureFin;
	}

	public int getNbPersonne() {
		return nbPersonne;
	}

	public void setNbPersonne(int nbPersonne) {
		this.nbPersonne = nbPersonne;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
}