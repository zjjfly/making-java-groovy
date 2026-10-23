package com.github.zjjfly.mjg.ch6


import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertEquals
/**
 * @author <a href="https://github.com/zjjfly"/>zjjfly</a>
 */
class AccountServiceTest {

    AccountService service = new AccountService()

    Account a1 = new Account(1, 100D)

    Account a2 = new Account(2, 100D)

    def accounts = [1: a1, 2: a2]

    @BeforeEach
    void setUp() throws Exception {
        //使用coerced closure来快速建立一个测试桩，只需要closure的签名和想要转换成的接口中的方法一致就行
        //且不需要实现接口的所有方法
        service.dao = { id -> accounts[id] } as AccountDAO
    }

    @Test
    void testTransferFunds() {
        assertEquals 100, a1.balance, 0.01
        assertEquals 100, a2.balance, 0.01
        service.transferFunds(1, 2, 50)
        assertEquals 50, a1.balance, 0.01
        assertEquals 150, a2.balance, 0.01
    }
}
