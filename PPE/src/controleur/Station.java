package controleur;

public class Station {
	private int codeS ; 
	private String nomS;
	private int etoiles;
	private String codeR;
	
	public Station(int codeS, String nomS, int etoiles, String codeR) {
		 
		this.codeS = codeS;
		this.nomS = nomS;
		this.etoiles = etoiles;
		this.codeR = codeR;
		
		
	} 
	
	public Station (String nomS, int etoiles, String codeR) {
		 
		this.nomS = nomS;
		this.etoiles = etoiles;
		this.codeR = codeR;
		
	}

	public int getCodeS() {
		return codeS;
	}

	public void setCodeS(int codeS) {
		this.codeS = codeS;
	}

	public String getNomS() {
		return nomS;
	}

	public void setNomS(String nomS) {
		this.nomS = nomS;
	}

	public int getEtoiles() {
		return etoiles;
	}

	public void setEtoiles(int etoiles) {
		this.etoiles = etoiles;
	}

	public String getCodeR() {
		return codeR;
	}

	public void setCodeR(String codeR) {
		this.codeR = codeR;
	}
	
}
