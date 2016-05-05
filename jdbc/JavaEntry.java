import java.sql.*;

public class JavaEntry {

	public static void main(String[] args) {
		Connection connect;
		DbConnect db = new DbConnect();
		connect = DbConnect.getConn();
		try {
			db.createDatabase(connect, "testTable");
		} catch (SQLException e) {

		}
	}
}

class DbConnect {

	//private static final String url = "jdbc:sqlserver://localhost:1433;databaseName=ShopMall";
	//private static final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String url = "jdbc:mysql://127.0.0.1/ShopMall";
	private static final String driver = "com.mysql.jdbc.Driver";

	public DbConnect () {

	}

	/**
	 */
	public static Connection getConn(){
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,"sa","123");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	public static void close(ResultSet rs,Statement stmt,Connection conn){
		try {
			if(rs != null)			
				rs.close();
			if(stmt !=null)
				stmt.close();
			if(conn != null)
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createDatabase(Connection cb, String name) throws SQLException {
        Statement statement = cb.createStatement();
        statement.executeUpdate("create database if not exists "+name);
        statement.close();
	}

	public void createTable(Connection cb, String tableName, String tableItems) throws SQLException {
        Statement statement = cb.createStatement();
        statement.executeUpdate("drop "+tableName+" if exists employees");
        statement.executeUpdate("create table "+tableName+" ("+tableItems+")");
	}

	public void insertDataExample(Connection cb, String command) throws SQLException {
        PreparedStatement ps = cb.prepareStatement("INSERT INTO employees.employees VALUES (?,?,?)");
            ps.setInt(1, 0);
            ps.setString(2, "");
            ps.setString(3, "");
            ps.executeUpdate();
	}
}
