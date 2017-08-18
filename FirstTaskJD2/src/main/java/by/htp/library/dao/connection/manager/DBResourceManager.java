package by.htp.library.dao.connection.manager;

import java.util.ResourceBundle;

public final class DBResourceManager {
	private static final  String DATABASE="db";
	private static DBResourceManager instance = null;
	private final ResourceBundle bundle = ResourceBundle.getBundle(DATABASE);
	
	private DBResourceManager() {}

	public static DBResourceManager getInstance() {
		if(instance == null){
			instance = new DBResourceManager();
		}
		return instance;
	}
	
	public String getValue(String key){
		return bundle.getString(key);
	}
	
}