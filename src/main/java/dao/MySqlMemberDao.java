package dao;

import annotation.Component;
import vo.Member;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component("memberDao")
public class MySqlMemberDao implements MemberDao {
    DataSource ds;

    public void setDataSource(DataSource ds) {
        this.ds = ds;
    }

    public int insert(Member member) {
        PreparedStatement stmt = null;
        Connection connection = null;

        try {
            connection = ds.getConnection();
            stmt = connection.prepareStatement("INSERT INTO MEMBERS(EMAIL, PWD, MNAME, CRE_DATE, MOD_DATE) VALUES (?, ?, ?, NOW(), NOW())");
            stmt.setString(1, member.getEmail());
            stmt.setString(2, member.getPassword());
            stmt.setString(3, member.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try{ if(stmt!= null){stmt.close();}} catch (SQLException e) {}
            try{ if(connection!= null){connection.close();}} catch (SQLException e) {}
        }
        return 0;
    }

    public int delete(int no) {
        PreparedStatement stmt = null;
        Connection connection = null;
        try {
            connection = ds.getConnection();
            stmt = connection.prepareStatement("DELETE FROM members WHERE MNO=?");
            stmt.setInt(1, no);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try{ if(connection!= null){connection.close();}} catch (SQLException e) {}
            try{ if(stmt!= null){stmt.close();}} catch (SQLException e) {}
        }
        return no;
    }

//    회원 상세 정보 조회
    public Member selectOne(int no) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Member member =null;
        Connection connection = null;
        try{
            connection = ds.getConnection();
            stmt = connection.prepareStatement("SELECT MNO, MNAME, EMAIL, CRE_DATE FROM members WHERE MNO=?");
            stmt.setInt(1, no);
            rs = stmt.executeQuery();
            rs.next();
            member = new Member().setNo(rs.getInt("MNO")).setName(rs.getString("MNAME"))
                    .setEmail(rs.getString("EMAIL")).setCreateDate(rs.getDate("CRE_DATE"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try{ if(stmt!=null) stmt.close(); } catch (SQLException e) {}
            try{ if(connection!= null){connection.close();}} catch (SQLException e) {}
            try{ if(rs!=null) rs.close(); } catch (SQLException e) {}
        }
        return member;
    }

//    회원 정보 변경
    public int update(Member member){
        Connection connection = null;
        try {
            connection = ds.getConnection();
            PreparedStatement stmt = connection.prepareStatement("UPDATE MEMBERS SET EMAIL=?, MNAME=?, MOD_DATE=NOW() WHERE MNO=?");
            stmt.setString(1, member.getEmail());
            stmt.setString(2, member.getName());
            stmt.setInt(3, member.getNo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try{ if(connection!= null){connection.close();}} catch (SQLException e) {}
        }
        return 0;
    }

//    있으면 Member 객체 리턴, 없으면 null 리턴
    public Member exist(String email, String password){
        PreparedStatement stmt = null;
        Connection connection = null;
        try {
            connection = ds.getConnection();
            stmt = connection.prepareStatement("SELECT MNO, MNAME, EMAIL, CRE_DATE FROM members WHERE EMAIL=? AND PWD=?");
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Member member = new Member().setNo(rs.getInt("MNO")).setName(rs.getString("MNAME"))
                        .setEmail(rs.getString("EMAIL")).setCreateDate(rs.getDate("CRE_DATE"));
                return member;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try{ if(connection!= null){connection.close();}} catch (SQLException e) {}
        }
        return null;
    }

    public List<Member> selectList() {
        Statement stmt = null;
        ResultSet rs = null;
        Connection connection = null;
        ArrayList<Member> members = new ArrayList<Member>();

        try{
            connection = ds.getConnection();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(
                    "SELECT MNO, MNAME, EMAIL, CRE_DATE FROM MEMBERS order by MNO asc "
            );
            while(rs.next()){
                members.add(new Member().setNo(rs.getInt("MNO")).setName(rs.getString("MNAME"))
                .setEmail(rs.getString("EMAIL"))
                .setCreateDate(rs.getDate("CRE_DATE")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if(rs!=null) rs.close();} catch (Exception e ){}
            try { if(stmt!=null) stmt.close();} catch (Exception e ){}
            try{ if(connection!= null){connection.close();}} catch (SQLException e) {}
        }
        return members;
    }
}