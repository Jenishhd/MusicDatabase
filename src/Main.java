import model.Artist;
import model.DataSource;

import java.util.List;

public class Main {


    public static void main(String[] args) {
        DataSource dataSource = new DataSource();
        if(!dataSource.open()) {
            System.out.println("Can't open datasource");
            return;
        }


        List<Artist> artistList = dataSource.queryArtist();
        if(artistList == null) {
            System.out.println("no artists");
            return;
        }

        for(Artist artist : artistList) {
            System.out.println("ID = " + artist.getId() + ", Name = " + artist.getName());
        }

        dataSource.close();
    }
}
