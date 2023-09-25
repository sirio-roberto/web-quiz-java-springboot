package engine.business;

import engine.business.entities.AnswerResponse;
import engine.business.entities.AppUser;
import engine.business.entities.AppUserAdapter;
import engine.business.entities.Quiz;
import engine.persistence.AppUserRepository;
import engine.persistence.QuizRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Page<Quiz> getQuizzes(int page) {
        return quizRepository.findAll(PageRequest.of(page, 10));
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

    public boolean deleteQuiz(long id) {
        Quiz quiz = getQuizById(id);
        AppUser user = getAuthenticatedUser();
        if (quiz.getUser().equals(user)) {
            quizRepository.delete(quiz);
            return true;
        } else {
            return false;
        }
    }

    public boolean updateQuiz(long id, Quiz quiz) {
        Quiz dbQuiz = getQuizById(id);
        AppUser user = getAuthenticatedUser();
        if (dbQuiz.getUser().equals(user)) {
            Quiz.copyQuizProperties(quiz, dbQuiz);
            quizRepository.save(dbQuiz);
            return true;
        } else {
            return false;
        }
    }
}
