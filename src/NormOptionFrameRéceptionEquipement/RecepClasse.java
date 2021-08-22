package NormOptionFrameRéceptionEquipement;

public class RecepClasse 
{
	private int idItem;
	private String service, fournisseur, fabricant, typeAppa, designation, nSerie, dateRecep, motifRecp,
	               aspecColis, admission, nomRecept, classeMedical;
	
	public RecepClasse(int idItem, 
					   String service, 
					   String fournisseur, 
					   String fabricant, 
					   String typeAppa,
			           String designation,
			           String nSerie, 
			           String dateRecep, 
			           String motifRecp, 
			           String aspecColis,
			           String admission, 
			           String nomRecept, 
			           String classeMedical) 
	{
		this.idItem = idItem;
		this.service = service;
		this.fournisseur = fournisseur;
		this.fabricant = fabricant;
		this.typeAppa = typeAppa;
		this.designation = designation;
		this.nSerie = nSerie;
		this.dateRecep = dateRecep;
		this.motifRecp = motifRecp;
		this.aspecColis = aspecColis;
		this.admission = admission;
		this.nomRecept = nomRecept;
		this.classeMedical = classeMedical;
	}

	public int getIdItem() { return idItem; }

	public String getService() { return service; }

	public String getFournisseur() { return fournisseur; }

	public String getFabricant() { return fabricant; }

	public String getTypeAppa() { return typeAppa; }

	public String getDesignation() { return designation; }

	public String getnSerie() { return nSerie; }

	public String getDateRecep() { return dateRecep; }

	public String getMotifRecp() { return motifRecp; }

	public String getAspecColis() { return aspecColis; }

	public String getAdmission() { return admission; }

	public String getNomRecept() { return nomRecept; }

	public String getClasseMedical() { return classeMedical; }
}