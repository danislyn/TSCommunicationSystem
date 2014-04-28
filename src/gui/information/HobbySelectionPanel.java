package gui.information;

import gui.data.DataSet;

import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;

public class HobbySelectionPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JLabel hobbyLabel1 = null;
	private JLabel hobbyLabel2 = null;
	private JLabel hobbyLabel3 = null;
	private JLabel hobbyLabel4 = null;
	private JLabel hobbyLabel5 = null;
	private JLabel hobbyLabel6 = null;
	private JLabel hobbyLabel7 = null;
	private JLabel hobbyLabel8 = null;
	private JLabel hobbyLabel9 = null;
	private JLabel hobbyLabel10 = null;
	private JLabel hobbyLabel11 = null;
	private JLabel hobbyLabel12 = null;
	private JCheckBox hobbyCheckBox1 = null;
	private JCheckBox hobbyCheckBox2 = null;
	private JCheckBox hobbyCheckBox3 = null;
	private JCheckBox hobbyCheckBox4 = null;
	private JCheckBox hobbyCheckBox5 = null;
	private JCheckBox hobbyCheckBox6 = null;
	private JCheckBox hobbyCheckBox7 = null;
	private JCheckBox hobbyCheckBox8 = null;
	private JCheckBox hobbyCheckBox9 = null;
	private JCheckBox hobbyCheckBox10 = null;
	private JCheckBox hobbyCheckBox11 = null;
	private JCheckBox hobbyCheckBox12 = null;
	
	private HashMap<Integer, JCheckBox> selectionMap;
	

	/**
	 * This is the default constructor
	 */
	public HobbySelectionPanel() {
		super();
			
		initialize();
			
		selectionMap = new HashMap<Integer, JCheckBox>();
		selectionMap.put(1, hobbyCheckBox1);
		selectionMap.put(2, hobbyCheckBox2);
		selectionMap.put(3, hobbyCheckBox3);
		selectionMap.put(4, hobbyCheckBox4);
		selectionMap.put(5, hobbyCheckBox5);
		selectionMap.put(6, hobbyCheckBox6);
		selectionMap.put(7, hobbyCheckBox7);
		selectionMap.put(8, hobbyCheckBox8);
		selectionMap.put(9, hobbyCheckBox9);
		selectionMap.put(10, hobbyCheckBox10);
		selectionMap.put(11, hobbyCheckBox11);
		selectionMap.put(12, hobbyCheckBox12);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {		
		hobbyLabel12 = new JLabel();
		hobbyLabel12.setText(DataSet.hobbyDefinitionList.get(11));
		hobbyLabel12.setSize(new Dimension(63, 20));
		hobbyLabel12.setLocation(new Point(38, 341));
		hobbyLabel11 = new JLabel();
		hobbyLabel11.setText(DataSet.hobbyDefinitionList.get(10));
		hobbyLabel11.setSize(new Dimension(63, 20));
		hobbyLabel11.setLocation(new Point(38, 311));
		hobbyLabel10 = new JLabel();
		hobbyLabel10.setText(DataSet.hobbyDefinitionList.get(9));
		hobbyLabel10.setSize(new Dimension(63, 20));
		hobbyLabel10.setLocation(new Point(38, 281));
		hobbyLabel9 = new JLabel();
		hobbyLabel9.setText(DataSet.hobbyDefinitionList.get(8));
		hobbyLabel9.setSize(new Dimension(63, 20));
		hobbyLabel9.setLocation(new Point(38, 251));
		hobbyLabel8 = new JLabel();
		hobbyLabel8.setText(DataSet.hobbyDefinitionList.get(7));
		hobbyLabel8.setLocation(new Point(38, 221));
		hobbyLabel8.setSize(new Dimension(63, 20));
		hobbyLabel7 = new JLabel();
		hobbyLabel7.setText(DataSet.hobbyDefinitionList.get(6));
		hobbyLabel7.setSize(new Dimension(63, 20));
		hobbyLabel7.setLocation(new Point(38, 191));
		hobbyLabel6 = new JLabel();
		hobbyLabel6.setText(DataSet.hobbyDefinitionList.get(5));
		hobbyLabel6.setSize(new Dimension(63, 20));
		hobbyLabel6.setLocation(new Point(38, 161));
		hobbyLabel5 = new JLabel();
		hobbyLabel5.setText(DataSet.hobbyDefinitionList.get(4));
		hobbyLabel5.setSize(new Dimension(63, 20));
		hobbyLabel5.setLocation(new Point(38, 131));
		hobbyLabel4 = new JLabel();
		hobbyLabel4.setText(DataSet.hobbyDefinitionList.get(3));
		hobbyLabel4.setSize(new Dimension(63, 20));
		hobbyLabel4.setLocation(new Point(38, 101));
		hobbyLabel3 = new JLabel();
		hobbyLabel3.setText(DataSet.hobbyDefinitionList.get(2));
		hobbyLabel3.setSize(new Dimension(63, 20));
		hobbyLabel3.setLocation(new Point(38, 71));
		hobbyLabel2 = new JLabel();
		hobbyLabel2.setText(DataSet.hobbyDefinitionList.get(1));
		hobbyLabel2.setSize(new Dimension(63, 20));
		hobbyLabel2.setLocation(new Point(38, 41));
		hobbyLabel1 = new JLabel();
		hobbyLabel1.setBounds(new Rectangle(38, 11, 63, 20));
		hobbyLabel1.setText(DataSet.hobbyDefinitionList.get(0));	
		this.setSize(116, 376);
		this.setLayout(null);
		this.add(hobbyLabel1, null);
		this.add(hobbyLabel2, null);
		this.add(hobbyLabel3, null);
		this.add(hobbyLabel4, null);
		this.add(hobbyLabel5, null);
		this.add(hobbyLabel6, null);
		this.add(hobbyLabel7, null);
		this.add(hobbyLabel8, null);
		this.add(hobbyLabel9, null);
		this.add(hobbyLabel10, null);
		this.add(hobbyLabel11, null);
		this.add(hobbyLabel12, null);
		this.add(getHobbyCheckBox1(), null);
		this.add(getHobbyCheckBox2(), null);
		this.add(getHobbyCheckBox3(), null);
		this.add(getHobbyCheckBox4(), null);
		this.add(getHobbyCheckBox5(), null);
		this.add(getHobbyCheckBox6(), null);
		this.add(getHobbyCheckBox7(), null);
		this.add(getHobbyCheckBox8(), null);
		this.add(getHobbyCheckBox9(), null);
		this.add(getHobbyCheckBox10(), null);
		this.add(getHobbyCheckBox11(), null);
		this.add(getHobbyCheckBox12(), null);
	}

	/**
	 * This method initializes hobbyCheckBox1	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getHobbyCheckBox1() {
		if (hobbyCheckBox1 == null) {
			hobbyCheckBox1 = new JCheckBox();
			hobbyCheckBox1.setSize(new Dimension(21, 21));
			hobbyCheckBox1.setLocation(new Point(12, 11));
		}
		return hobbyCheckBox1;
	}

	/**
	 * This method initializes hobbyCheckBox2	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getHobbyCheckBox2() {
		if (hobbyCheckBox2 == null) {
			hobbyCheckBox2 = new JCheckBox();
			hobbyCheckBox2.setSize(new Dimension(21, 21));
			hobbyCheckBox2.setLocation(new Point(12, 41));
		}
		return hobbyCheckBox2;
	}

	/**
	 * This method initializes hobbyCheckBox3	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getHobbyCheckBox3() {
		if (hobbyCheckBox3 == null) {
			hobbyCheckBox3 = new JCheckBox();
			hobbyCheckBox3.setSize(new Dimension(21, 21));
			hobbyCheckBox3.setLocation(new Point(12, 71));
		}
		return hobbyCheckBox3;
	}

	/**
	 * This method initializes hobbyCheckBox4	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getHobbyCheckBox4() {
		if (hobbyCheckBox4 == null) {
			hobbyCheckBox4 = new JCheckBox();
			hobbyCheckBox4.setBounds(new Rectangle(12, 101, 21, 21));
		}
		return hobbyCheckBox4;
	}

	/**
	 * This method initializes hobbyCheckBox5	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getHobbyCheckBox5() {
		if (hobbyCheckBox5 == null) {
			hobbyCheckBox5 = new JCheckBox();
			hobbyCheckBox5.setSize(new Dimension(21, 21));
			hobbyCheckBox5.setLocation(new Point(12, 131));
		}
		return hobbyCheckBox5;
	}

	/**
	 * This method initializes hobbyCheckBox6	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getHobbyCheckBox6() {
		if (hobbyCheckBox6 == null) {
			hobbyCheckBox6 = new JCheckBox();
			hobbyCheckBox6.setSize(new Dimension(21, 21));
			hobbyCheckBox6.setLocation(new Point(12, 161));
		}
		return hobbyCheckBox6;
	}

	/**
	 * This method initializes hobbyCheckBox7	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getHobbyCheckBox7() {
		if (hobbyCheckBox7 == null) {
			hobbyCheckBox7 = new JCheckBox();
			hobbyCheckBox7.setSize(new Dimension(21, 21));
			hobbyCheckBox7.setLocation(new Point(12, 191));
		}
		return hobbyCheckBox7;
	}

	/**
	 * This method initializes hobbyCheckBox8	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getHobbyCheckBox8() {
		if (hobbyCheckBox8 == null) {
			hobbyCheckBox8 = new JCheckBox();
			hobbyCheckBox8.setSize(new Dimension(21, 21));
			hobbyCheckBox8.setLocation(new Point(12, 221));
		}
		return hobbyCheckBox8;
	}

	/**
	 * This method initializes hobbyCheckBox9	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getHobbyCheckBox9() {
		if (hobbyCheckBox9 == null) {
			hobbyCheckBox9 = new JCheckBox();
			hobbyCheckBox9.setBounds(new Rectangle(12, 251, 21, 21));
		}
		return hobbyCheckBox9;
	}

	/**
	 * This method initializes hobbyCheckBox10	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getHobbyCheckBox10() {
		if (hobbyCheckBox10 == null) {
			hobbyCheckBox10 = new JCheckBox();
			hobbyCheckBox10.setSize(new Dimension(21, 21));
			hobbyCheckBox10.setLocation(new Point(12, 281));
		}
		return hobbyCheckBox10;
	}

	/**
	 * This method initializes hobbyCheckBox11	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getHobbyCheckBox11() {
		if (hobbyCheckBox11 == null) {
			hobbyCheckBox11 = new JCheckBox();
			hobbyCheckBox11.setSize(new Dimension(21, 21));
			hobbyCheckBox11.setLocation(new Point(12, 311));
		}
		return hobbyCheckBox11;
	}

	/**
	 * This method initializes hobbyCheckBox12	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getHobbyCheckBox12() {
		if (hobbyCheckBox12 == null) {
			hobbyCheckBox12 = new JCheckBox();
			hobbyCheckBox12.setSize(new Dimension(21, 21));
			hobbyCheckBox12.setLocation(new Point(12, 341));
		}
		return hobbyCheckBox12;
	}
	
	
	/**
	 * 重设选中项
	 * @param hobbyNumList
	 */
	protected void setSelectedHobbyNumList(ArrayList<Integer> hobbyNumList)
	{
		hobbyCheckBox1.setSelected(false);
		hobbyCheckBox2.setSelected(false);
		hobbyCheckBox3.setSelected(false);
		hobbyCheckBox4.setSelected(false);
		hobbyCheckBox5.setSelected(false);
		hobbyCheckBox6.setSelected(false);
		hobbyCheckBox7.setSelected(false);
		hobbyCheckBox8.setSelected(false);
		hobbyCheckBox9.setSelected(false);
		hobbyCheckBox10.setSelected(false);
		hobbyCheckBox11.setSelected(false);
		hobbyCheckBox12.setSelected(false);
		
		for(int i=0; i<hobbyNumList.size(); i++)
		{
			int num = hobbyNumList.get(i);
			
			selectionMap.get(num).setSelected(true);
		}
	}
		
	/**
	 * 取选中项列表
	 * @return ArrayList<Integer>
	 */
	protected ArrayList<Integer> getSelectedHobbyNumList()
	{
		ArrayList<Integer> resultList = new ArrayList<Integer>();
		
		if(hobbyCheckBox1.isSelected())
			resultList.add(1);
		if(hobbyCheckBox2.isSelected())
			resultList.add(2);
		if(hobbyCheckBox3.isSelected())
			resultList.add(3);
		if(hobbyCheckBox4.isSelected())
			resultList.add(4);
		if(hobbyCheckBox5.isSelected())
			resultList.add(5);
		if(hobbyCheckBox6.isSelected())
			resultList.add(6);
		if(hobbyCheckBox7.isSelected())
			resultList.add(7);
		if(hobbyCheckBox8.isSelected())
			resultList.add(8);
		if(hobbyCheckBox9.isSelected())
			resultList.add(9);
		if(hobbyCheckBox10.isSelected())
			resultList.add(10);
		if(hobbyCheckBox11.isSelected())
			resultList.add(11);
		if(hobbyCheckBox12.isSelected())
			resultList.add(12);
		
		return resultList;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
