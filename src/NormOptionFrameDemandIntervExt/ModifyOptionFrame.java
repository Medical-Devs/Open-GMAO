package NormOptionFrameDemandIntervExt;

import java.awt.EventQueue;
import java.awt.Font;
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
import Notifications.emptyFieldsMessages;
import OptionXLogin.loginInfo;

public class ModifyOptionFrame extends JFrame 
{
	private static final long serialVersionUID = 1808841131909091097L;
	private JPanel contentPane;
	private RoundJTextField typeApField, servField, mrqField, modField, 
							fournField, nSerField, typeIntField, refDmdField, datField, 
							numEnrField;
	private JLabel titLbl, typeAppLbl, servLbl, mrqLbl, modLbl, nSerLbl, typeIntLbl, 
				   refDmdLbl,fournLbl, dateLbl, nEnrgLbl;
	private String sql;
	private RoundJButton modBtn;
	private Connection con;
	private PreparedStatement ps;

	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					ModifyOptionFrame frame = new ModifyOptionFrame();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	public ModifyOptionFrame() 
	{
		setResizable(false);
		setTitle("Modifying Option");
		setType(Type.POPUP);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 665, 479);
		setLocationRelativeTo(null);
		contentPane = new JPanel();

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		titLbl = new JLabel("Modifying Option");
		titLbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		titLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titLbl.setBounds(254, 11, 184, 42);
		contentPane.add(titLbl);
		
		typeAppLbl = new JLabel("Type d'appareil");
		typeAppLbl.setHorizontalAlignment(SwingConstants.CENTER);
		typeAppLbl.setBounds(50, 78, 119, 28);
		contentPane.add(typeAppLbl);
		
		typeApField = new RoundJTextField(20);
		typeApField.setBounds(203, 78, 161, 23);
		contentPane.add(typeApField);
		typeApField.setColumns(10);
		
		servField = new RoundJTextField(20);
		servField.setColumns(10);
		servField.setBounds(203, 117, 161, 23);
		contentPane.add(servField);
		
		servLbl = new JLabel("Service");
		servLbl.setHorizontalAlignment(SwingConstants.CENTER);
		servLbl.setBounds(50, 117, 119, 28);
		contentPane.add(servLbl);
		
		mrqField = new RoundJTextField(20);
		mrqField.setColumns(10);
		mrqField.setBounds(203, 154, 161, 23);
		contentPane.add(mrqField);
		
		mrqLbl = new JLabel("Marque");
		mrqLbl.setHorizontalAlignment(SwingConstants.CENTER);
		mrqLbl.setBounds(50, 154, 119, 28);
		contentPane.add(mrqLbl);
		
		modField = new RoundJTextField(20);;
		modField.setColumns(10);
		modField.setBounds(203, 191, 161, 23);
		contentPane.add(modField);
		
		modLbl = new JLabel("Mod\u00E8le");
		modLbl.setHorizontalAlignment(SwingConstants.CENTER);
		modLbl.setBounds(50, 191, 119, 28);
		contentPane.add(modLbl);
		
		fournField = new RoundJTextField(20);
		fournField.setColumns(10);
		fournField.setBounds(203, 228, 161, 23);
		contentPane.add(fournField);
		
		fournLbl = new JLabel("Fournisseur");
		fournLbl.setHorizontalAlignment(SwingConstants.CENTER);
		fournLbl.setBounds(50, 228, 119, 28);
		contentPane.add(fournLbl);
		
		nSerField = new RoundJTextField(20);
		nSerField.setColumns(10);
		nSerField.setBounds(203, 298, 161, 23);
		contentPane.add(nSerField);
		
		nSerLbl = new JLabel("Num\u00E9ro de Serie");
		nSerLbl.setHorizontalAlignment(SwingConstants.CENTER);
		nSerLbl.setBounds(50, 295, 119, 28);
		contentPane.add(nSerLbl);
		
