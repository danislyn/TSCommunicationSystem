package gui.contact;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

import beans.mail.Address;

public class GroupItem extends JPanel {
	
	/**
	 * һ��������
	 */
	private static final long serialVersionUID = 1L;
	private ImageIcon imageicon;
	private JButton bttGroupTitle;
    private JPanel pMembers ;      
    private JScrollPane sp;
    private MyList list;
    private boolean visibleState=false;
    
    
    /**
     * Ĭ�Ϲ���
     */
    public GroupItem(){
    	this("");
    }
    
   
    /**
     * ָ�������Ĺ��췽��
     * @param name
     */
	public GroupItem(String name) {
		// TODO Auto-generated constructor stub
		
		imageicon=new ImageIcon("images/group.png");
		pMembers = new JPanel();
		list=new MyList();
		bttGroupTitle = new JButton(){
		      /**
			 * �ػ���ť�����ͱ߿�
			 */
			private static final long serialVersionUID = 1L;

			{
				setContentAreaFilled(false);
				setOpaque(false);
			}
			protected void paintComponent(Graphics g){
				
				JPanel p=new JPanel();
				g.drawImage(imageicon.getImage(), 0, 0,bttGroupTitle.getWidth()-1,bttGroupTitle.getHeight()-1,p);
				super.paintComponent(g);	
				
			}
			
			protected void paintBorder(Graphics g){}
			
		};      
		bttGroupTitle.setText(name);      
        bttGroupTitle.setFocusable(false);
        bttGroupTitle.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Color bg=new Color(248,213,247);
        pMembers.setBackground(bg);
        pMembers.setLayout(new GroupLayout());
        
        
        this.setLayout(new BorderLayout());      
        this.add(bttGroupTitle, BorderLayout.NORTH);      
  
              
  
        Color thumbColor = UIManager.getColor("ScrollBar.thumb");      
        Color trackColor = UIManager.getColor("ScrollBar.track");      
        Color trackHighlightColor = UIManager.getColor(      
            "ScrollBar.trackHighlight");      
  
        UIManager.put("ScrollBar.thumb", bg);      
        UIManager.put("ScrollBar.track", bg);      
        UIManager.put("ScrollBar.trackHighlight", bg);   
        
        pMembers.add(list);
        sp = new JScrollPane(pMembers);      
        sp.setHorizontalScrollBarPolicy(      
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);         
        this.add(sp,BorderLayout.CENTER);
        //this.add(pMembers,BorderLayout.CENTER);
        collapse();      
        UIManager.put("ScrollBar.thumb", thumbColor);      
        UIManager.put("ScrollBar.track", trackColor);      
        UIManager.put("ScrollBar.trackHighlight", trackHighlightColor);  
        
		
	}
	
	
	
	/**
	 * ȡ����ĳ�Ա������
	 * @return JPanel
	 */
	public JPanel getMembersContainer() {      
        return pMembers;      
    } 
	
	/**
	 * ������
	 */
	public void collapse() {      
        sp.setVisible(false); 
		//pMembers.setVisible(false);
        this.visibleState=false;
        this.revalidate();      
    }
	
	/**
	 * չ����
	 */
	public void expand() {      
        sp.setVisible(true); 
        this.visibleState=true;
        //pMembers.setVisible(true);
        this.revalidate();      
    }
	
	/**
	 * �����嵱ǰ�Ƿ���ʾ
	 * @return boolean
	 */
	public boolean isShow(){
		return this.visibleState;
	}
	
	/**
	 * ��������
	 */
	public void setName(String name) {      
        bttGroupTitle.setText(name);      
    }
	
	/**
	 * �������
	 * @return String
	 */
	public String getName() {      
        return bttGroupTitle.getText();      
    } 
	                            
	
	
	/**
	 * ���List
	 * @return MyList
	 */
	public MyList getList() {
		// TODO Auto-generated method stub
		return list;
		}
	
	/**
	 * ���һ����¼
	 * @param someone
	 */
	public void addListItem(Address someone) {
		// TODO Auto-generated method stub
		list.addCell(someone);
	}
	
	/**
	 * ���һ���¼
	 * @param others
	 */
	public void addListItem(ArrayList<Address> others){
		list.addGroup(others);
	}
	
	/**
	 * ɾ��һ����¼
	 * @param someone
	 */
	public void removeListItem(Address someone) {
		// TODO Auto-generated method stub
		list.removeCell(someone);
	}
	
	/**
	 * ɾ��һ���¼
	 * @param some
	 */
	public void removeListItem(ArrayList<Address> some) {
		// TODO Auto-generated method stub
		list.removeGroup(some);
	}
	
	
   
	
    /**
     * ȡ����ı��ⰴť
     * @return JButton
     */
	public JButton getTitleButton() {
		// TODO Auto-generated method stub
		return bttGroupTitle;
	}
	
	
	/**
	 * ��дtoString����
	 */
	public String toString() {      
        return getName();      
    }

}
