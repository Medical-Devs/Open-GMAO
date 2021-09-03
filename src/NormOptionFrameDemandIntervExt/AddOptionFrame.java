package NormOptionFrameDemandIntervExt;

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

public class AddOptionFrame extends JFrame 
{
	private static final long serialVersionUID = 5140780457525401774L;
	private JPanel contentPane;
	private RoundJTextField typeApField, servField, mrqField, modField, 
	                        fournField, nSerField, typeIntField, refDmdField, 
	                        datField, numEnrField;
	private RoundJButton addBtn;
	private String sql, sqlVerif;
	private boolean verifyBool, verifyExist;
	private Connection con;
	private ResultSet rs;
	private PreparedStatement ps;

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
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 715, 556);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titLbl = new JLabel("Adding Option");
		titLbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		titLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titLbl.setBounds(264, 28, 148, 42);
		contentPane.add(titLbl);
		
		JLabel typeAppLbl = new JLabel("Type d'appareil");
		typeAppLbl.setHorizontalAlignment(SwingConstants.CENTER);
		typeAppLbl.setBounds(10, 156, 119, 28);
		contentPane.add(typeAppLbl);
		
		typeApField = new RoundJTextField(20);
		typeApField.setBounds(126, 159, 161, 23);
		contentPane.add(typeApField);
		typeApField.setColumns(10);
		
		servField = new RoundJTextField(20);
		servField.setColumns(10);
		servField.setBounds(126, 198, 161, 23);
		autoCmp(servField, "Select Service from gmao.demandeintexterne;", "Service");
		contentPane.add(servField);
		
		JLabel servLbl = new JLabel("Service");
		servLbl.setHorizontalAlignment(SwingConstants.CENTER);
		servLbl.setBounds(10, 195, 119, 28);
		contentPane.add(servLbl);
		
		mrqField = new RoundJTextField(20);
		mrqField.setColumns(10);
		mrqField.setBounds(126, 244, 161, 23);
		autoCmp(mrqField, "Select Marque from gmao.demandeintexterne;", "Marque");
		contentPane.add(mrqField);
		
		JLabel mrqLbl = new JLabel("Marque");
		mrqLbl.setHorizontalAlignment(SwingConstants.CENTER);
		mrqLbl.setBounds(10, 241, 119, 28);
		contentPane.add(mrqLbl);
		
		modField = new RoundJTextField(20);
		modField.setColumns(10);
		modField.setBounds(126, 293, 161, 23);
		contentPane.add(modField);
		
		JLabel modLbl = new JLabel("Mod\u00E8le");
		modLbl.setHorizontalAlignment(SwingConstants.CENTER);
		modLbl.setBounds(10, 290, 119, 28);
		contentPane.add(modLbl);
		
		fournField = new RoundJTextField(20);
		fournField.setColumns(10);
		fournField.setBounds(126, 343, 161, 23);
		autoCmp(fournField, "Select Nom from gmao.entreprise;", "Nom");
		contentPane.add(fournField);
		
		JLabel fournLbl = new JLabel("Fournisseur");
		fournLbl.setHorizontalAlignment(SwingConstants.CENTER);
		fournLbl.setBounds(10, 340, 119, 28);
		contentPane.add(fournLbl);
		
		nSerField = new RoundJTextField(20);
		nSerField.setColumns(10);
		nSerField.setBounds(505, 198, 161, 23);
		contentPane.add(nSerField);
		
		JLabel nSerLbl = new JLabel("Num\u00E9ro de Serie");
		nSerLbl.setHorizontalAlignment(SwingConstants.CENTER);
		nSerLbl.setBounds(344, 195, 119, 28);
		contentPane.add(nSerLbl);
		
		typeIntField = new RoundJTextField(20);;
		typeIntField.setColumns(10);
		typeIntField.setBounds(505, 244, 161, 23);
		contentPane.add(typeIntField);
		
		JLabel typeIntLbl = new JLabel("Type d'intervention");
		typeIntLbl.setHorizontalAlignment(SwingConstants.CENTER);
		typeIntLbl.setBounds(344, 241, 119, 28);
		contentPane.add(typeIntLbl);
		
		refDmdField = new RoundJTextField(20);
		refDmdField.setColumns(10);
		refDmdField.setBounds(505, 293, 161, 23);
		contentPane.add(refDmdField);
		
		JLabel refDmdLbl = new JLabel("R\u00E9ference de la Demande");
		refDmdLbl.setHorizontalAlignment(SwingConstants.CENTER);
		refDmdLbl.setBounds(331, 290, 148, 28);
		contentPane.add(refDmdLbl);
		
		datField = new RoundJTextField(20);
		datField.setColumns(10);
		datField.setBounds(505, 343, 161, 23);
		contentPane.add(datField);
		
		JLabel dateLbl = new JLabel("Date");
		dateLbl.setHorizontalAlignment(SwingConstants.CENTER);
		dateLbl.setBounds(353, 341, 110, 26);
		contentPane.add(dateLbl);
		
		numEnrField = new RoundJTextField(20);
		numEnrField.setColumns(10);
		numEnrField.setBounds(505, 159, 161, 23);
		contentPane.add(numEnrField);
		
		JLabel nEnrgLbl = new JLabel("Num\u00E9ro d'enregistrement");
		nEnrgLbl.setHorizontalAlignment(SwingConstants.CENTER);
		nEnrgLbl.setBounds(331, 156, 148, 28);
		contentPane.add(nEnrgLbl);
	
		addBtn = new RoundJButton("Ajouter");
		addBtn.setBounds(285, 427, 110, 42);
		addBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(verifyField() == true) 
				{
					try 
					{
						if(verifyExist(refDmdField.getText().trim()) == false) 
						{			
							Add(typeApField, 
								servField, 
								mrqField, 
								modField, 
								fournField, 
								nSerField, 
								typeIntField, 
								refDmdField, 
								numEnrField,
								datField
								);
							Notification.sendNotification(MessageManager.titSoft, MessageManager.additionItem, MessageType.INFO);
							
						} 
						else if(verifyExist(refDmdField.getText().trim()) == true) 
						{
							JOptionPane.showMessageDialog(null, MessageManager.itemSameNRef);
						}
					} 
					catch(Exception e1) 
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
		con = DriverManager.getConnection(loginInfo.getUrl(), loginInfo.getUser(), loginInfo.getPwd());
	}
	
	private void Add(JTextField jt1, 
					 JTextField jt2, 
					 JTextField jt3, 
					 JTextField jt4, 
					 JTextField jt5, 
					 JTextField jt6, 
					 JTextField jt7, 
					 JTextField jt8, 
					 JTextField jt9, 
					 JTextField jt10) throws ClassNotFoundException, SQLException 
	{
		Connect();
		sql = "INSERT INTO gmao.demandeintexterne (TypeAppareil, Service, Marque, Modele, Fournisseur, "
				+ "NEnregistrement, NSerie, TypeIntervention, ReferenceDemande, Date) VALUES(?,?,?,?,?,?,?,?,?,?);";
		ps = con.prepareStatement(sql);
		ps.setString(1,  jt1.getText().trim());
		ps.setString(2,  jt2.getText().trim());
		ps.setString(3,  jt3.getText().trim());
		ps.setString(4,  jt4.getText().trim());
		ps.setString(5,  jt5.getText().trim());
		ps.setString(6,  jt6.getText().trim());
		ps.setString(7,  jt7.getText().trim());
		ps.setString(8,  jt8.getText().trim());
		ps.setString(9,  jt9.getText().trim());
		ps.setString(10, jt10.getText().trim());
		ps.executeUpdate();
		ps.close();
		con.close();
	}
	
	private boolean verifyField() {
		verifyBool = false;
		if(typeApField.getText().trim().isEmpty()  == false && 
		   servField.getText().trim().isEmpty()    == false && 
		   mrqField.getText().trim().isEmpty()     == false && 
		   modField.getText().trim().isEmpty()     == false &&
		   fournField.getText().trim().isEmpty()   == false &&
		   nSerField.getText().trim().isEmpty()    == false && 
		   typeIntField.getText().trim().isEmpty() == false && 
		   refDmdField.getText().trim().isEmpty()  == false && 
		   numEnrField.getText().trim().isEmpty()  == false && 
		   datField.getText().trim().isEmpty()     == false ) 
		{
			return verifyBool = true;
		}
		return verifyBool;
	}
	
	private boolean verifyExist(String str) throws ClassNotFoundException, SQLException {
		verifyExist = false;
		Connect();
		sqlVerif = "SELECT * FROM gmao.demandeintexterne WHERE ReferenceDemande = '" + str + "';";
		ps = con.prepareStatement(sqlVerif);
		rs = ps.executeQuery();
		while(rs.next()) 
		{
			if(str.trim().equals(rs.getString("ReferenceDemande")) == true) 
			{
				ps.close();
				rs.close();
				con.close();
				return verifyExist = true;
			}
		}
		ps.close();
		rs.close();
		con.close();
		return verifyExist;
	}
	
	private void autoCmp(JTextField jt, String str1, String str2) 
	{
		try 
		{
			Connect();
			TextAutoCompleter auto = new TextAutoCompleter(jt);
			String sqlAuto = str1;
			ps = con.prepareStatement(sqlAuto);
			rs = ps.executeQuery();
			while(rs.next()) {
				auto.addItem(rs.getString(str2));
			}
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
		}
	}
}