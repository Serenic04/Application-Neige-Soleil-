package controleur;

public class Activite{
    private int codeA;
    private String nomActivite;
    private float tarifA;
    
    public Activite(int codeA,String nomActivite, float tarifA){
        this.codeA = codeA;
        this.nomActivite = nomActivite;
        this.tarifA = tarifA;
    }


	public Activite(String nomActivite, float tarifA){
	        this.codeA = 0;
	        this.nomActivite = nomActivite;
	        this.tarifA = tarifA;
	    }


public int getCodeA() {
	return codeA;
}


public void setCodeA(int codeA) {
	this.codeA = codeA;
}


public String getNomActivite() {
	return nomActivite;
}


public void setNomActivite(String nomActivite) {
	this.nomActivite = nomActivite;
}


public float getTarifA() {
	return tarifA;
}


public void setTarifA(float tarifA) {
	this.tarifA = tarifA;
}


}