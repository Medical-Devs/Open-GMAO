package EditMultipleObjects;

import java.awt.Dimension;
import java.awt.Toolkit;

public class WindowSize 
{
	public static final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
	public static final int widthScreen = (int) size.getWidth();
	public static final int heightScreen = (int) size.getHeight();
}