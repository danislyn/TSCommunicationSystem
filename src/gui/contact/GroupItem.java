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
	 * 一个组的面板
	 */
	private static final long serialVersionUID = 1L;
	private ImageIcon imageicon;
	private JButton bttGroupTitle;
    private JPanel pMembers ;      
    private JScrollPane sp;
    private MyList list;
    private boolean visibleState=false;
    
    
    /**
     * 默认构造
     */
    public GroupItem(){
    	this("");
    }
    
   
    /**
     * 指定组名的构造方法
     * @param name
     */
	public GroupItem(String name) {
		// TODO Auto-generated constructor stub
		
		imageicon=new ImageIcon("images/group.png");
		pMembers = new JPanel();
		list=new MyList();
		bttGroupTitle = new JButton(){
		      /**
			 * 重画按钮背景和边框
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
	 * 取得组的成员组件面板
	 * @return JPanel
	 */
	public JPanel getMembersContainer() {      
        return pMembers;      
    } 
	
	/**
	 * 收缩组
	 */
	public void collapse() {      
        sp.setVisible(false); 
		//pMembers.setVisible(false);
        this.visibleState=false;
        this.revalidate();      
    }
	
	/**
	 * 展开组
	 */
	public void expand() {      
        sp.setVisible(true); 
        this.visibleState=true;
        //pMembers.setVisible(true);
        this.revalidate();      
    }
	
	/**
	 * 获得面板当前是否显示
	 * @return boolean
	 */
	public boolean isShow(){
		return this.visibleState;
	}
	
	/**
	 * 设置组名
	 */
	public void setName(String name) {      
        bttGroupTitle.setText(name);      
    }
	
	/**
	 * 获得组名
	 * @return String
	 */
	public String getName() {      
        return bttGroupTitle.getText();      
    } 
	                            
	
	
	/**
	 * 获得List
	 * @return MyList
	 */
	public MyList getList() {
		// TODO Auto-generated method stub
		return list;
		}
	
	/**
	 * 添加一条记录
	 * @param someone
	 */
	public void addListItem(Address someone) {
		// TODO Auto-generated method stub
		list.addCell(someone);
	}
	
	/**
	 * 添加一组记录
	 * @param others
	 */
	public void addListItem(ArrayList<Address> others){
		list.addGroup(others);
	}
	
	/**
	 * 删除一条记录
	 * @param someone
	 */
	public void removeListItem(Address someone) {
		// TODO Auto-generated method stub
		list.removeCell(someone);
	}
	
	/**
	 * 删除一组记录
	 * @param some
	 */
	public void removeListItem(ArrayList<Address> some) {
		// TODO Auto-generated method stub
		list.removeGroup(some);
	}
	
	
   
	
    /**
     * 取得组的标题按钮
     * @return JButton
     */
	public JButton getTitleButton() {
		// TODO Auto-generated method stub
		return bttGroupTitle;
	}
	
	
	/**
	 * 重写toString方法
	 */
	public String toString() {      
        return getName();      
    }

}
