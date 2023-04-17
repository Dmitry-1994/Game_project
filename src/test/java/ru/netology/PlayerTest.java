package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class PlayerTest {
    GameStore store = new GameStore();
    Player player_1 = new Player("Petya");
    Game game_1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
    Game game_2 = store.publishGame("Властелин Колец", "Стратегия");
    Game game_3 = store.publishGame("Тропико", "Стратегия");

    // Проверка количества проигранного времени в игры определенного жанра определенным игроком
    @Test
    public void shouldSumGenreIfSetOneGameOne() { //установлена одна игра, играли в одну подходящую игру

        player_1.installGame(game_1);
        player_1.play(game_1, 3); //ошибка здесь, при записи кличества проигранного времени в игру

        int expected = 3;
        int actual = player_1.sumGenre(game_1.getGenre());

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfSetFewGameNan() { //установлено несколько игр, не играли в подходящую игру

        player_1.installGame(game_1);
        player_1.installGame(game_2);
        player_1.play(game_1, 3);

        int expected = 0;
        int actual = player_1.sumGenre(game_1.getGenre());

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfSetFewGameFew() { //установлено несколько игр, играли в несколько игр

        player_1.installGame(game_1);
        player_1.installGame(game_2);
        player_1.installGame(game_3);
        player_1.play(game_2, 3);
        player_1.play(game_3, 5);

        int expected = 8;
        int actual = player_1.sumGenre(game_2.getGenre());

        Assertions.assertEquals(expected, actual);
    }

    // Проверка игры определенного жанра, в которую играли больше всего
    @Test
    public void mostPlayerByGenreNull() { //в игру заданного жанра не играли

        player_1.installGame(game_1);
        player_1.installGame(game_2);
        player_1.installGame(game_3);
        player_1.play(game_2, 3);
        player_1.play(game_3, 5);

        Game expected = null;
        Game actual = player_1.mostPlayerByGenre(game_1.getGenre());

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void mostPlayerByGenreOneGame() { //играли в одну игру заданного жанра

        player_1.installGame(game_1);
        player_1.installGame(game_2);
        player_1.installGame(game_3);
        player_1.play(game_1, 2);
        player_1.play(game_2, 3);
        player_1.play(game_3, 5);

        Game expected = game_1;
        Game actual = player_1.mostPlayerByGenre(game_1.getGenre());

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void mostPlayerByGenreFewGame() { //играли в несколько игр заданного жанра

        player_1.installGame(game_1);
        player_1.installGame(game_2);
        player_1.installGame(game_3);
        player_1.play(game_1, 7);
        player_1.play(game_2, 3);
        player_1.play(game_3, 5);

        Game expected = game_3;
        Game actual = player_1.mostPlayerByGenre(game_2.getGenre());

        Assertions.assertEquals(expected, actual);

    }

    // Проверка установки игры игроку
    // НЕОБХОДИМ метод fiendPlayedTime в классе Player который возвращал словарь с информацией обо всех установленных играх и количестве проигранного в них времени
//    @Test
//    public void installGameNull() { //у игрока нет установленных игр
//
//        Map<Game, Integer> expected = new HashMap<>();
//        Map<Game, Integer> actual = player_1.fiendPlayedTime;
//
//        Assertions.assertEquals(expected, actual);
//
//    }
//
//    @Test
//    public void installGameNull() { //у игрока одна установленная игра
//
//        player_1.installGame(game_2);
//
//        Map<Game, Integer> expected = new HashMap<>();
//        expected.put(game_2, 0);
//        Map<Game, Integer> actual = player_1.fiendPlayedTime;
//
//        Assertions.assertEquals(expected, actual);
//
//    }
//
//    @Test
//    public void installGameNull() { //у игрока несколько установленных игр
//
//        player_1.installGame(game_1);
//        player_1.installGame(game_3);
//        player_1.installGame(game_2);
//
//        Map<Game, Integer> expected = new HashMap<>();
//        expected.put(game_1, 0);
//        expected.put(game_3, 0);
//        expected.put(game_2, 0);
//        Map<Game, Integer> actual = player_1.fiendPlayedTime;
//
//        Assertions.assertEquals(expected, actual);
//
//    }

    @Test
    public void playNull() { //не играли в подходящую игру

        player_1.installGame(game_1);
        player_1.installGame(game_2);
        player_1.installGame(game_3);
        player_1.play(game_2, 3);
        player_1.play(game_3, 5);

        int expected = 0;
        int actual = player_1.play(game_1, 0);

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void playInGameFirst() { //играли в подходящую игру в первый раз

        player_1.installGame(game_1);
        player_1.installGame(game_2);
        player_1.installGame(game_3);
        player_1.play(game_2, 3);
        player_1.play(game_3, 5);

        int expected = 7;
        int actual = player_1.play(game_1, 7);

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void playInGameNoFirstTime() { //играли в подходящую игру не в первый раз

        player_1.installGame(game_1);
        player_1.installGame(game_2);
        player_1.installGame(game_3);
        player_1.play(game_1, 7);
        player_1.play(game_2, 3);
        player_1.play(game_3, 5);

        int expected = 11;
        int actual = player_1.play(game_1, 4);

        Assertions.assertEquals(expected, actual);

    }

//    @Test
//    public void playNotInstalledException() { //выкидывать исключение, если запускаемая игроком игра не была установлена
//
//        player_1.installGame(game_1);
//        player_1.installGame(game_3);
//
//        Assertions.assertThrows(NotInstalledException.class, () -> {
//            player_1.play(game_2, 2);
//        });
//
//    }

}