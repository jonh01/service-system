package com.servicesystem.api.config.triggers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.h2.api.Trigger;
import org.springframework.context.annotation.Profile;

@Profile("test")
public class UpdateReviewsAfterInsert implements Trigger {

    @Override
    public void init(Connection conn, String schemaName, String triggerName,
            String tableName, boolean before, int type) {
        // Initialization code if needed
    }

    @Override
    public void fire(Connection conn, Object[] oldRow, Object[] newRow) throws SQLException {
        // Extract values from the new row
        int newNote = (int) newRow[1]; // Assuming note is the second column in the row
        String serviceProvidedId = (String) newRow[2]; // Assuming fk_service_provided_id is the third column in the
                                                       // row

        // Update the number of reviews and sum of reviews in tb_service_provided
        try (PreparedStatement updateServiceProvidedStmt = conn.prepareStatement(
                "UPDATE tb_service_provided SET num_reviews = COALESCE(num_reviews, 0) + 1, " +
                        "sum_reviews = COALESCE(sum_reviews, 0) + ? WHERE id = ?")) {
            updateServiceProvidedStmt.setInt(1, newNote);
            updateServiceProvidedStmt.setString(2, serviceProvidedId);
            updateServiceProvidedStmt.executeUpdate();
        }

        // Check if the note already exists in tb_reviews_note
        try (PreparedStatement checkNoteExistsStmt = conn.prepareStatement(
                "SELECT 1 FROM tb_reviews_note WHERE fk_service_provided_id = ? AND note = ?")) {
            checkNoteExistsStmt.setString(1, serviceProvidedId);
            checkNoteExistsStmt.setInt(2, newNote);
            try (ResultSet rs = checkNoteExistsStmt.executeQuery()) {
                if (rs.next()) {
                    // Note exists, update the number of reviews
                    try (PreparedStatement updateNoteStmt = conn.prepareStatement(
                            "UPDATE tb_reviews_note SET num_reviews = num_reviews + 1 " +
                                    "WHERE fk_service_provided_id = ? AND note = ?")) {
                        updateNoteStmt.setString(1, serviceProvidedId);
                        updateNoteStmt.setInt(2, newNote);
                        updateNoteStmt.executeUpdate();
                    }
                } else {
                    // Note does not exist, insert a new row
                    try (PreparedStatement insertNoteStmt = conn.prepareStatement(
                            "INSERT INTO tb_reviews_note (note, num_reviews, fk_service_provided_id) " +
                                    "VALUES (?, 1, ?)")) {
                        insertNoteStmt.setInt(1, newNote);
                        insertNoteStmt.setString(2, serviceProvidedId);
                        insertNoteStmt.executeUpdate();
                    }
                }
            }
        }
    }

    @Override
    public void close() {
        // Cleanup code if needed
    }

    @Override
    public void remove() {
        // Code for when the trigger is removed
    }

}
