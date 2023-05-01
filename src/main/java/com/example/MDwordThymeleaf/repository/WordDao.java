package com.example.MDwordThymeleaf.repository;

import com.example.MDwordThymeleaf.Dto.wordDto;
import com.example.MDwordThymeleaf.connect.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class WordDao {


    public boolean insert(wordDto dto) {
        //필요한 객체를 담을 지역 변수 미리 만들기
        Connection conn = null;
        PreparedStatement pstmt = null;
        int flag = 0;
        try {
            //Connection 객체의 참조값 얻어오기
            conn = new DBConnect().getConn();
            //실행할 sql 문의 뼈대 미리 준비하기
            String sql = "insert into word (Korean, English) value(?,?);";
            //PreparedStatement 객체의 참조값 얻어오기
            pstmt = conn.prepareStatement(sql);
            //? 에 필요한값 바인딩하기
            pstmt.setString(1, dto.getKorean());
            pstmt.setString(2, dto.getEnglish());
            //sql 문 실행하기 (INSERT, UPDATE, DELETE)
            flag = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
            }
        }
        if (flag > 0) {
            //성공
            return true;
        } else {
            //실패
            return false;
        }
    }

    public List<wordDto> getList() {
        List<wordDto> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            //Connection 객체의 참조값 얻어오기
            conn = new DBConnect().getConn();
            //실행할 sql 문의 뼈대 미리 준비하기
            String sql = "select * from word;";
            //PreparedStatement 객체의 참조값 얻어오기
            stmt = conn.prepareStatement(sql);
            //? 에 필요한값 바인딩하기
            //sql 문 실행하기 (INSERT, UPDATE, DELETE)
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                wordDto word = new wordDto();
                word.setId(rs.getInt("id"));
                word.setKorean(rs.getString("korean"));
                word.setEnglish(rs.getString("english"));
                list.add(word);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public wordDto getWord(int _id) {
        wordDto dto = new wordDto();
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = new DBConnect().getConn();
            String sql = "select * from word where `id` = ?;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,_id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                dto.setId(rs.getInt("id"));
                dto.setKorean(rs.getString("korean"));
                dto.setEnglish(rs.getString("english"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

    public void modifyWord(wordDto dto) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = new DBConnect().getConn();
            String sql = "update word set korean=?,english=? where `id` = ?;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,dto.getKorean());
            pstmt.setString(2,dto.getEnglish());
            pstmt.setInt(3,dto.getId());
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteWord(int id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = new DBConnect().getConn();
            String sql = "delete from word where `id` = ?;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<wordDto> getRandomList() {
        List<wordDto> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = new DBConnect().getConn();
            String sql = "select * from word ORDER BY RAND();";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                wordDto word = new wordDto();
                word.setId(rs.getInt("id"));
                word.setKorean(rs.getString("korean"));
                word.setEnglish(rs.getString("english"));
                list.add(word);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void deleteAll() {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = new DBConnect().getConn();
            String sql = "delete from word where `id` >= 1;";
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<wordDto> randomWordByWordLimit(int wordLimit) {
        List<wordDto> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = new DBConnect().getConn();
            String sql = "select * from word ORDER BY RAND() LIMIT ?;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,wordLimit);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                wordDto word = new wordDto();
                word.setId(rs.getInt("id"));
                word.setKorean(rs.getString("korean"));
                word.setEnglish(rs.getString("english"));
                list.add(word);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    public int getListCount() {
        int cnt =0;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = new DBConnect().getConn();
            String sql = "select * from word ORDER BY RAND();";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                cnt++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cnt;

    }

    public List<wordDto> findWord(String search) {
        List<wordDto> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = new DBConnect().getConn();
            String sql = "select * from word WHERE korean LIKE ? OR english LIKE ?;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,"%"+search+"%");
            pstmt.setString(2,"%"+search+"%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                wordDto word = new wordDto();
                word.setId(rs.getInt("id"));
                word.setKorean(rs.getString("korean"));
                word.setEnglish(rs.getString("english"));
                System.out.println("검색결과 : " + word.toString());
                list.add(word);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
