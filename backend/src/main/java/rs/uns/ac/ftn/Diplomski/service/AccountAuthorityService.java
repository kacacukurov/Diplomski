package rs.uns.ac.ftn.Diplomski.service;

import rs.uns.ac.ftn.Diplomski.model.AccountAuthority;

public interface AccountAuthorityService {

    AccountAuthority save(AccountAuthority accountAuthority);

    void remove(Long id);
}
