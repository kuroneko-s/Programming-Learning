package structor.adaptor;

import structor.adaptor.security.UserDetails;

public class AccountUserDetailsAdaptor implements UserDetails {

    private Account account;

    public AccountUserDetailsAdaptor(Account account) {
        this.account = account;
    }

    @Override
    public String getUserName() {
        return this.account.getUsername();
    }

    @Override
    public String getPassword() {
        return this.account.getPassword();
    }
}
