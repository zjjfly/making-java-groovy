package com.github.zjjfly.mjg.ch2

/**
 * @author <a href="https://github.com/zjjfly"/>zjjfly</a>
 */
class GameResult {
    String home
    String away
    String hScore
    String aScore
    Stadium stadium

    String toString() { "$home $hScore, $away $aScore" }
}
