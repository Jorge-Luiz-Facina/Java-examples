package example.mapper;

import example.model.Game;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameRowMapper implements RowMapper<Game> {

	@Override
	public Game mapRow(ResultSet rs, int rowNum) throws SQLException {
		return Game.builder()
					.id(rs.getLong("id"))
					.name(rs.getString("name"))
					.developer(rs.getString("developer"))
					.genre(rs.getString("genre"))
					.build();
	}
}
