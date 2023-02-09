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

//    public boolean getBestResult(List<Task> taskList, String typeOfTask) {
//        switch (typeOfTask.toLowerCase()) {
//            case "finances" -> {
//                for (Task task: taskList) {
//                    if (task.getNameOfTask().equals(typeOfTask)) {
//                        if (task.getIsAnswerCorrect()) {
//                            return true;
//                        }
//                    }
//                }
//            }
//
//        }
//    }

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