package gui.mail;

import gui.data.DataSet;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import beans.mail.Address;
import beans.mail.AddressGroup;

public class AddressPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTree jTree = null;
	private JScrollPane jScrollPane = null;
	private JButton okButton = null;
	private JLabel infoLabel1 = null;
	private JLabel infoLabel2 = null;
	
	private DefaultMutableTreeNode rootNode;
	private JLabel infoLabel = null;


	/**
	 * This is the default constructor
	 */
	public AddressPanel() {
		super();
		setOpaque(false);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		infoLabel = new JLabel();
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		infoLabel.setSize(new Dimension(156, 17));
		infoLabel.setLocation(new Point(10, 369));
		infoLabel.setText("双击添加");
		infoLabel2 = new JLabel();
		infoLabel2.setBounds(new Rectangle(10, 401, 156, 15));
		infoLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel2.setHorizontalTextPosition(SwingConstants.CENTER);
		infoLabel2.setText("按\"确定\"覆盖(可重复提交)");
		infoLabel1 = new JLabel();
		infoLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel1.setHorizontalTextPosition(SwingConstants.CENTER);
		infoLabel1.setSize(new Dimension(156, 16));
		infoLabel1.setLocation(new Point(10, 386));
		infoLabel1.setText("按住Ctrl或Shift可多选");
		this.setSize(239, 419);
		this.setLayout(null);
		this.add(getJScrollPane(), null);
		this.add(getOkButton(), null);
		this.add(infoLabel1, null);
		this.add(infoLabel2, null);
		this.add(infoLabel, null);
	}

	public JScrollPane getJScrollPane()
	{
		if(jScrollPane == null)
		{
			jScrollPane = new JScrollPane(getJTree());
			jScrollPane.setOpaque(false);
			jScrollPane.setBounds(new Rectangle(10, 6, 222, 362));
		}
		return jScrollPane;
	}
	
	/**
	 * This method initializes jTree	
	 * 	
	 * @return javax.swing.JTree	
	 */
	private JTree getJTree() {
		if (jTree == null) {
			updateTreeNode();
			jTree = new JTree(rootNode);			
			jTree.setBounds(new Rectangle(7, 6, 235, 378));
			
			getTreeSelectionModel().setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);  //设置离散模式
			jTree.setCellRenderer(new CategoryNodeRenderer());  //设置渲染器
			
			jTree.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					//System.out.println("mouseClicked()"); // TODO Auto-generated Event stub mouseClicked()
					
					//双击
					if(e.getClickCount() == 2)
					{
						TreePath path = jTree.getPathForLocation(e.getX(), e.getY());
						DefaultMutableTreeNode node = (DefaultMutableTreeNode) jTree.getLastSelectedPathComponent();
						
						if(node.isLeaf() == true)
						{
							String s = (String) node.getUserObject();
							String[] tempsStrings = s.split("-");
							
							DataSet.mainFrame.mailBoxFrame.writeMailPanel.addReceiverUsername(tempsStrings[1]);
						}						
					}
				}
			});
		}
		return jTree;
	}
	
	/**
	 * This method initializes okButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getOkButton() {
		if (okButton == null) {
			okButton = new JButton(){
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
			okButton.setBounds(new Rectangle(169, 388, 63, 28));
			okButton.setText("确定");
			okButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			okButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					
					int count = jTree.getSelectionCount();
					
					//确保有选择
					if(count > 0)
					{
						ArrayList<String> usernameList = new ArrayList<String>();
						
						TreeSelectionModel selectionModel = jTree.getSelectionModel();
						TreePath[] treePaths = selectionModel.getSelectionPaths();
						
						for(int i=0; i<treePaths.length; i++)
						{
							TreePath treePath = treePaths[i];							
							DefaultMutableTreeNode node = (DefaultMutableTreeNode) treePath.getLastPathComponent();
							
							if(node.isLeaf() == true)
							{
								String s = (String) node.getUserObject();							
								String[] tempsStrings = s.split("-");
								
								usernameList.add(tempsStrings[1]);
							}
						}
						
						DataSet.mainFrame.mailBoxFrame.writeMailPanel.setReceiverUsernameList(usernameList);
					}
				}
			});
		}
		return okButton;
	}
	
	
	/**
	 * 重新载入树节点
	 */
	protected void updateTreeNode()
	{
		rootNode = new DefaultMutableTreeNode("地址簿列表", true);		
		
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
			
			rootNode.add(groupNode);
		}
		
		//update
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
			
			rootNode.add(groupNode);
		}
		
	}
	
	
	private DefaultTreeModel getTreeModel(){
		return (DefaultTreeModel)jTree.getModel();
	}

	private DefaultMutableTreeNode getRootNode(){
		return (DefaultMutableTreeNode)getTreeModel().getRoot();
	}
		  
	private TreeSelectionModel getTreeSelectionModel(){
		return jTree.getSelectionModel();
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
