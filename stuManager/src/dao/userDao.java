package dao;

public interface userDao {
	
	boolean login(String username,String password);
	boolean addUser(user u);
}
