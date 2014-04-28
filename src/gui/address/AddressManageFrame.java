package gui.address;

import gui.data.DataSet;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import beans.mail.Address;
import beans.mail.AddressGroup;
import javax.swing.JTree;

import client.login.Login;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

public class AddressManageFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel jContentPane = null;
	private JScrollPane privateScrollPane = null;
	private JScrollPane globalScrollPane = null;
	private JButton deleteGroupButton = null;
	private JButton addGroupButton = null;
	private JButton moveButton = null;
	private JButton removeButton = null;
	
	private JTree privateTree = null;
	private JTree globalTree = null;
	
	private DefaultMutableTreeNode privateRootNode;
	private DefaultMutableTreeNode globalRootNode;

	
	/**
	 * This is the default constructor
	 */
	public AddressManageFrame() {
		super();			
		initialize();
		
		//��ȡ�ڵ�
		updatePrivateTreeNode();
		updateGlobalTreeNode();
	
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(633, 517);
		this.setContentPane(getJContentPane());
		this.setTitle("AddressGroupFrame");
		
		this.setLocationRelativeTo(null);
		
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
				 * 
				 */
				private static final long serialVersionUID = 1L;
				{
					setOpaque(false);
				}
				public void paintComponent(Graphics g) { 
			        g.setColor(Color.blue); 
			        ImageIcon img=null;
					img = new ImageIcon("images/b7.jpg");
					g.drawImage(img.getImage(),0,0,null); 
			    }
				
			};
			jContentPane.setLayout(null);
			jContentPane.add(getPrivateScrollPane(), null);
			jContentPane.add(getGlobalScrollPane(), null);
			jContentPane.add(getDeleteGroupButton(), null);
			jContentPane.add(getAddGroupButton(), null);
			jContentPane.add(getMoveButton(), null);
			jContentPane.add(getRemoveButton(), null);
		}
		return jContentPane;
	}
	
	/**
	 * This method initializes privateScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getPrivateScrollPane()
	{
		if(privateScrollPane == null)
		{
			privateScrollPane = new JScrollPane(getPrivateTree());
			privateScrollPane.setSize(new Dimension(243, 390));
			privateScrollPane.setLocation(new Point(15, 48));
		}
		return privateScrollPane;
	}
	
	/**
	 * This method initializes privateTree	
	 * 	
	 * @return javax.swing.JTree	
	 */
	private JTree getPrivateTree() {
		if (privateTree == null) {
			privateTree = new JTree(privateRootNode);
			privateTree.setBounds(new Rectangle(13, 48, 243, 390));			
			
			privateTree.getSelectionModel().setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);  //������ɢģʽ
			privateTree.setCellRenderer(new CategoryNodeRenderer());  //������Ⱦ��
			
			privateTree.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					//System.out.println("mouseClicked()"); // TODO Auto-generated Event stub mouseClicked()
					
					
					
				}
			});
			
		}
		return privateTree;
	}
	
	/**
	 * This method initializes globalScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getGlobalScrollPane()
	{
		if(globalScrollPane == null)
		{
			globalScrollPane = new JScrollPane(getGlobalTree());
			globalScrollPane.setSize(new Dimension(243, 390));
			globalScrollPane.setLocation(new Point(356, 48));
		}
		return globalScrollPane;
	}
	
	/**
	 * This method initializes globalTree	
	 * 	
	 * @return javax.swing.JTree	
	 */
	private JTree getGlobalTree() {
		if (globalTree == null) {
			globalTree = new JTree(globalRootNode);
			globalTree.setBounds(new Rectangle(356, 46, 243, 390));			
			
			globalTree.getSelectionModel().setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);  //������ɢģʽ
			globalTree.setCellRenderer(new CategoryNodeRenderer());  //������Ⱦ��
					
			globalTree.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					//System.out.println("mouseClicked()"); // TODO Auto-generated Event stub mouseClicked()
					
					//˫��
					if(e.getClickCount() == 2)
					{
						TreePath path = globalTree.getPathForLocation(e.getX(), e.getY());
						DefaultMutableTreeNode rightNode = (DefaultMutableTreeNode)globalTree.getLastSelectedPathComponent();
						
						//��Ҷ�ڵ�
						if(rightNode.isLeaf() == true)
						{						
							//��ߵ�ѡ��
							int leftCount = privateTree.getSelectionCount();
							
							//ȷ��ֻѡ��һ��
							if(leftCount == 1)
							{						
								TreeSelectionModel selectionModel = privateTree.getSelectionModel();
								TreePath treePath = selectionModel.getSelectionPath();
								
								DefaultMutableTreeNode leftNode = (DefaultMutableTreeNode) treePath.getLastPathComponent();
								
								//�������ڵ�
								if(leftNode.getParent() == privateRootNode)
								{
									String leftGroupName = (String) leftNode.getUserObject();
									//System.out.println(leftGroupName);
									
									//�ұ�
									String s = (String) rightNode.getUserObject();
									String[] tempsStrings = s.split("-");
									String username = tempsStrings[1];
									
									DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) rightNode.getParent();
									String groupName = (String) parentNode.getUserObject();
									
									Address address = DataSet.getGlobalAddress(groupName, username);
									ArrayList<Address> addressList = new ArrayList<Address>();
									addressList.add(address);
									
									//���
									Login.person.addAddressInGroup(leftGroupName, addressList);
									DataSet.mainFrame.left.addContacts(leftGroupName,addressList);
									
									//update
									updatePrivateTreeNode();
									
									
									//չ���ڵ�
									int row = 0;								
									for(Enumeration<?> enumeration=privateRootNode.breadthFirstEnumeration(); enumeration.hasMoreElements(); )
									{
										DefaultMutableTreeNode childNode = (DefaultMutableTreeNode) enumeration.nextElement();
										
										if(((String)childNode.getUserObject()).equals((String)leftNode.getUserObject()))
											break;
										
										row++;
									}							
									
									TreePath updatePath = privateTree.getPathForRow(row);
									privateTree.expandPath(updatePath);
									privateTree.setSelectionPath(updatePath);
									repaint();
								}
								else
								{
									JOptionPane.showMessageDialog(null, "����ѡ����ߵ���ڵ�", "��ʾ",JOptionPane.INFORMATION_MESSAGE);
								}
									
							}						
						}
					}
				}
				
			});
			
		}
		return globalTree;
	}

	/**
	 * This method initializes deleteGroupButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getDeleteGroupButton() {
		if (deleteGroupButton == null) {
			deleteGroupButton = new JButton(){
				/**
				 * �ػ水ť
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
			deleteGroupButton.setText("ɾ����");
			deleteGroupButton.setSize(new Dimension(75, 30));
			deleteGroupButton.setLocation(new Point(15, 11));
			deleteGroupButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			deleteGroupButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					 // TODO Auto-generated Event stub actionPerformed()
					
					int count = privateTree.getSelectionCount();
										
					//ȷ��ֻѡ��һ��
					if(count == 1)
					{						
						TreeSelectionModel selectionModel = privateTree.getSelectionModel();
						TreePath treePath = selectionModel.getSelectionPath();
						
						DefaultMutableTreeNode node = (DefaultMutableTreeNode) treePath.getLastPathComponent();
						
						//�������ڵ�
						if(node.getParent() == privateRootNode)
						{
							String groupName = (String) node.getUserObject();							
							
							int choice = JOptionPane.showConfirmDialog(null, "ȷ��Ҫɾ��" + groupName + "����?", "��ʾ", JOptionPane.YES_NO_OPTION);
							
							if(choice == 0)  //��һ����ť
							{
								if(Login.person.deleteAddressGroup(groupName) == true)
								{
									DataSet.mainFrame.left.removeGroup(groupName);
									//JOptionPane.showMessageDialog(null, "ɾ���ɹ�", "��ʾ",JOptionPane.INFORMATION_MESSAGE);	
									updatePrivateTreeNode();
								}
								
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "��ѡ����ڵ�", "��ʾ",JOptionPane.INFORMATION_MESSAGE);
						}
																	
					}
										
				}
			});
		}
		return deleteGroupButton;
	}

	/**
	 * This method initializes addGroupButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getAddGroupButton() {
		if (addGroupButton == null) {
			addGroupButton = new JButton(){
				/**
				 * �ػ水ť
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
			addGroupButton.setText("�½���");
			addGroupButton.setLocation(new Point(182, 11));
			addGroupButton.setSize(new Dimension(75, 30));
			addGroupButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			addGroupButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					
					String groupName = JOptionPane.showInputDialog("����������");
					
					//������Ч
					if(groupName != null && groupName.length() > 0)
					{
						if(Login.person.addAddressInGroup(groupName, new ArrayList<Address>()) == true)
						{
							DataSet.mainFrame.left.addGroup(groupName);
							//JOptionPane.showMessageDialog(null, "��ӳɹ�", "��ʾ",JOptionPane.INFORMATION_MESSAGE);							
							updatePrivateTreeNode();
						}
					}
					
				}
			});
		}
		return addGroupButton;
	}
	
	/**
	 * This method initializes moveButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getMoveButton() {
		if (moveButton == null) {
			moveButton = new JButton(){
				/**
				 * �ػ水ť
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
			moveButton.setBounds(new Rectangle(275, 203, 64, 26));
			moveButton.setFont(new Font("Dialog", Font.BOLD, 14));
			moveButton.setText("<==");
			moveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			moveButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					
					int rightCount = globalTree.getSelectionCount();
					
					//ȷ����ѡ��
					if(rightCount > 0)
					{
						//��ߵ�ѡ��
						int leftCount = privateTree.getSelectionCount();
						
						//ȷ��ֻѡ��һ��
						if(leftCount == 1)
						{						
							TreeSelectionModel leftSelectionModel = privateTree.getSelectionModel();
							TreePath leftTreePath = leftSelectionModel.getSelectionPath();
							
							DefaultMutableTreeNode leftNode = (DefaultMutableTreeNode) leftTreePath.getLastPathComponent();
							
							//�������ڵ�
							if(leftNode.getParent() == privateRootNode)
							{
								String leftGroupName = (String) leftNode.getUserObject();
								//System.out.println(leftGroupName);
								
								ArrayList<Address> addressList = new ArrayList<Address>();
								
								TreeSelectionModel rightSelectionModel = globalTree.getSelectionModel();
								TreePath[] treePaths = rightSelectionModel.getSelectionPaths();
								
								for(int i=0; i<treePaths.length; i++)
								{
									TreePath rightTreePath = treePaths[i];							
									DefaultMutableTreeNode rightNode = (DefaultMutableTreeNode) rightTreePath.getLastPathComponent();
									
									if(rightNode.isLeaf() == true)
									{
										String s = (String) rightNode.getUserObject();							
										String[] tempsStrings = s.split("-");
										
										String username = tempsStrings[1];
										
										DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) rightNode.getParent();
										String groupName = (String) parentNode.getUserObject();
										
										Address address = DataSet.getGlobalAddress(groupName, username);
										
										addressList.add(address);																				
									}
								}																								
								
								//���
								Login.person.addAddressInGroup(leftGroupName, addressList);
								
								DataSet.mainFrame.left.addContacts(leftGroupName,addressList);
								//update
								updatePrivateTreeNode();
																
								//չ���ڵ�
								int row = 0;								
								for(Enumeration<?> enumeration=privateRootNode.breadthFirstEnumeration(); enumeration.hasMoreElements(); )
								{
									DefaultMutableTreeNode childNode = (DefaultMutableTreeNode) enumeration.nextElement();
									
									if(((String)childNode.getUserObject()).equals((String)leftNode.getUserObject()))
										break;
									
									row++;
								}							
								
								TreePath updatePath = privateTree.getPathForRow(row);
								privateTree.expandPath(updatePath);
								privateTree.setSelectionPath(updatePath);
								repaint();
								
							}
							else
							{
								JOptionPane.showMessageDialog(null, "����ѡ����ߵ���ڵ�", "��ʾ",JOptionPane.INFORMATION_MESSAGE);
							}
								
						}											
						
					}
				}
			});
		}
		return moveButton;
	}

	/**
	 * This method initializes removeButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getRemoveButton() {
		if (removeButton == null) {
			removeButton = new JButton(){
				/**
				 * �ػ水ť
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
			removeButton.setLocation(new Point(156, 442));
			removeButton.setText("�Ƴ���ϵ��");
			removeButton.setSize(new Dimension(101, 30));
			removeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			removeButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					
					int count = privateTree.getSelectionCount();
					
					//ȷ����ѡ��
					if(count > 0)
					{								
						ArrayList<DefaultMutableTreeNode> parentNodeList = new ArrayList<DefaultMutableTreeNode>();
						
						TreeSelectionModel selectionModel = privateTree.getSelectionModel();
						TreePath[] treePaths = selectionModel.getSelectionPaths();
						
						for(int i=0; i<treePaths.length; i++)
						{
							TreePath treePath = treePaths[i];							
							DefaultMutableTreeNode node = (DefaultMutableTreeNode) treePath.getLastPathComponent();
							
							if(node.isLeaf() == true)
							{
								String s = (String) node.getUserObject();							
								String[] tempsStrings = s.split("-");
															
								String username = tempsStrings[1];
								
								DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) node.getParent();
								String groupName = (String) parentNode.getUserObject();
								
								Address address = DataSet.getPrivateAddress(groupName, username);
								ArrayList<Address> addressList = new ArrayList<Address>();
								addressList.add(address);
								
								Login.person.deleteAddressInGroup(groupName, addressList);
								DataSet.mainFrame.left.deleteContacts(groupName, addressList);
								
								//��Ӳ��ظ��ĸ��ڵ㣨��ڵ㣩����չ��
								if(parentNodeList.contains(parentNode) == false)
									parentNodeList.add(parentNode);
							}
						}
						
						//update
						updatePrivateTreeNode();
												
						//չ���ڵ�
						for(int i=0; i<parentNodeList.size(); i++)
						{
							DefaultMutableTreeNode parentNode = parentNodeList.get(i);
							
							int row = 0;								
							for(Enumeration<?> enumeration=privateRootNode.breadthFirstEnumeration(); enumeration.hasMoreElements(); )
							{
								DefaultMutableTreeNode childNode = (DefaultMutableTreeNode) enumeration.nextElement();
								
								if(((String)childNode.getUserObject()).equals((String)parentNode.getUserObject()))
									break;
								
								row++;
							}							
							
							TreePath updatePath = privateTree.getPathForRow(row);
							privateTree.expandPath(updatePath);
							privateTree.setSelectionPath(updatePath);
						}
						repaint();
					}
				}
			});
		}
		return removeButton;
	}
	
	
	/**
	 * ����˽�з������ڵ�
	 */
	protected void updatePrivateTreeNode()
	{
		privateRootNode = new DefaultMutableTreeNode("�Զ������", true);		
						
		DataSet.updatePrivateAddressGroupList();
		
		//get private address group
		for(int i=0; i<DataSet.privateAddressGroupList.size(); i++)
		{
			AddressGroup addressGroup = DataSet.privateAddressGroupList.get(i);
			ArrayList<Address> addressList = addressGroup.getAddressList();
			
			DefaultMutableTreeNode groupNode = new DefaultMutableTreeNode(addressGroup.getGroupName(), true);
			
			for(int j=0; j<addressList.size(); j++)
			{
				Address address = addressList.get(j);
				
				groupNode.add(new DefaultMutableTreeNode(address.getName() + "-" + address.getUsername()));				
			}
			
			privateRootNode.add(groupNode);
		}
		
		TreeModel treeModel = new DefaultTreeModel(privateRootNode);
		privateTree.setModel(treeModel);
	}
	
	/**
	 * ����ȫ�ַ������ڵ�
	 */
	protected void updateGlobalTreeNode()
	{
		globalRootNode = new DefaultMutableTreeNode("ȫ�ַ���", true);		
		
		//get global address group
		for(int i=0; i<DataSet.globalAddressGroupList.size(); i++)
		{
			AddressGroup addressGroup = DataSet.globalAddressGroupList.get(i);
			ArrayList<Address> addressList = addressGroup.getAddressList();
			
			DefaultMutableTreeNode groupNode = new DefaultMutableTreeNode(addressGroup.getGroupName(), true);
			
			for(int j=0; j<addressList.size(); j++)
			{
				Address address = addressList.get(j);
				DefaultMutableTreeNode addressNode = new DefaultMutableTreeNode(address.getName() + "-" + address.getUsername());
				groupNode.add(addressNode);
			}
			
			globalRootNode.add(groupNode);
		}
		
		TreeModel treeModel = new DefaultTreeModel(globalRootNode);
		globalTree.setModel(treeModel);
	}

		
	class CategoryNodeRenderer extends DefaultTreeCellRenderer{
	    private static final long serialVersionUID = 8532405600839140757L;
	   
	    //private final ImageIcon categoryLeafIcon = new ImageIcon(CategoryNodeRenderer.class.getResource("images/categoryLeaf.png"));
	    //private final ImageIcon openFolderIcon = new ImageIcon(CategoryNodeRenderer.class.getResource("images/openFolder.png"));
	    //private final ImageIcon closedFolderIcon = new ImageIcon(CategoryNodeRenderer.class.getResource("images/closedFolder.png"));
	    private final ImageIcon categoryLeafIcon = new ImageIcon("images/categoryLeaf.png");
	    private final ImageIcon openFolderIcon = new ImageIcon("images/openFolder.png");
	    private final ImageIcon closedFolderIcon = new ImageIcon("images/closedFolder.png");
	    
	    public Component getTreeCellRendererComponent(JTree tree,
	                                              Object value,
	                                              boolean sel,
	                                              boolean expanded,
	                                              boolean leaf,
	                                              int row,
	                                              boolean hasFocus){
	     
	      super.getTreeCellRendererComponent(tree,  
	                  value,
	                  sel,  
	                  expanded,  
	                  leaf,  
	                  row,  
	                  hasFocus);  
	     
	      if(leaf){
	        setIcon(categoryLeafIcon);
	      }
	      else if(expanded){
	        setIcon(openFolderIcon);
	      }
	      else{
	        setIcon(closedFolderIcon);
	      }
	         
	      return this;   
	    }
	  }
	
}  //  @jve:decl-index=0:visual-constraint="10,10"
