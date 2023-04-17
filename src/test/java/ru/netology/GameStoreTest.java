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


    @Test //Проверяет наличие недобавленной игры
    public void searchNonExistentGame() {

        Game game = new Game("Симс", "Симуляторы", store);

        assertFalse(store.containsGame(game));
    }

//    @Test //регистрация и суммирование времени игрока
//    public void addPlayTime() {
//
//        store.addPlayTime("Player1", 3);
//        store.addPlayTime("Player1", 4);
//        store.addPlayTime("Player1", 5);
//
//        int expected = 12;
//        int actual =  //Вот тут нужно, чтобы суммировалось время одного игрока
//
//        Assertions.assertEquals(expected, actual);
//
//    }

    @Test //поиск игрока, который сыграл дольше всех
    public void showBestPlayer() {
        store.addPlayTime("Player1", 30);
        store.addPlayTime("Player2", 50);
        store.addPlayTime("Player3", 40);

        String expected = "Player2";
        String actual = store.getMostPlayer();

        Assertions.assertEquals(expected, actual);

    }

    @Test //если игроков нет
    public void showBestPlayerNull() {

        String expected = "null";
        String actual = store.getMostPlayer();

        Assertions.assertEquals(expected, actual);

    }


    @Test //суммирование времени 3 игроков
    public void sumPlayedTime() {

        store.addPlayTime("Player1", 30);
        store.addPlayTime("Player2", 50);
        store.addPlayTime("Player3", 30);

        int expected = 110;
        int actual = store.getSumPlayedTime();

        Assertions.assertEquals(expected, actual);

    }


}