package ru.financialliteracy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.financialliteracy.entities.User;
import ru.financialliteracy.repositories.UserRepository;
import ru.financialliteracy.service.interfaces.IUserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;

    @Override
    public boolean isEmailExist(String username) {
        List<User> users = userRepository.findAll();
        return users.stream()
                .anyMatch(userEmail -> userEmail.getEmail().equalsIgnoreCase(username));
    }
}