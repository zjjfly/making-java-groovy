package com.github.zjjfly.mjg.ch2

import groovy.json.JsonSlurper
import groovy.sql.Sql
import groovy.xml.MarkupBuilder
import org.postgresql.Driver

/**
 * @author <a href="https://github.com/zjjfly"/>zjjfly</a>
 */
class GetGameData {
    var year
    var month
    var day
    String base = 'https://statsapi.mlb.com/api/v1/schedule?sportId=1'
    Map<Integer, Stadium> stadiumMap = [:]

    GetGameData(){
        fillInStadiumMap()
    }

    def fillInStadiumMap() {
        Sql db = Sql.newInstance('jdbc:postgresql:postgres', 'postgres', 'postgres', Driver.class.name)
        db.eachRow("select * from stadium") { row ->
            Stadium stadium = new Stadium(
                    id: row.id,
                    name: row.name,
                    abbr: row.abbr,
                    latitude: row.latitude,
                    longitude: row.longitude
            )
            stadiumMap[stadium.id] = stadium
        }
        db.close()
    }

    def getGames() {
        var url = "$base&date=$year-${String.format("%02d", month)}-${String.format("%02d", day)}"
        var resp = url.toURL().text
        var data = new JsonSlurper().parseText(resp)
        var games = data.dates[0].games
        games.collect {
            var result = new GameResult()
            var teams = it.teams
            var home = teams.home
            var away = teams.away
            result.away = away.team.name
            result.aScore = away.score
            result.home = home.team.name
            result.hScore = home.score
            var stadium = stadiumMap[home.team.id]
            result.stadium = stadium
            result
        }
    }

    static void main(String[] args) {
        var d = new GetGameData(month: 5, day: 7, year: 2025)
        d.fillInStadiumMap()
        var results = d.getGames()
        var markupBuilder = new MarkupBuilder()
        var xml = markupBuilder.games {
            results.each { g ->
                game(
                        outcome: "$g.away $g.aScore, $g.home $g.hScore",
                        lat: g.stadium.latitude,
                        lng: g.stadium.longitude
                )
            }
        }
        println(xml)
    }

}
