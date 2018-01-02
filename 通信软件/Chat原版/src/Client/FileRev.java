package Client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFileChooser;
import javax.swing.JLabel;

public class FileRev extends Thread {

    //接收文件
    //文件接收参数
    byte[] inputByte = null;  
    int lengthR = 0;  
    DataInputStream dis = null;  
    FileOutputStream fos = null; 
    ServerSocket serverSocketF;
    String fileNameRev;
    int port;
    public FileRev(int port, String fileNameRev) throws IOException{
		this.port = port;
    	this.fileNameRev=fileNameRev;
    }
    public void run(){
    	//用户确认是否接收对话框
		JFileChooser jfc=new JFileChooser(); //文件夹选择器 
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY );  
        jfc.showDialog(new JLabel(), "选择文件夹");  
        File fileDir=jfc.getSelectedFile(); 
        String filePath = fileDir+"\\"+fileNameRev;//接收的文件存放路径获取
    	try {  
            	serverSocketF = new ServerSocket(port);
            	Socket socketF = serverSocketF.accept();
                dis = new DataInputStream(socketF.getInputStream());  
                if(!fileDir.exists()){  
                	fileDir.mkdir();    
                }  
                fos = new FileOutputStream(new File(filePath));      
                inputByte = new byte[1024];
                System.out.println("开始接收数据...");    
                while ((lengthR = dis.read(inputByte, 0, inputByte.length)) > 0) {  
                	 System.out.println(lengthR); 
                    fos.write(inputByte, 0, lengthR);  
                    fos.flush();      
                }  
                System.out.println("完成接收："+filePath);
                dis.close();
                fos.close();
                serverSocketF.close();
//                Client.writer = new PrintWriter(Client.socket.getOutputStream());
//                Client.reader = new BufferedReader(
//                		new InputStreamReader(
//                				Client.socket.getInputStream()));
               
              
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }
}
