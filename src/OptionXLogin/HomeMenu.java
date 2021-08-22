package OptionXLogin;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.sql.PreparedStatement;
import Filters.Help;
import Filters.ModifAuthority;
import NormOptionFrameCommandePRechange.CommandePRechange;
import NormOptionFrameDemandIntervExt.DemandeIntervExterne;
import NormOptionFrameRapport_Intevention.Rapport_Intervention;
import NormOptionFrameRéceptionEquipement.RéceptionEquipement;
import OptionFrameInventaire.Inventaire;
import OptionFrameMaintenance.Maintenance;
import OptionFramePlannification.Plannification;
import OptionFrameStockDeRechange.StockRechange;

public class HomeMenu extends JFrame 
{

	private static final long serialVersionUID = 3388658917754579661L;
	private JPanel contentPane;
	private JLabel HomeLabl, backGLbl, sidePLbl, lilT, discLbl, 
				   arcsLbl, maintLbl, stockLbl, cmdPLbl, nameLbl;
	private JLabel maintInt;
	private JLabel invLbl;
	private JLabel planLbl;
	private JLabel recptLbl;
	private JLabel intExtLbl;
	private JLabel avtLbl;
	private Connection con;
	private ResultSet rs;
	private PreparedStatement ps;
	private String sql;
	private JLabel authManag;
	
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try
				{
					HomeMenu frame = new HomeMenu();
					frame.setVisible(true);
				} 
					catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	public HomeMenu() 
	{
		Image img = new ImageIcon(this.getClass().getResource("/Icons/frame.png")).getImage();
		setIconImage(img);
		setType(Type.POPUP);
		setTitle("Home Menu");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		HomeLabl = new JLabel("");
		HomeLabl.setBounds(1254, 21, 96, 96);
		HomeLabl.setHorizontalAlignment(SwingConstants.CENTER);
		Image help = new ImageIcon(this.getClass().getResource("/Home/qstmrq.png")).getImage();
		HomeLabl.setIcon(new ImageIcon(help));
		HomeLabl.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				Help hl = new Help();
				hl.setVisible(true);
			}
		});
		contentPane.add(HomeLabl);
		
		discLbl = new JLabel("");
		discLbl.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				LoginWindow lw = new LoginWindow();
				lw.setVisible(true);	
				dispose();
			}
		});
		
		maintLbl = new JLabel("");
		maintLbl.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				Maintenance mtn = new Maintenance();
				mtn.setVisible(true);
				dispose();
			}
		});
		
		avtLbl = new JLabel("");
		avtLbl.setBounds(64, 21, 186, 215);
		Image avt = new ImageIcon(this.getClass().getResource("/Home/avatar.png")).getImage();
		avtLbl.setIcon(new ImageIcon(avt));
		contentPane.add(avtLbl);
		
		lilT = new JLabel("");
		lilT.setBounds(1, 227, 360, 50);
		Image lil = new ImageIcon(this.getClass().getResource("/Home/lilthingi.png")).getImage();
		lilT.setIcon(new ImageIcon(lil));
		contentPane.add(lilT);
		
		sidePLbl = new JLabel("");
		Image side = new ImageIcon(this.getClass().getResource("/Home/handler.png")).getImage();
		sidePLbl.setBounds(2, 0, 316, 768);
		sidePLbl.setIcon(new ImageIcon(side));
		contentPane.add(sidePLbl);
		
		maintLbl.setBounds(430, 114, 166, 172);
		Image maint = new ImageIcon(this.getClass().getResource("/Home/maint.png")).getImage();
		maintLbl.setIcon(new ImageIcon(maint));
		contentPane.add(maintLbl);
		
		arcsLbl = new JLabel("");
		arcsLbl.setBounds(221, 374, 1145, 354);
		Image arc = new ImageIcon(this.getClass().getResource("/Home/arcs.png")).getImage();
		arcsLbl.setIcon(new ImageIcon(arc));
		contentPane.add(arcsLbl);
		
		cmdPLbl = new JLabel("");
		cmdPLbl.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				CommandePRechange cmd = new CommandePRechange();
				cmd.setVisible(true);
				dispose();
			}
		});
		
		cmdPLbl.setBounds(848, 114, 120, 172);
		Image cmdP = new ImageIcon(this.getClass().getResource("/Home/cmdp.png")).getImage();
		cmdPLbl.setIcon(new ImageIcon(cmdP));
		contentPane.add(cmdPLbl);
		
		
		discLbl.setBounds(324, 21, 96, 96);
		Image disc = new ImageIcon(this.getClass().getResource("/Home/logout.png")).getImage();
		discLbl.setIcon(new ImageIcon(disc));
		contentPane.add(discLbl);
		
		stockLbl = new JLabel("");
		stockLbl.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				StockRechange stk = new StockRechange();
				stk.setVisible(true);
				dispose();
			}
		});
		stockLbl.setBounds(639, 114, 166, 172);
		Image stock = new ImageIcon(this.getClass().getResource("/Home/stock.png")).getImage();
		stockLbl.setIcon(new ImageIcon(stock));
		contentPane.add(stockLbl);
		
		invLbl = new JLabel("");
		invLbl.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				Inventaire inv = new Inventaire();
				inv.setVisible(true);
				dispose();
			}
		});
		invLbl.setBounds(430, 295, 166, 172);
		Image inv = new ImageIcon(this.getClass().getResource("/Home/inv.png")).getImage();
		invLbl.setIcon(new ImageIcon(inv));
		contentPane.add(invLbl);
		
		maintInt = new JLabel("");
		maintInt.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				Rapport_Intervention rp = new Rapport_Intervention();
				rp.setVisible(true);
				dispose();
			}
		});
		
		recptLbl = new JLabel("");
		recptLbl.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				RéceptionEquipement eq = new RéceptionEquipement();
				eq.setVisible(true);
				dispose();
			}
		});
		recptLbl.setBounds(848, 295, 166, 172);
		Image rec = new ImageIcon(this.getClass().getResource("/Home/recept.png")).getImage();
		recptLbl.setIcon(new ImageIcon(rec));
		contentPane.add(recptLbl);	
		
		intExtLbl = new JLabel("");
		intExtLbl.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				DemandeIntervExterne dme = new DemandeIntervExterne();
				dme.setVisible(true);
				dispose();
			}
		});
		
		intExtLbl.setBounds(1057, 295, 166, 172);
		Image inExt = new ImageIcon(this.getClass().getResource("/Home/intext.png")).getImage();
		intExtLbl.setIcon(new ImageIcon(inExt));
		contentPane.add(intExtLbl);
		
		planLbl = new JLabel("");
		planLbl.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				Plannification pln = new Plannification();
				pln.setVisible(true);
				dispose();
			}
		});
		
		planLbl.setBounds(639, 295, 166, 172);
		Image plan = new ImageIcon(this.getClass().getResource("/Home/plannification.png")).getImage();
		planLbl.setIcon(new ImageIcon(plan));
		contentPane.add(planLbl);
		
		maintInt.setBounds(1057, 114, 166, 172);
		Image mantIntIc = new ImageIcon(this.getClass().getResource("/Home/maintint.png")).getImage();
		maintInt.setIcon(new ImageIcon(mantIntIc));
		contentPane.add(maintInt);
		
		nameLbl = new JLabel("");
		nameLbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		nameLbl.setHorizontalAlignment(SwingConstants.CENTER);
		nameLbl.setBounds(40, 295, 225, 23);
		contentPane.add(nameLbl);
		
		authManag = new JLabel("");
		authManag.setBounds(1268, 145, 60, 56);
		authManag.addMouseListener(new MouseAdapter() 
		{

			@Override
			public void mouseClicked(MouseEvent e) 
			{
				ModifAuthority mod = new ModifAuthority();
				mod.setVisible(true);
			}
		});
		contentPane.add(authManag);
		
		backGLbl = new JLabel("");
		backGLbl.setBounds(0, 0, 1366, 768);
		Image back = new ImageIcon(this.getClass().getResource("/UI/HomeMenu/back.png")).getImage();	
		backGLbl.setIcon(new ImageIcon(back));
		contentPane.add(backGLbl);	
		
		try 
		{
			AddUserInfo();	
		} 
			catch(Exception e1) 
		{
			e1.printStackTrace();
		}
	}
	
	private void Connect() throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.mysql.jdbc.Driver");
		con = (Connection) DriverManager.getConnection(
				loginInfo.getUrl(), 
				loginInfo.getUser(), 
				loginInfo.getPwd()
			);
	}
	
	private void AddUserInfo() throws ClassNotFoundException, SQLException
	{
		Connect();
		sql = "select Name, FName, Role, userType from gmao.users WHERE idUser = 1;";
		ps = (PreparedStatement) con.prepareStatement(sql);
		rs = ps.executeQuery();
		if(rs.next())
			nameLbl.setText(rs.getString("Name"));
		con.close();
		ps.close();
		rs.close();
	}
}