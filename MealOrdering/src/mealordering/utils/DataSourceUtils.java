package mealordering.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 数据源的工具类
 * <br>NOTE 其他人请在这里更改dataSource字段对应的构造方法的参数，以对应配置文件。
 * @noinspection unused, WeakerAccess
 */
public class DataSourceUtils {
	/** 从数据库连接池中得到一个连接 */
	private static DataSource dataSource = new ComboPooledDataSource("Windea");
	/** 本地线程 */
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();


	/**
	 * 得到数据源。
	 */
	public static DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * 得到连接（需要手动控制事务时）。
	 */
	public static Connection getConnection() throws SQLException {
		var con = threadLocal.get();
		if (con == null) {
			con = dataSource.getConnection();
			threadLocal.set(con);
		}
		return con;
	}

	/**
	 * 开启事务。
	 */
	public static void startTransaction() throws SQLException {
		var con = getConnection();
		if (con != null)
			con.setAutoCommit(false);
	}

	/**
	 * 结束事务。
	 */
	public static void stopTransaction() throws SQLException {
		var con = getConnection();
		if (con != null)
			con.setAutoCommit(true);
	}

	/**
	 * 事物回滚。
	 */
	public static void rollback() throws SQLException {
		var con = getConnection();
		if (con != null)
			con.rollback();
	}

	/**
	 * 从本地线程中释放并关闭连接，且结束事务。
	 */
	public static void releaseAndCloseConnection() throws SQLException {
		Connection con = getConnection();
		if (con == null)
			return;
		con.commit();
		threadLocal.remove();
		con.close();
	}
}
