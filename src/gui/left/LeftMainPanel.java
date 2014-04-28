package gui.left;

import gui.announce.AnnounceP;
import gui.announce.AnnouncementPanel;
import gui.contact.JGroupPanel;
import gui.data.DataSet;
import gui.selfInfo.MoreActionPanel;
import gui.selfInfo.SelfInfoPane;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import beans.mail.Address;
import beans.mail.AddressGroup;

import client.login.Login;

public class LeftMainPanel extends JPanel {
	/**
	 * ��ߵ���Ҫ�İ�
	 */
	private static final long serialVersionUID = 1L;
	public  JPanel pane = null; // ��Ҫ��JPanel����JPanel�Ĳ��ֹ��������ó�CardLayout
	public  JPanel subpane=null;
    private JPanel buttonp = null; // �Ű�ť��JPanel
    public  CardLayout card = null; // CardLayout���ֹ�����
    private JButton info = null; 
    private JButton contact = null; 
    private JButton announce= null; // ������ֱ�ӷ�ת��JPanel����İ�ť
    private AnnounceP  announcep = null; // Ҫ�л�������JPanel
    private JGroupPanel contactp=null;
    public SelfInfoPane selfp=null;
    private boolean aFlag=false;
    public MoreActionPanel p1;
    public AnnouncementPanel p2;
    
   
    
    transient ActionListener al = new ActionListener() {      
        public void actionPerformed(ActionEvent e) {      
            JButton bttTitle = (JButton) e.getSource();      
             aFlag=((SelfInfoPane) bttTitle.getParent()).getState();
             card.show(subpane, "info");
        	 subpane.setVisible(aFlag);
        	    
            
        }      
    }; 
    transient ActionListener a2= new ActionListener() {      
        public void actionPerformed(ActionEvent e) {      
            JButton bttTitle = (JButton) e.getSource();     
            aFlag=((AnnounceP)bttTitle.getParent()).getState();
            card.show(subpane, "announce");
       	    subpane.setVisible(aFlag);
            
            
        }      
    }; 
    
    /**
     * Ĭ�Ϲ���
     */
    public LeftMainPanel(){
    	this.setLayout(null);
    	init();
    }
    
    /**
     * ��ʼ��
     */
    private void init(){
    	card = new CardLayout(5, 5);
        pane = new JPanel(card){
        	
        	/**
			 * �ػ�����
			 */
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) { 
    	        g.setColor(Color.blue); 
    	        ImageIcon img=null;
    			img = new ImageIcon("images/cardp.jpg");
    			g.drawImage(img.getImage(),0,0,null); 
    	    }
    	    
        }; // JPanel�Ĳ��ֹ��������ó�CardLayout
        
        buttonp=new JPanel();
        buttonp.setLayout(null);
        info=new JButton(){
    		/**
			 * �ػ���ť
			 */
			private static final long serialVersionUID = 1L;
			{
    			setMargin(new Insets(2,2,2,2));
    			setBounds(100,10,80,38);
    			setContentAreaFilled(false);
				setOpaque(false);
    		}
            protected void paintComponent(Graphics g){
            	ImageIcon imageicon=new ImageIcon("images/info.png");
				g.drawImage(imageicon.getImage(), 0, 0,null);
				super.paintComponent(g);	
				
			}

    		
    	};
    	info.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    	contact=new JButton(){
    		/**
			 * �ػ���ť
			 */
			private static final long serialVersionUID = 1L;
			{
    			setMargin(new Insets(2,2,2,2));
    			setBounds(175,50,80,38);
    			setContentAreaFilled(false);
				setOpaque(false);
    		}
            protected void paintComponent(Graphics g){
            	ImageIcon imageicon=new ImageIcon("images/contact.png");
				g.drawImage(imageicon.getImage(), 0, 0,null);
				super.paintComponent(g);	
				
			}
    		
    	};
    	contact.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    	announce=new JButton(){
    		/**
			 * �ػ���ť
			 */
			private static final long serialVersionUID = 1L;
			{
    			setMargin(new Insets(2,2,2,2));
    			setBounds(130,100,80,38);
    			setContentAreaFilled(false);
				setOpaque(false);
    		}
            protected void paintComponent(Graphics g){
            	ImageIcon imageicon=new ImageIcon("images/annouce.png");
				g.drawImage(imageicon.getImage(), 0, 0,null);
				super.paintComponent(g);	
				
			}
    	};
    	announce.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    	
    	buttonp.add(info);
    	buttonp.add(contact);
    	buttonp.add(announce);
    	
    	
    	selfp = new SelfInfoPane(Login.person.getMyInformation(), false);
    	selfp.getButton().addActionListener(al);
    	
