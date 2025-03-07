package controleur;

public class ContratMandatLocatif {
	private int idContrat ; 
	private String etatContrat ;
	private int dateSignature;
	private int idProprietaire;
	private int idHabitation;
	
	public ContratMandatLocatif (int idContrat, String etatContrat, int dateSignature, int idProprietaire, int idHabitation) {
		 
		this.idContrat = idContrat;
		this.etatContrat = etatContrat;
		this.dateSignature = dateSignature;
		this.idProprietaire = idProprietaire;
		this.idHabitation = idHabitation;
		
		
	} 
	
	public ContratMandatLocatif ( String etatContrat, int dateSignature, int idProprietaire, int idHabitation) {
		 
		this.idContrat = 0;
		this.etatContrat = etatContrat;
		this.dateSignature = dateSignature;
		this.idProprietaire = idProprietaire;
		this.idHabitation = idHabitation;
		
	}

	public int getIdContrat() {
		return idContrat;
	}

	public void setIdContrat(int idContrat) {
		this.idContrat = idContrat;
	}

	public String getEtatContrat() {
		return etatContrat;
	}

	public void setEtatContrat(String etatContrat) {
		this.etatContrat = etatContrat;
	}

	public int getDateSignature() {
		return dateSignature;
	}

	public void setDateSignature(int dateSignature) {
		this.dateSignature = dateSignature;
	}

	public int getIdProprietaire() {
		return idProprietaire;
	}

	public void setIdProprietaire(int idProprietaire) {
		this.idProprietaire = idProprietaire;
	}

	public int getIdHabitation() {
		return idHabitation;
	}

	public void setIdHabitation(int idHabitation) {
		this.idHabitation = idHabitation;
	}

}
