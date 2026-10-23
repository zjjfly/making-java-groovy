package com.github.zjjfly.mjg.ch6

import groovy.test.GroovyLogTestCase

import java.util.logging.Level

/**
 * @author <a href="https://github.com/zjjfly"/>zjjfly</a>
 */
class CalcWithLoggerTests extends GroovyLogTestCase {

    void testAddition() {
        var result = stringLog(Level.INFO, 'calc_with_logger') {
            Binding b = new Binding()
            b.x = 3; b.y = 4
            GroovyShell shell = new GroovyShell(b)
            shell.evaluate(
                    new File('src/main/groovy/com/github/zjjfly/mjg/ch6/calc_with_logger.groovy'))
            assert 7 == shell.context.z
        }
        assert result.contains('Received (x,y) = (3,4)')
    }
}
