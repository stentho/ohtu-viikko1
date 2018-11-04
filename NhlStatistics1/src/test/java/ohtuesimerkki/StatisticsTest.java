package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class StatisticsTest {
 
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }

    @Test
    public void searchToimiiOikein() {
        Player player = stats.search("Selänne");
        assertEquals(player, null);
        
        player = stats.search("Gretzky");
        assertEquals(player.getName(), "Gretzky");
    }
    
    @Test
    public void teamToimiiOikein() {
        List<Player> playersOfTeam = stats.team("PIT");
        assertEquals(playersOfTeam.get(0).getName(), "Lemieux");
    }
    
    @Test
    public void topScorersToimiiOikein() {
        assertEquals(stats.topScorers(-1).size(), 0);
        
        
        List<Player> topScorers = stats.topScorers(3);
        assertEquals(topScorers.size(), 4);
    }
}
