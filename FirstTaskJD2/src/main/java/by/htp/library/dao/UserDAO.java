package by.htp.library.dao;

import by.htp.library.bean.User;
import by.htp.library.dao.exception.DAOException;

public interface UserDAO {
	User signIn(String login, int password) throws DAOException;
	void signUp(String login, int password) throws DAOException;
}
