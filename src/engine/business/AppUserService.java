package engine.business;

import engine.business.entities.AppUser;
import engine.persistence.AppUserRepository;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.Set;

@Service
public class AppUserService {
    private final AppUserRepository repository;

    public AppUserService(AppUserRepository repository) {
        this.repository = repository;
    }

    public void createUser(AppUser user) {
        if (repository.existsByEmail(user.getEmail())) {
            throw new ConstraintViolationException(Set.of());
        }
        repository.save(user);
    }

    public Iterable<AppUser> getAllUsers() {
        return repository.findAll();
    }
}
