package NormOptionFrameCommandePRechange;

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
import java.text.ParseException;
import javax.swing.JButton;
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

public class AddingOptionFrame extends JFrame 
{
	private static final long serialVersionUID = -7222454040752067880L;
	private JPanel contentPane;
	private RoundJTextField fournField, nComField, prixField, 
							qteField, serviceField, dateField;
	private String sql, sqlVerif;
	private JButton addBtn;
	private boolean verifBool, verifEx;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					AddingOptionFrame frame = new AddingOptionFrame();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	public AddingOptionFrame() throws ClassNotFoundException, SQLException 
	{
		setTitle("Adding Options");
		setType(Type.POPUP);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 901, 414);
		contentPane = new JPanel();
		setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel titLabel = new JLabel("Adding Options");
		titLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		titLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titLabel.setBounds(356, 26, 173, 23);
		contentPane.add(titLabel);
		
		JLabel founLbl = new JLabel("Fournisseur");
		founLbl.setHorizontalAlignment(SwingConstants.CENTER);
		founLbl.setBounds(54, 131, 142, 23);
		contentPane.add(founLbl);
		
		nComField = new RoundJTextField(20);
		nComField.setColumns(10);
		nComField.setBounds(233, 185, 173, 20);
		contentPane.add(nComField);
		
		fournField = new RoundJTextField(20);
		autoCmp(fournField, "Select Nom from gmao.entreprise;", "Nom");
		fournField.setBounds(233, 131, 173, 20);
		contentPane.add(fournField);
		fournField.setColumns(10);
		
		JLabel nCommandeLbl = new JLabel("Num\u00E9ro de Commande");
		nCommandeLbl.setHorizontalAlignment(SwingConstants.CENTER);
		nCommandeLbl.setBounds(54, 184, 142, 23);
		contentPane.add(nCommandeLbl);
		
		prixField = new RoundJTextField(20);
		prixField.setColumns(10);
		prixField.setBounds(233, 234, 173, 20);
		contentPane.add(prixField);
		
		JLabel prixLbl = new JLabel("Prix UHT");
		prixLbl.setHorizontalAlignment(SwingConstants.CENTER);
		prixLbl.setBounds(54, 234, 142, 23);
		contentPane.add(prixLbl);
		
		qteField = new RoundJTextField(20);
		qteField.setColumns(10);
		qteField.setBounds(620, 133, 173, 20);
		contentPane.add(qteField);
		
		JLabel qteLbl = new JLabel("Quantit\u00E9");
		qteLbl.setHorizontalAlignment(SwingConstants.CENTER);
		qteLbl.setBounds(501, 131, 109, 23);
		contentPane.add(qteLbl);
		
		serviceField = new RoundJTextField(20);
		serviceField.setColumns(10);
		serviceField.setBounds(620, 188, 173, 20);
		autoCmp(serviceField, "Select Service_Concerne from gmao.commandePieceRechange;", "Service_Concerne");
		contentPane.add(serviceField);
		
		JLabel servConcLbl = new JLabel("Service Concern\u00E9");
		servConcLbl.setHorizontalAlignment(SwingConstants.CENTER);
		servConcLbl.setBounds(501, 187, 109, 23);
		contentPane.add(servConcLbl);
		
		dateField = new RoundJTextField(20);
		dateField.setColumns(10);
		dateField.setBounds(620, 237, 173, 20);
		contentPane.add(dateField);
		
		JLabel dateLbl = new JLabel("Date de Commande");
		dateLbl.setHorizontalAlignment(SwingConstants.CENTER);
		dateLbl.setBounds(490, 236, 120, 23);
		contentPane.add(dateLbl);
		
		addBtn = new RoundJButton("Ajouter");
		addBtn.setBounds(409, 318, 89, 23);
		addBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(verifyField() == true) 
				{
						try 
						{
							if(verifExist(nComField.getText().trim()) == true) 
							{
								JOptionPane.showMessageDialog(null, MessageManager.itemSameNCommande);
							} 
							else if(verifExist(nComField.getText().trim()) == false) 
							{
								Add(fournField, nComField, prixField, qteField, serviceField, dateField);
								Notification.sendNotification(MessageManager.titSoft, MessageManager.additionItem, MessageType.INFO);							
							}
						} 
						catch(ClassNotFoundException | SQLException | MalformedURLException | AWTException | ParseException e1) 
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
		con = (Connection) DriverManager.getConnection(loginInfo.getUrl(), 	
													   loginInfo.getUser(), 
													   loginInfo.getPwd());
	}
	
	private void Add(JTextField jt1,
					 JTextField jt2,	 
					 JTextField jt3,	
					 JTextField jt4,	
					 JTextField jt5,	
					 JTextField jt6) throws ClassNotFoundException, SQLException, ParseException 
	{
		Connect();
		sql = "INSERT INTO gmao.commandepiecerechange (Fournisseur, NCommande, PrixUHT, Qte, "
				+ "Service_Concerne, Date_Commande) VALUES(?,?,?,?,?,?);";
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
		verifBool = false;
		if(fournField.getText().trim().isEmpty()   == false && 
		   nComField.getText().trim().isEmpty()    == false && 
		   prixField.getText().trim().isEmpty()    == false &&
		   qteField.getText().trim().isEmpty()     == false && 
		   serviceField.getText().trim().isEmpty() == false && 
		   dateField.getText().trim().isEmpty()    == false) {
			return verifBool = true;
		}
		return verifBool;
	}
	
	private boolean verifExist(String str) throws ClassNotFoundException, SQLException 
	{
		verifEx = false;
		Connect();
		sqlVerif = "SELECT * FROM gmao.commandepiecerechange WHERE NCommande = '" + str + "';";
		ps = (PreparedStatement) con.prepareStatement(sqlVerif);
		rs = ps.executeQuery();
		while(rs.next()) 
		{
			if(str.trim().equals(rs.getString("NCommande")) == true) 
			{
				return verifEx = true;
			}
		}
		con.close();
		ps.close();
		rs.close();
		return verifEx;
	}
	
	private void autoCmp(JTextField jt, String str1, String str2) 
	{
		try 
		{
			Connect();
			TextAutoCompleter auto = new TextAutoCompleter(jt);
			String sqlAuto = str1;
			ps = (PreparedStatement) con.prepareStatement(sqlAuto);
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