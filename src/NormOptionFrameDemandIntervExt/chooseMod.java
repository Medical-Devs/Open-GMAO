package NormOptionFrameDemandIntervExt;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import java.awt.Font;

public class chooseMod extends JFrame 
{
	private static final long serialVersionUID = -3055174716906329798L;
	private JPanel contentPane;
	public final JCheckBox typeApCb = new JCheckBox("Type d'appareil");
	protected final JCheckBox servCB = new JCheckBox("Service");
	protected final JCheckBox mrqCB = new JCheckBox("Marque");
	protected final JCheckBox numSerCB = new JCheckBox("Num\u00E9ro de serie");
	protected final JCheckBox modCB = new JCheckBox("Mod\u00E8le");
	protected final JCheckBox fournCB = new JCheckBox("Fournisseur");
	protected final JCheckBox numEnrCB = new JCheckBox("Num\u00E9ro d'enregistrement");
	protected final JCheckBox typeIntCB = new JCheckBox("Type d'intervention");
	protected final JCheckBox dateIntCB = new JCheckBox("Date d'intervention");

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
		setTitle("Selection des \u00E9l\u00E9ments");
		setType(Type.POPUP);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 579, 349);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		typeApCb.setSelected(true);
		typeApCb.setBounds(59, 95, 139, 23);
		contentPane.add(typeApCb);
		
		servCB.setSelected(true);
		servCB.setBounds(59, 146, 139, 23);
		contentPane.add(servCB);
		
		mrqCB.setSelected(true);
		mrqCB.setBounds(59, 200, 97, 23);
		contentPane.add(mrqCB);
		
		numSerCB.setSelected(true);
		numSerCB.setBounds(419, 95, 138, 23);
		contentPane.add(numSerCB);
		
		modCB.setSelected(true);
		modCB.setBounds(233, 95, 97, 23);
		contentPane.add(modCB);
		
		fournCB.setSelected(true);
		fournCB.setBounds(233, 146, 97, 23);
		contentPane.add(fournCB);
		
		numEnrCB.setSelected(true);
		numEnrCB.setBounds(233, 200, 161, 23);
		contentPane.add(numEnrCB);
		
		typeIntCB.setSelected(true);
		typeIntCB.setBounds(419, 146, 138, 23);
		contentPane.add(typeIntCB);
		
		dateIntCB.setSelected(true);
		dateIntCB.setBounds(419, 200, 144, 23);
		contentPane.add(dateIntCB);

		JButton confBtn = new JButton("Confirmer");
		confBtn.setBounds(213, 251, 113, 36);
		confBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				ModifyOptionFrame mod = new ModifyOptionFrame();
			
				if(verifCb(typeApCb) == true) 
				{
					mod.getTypeApField().setEnabled(true);
					mod.getTypeAppLbl().setEnabled(true);
				} 
				else if(verifCb(typeApCb) == false) 
				{
					mod.getTypeApField().setEnabled(false);
					mod.getTypeAppLbl().setEnabled(false);
				}
				
				if(verifCb(servCB) == true) 
				{
					mod.getServField().setEnabled(true);
					mod.getServLbl().setEnabled(true);
					dispose();
				} 
				else if(verifCb(servCB) == false) 
				{
					mod.getServField().setEnabled(false);
					mod.getServLbl().setEnabled(false);
				}
				
				if(verifCb(mrqCB) == true) 
				{
					mod.getMrqField().setEnabled(true);
					mod.getMrqLbl().setEnabled(true);
					dispose();
				} 
				else if(verifCb(mrqCB) == false) 
				{
					mod.getMrqField().setEnabled(false);
					mod.getMrqLbl().setEnabled(false);
				}
				
				if(verifCb(numSerCB) == true) 
				{
					mod.getnSerField().setEnabled(true);
					mod.getnSerLbl().setEnabled(true);
					dispose();
				} 
				else if(verifCb(numSerCB) == false) 
				{
					mod.getnSerField().setEnabled(false);
					mod.getnSerLbl().setEnabled(false);
				}
				
				if(verifCb(modCB) == true) 
				{
					mod.getModField().setEnabled(true);
					mod.getModLbl().setEnabled(true);
					dispose();
				} 
				else if(verifCb(modCB) == false) 
				{
					mod.getModField().setEnabled(false);
					mod.getModLbl().setEnabled(false);
				}
				
				if(verifCb(fournCB) == true) 
				{
					mod.getFournField().setEnabled(true);
					mod.getFournLbl().setEnabled(true);
				} 
				else if(verifCb(fournCB) == false) 
				{
					mod.getFournField().setEnabled(false);
					mod.getFournLbl().setEnabled(false);
				}
				
				if(verifCb(numEnrCB) == true) 
				{
					mod.getNumEnrField().setEnabled(true);
					mod.getnEnrgLbl().setEnabled(true);
				} 
				else if(verifCb(numEnrCB) == false) 
				{
					mod.getNumEnrField().setEnabled(false);
					mod.getnEnrgLbl().setEnabled(false);
				}
				
				if(verifCb(typeIntCB) == true) 
				{
					mod.getTypeIntField().setEnabled(true);
					mod.getTypeIntLbl().setEnabled(true);
				} 
				else if(verifCb(typeIntCB) == false) 
				{
					mod.getTypeIntField().setEnabled(false);
					mod.getTypeIntLbl().setEnabled(false);
				}
				
				if(verifCb(dateIntCB) == true) 
				{
					mod.getDatField().setEnabled(true);
					mod.getDateLbl().setEnabled(true);
					dispose();
				} 
				else if(verifCb(dateIntCB) == false) 
				{
					mod.getDatField().setEnabled(false);
					mod.getDateLbl().setEnabled(false);
				}
				
				mod.setVisible(true);
				dispose();
			}
		});
		contentPane.add(confBtn);
		
		JLabel lblNewLabel = new JLabel("Selection des \u00E9l\u00E9ments \u00E0 modifier");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(101, 24, 337, 46);
		contentPane.add(lblNewLabel);
	}
	
	private boolean verifCb(JCheckBox c) 
	{
		boolean verif = false;
		if(c.isSelected() == true) 
		{
			return verif = true;
		}
		return verif;
	}
}