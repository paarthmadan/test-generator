import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class OptionPanel extends JPanel{
	
	private JLabel label;
	private JButton button;
	private ImageIcon icon;
	
	
	public OptionPanel(String label, File iconPath){
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		button = new JButton();
		
		try {
			icon = new ImageIcon(ImageIO.read(iconPath));
			button.setIcon(icon);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.add(button);
		this.add(new JLabel(label));
		
	}

}
