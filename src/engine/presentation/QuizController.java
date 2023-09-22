package engine.presentation;

import engine.business.entities.Quiz;
import engine.business.entities.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Object> postQuiz(@RequestBody Quiz quiz) {
        return ResponseEntity.ok(service.createQuiz(quiz));
    }


    @PostMapping("/{id}/solve")
    public ResponseEntity<Object> postAnswer(@PathVariable long id, @RequestParam int answer) {
        try {
            return ResponseEntity.ok(service.answerResponse(id, answer));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
