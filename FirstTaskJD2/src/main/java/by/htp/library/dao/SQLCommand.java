package by.htp.library.dao;

public final class SQLCommand {
	private SQLCommand() {}
	public static final String INSERT_USER = "INSERT INTO user (u_login, u_password) VALUES (?,?)";
	public static final String SELECT_USER_BY_LOGIN_PASSWORD = "SELECT u_id, u_login, u_password FROM user WHERE u_login = ? AND u_password = ?";
	public static final String INSERT_BOOK = "INSERT INTO book (b_title, b_author, b_genre, b_year, b_quantity) VALUES (?,?,?,?,?)";
	public static final String UPDATE_BOOK = "UPDATE book SET b_title = ?, b_author = ?, b_genre = ?, b_year = ?, b_quantity = ? WHERE b_id = ?";
	public static final String SELECT_BOOK = "SELECT * FROM book";
}
