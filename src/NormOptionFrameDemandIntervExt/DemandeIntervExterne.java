package NormOptionFrameDemandIntervExt;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import EditMultipleObjects.BackgroundMenuBar;
import EditMultipleObjects.DocEditor;
import EditMultipleObjects.WindowSize;
import Filters.Désignation;
import Filters.EntrepriseList;
import Filters.Help;
import Filters.Marque;
import Filters.Service_Conc;
import OptionXLogin.HomeMenu;
import OptionXLogin.loginInfo;

public class DemandeIntervExterne extends JFrame implements ActionListener 
{
	private static final long serialVersionUID = -5707976813502140728L;
	private JPanel contentPane;
	private DefaultTableCellRenderer centerRenderer;
	private JTable table;
	private Connection con;
	private JMenuItem openFile, aideItem, addSrvItem, addFournItem, addMrqItem, addDesItem;
	private BackgroundMenuBar menuBar;
	private JMenu fileMenu, outilsMenu;
	private ResultSet rs;
	private String sql;
	private PreparedStatement ps;
	private JTextField searchField;
	private JLabel retLbl;
	private JLabel addLbl;
	private JLabel delLbl;
	private JLabel modLbl;
	private JLabel actLbl;
	private JScrollPane scrollPane;
	private Service_Conc srv = new Service_Conc();
	private Désignation des = new Désignation();
	private EntrepriseList ent = new EntrepriseList();
	private Help hl = new Help();
	private Marque mrq = new Marque();
	
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					DemandeIntervExterne frame = new DemandeIntervExterne();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	public DemandeIntervExterne() {
		Image img = new ImageIcon(this.getClass().getResource("/Icons/frame.png")).getImage();
		setIconImage(img);
		setResizable(false);
		setTitle("Demande d'Intervention Externe");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, WindowSize.widthScreen, WindowSize.heightScreen);
		setExtendedState(MAXIMIZED_BOTH);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(85,122,177));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 93, 1340, 551);
		contentPane.add(scrollPane);
	
		menuBar = new BackgroundMenuBar();
		menuBar.setBounds(0, 0, 1928, 22);
		menuBar.setColor(new Color(53,98,168));
		contentPane.add(menuBar);
		
		fileMenu = new JMenu("Fichier");
		fileMenu.setForeground(Color.WHITE);

		openFile = new JMenuItem("Ouvrir un nouveau fichier");
		
		openFile.addActionListener(this);
		fileMenu.add(openFile);
		menuBar.add(fileMenu);
		
		outilsMenu = new JMenu("Outils");
		outilsMenu.setForeground(Color.WHITE);
		outilsMenu.setBackground(UIManager.getColor("Button.disabledShadow"));
		menuBar.add(outilsMenu);
		
		addSrvItem = new JMenuItem("Ajouter un service");
		addSrvItem.addActionListener(this);
		outilsMenu.add(addSrvItem);
		
		addDesItem = new JMenuItem("Ajouter une d\u00E9signation");
		addDesItem.addActionListener(this);
		outilsMenu.add(addDesItem);
		
		addFournItem = new JMenuItem("Ajouter un fournisseur");
		addFournItem.addActionListener(this);
		outilsMenu.add(addFournItem);
		
		addMrqItem = new JMenuItem("Ajouter une marque");
		addMrqItem.addActionListener(this);
		outilsMenu.add(addMrqItem);
		
		aideItem = new JMenuItem("Aide");
		aideItem.addActionListener(this);
		outilsMenu.add(aideItem);
		
		searchField = new JTextField();
		searchField.setText("Recherche");
		searchField.setHorizontalAlignment(SwingConstants.CENTER);
		searchField.setBounds(548, 47, 275, 30);
		
		table = new JTable();
		table.setEnabled(false);
		table.setBackground(Color.LIGHT_GRAY);
		table.setModel(new DefaultTableModel(
				new Object[][] {
			
				},
				new String[] {
						"idItem", "Type d'appareil", "Service", "Marque", "Modèle", "Fournisseur", "Numéro d'enregistrement", 
						"Numéro de Serie", "Type d'intervention", "Réference de la Demande", "Date d'intervention"
				}));
		
		scrollPane.setViewportView(table);
		
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
		table.setRowSorter(sorter);
		searchField.getDocument().addDocumentListener(new DocumentListener() 
		{
			@Override
			public void insertUpdate(DocumentEvent e) { Search(searchField.getText().trim()); }

			@Override
			public void removeUpdate(DocumentEvent e) { Search(searchField.getText().trim()); }

			@Override
			public void changedUpdate(DocumentEvent e) { Search(searchField.getText().trim()); }
			
			public void Search(String str) 
			{
				if(str.length() == 0) 
				{
					sorter.setRowFilter(null);
				} 
				else 
				{
					sorter.setRowFilter(RowFilter.regexFilter(str));
				}
			}
		});

		contentPane.add(searchField);
		searchField.setColumns(10);
		
		Image addImg = new ImageIcon(this.getClass().getResource("/UI/Utility/add.png")).getImage();
		Image imgRef = new ImageIcon(this.getClass().getResource("/UI/Utility/act.png")).getImage();
		Image imgRet = new ImageIcon(this.getClass().getResource("/UI/Utility/ret.png")).getImage();
		Image modImg = new ImageIcon(this.getClass().getResource("/UI/Utility/mod.png")).getImage();
		Image delImg = new ImageIcon(this.getClass().getResource("/UI/Utility/del.png")).getImage();
		
		retLbl = new JLabel("");
		retLbl.setToolTipText("Retour vers la page principale");
		retLbl.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				HomeMenu hm = new HomeMenu();
				hm.setVisible(true);
				dispose();
			}
		});
		retLbl.setIcon(new ImageIcon(imgRet));
		retLbl.setBounds(10, 33, 45, 45);
		contentPane.add(retLbl);
		
		addLbl = new JLabel("");
		addLbl.setToolTipText("Ajouter un item");
		addLbl.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				try {
					AddOptionFrame addOpt = new AddOptionFrame();
					addOpt.setVisible(true);
				} 
				catch(Exception e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		addLbl.setIcon(new ImageIcon(addImg));
		addLbl.setBounds(338, 655, 73, 73);
		contentPane.add(addLbl);
		
		delLbl = new JLabel("");
		delLbl.setToolTipText("Supprimer un item");
		delLbl.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				DelOptionFrame delOpt = new DelOptionFrame();
				delOpt.setVisible(true);
			}
		});
		delLbl.setBounds(550, 655, 73, 73);
		delLbl.setIcon(new ImageIcon(delImg));
		contentPane.add(delLbl);
		
		modLbl = new JLabel("");
		modLbl.setToolTipText("Modifier un item");
		modLbl.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				try 
				{
					chooseMod cm = new chooseMod();
					cm.setVisible(true);
				} 
				catch(Exception e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		modLbl.setIcon(new ImageIcon(modImg));
		modLbl.setBounds(761, 655, 73, 73);
		contentPane.add(modLbl);
		
		actLbl = new JLabel("");
		actLbl.setToolTipText("Actualiser");
		actLbl.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				try 
				{
					Update();
				} 
				catch(Exception e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		actLbl.setIcon(new ImageIcon(imgRef));
		actLbl.setBounds(973, 655, 73, 73);
		contentPane.add(actLbl);
		
		try 
		{
			Update();
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	private void Connect() throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.mysql.jdbc.Driver");
		con = (Connection) DriverManager.getConnection(loginInfo.getUrl(), 
													   loginInfo.getUser(), 
													   loginInfo.getPwd());
	}
	
	private void Update() throws ClassNotFoundException, SQLException 
	{
		ArrayList<DemandIntClasse> list = demList();
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);
		Object[] column = new Object[11];
		for(int i = 0; i < list.size() ; i++) {
			column[0] = list.get(i).getIdItem();
			column[1] = list.get(i).getTypeAppareil();
			column[2] = list.get(i).getService();
			column[3] = list.get(i).getMarque();
			column[4] = list.get(i).getModele();
			column[5] = list.get(i).getFournisseur();
			column[6] = list.get(i).getNEnregistrement();
			column[7] = list.get(i).getNSerie();
			column[8] = list.get(i).getTypeInternvention();
			column[9] = list.get(i).getReferenceDemande();
			column[10] = list.get(i).getDate();
			table.setAlignmentX(CENTER_ALIGNMENT);
			table.setAlignmentY(CENTER_ALIGNMENT);
			centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(JLabel.CENTER);
			for(int y = 0; y < column.length ; y++) 
			{
				table.getColumnModel().getColumn(y).setCellRenderer(centerRenderer);
			}
			tableModel.addRow(column);
			con.close();
		}
	}
	
	private ArrayList<DemandIntClasse> demList()
	{
		ArrayList<DemandIntClasse> dem = new ArrayList<>();
		sql = "SELECT idItem, TypeAppareil, Service, Marque, Modele, Fournisseur, NEnregistrement, "
				+ "NSerie, TypeIntervention, ReferenceDemande, DATE_FORMAT(Date, '%d/%m/%Y') AS Date FROM gmao.demandeintexterne;";
		try {
			Connect();
			ps = (PreparedStatement) con.prepareStatement(sql);
			rs = ps.executeQuery();
			DemandIntClasse demandeC;
			while(rs.next()) 
			{
				demandeC = new DemandIntClasse( rs.getInt("idItem"),              rs.getString("TypeAppareil"),     rs.getString("Service"),         rs.getString("Marque"), 
						                        rs.getString("Modele"),           rs.getString("Fournisseur"),      rs.getString("NEnregistrement"), rs.getString("NSerie"), 
						                        rs.getString("TypeIntervention"), rs.getString("ReferenceDemande"), rs.getString("Date"));
				dem.add(demandeC);
			}
		}
		catch(Exception e1) 
		{
			JOptionPane.showMessageDialog(null, e1);
		}
		return dem;
	}	
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == openFile) { DocEditor.main(null); } 
		
		if(e.getSource() == addSrvItem) { srv.setVisible(true); } 
		
		if(e.getSource() == addDesItem) { des.setVisible(true); } 
		
		if(e.getSource() == addFournItem) { ent.setVisible(true); } 
		
		if(e.getSource() == addMrqItem) { mrq.setVisible(true); }
		
		if(e.getSource() == aideItem) { hl.setVisible(true); }
	}
}