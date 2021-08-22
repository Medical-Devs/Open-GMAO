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

public class DelOptionFrame extends JFrame 
{
	private static final long serialVersionUID = 5679793741137389246L;
	private JPanel contentPane;
	private String sql, sqlVerif;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private RoundJTextField nSerieField;

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
		setTitle("Delete Window");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 504, 262);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel nSerieLbl = new JLabel("Num\u00E9ro de Serie");
		nSerieLbl.setHorizontalAlignment(SwingConstants.CENTER);
		nSerieLbl.setBounds(79, 103, 134, 20);
		contentPane.add(nSerieLbl);
		
		nSerieField = new RoundJTextField(20);
		nSerieField.setHorizontalAlignment(SwingConstants.CENTER);
		nSerieField.setColumns(10);
		autoCmp(nSerieField, "Select nSerie from gmao.plannification;", "nSerie");
		nSerieField.setBounds(253, 103, 134, 20);
		contentPane.add(nSerieField);
		
		RoundJButton delBtn = new RoundJButton("Suprimer");
		delBtn.setBounds(185, 175, 98, 34);
		delBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(nSerieField.getText().trim().isEmpty() == false) 
				{
					try 
					{
						if(verifyExist(nSerieField.getText().trim()) == true) 
						{
							Delete(nSerieField);
							Notification.sendNotification(MessageManager.titSoft, MessageManager.deleteItem, MessageType.INFO);	
						} 
						else if(verifyExist(nSerieField.getText().trim()) == false) 
						{
							JOptionPane.showMessageDialog(null, MessageManager.itemNotFound);
						}
					} 
					catch(ClassNotFoundException | SQLException | MalformedURLException | AWTException e1) 
					{
						e1.printStackTrace();
					}	
				} 
				else if(nSerieField.getText().trim().isEmpty() == true)
				{
					JOptionPane.showMessageDialog(null, MessageManager.errorMsg);
				}
			}
		});
		contentPane.add(delBtn);
		
		JLabel lblNewLabel = new JLabel("Delete Options");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(161, 22, 162, 42);
		contentPane.add(lblNewLabel);
	}
	
	private void Delete(JTextField jt) throws ClassNotFoundException, SQLException 
	{
		Connect();
		sql = "DELETE FROM gmao.plannification WHERE nSerie = ?";
		ps = (PreparedStatement) con.prepareStatement(sql);
		ps.setString(1, jt.getText().trim());
		ps.executeUpdate();
	}
	
	private void Connect() throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.mysql.jdbc.Driver");
		con = (Connection) DriverManager.getConnection(loginInfo.getUrl(), 
													   loginInfo.getUser(), 
													   loginInfo.getPwd()
													   );
	}
	
	private boolean verifyExist(String str) throws ClassNotFoundException, SQLException
	{
		boolean verifExist = false;
		Connect();
		sqlVerif = "select * from gmao.plannification where nSerie = '" + str + "';";
		ps = (PreparedStatement) con.prepareStatement(sqlVerif);
		rs = ps.executeQuery();
		while(rs.next()) 
		{
			if(str.trim().equals(rs.getString("nSerie")) == true) 
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