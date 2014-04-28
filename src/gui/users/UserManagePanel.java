package gui.users;

import gui.data.DataSet;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JList;
import java.awt.Rectangle;
import javax.swing.JButton;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

import client.login.Login;

import beans.admin.UserBaseInfo;

public class UserManagePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JScrollPane jScrollPane = null;
	private JList jList = null;
	private JPanel rightPanel = null;  //�����л�
	private JButton addButton = null;
	private JButton deleteButton = null;
	private JTextField searchTextField = null;
	private JLabel searchLabel = null;
	private JButton searchButton = null;
	private JComboBox sortComboBox = null;
	private JButton refreshButton = null;
	
	private UserBaseInfoPanel userBaseInfoPanel;  //��panel
	
	private HashMap<Integer, String> gradeToStringMap;
	private HashMap<Integer, String> searchToStringMap;
	
	private int listTag;
	
	private HashMap<Integer, ArrayList<UserBaseInfo>> tagToListMap;
	
	private final int ALL = 0;
	private final int TEACHER = 1;
	private final int GRADE1 = 2;
	private final int GRADE2 = 3;
	private final int GRADE3 = 4;
	private final int GRADE4 = 5;
	
	
	/**
	 * This is the default constructor
	 */
	public UserManagePanel() {
		super();
		this.setOpaque(false);
		
		listTag = ALL;
		
		gradeToStringMap = new HashMap<Integer, String>();		
		gradeToStringMap.put(0, "��ʦ");
		gradeToStringMap.put(1, "��һ");
		gradeToStringMap.put(2, "���");
		gradeToStringMap.put(3, "����");
		gradeToStringMap.put(4, "����");
		
		searchToStringMap = new HashMap<Integer, String>();
		searchToStringMap.put(0, "��ʾ�����û��б�");
		searchToStringMap.put(1, "��ʾ��ʦ�б�");
		searchToStringMap.put(2, "��ʾ��һѧ���б�");
		searchToStringMap.put(3, "��ʾ���ѧ���б�");
		searchToStringMap.put(4, "��ʾ����ѧ���б�");
		searchToStringMap.put(5, "��ʾ����ѧ���б�");
		
		tagToListMap = new HashMap<Integer, ArrayList<UserBaseInfo>>();
		tagToListMap.put(0, DataSet.allUserList);
		tagToListMap.put(1, DataSet.userList0);
		tagToListMap.put(2, DataSet.userList1);
		tagToListMap.put(3, DataSet.userList2);
		tagToListMap.put(4, DataSet.userList3);
		tagToListMap.put(5, DataSet.userList4);
		
		
		//��ʼ����Ҫ�õ�������panel
		userBaseInfoPanel = new UserBaseInfoPanel();
		
		initialize();
		
		displayList();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		searchLabel = new JLabel();
		searchLabel.setBounds(new Rectangle(398, 13, 71, 28));
		searchLabel.setHorizontalAlignment(SwingConstants.CENTER);
		searchLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		searchLabel.setText("�����û���");
		this.setSize(682, 481);
		this.setLayout(null);
		this.add(getJScrollPane(), null);
		this.add(getRightPanel(), null);
		this.add(getAddButton(), null);
		this.add(getDeleteButton(), null);
		this.add(getSearchTextField(), null);
		this.add(searchLabel, null);
		this.add(getSearchButton(), null);
		this.add(getSortComboBox(), null);
		this.add(getRefreshButton(), null);
	}
	
	private JScrollPane getJScrollPane()
	{
		if(jScrollPane == null)
		{
			jScrollPane = new JScrollPane(getJList());
			jScrollPane.setBounds(new Rectangle(8, 50, 380, 423));
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJList() {
		if (jList == null) {
			jList = new JList();
			jList.setBounds(new Rectangle(8, 50, 380, 423));
			
			jList.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					//System.out.println("mouseClicked()"); // TODO Auto-generated Event stub mouseClicked()

				}
			});
			
			jList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
				public void valueChanged(javax.swing.event.ListSelectionEvent e) {
					//System.out.println("valueChanged()"); // TODO Auto-generated Event stub valueChanged()
					
					int index = jList.getSelectedIndex();
					
					if(index != -1)
					{
						UserBaseInfo userBaseInfo = tagToListMap.get(listTag).get(index);
						
						userBaseInfoPanel.setTag(0);  //
						userBaseInfoPanel.reset();  //clear
						userBaseInfoPanel.setUser(userBaseInfo);
						userBaseInfoPanel.updateUI();
					}
				}
			});
		}
		return jList;
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
			rightPanel.setBounds(new Rectangle(394, 50, 281, 425));
			
			rightPanel.add(userBaseInfoPanel, null);
		}
		return rightPanel;
	}
	
	/**
	 * This method initializes refreshButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getRefreshButton() {
		if (refreshButton == null) {
			refreshButton = new JButton(){
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
			refreshButton.setText("ˢ��");
			refreshButton.setSize(new Dimension(62, 33));
			refreshButton.setLocation(new Point(164, 11));
			refreshButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			refreshButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					
					//update
					Login.person.updateUserManager();
					DataSet.updateAllUserList();
					
					//������ʾ�����û��б�
					listTag = ALL;
					sortComboBox.setSelectedIndex(ALL);
					
					displayList();
				}
			});
		}
		return refreshButton;
	}

	/**
	 * This method initializes addButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getAddButton() {
		if (addButton == null) {
			addButton = new JButton(){
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
			addButton.setText("����û�");
			addButton.setLocation(new Point(232, 11));
			addButton.setSize(new Dimension(88, 33));
			addButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			addButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					
					userBaseInfoPanel.setTag(1);
					userBaseInfoPanel.reset();
					userBaseInfoPanel.updateUI();
				}
			});
		}
		return addButton;
	}

	/**
	 * This method initializes deleteButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getDeleteButton() {
		if (deleteButton == null) {
			deleteButton = new JButton(){
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
			deleteButton.setText("ɾ��");
			deleteButton.setSize(new Dimension(62, 33));
			deleteButton.setLocation(new Point(326, 11));
			deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			deleteButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					
					int index = jList.getSelectedIndex();
					
					if(index != -1)
					{
						UserBaseInfo userBaseInfo = tagToListMap.get(listTag).get(index);
						
						//����ɾ������Ա
						if(userBaseInfo.getAuthority() == 5)
						{
							JOptionPane.showMessageDialog(null, "��Ȩɾ������Ա", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						
						int choice = JOptionPane.showConfirmDialog(null, "ȷ��Ҫɾ��" + userBaseInfo.getUsername() + "���û���", "��ʾ", JOptionPane.YES_NO_OPTION);
						
						//ȷ��
						if(choice == 0)  //��һ����ť
						{
							if(Login.person.deleteUser(userBaseInfo) == true)
							{						
								JOptionPane.showMessageDialog(null, "ɾ���ɹ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);	
								
								//���
								userBaseInfoPanel.reset();
								
								//update
								DataSet.updateAllUserList();
								displayList();
							}
							else 
							{
								JOptionPane.showMessageDialog(null, "ɾ��ʧ��", "��ʾ", JOptionPane.INFORMATION_MESSAGE);	
							}
						}
					}
				}
			});
		}
		return deleteButton;
	}

	/**
	 * This method initializes searchTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getSearchTextField() {
		if (searchTextField == null) {
			searchTextField = new JTextField();
			searchTextField.setBounds(new Rectangle(472, 13, 139, 29));
		}
		return searchTextField;
	}

	/**
	 * This method initializes searchButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSearchButton() {
		if (searchButton == null) {
			searchButton = new JButton(){
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
			searchButton.setBounds(new Rectangle(614, 12, 60, 29));
			searchButton.setText("ȷ��");
			searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			searchButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					
					String username = searchTextField.getText();
					
					if(username == null || username.length() == 0)
					{
						JOptionPane.showMessageDialog(null, "�������û���", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					
					UserBaseInfo oneUserBaseInfo = Login.person.getOneUser(username);
					
					//���û�����
					if(oneUserBaseInfo != null)
					{
						//�滻��ʾ
						userBaseInfoPanel.setTag(0);  //
						userBaseInfoPanel.reset();  //clear
						userBaseInfoPanel.setUser(oneUserBaseInfo);
					}
					//�û�������
					else 
					{
						JOptionPane.showMessageDialog(null, "���û���������", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
						userBaseInfoPanel.setTag(0);
						userBaseInfoPanel.reset();
					}
					
					//���
					searchTextField.setText("");
				}
			});
		}
		return searchButton;
	}

	/**
	 * This method initializes sortComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getSortComboBox() {
		if (sortComboBox == null) {
			sortComboBox = new JComboBox();
			sortComboBox.setBounds(new Rectangle(8, 13, 146, 29));
			
			for(int i=0; i<searchToStringMap.keySet().size(); i++)
			{
				sortComboBox.addItem(searchToStringMap.get(i));
			}
			
			//��ʼ����ʾ�����û��б�
			sortComboBox.setSelectedIndex(0);
			
			sortComboBox.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					//System.out.println("itemStateChanged()"); // TODO Auto-generated Event stub itemStateChanged()
					
					int index = sortComboBox.getSelectedIndex();
					
					if(index != -1)
					{
						//update
						listTag = index;
						displayList();
					}
				}
			});
		}
		return sortComboBox;
	}
	
	
	/**
	 * ��������list
	 */
	public void displayList()
	{
		DefaultListModel listModel = new DefaultListModel();
		
		DataSet.updateReceivedMailList();
			
		for(int i=0; i<tagToListMap.get(listTag).size(); i++)
		{	
			UserBaseInfo userBaseInfo = tagToListMap.get(listTag).get(i);
			
			String tempsString = userBaseInfo.getUsername() + "    --------    " + userBaseInfo.getName() +
								"    --------    " + gradeToStringMap.get(userBaseInfo.getGrade());
			
			listModel.addElement(tempsString);
		}
		
		jList.setModel(listModel);		
	}
	
	/**
	 * ����
	 */
	public void reset()
	{
		userBaseInfoPanel.reset();
		userBaseInfoPanel.setTag(0);
		displayList();
	}
	
	/**
	 * �ػ�
	 */
	public void paintComponent(Graphics g){
    	g.setColor(Color.blue); 
        ImageIcon img=null;
		img = new ImageIcon("images/file.png");
		g.drawImage(img.getImage(),0,0,null); 
    	
    }


}  //  @jve:decl-index=0:visual-constraint="10,10"
