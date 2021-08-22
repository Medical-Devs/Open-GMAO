package Filters;

import java.awt.AWTException;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
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
import EditMultipleObjects.RoundJTextField;
import Notifications.MessageManager;
import OptionXLogin.loginInfo;
import doryan.windowsnotificationapi.fr.Notification;

public class Désignation extends JFrame 
{
	private static final long serialVersionUID = 119191919L;
	private JPanel contentPane;
	private JTextField desField;
	private Connection con;
	private PreparedStatement ps;

	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Désignation frame = new Désignation();
					frame.setVisible(true);
					
				} 
				catch (Exception e) 
				{
						e.printStackTrace();
				}
			}
		});
	}

	public Désignation() 
	{
		setTitle("Option d'insertion de d\u00E9signation");
		setType(Type.POPUP);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 542, 384);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton confBtn = new RoundJButton("Confirmer");
		confBtn.setBounds(208, 271, 119, 40);
		confBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					if(desField.getText().isEmpty() == false) 
					{
						add(desField);
						Notification.sendNotification(MessageManager.additionItem, MessageType.INFO);		
					} 
					else if(desField.getText().isEmpty() == true) 
					{
							Notification.sendNotification(MessageManager.errorMsg, MessageType.WARNING);
					}
				} 
				catch (MalformedURLException | AWTException | ClassNotFoundException | SQLException e1) 
				{
					e1.printStackTrace();
				} 
			}
		});
		contentPane.add(confBtn);
		
		desField = new RoundJTextField(20);
		desField.setHorizontalAlignment(SwingConstants.CENTER);
		desField.setBounds(188, 155, 236, 20);
		desField.addKeyListener(new KeyAdapter() 
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
		contentPane.add(desField);
		desField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("D\u00E9signation");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(72, 158, 79, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Insertion de d\u00E9signation");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(120, 46, 274, 30);
		contentPane.add(lblNewLabel_1);
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
		String sql = "INSERT INTO gmao.designation (des) values(?);";
		ps = (PreparedStatement) con.prepareStatement(sql);
		ps.setString(1, jt.getText().trim());
		ps.executeUpdate();
		con.close();
		ps.close();
	}
}