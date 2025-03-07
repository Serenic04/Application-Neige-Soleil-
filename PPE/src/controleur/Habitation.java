package controleur;

public class Habitation{
    private int idHabitation;
    private String adresse;
    private int taille;
    private int idProprietaire;
    private String codeR;

    public Habitation(int idHabitation, String adresse, int taille, int idProprietaire, String codeR){
        this.idHabitation = idHabitation;
        this.adresse = adresse;
        this.taille = taille;
        this.idProprietaire = idProprietaire;
        this.codeR = codeR;
    }

    public Habitation(String adresse, int taille, int idProprietaire, String codeR){
        this.idHabitation = 0;
        this.adresse = adresse;
        this.taille = taille;
        this.idProprietaire = idProprietaire;
        this.codeR = codeR;
    }

	public int getIdHabitation() {
		return idHabitation;
	}

	public void setIdHabitation(int idHabitation) {
		this.idHabitation = idHabitation;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public int getIdProprietaire() {
		return idProprietaire;
	}

	public void setIdProprietaire(int idProprietaire) {
		this.idProprietaire = idProprietaire;
	}

	public String getCodeR() {
		return codeR;
	}

	public void setCodeR(String codeR) {
		this.codeR = codeR;
	}



}