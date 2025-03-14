package com.sportflow.dao;

import com.sportflow.model.Trainer;
import com.sportflow.model.User;

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

    private static final String GET_USER_BY_ID = "SELECT\n" +
            "    users.id,\n" +
            "    firstName,\n" +
            "    lastName,\n" +
            "    email,\n" +
            "    specialty\n" +
            "FROM users\n" +
            "inner join trainers\n" +
            "on users.id = trainers.user_id\n" +
            "where users.id = ?;";

    private static final String UPDATE_USER_BY_ID =  "UPDATE users\n" +
            "SET\n" +
            "    firstName = ?,\n" +
            "    lastName = ?,\n" +
            "    email = ?\n" +
            "WHERE id = ?;";

    private static final String UPDATE_TRAINER_BY_USER_ID = "UPDATE trainers\n" +
            "SET\n" +
            "    specialty = ?\n" +
            "WHERE user_id = ?;";

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
                trainer.setId(rs.getInt("id"));
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

    public Trainer getTrainerByUserId (int userId ) {
        Trainer trainer = new Trainer();
        try (
                Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(GET_USER_BY_ID);
        ){
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                trainer.setId(rs.getInt("id"));
                trainer.setFirstName(rs.getString("firstName"));
                trainer.setLastName(rs.getString("lastName"));
                trainer.setEmail(rs.getString("email"));
                trainer.setSpecialty(rs.getString("specialty"));
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return trainer;
    }

    public void updateTrainerByUserId ( Trainer trainer ) {
        try (
                Connection con = getConnection();
                PreparedStatement stmtUser = con.prepareStatement(UPDATE_USER_BY_ID);
                PreparedStatement stmtTrainer = con.prepareStatement(UPDATE_TRAINER_BY_USER_ID);
        ){
            stmtUser.setString(1, trainer.getFirstName());
            stmtUser.setString(2, trainer.getLastName());
            stmtUser.setString(3, trainer.getEmail());
            stmtUser.setInt(4, trainer.getId());

            stmtTrainer.setString(1, trainer.getSpecialty());
            stmtTrainer.setInt(2, trainer.getId());

            stmtUser.executeUpdate();
            stmtTrainer.executeUpdate();

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

}
