package Music;

import java.util.ArrayList;

public class Playlist extends ArrayList<Song> {
    public Song atSecond(int seconds){
        int sumOfSeconds = 0;
        if(seconds < 0){
            throw new IndexOutOfBoundsException("Too small.");
        }
        for(Song song : this){
            if((sumOfSeconds <= seconds) && (seconds < (sumOfSeconds + song.getDuration()))){
                return song;
            }
            sumOfSeconds += song.getDuration();
        }
        throw new IndexOutOfBoundsException();

    }

}