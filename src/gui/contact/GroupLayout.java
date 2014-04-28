package gui.contact;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.io.Serializable;

public class GroupLayout implements LayoutManager, Serializable {
	/**
	 * ��д�Ĳ��ֹ���
	 */
	private static final long serialVersionUID = 1L;
	int vgap = 0;      
    int hgap = 0;  
    
    
    /**
     * Ĭ�Ϲ���
     */
    public GroupLayout() {      
    }      
  
    /**
     * ָ��ˮƽ�ʹ�ֱ���Ĺ��췽��
     * @param hg ˮƽ���
     * @param vg ��ֱ���
     */
    public GroupLayout(int hg, int vg) {      
        this.hgap = hg;      
        this.vgap = vg;      
    }      

	@Override
	public void addLayoutComponent(String name, Component comp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void layoutContainer(Container parent) {
		// TODO Auto-generated method stub
		synchronized (parent.getTreeLock()) {      
            Insets insets = parent.getInsets();      
            int ncomponents = parent.getComponentCount();      
            if (ncomponents == 0) {      
                return;      
            }      
            int y = insets.top + vgap;      
            for (int c = 0; c < ncomponents; c++) {      
                int h = parent.getComponent(c).getPreferredSize().height;      
                parent.getComponent(c).setBounds(      
                    insets.left + hgap,      
                    y,      
                    parent.getWidth() - insets.left - insets.right -      
                    2 * hgap, h);      
                y += h + vgap;      
            }      
        }      

	}

	@Override
	public Dimension minimumLayoutSize(Container parent) {
		// TODO Auto-generated method stub
		return preferredLayoutSize(parent);
	}

	@Override
	public Dimension preferredLayoutSize(Container parent) {
		// TODO Auto-generated method stub
		synchronized (parent.getTreeLock()) {      
            Insets insets = parent.getInsets();      
            int ncomponents = parent.getComponentCount();      
            int w = 0;      
            int h = 0;      
            for (int i = 0; i < ncomponents; i++) {      
                Component comp = parent.getComponent(i);      
                Dimension d = comp.getPreferredSize();      
                if (w < d.width) {      
                    w = d.width;      
                }      
                h += d.height + vgap;      
            }      
            return new Dimension(insets.left + insets.right + w + 2 * hgap,      
                                 insets.top + insets.bottom + h + 2 * vgap);      
        }      
	}

	@Override
	public void removeLayoutComponent(Component comp) {
		// TODO Auto-generated method stub

	}
	
	public String toString() {      
        return getClass().getName();      
    }     

}
