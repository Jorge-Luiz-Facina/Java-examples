package example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    private String name;
    private String developer;
    private String genre;

    public Game toUpperCase() {
        Game game = new Game(
                getName().toUpperCase(),
                getDeveloper().toUpperCase(),
                getGenre().toUpperCase());
        return game;
    }
}
