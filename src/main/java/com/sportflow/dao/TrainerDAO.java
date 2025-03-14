package com.sportflow.dao;

import com.sportflow.model.Trainer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrainerDAO extends ConnectToDB {

    private static final String GET_TRAINERS_BY_AUTH_USER = "SELECT \n" +
            "\tfirstName,\n" +
            "    lastName,\n" +
            "    email,\n" +
            "    trainers.id,\n" +
            "    trainers.specialty,\n" +
            "    trainers.user_id\n" +
            "FROM users\n" +
            "inner join trainers\n" +
            "on users.id = trainers.user_id\n" +
            "WHERE trainers.belongsTo = ?;";


    public List<Trainer> getAllTrainersByAuthenticatedUser ( int belongsTo ) {
        List<Trainer> trainers = new ArrayList<>();
        try (
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(GET_TRAINERS_BY_AUTH_USER);
        ){

            stmt.setInt(1, belongsTo);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                Trainer trainer = new Trainer();
                trainer.setId(rs.getInt("user_id"));
                trainer.setFirstName(rs.getString("firstName"));
                trainer.setLastName(rs.getString("lastName"));
                trainer.setEmail(rs.getString("email"));
                trainer.setSpecialty(rs.getString("specialty"));
                trainers.add(trainer);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return trainers;
    }

}
