package OptionFrameStockDeRechange;

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
import javax.swing.SwingConstants;
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
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StockRechange extends JFrame implements ActionListener
{	
	private static final long serialVersionUID = -2218644679317233849L;
	private JTable table;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private String sql;
	private JMenuItem openFile, aideItem, addSrvItem, addFournItem, addMrqItem, addDesItem;
	private BackgroundMenuBar menuBar;
	private JMenu fileMenu, outilsMenu;
	private Connection con;
	private ResultSet rs;
	private PreparedStatement ps;
	private DefaultTableCellRenderer centerRenderer;
	private JLabel addLbl;
	private JLabel retLbl;
	private JLabel delLbl;
	private JLabel modifLbl;
	private JLabel actLbl;
	private Service_Conc srv = new Service_Conc();
	private Désignation des = new Désignation();
	private EntrepriseList ent = new EntrepriseList();
	private Help hl = new Help();
	private Marque mrq = new Marque();
	public  int k = 0;
	private JTextField searchField;
	
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run() 
			{
				try 
				{
					StockRechange frame = new StockRechange();
					frame.setVisible(true);
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	public StockRechange() 
	{
		Image img = new ImageIcon(this.getClass().getResource("/Icons/frame.png")).getImage();
		setIconImage(img);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setType(Type.POPUP);
		setTitle("Stock de rechange");
		setBounds(0, 0, WindowSize.widthScreen, WindowSize.heightScreen);
		setExtendedState(MAXIMIZED_BOTH);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(85,122,177));
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 91, 1340, 559);
		contentPane.add(scrollPane);
		setContentPane(contentPane);
		
		table = new JTable();
		table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		table.setBackground(Color.LIGHT_GRAY);
		table.setEnabled(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"idDm", "Type", "Marque", "Mod\u00E8le", "Quantit\u00E9", "Date d'acquisition", "Num\u00E9ro de S\u00E9rie"
			}
		));
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.getColumnModel().getColumn(6).setPreferredWidth(93);
		table.setRowSelectionAllowed(false);

		scrollPane.setViewportView(table);
		
		menuBar = new BackgroundMenuBar();
		menuBar.setBounds(0, 0, 1375, 22);
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
		searchField.setColumns(10);
		searchField.setBounds(546, 43, 275, 30);
		contentPane.add(searchField);
		
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
		table.setRowSorter(sorter);
		searchField.getDocument().addDocumentListener(new DocumentListener() {

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
		
		addLbl = new JLabel("");
		addLbl.setToolTipText("Ajouter un item");
		addLbl.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				AddOptionFrame addOpt = new AddOptionFrame();
				addOpt.setVisible(true);
			}
		});
		addLbl.setHorizontalAlignment(SwingConstants.CENTER);
		addLbl.setBounds(338, 656, 73, 73);
		Image add = new ImageIcon(this.getClass().getResource("/UI/Utility/add.png")).getImage();
		addLbl.setIcon(new ImageIcon(add));
		contentPane.add(addLbl);
		
		retLbl = new JLabel("");
		retLbl.setToolTipText("Retour \u00E0 la page principale");
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
		Image ret = new ImageIcon(this.getClass().getResource("/UI/Utility/ret.png")).getImage();
		retLbl.setIcon(new ImageIcon(ret));
		contentPane.add(retLbl);
		
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
		Image del = new ImageIcon(this.getClass().getResource("/UI/Utility/del.png")).getImage();
		delLbl.setIcon(new ImageIcon(del));
		contentPane.add(delLbl);
		
		modifLbl = new JLabel("");
		modifLbl.setToolTipText("Modifier un item");
		modifLbl.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				chooseMod cm = new chooseMod();
				cm.setVisible(true);
			}
		});
		modifLbl.setBounds(761, 656, 73, 73);
		Image mod = new ImageIcon(this.getClass().getResource("/UI/Utility/mod.png")).getImage();
		modifLbl.setIcon(new ImageIcon(mod));
		contentPane.add(modifLbl);
		
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
		actLbl.setBounds(973, 656, 73, 73);
		Image act = new ImageIcon(this.getClass().getResource("/UI/Utility/act.png")).getImage();
		actLbl.setIcon(new ImageIcon(act));
		contentPane.add(actLbl);

		try 
		{
			Update();
		}
		catch (ClassNotFoundException | SQLException e1)
		{
			e1.printStackTrace();
		}
	}
	
	private void Connect() throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.mysql.jdbc.Driver");
		con = (Connection) DriverManager.getConnection(loginInfo.getUrl(), loginInfo.getUser(), loginInfo.getPwd());
	}
	
	private void Update() throws ClassNotFoundException, SQLException 
	{
		ArrayList<StockRechClasse> list = stockList();
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);
		Object[] column = new Object[7];
		
		for(int i = 0; i < list.size(); i++) 
		{
			column[0] = list.get(i).getIdItem();
			column[1] = list.get(i).getType();
			column[2] = list.get(i).getMarque();
			column[3] = list.get(i).getModele();
			column[4] = list.get(i).getQte();
			column[5] = list.get(i).getDateAcquisition();
			column[6] = list.get(i).getNdeSerie();
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
	
	private ArrayList<StockRechClasse> stockList(){
		ArrayList<StockRechClasse> stockRech = new ArrayList<>();
		sql = "SELECT idItem, Type, Marque, Modele, Qte, DATE_FORMAT(DateAcquisition, '%d/%m/%Y') as DateAcquisition, NdeSerie FROM gmao.stockderechange;";
		try 
		{
			Connect();
			ps = (PreparedStatement) con.prepareStatement(sql);
			rs = ps.executeQuery();
			StockRechClasse stockR;
			while(rs.next()) 
			{
				stockR = new StockRechClasse(rs.getInt("idItem"),       rs.getString("Type"), rs.getString("Marque"), 
						                     rs.getString("Modele"),    rs.getInt("Qte"),     rs.getString("DateAcquisition"), 
						                     rs.getString("NdeSerie"));
				stockRech.add(stockR);
			}
			
		} 
		catch(Exception e1) 
		{
			JOptionPane.showMessageDialog(null, e1);
		}
		return stockRech;
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