package engine.business;

import engine.business.entities.AppUser;
import engine.persistence.AppUserRepository;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {
    private final AppUserRepository repository;

    public AppUserService(AppUserRepository repository) {
        this.repository = repository;
    }

    public void createUser(AppUser user) {
        repository.save(user);
    }

    public Iterable<AppUser> getAllUsers() {
        return repository.findAll();
    }
}
