package Music;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlaylistTest {
    @Test
    void testEmptyPlaylist(){
        Playlist playlist = new Playlist();
        assertTrue(playlist.isEmpty());
    }

    @Test
    void testSingleElement(){
        Playlist playlist = new Playlist();
        playlist.add(new Song("Atr", "000", 180));
        assertEquals(1, playlist.size());
    }

    @Test
    void testSameElement(){
        Playlist playlist = new Playlist();
        Song newSong = new Song("Atr", "000", 180);
        playlist.add(newSong);
        assertEquals(newSong, playlist.get(0));
    }

    @Test
    void testEqualElement(){
        Playlist playlist = new Playlist();
        Song newSong1 = new Song("Atr", "000", 180);
        Song newSong2 = new Song("Atr", "000", 180);
        playlist.add(newSong1);
        playlist.add(newSong2);
        assertEquals(newSong2, newSong1);
    }

    @Test
    void testAtSecond(){
        Playlist playlist = new Playlist();
        Song newSong1 = new Song("Atr", "Niovbi", 180);
        Song newSong2 = new Song("Bad", "Sbjibu", 480);
        Song newSong3 = new Song("Wor", "Jfrhof", 300);
        playlist.add(newSong1);
        playlist.add(newSong2);
        playlist.add(newSong3);
        assertEquals(newSong2, playlist.atSecond(200));
    }

    private String doesThrowExceptionCommon(int seconds){
        Playlist playlist = new Playlist();
        Song newSong1 = new Song("Atr1", "OO1", 100);
        Song newSong2 = new Song("Atr2", "OO2", 150);
        Song newSong3 = new Song("Atr3", "OO3", 200);
        playlist.add(newSong1);
        playlist.add(newSong2);
        playlist.add(newSong3);
        IndexOutOfBoundsException indexOutOfBoundsException = assertThrows(IndexOutOfBoundsException.class, () -> playlist.atSecond(seconds));
        return indexOutOfBoundsException.getMessage();
    }

    @Test
    void doesThrowException(){
        String result = doesThrowExceptionCommon(60000);
        assertEquals("Too big.", result);
    }

    @Test
    void doesThrowExceptionNegative(){
        String result = doesThrowExceptionCommon(-3);
        assertEquals("Too small.", result);
    }
}
