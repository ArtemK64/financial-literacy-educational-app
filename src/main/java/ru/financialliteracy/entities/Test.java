package ru.financialliteracy.entities;

import lombok.*;
import org.hibernate.Hibernate;
import ru.financialliteracy.annotations.ValidAnswer;

import javax.persistence.*;
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