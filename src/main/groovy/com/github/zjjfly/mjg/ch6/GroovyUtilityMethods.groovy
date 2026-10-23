package com.github.zjjfly.mjg.ch6

/**
 * @author <a href="https://github.com/zjjfly"/>zjjfly</a>
 */
class GroovyUtilityMethods implements UtilityMethods {
    @Override
    int[] getPositives(int ... values) {
        values.findAll { it > 0 }
    }

    @Override
    boolean isPrime(int x) {
        if (x < 0) throw new IllegalArgumentException("argument must be > 0");
        if (x == 2) return true
        (2..Math.sqrt(x) + 1).every {
            x % it != 0
        }
    }

    @Override
    boolean isPalindrome(String s) {
        String str = s.toLowerCase().replaceAll(/\W/,'')
        str.reverse() == str
    }

}
