package Client;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;

/**
 * @demostrate
 * @author zhaojun
 * @number 15020310086
 * @version
 */
public class ClientLogIN extends JFrame {
	
	private JPanel contentPane;
	private JTextField iptext;
	private JTextField porttext;
	private JTextField accountText;
	private JButton logINBut;
	private JButton RegisterBut;
	private JPasswordField passwordField;
	private JTextField status;
	
	private InetAddress IPAddress;
	private int port;
	private String account;
	private String password;

	public InetAddress getIpAddress() {
		return IPAddress;
	}

	private boolean setIpAddress() {
		if(!iptext.getText().isEmpty()){
			try {
				this.IPAddress = InetAddress.getByName(iptext.getText());
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

	public int getPort() {
		return port;
	}
	
	private boolean setPort() {
		if(!porttext.getText().isEmpty()){
			this.port = Integer.parseInt(porttext.getText());
			return true;
		}
		else{
			JOptionPane.showMessageDialog(null, "请输入端口号");
			return false;
		}
	}

	public String getAccount() {
		return account;
	}
	

	private boolean setAccount() {
		if(!accountText.getText().isEmpty()){
			this.account = accountText.getText();
			return true;
		}
		else{
			JOptionPane.showMessageDialog(null, "请输入账号");
			return false;
		}
	}	

	public String getPassword() {
		return password;
	}
	

	private boolean setPassword() {
		if(!passwordField.getText().isEmpty()){
			this.password = passwordField.getText();
			return true;
		}
		else{
			JOptionPane.showMessageDialog(null, "请输入密码");
			return false;
		}
	}
	
	public void setConnStatus(){
		this.status.setText("连接到服务器");
	}
	
	public void setDisConnStatus(){
		this.status.setText("未连接");
	}

	/**
	 * Create the frame.
	 */
	public ClientLogIN() {
		setResizable(false);
		setTitle("\u5BA2\u6237\u7AEF\u767B\u5F55");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 200, 400, 280);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBounds(31, 35, 145, 177);
		panel.setBorder(new TitledBorder(null, "\u670D\u52A1\u5668\u8BBE\u7F6E", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.setLayout(null);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblIp = new JLabel("IP\u5730\u5740");
		lblIp.setFont(new Font("新宋体", Font.PLAIN, 12));
		lblIp.setBounds(10, 35, 54, 15);
		panel.add(lblIp);
		
		iptext = new JTextField();
		iptext.setText("127.0.0.1");
		iptext.setBounds(10, 49, 114, 21);
		panel.add(iptext);
		iptext.setColumns(10);
		
		JLabel label_1 = new JLabel("\u7AEF\u53E3\u53F7");
		label_1.setFont(new Font("新宋体", Font.PLAIN, 12));
		label_1.setBounds(10, 80, 54, 15);
		panel.add(label_1);
		
		porttext = new JTextField();
		porttext.setText("6666");
		porttext.setBounds(10, 96, 114, 21);
		panel.add(porttext);
		porttext.setColumns(10);
		
		status = new JTextField();
		status.setHorizontalAlignment(SwingConstants.CENTER);
		status.setText("\u672A\u8FDE\u63A5");
		status.setBackground(UIManager.getColor("CheckBox.light"));
		status.setBounds(24, 146, 82, 21);
		panel.add(status);
		status.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u767B\u5F55/\u6CE8\u518C", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(202, 35, 161, 177);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("\u8D26\u53F7");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("新宋体", Font.PLAIN, 13));
		label.setBounds(10, 49, 35, 21);
		panel_1.add(label);
		
		accountText = new JTextField();
		accountText.setColumns(10);
		accountText.setBounds(55, 49, 94, 21);
		panel_1.add(accountText);
		
		JLabel label_2 = new JLabel("\u5BC6\u7801");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("新宋体", Font.PLAIN, 13));
		label_2.setBounds(10, 96, 35, 21);
		panel_1.add(label_2);
		
		logINBut = new JButton("\u767B\u5F55");
		logINBut.addMouseListener(new MouseAdapter() {
			@Override
			//客户端登录
			public void mouseClicked(MouseEvent e) {
				if(setIpAddress() && setPort() && setAccount() && setPassword()){
					ClientSocket cs = new ClientSocket(IPAddress, port,account, password);
//					cs.setFlag(true);
					cs.start();
				}
			}
		});
		logINBut.setBounds(13, 144, 65, 23);
		panel_1.add(logINBut);
		
		RegisterBut = new JButton("\u6CE8\u518C");
		RegisterBut.addMouseListener(new MouseAdapter() {
			@Override
			//新用户注册
			public void mouseClicked(MouseEvent e) {
				if(setIpAddress() && setPort()){
					//新建线程
					ClientSocket cs = new ClientSocket(IPAddress, port);
//					cs.setFlag(false);		//注册
					cs.start();
				}
			}
		});
		RegisterBut.setBounds(84, 144, 65, 23);
		panel_1.add(RegisterBut);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(55, 96, 94, 21);
		panel_1.add(passwordField);
	}
}
