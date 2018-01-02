package Client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JFileChooser;
import javax.swing.JLabel;

public class FileSend extends Thread{
	private int lengthS = 0;  
	private double sumL = 0 ;  
	private byte[] sendBytes = null; 
	private Socket socketS;
	private ClientSocket cs;
	private int port;
	private InetAddress ip;
	private ClientWindow cw;
	private boolean flag = true;

	public FileSend(ClientSocket cs){
		this.ip=cs.getIPAddress();
		this.port=cs.getPort();
		this.cs = cs;
		this.cw = cs.getCW();
	}

	public void run(){
		while(flag){
			//创建文件选择窗口
			JFileChooser jfc=new JFileChooser();  
			jfc.setFileSelectionMode(JFileChooser.FILES_ONLY );  
			jfc.showDialog(new JLabel(), "选择文件");  
			File file=jfc.getSelectedFile(); 
			String fileName = file.getName();
			System.out.println(fileName);
			//客户端发送文件信息
			cs.send("///FILE///");
			cs.send(fileName);

			//文件发送操作
			try {  
				sleep(5000);
				socketS = new Socket(ip,port);

				long l = file.length(); 
				//                Client.sendMessage(Client.nameLable.getText() + "@" +"FILE");//发送消息类型
				cs.send("///FILE///");
				DataOutputStream dos = new DataOutputStream(socketS.getOutputStream());  
				FileInputStream fis = new FileInputStream(file);        
				sendBytes = new byte[1024];    
				while ((lengthS = fis.read(sendBytes, 0, sendBytes.length)) > 0) {  
					sumL += lengthS;    
					System.out.println("已传输："+((sumL/l)*100)+"%");  
					dos.write(sendBytes, 0, lengthS);  
					dos.flush();  
				}   
				if(sumL==l){  
					cw.appendText("文件："+fileName+" 发送成功"+"\r\n");
				}  

				fis.close();
				dos.close();
				quit();
				//                socketS.close();
				//                Client.writer = new PrintWriter(Client.socket.getOutputStream());
				//                Client.reader = new BufferedReader(
				//                		new InputStreamReader(
				//                				Client.socket.getInputStream()));

			}catch (Exception e1) {  
				System.out.println("客户端文件传输异常");  
				e1.printStackTrace();    
			}
		}

	}  

	private void quit(){
		try {
			send("***CLOSED***");
			flag = false;
			socketS.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void send(String out){
		try {
			PrintWriter writer = new PrintWriter(
					new OutputStreamWriter(
							socketS.getOutputStream(),"UTF-8"));
			writer.write(out + "\n");
			writer.flush();
			flag = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

