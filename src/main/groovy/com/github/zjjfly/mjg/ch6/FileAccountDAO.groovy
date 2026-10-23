package com.github.zjjfly.mjg.ch6

/**
 * @author <a href="https://github.com/zjjfly"/>zjjfly</a>
 */
class FileAccountDAO implements AccountDAO {

    var accountsFile

    Map<Integer, Account> accounts = [:]

    private static int nextId

    boolean dirty

    private void readAccountsFromFile() {
        accountsFile.splitEachLine(',') { line ->
            int id = line[0].toInteger()
            double balance = line[1].toDouble()
            accounts[id] = new Account(id: id, balance: balance)
        }
        nextId = accounts?.keySet()?.max() ?: 0
        nextId++
        dirty = false
    }

    private void writeAccountsToFile() {
        accountsFile.withWriter { w ->
            accounts.each { id, account ->
                w.println("$id,$account.balance")
            }
        }
        dirty = true
    }

    @Override
    Account findAccountById(int id) {
        if (dirty) readAccountsFromFile()
        return accounts[id]
    }

    @Override
    Collection<Account> findAllAccounts() {
        if (dirty) readAccountsFromFile()
        return accounts.values()
    }

    @Override
    int createNewAccount(double balance) {
        int newId = nextId++
        accounts[newId] = new Account(id: newId, balance: balance)
        writeAccountsToFile()
        return newId;
    }

    @Override
    void deleteAccount(int id) {
        accounts.remove(id)
        writeAccountsToFile()
    }
}
