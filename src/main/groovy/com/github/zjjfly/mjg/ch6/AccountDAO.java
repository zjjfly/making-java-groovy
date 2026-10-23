package com.github.zjjfly.mjg.ch6;

import java.util.Collection;

/**
 * @author <a href="https://github.com/zjjfly"/>zjjfly</a>
 */
public interface AccountDAO {
    Account findAccountById(int id);
    Collection<Account> findAllAccounts();
    int createNewAccount(double balance);
    void deleteAccount(int id);
}
