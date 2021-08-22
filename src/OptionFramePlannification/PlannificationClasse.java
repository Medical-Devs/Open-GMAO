package OptionFramePlannification;

public class PlannificationClasse 
{	
	private int idPlan;
	private String Marque, Modele, TypeMaintenance, DateMaint, nSerie ,RefRapport;
	
	public PlannificationClasse(int idPlan, 	
								String Marque, 
								String Modele, 
								String TypeMaintenance, 
							    String DateMaint, 
							    String nSerie, 
							    String RefRapport) 
	{
		this.idPlan = idPlan;
		this.Marque = Marque;
		this.Modele = Modele;
		this.TypeMaintenance = TypeMaintenance;
		this.DateMaint = DateMaint;
		this.nSerie = nSerie;
		this.RefRapport = RefRapport;
	}

	public int getIdPlan() { return idPlan; }

	public String getMarque() { return Marque; }

	public String getModele() { return Modele; }

	public String getTypeMaintenance() { return TypeMaintenance; }

	public String getDateMaint() { return DateMaint; }

	public String getnSerie() { return nSerie; }

	public String getRefRapport() { return RefRapport; }
}