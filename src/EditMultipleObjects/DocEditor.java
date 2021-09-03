package EditMultipleObjects;

import javax.swing.JFrame;

public class DocEditor extends JFrame
{

	private static final long serialVersionUID = -8208268358136576156L;

	public DocEditor() 
	{
		sferyx.administration.editors.HTMLEditor hTMLEditor1;
		hTMLEditor1 = new sferyx.administration.editors.HTMLEditor();

        addWindowListener(new java.awt.event.WindowAdapter() 
        {
            @Override
			public void windowClosing(java.awt.event.WindowEvent evt) 
            {
                exitForm(evt);
            }
        });

        hTMLEditor1.setRemovedMenuItems("");
        hTMLEditor1.setStatusMessage("");
        hTMLEditor1.setTableItemsStatus(false);
        getContentPane().add(hTMLEditor1, java.awt.BorderLayout.CENTER);
        pack();
	}
	
	public static void main(String[] args) 
	{
		DocEditor z = new DocEditor();
		z.setExtendedState(MAXIMIZED_BOTH);
		z.setBounds(0,0,1920,1080);
		z.setLocationRelativeTo(null);
		z.setVisible(true);
		z.setTitle("Editeur de Documents");
		z.setResizable(true);
	}
	
    private void exitForm(java.awt.event.WindowEvent evt) 
    {
        System.exit(0);
    }
}