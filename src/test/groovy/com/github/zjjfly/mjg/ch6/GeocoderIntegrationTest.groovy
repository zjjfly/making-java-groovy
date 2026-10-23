package com.github.zjjfly.mjg.ch6

import com.github.zjjfly.mjg.ch2.Geocoder
import com.github.zjjfly.mjg.ch2.Stadium
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
/**
 * @author <a href="https://github.com/zjjfly"/>zjjfly</a>
 */
class GeocoderIntegrationTest {

    Geocoder geocoder = new Geocoder()

    @Test
    void testFillInLatLng() {
        Stadium google = new Stadium(venue: 'Googleplex',
                city: 'Mountain View')
        geocoder.fillInLatLng(google)
        Assertions.assertEquals(37.422, google.latitude, 0.01)
        Assertions.assertEquals(-122.083, google.longitude, 0.01)
    }

}
