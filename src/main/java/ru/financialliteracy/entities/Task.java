package ru.financialliteracy.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_of_task", nullable = false)
    private String nameOfTask;

    @NotEmpty(message = "Данное поле не может быть пустым")
    @Column(nullable = false)
    private String answer;

    @Column(nullable = false, name = "is_answer_correct")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isAnswerCorrect;

    @ManyToOne
    @JoinColumn(name = "user_email", referencedColumnName = "email", nullable = false)
    private User user;

    public final StringBuilder getBestResult(List<Task> taskList, String typeOfTask, User currentUser) {
        if (!taskList.isEmpty()) {
            switch (typeOfTask.toLowerCase()) {
                case "finances", "deposit", "insurance", "investment", "pension" -> {
                    for (Task task: taskList) {
                        if (task.getNameOfTask().equalsIgnoreCase(typeOfTask) &&
                                currentUser.getEmail().equalsIgnoreCase(task.getUser().getEmail())) {
                            if (task.getIsAnswerCorrect()) {
                                return new StringBuilder("Вы верно решили задание «" + typeOfTask + "»");
                            }
                        }
                    }
                }
            }
        }
        return new StringBuilder("Вы решили неправильно задание «" + typeOfTask + "» или не решали вовсе");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Task task = (Task) o;
        return id != null && Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}