    	ArrayList<AddressGroup> all =new ArrayList<AddressGroup>();
    	all.addAll(DataSet.globalAddressGroupList);
    	all.addAll(DataSet.privateAddressGroupList);
    	
    	contactp =  new JGroupPanel(all);
    	
        
        
    	
    	
       announcep=new AnnounceP();
        
      
        JScrollPane sp=new JScrollPane(contactp);
        sp.setHorizontalScrollBarPolicy(      
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    	
    	pane.add(selfp, "p1");
        pane.add(sp, "p2");
        pane.add(announcep, "p3");
        
       
        
        info.addActionListener(new ActionListener() { // ֱ�ӷ�ת��p_1
            public void actionPerformed(ActionEvent e) {
                card.show(pane, "p1");
                pane.setVisible(true);
                subpane.setVisible(false);
            }
        });
        contact.addActionListener(new ActionListener() { // ֱ�ӷ�ת��p_2
            public void actionPerformed(ActionEvent e) {
            	contactp.update();
                card.show(pane, "p2");
                pane.setVisible(true);
                subpane.setVisible(false);
            }
        });
        announce.addActionListener(new ActionListener() { // ֱ�ӷ�ת��p_3
            public void actionPerformed(ActionEvent e) {
            	//update
            	Login.person.updateAnnouncementManager();
        
                card.show(pane, "p3");
                subpane.setVisible(false);
            }
        });
        buttonp.setOpaque(false);
        pane.setOpaque(false);
        buttonp.setBounds(0, 20, 320, 200);
        pane.setBounds(0, 220, 310, 200);
        
        //p1=new JPanel(){
        p1 = new MoreActionPanel(){
        	/**
			 * �ػ�����
			 */
			private static final long serialVersionUID = 1L;

			{
        	setOpaque(false);
        	}
        	
        protected void paintComponent(Graphics g){
        		
             	ImageIcon imageicon=new ImageIcon("images/sub.png");
 				g.drawImage(imageicon.getImage(), 0, 0,null);
 				super.paintComponent(g);	
 				
 			}
        };
        p2=new AnnouncementPanel(){
        	/**
			 * �ػ�����
			 */
			private static final long serialVersionUID = 1L;

			{
        	setOpaque(false);
        	}
        	
           protected void paintComponent(Graphics g){
        		
             	ImageIcon imageicon=new ImageIcon("images/sub2.png");
 				g.drawImage(imageicon.getImage(), 0, 0,null);
 				super.paintComponent(g);	
 				
 			}
        };
        subpane=new JPanel(card){
        	/**
			 * �ػ�����
			 */
			private static final long serialVersionUID = 1L;

			{
        		setOpaque(false);
        		setBounds(0,420,310,200);
        		setVisible(aFlag);
        		add(p1,"info");
        		add(p2,"announce");
        	}
        	
        };
        
        add(buttonp);
        add(pane);
        add(subpane);
        setVisible(true);
    }
    
    
    /**
     * �����������ϵ��
     * @param groupname ����
     * @param other ������ϵ��
     */
    public void addContacts(String groupname,ArrayList<Address> other){
    	int index=contactp.getGroupIndex(groupname);
    	if(index!=-1)
    	contactp.addListItem(index, other);
    }
    
    /**
     * ɾ����
     * @param groupName ����
     */
    public void removeGroup(String groupName){
    	contactp.removeGroup(groupName);
    }
    
    /**
     * �����
     * @param groupName ����
     */
    public void addGroup(String groupName){
    	contactp.addGroup(groupName);
    }
    
    /**
     * ɾ��������ϵ��
     * @param groupname ����
     * @param other ɾ������ϵ��
     */
    public void deleteContacts(String groupname,ArrayList<Address> other){
    	int index=contactp.getGroupIndex(groupname);
    	if(index!=-1)
    	contactp.removeListItem(index, other);
    }
   
    

}
