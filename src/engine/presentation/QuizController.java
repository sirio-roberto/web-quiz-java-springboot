package engine.presentation;

import engine.business.entities.AnswerResponse;
import engine.business.entities.Quiz;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {
    private final Quiz quiz = new Quiz(
            "The Java Logo",
            "What is depicted on the Java logo?",
            List.of("Robot","Tea leaf","Cup of coffee","Bug").toArray(String[]::new)
    );

    @GetMapping
    public Quiz getQuiz() {
        return quiz;
    }

    @PostMapping
    public ResponseEntity<Object> postAnswer(@RequestParam int answer) {
        if (answer == 2) {
            return ResponseEntity.ok(new AnswerResponse(true, "Congratulations, you're right!"));
        }
        return ResponseEntity.ok(new AnswerResponse(false, "Wrong answer! Please, try again."));
    }
}
