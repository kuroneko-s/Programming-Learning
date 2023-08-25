package structor.adaptor;

import java.util.HashMap;
import java.util.Map;

public class AccountService {
    Map<String, Account> caching = new HashMap<>();

    public Account createAccount() {
        Account account = new Account("dong", "1234");
        caching.put(account.getUsername(), account);

        return account;
    }

    public Account getAccount(String username) {
        if (this.caching.containsKey(username)) {
            return caching.get(username);
        } else {
            Account account = this.createAccount();
            caching.put(account.getUsername(), account);

            return account;
        }
    }
}
