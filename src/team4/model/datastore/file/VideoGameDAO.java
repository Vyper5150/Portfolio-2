package team4.model.datastore.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import team4.model.VideoGame;
import team4.model.IVideoGameDAO;

/**
 * @author Team Four
 * @version 201610
 *
 */
public class VideoGameDAO implements IVideoGameDAO {

    protected String fileName = null;
    protected final List<VideoGame> myList;

    public VideoGameDAO() {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("res/file/db.properties"));
            this.fileName = props.getProperty("DB_FILENAME");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.myList = new ArrayList<>();
        try {
            Files.createFile(Paths.get(fileName));
        } catch (FileAlreadyExistsException fae) {
            ;
        } catch (IOException ioe) {
            System.out.println("Create file error with " + ioe.getMessage());
        }
        readList();
    }

    @Override
    public void createRecord(VideoGame videogame) {
        myList.add(videogame);
        writeList();
    }

    @Override
    public VideoGame retrieveRecordById(int id) {
        for (VideoGame videogame : myList) {
            if (videogame.getId() == id) {
                return videogame;
            }
        }
        return null;
    }

    @Override
    public List<VideoGame> retrieveAllRecords() {
        return myList;
    }

    @Override
    public void updateRecord(VideoGame updatedVideoGame) {
        for (VideoGame employee : myList) {
            if (employee.getId() == updatedVideoGame.getId()) {
                employee.setName(updatedVideoGame.getName());
                employee.setDeveloper(updatedVideoGame.getDeveloper());
                employee.setConsole(updatedVideoGame.getConsole());
                employee.setRating(updatedVideoGame.getRating());
                break;
            }
        }
        writeList();
    }

    @Override
    public void deleteRecord(int id) {
        for (VideoGame videogame : myList) {
            if (videogame.getId() == id) {
                myList.remove(videogame);
                break;
            }
        }
        writeList();
    }

    @Override
    public void deleteRecord(VideoGame videogame) {
        myList.remove(videogame);
        writeList();
    }

    private void readList() {
        Path path = Paths.get(fileName);
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String developer = data[2];
                String console = data[3];
                String rating = data[4];
                VideoGame videogame = new VideoGame(id, name, developer, console, rating);
                myList.add(videogame);
            }
        } catch (IOException ioe) {
            System.out.println("Read file error with " + ioe.getMessage());
        }
    }

    private void writeList() {
        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            for (VideoGame videogame : myList) {
                writer.write(String.format("%d,%s,%s,%s,%.2f\n",
                        videogame.getId(),
                        videogame.getName(),
                        videogame.getDeveloper(),
                        videogame.getConsole(),
                        videogame.getRating()));
            }
        } catch (IOException ioe) {
            System.out.println("Write file error with " + ioe.getMessage());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (VideoGame videogame : myList) {
            sb.append(String.format("%5d : %s, %s, %s, %.2f\n",
                    videogame.getId(),
                    videogame.getName(),
                    videogame.getDeveloper(),
                    videogame.getConsole(),
                    videogame.getRating()));
        }

        return sb.toString();
    }
}
