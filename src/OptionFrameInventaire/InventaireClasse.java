package OptionFrameInventaire;

public class InventaireClasse 
{
	private int Quantite, idItem;
	private String Service, Designation, TypeEquipement, Marque, Modele, NSerie, NContrat;

	public InventaireClasse(int idItem, 
							String Service, 
							String Designation, 
							String TypeEquipement, 
			                String Marque, 
			                String Modele, 
			                int Quantite, 
			                String NSerie, 
			                String NContrat) 
	{
		this.idItem = idItem;
		this.Service = Service;
		this.Designation = Designation;
		this.TypeEquipement = TypeEquipement;
		this.Marque = Marque;
		this.Modele = Modele;
		this.Quantite = Quantite;
		this.NSerie = NSerie;
		this.NContrat = NContrat;
	}

	public int getQuantite() { return Quantite; }

	public int getIdItem() { return idItem; }

	public String getService() { return Service; }

	public String getDesignation() { return Designation; }

	public String getTypeEquipement() { return TypeEquipement; }

	public String getMarque() { return Marque; }

	public String getModele() { return Modele; }

	public String getNSerie() { return NSerie; }

	public String getNContrat() { return NContrat; }
}