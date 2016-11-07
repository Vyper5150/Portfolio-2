package team4.model;

import java.util.List;

/**
 *
 * @author Team Four
 * @version 201610
 *
 */
public interface IVideoGameDAO {

    void createRecord(VideoGame videogame);

    VideoGame retrieveRecordById(int id);

    List<VideoGame> retrieveAllRecords();

    void updateRecord(VideoGame updatedVideoGame);

    void deleteRecord(int id);

    void deleteRecord(VideoGame videogame);

    @Override
    String toString();

}
