package OptionFrameMaintenance;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import com.mxrck.autocompleter.TextAutoCompleter;
import EditMultipleObjects.RoundJButton;
import EditMultipleObjects.RoundJTextField;
import Notifications.MessageManager;
import OptionXLogin.loginInfo;
import doryan.windowsnotificationapi.fr.Notification;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AddOptionFrame extends JFrame 
{
	private static final long serialVersionUID = -3943260467980122653L;
	private JPanel contentPane;
	private String sql, sqlVerif;
	private Connection con;
	private PreparedStatement ps;
	private RoundJButton addBtn;
	private ResultSet rs;
	private RoundJTextField servField, desField, eqpField, etatField, mrqField, 
	                   modField,nSerField, datPField, datIntField, decIntField, 
	                   durIntField, montField;

	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			@Override
			public void run() 
			{
				try 
				{
					AddOptionFrame frame = new AddOptionFrame();
					frame.setVisible(true);
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	public AddOptionFrame() 
	{
		setType(Type.POPUP);
		setTitle("Adding Options");
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 648, 477);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Option d'insertion");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(223, 34, 194, 27);
		contentPane.add(lblNewLabel);
		
		servField = new RoundJTextField(20);
		autoCmp(servField, "Select NomServ from gmao.serviceconcfilter", "NomServ");
		servField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER) 
				{
					addBtn.doClick();
				}
			}
		});
		servField.setHorizontalAlignment(SwingConstants.CENTER);
		servField.setBounds(146, 135, 139, 20);
		contentPane.add(servField);
		servField.setColumns(10);
		
		JLabel servLbl = new JLabel("Service");
		servLbl.setHorizontalAlignment(SwingConstants.CENTER);
		servLbl.setBounds(39, 135, 97, 20);
		contentPane.add(servLbl);
		
		desField = new RoundJTextField(20);
		autoCmp(desField, "Select des from gmao.designation;", "des");
		desField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER) 
				{
					addBtn.doClick();
				}
			}
		});
		desField.setColumns(10);
		desField.setBounds(146, 166, 139, 20);
		contentPane.add(desField);
		
		JLabel desLbl = new JLabel("D\u00E9signation");
		desLbl.setHorizontalAlignment(SwingConstants.CENTER);
		desLbl.setBounds(39, 166, 97, 20);
		contentPane.add(desLbl);
		
		eqpField = new RoundJTextField(20);
		eqpField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER) 
				{
					addBtn.doClick();
				}
			}
		});
		eqpField.setColumns(10);
		eqpField.setBounds(146, 197, 139, 20);
		contentPane.add(eqpField);
		
		JLabel eqpLbl = new JLabel("Equipement");
		eqpLbl.setHorizontalAlignment(SwingConstants.CENTER);
		eqpLbl.setBounds(39, 197, 97, 20);
		contentPane.add(eqpLbl);
		
		etatField = new RoundJTextField(20);
		etatField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER) 
				{
					addBtn.doClick();
				}
			}
		});
		etatField.setColumns(10);
		etatField.setBounds(146, 228, 139, 20);
		contentPane.add(etatField);
		
		JLabel etatLbl = new JLabel("Etat");
		etatLbl.setHorizontalAlignment(SwingConstants.CENTER);
		etatLbl.setBounds(39, 228, 97, 20);
		contentPane.add(etatLbl);
		
		mrqField = new RoundJTextField(20);
		autoCmp(mrqField, "Select mrq from gmao.marque;", "mrq");
		mrqField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER) 
				{
					addBtn.doClick();
				}
			}
		});
		mrqField.setColumns(10);
		mrqField.setBounds(146, 259, 139, 20);
		contentPane.add(mrqField);
		
		JLabel mrqLbl = new JLabel("Marque");
		mrqLbl.setHorizontalAlignment(SwingConstants.CENTER);
		mrqLbl.setBounds(39, 259, 97, 20);
		contentPane.add(mrqLbl);
		
		modField = new RoundJTextField(20);
		modField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER) 
				{
					addBtn.doClick();
				}
			}
		});
		modField.setColumns(10);
		modField.setBounds(146, 290, 139, 20);
		contentPane.add(modField);
		
		JLabel modLbl = new JLabel("Mod\u00E8le");
		modLbl.setHorizontalAlignment(SwingConstants.CENTER);
		modLbl.setBounds(39, 290, 97, 20);
		contentPane.add(modLbl);
		
		nSerField = new RoundJTextField(20);
		nSerField.setColumns(10);
		nSerField.setBounds(473, 135, 139, 20);
		contentPane.add(nSerField);
		
		JLabel nSerLbl = new JLabel("Num\u00E9ro de Serie");
		nSerLbl.setHorizontalAlignment(SwingConstants.CENTER);
		nSerLbl.setBounds(342, 135, 121, 20);
		contentPane.add(nSerLbl);
		
		datPField = new RoundJTextField(20);
		datPField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER) 
				{
					addBtn.doClick();
				}
			}
		});
		datPField.setColumns(10);
		datPField.setBounds(473, 166, 139, 20);
		contentPane.add(datPField);
		
		JLabel datPLbl = new JLabel("Date Pr\u00E9vue");
		datPLbl.setHorizontalAlignment(SwingConstants.CENTER);
		datPLbl.setBounds(342, 166, 105, 20);
		contentPane.add(datPLbl);
		
		datIntField = new RoundJTextField(20);
		datIntField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER) 
				{
					addBtn.doClick();
				}
			}
		});
		datIntField.setColumns(10);
		datIntField.setBounds(473, 197, 139, 20);
		contentPane.add(datIntField);
		
		JLabel lblNewLabel_1_8 = new JLabel("Date d'intervention");
		lblNewLabel_1_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_8.setBounds(342, 197, 121, 20);
		contentPane.add(lblNewLabel_1_8);
		
		decIntField = new RoundJTextField(20);
		decIntField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER) 
				{
					addBtn.doClick();
				}
			}
		});
		decIntField.setColumns(10);
		decIntField.setBounds(473, 228, 139, 20);
		contentPane.add(decIntField);
		
		JLabel decIntLbl = new JLabel("Decalage d'intervention");
		decIntLbl.setHorizontalAlignment(SwingConstants.CENTER);
		decIntLbl.setBounds(320, 228, 143, 20);
		contentPane.add(decIntLbl);
		
		durIntField = new RoundJTextField(20);
		durIntField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER) 
				{
					addBtn.doClick();
				}
			}
		});
		durIntField.setColumns(10);
		durIntField.setBounds(473, 259, 139, 20);
		contentPane.add(durIntField);
		
		JLabel durIntLbl = new JLabel("Duree d'intervention");
		durIntLbl.setHorizontalAlignment(SwingConstants.CENTER);
		durIntLbl.setBounds(330, 259, 133, 20);
		contentPane.add(durIntLbl);
		
		montField = new RoundJTextField(20);
		montField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER) 
				{
					addBtn.doClick();
				}
			}
		});
		montField.setColumns(10);
		montField.setBounds(473, 290, 139, 20);
		contentPane.add(montField);
		
		JLabel montLbl = new JLabel("Montant d'intervention");
		montLbl.setHorizontalAlignment(SwingConstants.CENTER);
		montLbl.setBounds(320, 290, 139, 20);
		contentPane.add(montLbl);
		
		addBtn  = new RoundJButton("Ajouter");
		addBtn.setBounds(268, 356, 89, 23);
		addBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(verifField() == true){
					try 
					{
						if(verifExist(nSerField.getText().trim()) == false) 
						{
							Ajouter(servField, desField, eqpField, etatField, mrqField, modField, 
									nSerField, datPField, datIntField, decIntField, durIntField, montField);
							Notification.sendNotification(MessageManager.titSoft, 
														  MessageManager.additionItem, 
														  MessageType.INFO
														  );	
						} 
						else if(verifExist(nSerField.getText().trim()) == true) 
						{
							JOptionPane.showMessageDialog(null, MessageManager.itemSameNSerie);
						}
					} 
					catch(Exception e1) 
					{
						e1.printStackTrace();
					}
				} 
				else if(verifField() == false)
				{
					JOptionPane.showMessageDialog(null, MessageManager.errorMsg);
				}
			}
		});
		contentPane.add(addBtn);
	}
	
	protected void Connect() throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(loginInfo.getUrl(), loginInfo.getUser(), loginInfo.getPwd());
	}
	
	protected void Ajouter(JTextField jt01, 
						   JTextField jt02,
						   JTextField jt03,
						   JTextField jt04,
						   JTextField jt05,
						   JTextField jt06, 
						   JTextField jt07,
						   JTextField jt08,
						   JTextField jt09,
						   JTextField jt10,
						   JTextField jt11, 
						   JTextField jt12) throws ClassNotFoundException, SQLException 
	{
		
		Connect();
		sql = "INSERT INTO gmao.maintenance (Service, Designation, Equipement, Etat, Marque, Modele, NdeSerie, "
				                          + "DatePrevu, Date_Intervention, Decalage_Intervention, Duree_Intervention, "
			                           	  + "Montant_Intervention) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";

		ps= con.prepareStatement(sql);
		ps.setString(1, jt01.getText().trim());
		ps.setString(2, jt02.getText().trim());
		ps.setString(3, jt03.getText().trim());
		ps.setString(4, jt04.getText().trim());
		ps.setString(5, jt05.getText().trim());
		ps.setString(6, jt06.getText().trim());
		ps.setString(7, jt07.getText().trim());
		ps.setString(8, jt08.getText().trim());
		ps.setString(9, jt09.getText().trim());
		ps.setString(10,jt10.getText().trim());
		ps.setString(11,jt11.getText().trim());
		ps.setString(12,jt12.getText().trim());
		ps.executeUpdate();
		ps.close();
		con.close();
	}
	
	protected boolean verifField() 
	{
		boolean verifField = false;
		if(servField.getText().trim().isEmpty()   == false &&
		   desField.getText().trim().isEmpty()    == false && 
		   eqpField.getText().trim().isEmpty()    == false && 
		   etatField.getText().trim().isEmpty()   == false && 
		   mrqField.getText().trim().isEmpty()    == false &&
		   modField.getText().trim().isEmpty()    == false &&
		   nSerField.getText().trim().isEmpty()   == false &&
		   datPField.getText().trim().isEmpty()   == false && 
		   datIntField.getText().trim().isEmpty() == false &&
		   decIntField.getText().trim().isEmpty() == false && 
		   durIntField.getText().trim().isEmpty() == false && 
		   montField.getText().trim().isEmpty()   == false) 
		{
			return verifField = true;
		}
		return verifField;
	}
	
	protected boolean verifExist(String str) throws ClassNotFoundException, SQLException 
	{
		boolean verifExist = false;
		sqlVerif = "SELECT * FROM gmao.maintenance WHERE NdeSerie = '" + str + "';";
		Connect();
		ps = con.prepareStatement(sqlVerif);
		rs = ps.executeQuery();
		while(rs.next()) {
			if(str.trim().equals(rs.getString("NdeSerie")) == true) 
			{
				return verifExist = true;
			}
		}
		return verifExist;
	}
	
	protected void autoCmp(JTextField jt, String str1, String str2) 
	{
		try 
		{
			Connect();
			TextAutoCompleter auto = new TextAutoCompleter(jt);
			String sqlAuto = str1;
			ps = con.prepareStatement(sqlAuto);
			ResultSet rs;
			rs = ps.executeQuery();
			while(rs.next()) {
				auto.addItem(rs.getString(str2));
			}
			con.close();
			ps.close();
			rs.close();
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
		}
	}
}