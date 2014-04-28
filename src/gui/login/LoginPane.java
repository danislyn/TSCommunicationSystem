package gui.login;

import gui.data.DataSet;
import gui.main.MainFrame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import server.reflection.Call;
import client.login.Login;
import client.users.AdminTeacher;
import client.users.CounsellorTeacher;
import client.users.DeanTeacher;
import client.users.DepartmentTeacher;
import client.users.MajorTeacher;
import client.users.Student;

public class LoginPane extends JPanel {
	/**
	 * 登陆界面的主要panel
	 */
	private static final long serialVersionUID = 1L;
	
	//界面的显示
	private JFrame fatherFrame = null;
	private JLabel usernameLabel = null;
	private JLabel passwordLabel = null;
	private JTextField usernameTextField = null;
	private JPasswordField passwordPasswordField = null;
	private JLabel locationLabel = null;
	private JLabel gulouLabel = null;
	private JLabel xianlinLabel = null;
	private JRadioButton gulouRadioButton = null;
	private JRadioButton xianlinRadioButton = null;
	private JButton loginButton = null;
	private JButton offlineButton = null;
	private JButton exit=null;
	
	//获得数据时存放的变量
	private String username =  null;
	private String password = null;
	private int location = 0;
	/**
	 * 构造函数
	 * @param f 父容器
	 */
	LoginPane(JFrame f){
		this.setLayout(null);
		this.fatherFrame = f;
		initilize();
	}
	/**
	 * 重写父类的方法画背景
	 */
	
	public void paintComponent(Graphics g) { 
        g.setColor(Color.blue); 
        ImageIcon img=null;
		img = new ImageIcon("images/login3.png");
		g.drawImage(img.getImage(),0,0,null); 
    }
	
	/**
	 * 初始化界面
	 */
	
	private void initilize(){
		usernameLabel=new JLabel("帐号：");
		usernameLabel.setBounds(new Rectangle(210, 160, 60, 31));
		usernameLabel.setPreferredSize(new Dimension(26, 20));
		usernameLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernameLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		
		passwordLabel = new JLabel("密码：");
		passwordLabel.setBounds(new Rectangle(210, 210, 60, 31));
		passwordLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		passwordLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		
		xianlinLabel = new JLabel("莫愁");
		xianlinLabel.setBounds(new Rectangle(431, 260, 28, 18));
		
		gulouLabel = new JLabel("浦口");
		gulouLabel.setBounds(new Rectangle(355, 260, 30, 18));
		
		locationLabel = new JLabel("清选择登录地点");
		locationLabel.setBounds(new Rectangle(207, 260, 97, 18));
		
		
		add(usernameLabel, null);
		add(passwordLabel, null);
		add(getUsernameTextField(), null);
		add(getPasswordPasswordField(), null);
		add(locationLabel, null);
		add(gulouLabel, null);
		add(xianlinLabel, null);
		add(getGulouRadioButton(), null);
		add(getXianlinRadioButton(), null);
//	    add(getexitButton(), null);
		add(getLoginButton(), null);
		add(getOfflineButton(), null);
		
	}
	
	/**
	 * 初始化用户名编辑框
	 * @return 返回初始化的用户名编辑框
	 */
	private JTextField getUsernameTextField() {
		if (usernameTextField == null) {
			usernameTextField = new JTextField();
			usernameTextField.setBounds(new Rectangle(292, 160, 169, 26));
		}
		return usernameTextField;
	}
	
	/**
	 * 初始化密码编辑框
	 * @return 返回初始化的密码编辑框
	 */
	private JPasswordField getPasswordPasswordField() {
		if (passwordPasswordField == null) {
			passwordPasswordField = new JPasswordField();
			passwordPasswordField.setBounds(new Rectangle(292, 210, 167, 27));
		}
		return passwordPasswordField;
	}
	
