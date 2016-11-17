package team4.viewui;

import team4.util.Validator;
import java.util.Scanner;

import team4.model.VideoGame;
import team4.model.datastore.mysql.VideoGameDAO;
import team4.model.IVideoGameDAO;

/**
 *
 * @author Team Four
 * @version 201610
 *
 */
public class VideoGameApp {

    IVideoGameDAO videogameList = new VideoGameDAO();
    Scanner sc = new Scanner(System.in);

    public VideoGameApp() {
        menuLoop();
    }

    private void menuLoop() {
        int id;
        String name, developer, console, rating;
        String choice = "1";
        while (!choice.equals("0")) {
            System.out.println("\nVideogame App");
            System.out.println("0 = Quit");
            System.out.println("1 = List All Records");
            System.out.println("2 = Create New Record");
            System.out.println("3 = Retrieve Record");
            System.out.println("4 = Update Record");
            System.out.println("5 = Delete Record");
            choice = Validator.getLine(sc, "Number of choice: ", "^[0-5]$");

            switch (choice) {
                case "1":
                    System.out.println(videogameList.toString());
                    break;
                case "2":
                    id = Validator.getInt(sc, "New game ID: ");
                    name = Validator.getLine(sc, "Name: ");
                    developer = Validator.getLine(sc, "Developer: ");
                    console = Validator.getLine(sc, "Console: ");
                    rating = Validator.getLine(sc, "Rating: ");
                    videogameList.createRecord(new VideoGame(id, name, developer, console, rating));
                    break;
                case "3":
                    id = Validator.getInt(sc, "Game id to retrieve: ");
                    System.out.println(videogameList.retrieveRecordById(id));
                    break;
                case "4":
                    id = Validator.getInt(sc, "Videogame ID to update: ");
                    name = Validator.getLine(sc, "Name: ");
                    developer = Validator.getLine(sc, "Developer: ");
                    console = Validator.getLine(sc, "Console: ");
                    rating = Validator.getLine(sc, "Rating: ");
                    videogameList.updateRecord(new VideoGame(id, name, developer, console, rating));
                    break;
                case "5":
                    id = Validator.getInt(sc, "Game ID to delete: ");
                    System.out.println(videogameList.retrieveRecordById(id));
                    String ok = Validator.getLine(sc, "Delete this record? (y/n) ", "^[yYnN]$");
                    if (ok.equalsIgnoreCase("Y")) {
                        videogameList.deleteRecord(id);
                    }
                    break;
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new VideoGameApp();
    }
}
