package by.htp.library.dao.connection;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.library.dao.connection.manager.DBParameter;
import by.htp.library.dao.connection.manager.DBResourceManager;
import by.htp.library.dao.exception.ConnectionPoolException;
import by.htp.library.dao.exception.DAOException;

public final class ConnectionPool implements Closeable{	
	private static final Logger log=LogManager.getRootLogger();
	private static final String ERROR_DATABASE_DRIVER_CLASS ="Can't find database driver class";
	private static final String SQL_EXCEPTION_CONNECTIONPOOL ="SQLException in ConnectionPool";
	private static final String ERROR_CONNECTION_SOURCE ="Error connecting to the data source";
	private static final String ERROR_CONNECTION_NULL ="Connection is null";
	private static final String ERROR_RETURN_CONNECTION_POOL ="Connection isn't return to the pool";
	private static final String ERROR_CLOSE_STATEMENT ="Statement isn't closed";
	private static final String ERROR_CLOSE_PREPARESTATEMENT ="PrepareStatement ins't closed";
	private static final String ERROR_CLOSE_RESALTSET="ResultSet ins't closed";
	
	
	private static final String ERROR_FAIL_CONNECTIONPOOL="fail in ConnectionPool";

	private BlockingQueue<Connection> freeConnection;
	private BlockingQueue<Connection> busyConnection;

	private int poolsize;
	private String driver;
	private String user;
	private String password;
	private String url;

	

	private ConnectionPool() {
		DBResourceManager resourceManager = DBResourceManager.getInstance();
		this.driver = resourceManager.getValue(DBParameter.DB_DRIVER);
		this.user = resourceManager.getValue(DBParameter.DB_USER);
		this.password = resourceManager.getValue(DBParameter.DB_PASSWORD);
		this.url = resourceManager.getValue(DBParameter.DB_URL);
		
		try{
			this.poolsize = Integer.parseInt(resourceManager.getValue(DBParameter.DB_POOLSIZE));
		}catch (NumberFormatException e) {
			this.poolsize = 6;
		}	
	}
	


	public void init() throws ConnectionPoolException {
		freeConnection = new ArrayBlockingQueue<Connection>(poolsize);
		busyConnection = new ArrayBlockingQueue<Connection>(poolsize);

		try {
			Class.forName(driver);
			for (int i = 0; i < poolsize; i++) {
				freeConnection.add(DriverManager.getConnection(url, user, password));
			}
		} catch (ClassNotFoundException e) {
			log.error(ERROR_FAIL_CONNECTIONPOOL, e);
			throw new ConnectionPoolException(ERROR_DATABASE_DRIVER_CLASS, e);
		} catch (SQLException e) {
			log.error(ERROR_FAIL_CONNECTIONPOOL, e);
			throw new ConnectionPoolException(SQL_EXCEPTION_CONNECTIONPOOL, e);
		}

	}

	public Connection take() throws ConnectionPoolException {
		Connection connection = null;
		try {
			connection = freeConnection.take();
			busyConnection.put(connection);
		} catch (InterruptedException e) {
			log.error(ERROR_FAIL_CONNECTIONPOOL, e);
			throw new ConnectionPoolException( ERROR_CONNECTION_SOURCE, e);
		}
		return connection;
	}

	public void free(Connection connection) throws InterruptedException, DAOException {
		if (connection == null) {
			throw new DAOException(ERROR_CONNECTION_NULL);
		}

		Connection tempConnection = connection;
		connection = null;
		busyConnection.remove(tempConnection);
		freeConnection.put(tempConnection);
	}

	@Override
	public void close() throws IOException {
		List<Connection> listConnection = new ArrayList<Connection>();
		listConnection.addAll(this.busyConnection);
		listConnection.addAll(this.freeConnection);

		for (Connection connection : listConnection) {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.error(ERROR_FAIL_CONNECTIONPOOL, e);
			}
		}
	}

	public void closeConnection(Connection con, Statement st, PreparedStatement preSt, ResultSet rs) {
		if (con != null) {
			try {
				free(con);
			} catch (InterruptedException | DAOException e) {
				log.error( ERROR_RETURN_CONNECTION_POOL, e);
			}
		}

		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				log.error(ERROR_CLOSE_STATEMENT, e);
			}
		}

		if (preSt != null) {
			try {
				preSt.close();
			} catch (SQLException e) {
				log.error(ERROR_CLOSE_PREPARESTATEMENT , e);
			}
		}

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				log.error(ERROR_CLOSE_RESALTSET, e);
			}
		}
	}

	public void closeConnection(Connection con, Statement st) {
		if (con != null) {
			try {
				free(con);
			} catch (InterruptedException | DAOException e) {
				log.error(ERROR_RETURN_CONNECTION_POOL, e);
			}
		}

		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				log.error(ERROR_CLOSE_STATEMENT , e);
			}
		}
	}

	public void closeConnection(Connection con, PreparedStatement preSt) {
		if (con != null) {
			try {
				free(con);
			} catch (InterruptedException | DAOException e) {
				log.error(ERROR_RETURN_CONNECTION_POOL, e);
			}
		}

		if (preSt != null) {
			try {
				preSt.close();
			} catch (SQLException e) {
				log.error(ERROR_CLOSE_PREPARESTATEMENT, e);
			}
		}
	}

	public void closeConnection(Connection con, ResultSet rs) {
		if (con != null) {
			try {
				free(con);
			} catch (InterruptedException | DAOException e) {
				log.error(ERROR_RETURN_CONNECTION_POOL, e);
			}
		}

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				log.error(ERROR_CLOSE_RESALTSET, e);
			}
		}
	}

	public void closeConnection(Connection con, Statement st, PreparedStatement preSt) {
		if (con != null) {
			try {
				free(con);
			} catch (InterruptedException | DAOException e) {
				log.error(ERROR_RETURN_CONNECTION_POOL, e);
			}
		}

		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				log.error(ERROR_CLOSE_STATEMENT, e);
			}
		}

		if (preSt != null) {
			try {
				preSt.close();
			} catch (SQLException e) {
				log.error(ERROR_CLOSE_PREPARESTATEMENT, e);
			}
		}

	}

	public void closeConnection(Connection con, PreparedStatement preSt, ResultSet rs) {
		if (con != null) {
			try {
				free(con);
			} catch (InterruptedException | DAOException e) {
				log.error(ERROR_RETURN_CONNECTION_POOL, e);
			}
		}

		if (preSt != null) {
			try {
				preSt.close();
			} catch (SQLException e) {
				log.error(ERROR_CLOSE_PREPARESTATEMENT, e);
			}
		}

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				log.error(ERROR_CLOSE_RESALTSET, e);
			}
		}
	}

	public void closeConnection(Connection con, Statement st, ResultSet rs) {
		if (con != null) {
			try {
				free(con);
			} catch (InterruptedException | DAOException e) {
				log.error(ERROR_RETURN_CONNECTION_POOL, e);
			}
		}

		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				log.error(ERROR_CLOSE_STATEMENT, e);
			}
		}

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				log.error(ERROR_CLOSE_RESALTSET, e);
			}
		}
	}

}

