package gui.contact;

import gui.data.DataSet;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import client.login.Login;

import beans.mail.Address;
import beans.mail.AddressGroup;

public class JGroupPanel extends JPanel {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//��������������
	 private JPanel pNorth = new JPanel(); 
	 private JPanel pCenter = new JPanel();      
	 private JPanel pSouth = new JPanel();
	 
	 //��ǰȫ����ļ���
	 private ArrayList<GroupItem> groupList ;
	 
	 //�Ƿ��ֹ�������
	 private boolean isForbided= false; 
	 
	 
	 
	 //���ǰ��
	 private GroupItem activeGroup =null;
	 transient ActionListener al = new ActionListener() {      
	        public void actionPerformed(ActionEvent e) {      
	            JButton bttTitle = (JButton) e.getSource();      
	            //��ת��ʾ  
	            if(!((GroupItem) bttTitle.getParent()).isShow())
	                expandGroup( (GroupItem) bttTitle.getParent());
	                else
	                collapseGroup((GroupItem) bttTitle.getParent());
	        }      
	    }; 
	    
	

	
	
	/**
	 * 
	 * @param group
	 */
	public JGroupPanel(ArrayList<AddressGroup> group){
		initComponents();
		createDefaultGroupwithGroup(group);
	}
	
	

	/**
	 * ��ʼ�����
	 */
	private void initComponents(){      
        this.setLayout(new BorderLayout());      
        this.add(pNorth, BorderLayout.NORTH);      
        this.add(pCenter, BorderLayout.CENTER);      
        this.add(pSouth, BorderLayout.SOUTH);  
        pSouth.setOpaque(false);
        pNorth.setOpaque(false);
        pCenter.setOpaque(false);
        pNorth.setLayout(new GroupLayout());      
        pCenter.setLayout(new BorderLayout());      
        pSouth.setLayout(new GroupLayout());      
        isForbided= true;      
    } 
	
	
	
	/**
	 * default with group
	 * @param group
	 */
	private void createDefaultGroupwithGroup(
			ArrayList<AddressGroup> group) {
		// TODO Auto-generated method stub
		this.groupList= new ArrayList<GroupItem>();
		for(int i=0;i<group.size();i++){
			insertGroup(i,group.get(i).getGroupName());
			addListItem(i,group.get(i).getAddressList());
		}
	}
	
	/**
	 * չ����
	 * @param groupname
	 */
	public void expandGroup(String groupname){
		 for (int i = getGroupCount() - 1; i >= 0; i--) {      
	            if (getGroupName(i).equals(groupname)) {      
	                expandGroup(i);      
	            }      
	        }      
	}
	
	/**
	 * չ����
	 * @param groupindex
	 */
    public void expandGroup(int groupindex){
    	expandGroup(getGroup(groupindex));
	}
    
    /**
     * չ����
     * @param item
     */
    protected void expandGroup(GroupItem item){
    	pNorth.removeAll();      
        pCenter.removeAll();      
        pSouth.removeAll();      
        boolean hasAddCenter = false;      
        for (int i = 0; i < groupList.size(); i++) {      
            Component c = (Component) groupList.get(i);      
            if (hasAddCenter) {      
                pSouth.add(c);      
            }      
            else if (c == item) {      
                pCenter.add(c, BorderLayout.CENTER);      
                hasAddCenter = true;      
            }      
            else {      
                pNorth.add(c);      
            }      
        }      
        if (activeGroup != null) {      
            activeGroup.collapse();      
        }      
        activeGroup = item;      
        activeGroup.expand();      
        pNorth.doLayout();      
        pCenter.doLayout();      
        pSouth.doLayout();      
        doLayout();      
    }
    
    
    /**
     * ������
     * @param groupname
     */
    public void collapseGroup(String groupname){
    	for (int i = getGroupCount() - 1; i >= 0; i--) {      
            if (getGroupName(i).equals(groupname)) {      
                collapseGroup(i);      
            }      
        }     
    }
    
    /**
     * ������
     * @param groupindex
     */
    public void collapseGroup(int groupindex){
    	collapseGroup(getGroup(groupindex));
    }
    
    /**
     * ������
     * @param item
     */
    protected void collapseGroup(GroupItem item){
    	activeGroup.collapse();      
        activeGroup = null; 
        pNorth.removeAll();      
        pCenter.removeAll();      
        pSouth.removeAll();  
        for (int i = 0; i < groupList.size(); i++){
        	Component c = (Component) groupList.get(i);
        	pNorth.add(c);
        }
        pNorth.doLayout();      
        pCenter.doLayout();      
        pSouth.doLayout();      
        doLayout();
    }
   
    
    /**
     * ��ӵ���
     * @param groupname
     */
    public void addGroup(String groupname) {      
        this.insertGroup(getGroupCount(), groupname);      
    } 
    
