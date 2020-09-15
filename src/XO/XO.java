package XO;

import javax.swing.*;

public class XO {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		Canvas panel = new Canvas(500, 500);
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(panel);
		frame.setVisible(true);
	}
}
