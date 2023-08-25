package structor.adaptor.security;

public class LoginHandler {

    UserDetailsService detailsService;

    public LoginHandler(UserDetailsService detailsService) {
        this.detailsService = detailsService;
    }

    public String loginHandler(String username, String password) {
        UserDetails userDetails = detailsService.loginHandler(username);

        if (userDetails.getPassword().equals(password)) {
            System.out.println("login - " + userDetails.getUserName());
            return userDetails.getUserName();
        } else {
            throw new IllegalArgumentException();
        }
    }
}
