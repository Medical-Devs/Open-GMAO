package OptionFrameStockDeRechange;

public class StockRechClasse 
{	
	private int idItem, Qte;
	private String Type, Marque, Modele, DateAcquisition, NdeSerie;
	
	public StockRechClasse(int idItem, 
						   String Type, 
						   String Marque, 
						   String Modele,
			               int Qte,
			               String DateAcquisition, 
			               String NdeSerie) 
	{
		this.idItem = idItem;
		this.Type = Type;
		this.Marque = Marque;
		this.Modele = Modele;
		this.Qte = Qte;
		this.DateAcquisition = DateAcquisition;
		this.NdeSerie = NdeSerie;
	}

	public int getIdItem() { return idItem; }

	public int getQte() { return Qte; }

	public String getType() { return Type; }

	public String getMarque() { return Marque; }

	public String getModele() { return Modele; }

	public String getDateAcquisition() { return DateAcquisition; }

	public String getNdeSerie() { return NdeSerie; }
}