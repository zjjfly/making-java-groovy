package com.github.zjjfly.mjg.ch6

import groovy.test.GroovyShellTestCase

/**
 * GroovyShellTestCase是一个专门用于测试Groovy脚本的基础类
 * 它是GroovyTestCase的子类，有一个内置的GroovyShell对象，
 * 并提供了withBinding方法来绑定输入输出
 *
 * @author <a href="https://github.com/zjjfly"/>zjjfly</a>
 */
class ScriptShellTests extends GroovyShellTestCase {

    String base = 'src/main/groovy/com/github/zjjfly'

    void testChuckNorris() {
        shell.evaluate(new File("$base/mjg/ch6/chuck_norris.groovy"))
    }

    void testHelloWorld() {
        var content = new StringWriter()
        withBinding([out: new PrintWriter(content)]) {
            shell.evaluate(new File("$base/mjg/ch6/hello_world.groovy"))
            assert "Hello, World!" == content.toString().trim()
        }
    }

    void testHelloName() {
        var content = new StringWriter()
        withBinding([out: new PrintWriter(content), name: 'Dolly']) {
            shell.evaluate(new File("$base/mjg/ch6/hello_name.groovy"))
            assert "Hello, Dolly!" == content.toString().trim()
        }
    }

    void testAddition() {
        var result = withBinding( [x:3,y:4] ) {
            shell.evaluate(new File('$base/mjg/ch6/calc.groovy'))
            shell.context.z
        }
        assert 7 == result
    }
}
