package rs.uns.ac.ftn.Diplomski.service;

import rs.uns.ac.ftn.Diplomski.model.Account;

import java.util.List;

public interface AccountService {
    List<Account> findAll();

    Account findOne(Long id);

    Account save(Account account);

    Account findByUsername(String username);

    void remove(Account account);

    boolean checkUsername(String username);

}
