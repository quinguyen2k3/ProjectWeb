package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import interfacedao.GenericDAO;
import mapper.RowMapper;

public class AbstractDAO<T> implements GenericDAO <T> {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public Connection getConnection() throws ClassNotFoundException {
		try {
			String url = "jdbc:mysql://localhost:3306/shopkinhmat";
			String username = "root";
			String password = "";
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(url, username, password);
		}catch(ClassNotFoundException | SQLException e) {
				System.out.println("Loi " + e.getMessage());
				return null;
		}
	}
	
	public <T> List<T> query(String sql, RowMapper<T> rowmapper, Object... param){
		List<T> results = new ArrayList<>();
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			setParameter(ps,param);
			rs = ps.executeQuery();
			while(rs.next()) {
				results.add(rowmapper.mapRow(rs));
			}
			return results;
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				if(conn != null)
					conn.close();
				if(ps != null)
					ps.close();
				if(rs != null)
					rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
	}
	
	public void update(String sql, Object...param) {
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			setParameter(ps,param);
			ps.executeUpdate();
			conn.commit();
		}catch( SQLException | ClassNotFoundException e){
			if(conn != null) {
				try {
					conn.rollback();
				}catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		}finally {
			try {
				if(conn != null)
					conn.close();
				if(ps != null)
					ps.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Long insert (String sql, Object...param) {
		try {
			Long id = null;
			conn = getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			setParameter(ps,param);
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			while(rs.next()) {
				id = rs.getLong(1);
			}
			conn.commit();
			return id;
		}catch( SQLException | ClassNotFoundException e){
			if(conn != null) {
				try {
					conn.rollback();
				}catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
			return null;
		}finally {
			try {
				if(conn != null)
					conn.close();
				if(ps != null)
					ps.close();
				if(rs != null)
					rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
	}
	public int count(String sql, Object... param) {
		try {
			int count = 0;
			conn= getConnection();
			ps = conn.prepareStatement(sql);
			setParameter(ps, param);
			rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
			return count;
		} catch (SQLException |ClassNotFoundException e) {
			return 0;
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				return 0;
			}
		}
	}
	public void setParameter(PreparedStatement ps, Object... param){
		try {
			for(int i = 0 ; i < param.length ; i++) {
				Object parameter = param[i];
				int index = i + 1;
				if(parameter instanceof Integer) {
					ps.setInt(index,(int) parameter);
				}else if(parameter instanceof Double) {
					ps.setDouble(index,(double) parameter);
				}else if(parameter instanceof String) {
					ps.setString(index, (String) parameter);
				}else if(parameter instanceof Long) {
					ps.setLong(index, (Long) parameter);
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
