package com.github.zjjfly.mjg.ch6

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * @author <a href="https://github.com/zjjfly"/>zjjfly</a>
 */
class FileAccountDAOUnitTests {

    FileAccountDAO dao

    @BeforeEach
    void setUp() {
        //使用Expando模拟对象行为，快速构建具有特定属性或方法的测试对象
        //它的开销比较高，所以不适用高性能场景和大规模对象管理
        Expando ex = new Expando()
        ex.data = ''
        ex.splitEachLine = { pattern, clos ->
            data.splitEachLine(pattern, clos)
        }
        ex.withWriter = { new StringWriter() }
        ex.println = { data.append(it) }
        dao = new FileAccountDAO(accountsFile: ex)
    }

    @Test
    void testCreateAndFindNewAccount() {
        int id = dao.createNewAccount(100.0)
        Account local = new Account(id: id, balance: 100.0)
        Account fromDao = dao.findAccountById(id)
        Assertions.assertEquals local.id, fromDao.id
        Assertions.assertEquals local.balance, fromDao.balance, 0.01
    }
}
