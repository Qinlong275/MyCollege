package Client;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.net.Socket;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;

/**
 * @demostrate
 * @author zhaojun
 * @number 15020310086
 * @version
 */
public class ClientWindow extends JFrame implements Runnable{

	private JPanel contentPane;
	private JTextField name;
	private JTextArea message;
	private JButton exit;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_2;
	private JTextArea tosend;
	private JScrollPane scrollPane_1;
	private JButton send;
	private JButton sendfile;

//	private String name;
	private String account;
	private Socket socket;
	private ClientSocket cs;
	private BufferedReader reader;
	private JList list;
	private boolean flag = true;

	public String getAccount() {
		return account;
	}

	public String getTosend(){
		return tosend.getText()+getName();
	}
	
	public void setOut(){
		exit.setEnabled(false);
		send.setEnabled(false);
		sendfile.setEnabled(false);
		flag = false;
		appendText("您已下线");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Create the frame.
	 */
	public ClientWindow(String account, Socket socket, ClientSocket cs) {
		setResizable(false);

		this.cs = cs;
		this.account = account;
		this.socket = socket;

		setTitle("\u5BA2\u6237\u7AEF");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(700, 200, 455, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		exit = new JButton("\u9000\u51FA");
		exit.addMouseListener(new MouseAdapter() {
			@Override
			//退出
			public void mouseClicked(MouseEvent e) {
				if(flag){
					cs.send("!!! " + account + " STOP !!!");			//主动下线
					cs.exit();
				}
				
			}
		});
		exit.setBounds(237, 26, 66, 23);
		contentPane.add(exit);

		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(15, 59, 298, 185);
		contentPane.add(scrollPane);

		message = new JTextArea();
		message.setEditable(false);
		scrollPane.setViewportView(message);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u5728\u7EBF\u7528\u6237", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(331, 59, 93, 185);
		contentPane.add(scrollPane_1);
		
		list = new JList(user);
		scrollPane_1.setViewportView(list);

		scrollPane_2 = new JScrollPane();
		scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_2.setBounds(15, 261, 304, 65);
		contentPane.add(scrollPane_2);

		tosend = new JTextArea();
		scrollPane_2.setViewportView(tosend);

		send = new JButton("\u53D1\u9001");
		send.addMouseListener(new MouseAdapter() {
			@Override
			//发送消息
			public void mouseClicked(MouseEvent e) {
				String message = tosend.getText();
				if(!message.isEmpty()){
					cs.setFlag(false);
					cs.send(message);
					appendText("我：" + tosend.getText());
					tosend.setText("");
				}
				else
					JOptionPane.showMessageDialog(null, "发送消息不能为空");
			}
		});
		send.setBounds(331, 270, 93, 23);
		contentPane.add(send);

		sendfile = new JButton("\u53D1\u9001\u6587\u4EF6");
		sendfile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new FileSend(cs).start();
			}
		});
		sendfile.setBounds(331, 303, 93, 23);
		contentPane.add(sendfile);

		name = new JTextField(account);
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setEditable(false);
		name.setBounds(91, 27, 80, 21);
		contentPane.add(name);
		name.setColumns(10);

		JLabel label = new JLabel("\u7528\u6237\u4FE1\u606F");
		label.setBounds(26, 30, 54, 15);
		contentPane.add(label);
	}

	@Override
	public void run() {
		while(flag);
	}

	public void appendText(String out){
		message.append(out + "\n");
	}
	
	private DefaultListModel<String> user = new DefaultListModel<>();
	public void addUser(String userName){
		user.addElement(userName);
	}
	public void removeUser(String name){
		user.removeElement(name);
	}

}
