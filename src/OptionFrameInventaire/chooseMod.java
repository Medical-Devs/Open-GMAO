package OptionFrameInventaire;

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

import EditMultipleObjects.RoundJButton;

public class chooseMod extends JFrame 
{
	private static final long serialVersionUID = 2731346127226914259L;
	private JPanel contentPane;

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
		setResizable(false);
		setType(Type.POPUP);
		setTitle("S\u00E9lection des \u00E9lements");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 606, 414);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JCheckBox servCB = new JCheckBox("Service");
		servCB.setSelected(true);
		servCB.setBounds(58, 146, 97, 23);
		contentPane.add(servCB);
		
		JCheckBox desCB = new JCheckBox("D\u00E9signation");
		desCB.setSelected(true);
		desCB.setBounds(58, 248, 97, 23);
		contentPane.add(desCB);
		
		JCheckBox typeEquiCB = new JCheckBox("Type d'\u00E9quipement");
		typeEquiCB.setSelected(true);
		typeEquiCB.setBounds(58, 198, 145, 23);
		contentPane.add(typeEquiCB);
		
		JCheckBox mrqCB = new JCheckBox("Marque");
		mrqCB.setSelected(true);
		mrqCB.setBounds(274, 146, 97, 23);
		contentPane.add(mrqCB);
		
		JCheckBox qteCB = new JCheckBox("Quantit\u00E9");
		qteCB.setSelected(true);
		qteCB.setBounds(274, 198, 97, 23);
		contentPane.add(qteCB);
		
		JCheckBox modCB = new JCheckBox("Mod\u00E8le");
		modCB.setSelected(true);
		modCB.setBounds(274, 248, 97, 23);
		contentPane.add(modCB);
		
		JCheckBox nCtrCB = new JCheckBox("Num\u00E9ro du contrat");
		nCtrCB.setSelected(true);
		nCtrCB.setBounds(453, 146, 136, 23);
		contentPane.add(nCtrCB);
		
		JButton confBtn = new RoundJButton("Confirmer");
		confBtn.setBounds(244, 315, 131, 40);
		confBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				ModificationOption mod = new ModificationOption();
				
				if(verifCb(servCB) == true) 
				{
					mod.getSrvField().setEnabled(true);
					mod.getSrvLbl().setEnabled(true);
				} 
				else if(verifCb(servCB) == false) 
				{
					mod.getSrvField().setEnabled(false);
					mod.getSrvLbl().setEnabled(false);
				}
				
				if(verifCb(desCB) == true) 
				{
					mod.getDesField().setEnabled(true);
					mod.getDesLbl().setEnabled(true);
				} 
				else if(verifCb(desCB) == false) 
				{
					mod.getDesField().setEnabled(false);
					mod.getDesLbl().setEnabled(false);
				}
				
				if(verifCb(typeEquiCB) == true) 
				{
					mod.getTypeEqField().setEnabled(true);
					mod.getTypeEqLbl().setEnabled(true);
				} 
				else if(verifCb(typeEquiCB) == false) 
				{
					mod.getTypeEqField().setEnabled(false);
					mod.getTypeEqLbl().setEnabled(false);
				}
				
				if(verifCb(mrqCB) == true) 
				{
					mod.getMrqField().setEnabled(true);
					mod.getMrqLbl().setEnabled(true);
				} 
				else if(verifCb(mrqCB) == false) 
				{
					mod.getMrqField().setEnabled(false);
					mod.getMrqLbl().setEnabled(false);
				}
				
				if(verifCb(qteCB) == true) 
				{
					mod.getQteField().setEnabled(true);
					mod.getQteLbl().setEnabled(true);
				} 
				else if(verifCb(qteCB) == false) 
				{
					mod.getQteField().setEnabled(false);
					mod.getQteLbl().setEnabled(false);
				}
				
				if(verifCb(modCB) == true) 
				{
					mod.getModField().setEnabled(true);
					mod.getModLbl().setEnabled(true);
				} 
				else if(verifCb(modCB) == false) 
				{
					mod.getModField().setEnabled(false);
					mod.getModLbl().setEnabled(false);
				}
				
				if(verifCb(nCtrCB) == true) 
				{
					mod.getnCtrField().setEnabled(true);
					mod.getnCtrLbl().setEnabled(true);
				} 
				else if(verifCb(nCtrCB) == false) 
				{
					mod.getnCtrField().setEnabled(false);
					mod.getnCtrLbl().setEnabled(false);
				}
				
				mod.setVisible(true);
				dispose();
			}
		});
		contentPane.add(confBtn);
		
		JLabel titLbl = new JLabel("S\u00E9lection des \u00E9lements");
		titLbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		titLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titLbl.setBounds(185, 48, 257, 40);
		contentPane.add(titLbl);
	}
	
	private boolean verifCb(JCheckBox c) 
	{
		boolean verif = false;
		if(c.isSelected() == true) {
			return verif = true;
		}
		return verif;
	}
}