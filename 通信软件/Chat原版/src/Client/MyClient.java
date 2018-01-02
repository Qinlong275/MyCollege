package Client;

import java.awt.EventQueue;

/**
 * @demostrate
 * @author zhaojun
 * @number 15020310086
 * @version
 */
public class MyClient {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientLogIN frame = new ClientLogIN();
					ClientSocket.setCLN(frame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
