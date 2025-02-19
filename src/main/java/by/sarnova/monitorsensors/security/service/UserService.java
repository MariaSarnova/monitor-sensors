package by.sarnova.monitorsensors.security.service;

import by.sarnova.monitorsensors.security.entity.User;
import by.sarnova.monitorsensors.security.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository repository;
    public Optional<User> findByNameAndPassword(String name, String password){
        return repository.findByNameAndPassword(name, password);
    }
    public Optional<User> findByName(String name){
        return repository.findByName(name);
    }
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByName(username).orElseThrow(()->new UsernameNotFoundException(
                String.format("User '%s' not found",username)
        ));
        return new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(user.getRole().name()))
        );
    }
}
