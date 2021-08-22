package OptionFrameInventaire;

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
	private static final long serialVersionUID = -3052504899477951019L;
	private JPanel contentPane;
	private RoundJTextField delField;
	private String sql, sqlVerif;
	private boolean verifExist;
	private PreparedStatement ps;
	private Connection con;
	private ResultSet rs;
	
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
		setBounds(100, 100, 652, 307);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JLabel delLblTitle = new JLabel("Options de supression");
		delLblTitle.setFont(new Font("Tahoma", Font.BOLD, 29));
		delLblTitle.setBounds(175, 11, 344, 71);
		contentPane.add(delLblTitle);
		
		delField = new RoundJTextField(20);
		delField.setHorizontalAlignment(SwingConstants.CENTER);
		delField.setBounds(163, 122, 437, 23);
		autoCmp(delField, "SELECT NSerie from gmao.inventaire;", "NSerie");
		contentPane.add(delField);
		delField.setColumns(10);
		
		JLabel delLbl = new JLabel("Numéro de Série");
		delLbl.setHorizontalAlignment(SwingConstants.CENTER);
		delLbl.setBounds(44, 120, 94, 27);
		contentPane.add(delLbl);
		
		RoundJButton delBtn = new RoundJButton("Suprimer");
		delBtn.setBounds(268, 202, 108, 38);
		delBtn.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) 
				{
					delBtn.doClick();
				}
			}
		});
		delBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(delField.getText().isEmpty() == false) 
				{
					try 
					{
						if(verifExist(delField.getText().trim()) == true) 
						{
							Suprimer(delField);
							Notification.sendNotification(MessageManager.titSoft, MessageManager.deleteItem, MessageType.INFO);	
						} 
						else if(verifExist(delField.getText().trim()) == false) 
						{
							JOptionPane.showMessageDialog(null, MessageManager.itemNotFound);
						}
					} 
					catch (ClassNotFoundException | SQLException | MalformedURLException | AWTException e1) 
					{
						e1.printStackTrace();
					}
				} 
				else if(delField.getText().isEmpty() == true) 
				{
					JOptionPane.showMessageDialog(null, MessageManager.errorMsg);
				}
			}
		});
		contentPane.add(delBtn);
	}
	
	private void Suprimer(JTextField jt) throws SQLException, ClassNotFoundException 
	{
		Connect();
		sql = "DELETE FROM gmao.inventaire WHERE NSerie = ?";
		ps = (PreparedStatement) con.prepareStatement(sql);
		ps.setString(1, jt.getText());
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
	
	private boolean verifExist(String str) throws SQLException, ClassNotFoundException 
	{
		verifExist = false;
		Connect();
		sqlVerif = "SELECT * FROM gmao.inventaire WHERE NSerie = '" + str + "';";
		ps = (PreparedStatement) con.prepareStatement(sqlVerif);
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