	private JRadioButton getGulouRadioButton() {
		if (gulouRadioButton == null) {
			gulouRadioButton = new JRadioButton();
			gulouRadioButton.setBounds(new Rectangle(324, 260, 21, 21));
			gulouRadioButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					xianlinRadioButton.setSelected(false);
				}
			});
		}
		return gulouRadioButton;
	}
	
	private JRadioButton getXianlinRadioButton() {
		if (xianlinRadioButton == null) {
			xianlinRadioButton = new JRadioButton();
			xianlinRadioButton.setBounds(new Rectangle(402, 260, 21, 21));
			xianlinRadioButton.setText("");
			xianlinRadioButton.setSelected(true);
			xianlinRadioButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// TODO Auto-generated Event stub actionPerformed()
					gulouRadioButton.setSelected(false);
				}
			});
		}
		return xianlinRadioButton;
	}
	private JButton getLoginButton() {
		if (loginButton == null) {
			loginButton = new JButton("登录"){
				/**
				 * 重画登录按钮
				 */
				private static final long serialVersionUID = 1L;
				{
				   setContentAreaFilled(false);
				   setOpaque(false);
				}
				protected void paintComponent(Graphics g){
				    ImageIcon imageicon=new ImageIcon("images/group.png");
				    g.drawImage(imageicon.getImage(), 0, 0,this);
				    super.paintComponent(g);
					
				}
				protected void paintBorder(Graphics g){}
			};
			
			loginButton.setBounds(new Rectangle(410, 310, 100, 28));
			loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			loginButton.addActionListener(new java.awt.event.ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// TODO Auto-generated Event stub actionPerformed()
					
					//get user input
					username = usernameTextField.getText();
					password = passwordPasswordField.getText();
					
					if(gulouRadioButton.isSelected())
						location=2;
					else{if(xianlinRadioButton.isSelected())
						location=1;
					else
						location=0;
					}
					
					
					//start socket
			    	Login.start();
					
					Call call=new Call("server.interfaces.UserOperationInterface",
							"loginCheck",new Class[]{String.class, String.class, int.class}, 
							new Object[]{username, password, location});
					Login.writeCall(call);
					Login.readCall();
					
					int authority = (Integer)Login.resultCall.getResult();
					
					//username and password right
					if(authority > -1)
					{
						if(authority == 0)
							Login.person = new Student(username, password, authority, location);
						else if(authority == 1)
							Login.person = new MajorTeacher(username, password, authority, location);
						else if(authority == 2)
							Login.person = new CounsellorTeacher(username, password, authority, location);
						else if(authority == 3)
							Login.person = new DepartmentTeacher(username, password, authority, location);
						else if(authority == 4)
							Login.person = new DeanTeacher(username, password, authority, location);
						else if(authority == 5)
							Login.person = new AdminTeacher(username, password, authority, location);
							
						
						setVisible(false);
						fatherFrame.dispose();
						
						DataSet.mainFrame = new MainFrame((authority == 5));
						DataSet.mainFrame.setVisible(true);
						
						
						//未读邮件提示
						int unreadMailSum = Login.person.getUnreadReceivedMailSum();
						if(unreadMailSum > 0)
						{
							JOptionPane.showMessageDialog(null, "您有" + unreadMailSum + "条未读邮件，请单击\"我的信箱\"查看", "提示",JOptionPane.INFORMATION_MESSAGE);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "用户名密码错误，请重试", "提示", JOptionPane.INFORMATION_MESSAGE);
						usernameTextField.setText("");
						passwordPasswordField.setText("");
					}
							
				}
			});
		}
		return loginButton;
	}

	/**
	 * This method initializes offlineButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getOfflineButton() {
		if (offlineButton == null) {
			offlineButton = new JButton(){
				/**
				 * 重画按钮
				 */
				private static final long serialVersionUID = 1L;

				{
				setContentAreaFilled(false);
				setOpaque(false);
				}
				protected void paintComponent(Graphics g){
				ImageIcon imageicon=new ImageIcon("images/group.png");
				g.drawImage(imageicon.getImage(), 0, 0,this);
				super.paintComponent(g);
					
				}
				
				protected void paintBorder(Graphics g){}
			};
			offlineButton.setText("脱机登录");
			offlineButton.setLocation(new Point(310, 310));
			offlineButton.setSize(new Dimension(100, 28));
			
			offlineButton.setVisible(false);
		}
		return offlineButton;
	}
	
/*	private JButton getexitButton(){
		if(exit==null){
			exit=new JButton();
			exit.setIcon(new ImageIcon("images/exit.png"));
			exit.setBounds(new Rectangle(560,80,20,20));
		    exit.setBorder(null);
		    exit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					System.exit(0);
				}
			    });
		}
		return exit;
	}*/
	

}
