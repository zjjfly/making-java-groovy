package com.github.zjjfly.mjg.ch4

/**
 * Groovy的AST可以方便的实现代理模式，把被@Delegate注释的实例的方法放到container类中
 *
 * @author <a href="https://github.com/zjjfly"/>zjjfly</a>
 */
class SmartPhone {
    @Delegate
    Camera camera = new Camera()
    @Delegate
    Phone phone = new Phone()
}
