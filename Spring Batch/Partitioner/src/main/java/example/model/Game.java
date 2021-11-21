package example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Game
{
	private Long id;
	private String name;
	private String developer;
	private String genre;

	public Game toUpperCase() {
		return Game.builder()
				.name(getName().toUpperCase())
				.developer(getDeveloper().toUpperCase())
				.genre(getGenre().toUpperCase())
				.build();
	}
}