import java.util.Comparator;
import java.util.Collections;


public class equipa  {
    private String name;
    private String country;
    private String stadium;
    private int golos=0;
    private int points = 0;

    public equipa()
    {

    }

    public equipa(String name, String country,String stadium)
    {
        this.name=name;
        this.country=country;
        this.stadium=stadium;
    }
    public String toString(){//overriding the toString() method
        return name;
    }

    public String getName() {
        return this.name;
    }
    public String getCountry() {
        return this.country;
    }
    public String getStadium() {
        return this.stadium;
    }
    public int getgolos() {
        return this.golos;
    }
    public void setGolos(int value) { this.golos += value;}

    public int getPoints() {
        return this.points;
    }
    public void setPoints(int value) {
        this.points = value;
    }





}