		typeIntField = new RoundJTextField(20);
		typeIntField.setColumns(10);
		typeIntField.setBounds(203, 333, 161, 23);
		contentPane.add(typeIntField);
		
		typeIntLbl = new JLabel("Type d'intervention");
		typeIntLbl.setHorizontalAlignment(SwingConstants.CENTER);
		typeIntLbl.setBounds(50, 334, 119, 28);
		contentPane.add(typeIntLbl);
		
		refDmdField = new RoundJTextField(20);
		refDmdField.setColumns(10);
		autoCmp(refDmdField, "Select ReferenceDemande from gmao.demandeintexterne;", "ReferenceDemande");
		refDmdField.setBounds(441, 243, 161, 23);
		contentPane.add(refDmdField);
		
		refDmdLbl = new JLabel("R\u00E9ference de la Demande");
		refDmdLbl.setHorizontalAlignment(SwingConstants.CENTER);
		refDmdLbl.setBounds(441, 213, 169, 28);
		contentPane.add(refDmdLbl);
		
		datField = new RoundJTextField(20);
		datField.setColumns(10);
		datField.setBounds(203, 370, 161, 23);
		contentPane.add(datField);
		
		dateLbl = new JLabel("Date d'intervention");
		dateLbl.setHorizontalAlignment(SwingConstants.CENTER);
		dateLbl.setBounds(65, 367, 119, 28);
		contentPane.add(dateLbl);
		
		numEnrField = new RoundJTextField(20);
		numEnrField.setColumns(10);
		numEnrField.setBounds(203, 264, 161, 23);
		contentPane.add(numEnrField);
		
		nEnrgLbl = new JLabel("Num\u00E9ro d'enregistrement");
		nEnrgLbl.setHorizontalAlignment(SwingConstants.CENTER);
		nEnrgLbl.setBounds(23, 264, 161, 28);
		contentPane.add(nEnrgLbl);
		
		modBtn = new RoundJButton("Modify");
		modBtn.setBounds(451, 282, 138, 42);
		modBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					if(verifField(refDmdField) == true) 
					{
						if(existanceVerif(refDmdField) == true) 
						{
							if(typeApField.isEnabled() == true) 
							{
								if(verifField(typeApField) == true) 
								{
									Modify("TypeAppareil", typeApField, refDmdField);
								} 
								else if(verifField(typeApField) == false) 
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.typeApFieldMsg);
								}	
							}
							
