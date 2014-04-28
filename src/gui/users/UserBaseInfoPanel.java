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
	
	private int tag;  //0��ʾ�޸ģ�1��ʾ�����û�
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
		gradeToStringMap.put(0, "��ʦ");
		gradeToStringMap.put(1, "��һ");
		gradeToStringMap.put(2, "���");
		gradeToStringMap.put(3, "����");
		gradeToStringMap.put(4, "����");
		
		authorityToStringMap = new HashMap<Integer, String>();
		authorityToStringMap.put(0, "ѧ��");
		authorityToStringMap.put(1, "�ο���ʦ");
		authorityToStringMap.put(2, "����Ա");
		authorityToStringMap.put(3, "����Ա");
		authorityToStringMap.put(4, "����������Ա");
		authorityToStringMap.put(5, "ϵͳ����Ա");
		
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
		nameInfoLabel.setText("���Ȳ��ó���4������");
		nameInfoLabel.setVisible(false);
		passwordInfoLabel = new JLabel();
		passwordInfoLabel.setBounds(new Rectangle(94, 122, 171, 17));
		passwordInfoLabel.setForeground(Color.red);
		passwordInfoLabel.setText("���Ȳ��ó���16λ��ĸ������");
		passwordInfoLabel.setVisible(false);
		usernameInfoLabel = new JLabel();
		usernameInfoLabel.setBounds(new Rectangle(95, 64, 170, 18));
		usernameInfoLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		usernameInfoLabel.setForeground(Color.red);
		usernameInfoLabel.setText("���Ȳ��ó���10λ��ĸ������");
		usernameInfoLabel.setVisible(false);
		authorityLabel = new JLabel();
		authorityLabel.setHorizontalAlignment(SwingConstants.CENTER);
		authorityLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		authorityLabel.setLocation(new Point(15, 270));
		authorityLabel.setSize(new Dimension(70, 30));
		authorityLabel.setText("Ȩ�޼���");
		gradeLabel = new JLabel();
		gradeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gradeLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		gradeLabel.setLocation(new Point(15, 210));
		gradeLabel.setSize(new Dimension(70, 30));
		gradeLabel.setText("�꼶");
		nameLabel = new JLabel();
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		nameLabel.setLocation(new Point(15, 150));
		nameLabel.setSize(new Dimension(70, 30));
		nameLabel.setText("����(ʵ��)");
		passwordLabel = new JLabel();
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		passwordLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		passwordLabel.setLocation(new Point(15, 91));
		passwordLabel.setSize(new Dimension(70, 30));
		passwordLabel.setText("����");
		usernameLabel = new JLabel();
		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernameLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		usernameLabel.setLocation(new Point(15, 32));
		usernameLabel.setSize(new Dimension(70, 30));
		usernameLabel.setText("�û���(ID)");
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
			
			//�����޸��û���
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
			batchButton.setText("�������");
			batchButton.setLocation(new Point(28, 320));
			batchButton.setSize(new Dimension(89, 34));
			
			batchButton.setVisible(false);
			batchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			batchButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					
					//���������ļ���							
					JFileChooser fileChooser = new JFileChooser();		
					fileChooser.setDialogTitle("ѡ���ļ�");
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
								
								//��Ϣ��ȫ
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
						
						//�������ȷ���û���Ϣ
						if(userList.size() > 0)
						{
							if(Login.person.addUser(userList) == true)
							{
								JOptionPane.showMessageDialog(null, "��ӳɹ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);														
								reset();  //tag��Ϊ1���ɼ������
								
								//update
								DataSet.updateAllUserList();
								DataSet.mainFrame.userManagePanel.displayList();
							}
							else
							{
								JOptionPane.showMessageDialog(null, "���ʧ�ܣ��п��ܸ��û������ظ���������", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
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
			okButton.setText("ȷ���޸�");
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
						
					//ȷ������Ϸ�
					if(username != null && password != null && name != null && grade != -1 && authority != -1)
					{
						UserBaseInfo newUserBaseInfo = new UserBaseInfo();
						newUserBaseInfo.setUsername(username);
						newUserBaseInfo.setPassword(password);
						newUserBaseInfo.setName(name);
						newUserBaseInfo.setGrade(grade);
						newUserBaseInfo.setAuthority(authority);
							
						//�޸��û�
						if(tag == 0)
						{
							if(Login.person.reviseUser(newUserBaseInfo) == true)
							{
								JOptionPane.showMessageDialog(null, "�޸ĳɹ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
								
								//update
								setUser(newUserBaseInfo);
								
								DataSet.updateAllUserList();
								DataSet.mainFrame.userManagePanel.displayList();
							}
							else
							{
								JOptionPane.showMessageDialog(null, "�޸�ʧ�ܣ������û���������̫���ˣ�������", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
								
								//�ָ�
								usernameTextField.setText(userBaseInfo.getUsername());
								passwordTextField.setText(userBaseInfo.getPassword());
								nameTextField.setText(userBaseInfo.getName());
								gradeComboBox.setSelectedIndex(userBaseInfo.getGrade());
								authorityComboBox.setSelectedIndex(userBaseInfo.getAuthority());
							}
						}
						//�����û�
						else
						{
							if(Login.person.addUser(newUserBaseInfo) == true)
							{
								JOptionPane.showMessageDialog(null, "��ӳɹ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);														
								reset();  //tag��Ϊ1���ɼ������
								
								//update
								DataSet.updateAllUserList();
								DataSet.mainFrame.userManagePanel.displayList();
							}
							else 
							{
								JOptionPane.showMessageDialog(null, "���ʧ�ܣ��п��ܸ��û����Ѵ��ڣ�������", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
								//setUser(newUser);  //����֮ǰ���
								usernameTextField.setText("");  //���username
							}
						}						
							
					}
					//���벻�Ϸ�
					else 
					{
						JOptionPane.showMessageDialog(null, "���벻�Ϸ�������Ϊ��", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
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
			cancelButton.setText("ȡ���޸�");
			cancelButton.setSize(new Dimension(89, 34));
			cancelButton.setLocation(new Point(155, 370));
			cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			cancelButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					
					//������޸��û�
					if(tag == 0)
					{
						if(userBaseInfo != null)
						{
							//�ָ�
							usernameTextField.setText(userBaseInfo.getUsername());
							passwordTextField.setText(userBaseInfo.getPassword());
							nameTextField.setText(userBaseInfo.getName());
							gradeComboBox.setSelectedIndex(userBaseInfo.getGrade());
							authorityComboBox.setSelectedIndex(userBaseInfo.getAuthority());
						}
					}
					//���������û�
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
	 * ���ñ��
	 * @param tag  0��ʾ�޸ģ�1��ʾ�����û�
	 */
	protected void setTag(int tag)
	{
		this.tag = tag;
		
		//ֻ���������û���ʱ��ɱ༭
		if(tag == 1)
		{
			usernameTextField.setEditable(true);
			
			usernameInfoLabel.setVisible(true);
			passwordInfoLabel.setVisible(true);
			nameInfoLabel.setVisible(true);
			
			okButton.setText("���");
			cancelButton.setText("ȡ��");
			
			batchButton.setVisible(true);
		}
		else
		{
			usernameTextField.setEditable(false);
			
			usernameInfoLabel.setVisible(false);
			passwordInfoLabel.setVisible(false);
			nameInfoLabel.setVisible(false);
			
			okButton.setText("ȷ���޸�");
			cancelButton.setText("ȡ���޸�");
			
			batchButton.setVisible(false);
		}
	}
	
	
	/**
	 * ����user��Ϣ��������ʾ��
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
			
			//�����޸Ĺ���Ա�����޸�״̬�£�
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
	 * ����
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
		
		//ֻ���������û���ʱ��ɱ༭
		/*if(tag == 1)
			usernameTextField.setEditable(true);
		else
			usernameTextField.setEditable(false);*/
	}

}
