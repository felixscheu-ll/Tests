package TheGame;

import javax.swing.JFrame;

public class Main {
	// X and Y dimensions of the JFrame	
	static final int fX=1500;
	static final int fY=800;
		
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setBounds(10,10,fX,fY);
		f.setTitle("I 'm gonna send me to Outer Space and find another race");
		
		// Gameplay panel is instantiated and added to JFrame
		Gameplay gameplay = new Gameplay();
		f.add(gameplay);
		
		f.setResizable(false);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	} 	
}
