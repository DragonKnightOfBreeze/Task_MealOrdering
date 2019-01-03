package windea.test.unsorted;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCTest1 {
	//定义MySQL数据库的驱动程序
	public static final String DBDRIVER = "org.git.mm.mysql.Driver";
	//定义MySQL数据库的连接地址
	public static final String DBURL = "jdbc:mysql://localhost:3306/Book1";
	//MySQL数据库的连接用户名
	public static final String DBUSER = "Windea";
	//MySQL数据库的连接密码
	public static final String DBPW = "BreezesLanding";

	public static void main(String[] args) {
		Connection con = null;
		//加载驱动程序
		try {
			Class.forName(DBDRIVER);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		//连接数据库，指定数据库名、用户名和密码
		try {
			con = DriverManager.getConnection(DBURL, DBUSER, DBPW);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		//打印数据
		System.out.println(con);
		//关闭数据库
		try {
			con.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}

	}
}
