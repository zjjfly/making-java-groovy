package com.github.zjjfly.mjg.ch2


import org.junit.jupiter.api.*

/**
 * @author <a href="https://github.com/zjjfly"/>zjjfly</a>
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation)
class GetGameDataTest {

    GetGameData ggd = new GetGameData(month: 5, day: 7, year: 2025)

    @Test
    @Order(1)
    void testFillInStadiumMap() {
        def stadiums = ggd.stadiumMap.values()
        assert 30 == ggd.stadiumMap.size()
        stadiums.each { Stadium stadium ->
            assert stadium.latitude > 25 && stadium.latitude < 48
            assert stadium.longitude > -123 && stadium.longitude < -71
        }
    }

    @Test
    @Order(2)
    void testGetGames() {
        List<GameResult> games = ggd.getGames()
        games.each {
            Assertions.assertNotNull(it.home)
            Assertions.assertNotNull(it.away)
            Assertions.assertNotNull(it.hScore)
            Assertions.assertNotNull(it.aScore)
            Assertions.assertNotNull(it.stadium)
        }
    }
}
