package Server;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.swing.JList;

/**
 * @demostrate
 * @author zhaojun
 * @number 15020310086
 * @version
 */
public class ServerWindow extends JFrame {

	private JPanel contentPane;
	private JTextField ipText;
	private JTextField portText;
	private JButton start;
	private JButton stop;
	private JLabel lblNewLabel;
	private JLabel label_1;
	private JPanel panel;
	private JPanel panel_3;
	private JLabel label_2;
	private JScrollPane scrollPane_1;
	private JButton serverSend;
	private JPanel panel_1;
	private JLabel label;
	private JScrollPane scrollPane_2;
	private JPanel panel_2;
	private JScrollPane scrollPane;
	private JLabel label_3;
	private JTextArea statusInfo;
	private JTextArea serverInfo;

	private InetAddress IPAddress;				//IP地址
	private int port;							//端口号
	private ServerListener sl = null;
	private JList list;

	public InetAddress getIPAddress(){
		return this.IPAddress;
	}

	private boolean setIPAddress(){
		if(!ipText.getText().isEmpty()){
			try {
				this.IPAddress = InetAddress.getByName(ipText.getText());
				return true;
			} catch (UnknownHostException e) {
				JOptionPane.showMessageDialog(null, "地址错误，请更换IP地址");
				e.printStackTrace();
				return false;
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "请输入IP地址");
			return false;
		}

	}

	public int getPort(){
		return this.port;
	}

	private boolean setPort(){
		if(!portText.getText().isEmpty()){
			this.port = Integer.parseInt(portText.getText());
			return true;
		}
		else{
			JOptionPane.showMessageDialog(null, "请输入端口号");
			return false;
		}
	}

	//服务器启动成功的界面设置
	public void startSettings(){
		statusInfo.append("服务器" + IPAddress + "已启动，端口为" + port + "\n");	//添加服务器启动的状态信息

		//更新服务器参数配置
		ipText.setEditable(false);
		portText.setEditable(false);
		start.setEnabled(false);
		stop.setEnabled(true);
		serverSend.setEnabled(true);
	}

	//在线用户列表
	private DefaultListModel<String> userOnline = new DefaultListModel<>();
	public void addUser(String userName){
		userOnline.addElement(userName);
	}
	public void removeUser(String userName){
		userOnline.removeElement(userName);
	}

	//添加服务器停止的状态信息
	public void stopSettings(){
		//清空在线用户列表
		userOnline.clear();
		
		statusInfo.append("服务器" + IPAddress + " : " + port + "已停止\n");

		//更新窗口界面
		ipText.setEditable(true);
		portText.setEditable(true);
		start.setEnabled(true);
		stop.setEnabled(false);
		serverSend.setEnabled(false);
	}

	//向状态消息框中添加消息
	public void appendMessage(String message){
		statusInfo.append(message);
	}

	/**
	 * Create the frame.
	 */
	public ServerWindow() {
		setResizable(false);
		setFont(new Font("黑体", Font.PLAIN, 12));
		setTitle("\u670D\u52A1\u5668");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 380, 220, 100);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setBackground(Color.pink);

		panel = new JPanel();
//		panel.hide();
		panel.setBounds(23, 17,150, 95);
//		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u670D\u52A1\u5668\u914D\u7F6E", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		panel_3 = new JPanel();
		panel_3.setBounds(23, 338, 325, 83);
		panel_3.hide();

		serverSend = new JButton("\u53D1\u9001");
		serverSend.hide();
		serverSend.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(serverSend.isEnabled()){
					String info = serverInfo.getText();
					if(! info.isEmpty()){
						//服务器发送公告
						ChatManager.getChatManager().publish(null, "服务器公告：" + info);

						//将公告信息添加到状态信息
						statusInfo.append("服务器公告：" + info + "\n");
						serverInfo.setText("");
					}
					else
						JOptionPane.showMessageDialog(null, "公告信息不能为空，请重新输入");
				}
			}
		});
		serverSend.setFont(new Font("新宋体", Font.BOLD, 14));
		serverSend.setBounds(358, 396, 64, 25);
		serverSend.setEnabled(false);
		contentPane.setLayout(null);
		contentPane.add(panel);
