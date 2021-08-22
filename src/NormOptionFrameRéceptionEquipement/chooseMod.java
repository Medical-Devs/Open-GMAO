package NormOptionFrameRéceptionEquipement;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class chooseMod extends JFrame 
{

	private static final long serialVersionUID = 5697739708473198186L;
	private JPanel contentPane;
	protected final JCheckBox servCB, fournCB, fabCB, typeApCB, desCB, classMedCB, 
							  dateRecCB, motRecCB, aspcColisCB, admCB, nomRecCB;
	
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					chooseMod frame = new chooseMod();
					frame.setVisible(true);
				} 
					catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	public chooseMod() 
	{
		setTitle("S\u00E9l\u00E9ction des \u00E9l\u00E9ments");
		setType(Type.POPUP);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 732, 497);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Selection des \u00E9l\u00E9ments");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(231, 36, 241, 37);
		contentPane.add(lblNewLabel);
		
		servCB = new JCheckBox("Service");
		servCB.setSelected(true);
		servCB.setBounds(75, 144, 97, 23);
		contentPane.add(servCB);
		
		fournCB = new JCheckBox("Fournisseur");
		fournCB.setSelected(true);
		fournCB.setBounds(75, 186, 97, 23);
		contentPane.add(fournCB);
		
		fabCB = new JCheckBox("Fabricant");
		fabCB.setSelected(true);
		fabCB.setBounds(75, 233, 97, 23);
		contentPane.add(fabCB);
		
		typeApCB = new JCheckBox("Type d'appareil");
		typeApCB.setSelected(true);
		typeApCB.setBounds(75, 282, 129, 23);
		contentPane.add(typeApCB);
		
		desCB = new JCheckBox("D\u00E9signation");
		desCB.setSelected(true);
		desCB.setBounds(270, 144, 97, 23);
		contentPane.add(desCB);
		
		classMedCB = new JCheckBox("Classe M\u00E9dicale");
		classMedCB.setSelected(true);
		classMedCB.setBounds(475, 240, 142, 23);
		contentPane.add(classMedCB);
		
		dateRecCB = new JCheckBox("Date de r\u00E9ception");
		dateRecCB.setSelected(true);
		dateRecCB.setBounds(270, 186, 142, 23);
		contentPane.add(dateRecCB);
		
		motRecCB = new JCheckBox("Motif de r\u00E9ception");
		motRecCB.setSelected(true);
		motRecCB.setBounds(270, 233, 142, 23);
		contentPane.add(motRecCB);
		
		aspcColisCB = new JCheckBox("Aspect du colis");
		aspcColisCB.setSelected(true);
		aspcColisCB.setBounds(270, 282, 109, 23);
		contentPane.add(aspcColisCB);
		
		admCB = new JCheckBox("Admission");
		admCB.setSelected(true);
		admCB.setBounds(475, 144, 97, 23);
		contentPane.add(admCB);
		
		nomRecCB = new JCheckBox("Nom du r\u00E9ceptionniste");
		nomRecCB.setSelected(true);
		nomRecCB.setBounds(475, 191, 154, 23);
		contentPane.add(nomRecCB);
		
		JButton confBtn = new JButton("Confirmer");
		confBtn.setBounds(258, 371, 154, 46);
		confBtn.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				ModifyOptions mod = new ModifyOptions();
				
				if(verifCb(servCB) == true) 
				{
					mod.getServField().setEnabled(true);
					mod.getServLbl().setEnabled(true);
				} 
				else if(verifCb(servCB) == false) 
				{
					mod.getServField().setEnabled(false);
					mod.getServLbl().setEnabled(false);
				}
				
				if(verifCb(fournCB) == true) {
					mod.getFournField().setEnabled(true);
					mod.getFournLbl().setEnabled(true);
				} else if(verifCb(fournCB) == false) {
					mod.getFournField().setEnabled(false);
					mod.getFournLbl().setEnabled(false);
				}
				
				if(verifCb(fabCB) == true) {
					mod.getFabField().setEnabled(true);
					mod.getFabLbl().setEnabled(true);
				} else if(verifCb(fabCB) == false) {
					mod.getFabField().setEnabled(false);
					mod.getFabLbl().setEnabled(false);
				}
				
				if(verifCb(typeApCB) == true) {
					mod.getTypeApField().setEnabled(true);
					mod.getTypeApLbl().setEnabled(true);
				} else if(verifCb(typeApCB) == false) {
					mod.getTypeApField().setEnabled(false);
					mod.getTypeApLbl().setEnabled(false);
				}
				
				if(verifCb(desCB) == true) {
					mod.getDesField().setEnabled(true);
					mod.getDesLbl().setEnabled(true);
				} else if(verifCb(desCB) == false) {
					mod.getDesField().setEnabled(false);
					mod.getDesLbl().setEnabled(false);
				}
				
				if(verifCb(classMedCB) == true) {
					mod.getClassMedField().setEnabled(true);
					mod.getClassMedField().setEnabled(true);
				} else if(verifCb(classMedCB) == false) {
					mod.getClassMedField().setEnabled(false);
					mod.getClassMedLbl().setEnabled(false);
				}
				
				if(verifCb(dateRecCB) == true) {
					mod.getDateRecepLbl().setEnabled(true);
					mod.getDateRecField().setEnabled(true);
				} else if(verifCb(dateRecCB) == false) {
					mod.getDateRecepLbl().setEnabled(false);
					mod.getDateRecField().setEnabled(false);
				}
				
				if(verifCb(motRecCB) == true) {
					mod.getMotifRecLbl().setEnabled(true);
					mod.getMotRecField().setEnabled(true);
				} else if(verifCb(motRecCB) ==  false) {
					mod.getMotifRecLbl().setEnabled(false);
					mod.getMotRecField().setEnabled(false);
				}
				
				if(verifCb(aspcColisCB) == true) {
					mod.getAspColField().setEnabled(true);
					mod.getAspecColLbl().setEnabled(true);
				} else if(verifCb(aspcColisCB) == false) {
					mod.getAspColField().setEnabled(false);
					mod.getAspecColLbl().setEnabled(false);
				}
				
				if(verifCb(admCB) == true) {
					mod.getAdmField().setEnabled(true);
					mod.getAdmLbl().setEnabled(true);
				} else if(verifCb(admCB) == false) {
					mod.getAdmField().setEnabled(false);
					mod.getAdmLbl().setEnabled(false);
				}
				
				if(verifCb(nomRecCB) == true) {
					mod.getNomRecField().setEnabled(true);
					mod.getNomRecLbl().setEnabled(true);
				} else if(verifCb(nomRecCB) == false) {
					mod.getNomRecField().setEnabled(false);
					mod.getNomRecLbl().setEnabled(false);
				}
				
				mod.setVisible(true);
				dispose();
			}
		});
		contentPane.add(confBtn);	
	}
	
	private boolean verifCb(JCheckBox c) {
		boolean verif = false;
		if(c.isSelected() == true) {
			return verif = true;
		}
		return verif;
	}
}