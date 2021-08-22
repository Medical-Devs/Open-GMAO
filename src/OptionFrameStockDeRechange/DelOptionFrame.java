package OptionFrameStockDeRechange;

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
	private static final long serialVersionUID = -7660600504338776431L;
	private String sql, sqlVerif;
	private Connection con;
	private JPanel contentPane;
	private RoundJTextField nSerieField;
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
		setTitle("Option de suppression");
		setType(Type.POPUP);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 481, 272);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		nSerieField = new RoundJTextField(20);
		nSerieField.setHorizontalAlignment(SwingConstants.CENTER);
		autoCmp(nSerieField, "Select NdeSerie from gmao.stockderechange;", "NdeSerie");
		nSerieField.setBounds(210, 113, 222, 20);
		contentPane.add(nSerieField);
		nSerieField.setColumns(10);
		
		JLabel NdeSerie = new JLabel("Num\u00E9ro de Serie");
		NdeSerie.setHorizontalAlignment(SwingConstants.CENTER);
		NdeSerie.setBounds(80, 113, 109, 20);
		contentPane.add(NdeSerie);
		
		JLabel titleLabel = new JLabel("Option de suppression");
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(133, 37, 208, 29);
		contentPane.add(titleLabel);
		
		
		RoundJButton delButton = new RoundJButton("Delete");
		delButton.setBounds(185, 189, 89, 23);
		delButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(nSerieField.getText().trim().isEmpty() == false) 
				{
					try 
					{
						if(verifyExistance(nSerieField.getText().trim()) == true) 
						{
							Suprimer(nSerieField);
							Notification.sendNotification(MessageManager.titSoft, 
														  MessageManager.deleteItem, 
														  MessageType.INFO
														  );
						} 
						else if(verifyExistance(nSerieField.getText().trim()) == false) 
						{
							JOptionPane.showMessageDialog(null, MessageManager.itemNotFound);
						}
					} catch (ClassNotFoundException | SQLException| MalformedURLException | AWTException e1) 
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
		contentPane.add(delButton);
	}
	
	private void Connect() throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.mysql.jdbc.Driver");
		con = (Connection) DriverManager.getConnection(loginInfo.getUrl(), 
													   loginInfo.getUser(), 
													   loginInfo.getPwd()
													   );
	}
	
	private void Suprimer(JTextField jt) throws ClassNotFoundException, SQLException
	{
		Connect();
		sql ="DELETE FROM gmao.stockderechange WHERE NdeSerie = ?";
		ps = (PreparedStatement) con.prepareStatement(sql);
		ps.setString(1, jt.getText().trim());
		ps.executeUpdate();
		ps.close();
		con.close();
	}
	
	private boolean verifyExistance(String str) throws ClassNotFoundException, SQLException
	{
		boolean verifyEx = false;
		Connect();
		sqlVerif = "select * from gmao.stockderechange where NdeSerie = '" + str + "';";
		ps = (PreparedStatement) con.prepareStatement(sqlVerif);
		rs = ps.executeQuery();
		while(rs.next()) {
			if(str.trim().equals(rs.getString("NdeSerie")) == true) 
			{
				return verifyEx = true;
			}
		}
		return verifyEx;
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