import java.awt.Component;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class OptionPanel extends JPanel{
	
	private JLabel label;
	private JButton button;
	private ImageIcon icon;
	
	
	public OptionPanel(String labelText, File iconPath){
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		button = new JButton();
		label = new JLabel(labelText);
		
		try {
			icon = new ImageIcon(ImageIO.read(iconPath));
			button.setIcon(icon);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.setSelected(false);
		
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		this.setBorder(new EmptyBorder(10,10,10,10));
		
		this.add(button);
		this.add(label);
		
	}

}
