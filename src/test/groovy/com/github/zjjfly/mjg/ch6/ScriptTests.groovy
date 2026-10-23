package com.github.zjjfly.mjg.ch6

import org.junit.jupiter.api.Test

/**
 * @author <a href="https://github.com/zjjfly"/>zjjfly</a>
 */
class ScriptTests {

    @Test
    void testChuckNorrisScript() {
        //对于本身有assert语句的Groovy脚本，可以直接用GroovyShell执行
        GroovyShell shell = new GroovyShell()
        shell.evaluate(new File('src/main/groovy/com/github/zjjfly/mjg/ch6/chuck_norris.groovy'))
    }

    @Test
    void testHelloWorld() {
        //对于需要输入输出的脚本，需要使用Binding
        Binding binding = new Binding()
        var content = new StringWriter()
        binding.out = new PrintWriter(content)
        GroovyShell shell = new GroovyShell(binding)
        shell.evaluate(new File('src/main/groovy/com/github/zjjfly/mjg/ch6/hello_world.groovy'))
        assert "Hello, World!" == content.toString().trim()
    }

    @Test
    void testHelloName() {
        Binding binding = new Binding()
        binding.name = 'Dolly'
        def content = new StringWriter()
        binding.out = new PrintWriter(content)
        GroovyShell shell = new GroovyShell(binding)
        shell.evaluate(new File('src/main/groovy/com/github/zjjfly/mjg/ch6/hello_name.groovy'))
        assert "Hello, Dolly!" == content.toString().trim()
    }
}
