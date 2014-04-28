package gui.mail;

import gui.data.DataSet;

import javax.swing.JPanel;

import beans.mail.Mail;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JButton;

import client.login.Login;

public class WriteMailPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JLabel receiverLabel = null;
	private JTextField receiverTextField = null;
	private JLabel subjectLabel = null;
	private JTextField subjectTextField = null;
	private JLabel typeLabel = null;
	private JRadioButton normalRadioButton = null;
	private JLabel normalLabel = null;
	private JRadioButton needFeedbackRadioButton = null;
	private JLabel needFeedbackLabel = null;
	private JTextArea contentTextArea = null;
	private JButton addressButton = null;
	private JButton fuzzyButton = null;
	private JButton sendButton = null;
	private JButton cancelButton = null;
	
	private JScrollPane jScrollPane = null;
	private JPanel rightPanel = null;  //�����л�
	
	private Mail mail = null;  //  @jve:decl-index=0:

	private JButton backButton = null;

	private AddressPanel addressPanel = null;
	private FuzzyPanel fuzzyPanel = null;
	
	private int receiverTag;
	
	private final int NORMAL = 0;
	private final int ADDRESS = 1;
	private final int FUZZY = 2;
	
	private ArrayList<String> receiverUsernameList;  //  @jve:decl-index=0:
	
	/**
	 * This is the default constructor
	 */
	public WriteMailPanel() {
		super();
		setOpaque(false);
		
		//Ĭ��tag=0;
		receiverTag = NORMAL;
		
		receiverUsernameList = new ArrayList<String>();
		
		//��ʼ��������ʱ�ò�����panel
		addressPanel = new AddressPanel();
		fuzzyPanel = new FuzzyPanel();
		
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		needFeedbackLabel = new JLabel();
		needFeedbackLabel.setBounds(new Rectangle(239, 103, 86, 31));
		needFeedbackLabel.setHorizontalAlignment(SwingConstants.CENTER);
		needFeedbackLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		needFeedbackLabel.setText("������ʼ�");
		normalLabel = new JLabel();
		normalLabel.setHorizontalAlignment(SwingConstants.CENTER);
		normalLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		normalLabel.setLocation(new Point(121, 103));
		normalLabel.setSize(new Dimension(78, 30));
		normalLabel.setText("��ͨ�ʼ�");
		typeLabel = new JLabel();
		typeLabel.setBounds(new Rectangle(17, 103, 69, 30));
		typeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		typeLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		typeLabel.setText("����");
		subjectLabel = new JLabel();
		subjectLabel.setHorizontalAlignment(SwingConstants.CENTER);
		subjectLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		subjectLabel.setLocation(new Point(17, 60));
		subjectLabel.setSize(new Dimension(67, 30));
		subjectLabel.setText("����");
		receiverLabel = new JLabel();
		receiverLabel.setHorizontalAlignment(SwingConstants.CENTER);
		receiverLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		receiverLabel.setLocation(new Point(16, 18));
		receiverLabel.setSize(new Dimension(68, 30));
		receiverLabel.setText("�ռ���ID");
		this.setSize(458, 444);
		this.setLayout(null);
		this.add(receiverLabel, null);
		this.add(getSenderTextField(), null);
		this.add(subjectLabel, null);
		this.add(getSubjectTextField(), null);
		this.add(typeLabel, null);
		this.add(getNormalRadioButton(), null);
		this.add(normalLabel, null);
		this.add(getNeedFeedbackRadioButton(), null);
		this.add(needFeedbackLabel, null);
		this.add(getAddressButton(), null);
		this.add(getFuzzyButton(), null);
		this.add(getRightPanel(), null);
		this.add(getSendButton(), null);
		this.add(getCancelButton(), null);
		this.add(getBackButton(), null);
		this.add(getJScrollPane(), null);
	}
	
	
	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane()
	{
		if(jScrollPane == null)
		{
			jScrollPane = new JScrollPane(getContentTextArea());
			jScrollPane.setOpaque(false);
			jScrollPane.setBounds(new Rectangle(18, 140, 417, 261));
			jScrollPane.setViewportView(getContentTextArea());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes senderTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getSenderTextField() {
		if (receiverTextField == null) {
			receiverTextField = new JTextField();
			receiverTextField.setLocation(new Point(96, 18));
			receiverTextField.setSize(new Dimension(233, 30));
			
			receiverTextField.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyTyped(java.awt.event.KeyEvent e) {
					//System.out.println("keyTyped()"); // TODO Auto-generated Event stub keyTyped()
					
					//�������rightPanel
					if(receiverTag != NORMAL)
					{
						rightPanel.removeAll();
						receiverTag = NORMAL;
						receiverUsernameList.clear();
						
						changeToSmallWidth();
						repaint();
					}
				}
			});
		}
		return receiverTextField;
	}

	/**
	 * This method initializes subjectTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getSubjectTextField() {
		if (subjectTextField == null) {
			subjectTextField = new JTextField();
			subjectTextField.setSize(new Dimension(231, 30));
			subjectTextField.setLocation(new Point(97, 60));
		}
		return subjectTextField;
	}

	/**
	 * This method initializes normalRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getNormalRadioButton() {
		if (normalRadioButton == null) {
			normalRadioButton = new JRadioButton();
			normalRadioButton.setSize(new Dimension(21, 21));
			normalRadioButton.setLocation(new Point(98, 107));
			
			//Ĭ��Ϊ��ͨ�ʼ�
			normalRadioButton.setSelected(true);
			
			normalRadioButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					
					needFeedbackRadioButton.setSelected(false);
				}
			});
		}
		return normalRadioButton;
	}

	/**
	 * This method initializes needFeedbackRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getNeedFeedbackRadioButton() {
		if (needFeedbackRadioButton == null) {
			needFeedbackRadioButton = new JRadioButton();
			needFeedbackRadioButton.setSize(new Dimension(21, 21));
			needFeedbackRadioButton.setLocation(new Point(213, 107));
			
			needFeedbackRadioButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					
					normalRadioButton.setSelected(false);
				}
			});
		}
		return needFeedbackRadioButton;
	}

	/**
	 * This method initializes contentTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getContentTextArea() {
		if (contentTextArea == null) {
			contentTextArea = new JTextArea();
			contentTextArea.setBounds(new Rectangle(18, 140, 417, 261));
		}
		return contentTextArea;
	}

	/**
	 * This method initializes addressButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getAddressButton() {
		if (addressButton == null) {
			addressButton = new JButton(){
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
			addressButton.setText("չ������");
			addressButton.setSize(new Dimension(97, 30));
			addressButton.setLocation(new Point(341, 18));
			
			addressButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					
					receiverTextField.setText("");
					
					rightPanel.removeAll();
					rightPanel.add(addressPanel, null);
					addressPanel.setVisible(true);
					
					receiverTag = ADDRESS;
					
					changeToLargeWidth();
					repaint();
				}
			});
		}
		return addressButton;
	}

	/**
	 * This method initializes fuzzyButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getFuzzyButton() {
		if (fuzzyButton == null) {
			fuzzyButton = new JButton(){
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
			fuzzyButton.setText("չ������");
			fuzzyButton.setSize(new Dimension(96, 30));
			fuzzyButton.setLocation(new Point(342, 60));
			
			fuzzyButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					
					receiverTextField.setText("");
					
					rightPanel.removeAll();
					rightPanel.add(fuzzyPanel, null);
					fuzzyPanel.setVisible(true);
					fuzzyPanel.reset();
					
					receiverTag = FUZZY;
					
					changeToLargeWidth();
					repaint();				
				}
			});
		}
		return fuzzyButton;
	}

	/**
	 * This method initializes backButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBackButton() {
		if (backButton == null) {
			backButton = new JButton(){
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
			backButton.setText("����");
			backButton.setSize(new Dimension(94, 30));
			backButton.setLocation(new Point(342, 103));
			
			backButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					
					rightPanel.removeAll();
					receiverTag = NORMAL;
					
					changeToSmallWidth();
					repaint();
				}
			});
		}
		return backButton;
	}
	
	/**
	 * This method initializes rightPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getRightPanel() {
		if (rightPanel == null) {
			rightPanel = new JPanel();
			rightPanel.setOpaque(false);
			rightPanel.setLayout(null);
			rightPanel.setBounds(new Rectangle(450, 14, 240, 420));
		}
		return rightPanel;
	}

	/**
	 * This method initializes sendButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSendButton() {
		if (sendButton == null) {
			sendButton = new JButton(){
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
			sendButton.setBounds(new Rectangle(283, 407, 73, 24));
			sendButton.setText("����");
			sendButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			sendButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					
					String title = subjectTextField.getText();
					String content = contentTextArea.getText();
					boolean isNeedFeedback = false;
					
					if(needFeedbackRadioButton.isSelected() == true)
					{
						isNeedFeedback = true;
					}
					
					Mail mail = new Mail();
					mail.setSenderUsername(Login.person.getUsername());
					mail.setSenderName(Login.person.getName());
					mail.setTitle(title);
					mail.setContent(content);
					mail.setIsNeedFeedback(isNeedFeedback);
					
					//send mail
					if(receiverTag == NORMAL)
					{
						String s = receiverTextField.getText();
						ArrayList<String> usernameList = new ArrayList<String>();
						
						String[] tempsStrings = s.split(",");
						for(int i=0; i<tempsStrings.length; i++)
						{
							usernameList.add(tempsStrings[i]);
						}
						
						receiverUsernameList = usernameList;
						
						//������ͳɹ�
						if(Login.person.sendMail(receiverUsernameList, mail) == true)
						{
							JOptionPane.showMessageDialog(null, "���ͳɹ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);	
							backToReceivedMailList();
						}
						else
						{
							JOptionPane.showMessageDialog(null, "������˼������ʧ�ܣ������Ҳ���ָ�����ռ���", "��ʾ", JOptionPane.INFORMATION_MESSAGE);	
						}
					}
					else if(receiverTag == ADDRESS)
					{
						if(receiverUsernameList == null || receiverUsernameList.size() == 0)
						{
							JOptionPane.showMessageDialog(null, "��ѡ���ռ���,����\"ȷ��\"�ύ", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						
						//������ͳɹ�
						if(Login.person.sendMail(receiverUsernameList, mail) == true)
						{
							JOptionPane.showMessageDialog(null, "���ͳɹ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
							backToReceivedMailList();
						}
						else 
						{
							JOptionPane.showMessageDialog(null, "������˼������ʧ�ܣ������Ҳ���ָ�����ռ���", "��ʾ", JOptionPane.INFORMATION_MESSAGE);	
						}						
					}
					else if(receiverTag == FUZZY)
					{
						ArrayList<Integer> hobbyNumList = fuzzyPanel.getSelectedHobbyNumList();
						ArrayList<Integer> adeptnessNumList = fuzzyPanel.getSelectedAdeptnessNumList();
						
						//������ͳɹ�
						if(Login.person.sendMail(hobbyNumList, adeptnessNumList, mail) == true)
						{			
							JOptionPane.showMessageDialog(null, "���ͳɹ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
							backToReceivedMailList();
						}
						else 
						{
							JOptionPane.showMessageDialog(null, "������˼������ʧ�ܣ������Ҳ���ָ�����ռ���", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
						}
					}

				}
			});
		}
		return sendButton;
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
			cancelButton.setText("ȡ��");
			cancelButton.setLocation(new Point(363, 407));
			cancelButton.setSize(new Dimension(73, 24));
			cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			cancelButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					
					backToReceivedMailList();
				}
			});
		}
		return cancelButton;
	}
	
	
	/**
	 * ����mail���󣨻ظ����˵�ʱ����ã�
	 * @param mail
	 */
	public void setMail(Mail mail)
	{
		this.mail = mail;
		receiverUsernameList.clear();
		receiverTag = NORMAL;
		
		//setText
		if(mail != null)
		{
			receiverTextField.setText(mail.getSenderUsername());
			subjectTextField.setText("Re: " + mail.getTitle());
			normalRadioButton.setSelected(true);
			needFeedbackRadioButton.setSelected(false);
			contentTextArea.setText("");
			
			normalRadioButton.setEnabled(false);
			needFeedbackRadioButton.setEnabled(false);
			
			addressButton.setVisible(false);
			addressButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			fuzzyButton.setVisible(false);
			fuzzyButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			backButton.setVisible(false);
			backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			rightPanel.removeAll();
		}
	}
	
	/**
	 * ���ÿ�ݣ���ݷ��ŵ�ʱ����ã�
	 * @param receiverUsername
	 */
	public void setShortCut(String receiverUsername)
	{
		receiverUsernameList.clear();
		receiverTag = NORMAL;
		
		//setText
		receiverTextField.setText(receiverUsername);
		normalRadioButton.setSelected(true);
		needFeedbackRadioButton.setSelected(false);
			
		normalRadioButton.setEnabled(false);
		needFeedbackRadioButton.setEnabled(false);
			
		addressButton.setVisible(false);
		fuzzyButton.setVisible(false);
		backButton.setVisible(false);
			
		rightPanel.removeAll();
	}
	
	/**
	 * �л�����ģʽ
	 */
	private void changeToLargeWidth()
	{
		this.setSize(700, 444);
		DataSet.mainFrame.mailBoxFrame.changeToLargeWidth();
	}
	
	/**
	 * �л���խģʽ
	 */
	private void changeToSmallWidth()
	{
		this.setSize(458, 444);
		DataSet.mainFrame.mailBoxFrame.changeToSmallWidth();
	}
	
	
	/**
	 * �ص��ռ���
	 */
	private void backToReceivedMailList()
	{		
		DataSet.mainFrame.mailBoxFrame.mailListPanel.setTag(DataSet.mainFrame.mailBoxFrame.RECEIVED_MAIL_LIST);
		DataSet.mainFrame.mailBoxFrame.centerPanel.removeAll();
		DataSet.mainFrame.mailBoxFrame.centerPanel.add(DataSet.mainFrame.mailBoxFrame.mailListPanel, null);
		DataSet.mainFrame.mailBoxFrame.panelTag = DataSet.mainFrame.mailBoxFrame.MAIL_LIST_PANEL;
		DataSet.mainFrame.mailBoxFrame.changeToSmallWidth();
		DataSet.mainFrame.mailBoxFrame.centerPanel.repaint();
	}
	
	
	/**
	 * ����
	 */
	public void reset()
	{
		receiverTextField.setText("");
		subjectTextField.setText("");
		normalRadioButton.setSelected(true);
		needFeedbackRadioButton.setSelected(false);
		
		contentTextArea.setText("");
		
		normalRadioButton.setEnabled(true);
		needFeedbackRadioButton.setEnabled(true);
		
		addressButton.setVisible(true);
		fuzzyButton.setVisible(true);
		backButton.setVisible(true);
		
		receiverUsernameList.clear();
	}
	
	
	/**
	 * �����ռ����б�����չ������ʱ���ã�
	 * @param list
	 */
	protected void setReceiverUsernameList(ArrayList<String> list)
	{
		receiverUsernameList = list;
		
		if(receiverUsernameList != null && receiverUsernameList.size()>0)
		{
			String s = "";
			
			for(int i=0; i<receiverUsernameList.size(); i++)
			{
				if(i != receiverUsernameList.size()-1)
					s += (receiverUsernameList.get(i) + ",");
				else 
					s += receiverUsernameList.get(i);
			}
			
			receiverTextField.setText(s);
		}
	}
	
	 
	/**
	 * ��������ռ��ˣ�����չ������ʱ���ã�
	 * @param username
	 */
	protected void addReceiverUsername(String username)
	{
		receiverUsernameList.add(username);
		
		String s = "";
		
		for(int i=0; i<receiverUsernameList.size(); i++)
		{
			if(i != receiverUsernameList.size()-1)
				s += (receiverUsernameList.get(i) + ",");
			else 
				s += receiverUsernameList.get(i);
		}
		
		receiverTextField.setText(s);
	}


}  //  @jve:decl-index=0:visual-constraint="-15,-32"
