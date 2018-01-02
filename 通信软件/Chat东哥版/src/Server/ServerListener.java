package Server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @demostrate
 * @author zhaojun
 * @number 15020310086
 * @version
 */
public class ServerListener extends Thread {
	private int port;
	private InetAddress IP;
	private int BACKLOG = 20;

	private static ServerWindow window;
	private ServerSocket serverSocket = null;
	private boolean flag ;

	public static void setWindow(ServerWindow serverwindow){
		window = serverwindow;
	}

	public void set(boolean flag){
		this.flag = flag;
	}

	public ServerListener(InetAddress IP,int port) {
		this.IP = IP; 
		this.port = port;
	}

	public void run() {
		if(flag){			//����������
			try {
				serverSocket = new ServerSocket(port, BACKLOG, IP);

				//��ӷ�����������״̬��Ϣ
				window.startSettings();

				//�����ͻ�������
				while(true){
					Socket socket = serverSocket.accept();
					window.appendMessage("�ͻ��� "+ socket.getRemoteSocketAddress() + "������\n");
					ChatSocket cs = new ChatSocket(socket);
					cs.start();
//					ChatManager.getChatManager().add(cs);	 		//������������bug�ĵط�	
				}
			} catch (IOException e) {
				window.appendMessage("����������ʧ�ܣ������IP��ַ��˿ں��ٳ���\n");
				e.printStackTrace();
			}
		}
		else {		//��ֹ������
			//��ֹ����socket
			try {
				serverSocket.close();
				ChatManager.getChatManager().publish(null, "!!!EXIT!!!");
				//��ֹ������ͻ������ӵ��߳�
				ChatManager.getChatManager().stopAll();
				window.stopSettings();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
