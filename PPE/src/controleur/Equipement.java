package controleur;

public class Equipement {
	private int idEquipement ; 
	private String nomEquipement;
	private int qteEquipement;
	private int idTypeEquipement;
	
	public Equipement(int idEquipement, String nomEquipement, int qteEquipement, int idTypeEquipement) {
		 
		this.idEquipement = idEquipement;
		this.nomEquipement = nomEquipement;
		this.qteEquipement = qteEquipement;
		this.idTypeEquipement = idTypeEquipement;
		
		
		
		
		
	} 
	
	public Equipement (String nomEquipement, int qteEquipement, int idTypeEquipement) {
		 
		this.idEquipement = 0;
		this.nomEquipement = nomEquipement;
		this.qteEquipement = qteEquipement;
		this.idTypeEquipement = idTypeEquipement;
}

	public int getIdEquipement() {
		return idEquipement;
	}

	public void setIdEquipement(int idEquipement) {
		this.idEquipement = idEquipement;
	}

	public String getNomEquipement() {
		return nomEquipement;
	}

	public void setNomEquipement(String nomEquipement) {
		this.nomEquipement = nomEquipement;
	}

	public int getQteEquipement() {
		return qteEquipement;
	}

	public void setQteEquipement(int qteEquipement) {
		this.qteEquipement = qteEquipement;
	}

	public int getIdTypeEquipement() {
		return idTypeEquipement;
	}

	public void setIdTypeEquipement(int idTypeEquipement) {
		this.idTypeEquipement = idTypeEquipement;
	}


}
