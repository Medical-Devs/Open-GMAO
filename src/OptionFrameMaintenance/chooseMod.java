package OptionFrameMaintenance;

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
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class chooseMod extends JFrame 
{
	private static final long serialVersionUID = -2800362735007730753L;
	private JPanel contentPane;
	protected final JCheckBox servCB, desCB, equCB, mrqCB, modCB, datePCB, decIntCB, durIntCB, montIntCB, etatCB, dateIntCB;
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			@Override
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
		setType(Type.POPUP);
		setTitle("S\u00E9lection des \u00E9lements");
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 775, 451);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		servCB = new JCheckBox("Service");
		servCB.setSelected(true);
		servCB.setBounds(104, 141, 97, 23);
		contentPane.add(servCB);
		
		JLabel titLbl = new JLabel("S\u00E9lection des \u00E9lements");
		titLbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		titLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titLbl.setBounds(257, 40, 283, 46);
		contentPane.add(titLbl);
		
		desCB = new JCheckBox("D\u00E9signation");
		desCB.setSelected(true);
		desCB.setBounds(104, 183, 97, 23);
		contentPane.add(desCB);
		
		equCB = new JCheckBox("Equipement");
		equCB.setSelected(true);
		equCB.setBounds(104, 227, 97, 23);
		contentPane.add(equCB);
		
		mrqCB = new JCheckBox("Marque");
		mrqCB.setSelected(true);
		mrqCB.setBounds(331, 141, 97, 23);
		contentPane.add(mrqCB);
		
		modCB = new JCheckBox("Mod\u00E8le");
		modCB.setSelected(true);
		modCB.setBounds(331, 183, 97, 23);
		contentPane.add(modCB);
		
		datePCB = new JCheckBox("Date pr\u00E9vue");
		datePCB.setSelected(true);
		datePCB.setBounds(331, 227, 97, 23);
		contentPane.add(datePCB);
		
		decIntCB = new JCheckBox("D\u00E9calage d'intervention");
		decIntCB.setSelected(true);
		decIntCB.setBounds(558, 141, 166, 23);
		contentPane.add(decIntCB);
		
		durIntCB = new JCheckBox("Dur\u00E9e de l'intervention");
		durIntCB.setSelected(true);
		durIntCB.setBounds(558, 183, 166, 23);
		contentPane.add(durIntCB);
		
		 montIntCB = new JCheckBox("Montant de l'intervention");
		 montIntCB.setSelected(true);
		montIntCB.setBounds(558, 227, 166, 23);
		contentPane.add(montIntCB);
		
		etatCB = new JCheckBox("Etat");
		etatCB.setSelected(true);
		etatCB.setBounds(104, 271, 97, 23);
		contentPane.add(etatCB);
		
		dateIntCB = new JCheckBox("Date d'intervention");
		dateIntCB.setSelected(true);
		dateIntCB.setBounds(331, 271, 150, 23);
		contentPane.add(dateIntCB);
		
		JButton confBtn = new JButton("Confirmer");
		confBtn.setBounds(331, 357, 129, 40);
		confBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				ModifyOptionsFrame mod = new ModifyOptionsFrame();
				
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
				
				if(verifCb(equCB) == true) 
				{
					mod.getEqField().setEnabled(true);
					mod.getEqLbl().setEnabled(true);
				} 
				else if(verifCb(equCB) == false) 
				{
					mod.getEqField().setEnabled(false);
					mod.getEqLbl().setEnabled(false);
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
				
				if(verifCb(datePCB) == true) 
				{
					mod.getDatPField().setEnabled(true);
					mod.getDatPLbl().setEnabled(true);
				} 
				else if(verifCb(datePCB) == false) 
				{
					mod.getDatPField().setEnabled(false);
					mod.getDatPLbl().setEnabled(false);
				}
				
				if(verifCb(decIntCB) == true) 
				{
					mod.getDecIntField().setEnabled(true);
					mod.getDecIntLbl().setEnabled(true);
				} 
				else if(verifCb(decIntCB) == false) 
				{
					mod.getDecIntField().setEnabled(false);
					mod.getDecIntLbl().setEnabled(false);
				}
				
				if(verifCb(durIntCB) == true) 
				{
					mod.getDurIntField().setEnabled(true);
					mod.getDurIntLbl().setEnabled(true);
				} 
				else if(verifCb(durIntCB) == false)
				{
					mod.getDurIntField().setEnabled(false);
					mod.getDurIntLbl().setEnabled(false);
				}
				
				if(verifCb(montIntCB)  == true) 
				{
					mod.getMontIntField().setEnabled(true);
					mod.getMontIntLbl().setEnabled(true);
				} 
				else if(verifCb(montIntCB) == false) 
				{
					mod.getMontIntField().setEnabled(false);
					mod.getMontIntLbl().setEnabled(false);
				}
				
				if(verifCb(etatCB) == true) 
				{
					mod.getEtatField().setEnabled(true);
					mod.getEtatLbl().setEnabled(true);
				} 
				else if(verifCb(etatCB) == false) 
				{
					mod.getEtatField().setEnabled(false);
					mod.getEtatLbl().setEnabled(false);
				}
				
				if(verifCb(dateIntCB) == true) 
				{
					mod.getDatIntField().setEnabled(true);
					mod.getDatIntLbl().setEnabled(true);
				} 
				else if(verifCb(dateIntCB) == false) 
				{
					mod.getDatIntField().setEnabled(false);
					mod.getDatIntLbl().setEnabled(false);
				}
			
			mod.setVisible(true);
			dispose();
			}
		});
		contentPane.add(confBtn);
	}
	
	protected boolean verifCb(JCheckBox c) 
	{
		boolean verif = false;
		if(c.isSelected() == true)
		{
			return verif = true;
		}
		return verif;
	}
}