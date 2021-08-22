package OptionFramePlannification;

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
import javax.swing.border.EmptyBorder;
import com.mxrck.autocompleter.TextAutoCompleter;
import EditMultipleObjects.RoundJButton;
import EditMultipleObjects.RoundJTextField;
import Notifications.MessageManager;
import OptionXLogin.loginInfo;
import doryan.windowsnotificationapi.fr.Notification;

public class AddOptionFrame extends JFrame 
{
	private static final long serialVersionUID = 7780788596021171973L;
	private JPanel contentPane;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql, sqlVerif;
	private RoundJTextField mrqField, modField, typeMntnField, 
							dateMaintField, nSerieField, refRapField;
	
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
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
		setTitle("Option d'addition");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 772, 417);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Option d'addition");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(293, 40, 159, 31);
		contentPane.add(lblNewLabel);
		
		mrqField = new RoundJTextField(20);
		autoCmp(mrqField, "Select mrq from Marque;", "mrq");
		mrqField.setHorizontalAlignment(SwingConstants.CENTER);
		mrqField.setBounds(158, 140, 134, 20);
		contentPane.add(mrqField);
		mrqField.setColumns(10);
		
		JLabel mrqLbl = new JLabel("Marque");
		mrqLbl.setHorizontalAlignment(SwingConstants.CENTER);
		mrqLbl.setBounds(50, 140, 98, 20);
		contentPane.add(mrqLbl);
		
		JLabel modLbl = new JLabel("Mod\u00E8le");
		modLbl.setHorizontalAlignment(SwingConstants.CENTER);
		modLbl.setBounds(50, 184, 98, 20);
		contentPane.add(modLbl);
		
		modField = new RoundJTextField(20);
		modField.setHorizontalAlignment(SwingConstants.CENTER);
		modField.setColumns(10);
		modField.setBounds(158, 184, 134, 20);
		contentPane.add(modField);
		
		JLabel typeMntnLbl = new JLabel("Type de maintenance");
		typeMntnLbl.setHorizontalAlignment(SwingConstants.CENTER);
		typeMntnLbl.setBounds(24, 227, 124, 20);
		contentPane.add(typeMntnLbl);
		
		typeMntnField = new RoundJTextField(20);
		typeMntnField.setHorizontalAlignment(SwingConstants.CENTER);
		typeMntnField.setColumns(10);
		typeMntnField.setBounds(158, 227, 134, 20);
		contentPane.add(typeMntnField);
		
		JLabel dateMaintLbl = new JLabel("Date de maintenance");
		dateMaintLbl.setHorizontalAlignment(SwingConstants.CENTER);
		dateMaintLbl.setBounds(404, 140, 134, 20);
		contentPane.add(dateMaintLbl);
		
		dateMaintField = new RoundJTextField(20);
		dateMaintField.setHorizontalAlignment(SwingConstants.CENTER);
		dateMaintField.setColumns(10);
		dateMaintField.setBounds(554, 140, 134, 20);
		contentPane.add(dateMaintField);
		
		JLabel nSerieLbl = new JLabel("Num\u00E9ro de Serie");
		nSerieLbl.setHorizontalAlignment(SwingConstants.CENTER);
		nSerieLbl.setBounds(425, 184, 98, 20);
		contentPane.add(nSerieLbl);
		
		nSerieField = new RoundJTextField(20);
		nSerieField.setHorizontalAlignment(SwingConstants.CENTER);
		nSerieField.setColumns(10);
		nSerieField.setBounds(554, 184, 134, 20);
		contentPane.add(nSerieField);
		
		JLabel refRapportLbl = new JLabel("R\u00E9ference Rapport");
		refRapportLbl.setHorizontalAlignment(SwingConstants.CENTER);
		refRapportLbl.setBounds(425, 227, 98, 20);
		contentPane.add(refRapportLbl);
		
		refRapField = new RoundJTextField(20);
		refRapField.setHorizontalAlignment(SwingConstants.CENTER);
		refRapField.setColumns(10);
		refRapField.setBounds(554, 227, 134, 20);
		contentPane.add(refRapField);
		
		RoundJButton addBtn = new RoundJButton("Ajouter");
		addBtn.setBounds(322, 321, 89, 23);
		addBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(verifyField() == true) 
				{
					try
					{
						if(verifExistance(refRapField.getText().trim()) == false) 
						{
							Add(mrqField, modField, typeMntnField, dateMaintField, nSerieField, refRapField);
							Notification.sendNotification(MessageManager.titSoft, MessageManager.additionItem, MessageType.INFO);
						} 
						else if(verifExistance(refRapField.getText().trim()) == true)
						{
							JOptionPane.showMessageDialog(null, MessageManager.itemSameNRefRap);
						}
					} 
					catch(ClassNotFoundException | SQLException | MalformedURLException | AWTException e1) 
					{
						e1.printStackTrace();
					}
				} 
				else if(verifyField() == false)
				{
					JOptionPane.showMessageDialog(null, MessageManager.errorMsg);
				}
			}
		});
		contentPane.add(addBtn);
	}
	
	private void Connect() throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.mysql.jdbc.Driver");
		con = (Connection) DriverManager.getConnection(loginInfo.getUrl(), loginInfo.getUser(), loginInfo.getPwd());
	}
	
	private void Add( JTextField jt1, 
					  JTextField jt2, 
					  JTextField jt3, 
		 			  JTextField jt4, 
		 			  JTextField jt5, 
		 			  JTextField jt6) throws ClassNotFoundException, SQLException 
	{
	Connect();
	sql = "INSERT INTO gmao.plannification (Marque, Modele, TypeMaintenance, DateMaint, nSerie, RefRapport) VALUES (?, ?, ?, ?, ?, ?);";
	ps = (PreparedStatement) con.prepareStatement(sql);
	ps.setString(1, jt1.getText().trim());
	ps.setString(2, jt2.getText().trim());
	ps.setString(3, jt3.getText().trim());
	ps.setString(4, jt4.getText().trim());
	ps.setString(5, jt5.getText().trim());
	ps.setString(6, jt6.getText().trim());
	ps.executeUpdate();
	ps.close();
	con.close();
	}
	
	private boolean verifyField() 
	{
		boolean verifField = false;
		if(mrqField.getText().trim().isEmpty()       == false && 
		   modField.getText().trim().isEmpty()       == false &&
		   typeMntnField.getText().trim().isEmpty()  == false && 
		   dateMaintField.getText().trim().isEmpty() == false &&
		   nSerieField.getText().trim().isEmpty()    == false && 
		   refRapField.getText().trim().isEmpty()    == false ) 
		{
			return verifField = true;
		}
		return verifField;
	}
	
	private boolean verifExistance(String str) throws ClassNotFoundException, SQLException 
	{
		boolean verifExist = false;
		Connect();
		sqlVerif = "select * from gmao.plannification where RefRapport = '" + str + "';";
		ps = (PreparedStatement) con.prepareStatement(sqlVerif);
		rs = ps.executeQuery();
		while(rs.next()) 
		{
			if(str.trim().equals(rs.getString("RefRapport")) == true) 
			{
				return verifExist = true;
			}
		}
		return verifExist;
	}
	
	private void autoCmp(JTextField jt, String str1, String str2) 
	{
		try 
		{
			Connect();
			TextAutoCompleter auto = new TextAutoCompleter(jt);
			String sqlAuto = str1;
			ps = (PreparedStatement) con.prepareStatement(sqlAuto);
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