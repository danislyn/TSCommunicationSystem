package gui.mail;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MailBoxFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel jContentPane = null;  //总panel
	
	private JPanel actionPanel = null;	
	private JLabel receivedListLabel = null;
	private JLabel writeLabel = null;
	private JLabel sentListLabel = null;
	
	public JPanel centerPanel = null;  //用于切换
	
	protected MailListPanel mailListPanel = null;
	protected ReceivedMailPanel receivedMailPanel = null;
	protected SentMailPanel sentMailPanel = null;
	public WriteMailPanel writeMailPanel = null; 
	
	protected final int RECEIVED_MAIL_LIST = 0;
	protected final int SENT_MAIL_LIST = 1;
	
	protected final int MAIL_LIST_PANEL = 1;
	protected final int RECEIVED_MAIL_PANEL = 2;
	protected final int SENT_MAIL_PANEL = 3;
	protected final int WRITE_MAIL_PANEL = 4;
	
	public int panelTag;
	
	/**
	 * This is the default constructor
	 */
	public MailBoxFrame() {
		super();
		this.setResizable(false);
		
		//默认tag=1
		panelTag = MAIL_LIST_PANEL;
		
		//初始化其他暂时用不到的panel
		mailListPanel = new MailListPanel();
		receivedMailPanel = new ReceivedMailPanel();
		sentMailPanel = new SentMailPanel();
		writeMailPanel = new WriteMailPanel();
		
		//初始化当前frame
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(600, 475);
		this.setContentPane(getJContentPane());
		this.setTitle("MailBox");
		this.setLocationRelativeTo(null);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel(){
				{
					setOpaque(false);
				}
				public void paintComponent(Graphics g) { 
			        g.setColor(Color.blue); 
			        ImageIcon img=null;
					img = new ImageIcon("images/b5.jpg");
					g.drawImage(img.getImage(),0,0,null); 
			    }
			};
			jContentPane.setLayout(null);
			jContentPane.add(getActionPanel(), null);
			jContentPane.add(getCenterPanel(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes actionPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getActionPanel() {
		if (actionPanel == null) {
			sentListLabel = new JLabel();
			sentListLabel.setBounds(new Rectangle(14, 184, 89, 51));
			sentListLabel.setHorizontalAlignment(SwingConstants.CENTER);
			sentListLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			sentListLabel.setText("发件箱");
			sentListLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			sentListLabel.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					//System.out.println("mouseClicked()"); // TODO Auto-generated Event stub mouseClicked()				
					
					centerPanel.removeAll();
					
					mailListPanel.setTag(SENT_MAIL_LIST);  //发件箱
					mailListPanel.setVisible(true);
					mailListPanel.setBounds(new Rectangle(0, 0, 470, 444));
					
					centerPanel.add(mailListPanel, null);		
					changeToSmallWidth();
					repaint();
					
					panelTag = MAIL_LIST_PANEL;
				}
			});
			
			writeLabel = new JLabel();
			writeLabel.setBounds(new Rectangle(13, 113, 90, 48));
			writeLabel.setHorizontalAlignment(SwingConstants.CENTER);
			writeLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			writeLabel.setText("写信");
			writeLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			writeLabel.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					//System.out.println("mouseClicked()"); // TODO Auto-generated Event stub mouseClicked()
				
					centerPanel.removeAll();
					
					writeMailPanel.setVisible(true);
					writeMailPanel.setBounds(new Rectangle(0, 0, 458, 444));					
					centerPanel.add(writeMailPanel, null);
					
					writeMailPanel.reset();  //clear
					writeMailPanel.setMail(null);  //init
					writeMailPanel.updateUI();
					changeToSmallWidth();
					repaint();
					
					panelTag = WRITE_MAIL_PANEL;
				}
			});
			
			receivedListLabel = new JLabel();
			receivedListLabel.setBounds(new Rectangle(11, 37, 93, 49));
			receivedListLabel.setHorizontalAlignment(SwingConstants.CENTER);
			receivedListLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			receivedListLabel.setText("收件箱");
			receivedListLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			receivedListLabel.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					//System.out.println("mouseClicked()"); // TODO Auto-generated Event stub mouseClicked()								
					
					centerPanel.removeAll();
					
					mailListPanel.setTag(RECEIVED_MAIL_LIST);  //收件箱
					mailListPanel.setVisible(true);
					mailListPanel.setBounds(new Rectangle(0, 0, 470, 444));
					
					centerPanel.add(mailListPanel, null);
					changeToSmallWidth();
					repaint();
					
					panelTag = MAIL_LIST_PANEL;
				}
			});
			
			actionPanel = new JPanel();
			actionPanel.setOpaque(false);
			actionPanel.setLayout(null);
			actionPanel.setBounds(new Rectangle(2, 1, 116, 447));
			actionPanel.add(receivedListLabel, null);
			actionPanel.add(writeLabel, null);
			actionPanel.add(sentListLabel, null);
		}
		return actionPanel;
	}

	
	/**
	 * This method initializes choosePanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getCenterPanel() {
		if (centerPanel == null) {
			centerPanel = new JPanel();
			centerPanel.setOpaque(false);
			centerPanel.setLayout(null);
			centerPanel.setBounds(new Rectangle(121, 2, 695, 444));
			
			//初始化为收件箱
			mailListPanel.setTag(RECEIVED_MAIL_LIST);
			mailListPanel.setVisible(true);
			mailListPanel.setBounds(new Rectangle(0, 0, 470, 444));  //局部坐标
			centerPanel.add(mailListPanel, null);
		}
		return centerPanel;
	}
	
	
	/**
	 * 切换到宽模式
	 */
	protected void changeToLargeWidth()
	{
		this.setSize(840, 475);
	}
	
	/**
	 * 切换到窄模式
	 */
	public void changeToSmallWidth()
	{
		this.setSize(600, 475);
	}
	
	/**
	 * 重置
	 */
	public void reset()
	{
		changeToSmallWidth();
		centerPanel.removeAll();	
		mailListPanel.setTag(RECEIVED_MAIL_LIST);  //收件箱
		centerPanel.add(mailListPanel, null);
		repaint();
	}
	

}  //  @jve:decl-index=0:visual-constraint="10,10"
