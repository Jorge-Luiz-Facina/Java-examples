package example.processor;

import example.model.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class GameItemProcessor implements ItemProcessor<Game, Game> {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameItemProcessor.class);

    @Override
    public Game process(Game item) {

        Game transformedGame = item.toUpperCase();
        LOGGER.info("Converting ( {} ) into ( {} )", item, transformedGame);

        return transformedGame;
    }
}
