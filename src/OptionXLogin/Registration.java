package OptionXLogin;

import java.awt.EventQueue;
import java.awt.Image;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;

public class Registration extends JFrame {

	private static final long serialVersionUID = 9098386037717698878L;
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField UserTxtField;
	private String sql, pwdHash;
	private Connection con;
	private PreparedStatement ps;
	private JLabel bckUiLbl;
	private JLabel authLbl;
	private JLabel emailLbl;
	private JTextField roleField;
	private JLabel roleLbl;
	private JLabel pNameLbl;
	private JTextField pNameField;
	private JTextField nameField;
	private JLabel nameLbl;
	private JLabel pwdLbl;

	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Registration frame = new Registration();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	public Registration() 
	{
		setType(Type.POPUP);
		setResizable(false);
		setTitle("Page d'enregistrement");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 690, 666);
		Image img = new ImageIcon(this.getClass().getResource("/Icons/frame.png")).getImage();
		setIconImage(img);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		authLbl = new JLabel("");
		authLbl.setHorizontalAlignment(SwingConstants.CENTER);
		Image auth = new ImageIcon(this.getClass().getResource("/UI/enrg.png")).getImage();
		authLbl.setIcon(new ImageIcon(auth));
		authLbl.setBounds(257, 78, 203, 35);
		contentPane.add(authLbl);
		
		roleLbl = new JLabel("R\u00F4le");
		roleLbl.setForeground(Color.WHITE);
		roleLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		roleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		roleLbl.setBounds(97, 332, 125, 28);
		contentPane.add(roleLbl);
		
		emailLbl = new JLabel("Email");
		emailLbl.setForeground(Color.WHITE);
		emailLbl.setHorizontalAlignment(SwingConstants.CENTER);
		emailLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		emailLbl.setBounds(109, 406, 102, 30);
		contentPane.add(emailLbl);
		
		pNameLbl = new JLabel("Pr\u00E9nom");
		pNameLbl.setForeground(Color.WHITE);
		pNameLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pNameLbl.setHorizontalAlignment(SwingConstants.CENTER);
		pNameLbl.setBounds(97, 249, 125, 30);
		contentPane.add(pNameLbl);
		
		nameLbl = new JLabel("Nom");
		nameLbl.setForeground(Color.WHITE);
		nameLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		nameLbl.setHorizontalAlignment(SwingConstants.CENTER);
		nameLbl.setBounds(97, 170, 125, 30);
		contentPane.add(nameLbl);
		
		pwdLbl = new JLabel("Mot de passe");
		pwdLbl.setForeground(Color.WHITE);
		pwdLbl.setHorizontalAlignment(SwingConstants.CENTER);
		pwdLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pwdLbl.setBounds(67, 478, 178, 30);
		contentPane.add(pwdLbl);
		
		roleField = new JTextField();
		roleField.setBounds(274, 332, 263, 30);
		contentPane.add(roleField);
		
		pNameField = new JTextField();
		pNameField.setBounds(274, 249, 263, 30);
		contentPane.add(pNameField);
		
		nameField = new JTextField();
		nameField.setBounds(274, 170, 263, 30);
		contentPane.add(nameField);
		
		UserTxtField = new JTextField();
		UserTxtField.setBounds(274, 409, 263, 30);
		contentPane.add(UserTxtField);
		
		passwordField = new JPasswordField();		
		passwordField.setBounds(274, 481, 263, 30);
		contentPane.add(passwordField);
		
		JLabel registerLbl = new JLabel("");
		registerLbl.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				try 
				{
					addUser(nameField, pNameField, roleField, UserTxtField, passwordField);
					JOptionPane.showMessageDialog(null,"Votre compte a été crée !");
					LoginWindow lw = new LoginWindow();
					lw.setVisible(true);
					dispose();
				} 
				catch(Exception e2) 
				{
					e2.printStackTrace();
				}
			}
		});
		
		registerLbl.setBounds(262, 560, 159, 33);
		Image reg = new ImageIcon(this.getClass().getResource("/UI/Login/confBtn.png")).getImage();
		registerLbl.setIcon(new ImageIcon(reg));
		contentPane.add(registerLbl);
		
		bckUiLbl = new JLabel("");
		bckUiLbl.setBounds(0, 0, 684, 406);
		Image backG = new ImageIcon(this.getClass().getResource("/UI/Login/uback.png")).getImage();
		bckUiLbl.setIcon(new ImageIcon(backG));
		contentPane.add(bckUiLbl);
	}
	
	protected void Connect() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		con = (Connection) DriverManager.getConnection(loginInfo.getUrl(), loginInfo.getUser(), loginInfo.getPwd());
	}
	
	@SuppressWarnings("deprecation")
	private void addUser(JTextField jt2, JTextField jt3, JTextField jt4, JTextField jt, JPasswordField jpwd) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException, UnsupportedEncodingException 
	{
		Connect();
		pwdHash = sha256(passwordField.getText()); 
		sql = "INSERT INTO gmao.users (Email, Pwd, Name, FName, Role) Values (?, '" + pwdHash + "', ?, ?, ?);";
		ps = (PreparedStatement) con.prepareStatement(sql);
		ps.setString(1, jt.getText().trim());
		ps.setString(2, jt2.getText());
		ps.setString(3, jt3.getText());
		ps.setString(4, jt4.getText());
		ps.executeUpdate();
	}
	
    private static String convertByteArrayToHexString(byte[] arrayBytes) 
    {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < arrayBytes.length; i++) {
            stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return stringBuffer.toString();
    }
    
    private static String sha256(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(input.getBytes(StandardCharsets.UTF_8));
        return convertByteArrayToHexString(hash);
    }
}