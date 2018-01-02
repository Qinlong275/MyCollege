package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

/**
 * @demostrate
 * @author zhaojun
 * @number 15020310086
 * @version
 */
public class ChatSocket extends Thread {

	private Socket socket;
	private boolean stopMe = false;
	private String name;
	private BufferedReader br;

	public void stopMe(){
		stopMe = true;
	}

	public ChatSocket(Socket socket) {
		this.socket = socket;
	}

	public void out(String out){
		try {
			socket.getOutputStream().write((out+"\n").getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			ChatManager.getChatManager().remove(this);
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while(!stopMe){
			try {
				br = new BufferedReader(
						new InputStreamReader(
							socket.getInputStream(),"UTF-8"));
				String line = null;
				if((line = br.readLine()) != null){
//					System.out.println("in 1: " + line);
					if(line.startsWith("@@@ ") && line.endsWith(" @@@")){	//ע����û���������Ϣ
						//��ע���û���Ϣ��ӵ���ע���б�
						String[] info = line.split(" ");
						if(!ChatManager.getChatManager().hasUser(info[1])){
							ChatManager.getChatManager().addUser(info[1], info[2]);
							//����ע��ɹ���Ϣ
							send("@@@REGOK@@@");
						}
						else	
							send("@@@REGERROR@@@");			//ע��ʧ��
					}
					else if (line.equals("***CLOSED***")) {
						ChatManager.getChatManager().toSW(
								"�ͻ��� "+ socket.getRemoteSocketAddress() + "�Ͽ�����\n");
						stopMe();
						ChatManager.getChatManager().remove(this);
						socket.close();
					}
					else if(line.startsWith("### ") && line.endsWith(" ###")){	//��¼���û���Ϣ
						//ƥ���˺�����,�ɹ�������ӵ������û��б�����Ͽ����Ӳ���ʾ�˺Ż��������
						String[] info = line.split(" ");
						this.name = info[1];
						if(ChatManager.getChatManager().isOnline(name))
							send("###ONLINE###");
						else if(ChatManager.getChatManager().checkPassword(name, info[2])){
							send("###LOGINOK###");
							//��ӵ������û��б�
							ChatManager.getChatManager().addUserName(name);
							//�㲥�ͻ��˵�¼�ɹ�����Ϣ
							String message = "&&& " + name + " ������" + " &&&";
							ChatManager.getChatManager().publish(this, message);
							ChatManager.getChatManager().add(this);
							String s = "%%% " + ChatManager.getChatManager().userToString() + "%%%";
							ChatManager.getChatManager().toSpecific(this,s);
							while((line = br.readLine()) != null){
								if(line.startsWith("!!! ") && line.endsWith(" STOP !!!")){
									String[] s1 = line.split(" ");
									//�㲥�ͻ������ߵ�֪ͨ
									ChatManager.getChatManager().publish(this, 
											"�ͻ��� "+ s1[1] + " ������\n");
									ChatManager.getChatManager().toSW(
											"�ͻ��� "+ socket.getRemoteSocketAddress() + " ������\n");
									//��ֹ��ǰ�߳�
									socket.close();
									stopMe();
									//������߼�¼��Ϣ
									ChatManager.getChatManager().removeUserName(name);
									ChatManager.getChatManager().remove(this);
								}
								else{
									ChatManager.getChatManager().publish(this, name + ": " + line);
								}
							}
						}
						else{
							send("###LOGINERROR###");
							stopMe();
							socket.close();
							br.close();
							br = null;
							ChatManager.getChatManager().remove(this);

						}
					}
				}
				else{
					stopSocket();
					br.close();
					br = null;
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void stopSocket(){
		stopMe = true;
		try {
			socket.close();
			ChatManager.getChatManager().remove(this);
			ChatManager.getChatManager().removeUserName(name);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void send(String out){
		try {
			System.out.println(out);
			PrintWriter writer = new PrintWriter(
					new OutputStreamWriter(
							socket.getOutputStream(),"UTF-8"));
			writer.write(out + "\n");
			writer.flush();
			out = null;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
