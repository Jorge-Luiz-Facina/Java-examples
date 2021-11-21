package example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Game {
    private String name;
    private String developer;
    private String genre;

    public Game toUpperCase() {
        return Game.builder().
                name(getName().toUpperCase())
                .developer(getDeveloper().toUpperCase())
                .genre(getGenre().toUpperCase())
                .build();
    }
}
