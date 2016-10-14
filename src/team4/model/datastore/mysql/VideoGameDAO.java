package team4.model.datastore.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import team4.model.VideoGame;
import team4.model.IVideoGameDAO;

/**
 * @author Team Four
 * @version 201610
 *
 */
public class VideoGameDAO implements IVideoGameDAO {

    protected final static boolean DEBUG = true;

    @Override
    public void createRecord(VideoGame videogame) {
        final String QUERY = "Insert into videogame "
                + "(id, name, developer, console, rating) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection(); 
                PreparedStatement stmt = con.prepareStatement(QUERY);) {
            stmt.setInt(1, videogame.getId());
            stmt.setString(2, videogame.getName());
            stmt.setString(3, videogame.getDeveloper());
            stmt.setString(4, videogame.getConsole());
            stmt.setString(5, videogame.getRating());
            if (DEBUG) {
                System.out.println(stmt.toString());
            }
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("createRecord SQLException: " + ex.getMessage());
        }
    }

    @Override
    public VideoGame retrieveRecordById(int id) {
        final String QUERY = "select * "
                + "from videogame where id = " + id;
        VideoGame game = null;

        try (Connection con = DBConnection.getConnection(); 
                PreparedStatement stmt = con.prepareStatement(QUERY)) {
            if (DEBUG) {
                System.out.println(stmt.toString());
            }
            ResultSet rs = stmt.executeQuery(QUERY);
            if (rs.next()) {
                game = new VideoGame(
                        rs.getInt("id"), 
                        rs.getString("name"),
                        rs.getString("developer"),
                        rs.getString("console"), 
                        rs.getString("rating"));
            }
        } catch (SQLException ex) {
            System.out.println("retrieveRecordById SQLException: " 
                    + ex.getMessage());
        }

        return game;
    }

    @Override
    public List<VideoGame> retrieveAllRecords() {
         List<VideoGame> myList = new ArrayList<>();
        final String QUERY = "select * from videogame";

        try (Connection con = DBConnection.getConnection(); 
                PreparedStatement stmt = con.prepareStatement(QUERY)) {
            if (DEBUG) {
                System.out.println(stmt.toString());
            }
            ResultSet rs = stmt.executeQuery(QUERY);

            while (rs.next()) {
                myList.add(new VideoGame(
                        rs.getInt("id"), 
                        rs.getString("name"), 
                        rs.getString("developer"),
                        rs.getString("console"), 
                        rs.getString("rating")));
            }
        } catch (SQLException ex) {
            System.out.println("retrieveAllRecords SQLException: " + ex.getMessage());
        }

        return myList;
    }

    @Override
    public void updateRecord(VideoGame updatedVideoGame) {
        final String QUERY = "update videogame set name= ?, developer= ?, "
                + "console= ?, rating= ? where id= ?";

        try (Connection con = DBConnection.getConnection(); 
                PreparedStatement stmt = con.prepareStatement(QUERY)) {
            stmt.setString(1, updatedVideoGame.getName());
            stmt.setString(2, updatedVideoGame.getDeveloper());
            stmt.setString(3, updatedVideoGame.getConsole());
            stmt.setString(4, updatedVideoGame.getRating());
            stmt.setInt(5, updatedVideoGame.getId());
            if (DEBUG) {
                System.out.println(stmt.toString());
            }
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("updateRecord SQLException: " + ex.getMessage());
        }
    }

    @Override
    public void deleteRecord(int id) {
        final String QUERY = "delete from videogame where id = ?";

        try (Connection con = DBConnection.getConnection(); 
                PreparedStatement stmt = con.prepareStatement(QUERY)) {
            stmt.setInt(1, id);
            if (DEBUG) {
                System.out.println(stmt.toString());
            }
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("deleteRecord SQLException: " + ex.getMessage());
        }
    }

    @Override
    public void deleteRecord(VideoGame videogame) {
        final String QUERY = "delete from videogame where id = ?";

        try (Connection con = DBConnection.getConnection(); 
                PreparedStatement stmt = con.prepareStatement(QUERY)) {
            stmt.setInt(1, videogame.getId());
            if (DEBUG) {
                System.out.println(stmt.toString());
            }
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("deleteRecord SQLException: " + ex.getMessage());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (VideoGame videogame : retrieveAllRecords()) {
            sb.append(videogame.toString()).append("\n");
        }

        return sb.toString();
    }
}
