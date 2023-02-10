package ru.financialliteracy.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import ru.financialliteracy.annotations.ValidAnswer;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "test")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ValidAnswer
    @Column(name = "first_answer", nullable = false)
    private String firstAnswer;

    @ValidAnswer
    @Column(name = "second_answer", nullable = false)
    private String secondAnswer;

    @ValidAnswer
    @Column(name = "third_answer", nullable = false)
    private String thirdAnswer;

    @ValidAnswer
    @Column(name = "fourth_answer", nullable = false)
    private String fourthAnswer;

    @ValidAnswer
    @Column(name = "fifth_answer", nullable = false)
    private String fifthAnswer;

    @Column(name = "qty_of_correct_answers", nullable = false)
    private Integer qtyOfCorrectAnswers;

    @ManyToOne
    @JoinColumn(name = "user_email", referencedColumnName = "email", nullable = false)
    private User user;

    public Test(String firstAnswer, String secondAnswer, String thirdAnswer, String fourthAnswer, String fifthAnswer) {
        this.firstAnswer = firstAnswer;
        this.secondAnswer = secondAnswer;
        this.thirdAnswer = thirdAnswer;
        this.fourthAnswer = fourthAnswer;
        this.fifthAnswer = fifthAnswer;
    }

    public final List<String> getAllAnswers() {
        return removeSpacesFromStrings(
                firstAnswer,
                secondAnswer,
                thirdAnswer,
                fourthAnswer,
                fifthAnswer
        );
    }

    public final List<String> removeSpacesFromStrings(String... answers) {
        return Arrays.stream(answers).map(String::trim).toList();
    }

    public final int countCorrectAnswers(List<String> correctAnswers, List<String> userAnswers ) {
        int count = 0;
        for (int i = 0; i < correctAnswers.size(); i++) {
            if (correctAnswers.get(i).equalsIgnoreCase(userAnswers.get(i))) {
                count++;
            }
        }
        return count;
    }

    public final Test removeSpacesFromAnswersBeforeSaving(Test test) {
        test.firstAnswer = firstAnswer.trim();
        test.secondAnswer = secondAnswer.trim();
        test.thirdAnswer = thirdAnswer.trim();
        test.fourthAnswer = fourthAnswer.trim();
        test.fifthAnswer = fifthAnswer.trim();
        return test;
    }

    public final int getBestTestResults(List<Test> testResults) {
        Test test = testResults
                .stream()
                .max(Comparator.comparingInt(
                        Test::getQtyOfCorrectAnswers))
                .orElseThrow();
        return test.getQtyOfCorrectAnswers();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Test test = (Test) o;
        return id != null && Objects.equals(id, test.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}