package OptionXLogin;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginWindow extends JFrame 
{
	private static final long serialVersionUID = -5575302766265319404L;
	private JPanel contentPane;
	private JTextField UserTxtField, passwordField;
	private JLabel UserLabel, PwdLabel;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sqlQuery, pwdHash, user;
	private boolean testBool;
	private JLabel backGLBL;
	private JLabel concLbl;
	
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			@Override
			public void run() 
			{
				try 
				{
					LoginWindow frame = new LoginWindow();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	public LoginWindow() 
	{	
		setTitle("Login Window");
		Image img = new ImageIcon(this.getClass().getResource("/Icons/frame.png")).getImage();
		setIconImage(img);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(0, 0, 690, 435);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		UserTxtField = new JTextField();
		UserTxtField.setBounds(253, 167, 263, 28);
		contentPane.add(UserTxtField);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(253, 245, 263, 28);
		contentPane.add(passwordField);
		
		UserLabel = new JLabel("");
		UserLabel.setHorizontalAlignment(SwingConstants.CENTER);
		Image use = new ImageIcon(this.getClass().getResource("/UI/Email.png")).getImage();
		UserLabel.setIcon(new ImageIcon(use));
		UserLabel.setBounds(114, 167, 50, 28);
		contentPane.add(UserLabel);
		
		PwdLabel = new JLabel("");
		PwdLabel.setHorizontalAlignment(SwingConstants.CENTER);
		Image pwd = new ImageIcon(this.getClass().getResource("/UI/pwd.png")).getImage();
		PwdLabel.setIcon(new ImageIcon(pwd));
		PwdLabel.setBounds(76, 245, 125, 28);
		contentPane.add(PwdLabel);
		
		concLbl = new JLabel("");
		concLbl.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if(passwordField.getText().trim().isEmpty() == false) 
				{
					try 
					{
						if(testUser(UserTxtField, passwordField)== true) 
						{
							HomeMenu hm = new HomeMenu();
							hm.setVisible(true);
							dispose();
						} 
						else if(testUser(UserTxtField, passwordField)== false) 
						{
							JOptionPane.showMessageDialog(null,"Accès refusé !");
						}
					} 
					catch (ClassNotFoundException | NoSuchAlgorithmException | UnsupportedEncodingException | SQLException e1) 
					{
						e1.printStackTrace();
					}
				} 
				else if(passwordField.getText().trim().isEmpty() == true) 
				{
					JOptionPane.showMessageDialog(null,"Veuillez insérer vos informations !");
				}
			}
		});
		
		concLbl.setHorizontalAlignment(SwingConstants.CENTER);
		Image con = new ImageIcon(this.getClass().getResource("/UI/Login/confBtn.png")).getImage();
		concLbl.setIcon(new ImageIcon(con));
		concLbl.setBounds(259, 346, 159 ,33);
		contentPane.add(concLbl);
		
		JLabel titLbl = new JLabel("");
		titLbl.setFont(new Font("Tahoma", Font.PLAIN, 19));
		titLbl.setForeground(new Color(0, 0, 0));
		Image tit = new ImageIcon(this.getClass().getResource("/UI/Autho.png")).getImage();
		titLbl.setIcon(new ImageIcon(tit));
		titLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titLbl.setBounds(253, 66, 203, 35);
		contentPane.add(titLbl);
		
		backGLBL = new JLabel("");
		backGLBL.setHorizontalAlignment(SwingConstants.CENTER);
		Image backG = new ImageIcon(this.getClass().getResource("/UI/Login/uback.png")).getImage();
		backGLBL.setIcon(new ImageIcon(backG));
		backGLBL.setBounds(0, 0, 674, 396);
		contentPane.add(backGLBL);
	}
	
	private boolean testUser(JTextField jt, JTextField jp) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException, UnsupportedEncodingException 
	{
		testBool = false;
		Connect();
		pwdHash = sha256(passwordField.getText());
		sqlQuery = "SELECT * FROM gmao.users WHERE Email =? AND Pwd= '" + pwdHash + "';";
		ps = con.prepareStatement(sqlQuery);
		ps.setString(1, jt.getText());
		rs = ps.executeQuery();
		while(rs.next()) 
		{
			user = UserTxtField.getText();
			if(user.equals(rs.getString("Email"))) 
			{
				if(pwdHash.equals(rs.getString("Pwd")) == true) 
				{
					JOptionPane.showMessageDialog(null,"Vos avez accès à votre compte !");
					return testBool = true;
				} 
				else if(pwdHash.equals(rs.getString("Pwd")) == false)
				{
					JOptionPane.showMessageDialog(null,"Accès refusé !");
				}
			}
		}
		rs.close();
		con.close();
		ps.close();
		return testBool;
	}
	
	private void Connect() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(loginInfo.getUrl(), loginInfo.getUser(), loginInfo.getPwd());
	}
	
    private static String convertByteArrayToHexString(byte[] arrayBytes) 
    {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < arrayBytes.length; i++)
        {
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