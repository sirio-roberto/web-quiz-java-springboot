package engine.business;

import engine.business.entities.AnswerResponse;
import engine.business.entities.AppUser;
import engine.business.entities.AppUserAdapter;
import engine.business.entities.Quiz;
import engine.persistence.AppUserRepository;
import engine.persistence.QuizRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class QuizService {
    private final QuizRepository quizRepository;
    private final AppUserRepository userRepository;

    public QuizService(QuizRepository quizRepository, AppUserRepository userRepository) {
        this.quizRepository = quizRepository;
        this.userRepository = userRepository;
    }

    public Iterable<Quiz> getQuizzes() {
        return quizRepository.findAll();
    }

    public Quiz getQuizById(long id) {
        return quizRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Quiz id not found"));
    }

    public Quiz createQuiz(Quiz quiz) {
        AppUser user = getAuthenticatedUser();
        quiz.setUser(user);
        return quizRepository.save(quiz);
    }

    private AppUser getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AppUserAdapter userDetails = (AppUserAdapter) auth.getPrincipal();
        return userRepository
                .findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Not found"));
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
