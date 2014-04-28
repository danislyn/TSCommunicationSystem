package gui.announce;

import javax.swing.JPanel;
import javax.swing.JFrame;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JLabel;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;

import client.login.Login;

import beans.announcement.Announcement;

import java.awt.Point;
import java.util.HashMap;
import java.awt.Color;

public class SendAnnouncementFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel titleLabel = null;
	private JTextField titleTextField = null;
	private JLabel categoryLabel = null;
	private JComboBox categoryComboBox = null;
	private JLabel gradeLabel = null;
	private JComboBox gradeComboBox = null;
	private JTextArea contentTextArea = null;
	private JScrollPane jScrollPane = null;
	private JButton okButton = null;
	private JButton cancelButton = null;
	
	private HashMap<Integer, String> categoryToStringMap;
	private HashMap<Integer, String> gradeToStringMap;
	private JLabel contentInfoLabel = null;

	/**
	 * This is the default constructor
	 */
	public SendAnnouncementFrame() {
		super();
		
		categoryToStringMap = new HashMap<Integer, String>();
		categoryToStringMap.put(0, "学习");
		categoryToStringMap.put(1, "娱乐");
		categoryToStringMap.put(2, "生活");
		categoryToStringMap.put(3, "情感");
		categoryToStringMap.put(4, "其他");
		
		gradeToStringMap = new HashMap<Integer, String>();
		gradeToStringMap.put(0, "老师");
		gradeToStringMap.put(1, "大一");
		gradeToStringMap.put(2, "大二");
		gradeToStringMap.put(3, "大三");
		gradeToStringMap.put(4, "大四");
		
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(428, 319);
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
		this.setLocationRelativeTo(null);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			contentInfoLabel = new JLabel();
			contentInfoLabel.setBounds(new Rectangle(22, 234, 151, 19));
			contentInfoLabel.setForeground(Color.red);
			contentInfoLabel.setText("限150个汉字或300个字符");
			gradeLabel = new JLabel();
			gradeLabel.setHorizontalAlignment(SwingConstants.CENTER);
			gradeLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			gradeLabel.setSize(new Dimension(60, 30));
			gradeLabel.setLocation(new Point(213, 56));
			gradeLabel.setText("可见群体");
			categoryLabel = new JLabel();
			categoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
			categoryLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			categoryLabel.setLocation(new Point(18, 56));
			categoryLabel.setSize(new Dimension(60, 30));
			categoryLabel.setText("类别");
			titleLabel = new JLabel();
			titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
			titleLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			titleLabel.setLocation(new Point(17, 14));
			titleLabel.setSize(new Dimension(60, 30));
			titleLabel.setText("标题");
			jContentPane = new JPanel(){
				{
					setOpaque(false);
				}
				
				public void paintComponent(Graphics g) { 
			        g.setColor(Color.blue); 
			        ImageIcon img=null;
					img = new ImageIcon("images/announce.jpg");
					g.drawImage(img.getImage(),0,0,null); 
			    }
			};
			jContentPane.setLayout(null);
			jContentPane.add(titleLabel, null);
			jContentPane.add(getTitleTextField(), null);
			jContentPane.add(categoryLabel, null);
			jContentPane.add(getCategoryComboBox(), null);
			jContentPane.add(gradeLabel, null);
			jContentPane.add(getGradeComboBox(), null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(getOkButton(), null);
			jContentPane.add(getCancelButton(), null);
			jContentPane.add(contentInfoLabel, null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes titleTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTitleTextField() {
		if (titleTextField == null) {
			titleTextField = new JTextField();
			titleTextField.setSize(new Dimension(295, 30));
			titleTextField.setLocation(new Point(94, 14));
		}
		return titleTextField;
	}

	/**
	 * This method initializes categoryComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCategoryComboBox() {
		if (categoryComboBox == null) {
			categoryComboBox = new JComboBox();
			categoryComboBox.setSize(new Dimension(100, 30));
			categoryComboBox.setLocation(new Point(94, 56));
			
			for(int i=0; i<categoryToStringMap.keySet().size(); i++)
			{
				categoryComboBox.addItem(categoryToStringMap.get(i));
			}
			
			categoryComboBox.setSelectedIndex(-1);
		}
		return categoryComboBox;
	}

	/**
	 * This method initializes gradeComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getGradeComboBox() {
		if (gradeComboBox == null) {
			gradeComboBox = new JComboBox();
			gradeComboBox.setLocation(new Point(287, 56));
			gradeComboBox.setSize(new Dimension(100, 30));
			
			for(int i=0; i<gradeToStringMap.keySet().size(); i++)
			{
				gradeComboBox.addItem(gradeToStringMap.get(i));
			}
			
			gradeComboBox.setSelectedIndex(-1);
		}
		return gradeComboBox;
	}
	
	private JScrollPane getJScrollPane()
	{
		if(jScrollPane == null)
		{
			jScrollPane = new JScrollPane(getContentTextArea());
			jScrollPane.setBounds(new Rectangle(21, 93, 367, 140));
		}
		return jScrollPane;
	}

	/**
	 * This method initializes contentTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getContentTextArea() {
		if (contentTextArea == null) {
			contentTextArea = new JTextArea();
			contentTextArea.setBounds(new Rectangle(21, 93, 367, 140));
		}
		return contentTextArea;
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
			okButton.setPreferredSize(new Dimension(60, 30));
			okButton.setLocation(new Point(239, 242));
			okButton.setSize(new Dimension(70, 30));
			okButton.setText("确定");
			okButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			okButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					
					String title = titleTextField.getText();
					String content = contentTextArea.getText();
					int categoryIndex = categoryComboBox.getSelectedIndex();
					int gradeIndex = gradeComboBox.getSelectedIndex();
					
					if(title.length() == 0)
					{
						JOptionPane.showMessageDialog(null, "标题不可为空", "提示",JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					
					if(content.length() == 0)
					{
						JOptionPane.showMessageDialog(null, "内容不可为空", "提示",JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					
					if(categoryIndex == -1)
					{
						JOptionPane.showMessageDialog(null, "请选择类别", "提示",JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					
					if(gradeIndex == -1)
					{
						JOptionPane.showMessageDialog(null, "请选择可见群体", "提示",JOptionPane.INFORMATION_MESSAGE);
						return;
					}
									
					//以上检测没问题
					Announcement announcement = new Announcement();
					announcement.setSenderUsername(Login.person.getUsername());
					announcement.setSenderName(Login.person.getName());
					announcement.setTitle(title);
					announcement.setCategory(categoryToStringMap.get(categoryIndex));
					announcement.setGrade(gradeIndex);
					announcement.setContent(content);
					
					if(Login.person.sendAnnouncement(announcement) == true)
					{
						JOptionPane.showMessageDialog(null, "发布成功", "提示",JOptionPane.INFORMATION_MESSAGE);
						
						//update
						/////////////////////
						
						dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "发布失败，请检查标题或内容是否过长", "提示",JOptionPane.INFORMATION_MESSAGE);
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
			cancelButton.setText("取消");
			cancelButton.setLocation(new Point(319, 242));
			cancelButton.setPreferredSize(new Dimension(70, 30));
			cancelButton.setSize(new Dimension(70, 30));
			cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			cancelButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					
					dispose();
				}
			});
		}
		return cancelButton;
	}
	
	
	/**
	 * 重置
	 */
	public void reset()
	{
		titleTextField.setText("");
		contentTextArea.setText("");
		categoryComboBox.setSelectedIndex(-1);
		gradeComboBox.setSelectedIndex(-1);
	}
	

}  //  @jve:decl-index=0:visual-constraint="10,10"
