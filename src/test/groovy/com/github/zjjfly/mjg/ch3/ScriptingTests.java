package com.github.zjjfly.mjg.ch3;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.util.Eval;
import org.codehaus.groovy.control.CompilationFailedException;
import org.junit.jupiter.api.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author <a href="https://github.com/zjjfly"/>zjjfly</a>
 */
public class ScriptingTests {

    @Test
    void testLatLngJSR223() {
        ScriptEngine engine = new
                ScriptEngineManager().getEngineByName("groovy");
        engine.put("street", "Blackheath Avenue");
        engine.put("city", "Greenwich");
        engine.put("state", "UK");
        try {
            engine.eval(new FileReader("src/main/groovy/com/github/zjjfly/mjg/ch3/geocode.groovy"));
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assertEquals(51.4752654,
                Double.parseDouble((String) engine.get("lat")), 0.01);
        assertEquals(0.0014342,
                Double.parseDouble((String) engine.get("lng")), 0.01);
    }

    @Test
    void testEvalNoParams() {
        String result = (String) Eval.me("'abc' - 'b'");
        assertEquals("ac", result);
    }

    @Test
    public void testEvalOneParam() {
        String result = (String) Eval.x("a", "'abc' - x");
        assertEquals("bc", result);
    }

    @Test
    public void testEvalTwoParams() {
        String result = (String) Eval.xy("a", "b", "'abc' - x - y");
        assertEquals("c", result);
    }

    @Test
    public void testEvalThreeParams() {
        String result =
                (String) Eval.xyz("a", "b", "d", "'abc' - x - y + z");
        assertEquals("cd", result);
    }

    @Test
    void testGroovyShell() {
        GroovyShell shell = new GroovyShell();
        Object result = shell.evaluate("3+4");
        assertEquals(7, result);

        Binding binding = new Binding();
        binding.setVariable("street", "Blackheath Avenue");
        binding.setVariable("city", "Greenwich");
        binding.setVariable("state", "UK");
        shell = new GroovyShell(binding);

        try {
            shell.evaluate(new File("src/main/groovy/com/github/zjjfly/mjg/ch3/geocode.groovy"));
            assertEquals(51.475,
                    Double.parseDouble(
                            (String) binding.getVariable("lat")), 0.001);
            assertEquals(0.00143,
                    Double.parseDouble(
                            (String) binding.getVariable("lng")), 0.001);
        } catch (CompilationFailedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
