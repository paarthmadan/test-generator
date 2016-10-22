import javax.swing.*;

public class OptionPanel extends JPanel{
	
	private JLabel label;
	private JButton button;
	private ImageIcon icon;
	
	
	public OptionPanel(String label, String iconPath){
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.add(new JButton());
		this.add(new JLabel(label));
		
	}

}
