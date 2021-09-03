package OptionFrameInventaire;

import java.awt.AWTException;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
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

public class AddingOptions extends JFrame 
{
	private static final long serialVersionUID = 7651087971683946838L;
	private JPanel contentPane;
	private RoundJTextField servField, typeEqField, desField, mrqField, qteField, modField, nSerField, nCtrField;
	private String sql, sqlVerif;
	private RoundJButton addBtnOpt;
	private boolean verifExist, verifField;
	private Connection con;
	private PreparedStatement ps;
	public 	ResultSet rs;

	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			@Override
			public void run() 
			{
				try 
				{
					AddingOptions frame = new AddingOptions();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	public AddingOptions() 
	{
		setResizable(false);
		setType(Type.POPUP);
		setTitle("Adding Option");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 693, 509);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAjouterDesDonnes = new JLabel("Ajouter des donn\u00E9es");
		lblAjouterDesDonnes.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblAjouterDesDonnes.setHorizontalAlignment(SwingConstants.CENTER);
		lblAjouterDesDonnes.setBounds(166, 11, 324, 71);
		contentPane.add(lblAjouterDesDonnes);
		
		JLabel servLbl = new JLabel("Service");
		servLbl.setHorizontalAlignment(SwingConstants.CENTER);
		servLbl.setBounds(47, 151, 72, 27);
		contentPane.add(servLbl);
		
		servField = new RoundJTextField(20);
		servField.setHorizontalAlignment(SwingConstants.CENTER);
		servField.setBounds(141, 151, 163, 30);
		autoCmp(servField, "Select NomServ from gmao.serviceconcfilter;", "NomServ");
		contentPane.add(servField);
		servField.setColumns(10);
		
		JLabel typeEqLbl = new JLabel("Type d'\u00E9quipement");
		typeEqLbl.setHorizontalAlignment(SwingConstants.CENTER);
		typeEqLbl.setBounds(10, 212, 109, 27);
		contentPane.add(typeEqLbl);
		
		typeEqField = new RoundJTextField(20);
		typeEqField.setHorizontalAlignment(SwingConstants.CENTER);
		typeEqField.setColumns(10);
		typeEqField.setBounds(141, 212, 163, 30);
		contentPane.add(typeEqField);
		
		JLabel desLbl = new JLabel("D\u00E9signation");
		desLbl.setHorizontalAlignment(SwingConstants.CENTER);
		desLbl.setBounds(47, 270, 72, 27);
		contentPane.add(desLbl);
		
		desField = new RoundJTextField(20);
		autoCmp(desField, "Select des from designation;", "des");
		desField.setHorizontalAlignment(SwingConstants.CENTER);
		desField.setColumns(10);
		desField.setBounds(141, 270, 163, 30);
		contentPane.add(desField);
		
		JLabel mrqLbl = new JLabel("Marque");
		mrqLbl.setHorizontalAlignment(SwingConstants.CENTER);
		mrqLbl.setBounds(47, 330, 72, 27);
		contentPane.add(mrqLbl);
		
		mrqField = new RoundJTextField(20);
		autoCmp(mrqField, "Select mrq from gmao.marque;", "mrq");
		mrqField.setHorizontalAlignment(SwingConstants.CENTER);
		mrqField.setColumns(10);
		mrqField.setBounds(141, 330, 163, 30);
		contentPane.add(mrqField);
		
		JLabel qteLbl = new JLabel("Quantit\u00E9");
		qteLbl.setHorizontalAlignment(SwingConstants.CENTER);
		qteLbl.setBounds(385, 151, 72, 27);
		contentPane.add(qteLbl);
		
		qteField = new RoundJTextField(20);
		qteField.setHorizontalAlignment(SwingConstants.CENTER);
		qteField.setColumns(10);
		qteField.setBounds(467, 149, 163, 30);
		contentPane.add(qteField);
		
		JLabel modLbl = new JLabel("Mod\u00E8le");
		modLbl.setHorizontalAlignment(SwingConstants.CENTER);
		modLbl.setBounds(385, 212, 72, 27);
		contentPane.add(modLbl);
		
		modField = new RoundJTextField(20);
		modField.setHorizontalAlignment(SwingConstants.CENTER);
		modField.setColumns(10);
		modField.setBounds(467, 210, 163, 30);
		contentPane.add(modField);
		
		JLabel nSerLbl = new JLabel("Num\u00E9ro de Serie");
		nSerLbl.setHorizontalAlignment(SwingConstants.CENTER);
		nSerLbl.setBounds(352, 270, 105, 27);
		contentPane.add(nSerLbl);
		
		nSerField =  new RoundJTextField(20);
		nSerField.setHorizontalAlignment(SwingConstants.CENTER);
		nSerField.setColumns(10);
		nSerField.setBounds(467, 268, 163, 30);
		contentPane.add(nSerField);
		
		JLabel nCtrLbl = new JLabel("Num\u00E9ro de Contrat");
		nCtrLbl.setHorizontalAlignment(SwingConstants.CENTER);
		nCtrLbl.setBounds(329, 330, 128, 27);
		contentPane.add(nCtrLbl);
		
		nCtrField = new RoundJTextField(20);
		nCtrField.setHorizontalAlignment(SwingConstants.CENTER);
		nCtrField.setColumns(10);
		nCtrField.setBounds(467, 328, 163, 30);
		contentPane.add(nCtrField);
		
		addBtnOpt = new RoundJButton("Ajouter");
		addBtnOpt.setBounds(287, 405, 95, 41);
		addBtnOpt.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
			    if(verifField() == true) 
			    {
					try 
					{
				    	if(verifExist(nSerField.getText().trim()) == false) 
				    	{
							AddNewEqu(servField, 
									  desField, 
									  typeEqField, 
									  mrqField, 
									  modField, 
									  qteField, 
									  nSerField, 
									  nCtrField
									  );
							
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
					catch (ClassNotFoundException | SQLException | MalformedURLException | AWTException e1) 
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
		contentPane.add(addBtnOpt);
	}

	private void AddNewEqu(JTextField jt1, JTextField jt2, JTextField jt3, JTextField jt4, JTextField jt5, 
			                 JTextField jt6, JTextField jt7, JTextField jt8) throws ClassNotFoundException, SQLException 
	{
		Connect();
		sql = "INSERT INTO gmao.inventaire (Service, Designation, TypeEquipement, Marque, Modele, Quantite, NSerie, NContrat) VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
		ps = con.prepareStatement(sql);
		ps.setString(1, jt1.getText().trim());
		ps.setString(2, jt2.getText().trim());
		ps.setString(3, jt3.getText().trim());
		ps.setString(4, jt4.getText().trim());
		ps.setString(5, jt5.getText().trim());
		ps.setString(6, jt6.getText().trim());
		ps.setString(7, jt7.getText().trim());
		ps.setString(8, jt8.getText().trim());
		ps.executeUpdate();
	}
	
	private void Connect() throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(loginInfo.getUrl(), 
													   loginInfo.getUser(), 
													   loginInfo.getPwd()
													   );
	}
	
	private boolean verifField()
	{
		verifField = false;
		
		if(servField.getText().isEmpty()   == false && 
		   desField.getText().isEmpty()    == false &&
     	   typeEqField.getText().isEmpty() == false && 
		   desField.getText().isEmpty()    == false && 
		   mrqField.getText().isEmpty()    == false &&
		   modField.getText().isEmpty()    == false && 
		   qteField.getText().isEmpty()    == false && 
		   nSerField.getText().isEmpty()   == false && 
		   nCtrField.getText().isEmpty()   == false) 
		{
			return verifField = true;
		}
		return verifField;
	}
	
	private boolean verifExist(String str) throws ClassNotFoundException, SQLException 
	{
		verifExist = false;
		Connect();
		sqlVerif = "SELECT * FROM gmao.inventaire WHERE NSerie = '" + str + "';";
		ps = con.prepareStatement(sqlVerif);
		rs = ps.executeQuery();
		while(rs.next()) 
		{
			if(str.trim().equals(rs.getString("NSerie")) == true) 
			{
				return verifExist = true;
			}
		}
		con.close();
		rs.close();
		ps.close();
		return verifExist;
	}
	
	private void autoCmp(JTextField jt, String str1, String str2) 
	{
		try
		{
			Connect();
			TextAutoCompleter auto = new TextAutoCompleter(jt);
			String sqlAuto = str1;
			ps = con.prepareStatement(sqlAuto);
			ResultSet rs;
			rs = ps.executeQuery();
			while(rs.next()) 
			{
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