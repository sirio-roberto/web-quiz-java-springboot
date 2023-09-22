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
    private final QuizRepository quizRepository;

    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public Iterable<Quiz> getQuizzes() {
        return quizRepository.findAll();
    }

    public Quiz getQuizById(long id) {
        return quizRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Quiz id not found"));
    }

    public Quiz createQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public Iterable<Quiz> createQuizzes(List<Quiz> quizzes) {
        return quizRepository.saveAll(quizzes);
    }

    public AnswerResponse answerResponse(long id, Set<Integer> answer) {
        Quiz quiz = getQuizById(id);
        if (Objects.equals(answer, quiz.getAnswer())) {
            return new AnswerResponse(true, "Congratulations, you're right!");
        }
        return new AnswerResponse(false, "Wrong answer! Please, try again.");
    }
}
