package fr.efrei.web.my_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Score {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/java";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "1234";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public void addResult(GameResult result) {
        String query = "INSERT INTO results (player1, score1, player2, score2) VALUES (?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, result.getPlayer1());
            statement.setInt(2, result.getScore1());
            statement.setString(3, result.getPlayer2());
            statement.setInt(4, result.getScore2());
            statement.executeUpdate();
            System.out.println("Résultat ajouté avec succès !");
        } catch (SQLException e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }

    public Map<String, Integer> getAllResults() {
        Map<String, Integer> playerScores = new HashMap<>();
        String query = "SELECT * FROM results";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String player1 = resultSet.getString("player1");
                int score1 = resultSet.getInt("score1");
                String player2 = resultSet.getString("player2");
                int score2 = resultSet.getInt("score2");

                playerScores.put(player1, playerScores.getOrDefault(player1, 0) + score1);
                playerScores.put(player2, playerScores.getOrDefault(player2, 0) + score2);
            }
        } catch (SQLException e) {
            System.err.println("Erreur : " + e.getMessage());
        }

        return playerScores;
    }

    public Map<String, Integer> getTopPlayers() {
        Map<String, Integer> topScores = new HashMap<>();
        String query = "SELECT player1 AS player, score1 AS score FROM results UNION ALL SELECT player2 AS player, score2 AS score FROM results ORDER BY score DESC";
        		
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                String player = rs.getString("player");
                int score = rs.getInt("score");
                topScores.put(player, score);
            }
        } catch (SQLException e) {
            System.err.println("Erreur : " + e.getMessage());
        }

        return topScores;
    }
}
