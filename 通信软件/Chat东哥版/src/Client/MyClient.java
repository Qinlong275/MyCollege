package Client;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.Icon;
import javax.swing.ImageIcon;


public class MyClient {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					Icon bg = new ImageIcon("timg.jpg");
					ClientLogIN frame = new ClientLogIN();
					frame.setBackground(Color.pink);
					ClientSocket.setCLN(frame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
