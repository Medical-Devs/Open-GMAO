package NormOptionFrameRéceptionEquipement;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import com.mxrck.autocompleter.TextAutoCompleter;
import EditMultipleObjects.RoundJTextField;
import Notifications.MessageManager;
import Notifications.emptyFieldsMessages;
import OptionXLogin.loginInfo;

public class ModifyOptions extends JFrame 
{
	private static final long serialVersionUID = -1083894906971494587L;
	private JPanel contentPane;
	private String sql;
	private Connection con;
	private PreparedStatement ps;
	private RoundJTextField servField, fournField, typeApField, fabField, nSerieField, desField, 
							motRecField, dateRecField, admField, aspColField, classMedField, nomRecField;
	private JLabel servLbl, fournLbl ,typeApLbl, nSerieLbl, fabLbl, desLbl, motifRecLbl, dateRecepLbl, admLbl, aspecColLbl, classMedLbl, nomRecLbl;
	private JButton modBtn;

	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			@Override
			public void run() 
			{
				try 
				{
					ModifyOptions frame = new ModifyOptions();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	public ModifyOptions() 
	{
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 710, 542);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titLbl = new JLabel("Adding Options");
		titLbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		titLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titLbl.setBounds(289, 22, 139, 36);
		contentPane.add(titLbl);
		
		servLbl = new JLabel("Service");
		servLbl.setHorizontalAlignment(SwingConstants.CENTER);
		servLbl.setBounds(24, 102, 107, 28);
		contentPane.add(servLbl);
		
		servField = new RoundJTextField(20);
		servField.setBounds(160, 106, 156, 20);
		contentPane.add(servField);
		servField.setColumns(10);
		
		fournLbl = new JLabel("Fournisseur");
		fournLbl.setHorizontalAlignment(SwingConstants.CENTER);
		fournLbl.setBounds(24, 141, 107, 20);
		contentPane.add(fournLbl);
		
		fournField = new RoundJTextField(20);
		fournField.setBounds(160, 141, 156, 20);
		contentPane.add(fournField);
		fournField.setColumns(10);
		
		typeApField = new RoundJTextField(20);
		typeApField.setColumns(10);
		typeApField.setBounds(160, 211, 156, 20);
		contentPane.add(typeApField);
		
		typeApLbl = new JLabel("Type d'appareil");
		typeApLbl.setHorizontalAlignment(SwingConstants.CENTER);
		typeApLbl.setBounds(24, 211, 107, 20);
		contentPane.add(typeApLbl);
		
		fabLbl = new JLabel("Fabricant");
		fabLbl.setHorizontalAlignment(SwingConstants.CENTER);
		fabLbl.setBounds(24, 172, 107, 28);
		contentPane.add(fabLbl);
		
		fabField = new RoundJTextField(20);
		fabField.setColumns(10);
		fabField.setBounds(160, 176, 156, 20);
		contentPane.add(fabField);
		
		nSerieField = new RoundJTextField(20);
		nSerieField.setColumns(10);
		nSerieField.setBounds(477, 263, 156, 20);
		autoCmp(nSerieField, "Select NSerie from gmao.receptionequipement;", "NSerie");
		contentPane.add(nSerieField);
		
		nSerieLbl = new JLabel("Num\u00E9ro de serie");
		nSerieLbl.setHorizontalAlignment(SwingConstants.CENTER);
		nSerieLbl.setBounds(371, 263, 107, 20);
		contentPane.add(nSerieLbl);
		
		desLbl = new JLabel("D\u00E9signation");
		desLbl.setHorizontalAlignment(SwingConstants.CENTER);
		desLbl.setBounds(24, 242, 107, 28);
		contentPane.add(desLbl);
		
		desField = new RoundJTextField(20);
		desField.setColumns(10);
		desField.setBounds(160, 246, 156, 20);
		contentPane.add(desField);
		
		motRecField = new RoundJTextField(20);
		motRecField.setColumns(10);
		motRecField.setBounds(160, 320, 156, 20);
		contentPane.add(motRecField);
		
		motifRecLbl = new JLabel("Motif de r\u00E9ception");
		motifRecLbl.setHorizontalAlignment(SwingConstants.CENTER);
		motifRecLbl.setBounds(24, 320, 107, 20);
		contentPane.add(motifRecLbl);
		
		dateRecepLbl = new JLabel("Date de r\u00E9ception");
		dateRecepLbl.setHorizontalAlignment(SwingConstants.CENTER);
		dateRecepLbl.setBounds(24, 281, 107, 28);
		contentPane.add(dateRecepLbl);
		
		dateRecField = new RoundJTextField(20);
		dateRecField.setColumns(10);
		dateRecField.setBounds(160, 285, 156, 20);
		contentPane.add(dateRecField);
		
		admField = new RoundJTextField(20);
		admField.setColumns(10);
		admField.setBounds(160, 390, 156, 20);
		contentPane.add(admField);
		
		admLbl = new JLabel("Admission");
		admLbl.setHorizontalAlignment(SwingConstants.CENTER);
		admLbl.setBounds(24, 390, 107, 20);
		contentPane.add(admLbl);
		
		aspecColLbl = new JLabel("Aspect du colis");
		aspecColLbl.setHorizontalAlignment(SwingConstants.CENTER);
		aspecColLbl.setBounds(24, 351, 107, 28);
		contentPane.add(aspecColLbl);
		
		aspColField = new RoundJTextField(20);
		aspColField.setColumns(10);
		aspColField.setBounds(160, 355, 156, 20);
		contentPane.add(aspColField);
		
		classMedField = new RoundJTextField(20);
		classMedField.setColumns(10);
		classMedField.setBounds(160, 460, 156, 20);
		contentPane.add(classMedField);
		
		classMedLbl = new JLabel("Classification M\u00E9dcial");
		classMedLbl.setHorizontalAlignment(SwingConstants.CENTER);
		classMedLbl.setBounds(24, 460, 107, 20);
		contentPane.add(classMedLbl);
		
		nomRecLbl = new JLabel("Nom du r\u00E9ceptionniste");
		nomRecLbl.setHorizontalAlignment(SwingConstants.CENTER);
		nomRecLbl.setBounds(10, 421, 121, 28);
		contentPane.add(nomRecLbl);
		
		nomRecField = new RoundJTextField(20);
		nomRecField.setColumns(10);
		nomRecField.setBounds(160, 425, 156, 20);
		contentPane.add(nomRecField);
		
		modBtn = new JButton("Modifier");
		modBtn.setBounds(487, 312, 139, 36);
		modBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{	
				try 
				{
					if(verifField(nSerieField) == true) 
					{
						if(existanceVerif(nSerieField) == true) 
						{						
							if(servField.isEnabled() == true) 
							{
								if(verifField(servField) == true) 
								{
									Modify("Service", servField, nSerieField);
								} 
								else if(verifField(servField) == false) 
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.serviceFieldMsg);
								}
							}
							
							if(fournField.isEnabled() == true) 
							{
								if(verifField(fournField) == true) 
								{
									Modify("Fournisseur", fournField, nSerieField);
								} 
								else if(verifField(fournField) == false) 
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.fournFieldMsg); 
								}
							}
							
							if(fabField.isEnabled() == true) 
							{
								if(verifField(fabField) == true) 
								{
									Modify("Fabricant", fabField, nSerieField);
								} 
								else if(verifField(fabField) == false) 
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.fabFieldMsg);
								}
							}
							
