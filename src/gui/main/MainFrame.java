package gui.main;

import gui.address.AddressManageFrame;
import gui.announce.SendAnnouncementFrame;
import gui.announce.SentAnnouncementListFrame;
import gui.files.FilePanel;
import gui.information.ReviseInformationFrame;
import gui.information.RevisePasswordFrame;
import gui.left.LeftMainPanel;
import gui.log.LogPanel;
import gui.mail.MailBoxFrame;
import gui.post.RealPostPanel;
import gui.users.UserManagePanel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import server.reflection.Call;
import client.login.Login;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	
	private JButton mailBoxButton = null;
	
	private JButton userManageButton = null;
	private JButton logButton = null;
	private JButton fileButton = null;	
	private JButton postButton = null;
	private JButton myInfoButton = null;
	private CurvesPanel curves=null;
	private JLabel welcome=null;
	
	public JPanel rightPanel = null;  //�����л�
	
	//������Ҫ�õ�����Frame��Panel
	public MailBoxFrame mailBoxFrame = null;
	public RealPostPanel postPanel = null;
	public FilePanel filePanel = null;
	public LeftMainPanel left=null;
	public AddressManageFrame addressManageFrame = null;
	
	public ReviseInformationFrame reviseInformationFrame = null;
	public RevisePasswordFrame revisePasswordFrame = null;
	
	
	//��Ȩpanel
	public SendAnnouncementFrame sendAnnouncementFrame = null;
	public SentAnnouncementListFrame sentAnnouncementListFrame = null;
	
	public UserManagePanel userManagePanel = null;
	public LogPanel logPanel = null;
	

	/**
	 * ��ʼ��������
	 */
	private void startAnimation() {
        Timer timer = new Timer(50, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                curves.animate();
                curves.repaint();
            }
        });
        timer.start();
    }

	/**
	 * This is the default constructor
	 * @param adminTag trueΪ����Ա  falseΪ��ͨ�û�
	 */
	public MainFrame(boolean adminTag) {
		super("TSCS");
		this.setTitle("TSCS_��ӭ��");//��ܱ���
		this.setIconImage(new ImageIcon("images/icon.png").getImage());//���icon
		initialize();//��ʼ��������
		startAnimation();//��ʼ����
		
		//��ʼ����ʾpost
		if(postPanel==null)
		{
			postPanel = new RealPostPanel();
			postPanel.setBounds(0, 20, 710, 450);
		}
		postPanel.setVisible(true);
		rightPanel.add(postPanel, null);
		postPanel.updateUI();
		repaint();
		
		//����Ա�Ļ�
		if(adminTag == true)
		{
			userManageButton.setVisible(true);
			logButton.setVisible(true);
		}
		
		//�ȳ�ʼ��mailbox
		mailBoxFrame = new MailBoxFrame();
		
		//NOTE
		//������ʾ�߳�
		MailTimerTask mailTimerTask = new MailTimerTask();
		java.util.Timer timer = new java.util.Timer();
		timer.schedule(mailTimerTask, 60000, 60000);  //ÿ����ˢһ��
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		curves = new CurvesPanel();
		curves.setBounds(0, 0, 1186, 100);
		welcome=new JLabel();
		welcome.setIcon(new ImageIcon("images/welcome.png"));
		welcome.setBounds(744, 20, 400, 100);
		
		this.setSize(1186, 724);
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);		
		this.setLocationRelativeTo(null);
		
		//�������ڹر�
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				//System.out.println("windowClosed()"); // TODO Auto-generated Event stub windowClosed()
				
				Call call=new Call("server.interfaces.UserOperationInterface",
						"logout",new Class[]{String.class}, new Object[]{Login.person.getUsername()});
				Login.writeCall(call);
				Login.readCall();
				
			}
		});
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel(){
				/**
				 * �ػ������ı���
				 */
				private static final long serialVersionUID = 1L;

				public void paintComponent(Graphics g) { 
			        g.setColor(Color.blue); 
			        ImageIcon img=null;
					img = new ImageIcon("images/show.png");
					g.drawImage(img.getImage(),0,0,null); 
			    }

			};
			jContentPane.setLayout(null);
			jContentPane.add(getMailBoxButton(), null);
			jContentPane.add(getRightPanel(), null);
			jContentPane.add(getUserManageButton(), null);
			jContentPane.add(getLogButton(), null);
			jContentPane.add(getFileButton(), null);
			jContentPane.add(getPostButton(), null);
			jContentPane.add(getMyInfoButton(), null);
			jContentPane.add(getLeftMainPanel(), null);
			jContentPane.add(curves, null);
			jContentPane.add(welcome,null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes mailBoxButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getMailBoxButton() {
		if (mailBoxButton == null) {
			mailBoxButton = new JButton(){
				/**
				 * �ػ���ť
				 */
				private static final long serialVersionUID = 1L;

				protected void paintBorder(Graphics g){}
			};
			mailBoxButton.setBounds(new Rectangle(143, 621, 125, 42));
			mailBoxButton.setContentAreaFilled(false);
			mailBoxButton.setOpaque(false);
			mailBoxButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			
			mailBoxButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					 // TODO Auto-generated Event stub actionPerformed()
					
					if(mailBoxFrame == null)
					{
						mailBoxFrame = new MailBoxFrame();
					}
					mailBoxFrame.setVisible(true);
					
					mailBoxFrame.reset();
				}
			});
		}
		return mailBoxButton;
	}

	/**
	 * This method initializes rightPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getRightPanel() {
		if (rightPanel == null) {
			rightPanel = new JPanel();
			rightPanel.setLayout(null);
			rightPanel.setBounds(new Rectangle(448, 157, 893, 565));
			rightPanel.setOpaque(false);
		}
		return rightPanel;
	}

	/**
	 * This method initializes userManageButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getUserManageButton() {
		if (userManageButton == null) {
			userManageButton = new JButton(){
				/**
				 * �ػ���ť
				 */
				private static final long serialVersionUID = 1L;
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
			userManageButton.setBounds(new Rectangle(677, 119, 97, 30));
			userManageButton.setText("�û�����");
			userManageButton.setVisible(false);
			userManageButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			userManageButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					 // TODO Auto-generated Event stub actionPerformed()
					
					if(userManagePanel == null)
					{
						userManagePanel = new UserManagePanel();
					}
					userManagePanel.setVisible(true);
					
					
					
					rightPanel.removeAll();
					rightPanel.add(userManagePanel);
					userManagePanel.reset();
					userManagePanel.updateUI();
					repaint();
				}
			});
		}
		return userManageButton;
	}

	/**
	 * This method initializes logButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getLogButton() {
		if (logButton == null) {
			logButton = new JButton(){
				/**
				 * �ػ���ť
				 */
				private static final long serialVersionUID = 1L;
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
			logButton.setLocation(new Point(790, 119));
			logButton.setText("������־");
			logButton.setSize(new Dimension(97, 30));
			logButton.setVisible(false);
			logButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			logButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					 // TODO Auto-generated Event stub actionPerformed()
					
					if(logPanel == null)
					{
						logPanel = new LogPanel();
					}
					logPanel.setVisible(true);
					
					rightPanel.removeAll();
					rightPanel.add(logPanel, null);
					logPanel.displayList();
					logPanel.updateUI();
					repaint();
				}
			});
		}
		return logButton;
	}

	/**
	 * This method initializes fileButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getFileButton() {
		if (fileButton == null) {
			fileButton = new JButton(){
				/**
				 * �ػ���ť
				 */
				private static final long serialVersionUID = 1L;
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
			fileButton.setText("�ļ�����");
			fileButton.setSize(new Dimension(97, 30));
			fileButton.setLocation(new Point(564, 119));
			fileButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			fileButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					 // TODO Auto-generated Event stub actionPerformed()
					
					if(filePanel == null)
					{
						filePanel = new FilePanel();
					}
					filePanel.setVisible(true);
					
					
					
					rightPanel.removeAll();
					rightPanel.add(filePanel, null);
					filePanel.displayList();
					filePanel.updateUI();
					repaint();
				}
			});
		}
		return fileButton;
	}

	/**
	 * This method initializes postButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getPostButton() {
		if (postButton == null) {
			postButton = new JButton(){
				/**
				 * �ػ���ť
				 */
				private static final long serialVersionUID = 1L;
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
			postButton.setLocation(new Point(448, 119));
			postButton.setText("���Ӱ��");
			postButton.setSize(new Dimension(97, 30));
			postButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			postButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					 // TODO Auto-generated Event stub actionPerformed()
					
					//update
					Login.person.updateAllBlock();
					
					rightPanel.removeAll();
					postPanel = new RealPostPanel();
					postPanel.setBounds(0, 20, 710, 450);
					postPanel.setVisible(true);
					rightPanel.add(postPanel, null);
					//rest????????????????????????
					postPanel.updateUI();
					repaint();
				}
			});
		}
		return postButton;
	}

	/**
	 * This method initializes myInfoButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getMyInfoButton() {
		if (myInfoButton == null) {
			myInfoButton = new JButton(){
				/**
				 * �ػ���ť
				 */
				private static final long serialVersionUID = 1L;
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
			myInfoButton.setBounds(new Rectangle(200, 32, 82, 32));
			myInfoButton.setVisible(false);
			myInfoButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			myInfoButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					 // TODO Auto-generated Event stub actionPerformed()
					
					if(reviseInformationFrame == null)
					{
						reviseInformationFrame = new ReviseInformationFrame();
					}
					reviseInformationFrame.setVisible(true);
					
					reviseInformationFrame.setInformation(Login.person.getMyInformation());
				}
			});
		}
		return myInfoButton;
	}
	
	/**
	 * This method initializes left	
	 * 	
	 * @return javax.swing.left	
	 */
	
	private LeftMainPanel getLeftMainPanel(){
		if(left==null){
			left=new LeftMainPanel();
			left.setVisible(true);
	    	left.setBounds(25, 0, 320, 800);
	    	left.setOpaque(false);
			
		}
		return left;
		
	}
	

}
