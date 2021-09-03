package OptionFramePlannification;

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
import Filters.Désignation;
import Filters.EntrepriseList;
import Filters.Help;
import Filters.Marque;
import Filters.Service_Conc;
import OptionXLogin.HomeMenu;
import OptionXLogin.loginInfo;

public class Plannification extends JFrame implements ActionListener
{
	private static final long serialVersionUID = -467823369939518497L;
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private String sql;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private JMenuItem openFile, aideItem, addSrvItem, addFournItem, addMrqItem, addDesItem;
	private BackgroundMenuBar menuBar;
	private JMenu fileMenu, outilsMenu;
	private DefaultTableCellRenderer centerRenderer;
	private JTextField searchField;
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
					Plannification frame = new Plannification();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	public Plannification() {
		Image img = new ImageIcon(this.getClass().getResource("/Icons/frame.png")).getImage();
		setIconImage(img);
		setResizable(false);
		setTitle("Plannification");
		setExtendedState(MAXIMIZED_BOTH);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setBackground(new Color(85,122,177));
		contentPane.setLayout(null);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 97, 1340, 548);
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
					"idItem", "Marque", "Modèle", "Type de Maintenance", "Date de Maintenance", "Numéro de Serie", "Reférence du Rapport"
				}
		));
		scrollPane.setViewportView(table);
		
		searchField = new JTextField();
		searchField.setText("Recherche");
		searchField.setHorizontalAlignment(SwingConstants.CENTER);
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
		
		JLabel addLbl = new JLabel("");
		addLbl.setToolTipText("Ajouter un item");
		addLbl.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				try 
				{
					Update();
				} 
				catch (Exception e4) 
				{
					e4.printStackTrace();
				}
			}
		});
		addLbl.setBounds(338, 656, 73, 73);
		addLbl.setIcon(new ImageIcon(addImg));
		contentPane.add(addLbl);
		
		JLabel delLbl = new JLabel("");
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
		
		JLabel modLbl = new JLabel("");
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
		
		JLabel updLbl = new JLabel("");
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
				catch (ClassNotFoundException | SQLException e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		updLbl.setIcon(new ImageIcon(imgRef));
		updLbl.setBounds(973, 656, 73, 73);
		contentPane.add(updLbl);
		
		retLbl = new JLabel("");
		retLbl.setToolTipText("Retour vers la page principale");
		retLbl.addMouseListener(new MouseAdapter() {
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
		
		try 
		{
			Update();
		} 
		catch (ClassNotFoundException | SQLException e1) 
		{
			e1.printStackTrace();
		}

	}
	
	private ArrayList<PlannificationClasse> planList()
	{
		ArrayList<PlannificationClasse> plan = new ArrayList<>();
		sql = "SELECT idPlan, Marque, Modele, TypeMaintenance, DATE_FORMAT(DateMaint, '%d/%m/%Y') as DateMaint, nSerie, RefRapport FROM gmao.plannification;";
		try 
		{
			Connect();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			PlannificationClasse planC;
			while(rs.next()) 
			{
				planC = new PlannificationClasse(rs.getInt("idPlan"),       rs.getString("Marque"), rs.getString("Modele"),     rs.getString("TypeMaintenance"), 
						                         rs.getString("DateMaint"), rs.getString("nSerie"), rs.getString("RefRapport"));
				plan.add(planC);
			}
		} 
		catch(Exception e1) 
		{
			JOptionPane.showMessageDialog(null, e1);
		}
		return plan;
	}
	
	private void Update()throws ClassNotFoundException, SQLException
	{
		ArrayList<PlannificationClasse> list = planList();
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);
		Object[] column = new Object[7];
		for(int i = 0; i < list.size() ; i++) 
		{
			column[0] = list.get(i).getIdPlan();
			column[1] = list.get(i).getMarque();
			column[2] = list.get(i).getModele();
			column[3] = list.get(i).getTypeMaintenance();
			column[4] = list.get(i).getDateMaint();
			column[5] = list.get(i).getnSerie();
			column[6] = list.get(i).getRefRapport();
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

	private void Connect() throws ClassNotFoundException, SQLException 
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