/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ACER EXTENSA 15
 */
public class MyDB {
    private final String url = "jdbc:mysql://localhost:3306/bdr";
    private final String user = "root";
    private final String password = ""; 
    private Connection cnx;
    static MyDB instance;
     public MyDB(){
        try{
        cnx = DriverManager.getConnection(url, user, password);
                System.out.println("connection etablie");
                } catch (SQLException ex){
                    System.err.println(ex.getMessage());
                }
    }
    public static MyDB getInstance(){
        if(instance == null)
            instance = new MyDB();
        return instance;
    }
    public Connection getConnection(){
        return cnx;
    }
    public void close(Connection connect, PreparedStatement pstmt, ResultSet rs) {
		try {
			if(connect != null)
				connect.close();
			if(pstmt != null)
				pstmt.close();
			if(rs != null)
				rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void close(Connection connect, PreparedStatement pstmt) {
		try {
			close(connect, pstmt, null);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void close(PreparedStatement pstmt) {
		try {
			close(null, pstmt, null);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
