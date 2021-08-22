package Filters;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import EditMultipleObjects.RoundJButton;
import Notifications.MessageManager;
import OptionXLogin.loginInfo;
import doryan.windowsnotificationapi.fr.Notification;

public class EntrepriseList extends JFrame 
{
	private static final long serialVersionUID = -6348956373322849852L;
	private JPanel contentPane;
	private Connection con;
	private PreparedStatement ps;
	private String sql;
	private JTextField socField;
	private JButton confBtn;

	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					EntrepriseList frame = new EntrepriseList();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	public EntrepriseList() 
	{
		setResizable(false);
		setTitle("Option d'insertion d'entreprise");
		setType(Type.POPUP);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 488, 323);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		socField = new JTextField();
		socField.setHorizontalAlignment(SwingConstants.CENTER);
		socField.setBounds(184, 141, 213, 20);
		socField.addKeyListener(new KeyAdapter() 
		{
			@Override 
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER) 
				{
					confBtn.doClick();
				}
			}
		});
		contentPane.add(socField);
		socField.setColumns(10);
		
		JLabel socLbl = new JLabel("Nom de la soci\u00E9t\u00E9");
		socLbl.setHorizontalAlignment(SwingConstants.CENTER);
		socLbl.setBounds(27, 141, 130, 20);
		contentPane.add(socLbl);
		
		JLabel lblNewLabel = new JLabel("Insertion de nouvelle soci\u00E9t\u00E9");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(120, 46, 270, 40);
		contentPane.add(lblNewLabel);
		
		confBtn = new RoundJButton("Confirmer");
		confBtn.setBounds(168, 212, 108, 33);
		confBtn.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					add(socField);
					Notification.sendNotification(MessageManager.additionItem, MessageType.INFO);
				} 
				catch(Exception e1) 
				{
					e1.printStackTrace();
				}
			}			
		});
		contentPane.add(confBtn);
	}
	
	private void Connect() throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.mysql.jdbc.Driver");
		con = (Connection) DriverManager.getConnection(loginInfo.getUrl(), 
													   loginInfo.getUser(), 
													   loginInfo.getPwd()
													   );
	}
	
	private void add(JTextField jt) throws ClassNotFoundException, SQLException 
	{
		Connect();
		sql = "Insert into gmao.entreprise (Nom) values (?);";
		ps = (PreparedStatement) con.prepareStatement(sql);
		ps.setString(1, jt.getText().trim());
		ps.executeUpdate();
		ps.close();
		con.close();
	}
}