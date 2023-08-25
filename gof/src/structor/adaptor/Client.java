package structor.adaptor;

import structor.adaptor.security.LoginHandler;
import structor.adaptor.security.UserDetailsService;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class Client {
    public static void run() {
        AccountService accountService = new AccountService();

        UserDetailsService userDetailsService = new AccountUserDetailsServiceAdaptor(accountService);

        LoginHandler loginHandler = new LoginHandler(userDetailsService);
        loginHandler.loginHandler("dong", "1234");

        List<String> list = Arrays.asList("1", "2", "3");

        try (
                InputStream io = new FileInputStream("input.txt");
                InputStreamReader isr = new InputStreamReader(io);
                BufferedReader reader = new BufferedReader(isr)
        ) {
            while (reader.ready()) {
                System.out.println(reader.readLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
