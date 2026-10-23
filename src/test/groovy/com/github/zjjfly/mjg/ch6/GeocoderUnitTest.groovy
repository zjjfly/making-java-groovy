package com.github.zjjfly.mjg.ch6

import com.github.zjjfly.mjg.ch2.Geocoder
import com.github.zjjfly.mjg.ch2.Stadium
import groovy.mock.interceptor.StubFor
import groovy.xml.XmlSlurper
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

/**
 * @author <a href="https://github.com/zjjfly"/>zjjfly</a>
 */
class GeocoderUnitTest {
    Geocoder geocoder = new Geocoder()

    @Test
    void testFillInLatLng() {
        Stadium wrongStadium = new Stadium(
                venue: 'Munster Mansion',
                city: 'New York')
        String xml = '''
            <searchresults>
                <place lat="37.422" lon="-122.083"/>
            </searchresults>
            '''.stripIndent().strip()
        def correctRoot = new XmlSlurper().parseText(xml)
        def stub = new StubFor(XmlSlurper)
        stub.demand.parse { correctRoot }
        stub.use {
            geocoder.fillInLatLng(wrongStadium)
        }
        Assertions.assertEquals(37.422, wrongStadium.latitude, 0.01)
        Assertions.assertEquals(-122.083, wrongStadium.longitude, 0.01)
    }
}
