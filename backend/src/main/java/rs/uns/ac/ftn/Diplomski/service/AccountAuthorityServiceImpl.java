package rs.uns.ac.ftn.Diplomski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.uns.ac.ftn.Diplomski.model.AccountAuthority;
import rs.uns.ac.ftn.Diplomski.repository.AccountAuthorityRepository;


@Service
public class AccountAuthorityServiceImpl implements AccountAuthorityService {

    @Autowired
    private AccountAuthorityRepository accountAuthorityRepository;


    @Override
    @Transactional(readOnly = false)
    public AccountAuthority save(AccountAuthority accountAuthority) {
        return this.accountAuthorityRepository.save(accountAuthority);
    }

    @Override
    @Transactional
    public void remove(Long id){
        this.accountAuthorityRepository.delete(id);
    }
}
