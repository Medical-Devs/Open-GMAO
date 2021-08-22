package OptionFrameMaintenance;

public class MaintenanceClasse 
{
	private int idMain;
	private float Montant_Intervention;
	private String Service, Designation, Equipement, Etat, Marque, 
				   Modele, NdeSerie, DatePrevu, Date_Internvention, 
				   Decalage_Intervention, Duree_Intervention;
	
	public MaintenanceClasse(int idMain, 
							 String Service, 
							 String Designation, 
							 String Equipement, 
							 String Etat, 
							 String Marque, 
							 String Modele, 
							 String NdeSerie, 
							 String DatePrevu, 	
							 String Date_Intervention, 
							 String Decalage_Intervention, 
							 String Duree_Intervention, 
							 float Montant_Intervention
							 ) 
	{
		this.idMain = idMain;
		this.Service = Service;
		this.Designation = Designation; 
		this.Equipement = Equipement;
		this.Etat = Etat;
		this.Marque = Marque;
		this.Modele = Modele;
		this.NdeSerie = NdeSerie;
		this.DatePrevu = DatePrevu;
		this.Date_Internvention = Date_Intervention;
		this.Decalage_Intervention = Decalage_Intervention;
		this.Duree_Intervention = Duree_Intervention;
		this.Montant_Intervention = Montant_Intervention;
	}

	public int getIdMain() { return idMain; }

	public float getMontant_Intervention() { return Montant_Intervention; }

	public String getService() { return Service; }

	public String getDesignation() { return Designation; }

	public String getEquipement() { return Equipement; }

	public String getEtat() { return Etat; }

	public String getMarque() { return Marque; }

	public String getModele() { return Modele; }

	public String getNdeSerie() { return NdeSerie; }

	public String getDatePrevu() { return DatePrevu; }

	public String getDate_Internvention() { return Date_Internvention; }
	
	public String getDecalage_Intervention() { return Decalage_Intervention; }

	public String getDuree_Intervention() { return Duree_Intervention; }
}