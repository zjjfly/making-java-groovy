package com.github.zjjfly.mjg.ch6

import java.util.logging.ConsoleHandler
import java.util.logging.Logger

/**
 * @author <a href="https://github.com/zjjfly"/>zjjfly</a>
 */
Logger log = Logger.getLogger("calc_with_logger")
log.addHandler(new ConsoleHandler()) // 确保有处理器
log.info("Received (x,y) = ($x,$y)")
z = x + y