							if(servField.isEnabled() == true) 
							{
								if(verifField(servField) == true) 
								{
									Modify("Service", servField, refDmdField);
								} 
								else if(verifField(servField) == false) 
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.serviceFieldMsg);
								}
							}
						
							if(mrqField.isEnabled() == true) 
							{
								if(verifField(mrqField) == true) 
								{
									Modify("Marque", mrqField, refDmdField);
								} 
								else if(verifField(mrqField) == false) 
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.mrqFieldMsg);
								}
							}
								
							if(modField.isEnabled() == true) 
							{
								if(verifField(modField) == true) 
								{
									Modify("Modele", modField, refDmdField);
								} 
								else if(verifField(modField) == false) 
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.modFildMsg);
								}
							}
								
							if(fournField.isEnabled() == true) 
							{
								if(verifField(fournField) == true) 
								{
									Modify("Fournisseur", fournField, refDmdField);
								} 
								else if(verifField(fournField) == false) 
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.fournFieldMsg);
								}
							}
							
							if(datField.isEnabled() == true) 
							{
								if(verifField(datField) == true) {
									Modify("Date", datField, refDmdField);
								} 
								else if(verifField(datField) == false) 
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.dateIntFieldMsg);
								}
							}
								
							if(typeIntField.isEnabled() == true) 
							{
								if(verifField(typeIntField) == true) 
								{
									Modify("TypeIntervention", typeIntField, refDmdField);
								} 
								else if(verifField(typeIntField) == false) 
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.typeIntFieldMsg);
								}
							}
								
							if(nSerField.isEnabled() == true) 
							{
								if(verifField(nSerField) == true) 
								{
									Modify("NSerie", nSerField, refDmdField);
								} 
								else if(verifField(nSerField) == false) 
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.nSerFieldMsg);
								}
							}	
							
							if(numEnrField.isEnabled() == true) 
							{
								if(verifField(numEnrField) == true) 
								{
									Modify("NEnregistrement", numEnrField, refDmdField);
								} 
								else if(verifField(numEnrField) == false) 
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.numEnrFieldMsg);
								}
							}	
						} 
						else if(existanceVerif(refDmdField) == false) 
						{
							JOptionPane.showMessageDialog(null, MessageManager.itemRefDmdNotFound);
						} 
					} 
					else if(verifField(refDmdField) == false) 
					{
					JOptionPane.showMessageDialog(null, emptyFieldsMessages.refDmdMsg);
					}
				} 
				catch (ClassNotFoundException | SQLException e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(modBtn);
	}

	private void Connect() throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.mysql.jdbc.Driver");
		con = (Connection) DriverManager.getConnection(loginInfo.getUrl(), 
													   loginInfo.getUser(), 
													   loginInfo.getPwd()
													   );
	}
	
	private void Modify(String str, JTextField jt, JTextField jts) throws ClassNotFoundException, SQLException
	{
		Connect();
		sql = "UPDATE gmao.demandeintexterne SET " + str + " = ? WHERE ReferenceDemande = ?";
		ps = (PreparedStatement) con.prepareStatement(sql);
		ps.setString(1, jt.getText());
		ps.setString(2, jts.getText());
		ps.executeUpdate();
		ps.close();
		con.close();
	}
	
	private boolean existanceVerif(JTextField jt) throws ClassNotFoundException, SQLException
	{
		boolean existVerif = false;
		Connect();
		String sqlVerifEx = "SELECT * FROM gmao.demandeintexterne where ReferenceDemande = ?;";
		ps = (PreparedStatement) con.prepareStatement(sqlVerifEx);
		ps.setString(1, jt.getText().trim());
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			if(jt.getText().trim().equals(rs.getString("ReferenceDemande")) == true) {
				ps.close();
				rs.close();
				con.close();
				return existVerif = true;
			}
		}
		ps.close();
		rs.close();
		con.close();
		return existVerif;
	}
	
	private boolean verifField(JTextField jt)
	{
		boolean verifField = false;
			if(jt.getText().trim().isEmpty() == false) 
			{
				return verifField = true;
			}
		return verifField;
	}
	
	private void autoCmp(JTextField jt, String str1, String str2) 
	{
		try {
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
	
	public RoundJTextField getTypeApField()  { return typeApField; }

	public RoundJTextField getMrqField() { return mrqField; }

	public RoundJTextField getModField() { return modField; }

	public RoundJTextField getFournField()  { return fournField; }

	public RoundJTextField getnSerField()  { return nSerField; }

	public RoundJTextField getTypeIntField() { return typeIntField; }

	public RoundJTextField getDatField() { return datField; }

	public RoundJTextField getNumEnrField() { return numEnrField; }

	
	public JLabel getTypeAppLbl()  { return typeAppLbl; }

	public JLabel getServLbl()  { return servLbl; }

	public JLabel getMrqLbl()  { return mrqLbl; }

	public JLabel getModLbl()  { return modLbl; }

	public JLabel getnSerLbl()  { return nSerLbl; }

	public JLabel getTypeIntLbl()  { return typeIntLbl; }

	public JLabel getFournLbl() { return fournLbl; }

	public JLabel getDateLbl() { return dateLbl; }

	public JLabel getnEnrgLbl() { return nEnrgLbl; }

	
	public RoundJTextField getServField() { return servField; }
}