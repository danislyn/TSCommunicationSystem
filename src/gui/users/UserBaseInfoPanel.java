package gui.users;

import gui.data.DataSet;

import javax.swing.JPanel;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import client.login.Login;

import beans.admin.UserBaseInfo;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class UserBaseInfoPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel usernameLabel = null;
	private JTextField usernameTextField = null;
	private JLabel passwordLabel = null;
	private JTextField passwordTextField = null;
	private JLabel nameLabel = null;
	private JTextField nameTextField = null;
	private JLabel gradeLabel = null;
	private JComboBox gradeComboBox = null;
	private JLabel authorityLabel = null;
	private JComboBox authorityComboBox = null;
	private JButton batchButton = null;
	private JButton okButton = null;
	private JButton cancelButton = null;
	
	private UserBaseInfo userBaseInfo;  //  @jve:decl-index=0:
	
	private HashMap<Integer, String> gradeToStringMap;
	private HashMap<Integer, String> authorityToStringMap;
	
	private int tag;  //0表示修改，1表示新增用户
	private JLabel usernameInfoLabel = null;
	private JLabel passwordInfoLabel = null;
	private JLabel nameInfoLabel = null;
	

	/**
	 * This is the default constructor
	 */
	public UserBaseInfoPanel() {
		super();
		this.setOpaque(false);
		
		tag = 0;
		
		gradeToStringMap = new HashMap<Integer, String>();		
		gradeToStringMap.put(0, "老师");
		gradeToStringMap.put(1, "大一");
		gradeToStringMap.put(2, "大二");
		gradeToStringMap.put(3, "大三");
		gradeToStringMap.put(4, "大四");
		
		authorityToStringMap = new HashMap<Integer, String>();
		authorityToStringMap.put(0, "学生");
		authorityToStringMap.put(1, "任课老师");
		authorityToStringMap.put(2, "辅导员");
		authorityToStringMap.put(3, "教务员");
		authorityToStringMap.put(4, "行政工作人员");
		authorityToStringMap.put(5, "系统管理员");
		
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		nameInfoLabel = new JLabel();
		nameInfoLabel.setBounds(new Rectangle(94, 181, 170, 18));
		nameInfoLabel.setForeground(Color.red);
		nameInfoLabel.setText("长度不得超过4个汉字");
		nameInfoLabel.setVisible(false);
		passwordInfoLabel = new JLabel();
		passwordInfoLabel.setBounds(new Rectangle(94, 122, 171, 17));
		passwordInfoLabel.setForeground(Color.red);
		passwordInfoLabel.setText("长度不得超过16位字母或数字");
		passwordInfoLabel.setVisible(false);
		usernameInfoLabel = new JLabel();
		usernameInfoLabel.setBounds(new Rectangle(95, 64, 170, 18));
		usernameInfoLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		usernameInfoLabel.setForeground(Color.red);
		usernameInfoLabel.setText("长度不得超过10位字母或数字");
		usernameInfoLabel.setVisible(false);
		authorityLabel = new JLabel();
		authorityLabel.setHorizontalAlignment(SwingConstants.CENTER);
		authorityLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		authorityLabel.setLocation(new Point(15, 270));
		authorityLabel.setSize(new Dimension(70, 30));
		authorityLabel.setText("权限级别");
		gradeLabel = new JLabel();
		gradeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gradeLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		gradeLabel.setLocation(new Point(15, 210));
		gradeLabel.setSize(new Dimension(70, 30));
		gradeLabel.setText("年级");
		nameLabel = new JLabel();
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		nameLabel.setLocation(new Point(15, 150));
		nameLabel.setSize(new Dimension(70, 30));
		nameLabel.setText("姓名(实名)");
		passwordLabel = new JLabel();
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		passwordLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		passwordLabel.setLocation(new Point(15, 91));
		passwordLabel.setSize(new Dimension(70, 30));
		passwordLabel.setText("密码");
		usernameLabel = new JLabel();
		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernameLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		usernameLabel.setLocation(new Point(15, 32));
		usernameLabel.setSize(new Dimension(70, 30));
		usernameLabel.setText("用户名(ID)");
		this.setSize(281, 425);
		this.setLayout(null);
		this.add(usernameLabel, null);
		this.add(getUsernameTextField(), null);
		this.add(passwordLabel, null);
		this.add(getPasswordTextField(), null);
		this.add(nameLabel, null);
		this.add(getNameTextField(), null);
		this.add(gradeLabel, null);
		this.add(getGradeComboBox(), null);
		this.add(authorityLabel, null);
		this.add(getAuthorityComboBox(), null);
		this.add(getBatchButton(), null);
		this.add(getOkButton(), null);
		this.add(getCancelButton(), null);
		this.add(usernameInfoLabel, null);
		this.add(passwordInfoLabel, null);
		this.add(nameInfoLabel, null);
	}

	/**
	 * This method initializes usernameTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getUsernameTextField() {
		if (usernameTextField == null) {
			usernameTextField = new JTextField();
			usernameTextField.setLocation(new Point(95, 32));
			usernameTextField.setSize(new Dimension(170, 30));
			
			//不可修改用户名
			usernameTextField.setEditable(false);
		}
		return usernameTextField;
	}

	/**
	 * This method initializes passwordTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getPasswordTextField() {
		if (passwordTextField == null) {
			passwordTextField = new JTextField();
			passwordTextField.setLocation(new Point(95, 91));
			passwordTextField.setSize(new Dimension(170, 30));
		}
		return passwordTextField;
	}

	/**
	 * This method initializes nameTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getNameTextField() {
		if (nameTextField == null) {
			nameTextField = new JTextField();
			nameTextField.setLocation(new Point(95, 150));
			nameTextField.setSize(new Dimension(170, 30));
		}
		return nameTextField;
	}

	/**
	 * This method initializes gradeComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getGradeComboBox() {
		if (gradeComboBox == null) {
			gradeComboBox = new JComboBox();
			gradeComboBox.setLocation(new Point(95, 210));
			gradeComboBox.setSize(new Dimension(145, 30));
			
			for(int i=0; i<gradeToStringMap.keySet().size(); i++)
			{
				gradeComboBox.addItem(gradeToStringMap.get(i));				
			}
			
			gradeComboBox.setSelectedIndex(-1);
		}
		return gradeComboBox;
	}

	/**
	 * This method initializes authorityComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getAuthorityComboBox() {
		if (authorityComboBox == null) {
			authorityComboBox = new JComboBox();
			authorityComboBox.setLocation(new Point(95, 270));
			authorityComboBox.setSize(new Dimension(145, 30));
			
			for(int i=0; i<authorityToStringMap.keySet().size(); i++)
			{
				authorityComboBox.addItem(authorityToStringMap.get(i));
			}
			
			authorityComboBox.setSelectedIndex(-1);
		}
		return authorityComboBox;
	}
		
	/**
	 * This method initializes batchButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBatchButton() {
		if (batchButton == null) {
			batchButton = new JButton(){
				{
					setContentAreaFilled(false);
					setOpaque(false);
				}
				public void paintComponent(Graphics g) { 
			        g.setColor(Color.blue); 
			        ImageIcon img=null;
					img = new ImageIcon("images/buttonbg.png");
					g.drawImage(img.getImage(),0,0,null); 
					super.paintComponent(g);
			    }
			};
			batchButton.setText("批量添加");
			batchButton.setLocation(new Point(28, 320));
			batchButton.setSize(new Dimension(89, 34));
			
			batchButton.setVisible(false);
			batchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			batchButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					
					//批量（读文件）							
					JFileChooser fileChooser = new JFileChooser();		
					fileChooser.setDialogTitle("选择文件");
					int result = fileChooser.showOpenDialog(null);
					
					if(result == JFileChooser.APPROVE_OPTION)
					{
						ArrayList<UserBaseInfo> userList = new ArrayList<UserBaseInfo>();
						
						try {
							File file = fileChooser.getSelectedFile();
							
							BufferedReader br = new BufferedReader(new FileReader(file));
							
							String temp = null;
							
							while((temp = br.readLine()) != null)
							{
								String[] tempStrings = temp.split(" ");
								
								//信息不全
								if(tempStrings.length < 5)
									continue;
								
								UserBaseInfo user = new UserBaseInfo();
								
								user.setUsername(tempStrings[0]);
								user.setPassword(tempStrings[1]);
								user.setAuthority(Integer.parseInt(tempStrings[2]));
								user.setName(tempStrings[3]);
								user.setGrade(Integer.parseInt(tempStrings[4]));
								
								userList.add(user);
								
							}
							
						} catch (Exception ex) {
							// TODO: handle exception
							ex.printStackTrace();
						}
						
						//如果有正确的用户信息
						if(userList.size() > 0)
						{
							if(Login.person.addUser(userList) == true)
							{
								JOptionPane.showMessageDialog(null, "添加成功", "提示", JOptionPane.INFORMATION_MESSAGE);														
								reset();  //tag仍为1，可继续添加
								
								//update
								DataSet.updateAllUserList();
								DataSet.mainFrame.userManagePanel.displayList();
							}
							else
							{
								JOptionPane.showMessageDialog(null, "添加失败，有可能该用户名有重复！请重试", "提示", JOptionPane.INFORMATION_MESSAGE);
							}
							
						}
					}
				}
			});
		}
		return batchButton;
	}

	/**
	 * This method initializes okButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getOkButton() {
		if (okButton == null) {
			okButton = new JButton(){
				{
					setContentAreaFilled(false);
					setOpaque(false);
				}
				public void paintComponent(Graphics g) { 
			        g.setColor(Color.blue); 
			        ImageIcon img=null;
					img = new ImageIcon("images/buttonbg.png");
					g.drawImage(img.getImage(),0,0,null); 
					super.paintComponent(g);
			    }
			};
			okButton.setText("确认修改");
			okButton.setLocation(new Point(28, 370));
			okButton.setSize(new Dimension(89, 34));
			okButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			okButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					
					String username = usernameTextField.getText();
					String password = passwordTextField.getText();
					String name = nameTextField.getText();
					int grade = gradeComboBox.getSelectedIndex();
					int authority = authorityComboBox.getSelectedIndex();
						
					//确保输入合法
					if(username != null && password != null && name != null && grade != -1 && authority != -1)
					{
						UserBaseInfo newUserBaseInfo = new UserBaseInfo();
						newUserBaseInfo.setUsername(username);
						newUserBaseInfo.setPassword(password);
						newUserBaseInfo.setName(name);
						newUserBaseInfo.setGrade(grade);
						newUserBaseInfo.setAuthority(authority);
							
						//修改用户
						if(tag == 0)
						{
							if(Login.person.reviseUser(newUserBaseInfo) == true)
							{
								JOptionPane.showMessageDialog(null, "修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);
								
								//update
								setUser(newUserBaseInfo);
								
								DataSet.updateAllUserList();
								DataSet.mainFrame.userManagePanel.displayList();
							}
							else
							{
								JOptionPane.showMessageDialog(null, "修改失败，可能用户名或姓名太长了！请重试", "提示", JOptionPane.INFORMATION_MESSAGE);
								
								//恢复
								usernameTextField.setText(userBaseInfo.getUsername());
								passwordTextField.setText(userBaseInfo.getPassword());
								nameTextField.setText(userBaseInfo.getName());
								gradeComboBox.setSelectedIndex(userBaseInfo.getGrade());
								authorityComboBox.setSelectedIndex(userBaseInfo.getAuthority());
							}
						}
						//新增用户
						else
						{
							if(Login.person.addUser(newUserBaseInfo) == true)
							{
								JOptionPane.showMessageDialog(null, "添加成功", "提示", JOptionPane.INFORMATION_MESSAGE);														
								reset();  //tag仍为1，可继续添加
								
								//update
								DataSet.updateAllUserList();
								DataSet.mainFrame.userManagePanel.displayList();
							}
							else 
							{
								JOptionPane.showMessageDialog(null, "添加失败，有可能该用户名已存在！请重试", "提示", JOptionPane.INFORMATION_MESSAGE);
								//setUser(newUser);  //保留之前添的
								usernameTextField.setText("");  //清空username
							}
						}						
							
					}
					//输入不合法
					else 
					{
						JOptionPane.showMessageDialog(null, "输入不合法，不能为空", "提示", JOptionPane.INFORMATION_MESSAGE);
					}
					
				}
			});
		}
		return okButton;
	}

	/**
	 * This method initializes cancelButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getCancelButton() {
		if (cancelButton == null) {
			cancelButton = new JButton(){
				{
					setContentAreaFilled(false);
					setOpaque(false);
				}
				public void paintComponent(Graphics g) { 
			        g.setColor(Color.blue); 
			        ImageIcon img=null;
					img = new ImageIcon("images/buttonbg.png");
					g.drawImage(img.getImage(),0,0,null); 
					super.paintComponent(g);
			    }
			};
			cancelButton.setText("取消修改");
			cancelButton.setSize(new Dimension(89, 34));
			cancelButton.setLocation(new Point(155, 370));
			cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			cancelButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					
					//如果是修改用户
					if(tag == 0)
					{
						if(userBaseInfo != null)
						{
							//恢复
							usernameTextField.setText(userBaseInfo.getUsername());
							passwordTextField.setText(userBaseInfo.getPassword());
							nameTextField.setText(userBaseInfo.getName());
							gradeComboBox.setSelectedIndex(userBaseInfo.getGrade());
							authorityComboBox.setSelectedIndex(userBaseInfo.getAuthority());
						}
					}
					//如果是添加用户
					else
					{
						usernameTextField.setText("");
						passwordTextField.setText("");
						nameTextField.setText("");
						gradeComboBox.setSelectedIndex(-1);
						authorityComboBox.setSelectedIndex(-1);					
					}
				}
			});
		}
		return cancelButton;
	}
	
	
	/**
	 * 设置标记
	 * @param tag  0表示修改，1表示新增用户
	 */
	protected void setTag(int tag)
	{
		this.tag = tag;
		
		//只有在新增用户的时候可编辑
		if(tag == 1)
		{
			usernameTextField.setEditable(true);
			
			usernameInfoLabel.setVisible(true);
			passwordInfoLabel.setVisible(true);
			nameInfoLabel.setVisible(true);
			
			okButton.setText("添加");
			cancelButton.setText("取消");
			
			batchButton.setVisible(true);
		}
		else
		{
			usernameTextField.setEditable(false);
			
			usernameInfoLabel.setVisible(false);
			passwordInfoLabel.setVisible(false);
			nameInfoLabel.setVisible(false);
			
			okButton.setText("确认修改");
			cancelButton.setText("取消修改");
			
			batchButton.setVisible(false);
		}
	}
	
	
	/**
	 * 设置user信息（用于显示）
	 * @param userBaseInfo
	 */
	protected void setUser(UserBaseInfo userBaseInfo)
	{
		this.userBaseInfo = userBaseInfo;
		
		if(userBaseInfo != null)
		{
			usernameTextField.setText(userBaseInfo.getUsername());
			passwordTextField.setText(userBaseInfo.getPassword());
			nameTextField.setText(userBaseInfo.getName());
			
			gradeComboBox.setSelectedIndex(userBaseInfo.getGrade());
			authorityComboBox.setSelectedIndex(userBaseInfo.getAuthority());
			
			//不可修改管理员（在修改状态下）
			if(userBaseInfo.getAuthority() == 5 && tag == 0)
			{
				//usernameTextField.setEditable(false);
				passwordTextField.setEditable(false);
				nameTextField.setEditable(false);
				gradeComboBox.setEditable(false);
				authorityComboBox.setEditable(false);
			}			
		}
	}
	
	/**
	 * 重置
	 */
	protected void reset()
	{
		userBaseInfo = null;
		
		usernameTextField.setText("");
		passwordTextField.setText("");
		nameTextField.setText("");
		
		gradeComboBox.setSelectedIndex(-1);
		authorityComboBox.setSelectedIndex(-1);
		
		passwordTextField.setEditable(true);
		nameTextField.setEditable(true);
		gradeComboBox.setEditable(true);
		authorityComboBox.setEditable(true);
		
		//只有在新增用户的时候可编辑
		/*if(tag == 1)
			usernameTextField.setEditable(true);
		else
			usernameTextField.setEditable(false);*/
	}

}
