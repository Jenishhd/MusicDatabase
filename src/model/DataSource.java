package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataSource {
    public static final String DB_NAME = "music.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:/Users/jenishthanki/IdeaProjects/MusicDatabase/" + DB_NAME;

    public static final String TABLE_ALBUM = "albums";
    public static final String COLUMN_ALBUM_ID = "_id";
    public static final String COLUMN_ALBUM_NAME = "name";
    public static final String COLUMN_ALBUM_ARTIST = "artists";

    public static final String TABLE_ARTISTS = "artists";
    public static final String COLUMN_ARTIST_ID = "_id";
    public static final String COLUMN_ARTIST_NAME = "name";

    public static final String TABLE_SONGS = "songs";
    public static final String COLUMN_SONG_TRACK = "track";
    public static final String COLUMN_SONG_TITLE = "title";
    public static final String COLUMN_SONG_ALBUM = "album";

    private Connection databaseConnection;

    public boolean open() {
        try {
            databaseConnection = DriverManager.getConnection(CONNECTION_STRING);
            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't connect to database " + e.getMessage());
            e.getStackTrace();
            return false;
        }
    }


    public void close() {
        try {
            if (databaseConnection != null) {
                databaseConnection.close();
            }
        } catch (SQLException e) {
            System.out.println("Couldn't close connection " + e.getMessage());
            e.getStackTrace();
        }
    }

    public List<Artist> queryArtist() {


        try(Statement statement = databaseConnection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_ARTISTS)) {

            List<Artist> artistList = new ArrayList<>();

            while (results.next()) {
                Artist artist = new Artist();
                artist.setId(results.getInt(COLUMN_ARTIST_ID));
                artist.setName(results.getString(COLUMN_ARTIST_NAME));
                artistList.add(artist);
            }

            return artistList;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        } //finally {
//            try {
//                if (results != null) {
//                    results.close();
//                }
//            } catch (SQLException e) {
//                System.out.println("Error closing ResultSet " + e.getMessage());
//            }
//            try {
//                if (statement != null) {
//                    statement.close();
//                }
//            } catch (SQLException e) {
//                System.out.println("Error closing statement " + e.getMessage());
//            }
//        }
    }

}