    /**
     * ��Ӷ���
     * @param groupnames
     */
    public void addGroup(String groupnames[]) {      
        for (int i = 0; i < groupnames.length; i++) {      
            addGroup(groupnames[i]);      
        }      
       } 
    
    
    /**
     * ����һ����
     * @param index
     * @param name
     */
    public void insertGroup(int index, String name) {      
        if (index < 0 || index > groupList.size()) {      
            throw new ArrayIndexOutOfBoundsException("index:" + index +                      " >count:" + groupList.size());      
        }  
         
        
        int countNorth = pNorth.getComponentCount();      
        int countCenter = pCenter.getComponentCount();      
        int countSouth = pSouth.getComponentCount();      
        GroupItem group;      
        if (index <= countNorth) {      
            group = insertGroup(pNorth, index, name);      
        }      
        else if (index <= countNorth + countCenter) {      
            group = insertGroup(pCenter, index - countNorth, name);      
        }      
        else if (index <= countNorth + countCenter + countSouth) {      
            group = insertGroup(pSouth, index - countNorth - countCenter, name      
                               );      
        }      
        else {      
            group = insertGroup(pSouth, countSouth, name);      
        }      
        group.getTitleButton().addActionListener(al); 
        group.getTitleButton().setFont(new java.awt.Font("�´���Ӳ����", 1, 14) );
        groupList.add(index, group);      
      
    }      
    
     
    /**
     * ����һ����
     * @param p
     * @param index
     * @param name
     * @return
     */
    private GroupItem insertGroup(JPanel p, int index, String name) {      
    	GroupItem group = new GroupItem(name);      
        p.add(group);      
        return group;      
        }    
    
    /**
     * ɾ������
     * @param index
     */
    public void removeGroup(int index) {      
        GroupItem c = (GroupItem) groupList.get(index);  
        
        //NullPointer
        try {
	        c.getParent().remove(c);      
	        c.getTitleButton().removeActionListener(al);      
        } catch(Exception e) {}
        
    }  
    
    /**
     * ɾ������
     * @param name
     */
    public void removeGroup(String name) {      
        for (int i = getGroupCount() - 1; i >= 0; i--) {      
            if (getGroupName(i).equals(name)) {      
                this.removeGroup(i);      
            }      
        }      
    } 
    
    /**
     * ��������
     * @param index
     * @param name
     */
    public void setGroupName(int index, String name) {      
        this.getGroup(index).setName(name);      
    }
    
    /**
     * ȡ������
     * @param groupIndex
     * @return String
     */
    public String getGroupName(int groupIndex) {      
        return getGroup(groupIndex).getName();      
    }
    
    /**
     * ȡ����������
     * @return String[]
     */
    public String[] getGroupNames() {      
        String sResult[] = new String[getGroupCount()];      
        for (int i = 0; i < getGroupCount(); i++) {      
            sResult[i] = getGroupName(i);      
        }      
        return sResult;      
    }    
    
    /**
     * ȡ�õ�ǰ����
     * @return int
     */
    public int getGroupCount() {      
        return groupList.size();      
    }
    
    /**
     * ����һ����ϵ��
     * @param groupIndex
     * @param some
     */
    public void addListItem(int groupIndex,ArrayList<Address> some){
    	getGroup(groupIndex).getList().addGroup(some);
    }
    
    
    
    /**
     * ���뵥����ϵ��
     * @param groupIndex
     * @param some
     */
    public void insertListItem(int groupIndex, Address some) {      
        getGroup(groupIndex).getList().addCell(some);          
        }
    
    /**
     * �Ƴ�������ϵ��
     * @param groupIndex
     * @param Index
     */
    public void removeListItem(int groupIndex, int Index) {      
        getGroup(groupIndex).getList().remove(Index);      
    }
    
    
    /**
     * �Ƴ������ϵ��
     * @param groupIndex
     * @param some
     */
    public void removeListItem(int groupIndex, ArrayList<Address> some) {      
        getGroup(groupIndex).getList().removeGroup(some);      
    }
    
    /**
     * ȡ�ó�Ա���
     * @param groupIndex
     * @param memberIndex
     * @return ListItem
     */
    public ListItem getListItem(int groupIndex, int memberIndex) {      
        return getGroup(groupIndex).getList().getItem(memberIndex);      
    }
    
    
    /**
     * ��Ա�������
     * @param groupIndex
     * @return int
     */
    public int getCount(int groupIndex) {      
        return getGroup(groupIndex).getList().getNumber();
    }
    
    /**
     * ������
     * @param name
     * @return int
     */
    public int getGroupIndex(String name){
    	for(int i=0;i<groupList.size();i++)
    		if(groupList.get(i).getTitleButton().getText().equals(name))
    		return i;
    	return -1;
    }
     
    
    /**
     * ȡ����
     * @param index
     * @return GroupItem
     */
    protected GroupItem getGroup(int index) {      
        return  (GroupItem)groupList.get(index);      
    }
    
    
    
    /**
     * ��д��addImpl����,��ֹ����JGroupPane��������   
     */
    protected void addImpl(Component comp, Object constraints, int index) {      
        if (isForbided) {      
            if (! (comp instanceof GroupItem)) {      
                throw new UnsupportedOperationException(      
                    "JGroupPane can't add component!");      
            }      
        }      
        else {      
            super.addImpl(comp, constraints, index);      
        }      
    } 
    
    
    /**
     * ˢ�£��ػ���	
     */
    public void update(){
	    for(int i=0;i<groupList.size();i++)
	    	removeGroup(i);
	    
	    //update online person
	    Login.person.updateOnlinePersonManager();
	    
	    //update address group
	    DataSet.updatePrivateAddressGroupList();
	    DataSet.updateGlobalAddressGroupList();
	    
	    ArrayList<AddressGroup> all =new ArrayList<AddressGroup>();
   	    all.addAll(DataSet.globalAddressGroupList);
   	    all.addAll(DataSet.privateAddressGroupList);
   	    createDefaultGroupwithGroup(all);
   	    repaint();
   	    
    }  	


}
