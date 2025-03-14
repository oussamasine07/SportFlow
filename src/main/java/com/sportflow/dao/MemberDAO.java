package com.sportflow.dao;

import com.sportflow.model.Member;
import com.sportflow.model.Trainer;

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
            "\tmembers.id,\n" +
            "\tmembers.user_id\n" +
            "\tFROM users\n" +
            "\tinner join members\n" +
            "\ton users.id = members.user_id\n" +
            "\tWHERE members.belongsTo = ?;";

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

                members.add(member);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return members;

    }
}
