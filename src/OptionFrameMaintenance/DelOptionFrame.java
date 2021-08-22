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
import javax.swing.border.EmptyBorder;
import com.mxrck.autocompleter.TextAutoCompleter;
import EditMultipleObjects.RoundJButton;
import EditMultipleObjects.RoundJTextField;
import Notifications.MessageManager;
import OptionXLogin.loginInfo;
import doryan.windowsnotificationapi.fr.Notification;

public class DelOptionFrame extends JFrame 
{
	private static final long serialVersionUID = -705878156018324792L;
	private JPanel contentPane;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql, sqlVerif;
	private RoundJButton delBtn;
	private RoundJTextField nSerField;

	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					DelOptionFrame frame = new DelOptionFrame();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	public DelOptionFrame() 
	{
		setType(Type.POPUP);
		setTitle("Option de suppression");
		setResizable(false);
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titleLbl = new JLabel("Option de supression");
		titleLbl.setFont(new Font("Tahoma", Font.PLAIN, 23));
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setBounds(95, 11, 229, 28);
		contentPane.add(titleLbl);
		
		nSerField = new RoundJTextField(20);
		autoCmp(nSerField, "Select NdeSerie from gmao.maintenance;", "NdeSerie");
		nSerField.setBounds(199, 103, 175, 20);
		contentPane.add(nSerField);
		nSerField.setColumns(10);
		
		JLabel nSerLbl = new JLabel("Num\u00E9ro de Serie");
		nSerLbl.setHorizontalAlignment(SwingConstants.CENTER);
		nSerLbl.setBounds(37, 106, 124, 14);
		contentPane.add(nSerLbl);
		
		delBtn = new RoundJButton("Suprimer");
		delBtn.setBounds(161, 190, 89, 23);
		delBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(nSerField.getText().trim().isEmpty() == false) 
				{
					try {
						if(verifExist(nSerField.getText().trim()) == true) 
						{
							Delete(nSerField);
							Maintenance mt = new Maintenance();
							mt.Update();
							Notification.sendNotification(MessageManager.titSoft, 
														 MessageManager.deleteItem, 	
														 MessageType.INFO);	
						} 
						else if(verifExist(nSerField.getText().trim()) == false) 
						{
							JOptionPane.showMessageDialog(null, MessageManager.itemSameNSerie);
						}
					} 
					catch (Exception e1) 
					{
						e1.printStackTrace();
					}
				} 
				else if(nSerField.getText().trim().isEmpty() == true)
				{
					JOptionPane.showMessageDialog(null, MessageManager.errorMsg);
				}
			}
		});
		contentPane.add(delBtn);
	}
	
	protected void Connect() throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.mysql.jdbc.Driver");
		con = (Connection) DriverManager.getConnection(loginInfo.getUrl(), loginInfo.getUser(), loginInfo.getPwd());
	}
	
	protected void Delete(JTextField jt) throws ClassNotFoundException, SQLException 
	{
		Connect();
		sql= "DELETE FROM gmao.maintenance WHERE NdeSerie = ?";
		ps = (PreparedStatement) con.prepareStatement(sql);
		ps.setString(1, jt.getText().trim());
		ps.executeUpdate();
	}
	
	protected boolean verifExist(String str) throws ClassNotFoundException, SQLException 
	{
		boolean verifExist = false;
		Connect();
		sqlVerif = "select * from gmao.maintenance where NdeSerie = '" + str + "';";
		ps = (PreparedStatement) con.prepareStatement(sqlVerif);
		rs = ps.executeQuery();
		while(rs.next()) 
		{
			if(str.trim().equals(rs.getString("NdeSerie")) == true) 
			{
				return verifExist = true;
			}
		}
		con.close();
		rs.close();
		ps.close();
		return verifExist;
	}
	
	protected void autoCmp(JTextField jt, String str1, String str2) 
	{
		try 
		{
			Connect();
			TextAutoCompleter auto = new TextAutoCompleter(jt);
			String sqlAuto = str1;
			ps = (PreparedStatement) con.prepareStatement(sqlAuto);
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