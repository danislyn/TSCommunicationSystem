package gui.mail;

import gui.data.DataSet;

import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.SwingConstants;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JCheckBox;

public class FuzzyPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel hobbyLabel = null;
	private JLabel adeptnessLabel = null;
	private JLabel infoLabel = null;
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

	/**
	 * This is the default constructor
	 */
	public FuzzyPanel() {
		super();
		setOpaque(false);
		initialize();
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
		adeptnessLabel10.setLocation(new Point(161, 332));
		adeptnessLabel9 = new JLabel();
		adeptnessLabel9.setText(DataSet.adeptnessDefinitionList.get(8));
		adeptnessLabel9.setSize(new Dimension(63, 20));
		adeptnessLabel9.setLocation(new Point(161, 302));
		adeptnessLabel8 = new JLabel();
		adeptnessLabel8.setText(DataSet.adeptnessDefinitionList.get(7));
		adeptnessLabel8.setSize(new Dimension(63, 20));
		adeptnessLabel8.setLocation(new Point(161, 272));
		adeptnessLabel7 = new JLabel();
		adeptnessLabel7.setText(DataSet.adeptnessDefinitionList.get(6));
		adeptnessLabel7.setSize(new Dimension(63, 20));
		adeptnessLabel7.setLocation(new Point(161, 242));
		adeptnessLabel6 = new JLabel();
		adeptnessLabel6.setText(DataSet.adeptnessDefinitionList.get(5));
		adeptnessLabel6.setSize(new Dimension(63, 20));
		adeptnessLabel6.setLocation(new Point(161, 212));
		adeptnessLabel5 = new JLabel();
		adeptnessLabel5.setText(DataSet.adeptnessDefinitionList.get(4));
		adeptnessLabel5.setSize(new Dimension(63, 20));
		adeptnessLabel5.setLocation(new Point(161, 182));
		adeptnessLabel4 = new JLabel();
		adeptnessLabel4.setText(DataSet.adeptnessDefinitionList.get(3));
		adeptnessLabel4.setSize(new Dimension(63, 20));
		adeptnessLabel4.setLocation(new Point(161, 152));
		adeptnessLabel3 = new JLabel();
		adeptnessLabel3.setText(DataSet.adeptnessDefinitionList.get(2));
		adeptnessLabel3.setSize(new Dimension(63, 20));
		adeptnessLabel3.setLocation(new Point(161, 122));
		adeptnessLabel2 = new JLabel();
		adeptnessLabel2.setText(DataSet.adeptnessDefinitionList.get(1));
		adeptnessLabel2.setSize(new Dimension(63, 20));
		adeptnessLabel2.setLocation(new Point(161, 92));
		adeptnessLabel1 = new JLabel();
		adeptnessLabel1.setText(DataSet.adeptnessDefinitionList.get(0));
		adeptnessLabel1.setSize(new Dimension(63, 20));
		adeptnessLabel1.setLocation(new Point(161, 62));
		hobbyLabel12 = new JLabel();
		hobbyLabel12.setText(DataSet.hobbyDefinitionList.get(11));
		hobbyLabel12.setSize(new Dimension(63, 20));
		hobbyLabel12.setLocation(new Point(41, 392));
		hobbyLabel11 = new JLabel();
		hobbyLabel11.setText(DataSet.hobbyDefinitionList.get(10));
		hobbyLabel11.setSize(new Dimension(63, 20));
		hobbyLabel11.setLocation(new Point(41, 362));
		hobbyLabel10 = new JLabel();
		hobbyLabel10.setText(DataSet.hobbyDefinitionList.get(9));
		hobbyLabel10.setSize(new Dimension(63, 20));
		hobbyLabel10.setLocation(new Point(41, 332));
		hobbyLabel9 = new JLabel();
		hobbyLabel9.setText(DataSet.hobbyDefinitionList.get(8));
		hobbyLabel9.setSize(new Dimension(63, 20));
		hobbyLabel9.setLocation(new Point(41, 302));
		hobbyLabel8 = new JLabel();
		hobbyLabel8.setText(DataSet.hobbyDefinitionList.get(7));
		hobbyLabel8.setLocation(new Point(41, 272));
		hobbyLabel8.setSize(new Dimension(63, 20));
		hobbyLabel7 = new JLabel();
		hobbyLabel7.setText(DataSet.hobbyDefinitionList.get(6));
		hobbyLabel7.setSize(new Dimension(63, 20));
		hobbyLabel7.setLocation(new Point(41, 242));
		hobbyLabel6 = new JLabel();
		hobbyLabel6.setText(DataSet.hobbyDefinitionList.get(5));
		hobbyLabel6.setSize(new Dimension(63, 20));
		hobbyLabel6.setLocation(new Point(41, 212));
		hobbyLabel5 = new JLabel();
		hobbyLabel5.setText(DataSet.hobbyDefinitionList.get(4));
		hobbyLabel5.setSize(new Dimension(63, 20));
		hobbyLabel5.setLocation(new Point(41, 182));
		hobbyLabel4 = new JLabel();
		hobbyLabel4.setText(DataSet.hobbyDefinitionList.get(3));
		hobbyLabel4.setSize(new Dimension(63, 20));
		hobbyLabel4.setLocation(new Point(41, 152));
		hobbyLabel3 = new JLabel();
		hobbyLabel3.setText(DataSet.hobbyDefinitionList.get(2));
		hobbyLabel3.setSize(new Dimension(63, 20));
		hobbyLabel3.setLocation(new Point(41, 122));
		hobbyLabel2 = new JLabel();
		hobbyLabel2.setText(DataSet.hobbyDefinitionList.get(1));
		hobbyLabel2.setSize(new Dimension(63, 20));
		hobbyLabel2.setLocation(new Point(41, 92));
		hobbyLabel1 = new JLabel();
		hobbyLabel1.setBounds(new Rectangle(41, 62, 63, 20));
		hobbyLabel1.setText(DataSet.hobbyDefinitionList.get(0));
		infoLabel = new JLabel();
		infoLabel.setBounds(new Rectangle(27, 38, 190, 16));
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		infoLabel.setText("可多选，可两边都选");
		adeptnessLabel = new JLabel();
		adeptnessLabel.setText("按特长");
		adeptnessLabel.setLocation(new Point(142, 5));
		adeptnessLabel.setHorizontalAlignment(SwingConstants.CENTER);
		adeptnessLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		adeptnessLabel.setSize(new Dimension(75, 30));
		hobbyLabel = new JLabel();
		hobbyLabel.setBounds(new Rectangle(26, 5, 69, 30));
		hobbyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		hobbyLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		hobbyLabel.setText("按兴趣");
		this.setSize(249, 419);
		this.setLayout(null);
		this.add(hobbyLabel, null);
		this.add(adeptnessLabel, null);
		this.add(infoLabel, null);
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
	 * This method initializes hobbyCheckBox1	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getHobbyCheckBox1() {
		if (hobbyCheckBox1 == null) {
			hobbyCheckBox1 = new JCheckBox();
			hobbyCheckBox1.setSize(new Dimension(21, 21));
			hobbyCheckBox1.setLocation(new Point(15, 62));
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
			hobbyCheckBox2.setLocation(new Point(15, 92));
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
			hobbyCheckBox3.setLocation(new Point(15, 122));
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
			hobbyCheckBox4.setBounds(new Rectangle(15, 152, 21, 21));
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
			hobbyCheckBox5.setLocation(new Point(15, 182));
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
			hobbyCheckBox6.setLocation(new Point(15, 212));
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
			hobbyCheckBox7.setLocation(new Point(15, 242));
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
			hobbyCheckBox8.setLocation(new Point(15, 272));
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
			hobbyCheckBox9.setBounds(new Rectangle(15, 302, 21, 21));
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
			hobbyCheckBox10.setLocation(new Point(15, 332));
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
			hobbyCheckBox11.setLocation(new Point(15, 362));
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
			hobbyCheckBox12.setLocation(new Point(15, 392));
		}
		return hobbyCheckBox12;
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
			adeptnessCheckBox1.setLocation(new Point(135, 62));
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
			adeptnessCheckBox2.setLocation(new Point(135, 92));
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
			adeptnessCheckBox3.setLocation(new Point(135, 122));
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
			adeptnessCheckBox4.setLocation(new Point(135, 152));
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
			adeptnessCheckBox5.setLocation(new Point(135, 182));
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
			adeptnessCheckBox6.setLocation(new Point(135, 212));
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
			adeptnessCheckBox7.setLocation(new Point(135, 242));
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
			adeptnessCheckBox8.setBounds(new Rectangle(135, 272, 21, 21));
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
			adeptnessCheckBox9.setLocation(new Point(135, 302));
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
			adeptnessCheckBox10.setLocation(new Point(135, 332));
		}
		return adeptnessCheckBox10;
	}
	
	
	/**
	 * 清空选中项
	 */
	protected void reset()
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
	}
	
	/**
	 * 取出兴趣选中项列表
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
		
	/**
	 * 取出特长选中项列表
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
