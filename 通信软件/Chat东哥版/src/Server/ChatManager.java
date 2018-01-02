package Server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * @demostrate
 * @author zhaojun
 * @number 15020310086
 * @version
 */
public class ChatManager {

	//单例化
	private static final ChatManager cm = new ChatManager();
	private ChatManager(){}
	public static ChatManager getChatManager() {
		return cm;
	}

	//记录所有连接的客户端线程
	private Vector<ChatSocket> vector = new Vector<>();
	public void add(ChatSocket cs){
		vector.add(cs);
	}
	public void remove(ChatSocket cs){
		vector.remove(cs);
	}

	private ServerWindow sw;
	public void setSW(ServerWindow sw){
		this.sw = sw;
	}
	public void toSW(String out){
		sw.appendMessage(out);
	}

	//记录在线用户姓名
	private List<String> userOnline = new ArrayList<>();
	public void addUserName(String name){
		userOnline.add(name);
		sw.addUser(name);
	}
	public void removeUserName(String name){
		userOnline.remove(name);
		sw.removeUser(name);
	}
	public boolean isOnline(String name){
		return userOnline.contains(name);
	}

	//记录已注册的用户名和密码
	private Map<String, String> userInfo = new HashMap<>();
	public void addUser(String name, String password){
		userInfo.put(name, password);
	}
	public boolean checkPassword(String name, String password){
		return password.equals(userInfo.get(name));
	}
	public boolean hasUser(String name){
		if(userInfo.containsKey(name))
			return true;
		return false;
	}

	public String userToString(){
		StringBuffer stringBuffer = new StringBuffer();
		for(String user:userOnline)
			stringBuffer.append(user + " ");
		return stringBuffer.toString();
	}

	//向特定客户端发消息  单播
	public void toSpecific(ChatSocket cs, String out){
		if(cs == null)
			return;
		for(ChatSocket chatSocket : vector)
			if(chatSocket.equals(cs))
				chatSocket.out(out);
	}

	//广播消息
	public void publish(ChatSocket cs, String out){
		if(cs == null){
			for(ChatSocket chatSocket: vector){
				chatSocket.out(out);
			}
		}
		else{
			for(ChatSocket chatSocket:vector){
				if(!chatSocket.equals(cs))
					chatSocket.out(out);
			}
		}
	}

	public void stopAll(){
		for(ChatSocket cs:vector){
//			cs.stopMe();
			cs.stopSocket();
		}
	}

	//	private static String  filePath = "userdatabese.txt";
	//	private static File db = new File(filePath);
	//	private static Map<String, String[]> user = new HashMap<>();
	//	private static int maxId;

	//	public void readDB(){
	//		if(!db.exists()){
	//			try {
	//				db.createNewFile();
	//				user = null;
	//			} catch (IOException e) {
	//				e.printStackTrace();
	//			}
	//		}
	//		else{
	//			try {
	//				Scanner fsc = new Scanner(Paths.get(filePath));
	//				while(fsc.hasNext()){
	//					String[] userRecord = fsc.next().split(" ");
	//					String account = userRecord[0];
	//					String[] str = {userRecord[1], userRecord[1]};
	//					user.put(account, str);
	//					maxId++;
	//				}
	//				fsc.close();
	//			} catch (IOException e) {
	//				e.printStackTrace();
	//			}
	//		}
	//		
	//	}
	//	
	//	public static void AddUser(String account, String name, String password){
	//		String[] string = {name, password};
	//		user.put(account, string);
	//		FileWriter writer;
	//		try {
	//			writer = new FileWriter(filePath, true);
	//			writer.write(account + name + password + "\n"); 
	//		} catch (IOException e) {
	//			e.printStackTrace();
	//		}   
	//	}

	//	public void finalize(){
	//		try {
	//			PrintWriter pwt = new PrintWriter(db);
	//			while(!user.isEmpty()){
	//				Iterator<String> iter = user.keySet().iterator();
	//				while(iter.hasNext()){
	//					String account = iter.next();
	//					pwt.write(account + user.get(account).toString());
	//					user.remove(account);
	//				}
	//			}
	//			pwt.close();
	//		} catch (FileNotFoundException e) {
	//			e.printStackTrace();
	//		}
	//		
	//	}

}
