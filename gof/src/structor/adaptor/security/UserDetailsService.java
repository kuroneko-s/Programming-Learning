package structor.adaptor.security;

public interface UserDetailsService {
    UserDetails loginHandler(String username);
}
