package OptionFrameStockDeRechange;

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
	private static final long serialVersionUID = 2715605377450652545L;
	private JPanel contentPane;
	private RoundJTextField typeField, mrqField, modField, qteField, daField ,nSerieField;
	private String sql;
	private Connection con;
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
		setTitle("Option d'addition");
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 723, 477);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titLbl = new JLabel("Adding Items");
		titLbl.setFont(new Font("Tahoma", Font.PLAIN, 28));
		titLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titLbl.setBounds(219, 30, 292, 34);
		contentPane.add(titLbl);
		
		JLabel typeLbl = new JLabel("Type d'\u00E9quipement");
		typeLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		typeLbl.setHorizontalAlignment(SwingConstants.CENTER);
		typeLbl.setBounds(10, 172, 144, 15);
		contentPane.add(typeLbl);
		
		JLabel mrqLbl = new JLabel("Marque");
		mrqLbl.setHorizontalAlignment(SwingConstants.CENTER);
		mrqLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mrqLbl.setBounds(47, 244, 58, 15);
		contentPane.add(mrqLbl);
		
		JLabel modLbl = new JLabel("Mod\u00E8le");
		modLbl.setHorizontalAlignment(SwingConstants.CENTER);
		modLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		modLbl.setBounds(47, 329, 58, 15);
		contentPane.add(modLbl);
		
		JLabel qteLbl = new JLabel("Quantit\u00E9");
		qteLbl.setHorizontalAlignment(SwingConstants.CENTER);
		qteLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		qteLbl.setBounds(404, 172, 58, 15);
		contentPane.add(qteLbl);
		
		JLabel lblDateDacquisition = new JLabel("Date d'acquisition");
		lblDateDacquisition.setHorizontalAlignment(SwingConstants.CENTER);
		lblDateDacquisition.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDateDacquisition.setBounds(377, 244, 116, 15);
		contentPane.add(lblDateDacquisition);
		
		JLabel lblNumroDeSrie = new JLabel("Num\u00E9ro de S\u00E9rie");
		lblNumroDeSrie.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumroDeSrie.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNumroDeSrie.setBounds(377, 329, 105, 15);
		contentPane.add(lblNumroDeSrie);
		
		typeField = new RoundJTextField(20);
		typeField.setHorizontalAlignment(SwingConstants.CENTER);
		typeField.setBounds(160, 170, 167, 20);
		contentPane.add(typeField);
		typeField.setColumns(10);
		
		mrqField = new RoundJTextField(20);
		mrqField.setHorizontalAlignment(SwingConstants.CENTER);
		autoCmp(mrqField, "SELECT mrq from marque;", "mrq");
		mrqField.setColumns(10);
		mrqField.setBounds(160, 242, 167, 20);
		contentPane.add(mrqField);
		
		modField = new RoundJTextField(20);
		modField.setHorizontalAlignment(SwingConstants.CENTER);
		modField.setColumns(10);
		modField.setBounds(160, 327, 167, 20);
		contentPane.add(modField);
		
		qteField = new RoundJTextField(20);
		qteField.setHorizontalAlignment(SwingConstants.CENTER);
		qteField.setColumns(10);
		qteField.setBounds(508, 170, 167, 20);
		contentPane.add(qteField);
		
		daField = new RoundJTextField(20);
		daField.setHorizontalAlignment(SwingConstants.CENTER);
		daField.setColumns(10);
		daField.setBounds(508, 242, 167, 20);
		contentPane.add(daField);
		
		nSerieField = new RoundJTextField(20);
		nSerieField.setHorizontalAlignment(SwingConstants.CENTER);
		nSerieField.setColumns(10);
		nSerieField.setBounds(508, 327, 167, 20);
		contentPane.add(nSerieField);
		
		RoundJButton addBtn = new RoundJButton("Ajouter");
		addBtn.setBounds(297, 393, 105, 34);
		addBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(typeField.getText().trim().isEmpty()   == false && 
				   mrqField.getText().trim().isEmpty()    == false &&
				   modField.getText().trim().isEmpty()    == false && 
				   qteField.getText().trim().isEmpty()    == false && 
				   daField.getText().trim().isEmpty()     == false && 
				   nSerieField.getText().trim().isEmpty() == false) 
				{
					try 
					{
						Add(typeField, 
							mrqField,
							modField, 
							qteField,
							daField,
							nSerieField);
						
						Notification.sendNotification(MessageManager.titSoft, 
													  MessageManager.additionItem, 
													  MessageType.INFO
								  					  );
					} 
					catch(Exception e1) 
					{
						e1.printStackTrace();
					}
				} 
				else 
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
		con = DriverManager.getConnection(loginInfo.getUrl(), 
													   loginInfo.getUser(), 
													   loginInfo.getPwd());
	}
	
	private void Add(JTextField jt1, 
					 JTextField jt2, 
					 JTextField jt3, 
					 JTextField jt4, 
					 JTextField jt5, 
					 JTextField jt6) throws ClassNotFoundException, SQLException 
	{
		Connect();
		sql = "INSERT INTO gmao.stockderechange (Type, Marque, Modele, Qte, DateAcquisition, NdeSerie) VALUES (?, ?, ?, ?, ?, ?);";
		ps = con.prepareStatement(sql);
		ps.setString(1, typeField.getText().trim());
		ps.setString(2, mrqField.getText().trim());
		ps.setString(3, modField.getText().trim());
		ps.setString(4, qteField.getText().trim());
		ps.setString(5, daField.getText().trim());
		ps.setString(6, nSerieField.getText().trim());
		ps.executeUpdate();
	}
	
	private void autoCmp(JTextField jt, 
						 String str1, 
						 String str2) 
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