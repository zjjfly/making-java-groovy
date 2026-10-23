package com.github.zjjfly.mjg.ch4

import groovy.transform.Immutable

/**
 * @author <a href="https://github.com/zjjfly"/>zjjfly</a>
 */
@Immutable
class ImmutablePoint {
    double x;
    double y;
    String toString() { "($x,$y)" }
}
