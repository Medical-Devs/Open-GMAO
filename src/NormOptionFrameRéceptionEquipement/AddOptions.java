package NormOptionFrameRéceptionEquipement;

import EditMultipleObjects.RoundJButton;
import EditMultipleObjects.RoundJTextField;
import Notifications.MessageManager;
import OptionXLogin.loginInfo;
import doryan.windowsnotificationapi.fr.Notification;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AddOptions extends JFrame 
{
	private static final long serialVersionUID = 384093978650307694L;
	private JPanel contentPane;
	private RoundJTextField servField, fournField, typeApField, fabField, nSerieField, desField, 
							motRecField, dateRecField, admField, aspColField, classMedField, nomRecField;
	private String sql, sqlExist;
	private RoundJButton addBtn;
	private boolean verifExist, verifField;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			@Override
			public void run() 
			{
				try 
				{
					AddOptions frame = new AddOptions();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	public AddOptions() 
	{
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 758, 536);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titLbl = new JLabel("Options d'addition");
		titLbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		titLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titLbl.setBounds(225, 43, 287, 36);
		contentPane.add(titLbl);
		
		JLabel servLbl = new JLabel("Service");
		servLbl.setHorizontalAlignment(SwingConstants.CENTER);
		servLbl.setBounds(21, 141, 107, 28);
		contentPane.add(servLbl);
		
		servField = new RoundJTextField(20);
		autoCmp(servField, "Select NomServ from gmao.serviceconcfilter;", "NomServ");
		servField.setBounds(127, 145, 156, 20);
		contentPane.add(servField);
		servField.setColumns(10);
		
		JLabel fournLbl = new JLabel("Fournisseur");
		fournLbl.setHorizontalAlignment(SwingConstants.CENTER);
		fournLbl.setBounds(21, 180, 107, 20);
		contentPane.add(fournLbl);
		
		fournField = new RoundJTextField(20);
		autoCmp(fournField, "Select Nom from gmao.entreprise;", "Nom");
		fournField.setBounds(127, 180, 156, 20);
		contentPane.add(fournField);
		fournField.setColumns(10);
		
		typeApField = new RoundJTextField(20);
		typeApField.setColumns(10);
		typeApField.setBounds(127, 250, 156, 20);
		contentPane.add(typeApField);
		
		JLabel typeApLbl = new JLabel("Type d'appareil");
		typeApLbl.setHorizontalAlignment(SwingConstants.CENTER);
		typeApLbl.setBounds(21, 250, 107, 20);
		contentPane.add(typeApLbl);
		
		JLabel fabLbl = new JLabel("Marque");
		fabLbl.setHorizontalAlignment(SwingConstants.CENTER);
		fabLbl.setBounds(21, 211, 107, 28);
		contentPane.add(fabLbl);
		
		fabField = new RoundJTextField(20);
		autoCmp(fabField, "Select mrq from gmao.marque;", "mrq");
		fabField.setColumns(10);
		fabField.setBounds(127, 215, 156, 20);
		contentPane.add(fabField);
		
		nSerieField = new RoundJTextField(20);
		nSerieField.setColumns(10);
		nSerieField.setBounds(127, 320, 156, 20);
		contentPane.add(nSerieField);
		
		JLabel nSerieLbl = new JLabel("Num\u00E9ro de serie");
		nSerieLbl.setHorizontalAlignment(SwingConstants.CENTER);
		nSerieLbl.setBounds(21, 320, 107, 20);
		contentPane.add(nSerieLbl);
		
		JLabel desLbl = new JLabel("D\u00E9signation");
		desLbl.setHorizontalAlignment(SwingConstants.CENTER);
		desLbl.setBounds(21, 281, 107, 28);
		contentPane.add(desLbl);
		
		desField = new RoundJTextField(20);
		autoCmp(desField, "Select des from gmao.designation;", "des"); 
		desField.setColumns(10);
		desField.setBounds(127, 285, 156, 20);
		contentPane.add(desField);
		
		motRecField = new RoundJTextField(20);
		motRecField.setColumns(10);
		motRecField.setBounds(530, 180, 156, 20);
		contentPane.add(motRecField);
		
		JLabel motifRecLbl = new JLabel("Motif de r\u00E9ception");
		motifRecLbl.setHorizontalAlignment(SwingConstants.CENTER);
		motifRecLbl.setBounds(424, 180, 107, 20);
		contentPane.add(motifRecLbl);
		
		JLabel dateRecepLbl = new JLabel("Date de r\u00E9ception");
		dateRecepLbl.setHorizontalAlignment(SwingConstants.CENTER);
		dateRecepLbl.setBounds(424, 141, 107, 28);
		contentPane.add(dateRecepLbl);
		
		dateRecField = new RoundJTextField(20);
		dateRecField.setColumns(10);
		dateRecField.setBounds(530, 145, 156, 20);
		contentPane.add(dateRecField);
		
		admField = new RoundJTextField(20);
		admField.setColumns(10);
		admField.setBounds(530, 250, 156, 20);
		contentPane.add(admField);
		
		JLabel admLbl = new JLabel("Admission");
		admLbl.setHorizontalAlignment(SwingConstants.CENTER);
		admLbl.setBounds(424, 250, 107, 20);
		contentPane.add(admLbl);
		
		JLabel aspecColLbl = new JLabel("Aspect du colis");
		aspecColLbl.setHorizontalAlignment(SwingConstants.CENTER);
		aspecColLbl.setBounds(424, 211, 107, 28);
		contentPane.add(aspecColLbl);
		
		aspColField = new RoundJTextField(20);
		aspColField.setColumns(10);
		aspColField.setBounds(530, 215, 156, 20);
		contentPane.add(aspColField);
		
		classMedField = new RoundJTextField(20);
		classMedField.setColumns(10);
		classMedField.setBounds(530, 320, 156, 20);
		contentPane.add(classMedField);
		
		JLabel classMedLbl = new JLabel("Classification M\u00E9dcial");
		classMedLbl.setHorizontalAlignment(SwingConstants.CENTER);
		classMedLbl.setBounds(424, 320, 107, 20);
		contentPane.add(classMedLbl);
		
		JLabel nomRecLbl = new JLabel("Nom du r\u00E9ceptionniste");
		nomRecLbl.setHorizontalAlignment(SwingConstants.CENTER);
		nomRecLbl.setBounds(410, 281, 121, 28);
		contentPane.add(nomRecLbl);
		
		nomRecField = new RoundJTextField(20);
		nomRecField.setColumns(10);
		nomRecField.setBounds(530, 285, 156, 20);
		contentPane.add(nomRecField);
		
		addBtn = new RoundJButton("Ajouter");
		addBtn.setBounds(317, 423, 89, 23);
		addBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(verifyField() == true) 
				{
					try 
					{
						if(verifyExistance(nSerieField.getText().trim()) == false) 
						{
							Add(servField, fournField, fabField, 
								typeApField, desField, nSerieField,  
								dateRecField, aspColField, motRecField, 
								admField, nomRecField, classMedField);
							
							Notification.sendNotification(MessageManager.titSoft, 
														  MessageManager.additionItem, 
														  MessageType.INFO);
							
						} 
						else if(verifyExistance(nSerieField.getText().trim()) == true) 
						{
							JOptionPane.showMessageDialog(null, MessageManager.itemSameNSerie);
						}
					}
					catch(Exception e1) 
					{
						e1.printStackTrace();
					}
				} 
				else if(verifyField() == false)
				{
					JOptionPane.showMessageDialog(null, "Veuillez insérer toutes les données dans les champ appropriés !");
				}
			}
		});
		contentPane.add(addBtn);
	}
	
	private void Connect() throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(loginInfo.getUrl(), 
													   loginInfo.getUser(), 
													   loginInfo.getPwd());
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
					 JTextField jt10,
					 JTextField jt11, 
					 JTextField jt12) throws ClassNotFoundException, SQLException 
	{
		Connect();
		sql = "INSERT INTO gmao.receptionequipement (Service, Fournisseur, "
				+ "Fabricant, TypeApp, Designation, NSerie, DateRecep, "
				+ "MotifRecep, AspectColis, Admission, NomRecep, ClasseMedi) "
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?);";
		
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
		ps.setString(11, jt11.getText().trim());
		ps.setString(12, jt12.getText().trim());
		ps.executeUpdate();
	}
	
	private boolean verifyField() 
	{
		verifField = false;
		if(servField.getText().trim().isEmpty()     == false && 
		   fournField.getText().trim().isEmpty()    == false && 
		   fabField.getText().trim().isEmpty()      == false && 
		   typeApField.getText().trim().isEmpty()   == false && 
		   desField.getText().trim().isEmpty()      == false && 
		   nSerieField.getText().trim().isEmpty()   == false &&
		   dateRecField.getText().trim().isEmpty()  == false && 
		   aspColField.getText().trim().isEmpty()   == false &&
		   motRecField.getText().trim().isEmpty()   == false &&
		   admField.getText().trim().isEmpty()      == false && 
		   nomRecField.getText().trim().isEmpty()   == false &&
		   classMedField.getText().trim().isEmpty() == false) 
		{
			return verifField = true;
		}
		return verifField;
	}
	
	private boolean verifyExistance(String str) throws ClassNotFoundException, SQLException 
	{
		verifExist = false;
		Connect();
		sqlExist = "SELECT * FROM gmao.receptionequipement WHERE NSerie = '" + str + "';";
		ps = con.prepareStatement(sqlExist);
		rs = ps.executeQuery();
		while(rs.next()) 
		{
			if(str.trim().equals(rs.getString("NSerie")) == true) 
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