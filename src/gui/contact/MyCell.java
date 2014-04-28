package gui.contact;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

public class MyCell extends JPanel implements ListCellRenderer {
	/**
	 * 重写定义JList中的单元
	 */
	private static final long serialVersionUID = 1L;
	private JLabel label;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	
	private ImageIcon isOn,check;
	
	
	/**
	 * 构造方法
	 */
	MyCell(){
		
		label = new JLabel();
		label2 = new JLabel();
		label3 = new JLabel();
		label4 = new JLabel();
		
		
		this.setLayout(new GridLayout(1,4));
        setOpaque(true);
        setToolTipText("双击查看信息！");
        this.add(label);
        this.add(label2);
        this.add(label3);
        this.add(label4);
	}
	

	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		// TODO Auto-generated method stub
		ListItem aItem =(ListItem) value;
		isOn =new ImageIcon("images/"+aItem.getisOnicon());
		check=new ImageIcon("images/"+(aItem.getcheckicon()));
		label.setIcon(isOn);
		label2.setText(aItem.getuserno());
		label3.setText(aItem.getname());
		label4.setIcon(check);
		if(isSelected){
			setBackground(list.getSelectionBackground());   
            setForeground(list.getSelectionForeground());
            label2.setForeground(list.getSelectionForeground());
            label3.setForeground(list.getSelectionForeground());
            label2.setFont(new java.awt.Font("黑体",1,10));
            label3.setFont(new java.awt.Font("黑体",1,10));
		}else{ 
			setBackground(list.getBackground());   
            setForeground(list.getForeground()); 
            label2.setForeground(list.getForeground());
            label3.setForeground(list.getForeground());
            label2.setFont(list.getFont());
            label3.setFont(list.getFont());
		}
		label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		label.setEnabled(list.isEnabled());   
		
		
		label2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		label2.setEnabled(list.isEnabled());   
		
		
		label3.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		label3.setEnabled(list.isEnabled());   
		
		
		label4.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		label4.setEnabled(list.isEnabled());   
		
		return this;
	}
	
	

}
