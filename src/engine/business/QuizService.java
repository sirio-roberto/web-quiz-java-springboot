package engine.business;

import engine.business.entities.AnswerResponse;
import engine.business.entities.Quiz;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class QuizService {
    private final List<Quiz> quizzes = new ArrayList<>();

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public Quiz getQuizById(long id) {
        return quizzes.stream()
                .filter(q -> q.getId() == id)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Quiz id not found"));
    }

    public Quiz createQuiz(Quiz quiz) {
        quiz.setId(Quiz.getNextId());
        quizzes.add(quiz);
        return quiz;
    }

    public AnswerResponse answerResponse(long id, Set<Integer> answer) {
        Quiz quiz = getQuizById(id);
        if (Objects.equals(answer, quiz.getAnswer())) {
            return new AnswerResponse(true, "Congratulations, you're right!");
        }
        return new AnswerResponse(false, "Wrong answer! Please, try again.");
    }
}
