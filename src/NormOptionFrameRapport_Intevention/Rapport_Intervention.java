package NormOptionFrameRapport_Intevention;

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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
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

public class Rapport_Intervention extends JFrame implements ActionListener 
{
	private static final long serialVersionUID = -660206273638978455L;
	private JPanel contentPane;
	private JTable table;
	private JMenuItem openFile, aideItem, addSrvItem, addFournItem, addMrqItem, addDesItem;
	private BackgroundMenuBar menuBar;
	private JMenu fileMenu, outilsMenu;
	private String sql;
	private PreparedStatement ps;
	private ResultSet rs;
	private Connection con;
	private DefaultTableCellRenderer centerRenderer;
	private JScrollPane scrollPane;
	private JTextField searchField;
	private JLabel retLbl;
	private JLabel addLbl;
	private JLabel delLbl;
	private JLabel modLbl;
	private JLabel actLbl;
	private Service_Conc srv = new Service_Conc();
	private Désignation des = new Désignation();
	private EntrepriseList ent = new EntrepriseList();
	private Help hl = new Help();
	private Marque mrq = new Marque();
	
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			@Override
			public void run() 
			{
				try 
				{
					Rapport_Intervention frame = new Rapport_Intervention();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	public Rapport_Intervention() 
	{
		Image img = new ImageIcon(this.getClass().getResource("/Icons/frame.png")).getImage();
		setIconImage(img);
		setType(Type.POPUP);
		setResizable(false);
		setTitle("Rapport d'Intervention");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(0, 0, WindowSize.widthScreen, WindowSize.heightScreen);
		setExtendedState(MAXIMIZED_BOTH);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBackground(new Color(85,122,177));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(10, 101, 1340, 544);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setRowSelectionAllowed(false);
		table.setEnabled(false);
		table.setBackground(Color.LIGHT_GRAY);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				
			}, 
			
			new String[] {
					"idItem", "Service", "Désignation", "Type d'appareil", 
					"Marque", "Modèle", "Numéro d'inventaire","Numéro de Serie", 
					"Date d'intervention", "Référence du rapport"
			}
		));
		scrollPane.setViewportView(table);
		
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
		searchField.setToolTipText("");
		searchField.setText("Recherche");
		searchField.setHorizontalAlignment(SwingConstants.CENTER);
		searchField.setBounds(546, 52, 326, 28);
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
		
		retLbl.setHorizontalAlignment(SwingConstants.CENTER);
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
				try 
				{
					AddingOptions addOpt = new AddingOptions();
					addOpt.setVisible(true);
				} 
				catch(Exception e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		addLbl.setIcon(new ImageIcon(addImg));
		addLbl.setBounds(338, 656, 73, 73);
		contentPane.add(addLbl);
		
		delLbl = new JLabel("");
		delLbl.setToolTipText("Supprimer un item");
		delLbl.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				DelOptions delOpt = new DelOptions();
				delOpt.setVisible(true);
			}
		});
		delLbl.setBounds(550, 656, 73, 73);
		delLbl.setIcon(new ImageIcon(delImg));
		contentPane.add(delLbl);
		
		modLbl = new JLabel("");
		modLbl.setToolTipText("Modifier un item");
		modLbl.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				chooseMod cm = new chooseMod();
				cm.setVisible(true);
			}
		});
		
		modLbl.setIcon(new ImageIcon(modImg));
		modLbl.setBounds(761, 656, 73, 73);
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
		actLbl.setBounds(971, 656, 73, 73);
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
		con = DriverManager.getConnection(loginInfo.getUrl(), 
													   loginInfo.getUser(), 
													   loginInfo.getPwd()
													   );
	}
	
	private void Update() throws ClassNotFoundException, SQLException 
	{
		ArrayList<RapportIntClasse> list = rapIntList();
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);
		Object[] column = new Object[10];
		for(int i = 0; i < list.size() ; i++) 
		{
			column[0] = list.get(i).getIdItem();
			column[1] = list.get(i).getService();
			column[2] = list.get(i).getDesignation();
			column[3] = list.get(i).getTypeAppareil();
			column[4] = list.get(i).getMarque();
			column[5] = list.get(i).getModele();
			column[6] = list.get(i).getnInventaire();
			column[7] = list.get(i).getnSerie();
			column[8] = list.get(i).getDateInter();
			column[9] = list.get(i).getRefRapp();
			table.setAlignmentX(CENTER_ALIGNMENT);
			table.setAlignmentY(CENTER_ALIGNMENT);
			centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
			for(int y = 0; y < column.length ; y++) 
			{
				table.getColumnModel().getColumn(y).setCellRenderer(centerRenderer);
			}
			tableModel.addRow(column);
			con.close();
		}
	}
	
	private ArrayList<RapportIntClasse> rapIntList()
	{
		ArrayList<RapportIntClasse> rapInt = new ArrayList<>();
		sql = "SELECT idItem, Service, Designation, TypeAppareil, Marque, Modele, "
				+ "nInventaire, nSerie, DATE_FORMAT(Date_Intervention, '%d/%m/%Y') "
				+ "as Date_Intervention, Reference_Rapport FROM gmao.rapport_intervention;";
		try 
		{
			Connect();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			RapportIntClasse rapC;
			while(rs.next()) 
			{
				rapC = new RapportIntClasse(rs.getInt("idItem"),    	 	   rs.getString("Service"),     
											rs.getString("Designation"), 	   rs.getString("TypeAppareil"),     
											rs.getString("Marque"),      	   rs.getString("Modele"), 
											rs.getString("nInventaire"), 	   rs.getString("nSerie"),      
											rs.getString("Date_Intervention"), rs.getString("Reference_Rapport"));
				rapInt.add(rapC);
			}
		} 
		catch(Exception e1) 
		{
			e1.printStackTrace();
		}
		return rapInt;
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