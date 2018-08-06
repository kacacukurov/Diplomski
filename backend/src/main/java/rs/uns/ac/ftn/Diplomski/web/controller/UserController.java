package rs.uns.ac.ftn.Diplomski.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import rs.uns.ac.ftn.Diplomski.model.Account;
import rs.uns.ac.ftn.Diplomski.model.AccountAuthority;
import rs.uns.ac.ftn.Diplomski.model.Authority;
import rs.uns.ac.ftn.Diplomski.security.JWTUtils;
import rs.uns.ac.ftn.Diplomski.service.AccountAuthorityService;
import rs.uns.ac.ftn.Diplomski.service.AccountService;
import rs.uns.ac.ftn.Diplomski.service.AuthorityService;
import rs.uns.ac.ftn.Diplomski.web.dto.AccountDTO;
import rs.uns.ac.ftn.Diplomski.web.dto.LoginDTO;
import rs.uns.ac.ftn.Diplomski.web.dto.RegistrationDTO;
import rs.uns.ac.ftn.Diplomski.web.dto.TokenDTO;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private AccountAuthorityService accountAuthorityService;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;


    @RequestMapping(
            value = "/login",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity login(
            @RequestBody LoginDTO loginDTO) {
        try {

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    loginDTO.getUsername(), loginDTO.getPassword());
            authenticationManager.authenticate(token);
            Account account = this.accountService.findByUsername(loginDTO.getUsername());

            UserDetails details = userDetailsService.loadUserByUsername(account.getUsername());

            TokenDTO userToken = new TokenDTO(jwtUtils.generateToken(details));

            return new ResponseEntity<>(userToken, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(
            value = "/admin",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity regitrationAdmin(@RequestBody RegistrationDTO registrationDTO) {

        this.accountService.checkUsername(registrationDTO.getLoginAccount().getUsername());

        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        String pass = bc.encode(registrationDTO.getLoginAccount().getPassword());
        Account account = new Account(registrationDTO.getLoginAccount().getUsername(), pass,
                registrationDTO.getFirstName(), registrationDTO.getLastName());


        Authority authority = this.authorityService.findByType("ADMIN");

        AccountAuthority accountAuthority = new AccountAuthority(account, authority);
        account.getAccountAuthorities().add(accountAuthority);
        account = this.accountService.save(account);

        accountAuthority.setAccount(account);
        accountAuthority.setAuthority(authority);
        this.accountAuthorityService.save(accountAuthority);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/changeAccount",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity changeAccount(@RequestBody RegistrationDTO registrationDTO) {

        Account account = this.accountService.findByUsername(registrationDTO.getLoginAccount().getUsername());

        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        String pass = bc.encode(registrationDTO.getLoginAccount().getPassword());
        account.setFirstName(registrationDTO.getFirstName());
        account.setLastName(registrationDTO.getLastName());
        account.setPassword(pass);

        account = this.accountService.save(account);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(
            value = "/allAccounts",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getAllAccounts(@RequestParam String typeName) {

        List<Account> accounts = this.accountService.findAll();

        List<AccountDTO> accountDTOS = new ArrayList<>();

        for(Account account: accounts){
            for(AccountAuthority accountAuthority: account.getAccountAuthorities()){
                if (accountAuthority.getAuthority().getType().equals(typeName)){
                    accountDTOS.add(new AccountDTO(account.getUsername(), account.getFirstName(), account.getLastName()));
                }
            }
        }
        return new ResponseEntity<>(accountDTOS, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity deleteAccount(@RequestParam String username){

        Account account = this.accountService.findByUsername(username);
        System.out.println(username);
        if(account == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        this.accountService.remove(account);

        return new ResponseEntity(HttpStatus.OK);
    }
}
