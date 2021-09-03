package NormOptionFrameRéceptionEquipement;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TrayIcon.MessageType;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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

public class DelOptions extends JFrame 
{
	private static final long serialVersionUID = -4691808668938997654L;
	private JPanel contentPane;
	private RoundJTextField nSerieField;
	private String sql, sqlVerif;
	private RoundJButton delBtn;
	private boolean verifEx;
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
					DelOptions frame = new DelOptions();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	public DelOptions() 
	{
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 637, 251);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel delTitle = new JLabel("Deleting Options");
		delTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		delTitle.setHorizontalAlignment(SwingConstants.CENTER);
		delTitle.setBounds(210, 21, 178, 32);
		contentPane.add(delTitle);
		
		JLabel nSerie = new JLabel("Num\u00E9ro de Serie");
		nSerie.setHorizontalAlignment(SwingConstants.CENTER);
		nSerie.setBounds(126, 100, 129, 22);
		contentPane.add(nSerie);
		
		nSerieField = new RoundJTextField(20);
		nSerieField.setBounds(273, 101, 199, 20);
		autoCmp(nSerieField, "Select NSerie from gmao.receptionequipement;", "NSerie");
		contentPane.add(nSerieField);
		nSerieField.setColumns(10);
		
		delBtn = new RoundJButton("Delete");
		delBtn.setBounds(246, 178, 89, 23);
		delBtn.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER) 
				{
					if(nSerieField.getText().trim().isEmpty() == false) 
					{
						try 
						{
							if(verifExist(nSerieField.getText().trim()) == true) 
							{
								Delete(nSerieField);
								Notification.sendNotification(MessageManager.titSoft, MessageManager.deleteItem, MessageType.INFO);	
							} 
							else if(verifExist(nSerieField.getText().trim()) == false) 
							{
								JOptionPane.showMessageDialog(null, MessageManager.itemNotFound);
							}
						} 
						catch(Exception z) 
						{
							z.printStackTrace();
						}
					} 
					else if(nSerieField.getText().trim().isEmpty() == true)
					{
						JOptionPane.showMessageDialog(null, MessageManager.errorMsg);
					}
				}
			}
		});
		contentPane.add(delBtn);
	}
	
	private void Connect() throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(loginInfo.getUrl(), loginInfo.getUser(), loginInfo.getPwd());
	}
	
	private void Delete(JTextField jt) throws ClassNotFoundException, SQLException 
	{
		Connect();
		sql = "DELETE FROM gmao.receptionequipement WHERE NSerie = ? ;";
		ps = con.prepareStatement(sql);
		ps.setString(1, jt.getText().trim());
		ps.executeUpdate();
	}
	
	private boolean verifExist(String str) throws ClassNotFoundException, SQLException 
	{
		verifEx = false;
		Connect();
		sqlVerif = "SELECT * FROM gmao.receptionequipement WHERE NSerie = '" + str + "';";
		ps = con.prepareStatement(sqlVerif);
		rs = ps.executeQuery();
		while(rs.next()) {
			if(str.trim().equals(rs.getString("NSerie")) == true) 
			{
				return verifEx = true;
			}
		}
		return verifEx;
	}
	
	private void autoCmp(JTextField jt, String str1, String str2) 
	{
		try {
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