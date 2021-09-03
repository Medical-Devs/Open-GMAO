package OptionFrameMaintenance;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
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
import javax.swing.SwingConstants;

public class Maintenance extends JFrame implements ActionListener
{
	private static final long serialVersionUID = -7974823979960662285L;
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private String sql;
	private Connection con;
	private JMenuItem openFile, aideItem, addSrvItem, addFournItem, addMrqItem, addDesItem;
	private BackgroundMenuBar menuBar;
	private JMenu fileMenu, outilsMenu;
	private PreparedStatement ps;
	private ResultSet rs;
	private DefaultTableCellRenderer centerRenderer;
	private JTextField searchField;
	private JLabel addLbl;
	private JLabel delLbl;
	private JLabel modLbl;
	private JLabel updLbl;
	private JLabel retLbl;
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
					Maintenance frame = new Maintenance();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	public Maintenance() 
	{
		Image img = new ImageIcon(this.getClass().getResource("/Icons/frame.png")).getImage();
		setIconImage(img);
		setType(Type.POPUP);
		setTitle("Maintenance");
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(0, 0, WindowSize.widthScreen, WindowSize.heightScreen);
		setExtendedState(MAXIMIZED_BOTH);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(85,122,177));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		scrollPane= new JScrollPane();
		scrollPane.setBounds(10, 95, 1340, 550);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setEnabled(false);
		table.setBackground(Color.LIGHT_GRAY);
		table.setModel(new DefaultTableModel(
				new Object[][] {
					
				},
				
				new String[] {
						"idDm", "Service", "Désignation", "Equipement", "Etat", "Marque", "Modèle", "Numéro de Série", 
						"Date Prévue", "Date d'Intervention", "Décalage d'Intervention", "Durée d'Intervention", "Montant d'Intervention" 
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
		searchField.setHorizontalAlignment(SwingConstants.CENTER);
		searchField.setText("Recherche");
		searchField.setBounds(546, 43, 275, 28);
		
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
		
		addLbl = new JLabel("");
		addLbl.setToolTipText("Ajouter un item");
		addLbl.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				AddOptionFrame op = new AddOptionFrame();
				op.setVisible(true);
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
				DelOptionFrame delOpt = new DelOptionFrame();
				delOpt.setVisible(true);
			}
		});
		delLbl.setBounds(550, 656, 73, 73);
		delLbl.setIcon(new ImageIcon(delImg));
		contentPane.add(delLbl);
		
		updLbl = new JLabel("");
		updLbl.setToolTipText("Actualiser");
		updLbl.addMouseListener(new MouseAdapter() 
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
		updLbl.setIcon(new ImageIcon(imgRef));
		
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
		modLbl.setBounds(761, 656, 73, 73);
		modLbl.setIcon(new ImageIcon(modImg));
		contentPane.add(modLbl);
		
		updLbl.setBounds(973, 656, 73, 73);
		contentPane.add(updLbl);
		
		retLbl = new JLabel("");
		retLbl.setToolTipText("Retour vers la page principale");
		retLbl.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				try 
				{
					HomeMenu hm = new HomeMenu();
					hm.setVisible(true);
					dispose();
				} 
				catch (Exception e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		retLbl.setBounds(10, 33, 45, 45);
		retLbl.setIcon(new ImageIcon(imgRet));
		contentPane.add(retLbl);
		
		try 
		{
			Update();
		} 
		catch (ClassNotFoundException | SQLException e1) 
		{
			e1.printStackTrace();
		}
	}
	
	protected ArrayList<MaintenanceClasse> mainList()
	{
		ArrayList<MaintenanceClasse> maint = new ArrayList<>();
		sql = "SELECT idMain, Service, Designation, Equipement, Etat, Marque, Modele, NdeSerie, DATE_FORMAT(DatePrevu, '%d/%m/%Y') as DatePrevu, "
				+ "DATE_FORMAT(Date_Intervention, '%d/%m/%Y') as Date_Intervention, Decalage_Intervention, Duree_Intervention, Montant_Intervention FROM gmao.maintenance;";
		try 
		{
			Connect();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			MaintenanceClasse mainC;
			while(rs.next()) 
			{
				mainC = new MaintenanceClasse(rs.getInt("idMain"),                rs.getString("Service"),              rs.getString("Designation"), 
											  rs.getString("Equipement"),         rs.getString("Etat"),                 rs.getString("Marque"), 
											  rs.getString("Modele"),             rs.getString("NdeSerie"),             rs.getString("DatePrevu"), 
											  rs.getString("Date_Intervention"),  rs.getString("Decalage_Intervention"),
											  rs.getString("Duree_Intervention"), rs.getFloat("Montant_Intervention"));
				maint.add(mainC);				
			}
		} 
		catch(Exception e1) 
		{
			JOptionPane.showMessageDialog(null, e1);
		}
		return maint;
	}
	
	protected void Update()throws ClassNotFoundException, SQLException
	{
		ArrayList<MaintenanceClasse> list = mainList();
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);
		Object[] column = new Object[13];
		for(int i = 0; i < list.size(); i++) 
		{
			column[0] = list.get(i).getIdMain();
			column[1] = list.get(i).getService();
			column[2] = list.get(i).getDesignation();
			column[3] = list.get(i).getEquipement();
			column[4] = list.get(i).getEtat();
			column[5] = list.get(i).getMarque();
			column[6] = list.get(i).getModele();
			column[7] = list.get(i).getNdeSerie();
			column[8] = list.get(i).getDatePrevu();
			column[9] = list.get(i).getDate_Internvention();
			column[10] = list.get(i).getDecalage_Intervention();
			column[11] = list.get(i).getDuree_Intervention();
			column[12] = list.get(i).getMontant_Intervention();
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
	
	protected void Connect() throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(loginInfo.getUrl(), loginInfo.getUser(), loginInfo.getPwd());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == openFile) { DocEditor.main(null); }
		
		if(e.getSource() == addSrvItem) { srv.setVisible(true); } 
		
		if(e.getSource() == addDesItem) { des.setVisible(true); } 
		
		if(e.getSource() == addFournItem) { ent.setVisible(true); } 
		
		if(e.getSource() == addMrqItem) { mrq.setVisible(true); }
		
		if(e.getSource() == aideItem) { hl.setVisible(true); }
	}
}