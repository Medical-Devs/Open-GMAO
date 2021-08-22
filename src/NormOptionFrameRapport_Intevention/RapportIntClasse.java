package NormOptionFrameRapport_Intevention;

public class RapportIntClasse {
	private int idItem;
	private String service, designation, typeAppareil, marque, modele, 
				   nInventaire, nSerie, dateInter, refRapp;
	
	public RapportIntClasse(int idItem, 
							String service, 
							String designation, 
							String typeAppareil, 
							String marque, 
			                String modele, 
			                String nInventaire, 
			                String nSerie, 
			                String dateInter, 
			                String refRapp) 
	{
		this.idItem = idItem;
		this.service = service;
		this.designation = designation;
		this.typeAppareil = typeAppareil;
		this.marque = marque;
		this.modele = modele;
		this.nInventaire= nInventaire;
		this.nSerie = nSerie;
		this.dateInter = dateInter;
		this.refRapp = refRapp;
	}

	public int getIdItem() { return idItem; }

	public String getService() { return service; }

	public String getDesignation() { return designation; }

	public String getTypeAppareil() { return typeAppareil; }

	public String getMarque() { return marque;}

	public String getModele() { return modele; }

	public String getnInventaire() { return nInventaire; }

	public String getnSerie() { return nSerie; }

	public String getDateInter() { return dateInter; }

	public String getRefRapp() { return refRapp; }	
}