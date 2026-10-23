package com.github.zjjfly.mjg.ch2

import groovy.json.JsonSlurper
import groovy.sql.Sql
import org.postgresql.Driver

import java.time.Year

/**
 * @author <a href="https://github.com/zjjfly"/>zjjfly</a>
 */

//官方获取当赛季球队新的API：https://statsapi.mlb.com/api/v1/teams?sportId=1&season=2025&activeStatus=Y
//返回的例子：
'''{
  "teams": [
    {
      "springLeague": {
        "id": 114,
        "name": "Cactus League",
        "link": "/api/v1/league/114",
        "abbreviation": "CL"
      },
      "allStarStatus": "N",
      "id": 133,
      "name": "Athletics",
      "link": "/api/v1/teams/133",
      "season": 2025,
      "venue": {
        "id": 2529,
        "name": "Sutter Health Park",
        "link": "/api/v1/venues/2529"
      },
      "springVenue": {
        "id": 2507,
        "link": "/api/v1/venues/2507"
      },
      "teamCode": "ath",
      "fileCode": "ath",
      "abbreviation": "ATH",
      "teamName": "Athletics",
      "locationName": "Sacramento",
      "firstYearOfPlay": "1901",
      "league": {
        "id": 103,
        "name": "American League",
        "link": "/api/v1/league/103"
      },
      "division": {
        "id": 200,
        "name": "American League West",
        "link": "/api/v1/divisions/200"
      },
      "sport": {
        "id": 1,
        "link": "/api/v1/sports/1",
        "name": "Major League Baseball"
      },
      "shortName": "Athletics",
      "franchiseName": "Athletics",
      "clubName": "Athletics",
      "active": true
    }
  ]
}
'''
var year = Year.now()
var url = "https://statsapi.mlb.com/api/v1/teams?sportId=1&activeStatus=Y&season=${year}"
var resp = url.toURL().text
var json = new JsonSlurper().parseText(resp)
println "json = $json"

var teams = json.teams
List<Stadium> stadiums = teams.collect { new Stadium(id: it.id, name: it.teamName, abbr: it.abbreviation, city: it.locationName, venue: it.venue.name) }
println(stadiums)

Sql db = Sql.newInstance('jdbc:postgresql:postgres', 'postgres', 'postgres', Driver.class.name)
db.execute "drop table if exists stadium;"
db.execute '''
create table stadium(
    id int PRIMARY KEY,
    name varchar(200) not null,
    venue varchar(200) not null,
    city varchar(200) not null,
    abbr char(3) not null,
    latitude decimal,
    longitude decimal
);
'''
Geocoder geo = new Geocoder()
stadiums.each { s ->
    geo.fillInLatLng s
    db.execute """
insert into stadium(id,name,venue,city,abbr,latitude,longitude)
values(${s.id},${s.name},${s.venue},${s.city},${s.abbr},${s.latitude},${s.longitude});
"""
}
assert db.rows('select * from stadium').size() == stadiums.size()
db.eachRow('select latitude,longitude from stadium') { row ->
    assert row.latitude > 25 && row.latitude < 48
    assert row.longitude > -123 && row.longitude < -71
}
