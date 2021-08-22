package NormOptionFrameDemandIntervExt;

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
	private static final long serialVersionUID = -5572124443124503482L;
	private JPanel contentPane;
	private RoundJTextField rfField;
	private String sql, sqlVerif;
	private boolean verifEx;
	private RoundJButton delBtn;
	private Connection con;
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
		setType(Type.POPUP);
		setTitle("Delete Options");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 556, 303);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titLbl = new JLabel("Delete Options");
		titLbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		titLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titLbl.setBounds(201, 23, 144, 33);
		contentPane.add(titLbl);
		
		JLabel rfLbl = new JLabel("R\u00E9f\u00E9rence de la demande");
		rfLbl.setHorizontalAlignment(SwingConstants.CENTER);
		rfLbl.setBounds(41, 129, 165, 19);
		contentPane.add(rfLbl);
		
		rfField = new RoundJTextField(20);
		autoCmp(rfField, "Select ReferenceDemande from gmao.demandeintexterne;", "ReferenceDemande");
		rfField.setBounds(241, 128, 214, 20);
		contentPane.add(rfField);
		rfField.setColumns(10);
		
		delBtn = new RoundJButton("Suprimer");
		delBtn.setBounds(233, 211, 89, 23);
		delBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(rfField.getText().trim().isEmpty() == false) 
				{
					try 
					{
						if(existanceVerif(rfField.getText().trim()) == true) 
						{
							Delete(rfField);
							Notification.sendNotification(MessageManager.titSoft, 
														  MessageManager.deleteItem, 
														  MessageType.INFO
														  );	
						} 
						else if(existanceVerif(rfField.getText().trim()) == false) 
						{
							JOptionPane.showMessageDialog(null, MessageManager.itemSameNRef);
						}
					} 
					catch(Exception e1) 
					{
						e1.printStackTrace();
					}
				} 
				else if(rfField.getText().trim().isEmpty() == true) 
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
		con = (Connection) DriverManager.getConnection(loginInfo.getUrl(), 
													   loginInfo.getUser(), 
													   loginInfo.getPwd()
													   );
	}
	
	private void Delete(JTextField jt) throws ClassNotFoundException, SQLException 
	{
		Connect();
		sql = "DELETE FROM gmao.demandeintexterne WHERE ReferenceDemande = ?;";
		ps = (PreparedStatement) con.prepareStatement(sql);
		ps.setString(1, jt.getText().trim());
		ps.executeUpdate();
		ps.close();
		con.close();
	}
	
	private boolean existanceVerif(String str) throws ClassNotFoundException, SQLException {
		verifEx = false;
		Connect();
		sqlVerif = "SELECT * FROM gmao.demandeintexterne WHERE ReferenceDemande = '" + str + "';";
		ps = (PreparedStatement) con.prepareStatement(sqlVerif);
		rs = ps.executeQuery();
		while(rs.next()) {
			if(str.trim().equals(rs.getString("ReferenceDemande")) == true) {
				return verifEx = true;
			}
		}
		return verifEx;
	}
	
	private void autoCmp(JTextField jt, String str1, String str2) {
		try {
			Connect();
			TextAutoCompleter auto = new TextAutoCompleter(jt);
			String sqlAuto = str1;
			ps = (PreparedStatement) con.prepareStatement(sqlAuto);
			rs = ps.executeQuery();
			while(rs.next()) {
				auto.addItem(rs.getString(str2));
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}