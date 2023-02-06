package ru.financialliteracy.entities;

import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.financialliteracy.annotations.Cyrillic;
import ru.financialliteracy.annotations.Password;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Cyrillic
    @NotEmpty(message = "Поле с именем не может быть пустым")
    @Size(min = 2, max = 100, message = "Имя должно быть в диапазоне от 2 до 100 букв")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Cyrillic
    @NotEmpty(message = "Поле с фамилией не может быть пустой")
    @Size(min = 2, max = 100, message = "Фамилия должна быть в диапазоне от 2 до 100 букв")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Email(message = "Электронная почта должна быть валидной")
    @NotEmpty(message = "Поле с электронной почтой не может быть пустым")
    @Column(nullable = false)
    private String email;

    @Password
    @NotEmpty(message = "Поле с паролем не может быть пустым")
    @Column(nullable = false)
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
}

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}