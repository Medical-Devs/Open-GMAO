package NormOptionFrameRapport_Intevention;

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

public class AddingOptions extends JFrame {

	private static final long serialVersionUID = -7248049194529303615L;
	private JPanel contentPane;
	private RoundJTextField servField, desField, typeApField, mrqField, modField, 
							nInvField, nSerieField, dateIntField, refRapField;
	private String sql, sqlVerif;
	private RoundJButton addBtn;
	private boolean verifyBool, verifExist;
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
		setTitle("Adding Options");
		setResizable(false);
		setType(Type.POPUP);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 773, 475);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titLbl = new JLabel("Adding Options");
		titLbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		titLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titLbl.setBounds(312, 25, 143, 31);
		contentPane.add(titLbl);
		
		JLabel servLbl = new JLabel("Service");
		servLbl.setHorizontalAlignment(SwingConstants.CENTER);
		servLbl.setBounds(10, 100, 119, 31);
		contentPane.add(servLbl);
		
		servField = new RoundJTextField(20);
		servField.setBounds(126, 105, 183, 20);
		autoCmp(servField, "Select NomServ from gmao.serviceconcfilter;", "NomServ");
		contentPane.add(servField);
		servField.setColumns(10);
		
		JLabel desLbl = new JLabel("D\u00E9signation");
		desLbl.setHorizontalAlignment(SwingConstants.CENTER);
		desLbl.setBounds(10, 142, 119, 31);
		contentPane.add(desLbl);
		
		desField = new RoundJTextField(20);
		desField.setColumns(10);
		autoCmp(desField, "Select Des from gmao.designation;", "Des");
		desField.setBounds(126, 147, 183, 20);
		contentPane.add(desField);
		
		JLabel typeApLbl = new JLabel("Type d'appareil");
		typeApLbl.setHorizontalAlignment(SwingConstants.CENTER);
		typeApLbl.setBounds(10, 184, 119, 31);
		contentPane.add(typeApLbl);
		
		typeApField = new RoundJTextField(20);
		typeApField.setColumns(10);
		typeApField.setBounds(126, 189, 183, 20);
		contentPane.add(typeApField);
		
		JLabel mrqLbl = new JLabel("Marque");
		mrqLbl.setHorizontalAlignment(SwingConstants.CENTER);
		mrqLbl.setBounds(10, 226, 119, 31);
		contentPane.add(mrqLbl);
		
		mrqField = new RoundJTextField(20);
		mrqField.setColumns(10);
		mrqField.setBounds(126, 231, 183, 20);
		autoCmp(mrqField, "Select mrq from gmao.marque;", "mrq");
		contentPane.add(mrqField);
		
		JLabel modLbl = new JLabel("Mod\u00E8le");
		modLbl.setHorizontalAlignment(SwingConstants.CENTER);
		modLbl.setBounds(10, 268, 119, 31);
		contentPane.add(modLbl);
		
		modField = new RoundJTextField(20);
		modField.setColumns(10);
		modField.setBounds(126, 273, 183, 20);
		contentPane.add(modField);
		
		JLabel nInvLbl = new JLabel("Num\u00E9ro d'inventaire");
		nInvLbl.setHorizontalAlignment(SwingConstants.CENTER);
		nInvLbl.setBounds(-1, 310, 130, 31);
		contentPane.add(nInvLbl);
		
		nInvField = new RoundJTextField(20);
		nInvField.setColumns(10);
		nInvField.setBounds(126, 315, 183, 20);
		contentPane.add(nInvField);
		
		JLabel nSerieLbl = new JLabel("Num\u00E9ro de Serie");
		nSerieLbl.setHorizontalAlignment(SwingConstants.CENTER);
		nSerieLbl.setBounds(-1, 352, 119, 31);
		contentPane.add(nSerieLbl);
		
		nSerieField = new RoundJTextField(20);
		nSerieField.setColumns(10);
		nSerieField.setBounds(126, 357, 183, 20);
		contentPane.add(nSerieField);
		
		JLabel dateIntLbl = new JLabel("Date d'intervention");
		dateIntLbl.setHorizontalAlignment(SwingConstants.CENTER);
		dateIntLbl.setBounds(-1, 394, 130, 31);
		contentPane.add(dateIntLbl);
		
		dateIntField = new RoundJTextField(20);
		dateIntField.setColumns(10);
		dateIntField.setBounds(126, 399, 183, 20);
		contentPane.add(dateIntField);
		
		JLabel refRapLbl = new JLabel("R\u00E9f\u00E9rence du Rapport");
		refRapLbl.setHorizontalAlignment(SwingConstants.CENTER);
		refRapLbl.setBounds(369, 226, 130, 31);
		contentPane.add(refRapLbl);
		
		refRapField = new RoundJTextField(20);
		refRapField.setColumns(10);
		refRapField.setBounds(509, 231, 183, 20);
		contentPane.add(refRapField);
		
		addBtn = new RoundJButton("Ajouter");
		addBtn.setBounds(474, 298, 111, 37);
		addBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{	
				if(verifyField() == true)
				{
						try 
						{
							if(verifyExist(refRapField.getText().trim()) == false) 
							{
								Add(servField, desField, typeApField, mrqField, modField, nInvField, nSerieField, dateIntField, refRapField);	
								Notification.sendNotification(MessageManager.titSoft, MessageManager.additionItem, MessageType.INFO);	
							} 
							else if(verifyExist(refRapField.getText().trim()) == true) 
							{
								JOptionPane.showMessageDialog(null, MessageManager.itemSameNRefRap);
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
	
	protected void Connect() throws ClassNotFoundException, SQLException 
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
					 JTextField jt9) throws ClassNotFoundException, SQLException 
	{
		Connect();
		sql = "INSERT INTO gmao.rapport_intervention (Service, Designation, TypeAppareil, "
				+ "Marque, Modele, NInventaire, NSerie, Date_Intervention, Reference_Rapport) VALUES (?,?,?,?,?,?,?,?,?)";
		ps = con.prepareStatement(sql);
		ps.setString(1, jt1.getText().trim());
		ps.setString(2, jt2.getText().trim());
		ps.setString(3, jt3.getText().trim());
		ps.setString(4, jt4.getText().trim());
		ps.setString(5, jt5.getText().trim());
		ps.setString(6, jt6.getText().trim());
		ps.setString(7, jt7.getText().trim());
		ps.setString(8, jt8.getText().trim());
		ps.setString(9, jt9.getText().trim());
		ps.executeUpdate();
		}
	
	private boolean verifyField() 
	{
		verifyBool = false;
		if(servField.getText().trim().isEmpty()    == false && 
		   desField.getText().trim().isEmpty()     == false && 
		   typeApField.getText().trim().isEmpty()  == false && 
		   mrqField.getText().trim().isEmpty()     == false && 
		   modField.getText().trim().isEmpty()     == false && 
		   nInvField.getText().trim().isEmpty()    == false && 
		   nSerieField.getText().trim().isEmpty()  == false && 
		   dateIntField.getText().trim().isEmpty() == false &&
		   refRapField.getText().trim().isEmpty()  == false) 
		{
			return verifyBool = true;
		}
		return verifyBool;
	}
	
	private boolean verifyExist(String str) throws ClassNotFoundException, SQLException 
	{
		verifExist = false;
		Connect();
		sqlVerif = "SELECT * FROM gmao.rapport_intervention WHERE Reference_Rapport = '" + str + "';";
		ps = con.prepareStatement(sqlVerif);
		rs = ps.executeQuery();
		while(rs.next()) {
			if(str.trim().equals(rs.getString("Reference_Rapport")) == true) 
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