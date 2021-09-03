package Filters;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import EditMultipleObjects.RoundJButton;
import EditMultipleObjects.RoundJTextField;
import OptionXLogin.loginInfo;

public class Service_Conc extends JFrame 
{
	private static final long serialVersionUID = -125181154039342910L;
	private JPanel contentPane;
	private Connection con;
	private PreparedStatement ps;
	private JTextField servField;

	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			@Override
			public void run() 
			{
				try 
				{
					Service_Conc frame = new Service_Conc();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	public Service_Conc() 
	{
		setTitle("Option d'insertion du Service concern\u00E9");
		setType(Type.POPUP);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new RoundJButton("Confirmer");
		btnNewButton.setBounds(159, 193, 105, 37);
		btnNewButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					add(servField);
				} 
				catch(Exception e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnNewButton);
		
		servField = new RoundJTextField(20);
		servField.setHorizontalAlignment(SwingConstants.CENTER);
		servField.setBounds(176, 121, 219, 20);
		servField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnNewButton.doClick();
				}
			}
		});
		contentPane.add(servField);
		servField.setColumns(10);
		
		JLabel servLbl = new JLabel("Service concern\u00E9e");
		servLbl.setHorizontalAlignment(SwingConstants.CENTER);
		servLbl.setBounds(25, 123, 113, 17);
		contentPane.add(servLbl);
		
		JLabel lblNewLabel_1 = new JLabel("Insertion du Service concern\u00E9");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(81, 28, 296, 37);
		contentPane.add(lblNewLabel_1);
	}
	
	private void Connect() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(loginInfo.getUrl(), 
													   loginInfo.getUser(), 	
													   loginInfo.getPwd()	
													   );
	}
	
	private void add(JTextField jt) throws ClassNotFoundException, SQLException
	{
		Connect();
		String sql = "INSERT INTO gmao.serviceConcFilter (NomServ) Values(?);";
		ps = con.prepareStatement(sql);
		ps.setString(1, jt.getText().trim());
		ps.executeUpdate();
		ps.close();
		con.close();
	}
}