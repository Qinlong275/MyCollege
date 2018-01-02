package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import javax.swing.JOptionPane;


public class ClientSocket extends Thread{
	private Socket socket;
	private BufferedReader reader = null;
	private ClientWindow cw ;
	private InetAddress IPAddress;
	private int port;

	private boolean flag;

	public void setFlag(boolean flag){
		this.flag = flag;
	}
	
	public ClientWindow getCW(){
		return cw;
	}
	
	public InetAddress getIPAddress() {
		return IPAddress;
	}

	public int getPort() {
		return port;
	}

	public ClientSocket(InetAddress IPAddress,int port) {
		try {
			flag = false;
			socket = new Socket(IPAddress, port);
			cln.setConnStatus();
			ClientRegister cr = new ClientRegister();
			cr.setCR(cr);
			cr.setCLN(cln);
			cr.setCS(this);
			cr.setVisible(true);
			new Thread(cr).start();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "连接到服务器失败，请更改参数后重试");
			e.printStackTrace();
		}
	}

	public ClientSocket(InetAddress IPAddress, int port, String account, String password) {
		try {
			this.IPAddress = IPAddress;
			this.port = port;
			socket = new Socket(IPAddress, port);
			send("### " + account + " " + password + " ###");
			reader = new BufferedReader(
					new InputStreamReader(
							socket.getInputStream(),"UTF-8"));
			String in = reader.readLine();
			if(in.equals("###LOGINOK###")){
				cw = new ClientWindow(account, socket, this);
				flag = true;
				cln.setVisible(false);
				cw.setVisible(true);
				cw.appendText("您已成功登录！");
				new Thread(cw).start();
			}
			else if(in.equals("###ONLINE###")){
				reader.close();
				reader = null;
				socket.close();
				JOptionPane.showMessageDialog(null, "您已在线，不可重复登录");
			}
			else{
				reader.close();
				reader = null;
				socket.close();
				JOptionPane.showMessageDialog(null, "账号未注册或账号密码错误，请核对");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static ClientLogIN cln;
	public static void setCLN(ClientLogIN clientLogIN){
		cln = clientLogIN;
	}

	public Socket getSocket(){
		return socket;
	}

	@Override
	public void run() {
		String in = "";
		while(flag){
			try {
				if((in = reader.readLine()) != null){
					if(in.startsWith("&&& ") && in.endsWith(" &&&")){
						String[] message = in.split(" ");
						//将用户名添加到在线用户列表
						cw.addUser(message[1]);
						cw.appendText(message[1] + message[2]);
					}
					else if(in.startsWith("%%% ") && in.endsWith(" %%%")){
						String[] message = in.split(" ");
						for(int i = 1; i < message.length-1; i++){
							//将用户名添加到在线用户列表
							cw.addUser(message[i]);
						}
					}
					else if(in.endsWith("已下线")){
						String[] message = in.split(" ");
						cw.removeUser(message[1]);
						cw.appendText(in);
					}
					else if(in.equals("!!!EXIT!!!"))		//被迫下线
						exit();
					//接收文件
                    else if(in.equals("///FILE///")){
                    	String fileNameRev="tempRev";
//                    	if(stringTokenizer.hasMoreTokens()){
//                    		fileNameRev = stringTokenizer.nextToken();
//                    	}
//                    	System.out.println("客户端接收到文件传送命令");
                    	int value = JOptionPane.showOptionDialog(null, "是否接收文件？", "收到文件", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                     	if(value == JOptionPane.YES_OPTION){//确认接收
//                     		sendMessage("Acc");
//                     		System.out.println("待接收的文件名："+fileNameRev);
                     		
                     		new FileRev(28888,fileNameRev).start();
                     		
                     		cw.appendText("收到文件："+fileNameRev + "\r\n");//文件描述
                     	}
                     	
                    	//如果不接收文件
                     	else{
//                     		sendMessage("Den");
                    		return;
                    	}
                    }
					else{
						cw.appendText(in);
					}
					in = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public void send(String out){
		try {
			PrintWriter writer = new PrintWriter(
					new OutputStreamWriter(
							socket.getOutputStream(),"UTF-8"));
			writer.write(out + "\n");
			writer.flush();
			flag = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void exit(){
		try {
			flag = false;
			socket.close();
			cw.setOut();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
