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
import javax.swing.border.EmptyBorder;
import com.mxrck.autocompleter.TextAutoCompleter;
import EditMultipleObjects.RoundJButton;
import EditMultipleObjects.RoundJTextField;
import Notifications.MessageManager;
import OptionXLogin.loginInfo;
import doryan.windowsnotificationapi.fr.Notification;

public class DelOptions extends JFrame 
{
	private static final long serialVersionUID = 9054801362357071539L;
	private JPanel contentPane;
	private RoundJTextField refField;
	private String sql, sqlVerif;
	private RoundJButton delBtn;
	private boolean verifExist;
	private Connection con;
	private ResultSet rs;
	private PreparedStatement ps;

	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
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
		setTitle("Delete Options");
		setType(Type.POPUP);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 585, 291);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel delTit = new JLabel("Delete Option");
		delTit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		delTit.setHorizontalAlignment(SwingConstants.CENTER);
		delTit.setBounds(164, 26, 227, 31);
		contentPane.add(delTit);
		
		refField = new RoundJTextField(20);
		refField.setBounds(229, 114, 240, 20);
		autoCmp(refField, "Select Reference_Rapport from gmao.rapport_intervention;", "Reference_Rapport");
		contentPane.add(refField);
		refField.setColumns(10);
		
		JLabel refLbl = new JLabel("R\u00E9ference du rapport");
		refLbl.setHorizontalAlignment(SwingConstants.CENTER);
		refLbl.setBounds(64, 114, 136, 20);
		contentPane.add(refLbl);
		
		delBtn = new RoundJButton("Delete");
		delBtn.setBounds(219, 189, 108, 39);
		delBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{			
				if(refField.getText().trim().isEmpty() == false) 
				{
					try 
					{
						if(existanceVerif(refField.getText().trim()) == true) 
						{
							Delete(refField);
							Notification.sendNotification(MessageManager.titSoft, MessageManager.deleteItem, MessageType.INFO);
						} 
						else if(existanceVerif(refField.getText().trim()) == false) 
						{
							JOptionPane.showMessageDialog(null, MessageManager.itemNotFound);
						}
					} 
					catch(Exception e1) 
					{
						e1.printStackTrace();
					}
					
				} 
				else if(refField.getText().trim().isEmpty() == true)
				{
					JOptionPane.showMessageDialog(null, MessageManager.errorMsg);
				}
			}
		});
		contentPane.add(delBtn);
	}
	
	private void Connect() throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.mysql.jdbc.Driver");
		con = (Connection) DriverManager.getConnection(loginInfo.getUrl(), loginInfo.getUser(), loginInfo.getPwd());
	}
	
	private void Delete(JTextField jt) throws ClassNotFoundException, SQLException 
	{
		Connect();
		sql = "DELETE FROM gmao.rapport_intervention WHERE Reference_Rapport = ?; ";
		ps = (PreparedStatement) con.prepareStatement(sql);
		ps.setString(1, jt.getText().trim());
		ps.executeUpdate();
	}
	
	private boolean existanceVerif(String str) throws ClassNotFoundException, SQLException 
	{
		verifExist = false;
		Connect();
		sqlVerif = "SELECT * FROM gmao.rapport_intervention WHERE Reference_Rapport = '" + str + "';";
		ps = (PreparedStatement) con.prepareStatement(sqlVerif);
		rs = ps.executeQuery();
		while(rs.next()) 
		{
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