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
		
		//获取节点
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
			
			privateTree.getSelectionModel().setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);  //设置离散模式
			privateTree.setCellRenderer(new CategoryNodeRenderer());  //设置渲染器
			
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
			
			globalTree.getSelectionModel().setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);  //设置离散模式
			globalTree.setCellRenderer(new CategoryNodeRenderer());  //设置渲染器
					
			globalTree.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					//System.out.println("mouseClicked()"); // TODO Auto-generated Event stub mouseClicked()
					
					//双击
					if(e.getClickCount() == 2)
					{
						TreePath path = globalTree.getPathForLocation(e.getX(), e.getY());
						DefaultMutableTreeNode rightNode = (DefaultMutableTreeNode)globalTree.getLastSelectedPathComponent();
						
						//是叶节点
						if(rightNode.isLeaf() == true)
						{						
							//左边的选择
							int leftCount = privateTree.getSelectionCount();
							
							//确保只选择一个
							if(leftCount == 1)
							{						
								TreeSelectionModel selectionModel = privateTree.getSelectionModel();
								TreePath treePath = selectionModel.getSelectionPath();
								
								DefaultMutableTreeNode leftNode = (DefaultMutableTreeNode) treePath.getLastPathComponent();
								
								//如果是组节点
								if(leftNode.getParent() == privateRootNode)
								{
									String leftGroupName = (String) leftNode.getUserObject();
									//System.out.println(leftGroupName);
									
									//右边
									String s = (String) rightNode.getUserObject();
									String[] tempsStrings = s.split("-");
									String username = tempsStrings[1];
									
									DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) rightNode.getParent();
									String groupName = (String) parentNode.getUserObject();
									
									Address address = DataSet.getGlobalAddress(groupName, username);
									ArrayList<Address> addressList = new ArrayList<Address>();
									addressList.add(address);
									
									//添加
									Login.person.addAddressInGroup(leftGroupName, addressList);
									DataSet.mainFrame.left.addContacts(leftGroupName,addressList);
									
									//update
									updatePrivateTreeNode();
									
									
									//展开节点
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
									JOptionPane.showMessageDialog(null, "请先选中左边的组节点", "提示",JOptionPane.INFORMATION_MESSAGE);
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
				 * 重绘按钮
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
			deleteGroupButton.setText("删除组");
			deleteGroupButton.setSize(new Dimension(75, 30));
			deleteGroupButton.setLocation(new Point(15, 11));
			deleteGroupButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			deleteGroupButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					 // TODO Auto-generated Event stub actionPerformed()
					
					int count = privateTree.getSelectionCount();
										
					//确保只选择一个
					if(count == 1)
					{						
						TreeSelectionModel selectionModel = privateTree.getSelectionModel();
						TreePath treePath = selectionModel.getSelectionPath();
						
						DefaultMutableTreeNode node = (DefaultMutableTreeNode) treePath.getLastPathComponent();
						
						//如果是组节点
						if(node.getParent() == privateRootNode)
						{
							String groupName = (String) node.getUserObject();							
							
							int choice = JOptionPane.showConfirmDialog(null, "确定要删除" + groupName + "组吗?", "提示", JOptionPane.YES_NO_OPTION);
							
							if(choice == 0)  //第一个按钮
							{
								if(Login.person.deleteAddressGroup(groupName) == true)
								{
									DataSet.mainFrame.left.removeGroup(groupName);
									//JOptionPane.showMessageDialog(null, "删除成功", "提示",JOptionPane.INFORMATION_MESSAGE);	
									updatePrivateTreeNode();
								}
								
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "请选择组节点", "提示",JOptionPane.INFORMATION_MESSAGE);
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
				 * 重绘按钮
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
			addGroupButton.setText("新建组");
			addGroupButton.setLocation(new Point(182, 11));
			addGroupButton.setSize(new Dimension(75, 30));
			addGroupButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			addGroupButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					
					String groupName = JOptionPane.showInputDialog("请输入组名");
					
					//组名有效
					if(groupName != null && groupName.length() > 0)
					{
						if(Login.person.addAddressInGroup(groupName, new ArrayList<Address>()) == true)
						{
							DataSet.mainFrame.left.addGroup(groupName);
							//JOptionPane.showMessageDialog(null, "添加成功", "提示",JOptionPane.INFORMATION_MESSAGE);							
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
				 * 重绘按钮
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
					
					//确保有选择
					if(rightCount > 0)
					{
						//左边的选择
						int leftCount = privateTree.getSelectionCount();
						
						//确保只选择一个
						if(leftCount == 1)
						{						
							TreeSelectionModel leftSelectionModel = privateTree.getSelectionModel();
							TreePath leftTreePath = leftSelectionModel.getSelectionPath();
							
							DefaultMutableTreeNode leftNode = (DefaultMutableTreeNode) leftTreePath.getLastPathComponent();
							
							//如果是组节点
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
								
								//添加
								Login.person.addAddressInGroup(leftGroupName, addressList);
								
								DataSet.mainFrame.left.addContacts(leftGroupName,addressList);
								//update
								updatePrivateTreeNode();
																
								//展开节点
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
								JOptionPane.showMessageDialog(null, "请先选中左边的组节点", "提示",JOptionPane.INFORMATION_MESSAGE);
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
				 * 重绘按钮
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
			removeButton.setText("移除联系人");
			removeButton.setSize(new Dimension(101, 30));
			removeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			removeButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					
					int count = privateTree.getSelectionCount();
					
					//确保有选择
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
								
								//添加不重复的父节点（组节点）用于展开
								if(parentNodeList.contains(parentNode) == false)
									parentNodeList.add(parentNode);
							}
						}
						
						//update
						updatePrivateTreeNode();
												
						//展开节点
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
	 * 更新私有分组树节点
	 */
	protected void updatePrivateTreeNode()
	{
		privateRootNode = new DefaultMutableTreeNode("自定义分组", true);		
						
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
	 * 更新全局分组树节点
	 */
	protected void updateGlobalTreeNode()
	{
		globalRootNode = new DefaultMutableTreeNode("全局分组", true);		
		
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
