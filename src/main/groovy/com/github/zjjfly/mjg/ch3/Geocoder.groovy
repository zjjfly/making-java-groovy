package com.github.zjjfly.mjg.ch3


import groovy.xml.XmlSlurper

/**
 * @author <a href="https://github.com/zjjfly"/>zjjfly</a>
 */
class Geocoder {

    //由于Google Map的API需要key，所以改用OpenStreetMap Nominatim API
    //例子：https://nominatim.openstreetmap.org/search?q=1600+Amphitheatre+Parkway,+Mountain+View,+CA&format=xml
    //返回：
    //<searchresults timestamp="Sat, 26 Apr 2025 12:47:39 +00:00"
    //               attribution="Data © OpenStreetMap contributors, ODbL 1.0. http://osm.org/copyright"
    //               querystring="1600 Amphitheatre Parkway, Mountain View, CA"
    //               more_url="https://nominatim.openstreetmap.org/search?q=1600+Amphitheatre+Parkway%2C+Mountain+View%2C+CA&addressdetails=1&limit=20&exclude_place_ids=299815182%2C299141099&format=xml"
    //               exclude_place_ids="299815182,299141099">
    //    <SCRIPT id="allow-copy_script"/>
    //    <place place_id="299815182" osm_type="way" osm_id="23733659" ref="Google Building 41" lat="37.4224857"
    //           lon="-122.0855846" boundingbox="37.4221124,37.4228508,-122.0859868,-122.0851511" place_rank="30"
    //           address_rank="30"
    //           display_name="Google Building 41, 1600, Amphitheatre Parkway, 山景城, Santa Clara County, 加利福尼亚州/加利福尼亞州, 94043, 美利坚合众国/美利堅合眾國"
    //           class="building" type="commercial" importance="6.277943083843774e-05"/>
    //    <place place_id="299141099" osm_type="node" osm_id="2192620021" ref="Google Headquarters" lat="37.4217636"
    //           lon="-122.0846140" boundingbox="37.4217136,37.4218136,-122.0846640,-122.0845640" place_rank="30"
    //           address_rank="30"
    //           display_name="Google Headquarters, 1600, Amphitheatre Parkway, 山景城, Santa Clara County, 加利福尼亚州/加利福尼亞州, 94043, 美利坚合众国/美利堅合眾國"
    //           class="office" type="it" importance="6.277943083843774e-05"/>
    //</searchresults>

    def base = 'https://nominatim.openstreetmap.org/search?'


    void fillInLatLng(Location loc) {
        def addressFields = loc.street ?
                [loc.street, loc.city, loc.state] : [loc.city, loc.state]
        def address = addressFields.collect {
            URLEncoder.encode(it, 'UTF-8')
        }.join(',')
        def params = [format: "xml", q: address]
        def url = base + params.collect { k, v -> "$k=$v" }.join('&')
        //Groovy处理XML相当方便，有XmlSlurper和XmlParser两个类可以用于解析XML，其中XmlSlurper比较高效，所以这里使用它
        def response = new XmlSlurper().parse(url)
        def place = response.place[0]
        def attributes = place.attributes()
        loc.latitude = attributes.lat.toDouble()
        loc.longitude = attributes.lon.toDouble()
    }

}
