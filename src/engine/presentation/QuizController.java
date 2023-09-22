package engine.presentation;

import engine.business.QuizService;
import engine.business.entities.Quiz;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {
    private final QuizService service;

    public QuizController(QuizService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Object> getQuizzes() {
        return ResponseEntity.ok(service.getQuizzes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getQuiz(@PathVariable long id) {
        try {
            return ResponseEntity.ok(service.getQuizById(id));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Object> postQuiz(@Valid @RequestBody Quiz quiz) {
        return ResponseEntity.ok(service.createQuiz(quiz));
    }


    @PostMapping("/{id}/solve")
    public ResponseEntity<Object> postAnswer(@PathVariable long id, @RequestBody Map<String, Set<Integer>> answerMap) {
        try {
            Set<Integer> answer = answerMap.get("answer");
            if (answer == null) {
                 return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(service.answerResponse(id, answer));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
