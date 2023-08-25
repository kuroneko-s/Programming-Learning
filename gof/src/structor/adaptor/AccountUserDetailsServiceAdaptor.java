package structor.adaptor;

import structor.adaptor.security.UserDetails;
import structor.adaptor.security.UserDetailsService;

public class AccountUserDetailsServiceAdaptor implements UserDetailsService {

    private AccountService accountService;

    public AccountUserDetailsServiceAdaptor(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public UserDetails loginHandler(String username) {
        Account account = accountService.getAccount(username);
        return new AccountUserDetailsAdaptor(account);
    }
}
