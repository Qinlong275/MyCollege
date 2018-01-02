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
		if(flag){			//启动服务器
			try {
				serverSocket = new ServerSocket(port, BACKLOG, IP);

				//添加服务器启动的状态信息
				window.startSettings();

				//监听客户端请求
				while(true){
					Socket socket = serverSocket.accept();
					window.appendMessage("客户端 "+ socket.getRemoteSocketAddress() + "已连接\n");
					ChatSocket cs = new ChatSocket(socket);
					cs.start();
//					ChatManager.getChatManager().add(cs);	 		//导致找了两天bug的地方	
				}
			} catch (IOException e) {
				window.appendMessage("服务器启动失败，请更改IP地址或端口号再尝试\n");
				e.printStackTrace();
			}
		}
		else {		//终止服务器
			//终止监听socket
			try {
				serverSocket.close();
				ChatManager.getChatManager().publish(null, "!!!EXIT!!!");
				//终止所有与客户端连接的线程
				ChatManager.getChatManager().stopAll();
				window.stopSettings();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
