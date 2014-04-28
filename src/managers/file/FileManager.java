package managers.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import server.reflection.Call;
import client.login.Login;

import beans.file.FileBaseInfo;

public class FileManager {
	
	private ArrayList<FileBaseInfo> fileList;
	
	private final String username;
	
	
	/** 构造方法
	 * 
	 * @param username 用户名，一旦传入，不可修改
	 */
	public FileManager(String username)
	{
		this.username = username;
		update();
	}
	
	
	/** 更新方法，更新fileList
	 * 
	 * @return true
	 */
	@SuppressWarnings("unchecked")
	public boolean update()
	{
		Call call = new Call("server.interfaces.FileOperationInterface", "downloadSharedFileList", 
							new Class[]{}, new Object[]{});
		Login.writeCall(call);
		Login.readCall();
		
		fileList = (ArrayList<FileBaseInfo>) Login.resultCall.getResult();
		
		return true;
	}
	
	
	/** 上传文件
	 * 
	 * @return boolean
	 */
	public boolean uploadFile()
	{
		boolean result = false;
		boolean success = true;
		
		JFileChooser fileChooser = new JFileChooser();		
		fileChooser.setDialogTitle("选择文件");
		int choice = fileChooser.showOpenDialog(null);
		
		if(choice == JFileChooser.APPROVE_OPTION)
		{
			int length=0;
			byte[] sendBytes=new byte[1024];
			
			File file = fileChooser.getSelectedFile();
			
			int fileSize = (int) (file.length() / 1024);
			if(fileSize*1024 < file.length())
			{
				fileSize++;
			}
			
			FileBaseInfo fileBaseInfo = new FileBaseInfo();
			fileBaseInfo.setContributorUsername(username);
			fileBaseInfo.setFileName(file.getName());  //
			fileBaseInfo.setFileSize(fileSize);
			
			//开启文件传输socket
			Login.start2();
			
			try {
				java.io.FileInputStream fis = new FileInputStream(file);
		
				int count = 0;
				Login.resultCall=null;
				
				while((length = fis.read(sendBytes,0,sendBytes.length))>0)
				{
					count++;
					if(count == 1)
					{
						Call call = new Call("server.interfaces.FileOperationInterface", "uploadSharedFile", 1, fileBaseInfo);
						//Login.writeCall(call);
						Login.writeCall2(call);
						//Login.readCall();
						Login.readCall2();
						//if(Login.resultCall.getTag() == false)  //feedbcak fail
						if(Login.resultCall2.getTag() == false)  //feedbcak fail
						{
							success = false;
							break;
						}
					}
					
					try {
						//Login.output.write(sendBytes);
						//Login.output.flush();
						Login.output2.write(sendBytes);
						Login.output2.flush();
					} catch (IOException e) {
						System.out.println("Exception when output.write");
						e.printStackTrace();
					}
				}
	
				fis.close();
				
				if(success == true)
				{
					//等待响应
					//Login.readCall();
					Login.readCall2();
					//if((Boolean)Login.resultCall.getResult() == true)
					if((Boolean)Login.resultCall2.getResult() == true)
					{
						//update
						fileList.add(fileBaseInfo);
						
						result = true;
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				result = false;
				
			} finally {				
				//关闭文件传输socket
				Login.close2();
			}
		
		}
		return result;
	}
	
	
	/** 下载文件
	 * 
	 * @param fileBaseInfo 文件信息
	 * @return boolean
	 */
	public boolean downloadFile(FileBaseInfo fileBaseInfo)
	{	
		boolean result = false;
		
		JFileChooser fileChooser = new JFileChooser();		
		fileChooser.setDialogTitle("另存为");
		fileChooser.setSelectedFile(new File(fileBaseInfo.getFileName()));
		int choice = fileChooser.showSaveDialog(null);
		
		if(choice == JFileChooser.APPROVE_OPTION)
		{
			File file = fileChooser.getSelectedFile();
		
			byte[] fileBytes = new byte[1024];
			int length=0;
			int count = fileBaseInfo.getFileSize();
			
			//开启文件传输socket
			Login.start2();
			
			//文件call
			Call call = new Call("server.interfaces.FileOperationInterface", "downloadSharedFile", 2, fileBaseInfo);			
			//Login.writeCall(call);
			Login.writeCall2(call);
			
			System.out.println ("开始接收数据...");
	
			try {
				java.io.FileOutputStream fos = new FileOutputStream(file);
				
				//while(count>0 && (length = Login.input.read(fileBytes,0,fileBytes.length))>0)
				while(count>0 && (length = Login.input2.read(fileBytes,0,fileBytes.length))>0)
				{
					count--;
				
					fos.write(fileBytes);
					fos.flush();
				}			
				System.out.println("wait.....");
				fos.close();
				
				//等待响应
				//Login.readCall();
				
				//if(Login.resultCall.getTag() == true)
				//{
					//System.out.println ("下载成功");
					//System.out.println("FileSize: " + fileBaseInfo.getFileSize() + "KB");
				//}
				
				//fos.close();
				
				//inputstream cruppted!!!!!!!!!!!!!!!!!!!!!!!
				//Login.input=Login.socket.getInputStream();
				//Login.objInput=new ObjectInputStream(Login.input);
				
				result = true;;
				
			} catch(Exception e) {
				e.printStackTrace();
				result = false;
				
			} finally {
				//关闭文件传输socket
				Login.close2();
			}
		
		}
		return result;
	}
	
	
	/** 获取所有文件信息列表（不需排序）
	 * 
	 * @return ArrayList<FileBaseInfo>
	 */
	public ArrayList<FileBaseInfo> showList()
	{
		return fileList;
	}
	
}
