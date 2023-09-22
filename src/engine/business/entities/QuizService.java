package engine.business.entities;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public AnswerResponse answerResponse(long id, int answer) {
        Quiz quiz = getQuizById(id);
        if (answer == quiz.getAnswer()) {
            return new AnswerResponse(true, "Congratulations, you're right!");
        }
        return new AnswerResponse(false, "Wrong answer! Please, try again.");
    }
}
