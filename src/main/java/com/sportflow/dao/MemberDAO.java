package com.sportflow.dao;

import com.sportflow.model.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO extends ConnectToDB {

    private static final String GET_MEMBERS_BY_AUTH_USER = "SELECT \n" +
            "\tfirstName,\n" +
            "\tlastName,\n" +
            "\temail,\n" +
            "\tusers.id,\n" +
            "\tmembers.user_id,\n" +
            "\tmembers.dateOfBirth\n" +
            "\tFROM users\n" +
            "\tinner join members\n" +
            "\ton users.id = members.user_id\n" +
            "\tWHERE members.belongsTo = ?;";

    private static final String GET_USER_BY_ID = "SELECT\n" +
            "    users.id,\n" +
            "    firstName,\n" +
            "    lastName,\n" +
            "    email,\n" +
            "    dateOfBirth\n" +
            "FROM users\n" +
            "inner join members\n" +
            "on users.id = members.user_id\n" +
            "where users.id = ?;";

    private static final String UPDATE_USER_BY_ID =  "UPDATE users\n" +
            "SET\n" +
            "    firstName = ?,\n" +
            "    lastName = ?,\n" +
            "    email = ?\n" +
            "WHERE id = ?;";

    private static final String UPDATE_MEMBER_BY_USER_ID = "UPDATE members\n" +
            "SET\n" +
            "    dateOfBirth = ?\n" +
            "WHERE user_id = ?;";

    public MemberDAO () {}

    public List<Member> getAllMembersByAuthenticatedUser ( int belongsTo ) {

        List<Member> members = new ArrayList<>();

        try (
                Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(GET_MEMBERS_BY_AUTH_USER);
        ){

            stmt.setInt(1, belongsTo);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                Member member = new Member();
                member.setId(rs.getInt("id"));
                member.setFirstName(rs.getString("firstName"));
                member.setLastName(rs.getString("lastName"));
                member.setEmail(rs.getString("email"));
                member.setDateOfBirth(rs.getString("dateOfBirth"));
                members.add(member);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return members;
    }

    public Member getMemberByUserId (int userId ) {
        Member member = new Member();
        try (
                Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(GET_USER_BY_ID);
        ){
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                member.setId(rs.getInt("id"));
                member.setFirstName(rs.getString("firstName"));
                member.setLastName(rs.getString("lastName"));
                member.setEmail(rs.getString("email"));
                member.setDateOfBirth(rs.getString("dateOfBirth"));
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return member;
    }

    public void updateMemberByUserId (Member member) {
        try (
                Connection con = getConnection();
                PreparedStatement stmtUser = con.prepareStatement(UPDATE_USER_BY_ID);
                PreparedStatement stmtMember = con.prepareStatement(UPDATE_MEMBER_BY_USER_ID);
        ){
            stmtUser.setString(1, member.getFirstName());
            stmtUser.setString(2, member.getLastName());
            stmtUser.setString(3, member.getEmail());
            stmtUser.setInt(4, member.getId());

            stmtMember.setString(1, member.getDateOfBirth());
            stmtMember.setInt(2, member.getId());

            stmtUser.executeUpdate();
            stmtMember.executeUpdate();

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
