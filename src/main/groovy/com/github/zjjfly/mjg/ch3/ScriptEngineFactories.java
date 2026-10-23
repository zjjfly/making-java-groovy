package com.github.zjjfly.mjg.ch3;

import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author <a href="https://github.com/zjjfly"/>zjjfly</a>
 */
public class ScriptEngineFactories {

    private static Logger log = Logger.getLogger(ScriptEngineFactories.class.getName());

    public static void main(String[] args) {
        List<ScriptEngineFactory> engineFactories = new ScriptEngineManager().getEngineFactories();
        for (ScriptEngineFactory factory : engineFactories) {
            log.info("lang name: " + factory.getLanguageName());
            log.info("lang version: " + factory.getLanguageVersion());
            log.info("engine version: " + factory.getEngineVersion());
            log.info("engine name: " + factory.getEngineName());
            log.info(factory.getNames().toString());
        }
    }
}
