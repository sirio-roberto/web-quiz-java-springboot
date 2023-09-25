package engine.persistence;

import engine.business.entities.AppUser;
import engine.business.entities.QuizCompletion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface QuizCompletionRepository extends PagingAndSortingRepository<QuizCompletion, Long> {
    Page<QuizCompletion> findByUserAndCorrectAnswer(AppUser user, boolean correctAnswer, Pageable pageable);
}
