package managers.information;

import client.login.Login;
import server.reflection.Call;

public class PasswordManager {
	
	/** 修改登录密码
	 * 
	 * @param username 修改密码的用户名
	 * @param password 修改后的密码
	 * @return boolean
	 */
    public boolean revisePassword(String username,String password){
    	Call call = new Call("server.interfaces.InformationOperationInterface", "revisePassword",
    						new Class[]{String.class, String.class}, new Object[]{username, password});
    	Login.writeCall(call);
    	Login.readCall();
    	
    	return (Boolean)Login.resultCall.getResult();
    }
    
}
