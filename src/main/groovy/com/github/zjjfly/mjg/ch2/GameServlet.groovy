package com.github.zjjfly.mjg.ch2

/**
 * @author <a href="https://github.com/zjjfly"/>zjjfly</a>
 */
response.contentType = 'text/xml'
var month = params.month as int
var day = params.day as int
var year = params.year as int
ggd = new GetGameData(month: month, day: day, year: year)
ggd.fillInStadiumMap()
results = ggd.games
html.games {
    results.each { g ->
        game(
                outcome: "$g.away $g.aScore, $g.home $g.hScore",
                lat: g.stadium.latitude,
                lng: g.stadium.longitude
        )
    }
}
