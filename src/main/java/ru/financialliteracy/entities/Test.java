package ru.financialliteracy.entities;

import lombok.*;
import org.hibernate.Hibernate;
import ru.financialliteracy.annotations.ValidAnswer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    public Test(String firstAnswer, String secondAnswer, String thirdAnswer, String fourthAnswer, String fifthAnswer) {
        this.firstAnswer = firstAnswer;
        this.secondAnswer = secondAnswer;
        this.thirdAnswer = thirdAnswer;
        this.fourthAnswer = fourthAnswer;
        this.fifthAnswer = fifthAnswer;
    }

    public final List<String> getAllAnswers() {
        return List.of(
                firstAnswer,
                secondAnswer,
                thirdAnswer,
                fourthAnswer,
                fifthAnswer
        );
    }

    public final int countCorrectAnswers(List<String> correctAnswers, List<String> userAnswers) {
        int count = 0;
        for (int i = 0; i < correctAnswers.size(); i++) {
            if (correctAnswers.get(i).equalsIgnoreCase(userAnswers.get(i))) {
                count++;
            }
        }
        return count;
    }
}