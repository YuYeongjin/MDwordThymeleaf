package com.example.MDwordThymeleaf.connect;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.sql.Connection;
import java.sql.DriverManager;

//@Configuration
@PropertySource( value = "classpath:/application.properties")
public class DBConnect {

//    @Value("${spring.datasource.url}")
//    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

        //Connection 객체의 참조값을 담을 필드 선언

        Connection conn=null;

        //생성자에서 Connection 객체를 얻어오는 작업을 한다.

        public DBConnect() {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");

                conn = DriverManager.getConnection("jdbc:mysql://localhost:3308/word", "yyj","dudwls95@" );

                System.out.println("mysql DB 접속 성공!");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //Connection 객체를 리턴하는 메소드
        public Connection getConn() {
            return conn;
        }

}
