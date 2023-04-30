package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameStoreTest {
    GameStore store = new GameStore();

    @Test //Добавление игры и поиск ее в каталоге
    public void shouldAddGame() {

        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        assertTrue(store.containsGame(game));
    }

    @Test //Добавление двух игр и поиск в каталоге
    public void shouldAddTwoGame() {

        Game game1 = store.publishGame("Шахматы", "Логические");
        Game game2 = store.publishGame("Тетрис", "Логические");

        assertTrue(store.containsGame(game1));
        assertTrue(store.containsGame(game2));
    }


    @Test //Проверяет наличие недобавленной игры
    public void searchNonExistentGame() {

        Game game = new Game("Симс", "Симуляторы", store);

        assertFalse(store.containsGame(game));
    }

    @Test //регистрация и суммирование времени одного игрока
    public void addPlayTime() {

        store.addPlayTime("Player1", 3);
        store.addPlayTime("Player1", 4);
        store.addPlayTime("Player1", 5);

        int expected = 12;
        int actual = store.fiendPlayedTime().get("Player1");

        Assertions.assertEquals(expected, actual);

    }

    @Test //поиск игрока, который сыграл дольше всех
    public void showBestPlayer() {
        store.addPlayTime("Player1", 30);
        store.addPlayTime("Player2", 50);
        store.addPlayTime("Player3", 40);

        String expected = "Player2";
        String actual = store.getMostPlayer();

        Assertions.assertEquals(expected, actual);

    }


    @Test //поиск игрока, который сыграл дольше всех, если игрок один
    public void showBestPlayerOnePlayer() {
        store.addPlayTime("Player1", 30);

        String expected = "Player1";
        String actual = store.getMostPlayer();

        Assertions.assertEquals(expected, actual);

    }

    @Test //если игроков нет
    public void showBestPlayerNull() {

        String expected = null;
        String actual = store.getMostPlayer();

        Assertions.assertEquals(expected, actual);

    }

    @Test //поиск игрока, если он один и сыграл 1 час
    public void showBestPlayerOneHour() {
        store.addPlayTime("Player1", 1);

        String expected = "Player1";
        String actual = store.getMostPlayer();

        Assertions.assertEquals(expected, actual);

    }

    @Test //поиск игрока, если он один и сыграл 0 часов
    public void showBestPlayerZeroHour() {
        store.addPlayTime("Player1", 0);

        String expected = "Player1";
        String actual = store.getMostPlayer();

        Assertions.assertEquals(expected, actual);

    }

    @Test //поиск игрока, если он один и сыграл 2 часа
    public void showBestPlayer6() {
        store.addPlayTime("Player1", 2);

        String expected = "Player1";
        String actual = store.getMostPlayer();

        Assertions.assertEquals(expected, actual);

    }


    @Test //суммирование общего времени 3 игроков
    public void sumPlayedTimeThreePlayers() {

        store.addPlayTime("Player1", 30);
        store.addPlayTime("Player2", 50);
        store.addPlayTime("Player3", 30);

        int expected = 110;
        int actual = store.getSumPlayedTime();

        Assertions.assertEquals(expected, actual);

    }

    @Test //суммирование общего времени 2 игроков
    public void sumPlayedTimeTwoPlayers() {

        store.addPlayTime("Player1", 30);
        store.addPlayTime("Player2", 50);

        int expected = 80;
        int actual = store.getSumPlayedTime();

        Assertions.assertEquals(expected, actual);

    }

    @Test //суммирование общего времени, если игрок один
    public void sumPlayedTimeOnePlayer() {

        store.addPlayTime("Player1", 30);

        int expected = 30;
        int actual = store.getSumPlayedTime();

        Assertions.assertEquals(expected, actual);

    }

    @Test //суммирование общего времени, если игроков нет
    public void sumPlayedTimeNoPlayers() {

        int expected = 0;
        int actual = store.getSumPlayedTime();

        Assertions.assertEquals(expected, actual);

    }


}