							if(typeApField.isEnabled() == true) 
							{
								if(verifField(typeApField) == true) 
								{
									Modify("TypeApp", typeApField, nSerieField);
								} 
								else if(verifField(typeApField) == false) 
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.typeApFieldMsg);
								}
							}
							
							if(desField.isEnabled() == true) 
							{
								if(verifField(desField) == true) 
								{
									Modify("Designation", desField, nSerieField);
								} 
								else if(verifField(desField) == false) 
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.desFieldMsg);
								}
							}
							
							if(dateRecField.isEnabled() == true) 
							{
								if(verifField(dateRecField) == true) 
								{
									Modify("DateRecep", dateRecField, nSerieField);
								} 
								else if(verifField(dateRecField) == false) 
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.dateRecMsg);
								}
							}
							
							if(motRecField.isEnabled() == true) 
							{
								if(verifField(motRecField) == true) 
								{
									Modify("MotifRecep", motRecField, nSerieField);
								} 
								else if(verifField(motRecField) == false) 
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.motifMsg);
								}
							}
							
							if(aspColField.isEnabled() == true) 
							{
								if(verifField(aspColField) == true) 
								{
									Modify("AspectColis", aspColField, nSerieField);	
								} 
								else if(verifField(aspColField) == false) 
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.aspColMsg);
								}
							}
							
							if(nomRecField.isEnabled() == true) 
							{
								if(verifField(nomRecField) == true) 
								{
									Modify("NomRecep", nomRecField, nSerieField);
								} 
								else if(verifField(nomRecField) == false) 
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.nomRecepMsg); 
								}
							}
							
							if(classMedField.isEnabled() == true) 
							{
								if(verifField(classMedField) == true) 
								{
									Modify("ClassMedi", classMedField, nSerieField);
								} 
								else if(verifField(classMedField) == false) 
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.classMedMsg);
								}
							}
						} 
						else if(existanceVerif(nSerieField) == false) 
						{
							JOptionPane.showMessageDialog(null, MessageManager.itemNserieNotFound);
						}
					} 
					else if(verifField(nSerieField) == false) 
					{
						JOptionPane.showMessageDialog(null, emptyFieldsMessages.nSerFieldMsg);
					}					
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(modBtn);
	}

	private void Connect() throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(loginInfo.getUrl(), 	
													   loginInfo.getUser(), 
													   loginInfo.getPwd()
													   );
	}
	
	private void Modify(String str, JTextField jt, JTextField jts) throws ClassNotFoundException, SQLException
	{
		Connect();
		sql = "UPDATE gmao.receptionequipement SET " + str + "= ? WHERE NSerie = ?;";
		ps = con.prepareStatement(sql);
		ps.setString(1, jt.getText().trim());
		ps.setString(2, jts.getText().trim());
		ps.executeUpdate();
		ps.close();
		con.close();
	}
	
	private boolean existanceVerif(JTextField jt) throws ClassNotFoundException, SQLException 
	{
		boolean verif = false;
		Connect();
		String sqlVerif = "SELECT * FROM gmao.receptionequipement WHERE NSerie = ?;";
		ps = con.prepareStatement(sqlVerif);
		ps.setString(1, jt.getText().trim());
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			if(jt.getText().trim().equals(rs.getString("NSerie")) == true) 
			{
				ps.close();
				rs.close();
				con.close();
				return verif = true;
			}
		}
		ps.close();
		rs.close();
		con.close();
		return verif;
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
			ps = con.prepareStatement(sqlAuto);
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
	
	public RoundJTextField getServField() { return servField; }

	public RoundJTextField getFournField()  { return fournField; }

	public RoundJTextField getTypeApField() { return typeApField; }

	public RoundJTextField getFabField() { return fabField; }

	public RoundJTextField getDesField() { return desField; }

	public RoundJTextField getMotRecField() { return motRecField; }

	public RoundJTextField getDateRecField() { return dateRecField; }

	public RoundJTextField getAdmField() { return admField; }

	public RoundJTextField getAspColField() { return aspColField; }

	public RoundJTextField getClassMedField() { return classMedField; }

	public RoundJTextField getNomRecField() { return nomRecField; }

	
	public JLabel getServLbl() { return servLbl; }

	public JLabel getFournLbl() { return fournLbl; }

	public JLabel getTypeApLbl() { return typeApLbl; }

	public JLabel getFabLbl() { return fabLbl; }

	public JLabel getDesLbl() { return desLbl; }

	public JLabel getMotifRecLbl() { return motifRecLbl; }

	public JLabel getDateRecepLbl() { return dateRecepLbl; }

	public JLabel getAdmLbl() { return admLbl; }

	public JLabel getAspecColLbl() { return aspecColLbl; }

	public JLabel getClassMedLbl() { return classMedLbl; }

	public JLabel getNomRecLbl() { return nomRecLbl; }
}