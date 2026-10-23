package com.github.zjjfly.mjg.ch2

/**
 * @author <a href="https://github.com/zjjfly"/>zjjfly</a>
 */
class Stadium {
    int id
    String name
    String abbr
    String venue
    String city
    double latitude
    double longitude

    @Override
    String toString() {
        "($abbr,$name,$latitude,$longitude)"
    }
}


