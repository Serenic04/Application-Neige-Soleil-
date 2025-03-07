package controleur;

public class Region {
	String codeR;
	private int departement ; 
	private String villeR ;
	
	
	public Region(String String, int departement, String villeR) {
		 
		this.codeR = String;
		this.departement = departement;
		this.villeR = villeR;
		
		
	}


	public String getCodeR() {
		return codeR;
	}


	public void setCodeR(String codeR) {
		this.codeR = codeR;
	}


	public int getDepartement() {
		return departement;
	}


	public void setDepartement(int departement) {
		this.departement = departement;
	}


	public String getVilleR() {
		return villeR;
	}


	public void setVilleR(String villeR) {
		this.villeR = villeR;
	} 
	

}
