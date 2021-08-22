package Filters;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.sql.PreparedStatement;
import EditMultipleObjects.WindowSize;
import OptionXLogin.loginInfo;

public class ModifAuthority extends JFrame 
{
	private static final long serialVersionUID = -6753258969232993202L;
	private JPanel contentPane;
	private JTextField textField;
	private String[] types = {"Modérateur", "Utilisateur"};
	private int width = 666, height = 316;
	private JLabel titleLbl, nduLbl;
	private Connection con;
	private PreparedStatement ps;
	private String sql;
	private JComboBox<String> comboBox;
	private String str;
	
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					ModifAuthority frame = new ModifAuthority();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	public ModifAuthority() 
	{
		setTitle("Modification de l'authorit\u00E9");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setBounds(WindowSize.widthScreen / 2 - width / 2 , 
				  WindowSize.heightScreen / 2 - height / 2 , 
				  width, 
				  height
				  );
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		nduLbl = new JLabel("Nom de l'utilisateur");
		nduLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nduLbl.setHorizontalAlignment(SwingConstants.CENTER);
		nduLbl.setBounds(30, 120, 143, 27);
		contentPane.add(nduLbl);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(183, 124, 262, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton confBtn = new JButton("Confirmer");
		confBtn.setBounds(269, 210, 124, 35);
		confBtn.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					changeAuth(textField);
				} 
				catch(Exception e1) 
				{
					e1.printStackTrace();
				}
			}			
		});
		contentPane.add(confBtn);
		
		titleLbl = new JLabel("Modification de l'authorit\u00E9");
		titleLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setBounds(241, 27, 230, 35);
		contentPane.add(titleLbl);
		
		comboBox = new JComboBox<String>(types);
		comboBox.setMaximumRowCount(2);
		comboBox.setBounds(471, 123, 133, 22);
		contentPane.add(comboBox);
	}
	
	private void Connect() throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.mysql.jdbc.Driver");
		con = (Connection) DriverManager.getConnection(loginInfo.getUrl(), 
													   loginInfo.getUser(), 
													   loginInfo.getPwd()
													   );
	}
	
	private void changeAuth(JTextField jt) throws SQLException, ClassNotFoundException 
	{
		Connect();
		str = (String) comboBox.getSelectedItem();
		
		if(str == "Modérateur") {
			str = "Admin";
		} 
		else if(str == "Utilisateur") 
		{
			str = "User";
		}
		
		sql = "Update gmao.users Set userType = '" + str + "' Where Name = '" + jt.getText().trim() + "';";
		ps = (PreparedStatement) con.prepareStatement(sql);
		ps.executeUpdate();
		con.close();
		ps.close();
	}
}