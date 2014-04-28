package gui.contact;

import gui.data.DataSet;
import gui.selfInfo.SelfInfoPane;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import client.login.Login;

import beans.information.Information;
import beans.mail.Address;

public class MyList extends JList {
	/**
	 * 重写JList
	 */
	private static final long serialVersionUID = 1L;
	//该列表要保存的显示的图像、联系人、查看按钮的图像的地址（按钮图像地址其实可不要，直接在Mycell中初始化）
	private ArrayList<ListItem> myList;  
    DefaultListModel listModel;
    
    
    /**
     * 默认构造
     */
    public MyList(){
    	this(new ArrayList<Address>());
    }
    
    /**
     * 指定组内列表的构造方法
     * @param group
     */
	public MyList(ArrayList<Address> group){
		intiListItem(group);
		initListModel();
		this.setModel(listModel);
		setCellRenderer(new MyCell());//使用自己的CellRenderer   
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setSelectionBackground(new Color(248,203,245));
		this.setSelectionForeground(Color.BLUE);
		this.setBackground(Color.WHITE);
		this.setForeground(Color.black);
		this.addMouseListener(new MouseAdapter(){
			public void mouseClicked(java.awt.event.MouseEvent e) {
				
				//监听双击
				if(e.getClickCount() == 2)
				{
					int i=getSelectedIndex();
					String userno=myList.get(i).getuserno();
					Information info=Login.person.getOtherInformation(userno);
					SelfInfoPane other =new SelfInfoPane(info, true);
					DataSet.mainFrame.left.subpane.add(other,"other");
					DataSet.mainFrame.left.card.show(DataSet.mainFrame.left.subpane, "other");
					DataSet.mainFrame.left.subpane.setVisible(true);
					
					
				}
				
			}
		});
		
	}
	
	/**
	 * 初始化list item
	 * @param group
	 */
	void intiListItem(ArrayList<Address> group){
		myList=new ArrayList<ListItem>();
		
		for(int i=0;i<group.size();i++){
			addCell(group.get(i));
		}
	}
	
	/**
	 * 初始化list model
	 */
	void initListModel(){
		listModel = new DefaultListModel();   
		 for(int i=0;i<myList.size();i++){   
	     listModel.add(i, myList.get(i));   
	  }  
	}
	
	/**
	 * 添加cell
	 * @param someone
	 */
	public void addCell(Address someone){
		String s="isoff.png";
		if(Login.person.isUserOnline(someone.getUsername()))
			s="ison.png";
		myList.add(new ListItem(s,someone.getUsername(),someone.getName(),"check.png"));
		listModel.add(myList.size()-1, myList.get(myList.size()-1));	
		this.doLayout();
	}
	
	/**
	 * 移出cell
	 * @param some
	 */
	public void removeCell(Address some){
		int index=getIndex(some.getUsername());
		removeItem(index);
	}
	
	/**
	 * 添加组
	 * @param others
	 */
	public void addGroup(ArrayList<Address> others){
		for(int i=0;i<others.size();i++){
			addCell(others.get(i));
			
		}
	}
	
	/**
	 * 移出组
	 * @param some
	 */
	public void removeGroup(ArrayList<Address> some){
		for(int i=0;i<some.size();i++){
			removeCell(some.get(i));
			
			
		}
		
	}
	
	
	/**
	 * 移出list item
	 * @param index
	 */
	public void removeItem(int index){
		myList.remove(index);
		listModel.remove(index);
		this.doLayout();
	}
	
	/**
	 * 获取list item
	 * @param index
	 * @return ListItem
	 */
	public ListItem getItem(int index){
		return myList.get(index);
	}
	
	/**
	 * 去下标
	 * @param name
	 * @return int
	 */
	public int getIndex(String name){
		for(int i=0;i<myList.size();i++)
			if(myList.get(i).getuserno().equals(name))
		        return i;
		return -1;
	}
	
	/**
	 * 取size
	 * @return int
	 */
	public int getNumber(){
		return myList.size();
	}
	

}
