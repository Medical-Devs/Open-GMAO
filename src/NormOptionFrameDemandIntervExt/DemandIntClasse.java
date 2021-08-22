package NormOptionFrameDemandIntervExt;

public class DemandIntClasse 
{
	private int idItem;
	private String TypeAppareil, Marque, Service, Modele, Fournisseur, 
				   NEnregistrement, NSerie, TypeInternvention, ReferenceDemande, 
				   Date;
	
	public DemandIntClasse(int idItem, 
						   String TypeAppareil, 
						   String Marque, 
						   String Modele, 
						   String Service, 
			               String Fournisseur, 
			               String NEnregistrement, 
			               String NSerie, 
			               String TypeIntervention, 
			               String ReferenceDemande, 
			               String Date) 
	{
		this.idItem = idItem;
		this.TypeAppareil = TypeAppareil;
		this.Marque = Marque;
		this.Modele = Modele;
		this.Service = Service;
		this.Fournisseur = Fournisseur;
		this.NEnregistrement = NEnregistrement;
		this.NSerie = NSerie;
		this.TypeInternvention = TypeIntervention;
		this.ReferenceDemande = ReferenceDemande;
		this.Date = Date;
	}

	public int getIdItem() { return idItem; }

	public String getTypeAppareil() { return TypeAppareil; }

	public String getMarque() { return Marque; }

	public String getService() { return Service; }

	public String getModele() { return Modele; }

	public String getFournisseur() { return Fournisseur; }

	public String getNEnregistrement() { return NEnregistrement; }

	public String getNSerie() { return NSerie; }

	public String getTypeInternvention() { return TypeInternvention; }

	public String getReferenceDemande() { return ReferenceDemande; }

	public String getDate() { return Date; }
}