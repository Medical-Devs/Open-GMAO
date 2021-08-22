package NormOptionFrameCommandePRechange;

public class PieceRechClasse 
{
	private int  idPiece, Qte;
	private float PrixUHT;
	private String Fournisseur, NCommande, Service_Concerne, Date_Commande;
	
	public PieceRechClasse(int idPiece, 
						   String Fournisseur, 
						   String NCommande, 
			               float PrixUHT, 
			               int Qte, 
			               String Service_Concerne, 
			               String Date_Commande) 
	{
		this.idPiece = idPiece;
		this.Fournisseur = Fournisseur;
		this.NCommande = NCommande;
		this.PrixUHT = PrixUHT;
		this.Qte = Qte;
		this.Service_Concerne = Service_Concerne;
		this.Date_Commande = Date_Commande;
	}

	public int getIdPiece() { return idPiece; }

	public int getQte() { return Qte; }

	public float getPrixUHT() { return PrixUHT; }

	public String getFournisseur() { return Fournisseur; }

	public String getNCommande() { return NCommande; }

	public String getService_Concerne()  { return Service_Concerne; }

	public String getDate_Commande()  { return Date_Commande; }
}