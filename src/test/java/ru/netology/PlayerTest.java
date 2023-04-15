
package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    GameStore store = new GameStore();
    Game game_1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
    Game game_2 = store.publishGame("Властелин Колец", "Стратегия");
    Game game_3 = store.publishGame("Тропико", "Стратегия");

    @Test
    public void shouldSumGenreIfSetOneGameOne() { //установлена одна игра, играли в одну подходящую игру

        Player player = new Player("Petya");
        player.installGame(game_1);
        player.play(game_1, 3); //ошибка здесь, при записи кличества проигранного времени в игру

        int expected = 3;
        int actual = player.sumGenre(game_1.getGenre());
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void shouldSumGenreIfSetFewGameNan() { //установлено несколько игр, не играли в подходящую игру

        Player player = new Player("Petya");
        player.installGame(game_1);
        player.installGame(game_2);
        player.play(game_1, 3);

        int expected = 0;
        int actual = player.sumGenre(game_1.getGenre());
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void shouldSumGenreIfSetFewGameFew() { //установлено несколько игр, играли в несколько игр

        Player player = new Player("Petya");
        player.installGame(game_1);
        player.installGame(game_2);
        player.installGame(game_3);
        player.play(game_2, 3);
        player.play(game_3, 5);

        int expected = 8;
        int actual = player.sumGenre(game_2.getGenre());
        Assertions.assertEquals(expected, actual);
    }
}