package com.github.zjjfly.mjg.ch3;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.StringReader;

/**
 * @author <a href="https://github.com/zjjfly"/>zjjfly</a>
 */
public class ExecuteGroovyFromJSR223 {

    public static void main(String[] args) {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("groovy");
        try {
            engine.eval("println 'Hello, Groovy!'");
            engine.eval(new StringReader("println 'Hello, Groovy!'"));
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }
}
