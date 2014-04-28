package managers.information;

import client.login.Login;
import server.reflection.Call;

public class PasswordManager {
	
	/** �޸ĵ�¼����
	 * 
	 * @param username �޸�������û���
	 * @param password �޸ĺ������
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
