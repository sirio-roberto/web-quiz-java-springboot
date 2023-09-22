package engine.presentation;

import engine.business.AppUserService;
import engine.business.entities.AppUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AppUserController {
    private final AppUserService service;

    public AppUserController(AppUserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> postUser(@Valid @RequestBody AppUser user) {
        try {
            service.createUser(user);
            return ResponseEntity.ok().build();
        } catch (ConstraintViolationException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/users")
    public ResponseEntity<Object> getUsers() {
        return ResponseEntity.ok(service.getAllUsers());
    }
}
