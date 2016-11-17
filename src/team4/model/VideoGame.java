package team4.model;

/**
 * @author Team Four   
 * @version 201610
 *
 */
public class VideoGame {

    private int id;
    private String name;
    private String developer;
    private String console;
    private String rating;

    public VideoGame() {
        id = 0;
        name = "";
        developer = "";
        console = "";
        rating = "";
    }

    public VideoGame(int id, String name, String developer, String console, String rating) {
        this.id = id;
        this.name = name;
        this.developer = developer;
        this.console = console;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConsole() {
        return console;
    }

    public void setConsole(String console) {
        this.console = console;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return String.format("%5d, %s, %s, %s, %s", this.getId(), this.getName(),
                this.getDeveloper(), this.getConsole(), this.getRating());
    }
}
