package OptionXLogin;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class selectionLogin extends JFrame 
{
	private static final long serialVersionUID = 15955949L;
	private JPanel contentPane;
	private JLabel accLbl;
	private JLabel bienvLbl;

	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			@Override
			public void run() 
			{
				try 
				{
					selectionLogin frame = new selectionLogin();
					frame.setVisible(true);
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	public selectionLogin() throws InterruptedException
	{
		setType(Type.POPUP);
		setTitle("Page d'acceuil");
		Image img = new ImageIcon(this.getClass().getResource("/Icons/frame.png")).getImage();
		setIconImage(img);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 435);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel loginLbl = new JLabel("");
		loginLbl.setHorizontalAlignment(SwingConstants.CENTER);
		loginLbl.setBounds(265, 219, 165, 41);
		Image logBtn = new ImageIcon(this.getClass().getResource("/UI/Login_in.png")).getImage();
		loginLbl.setIcon(new ImageIcon(logBtn));
		contentPane.add(loginLbl);
		loginLbl.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				LoginWindow lw = new LoginWindow();
				lw.setVisible(true);
				dispose();
			}
		});
		
		
		bienvLbl = new JLabel("");
		bienvLbl.setHorizontalAlignment(SwingConstants.CENTER);
		bienvLbl.setBounds(283, 79, 127, 30);
		Image bien = new ImageIcon(this.getClass().getResource("/UI/Bienv.png")).getImage();
		bienvLbl.setIcon(new ImageIcon(bien));
		contentPane.add(bienvLbl);
		
		accLbl = new JLabel("");
		accLbl.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				Registration reg = new Registration();
		        reg.setVisible(true);
		        dispose();
			}
		});
		accLbl.setHorizontalAlignment(SwingConstants.CENTER);
		accLbl.setBounds(265, 299, 165, 41);
		Image cr = new ImageIcon(this.getClass().getResource("/UI/Create.png")).getImage();
		accLbl.setIcon(new ImageIcon(cr));
		contentPane.add(accLbl);
		
		JLabel uiLblBck = new JLabel("");
		uiLblBck.setBounds(0, 0, 684, 406);
		Image uiBackGround = new ImageIcon(this.getClass().getResource("/UI/Login/uback.png")).getImage();
		uiLblBck.setIcon(new ImageIcon(uiBackGround));
		contentPane.add(uiLblBck);
	}
}