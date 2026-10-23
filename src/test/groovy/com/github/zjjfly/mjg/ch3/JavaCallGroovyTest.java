package com.github.zjjfly.mjg.ch3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author <a href="https://github.com/zjjfly"/>zjjfly</a>
 */
public class JavaCallGroovyTest {
    @Test
    public void testGeocoder() {
        Location loc = new Location();
        loc.setState("1600 Pennsylvania Avenue");
        loc.setCity("Washington");
        loc.setState("DC");
        Geocoder geocoder = new Geocoder();
        geocoder.fillInLatLng(loc);
        assertEquals(38.895,loc.getLatitude(),0.001);
        assertEquals(-77.037,loc.getLongitude(),0.001);
    }
}
