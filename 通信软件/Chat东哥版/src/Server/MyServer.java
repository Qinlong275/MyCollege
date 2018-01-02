package Server;

import java.awt.EventQueue;


public class MyServer {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerWindow frame = new ServerWindow();
					ServerListener.setWindow(frame);
					ChatManager.getChatManager().setSW(frame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
