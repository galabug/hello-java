package com.gala.bug;


import com.gala.bug.entity.Account;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class JdbcDemo {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/mysqldemo?useSSL=false&serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf8&allowMultiQueries=true";

    // Database credentials
    static final String USER = "root";
    static final String PASS = "root123";

    @Test
    public void QueryPreparedStatementDemo() {
        Connection conn = null;
        PreparedStatement stmt = null;
        List<Account> users = new ArrayList<>();
        try {
            // STEP 2: 注册mysql的驱动
//            Class.forName(JDBC_DRIVER);

            // STEP 3: 获得一个连接
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4: 创建一个查询
            System.out.println("Creating statement...");
            String sql;
            sql = "SELECT * FROM account where name= ? ";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "lilei");//处理占位符
            System.out.println(stmt.toString());//打印sql
            ResultSet rs = stmt.executeQuery();

            // STEP 5: 从resultSet中获取数据并转化成bean
            while (rs.next()) {
                System.out.println("------------------------------");
                // Retrieve by column name
                Account account = new Account();
                account.setId(rs.getInt("id"));
                account.setName(rs.getString("name"));
                account.setBalance(rs.getInt("balance"));

                System.out.println(account.toString());

                users.add(account);
            }
            // STEP 6: 关闭连接
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("-------------------------");
        System.out.println("there are "+users.size()+" users in the list!");
    }
}
