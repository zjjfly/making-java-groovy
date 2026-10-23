package com.github.zjjfly.mjg.ch4

import groovy.transform.Immutable

/**
 * @author <a href="https://github.com/zjjfly"/>zjjfly</a>
 */
@Immutable
class ImmutableLine {
    ImmutablePoint start;
    ImmutablePoint end;

    def getLength() {
        double dx = end.x - start.x
        double dy = end.y - start.y
        return Math.sqrt(dx * dx + dy * dy)
    }

    String toString() { "from $start to $end" }
}
