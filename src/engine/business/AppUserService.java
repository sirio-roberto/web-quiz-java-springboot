package engine.business;

import engine.business.entities.AppUser;
import engine.persistence.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.Set;

@Service
public class AppUserService {
    private final AppUserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public AppUserService(AppUserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(AppUser user) {
        if (repository.existsByEmail(user.getEmail())) {
            throw new ConstraintViolationException(Set.of());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
    }

    public Iterable<AppUser> getAllUsers() {
        return repository.findAll();
    }
}
