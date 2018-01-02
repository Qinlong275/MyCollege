package Client;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.swing.JPasswordField;

/**
 * @demostrate
 * @author zhaojun
 * @number 15020310086
 * @version
 */
public class ClientRegister extends JFrame implements Runnable{

	private JPanel contentPane;
	private JTextField username;
	private JButton button;
	private JPasswordField passwordField;
	
	private String password;
	private String account;
	private ClientRegister cr ;
	private boolean flag = true;
	private ClientLogIN cln;
	private ClientSocket cs;
	private BufferedReader reader;
	
	@Override
	public void run() {
		while(flag);
	}
	
	public void setCS(ClientSocket cs){
		this.cs = cs;
	}
	
	public void setCLN(ClientLogIN cln){
		this.cln = cln;
	}
	
	public void setCR(ClientRegister cr ){
		this.cr = cr;
	}

	private boolean setAccount(){
		if(!username.getText().isEmpty()){
			this.account = username.getText();
			return true;
		}
		else{
			JOptionPane.showMessageDialog(null, "请输入用户名");
			return false;
		}
	}
	
	private boolean setPassword(){
		if(!passwordField.getText().isEmpty()){
			this.password = passwordField.getText();
			return true;
		}
		else{
			JOptionPane.showMessageDialog(null, "请输入密码");
			return false;
		}
	}

	/**
	 * Create the frame.
	 */
	public ClientRegister() {
		
		setTitle("\u7528\u6237\u6CE8\u518C");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 200, 342, 236);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u8D26\u53F7");
		label.setFont(new Font("新宋体", Font.PLAIN, 12));
		label.setBounds(76, 45, 54, 21);
		contentPane.add(label);
		
		username = new JTextField();
		username.setFont(UIManager.getFont("TextField.font"));
		username.setBounds(140, 45, 115, 21);
		contentPane.add(username);
		username.setColumns(10);
		
		JLabel label_1 = new JLabel("\u5BC6\u7801");
		label_1.setFont(new Font("新宋体", Font.PLAIN, 12));
		label_1.setBounds(76, 89, 54, 21);
		contentPane.add(label_1);
		
		button = new JButton("\u6CE8\u518C");
		button.setFont(new Font("新宋体", Font.BOLD, 12));
		button.setBounds(137, 147, 63, 25);
		contentPane.add(button);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(140, 89, 115, 21);
		contentPane.add(passwordField);
		
		button.addMouseListener(new MouseAdapter() {
			@Override
			//用户注册
			public void mouseClicked(MouseEvent e) {
				if(setAccount() && setPassword()){
					//向服务器发送新用户信息的专用标志
					cs.send("@@@ " + account + " " + password + " @@@");    
					try {		//读取服务器返回的信息
						reader = new BufferedReader(
								new InputStreamReader(
									cs.getSocket().getInputStream(),"UTF-8"));
						if(reader.readLine().equals("@@@REGOK@@@")){		
							JOptionPane.showMessageDialog(null, "注册成功");
							cr.setVisible(false);
							flag = false;				//终止当前线程
							cln.setDisConnStatus();
						}
						else
							JOptionPane.showMessageDialog(null, "注册失败，请更换账号");

					} catch (UnsupportedEncodingException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					finally{
						try {
							cs.send("***CLOSED***");
							reader.close();
							reader = null;
							cs.getSocket().close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
					}
				}
				
			}
		});
	}
}
