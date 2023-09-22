package engine.business;

import engine.business.entities.AnswerResponse;
import engine.business.entities.Quiz;
import engine.persistence.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class QuizService {
    private final QuizRepository repository;

    public QuizService(QuizRepository repository) {
        this.repository = repository;
    }

    public Iterable<Quiz> getQuizzes() {
        return repository.findAll();
    }

    public Quiz getQuizById(long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Quiz id not found"));
    }

    public Quiz createQuiz(Quiz quiz) {
        return repository.save(quiz);
    }

    public Iterable<Quiz> createQuizzes(List<Quiz> quizzes) {
        return repository.saveAll(quizzes);
    }

    public AnswerResponse answerResponse(long id, Set<Integer> answer) {
        Quiz quiz = getQuizById(id);
        if (Objects.equals(answer, quiz.getAnswer())) {
            return new AnswerResponse(true, "Congratulations, you're right!");
        }
        return new AnswerResponse(false, "Wrong answer! Please, try again.");
    }
}
