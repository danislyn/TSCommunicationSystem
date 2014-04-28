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
	 * ��дJList
	 */
	private static final long serialVersionUID = 1L;
	//���б�Ҫ�������ʾ��ͼ����ϵ�ˡ��鿴��ť��ͼ��ĵ�ַ����ťͼ���ַ��ʵ�ɲ�Ҫ��ֱ����Mycell�г�ʼ����
	private ArrayList<ListItem> myList;  
    DefaultListModel listModel;
    
    
    /**
     * Ĭ�Ϲ���
     */
    public MyList(){
    	this(new ArrayList<Address>());
    }
    
    /**
     * ָ�������б�Ĺ��췽��
     * @param group
     */
	public MyList(ArrayList<Address> group){
		intiListItem(group);
		initListModel();
		this.setModel(listModel);
		setCellRenderer(new MyCell());//ʹ���Լ���CellRenderer   
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setSelectionBackground(new Color(248,203,245));
		this.setSelectionForeground(Color.BLUE);
		this.setBackground(Color.WHITE);
		this.setForeground(Color.black);
		this.addMouseListener(new MouseAdapter(){
			public void mouseClicked(java.awt.event.MouseEvent e) {
				
				//����˫��
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
	 * ��ʼ��list item
	 * @param group
	 */
	void intiListItem(ArrayList<Address> group){
		myList=new ArrayList<ListItem>();
		
		for(int i=0;i<group.size();i++){
			addCell(group.get(i));
		}
	}
	
	/**
	 * ��ʼ��list model
	 */
	void initListModel(){
		listModel = new DefaultListModel();   
		 for(int i=0;i<myList.size();i++){   
	     listModel.add(i, myList.get(i));   
	  }  
	}
	
	/**
	 * ���cell
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
	 * �Ƴ�cell
	 * @param some
	 */
	public void removeCell(Address some){
		int index=getIndex(some.getUsername());
		removeItem(index);
	}
	
	/**
	 * �����
	 * @param others
	 */
	public void addGroup(ArrayList<Address> others){
		for(int i=0;i<others.size();i++){
			addCell(others.get(i));
			
		}
	}
	
	/**
	 * �Ƴ���
	 * @param some
	 */
	public void removeGroup(ArrayList<Address> some){
		for(int i=0;i<some.size();i++){
			removeCell(some.get(i));
			
			
		}
		
	}
	
	
	/**
	 * �Ƴ�list item
	 * @param index
	 */
	public void removeItem(int index){
		myList.remove(index);
		listModel.remove(index);
		this.doLayout();
	}
	
	/**
	 * ��ȡlist item
	 * @param index
	 * @return ListItem
	 */
	public ListItem getItem(int index){
		return myList.get(index);
	}
	
	/**
	 * ȥ�±�
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
	 * ȡsize
	 * @return int
	 */
	public int getNumber(){
		return myList.size();
	}
	

}
