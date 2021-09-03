package NormOptionFrameRéceptionEquipement;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RéceptionEquipement extends JFrame implements ActionListener
{
	private static final long serialVersionUID = -6989785829309471187L;
	private JPanel contentPane;
	private String sql;
	private Connection con;
	private ResultSet rs;
	private PreparedStatement ps;
	private JMenuItem openFile, aideItem, addSrvItem, addFournItem, addMrqItem, addDesItem;
	private BackgroundMenuBar menuBar;
	private JMenu fileMenu, outilsMenu;
	private JTable table;
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
					RéceptionEquipement frame = new RéceptionEquipement();
					frame.setVisible(true);
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public RéceptionEquipement() 
	{
		Image img = new ImageIcon(this.getClass().getResource("/Icons/frame.png")).getImage();
		setIconImage(img);
		setResizable(false);
		setTitle("R\u00E9ception des \u00E9quipements");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(0, 0, WindowSize.widthScreen, WindowSize.heightScreen);
		setExtendedState(MAXIMIZED_BOTH);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(85,122,177));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 89, 1340, 560);
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
		
		table = new JTable();
		table.setEnabled(false);
		table.setBackground(Color.LIGHT_GRAY);
		table.setModel(new DefaultTableModel(
				new Object[][] {
					
				},
				
				new String[] {
					"idItem", "Service", "Fournisseur", "Fabricant", "Type d'appareil", "Désignation", "Numéro de serie",
					"Date de Réception", "Motif de Réception", "Aspect du colis", "Admission", "Nom du réceptionniste", "Classe Médicale"
				}));
		scrollPane.setViewportView(table);
		
		Image addImg = new ImageIcon(this.getClass().getResource("/UI/Utility/add.png")).getImage();
		Image imgRef = new ImageIcon(this.getClass().getResource("/UI/Utility/act.png")).getImage();
		Image imgRet = new ImageIcon(this.getClass().getResource("/UI/Utility/ret.png")).getImage();
		Image modImg = new ImageIcon(this.getClass().getResource("/UI/Utility/mod.png")).getImage();
		Image delImg = new ImageIcon(this.getClass().getResource("/UI/Utility/del.png")).getImage();
		
		searchField = new JTextField();
		searchField.setText("Recherche");
		searchField.setHorizontalAlignment(SwingConstants.CENTER);
		searchField.setBounds(546, 41, 275, 28);
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
		
		retLbl = new JLabel("Retour vers la page principale");
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
		retLbl.setBounds(10, 33, 45, 45);
		retLbl.setIcon(new ImageIcon(imgRet));
		contentPane.add(retLbl);
		
		addLbl = new JLabel("");
		addLbl.setToolTipText("Ajouter un item");
		addLbl.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				AddOptions addOpt = new AddOptions();
				addOpt.setVisible(true);
			}
		});
		addLbl.setBounds(338, 656, 73, 73);
		addLbl.setIcon(new ImageIcon(addImg));
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
		actLbl.setBounds(973, 656, 73, 73);
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
													   loginInfo.getPwd());
	}
	
	private void Update() throws ClassNotFoundException, SQLException 
	{
		ArrayList<RecepClasse> list = recpList();
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);
		Object[] column = new Object[13];
		for(int i = 0; i < list.size(); i++) 
		{
			column[0]  = list.get(i).getIdItem();
			column[1]  = list.get(i).getService();
			column[2]  = list.get(i).getFournisseur();
			column[3]  = list.get(i).getFabricant();
			column[4]  = list.get(i).getTypeAppa();
			column[5]  = list.get(i).getDesignation();
			column[6]  = list.get(i).getnSerie();
			column[7]  = list.get(i).getDateRecep();
			column[8]  = list.get(i).getMotifRecp();
			column[9]  = list.get(i).getAspecColis();
			column[10] = list.get(i).getAdmission();
			column[11] = list.get(i).getNomRecept();
			column[12] = list.get(i).getClasseMedical();
			table.setAlignmentX(CENTER_ALIGNMENT);
			table.setAlignmentY(CENTER_ALIGNMENT);
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
			for(int y = 0; y < column.length ; y++) 
			{
				table.getColumnModel().getColumn(y).setCellRenderer(centerRenderer);
			}
			tableModel.addRow(column);
			con.close();	
		}
	}
	
	private ArrayList<RecepClasse> recpList()
	{
		ArrayList<RecepClasse> recpt = new ArrayList<RecepClasse>();
		sql = "SELECT idItem, Service, Fournisseur, Fabricant, TypeApp, Designation, NSerie, DATE_FORMAT(DateRecep, '%d/%m/%Y') "
				+ "as DateRecep, MotifRecep, AspectColis, Admission, NomRecep, ClasseMedi FROM gmao.receptionequipement;";
		try 
		{
			Connect();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			RecepClasse recepC;
			while(rs.next()) 
			{
				recepC = new RecepClasse(rs.getInt("idItem"),         rs.getString("Service"),    rs.getString("Fournisseur"),  rs.getString("Fabricant"),    rs.getString("TypeApp"),    rs.getString("Designation"), 
						                 rs.getString("NSerie"),      rs.getString("DateRecep"),  rs.getString("MotifRecep"),   rs.getString("AspectColis"),  rs.getString("Admission"),  rs.getString("NomRecep"), 
						                 rs.getString("ClasseMedi")); 
				recpt.add(recepC);
			}
		} 
		catch(Exception e1) 
		{
			e1.printStackTrace();
		}
		return recpt;
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