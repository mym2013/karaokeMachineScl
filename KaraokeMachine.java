package com.teamtreehouse;

import com.teamtreehouse.model.Song;
import com.teamtreehouse.model.SongBook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class KaraokeMachine {
  private SongBook mSongBook;
  private BufferedReader mReader;
  private Map<String, String> mMenu;

  public KaraokeMachine(SongBook songBook) {
    mSongBook = songBook;
    mReader = new BufferedReader(new InputStreamReader(System.in));
    mMenu = new HashMap<String, String>();
    mMenu.put("add", "Add a new song to the song book");
    mMenu.put("choose", "Choose a song to sing!");
    mMenu.put("quit", "Give up.  Exit the program");
  }
  
  private String promptAction() throws IOException {
    System.out.printf("There are %d songs available.  Your options are: %n",
                      mSongBook.getSongCount());
    for (Map.Entry<String, String> option : mMenu.entrySet()) {
      System.out.printf("%s - %s %n",
                        option.getKey(),
                        option.getValue());
    }
    System.out.print("What do you want to do:  ");
    String choice = mReader.readLine();
    return choice.trim().toLowerCase();
  }
  
  public void run() {
    String choice = "";
    do {
      try {
        choice = promptAction();
        switch(choice) {
          case "add":
            Song song = promptNewSong();
            mSongBook.addSong(song);
            System.out.printf("%s added!  %n%n", song);
            break;
          case "choose":
            String artist = promptArtist();
            Song artistSong = promptSongForArtist(artist);
            // TODO: Add to a song queue
            System.out.printf("You chose:  %s %n", artistSong);
            break;
          case "quit":
            System.out.println("Thanks for playing!");
            break;
          default:
            System.out.printf("Unknown choice:  '%s'. Try again.  %n%n%n",
                             choice);
        }
      } catch(IOException ioe) {
        System.out.println("Problem with input");
        ioe.printStackTrace();
      }
    } while(!choice.equals("quit"));
  }
	
  private Song promptNewSong() throws IOException {
    System.out.print("Enter the artist's name:  ");
    String artist = mReader.readLine();
    System.out.print("Enter the title:  ");
    String title = mReader.readLine();
    System.out.print("Enter the video URL:  ");
    String videoUrl = mReader.readLine();
    return new Song(artist, title, videoUrl);
  }
  
  private String promptArtist() throws IOException {
    System.out.println("Available artists:");
    List<String> artists = new ArrayList<>(mSongBook.getArtists());
    int index = promptForIndex(artists);
    return artists.get(index);
  }
  
  private Song promptSongForArtist(String artist) throws IOException {
    List<Song> songs = mSongBook.getSongsForArtist(artist);
    List<String> songTitles = new ArrayList<>();
    for (Song song : songs) {
      songTitles.add(song.getTitle());
    }
    int index = promptForIndex(songTitles);
    return songs.get(index);
  }
  // este método o función, es creadp para recibir una lista de cannciones y luego asignar un número a cada item o canción
	// this function has being created to get a list of songs and then we assign a number to each item on the list
  private int promptForIndex(List<String> options) throws IOException {
    int counter = 1;
		
// con este for se muestran todas las opciones y se le asigna el numero de posición por asi decirlo a cada ítem.
// whith this  for loop. we can show all the options (wich are songs) 	and it is gived a number of positions.	 
    for (String option : options) {
      System.out.printf("%d.)  %s %n", counter, option);
      counter++;
    }
	// en este punto se lee desde la consola la opción ingresada por el cliente
		// At this stage  the code it read from the console the input option selected by the client.
    String optionAsString = mReader.readLine();
    int choice = Integer.parseInt(optionAsString.trim());
    System.out.print("Your choice:   ");
    return choice - 1;
  }
    
    
    
    
    
    
   

}

