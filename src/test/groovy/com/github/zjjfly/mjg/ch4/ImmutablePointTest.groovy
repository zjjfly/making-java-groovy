package com.github.zjjfly.mjg.ch4

import spock.lang.Specification

/**
 * @author <a href="https://github.com/zjjfly"/>zjjfly</a>
 */
class ImmutablePointTest extends Specification {

    ImmutablePoint p = new ImmutablePoint(x: 3, y: 4)

    def "can use map ctor for immutables"() {
        expect:
        [3, 4] == [p.x, p.y]
    }

    def "can't change x"() {
        when:
        p.x = 5
        then:
        thrown(ReadOnlyPropertyException)
    }

    def "can't change y"() {
        when:
        p.y = 5
        then:
        thrown(ReadOnlyPropertyException)
    }

}