//		panel.setLayout(null);

		start = new JButton("\u542F\u52A8");
		start.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(start.isEnabled()){
					//服务器启动
					if(setIPAddress() && setPort()){
						sl = new ServerListener(IPAddress,port);
						sl.set(true);
						sl.start();
					}
				}
			}
		});
		start.setFont(new Font("新宋体", Font.BOLD, 14));
		start.setBounds(0, 24, 70, 25);
		panel.add(start);

		stop = new JButton("\u505C\u6B62");
		stop.setEnabled(false);
		stop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(stop.isEnabled()){
					//将ServerListener先挂起
					sl.suspend();
					//					sl.interrupt();
					//服务器停止
					sl.set(false);
					sl.run();
				}
			}
		});
		stop.setFont(new Font("新宋体", Font.BOLD, 14));
		stop.setBounds(0, 61, 70, 25);
		panel.add(stop);

		lblNewLabel = new JLabel("IP\u5730\u5740\uFF1A");
		lblNewLabel.setFont(new Font("新宋体", Font.PLAIN, 12));
		lblNewLabel.setBounds(20, 29, 54, 15);
		panel.add(lblNewLabel);
		lblNewLabel.hide();

		ipText = new JTextField();
		ipText.setText("127.0.0.1");
		ipText.setBounds(70, 25, 110, 23);
		panel.add(ipText);
		ipText.setColumns(10);
		ipText.hide();

		label_1 = new JLabel("\u7AEF\u53E3\u53F7\uFF1A");
		label_1.setFont(new Font("新宋体", Font.PLAIN, 12));
		label_1.setBounds(0, 66, 154, 15);
		panel.add(label_1);
		label_1.hide();
		
		portText = new JTextField();
		portText.hide();
		portText.setText("8082");
		portText.setBounds(70, 63, 110, 23);
		panel.add(portText);
		portText.setColumns(10);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		label_2 = new JLabel("\u670D\u52A1\u5668\u53D1\u9001\u516C\u544A");
		label_2.setBounds(0, 0, 106, 15);
		panel_3.add(label_2);
//		panel_3.hide();

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 21, 325, 62);
		panel_3.add(scrollPane_1);

		serverInfo = new JTextArea();
		serverInfo.setLineWrap(true);
		scrollPane_1.setViewportView(serverInfo);
		contentPane.add(serverSend);

		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(23, 125, 92, 196);
		contentPane.add(panel_1);

		label = new JLabel("\u5728\u7EBF\u7528\u6237\u5217\u8868");
		label.setFont(UIManager.getFont("Label.font"));
		label.setBounds(0, 0, 84, 15);
		label.hide();
		panel_1.add(label);
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_2.setBounds(0, 21, 92, 175);
		panel_1.add(scrollPane_2);
		scrollPane_2.hide();

		list = new JList(userOnline);
		scrollPane_2.setViewportView(list);

		panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(298, 125, 284, 196);
		contentPane.add(panel_2);
//		panel_2.hide();

		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 21, 283, 175);
		panel_2.add(scrollPane);

		statusInfo = new JTextArea();
		statusInfo.setLineWrap(true);
		statusInfo.setEditable(false);
		scrollPane.setViewportView(statusInfo);

		label_3 = new JLabel("\u7CFB\u7EDF\u72B6\u6001\u6D88\u606F");
		label_3.setFont(UIManager.getFont("Label.font"));
		label_3.setBounds(0, 0, 87, 15);
		panel_2.add(label_3);
		panel_2.hide();
		panel_1.setOpaque(false);
		panel_2.setOpaque(false);
		panel_3.setOpaque(false);
		panel.setOpaque(false);

	}

}
