package rs.uns.ac.ftn.Diplomski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.uns.ac.ftn.Diplomski.model.Authority;
import rs.uns.ac.ftn.Diplomski.repository.AuthorityRepository;

@Service
public class AuthorityServiceImpl implements AuthorityService{

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    @Transactional(readOnly = false)
    public Authority save(Authority authority) {
        return this.authorityRepository.save(authority);
    }

    @Override
    @Transactional
    public void remove(Long id){
        this.authorityRepository.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Authority findByType(String type) {
        return this.authorityRepository.findByType(type);
    }
}
