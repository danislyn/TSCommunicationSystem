package gui.files;

import gui.data.DataSet;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Dimension;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Point;
import javax.swing.JList;

import client.login.Login;

import beans.file.FileBaseInfo;

public class FilePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton refreshButton = null;
	private JButton downloadButton = null;
	private JButton uploadButton = null;
	private JList jList = null;
	private JScrollPane jScrollPane = null;

	/**
	 * This is the default constructor
	 */
	public FilePanel() {
		super();
		this.setOpaque(false);
		initialize();
		
		displayList();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(682, 481);
		this.setLayout(null);
		this.add(getRefreshButton(), null);
		this.add(getDownloadButton(), null);
		this.add(getUploadButton(), null);
		this.add(getJScrollPane(), null);
	}
	
	private JScrollPane getJScrollPane()
	{
		if(jScrollPane == null)
		{
			jScrollPane = new JScrollPane(getJList());
			jScrollPane.setBounds(new Rectangle(10, 59, 660, 411));
		}
		return jScrollPane;
	}

	/**
	 * This method initializes refreshButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getRefreshButton() {
		if (refreshButton == null) {
			refreshButton = new JButton(){
				{
					setContentAreaFilled(false);
					setOpaque(false);
				}
				public void paintComponent(Graphics g) { 
			        g.setColor(Color.blue); 
			        ImageIcon img=null;
					img = new ImageIcon("images/buttonbg.png");
					g.drawImage(img.getImage(),0,0,null); 
					super.paintComponent(g);
			    }
			};
			refreshButton.setText("刷新");
			refreshButton.setSize(new Dimension(70, 30));
			refreshButton.setLocation(new Point(385, 16));
			refreshButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			refreshButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					
					Login.person.updateFileManager();
					displayList();
				}
			});
		}
		return refreshButton;
	}

	/**
	 * This method initializes downloadButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getDownloadButton() {
		if (downloadButton == null) {
			downloadButton = new JButton(){
				{
					setContentAreaFilled(false);
					setOpaque(false);
				}
				public void paintComponent(Graphics g) { 
			        g.setColor(Color.blue); 
			        ImageIcon img=null;
					img = new ImageIcon("images/buttonbg.png");
					g.drawImage(img.getImage(),0,0,null); 
					super.paintComponent(g);
			    }
			};
			downloadButton.setText("下载");
			downloadButton.setSize(new Dimension(70, 30));
			downloadButton.setLocation(new Point(469, 16));
			downloadButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			downloadButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					
					int index = jList.getSelectedIndex();
					
					if(index == -1)
					{
						JOptionPane.showMessageDialog(null, "请选择下载项", "提示", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					
					FileBaseInfo fileBaseInfo = DataSet.fileList.get(index);
					
					if(Login.person.downloadFile(fileBaseInfo) == true)
					{
						JOptionPane.showMessageDialog(null, "下载成功", "提示", JOptionPane.INFORMATION_MESSAGE);
					}
					/*else
					{
						JOptionPane.showMessageDialog(null, "下载失败", "提示", JOptionPane.INFORMATION_MESSAGE);
					}*/
					
				}
			});
		}
		return downloadButton;
	}

	/**
	 * This method initializes uploadButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getUploadButton() {
		if (uploadButton == null) {
			uploadButton = new JButton(){
				{
					setContentAreaFilled(false);
					setOpaque(false);
				}
				public void paintComponent(Graphics g) { 
			        g.setColor(Color.blue); 
			        ImageIcon img=null;
					img = new ImageIcon("images/buttonbg.png");
					g.drawImage(img.getImage(),0,0,null); 
					super.paintComponent(g);
			    }
			};
			uploadButton.setText("上传");
			uploadButton.setSize(new Dimension(70, 30));
			uploadButton.setLocation(new Point(554, 16));
			uploadButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			uploadButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					
					if(Login.person.uploadFile() == true)
					{
						JOptionPane.showMessageDialog(null, "上传成功", "提示", JOptionPane.INFORMATION_MESSAGE);
					}
					/*else
					{
						JOptionPane.showMessageDialog(null, "上传失败", "提示", JOptionPane.INFORMATION_MESSAGE);
					}*/
					
					//update
					displayList();
				}
			});
		}
		return uploadButton;
	}

	/**
	 * This method initializes jList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJList() {
		if (jList == null) {
			jList = new JList();
			jList.setBounds(new Rectangle(10, 59, 660, 411));
		}
		return jList;
	}
	
	
	/**
	 * 重新载入list
	 */
	public void displayList()
	{
		DefaultListModel defaultListModel = new DefaultListModel();
		
		DataSet.updateFileList();
		
		for(int i=0; i<DataSet.fileList.size(); i++)
		{
			FileBaseInfo fileBaseInfo = DataSet.fileList.get(i);
			
			String tempString = "贡献者：" + fileBaseInfo.getContributorUsername() + " (ID)         " + "文件名：" + 
								fileBaseInfo.getFileName() + " ( " + fileBaseInfo.getFileSize() + " KB )"; 
			
			defaultListModel.addElement(tempString);
		}
		
		jList.setModel(defaultListModel);
	}
	
	
	/**
	 * 重绘
	 */
	public void paintComponent(Graphics g){
    	g.setColor(Color.blue); 
        ImageIcon img=null;
		img = new ImageIcon("images/file.png");
		g.drawImage(img.getImage(),0,0,null); 
    	
    }
	

}
