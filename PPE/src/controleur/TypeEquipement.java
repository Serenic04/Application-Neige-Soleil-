package controleur;

public class TypeEquipement {
	private int idTypeEquipement ; 
	private String nomType, description;
	
	
	public TypeEquipement(int idTypeEquipement, String nomType, String description) {
		 
		this.idTypeEquipement = idTypeEquipement;
		this.nomType = nomType;
		this.description = description;
		
		
	} 
	
	public TypeEquipement (String nomType, String description) {
		 
		this.idTypeEquipement = 0;
		this.nomType = nomType;
		this.description = description;
		
	}

	public int getIdTypeEquipement() {
		return idTypeEquipement;
	}

	public void setIdTypeEquipement(int idTypeEquipement) {
		this.idTypeEquipement = idTypeEquipement;
	}

	public String getNomType() {
		return nomType;
	}

	public void setNomType(String nomType) {
		this.nomType = nomType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}
