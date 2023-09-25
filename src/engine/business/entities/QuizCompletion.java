package engine.business.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class QuizCompletion {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

    @JsonProperty(value = "id")
    private Long quizId;

    private LocalDateTime completedAt;

    @JsonIgnore
    private boolean correctAnswer;

    @ManyToOne
    @JsonIgnore
    private AppUser user;


    public QuizCompletion() {
    }

    public QuizCompletion(Long quizId, LocalDateTime completedAt, AppUser user) {
        this.quizId = quizId;
        this.completedAt = completedAt;
        this.user = user;
    }

    public QuizCompletion(Long id, Long quizId, LocalDateTime completedAt, boolean correctAnswer, AppUser user) {
        this(quizId, completedAt, user);
        this.id = id;
        this.correctAnswer = correctAnswer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public boolean isCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }
}
