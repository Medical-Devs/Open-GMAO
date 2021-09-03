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

public class DelOptionFrame extends JFrame 
{
	private static final long serialVersionUID = 7476906812752718253L;
	private JPanel contentPane;
	private RoundJTextField nComField;
	private String sql, sqlVerf;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private RoundJButton delBtn;
	@SuppressWarnings("unused")
	private boolean existVer;
	
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			@Override
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
		setResizable(false);
		setTitle("Delete Option");
		setType(Type.POPUP);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 542, 340);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		nComField = new RoundJTextField(20);

		nComField.setBounds(228, 135, 181, 20);
		contentPane.add(nComField);
		nComField.setColumns(10);
		autoCmp(nComField);
		
		JLabel nComLbl = new JLabel("Num\u00E9ro de Commande");
		nComLbl.setHorizontalAlignment(SwingConstants.CENTER);
		nComLbl.setBounds(50, 138, 148, 14);
		contentPane.add(nComLbl);
		
		JLabel delTitle = new JLabel("Delete Options");
		delTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		delTitle.setHorizontalAlignment(SwingConstants.CENTER);
		delTitle.setBounds(184, 27, 173, 37);
		contentPane.add(delTitle);
		
		delBtn = new RoundJButton("Suprimer");
		delBtn.setBounds(228, 263, 89, 23);
		delBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				
				if(nComField.getText().trim().isEmpty() == false) 
				{
					try
					{
						if(existanceVerif(nComField.getText().trim()) == true) 
						{
							Delete(nComField);
							Notification.sendNotification(MessageManager.titSoft, MessageManager.deleteItem, MessageType.INFO);
						} 
						else if(existanceVerif(nComField.getText().trim()) == false)
						{
							JOptionPane.showMessageDialog(null, MessageManager.itemNotFound);
						}
					} 
					catch(ClassNotFoundException | SQLException | MalformedURLException | AWTException e1) 
					{
						e1.printStackTrace();
					}
				} 
				else if(nComField.getText().trim().isEmpty() == true)
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
		con = DriverManager.getConnection(loginInfo.getUrl(), 	
													   loginInfo.getUser(), 
													   loginInfo.getPwd()
													   );
	}
	
	private void Delete(JTextField jt) throws ClassNotFoundException, SQLException 
	{
		Connect();
		sql = "DELETE FROM gmao.commandepiecerechange WHERE NCommande = ?;";
		ps = con.prepareStatement(sql);
		ps.setString(1, jt.getText().trim());
		ps.executeUpdate();
		ps.close();
		con.close();
	}
	
	private boolean existanceVerif(String str) throws ClassNotFoundException, SQLException 
	{
		existVer = false;
		Connect();
		sqlVerf = "SELECT * FROM gmao.commandepiecerechange WHERE NCommande = '" + str + "';";
		ps = con.prepareStatement(sqlVerf);
		rs = ps.executeQuery();
		while(rs.next()) 
		{
			if(str.trim().equals(rs.getString("NCommande")) == true) 
			{
				return existVer = true;
			}
		}
		return existVer = false;
	}
	
	private void autoCmp(JTextField jt) 
	{
		try 
		{
			Connect();
			TextAutoCompleter auto = new TextAutoCompleter(jt);
			String sqlAuto = "Select NCommande from gmao.commandePieceRechange;";
			ps = con.prepareStatement(sqlAuto);
			rs = ps.executeQuery();
			while(rs.next()) 
			{
				auto.addItem(rs.getString("NCommande"));
			}
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
		}
	}
}