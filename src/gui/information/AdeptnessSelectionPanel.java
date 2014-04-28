package gui.information;

import gui.data.DataSet;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class AdeptnessSelectionPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel adeptnessLabel1 = null;
	private JLabel adeptnessLabel2 = null;
	private JLabel adeptnessLabel3 = null;
	private JLabel adeptnessLabel4 = null;
	private JLabel adeptnessLabel5 = null;
	private JLabel adeptnessLabel6 = null;
	private JLabel adeptnessLabel7 = null;
	private JLabel adeptnessLabel8 = null;
	private JLabel adeptnessLabel9 = null;
	private JLabel adeptnessLabel10 = null;
	private JCheckBox adeptnessCheckBox1 = null;
	private JCheckBox adeptnessCheckBox2 = null;
	private JCheckBox adeptnessCheckBox3 = null;
	private JCheckBox adeptnessCheckBox4 = null;
	private JCheckBox adeptnessCheckBox5 = null;
	private JCheckBox adeptnessCheckBox6 = null;
	private JCheckBox adeptnessCheckBox7 = null;
	private JCheckBox adeptnessCheckBox8 = null;
	private JCheckBox adeptnessCheckBox9 = null;
	private JCheckBox adeptnessCheckBox10 = null;
	
	private HashMap<Integer, JCheckBox> selectionMap;
	
	
	/**
	 * This is the default constructor
	 */
	public AdeptnessSelectionPanel() {
		super();
		
		initialize();
		
		selectionMap = new HashMap<Integer, JCheckBox>();
		selectionMap.put(1, adeptnessCheckBox1);
		selectionMap.put(2, adeptnessCheckBox2);
		selectionMap.put(3, adeptnessCheckBox3);
		selectionMap.put(4, adeptnessCheckBox4);
		selectionMap.put(5, adeptnessCheckBox5);
		selectionMap.put(6, adeptnessCheckBox6);
		selectionMap.put(7, adeptnessCheckBox7);
		selectionMap.put(8, adeptnessCheckBox8);
		selectionMap.put(9, adeptnessCheckBox9);
		selectionMap.put(10, adeptnessCheckBox10);		
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		adeptnessLabel10 = new JLabel();
		adeptnessLabel10.setText(DataSet.adeptnessDefinitionList.get(9));
		adeptnessLabel10.setSize(new Dimension(63, 20));
		adeptnessLabel10.setLocation(new Point(38, 282));
		adeptnessLabel9 = new JLabel();
		adeptnessLabel9.setText(DataSet.adeptnessDefinitionList.get(8));
		adeptnessLabel9.setSize(new Dimension(63, 20));
		adeptnessLabel9.setLocation(new Point(38, 252));
		adeptnessLabel8 = new JLabel();
		adeptnessLabel8.setText(DataSet.adeptnessDefinitionList.get(7));
		adeptnessLabel8.setSize(new Dimension(63, 20));
		adeptnessLabel8.setLocation(new Point(38, 222));
		adeptnessLabel7 = new JLabel();
		adeptnessLabel7.setText(DataSet.adeptnessDefinitionList.get(6));
		adeptnessLabel7.setSize(new Dimension(63, 20));
		adeptnessLabel7.setLocation(new Point(38, 192));
		adeptnessLabel6 = new JLabel();
		adeptnessLabel6.setText(DataSet.adeptnessDefinitionList.get(5));
		adeptnessLabel6.setSize(new Dimension(63, 20));
		adeptnessLabel6.setLocation(new Point(38, 162));
		adeptnessLabel5 = new JLabel();
		adeptnessLabel5.setText(DataSet.adeptnessDefinitionList.get(4));
		adeptnessLabel5.setSize(new Dimension(63, 20));
		adeptnessLabel5.setLocation(new Point(38, 132));
		adeptnessLabel4 = new JLabel();
		adeptnessLabel4.setText(DataSet.adeptnessDefinitionList.get(3));
		adeptnessLabel4.setSize(new Dimension(63, 20));
		adeptnessLabel4.setLocation(new Point(38, 102));
		adeptnessLabel3 = new JLabel();
		adeptnessLabel3.setText(DataSet.adeptnessDefinitionList.get(2));
		adeptnessLabel3.setSize(new Dimension(63, 20));
		adeptnessLabel3.setLocation(new Point(38, 72));
		adeptnessLabel2 = new JLabel();
		adeptnessLabel2.setText(DataSet.adeptnessDefinitionList.get(1));
		adeptnessLabel2.setSize(new Dimension(63, 20));
		adeptnessLabel2.setLocation(new Point(38, 42));
		adeptnessLabel1 = new JLabel();
		adeptnessLabel1.setText(DataSet.adeptnessDefinitionList.get(0));
		adeptnessLabel1.setSize(new Dimension(63, 20));
		adeptnessLabel1.setLocation(new Point(38, 12));
		this.setSize(116, 315);
		this.setLayout(null);
		this.add(adeptnessLabel1, null);
		this.add(adeptnessLabel2, null);
		this.add(adeptnessLabel3, null);
		this.add(adeptnessLabel4, null);
		this.add(adeptnessLabel5, null);
		this.add(adeptnessLabel6, null);
		this.add(adeptnessLabel7, null);
		this.add(adeptnessLabel8, null);
		this.add(adeptnessLabel9, null);
		this.add(adeptnessLabel10, null);
		this.add(getAdeptnessCheckBox1(), null);
		this.add(getAdeptnessCheckBox2(), null);
		this.add(getAdeptnessCheckBox3(), null);
		this.add(getAdeptnessCheckBox4(), null);
		this.add(getAdeptnessCheckBox5(), null);
		this.add(getAdeptnessCheckBox6(), null);
		this.add(getAdeptnessCheckBox7(), null);
		this.add(getAdeptnessCheckBox8(), null);
		this.add(getAdeptnessCheckBox9(), null);
		this.add(getAdeptnessCheckBox10(), null);
	}
	
	
	
	/**
	 * This method initializes adeptnessCheckBox1	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getAdeptnessCheckBox1() {
		if (adeptnessCheckBox1 == null) {
			adeptnessCheckBox1 = new JCheckBox();
			adeptnessCheckBox1.setSize(new Dimension(21, 21));
			adeptnessCheckBox1.setLocation(new Point(12, 12));
		}
		return adeptnessCheckBox1;
	}

	/**
	 * This method initializes adeptnessCheckBox2	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getAdeptnessCheckBox2() {
		if (adeptnessCheckBox2 == null) {
			adeptnessCheckBox2 = new JCheckBox();
			adeptnessCheckBox2.setSize(new Dimension(21, 21));
			adeptnessCheckBox2.setLocation(new Point(12, 42));
		}
		return adeptnessCheckBox2;
	}

	/**
	 * This method initializes adeptnessCheckBox3	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getAdeptnessCheckBox3() {
		if (adeptnessCheckBox3 == null) {
			adeptnessCheckBox3 = new JCheckBox();
			adeptnessCheckBox3.setSize(new Dimension(21, 21));
			adeptnessCheckBox3.setLocation(new Point(12, 72));
		}
		return adeptnessCheckBox3;
	}

	/**
	 * This method initializes adeptnessCheckBox4	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getAdeptnessCheckBox4() {
		if (adeptnessCheckBox4 == null) {
			adeptnessCheckBox4 = new JCheckBox();
			adeptnessCheckBox4.setSize(new Dimension(21, 21));
			adeptnessCheckBox4.setLocation(new Point(12, 102));
		}
		return adeptnessCheckBox4;
	}

	/**
	 * This method initializes adeptnessCheckBox5	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getAdeptnessCheckBox5() {
		if (adeptnessCheckBox5 == null) {
			adeptnessCheckBox5 = new JCheckBox();
			adeptnessCheckBox5.setSize(new Dimension(21, 21));
			adeptnessCheckBox5.setLocation(new Point(12, 132));
		}
		return adeptnessCheckBox5;
	}

	/**
	 * This method initializes adeptnessCheckBox6	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getAdeptnessCheckBox6() {
		if (adeptnessCheckBox6 == null) {
			adeptnessCheckBox6 = new JCheckBox();
			adeptnessCheckBox6.setSize(new Dimension(21, 21));
			adeptnessCheckBox6.setLocation(new Point(12, 162));
		}
		return adeptnessCheckBox6;
	}

	/**
	 * This method initializes adeptnessCheckBox7	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getAdeptnessCheckBox7() {
		if (adeptnessCheckBox7 == null) {
			adeptnessCheckBox7 = new JCheckBox();
			adeptnessCheckBox7.setSize(new Dimension(21, 21));
			adeptnessCheckBox7.setLocation(new Point(12, 192));
		}
		return adeptnessCheckBox7;
	}

	/**
	 * This method initializes adeptnessCheckBox8	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getAdeptnessCheckBox8() {
		if (adeptnessCheckBox8 == null) {
			adeptnessCheckBox8 = new JCheckBox();
			adeptnessCheckBox8.setBounds(new Rectangle(12, 222, 21, 21));
		}
		return adeptnessCheckBox8;
	}

	/**
	 * This method initializes adeptnessCheckBox9	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getAdeptnessCheckBox9() {
		if (adeptnessCheckBox9 == null) {
			adeptnessCheckBox9 = new JCheckBox();
			adeptnessCheckBox9.setSize(new Dimension(21, 21));
			adeptnessCheckBox9.setLocation(new Point(12, 252));
		}
		return adeptnessCheckBox9;
	}

	/**
	 * This method initializes adeptnessCheckBox10	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getAdeptnessCheckBox10() {
		if (adeptnessCheckBox10 == null) {
			adeptnessCheckBox10 = new JCheckBox();
			adeptnessCheckBox10.setSize(new Dimension(21, 21));
			adeptnessCheckBox10.setLocation(new Point(12, 282));
		}
		return adeptnessCheckBox10;
	}
	
	
	/**
	 * 重设选中项
	 * @param adeptnessNumList
	 */
	protected void setSelectAdeptnessNumList(ArrayList<Integer> adeptnessNumList)
	{
		adeptnessCheckBox1.setSelected(false);
		adeptnessCheckBox2.setSelected(false);
		adeptnessCheckBox3.setSelected(false);
		adeptnessCheckBox4.setSelected(false);
		adeptnessCheckBox5.setSelected(false);
		adeptnessCheckBox6.setSelected(false);
		adeptnessCheckBox7.setSelected(false);
		adeptnessCheckBox8.setSelected(false);
		adeptnessCheckBox9.setSelected(false);
		adeptnessCheckBox10.setSelected(false);
		
		for(int i=0; i<adeptnessNumList.size(); i++)
		{
			int num = adeptnessNumList.get(i);
			
			selectionMap.get(num).setSelected(true);
		}
		
	}
	
	
	/**
	 * 取选中项列表
	 * @return ArrayList<Integer>
	 */
	protected ArrayList<Integer> getSelectedAdeptnessNumList()
	{
		ArrayList<Integer> resultList = new ArrayList<Integer>();
		
		if(adeptnessCheckBox1.isSelected())
			resultList.add(1);
		if(adeptnessCheckBox2.isSelected())
			resultList.add(2);
		if(adeptnessCheckBox3.isSelected())
			resultList.add(3);
		if(adeptnessCheckBox4.isSelected())
			resultList.add(4);
		if(adeptnessCheckBox5.isSelected())
			resultList.add(5);
		if(adeptnessCheckBox6.isSelected())
			resultList.add(6);
		if(adeptnessCheckBox7.isSelected())
			resultList.add(7);
		if(adeptnessCheckBox8.isSelected())
			resultList.add(8);
		if(adeptnessCheckBox9.isSelected())
			resultList.add(9);
		if(adeptnessCheckBox10.isSelected())
			resultList.add(10);
		
		return resultList;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
