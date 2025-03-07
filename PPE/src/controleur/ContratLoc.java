package controleur;

public class ContratLoc {
    private int refC;
    private int dureeC;
    private String dateDebutC;
    private String dateFinC;
    private int nbPersonneC;
    private int idClient;
    private int idHabitation;

    public ContratLoc(int refC, int dureeC, String dateDebutC, String dateFinC, int nbPersonneC, int idClient, int idHabitation) {
    	this.refC = refC;
        this.dureeC = dureeC;
        this.dateDebutC = dateDebutC;
        this.dateFinC = dateFinC;
        this.nbPersonneC = nbPersonneC;
        this.idClient = idClient;
        this.idHabitation = idHabitation;
    }
    
    public ContratLoc (String dateDebutC, String dateFinC, int nbPersonneC, int idClient, int idHabitation) {
    	this.refC = 0;
    	this.dureeC = dureeC;
        this.dateDebutC = dateDebutC;
        this.dateFinC = dateFinC;
        this.nbPersonneC = nbPersonneC;
        this.idClient = idClient;
        this.idHabitation = idHabitation;

    }

	public int getRefC() {
		return refC;
	}

	public void setRefC(int refC) {
		this.refC = refC;
	}

	public int getDureeC() {
		return dureeC;
	}

	public void setDureeC(int dureeC) {
		this.dureeC = dureeC;
	}

	public String getDateDebutC() {
		return dateDebutC;
	}

	public void setDateDebutC(String dateDebutC) {
		this.dateDebutC = dateDebutC;
	}

	public String getDateFinC() {
		return dateFinC;
	}

	public void setDateFinC(String dateFinC) {
		this.dateFinC = dateFinC;
	}

	public int getNbPersonneC() {
		return nbPersonneC;
	}

	public void setNbPersonneC(int nbPersonneC) {
		this.nbPersonneC = nbPersonneC;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public int getIdHabitation() {
		return idHabitation;
	}

	public void setIdHabitation(int idHabitation) {
		this.idHabitation = idHabitation;
	}
    
}
