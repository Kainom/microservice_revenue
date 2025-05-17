package revenue.example.revenue.services;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import revenue.example.revenue.dto.UserDTO;
import revenue.example.revenue.model.User;
import revenue.example.revenue.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void storeUser(UserDTO userDTO) {
        String uuid = UUID.randomUUID().toString();
        userDTO.setSlug(userDTO.getName() + uuid);
    }

    public UserDTO getUserBySlug(String slug) {
        Optional<User> user = userRepository.findBySlug(slug);
        if (!user.isPresent())
            throw new RuntimeException("User not found");
        return user.get().toDTO();

    }

    public void updateUser(UserDTO user) {
        User userModel = userRepository.findBySlug(user.getSlug())
                .orElseThrow(() -> new RuntimeException("User not found"));
        userModel.setName(user.getName());
        userModel.setLastName(user.getLastName());
        userModel.setEmail(user.getEmail());
        userModel.setPassword(user.getPassword());
        userModel.setImageUrl(user.getImageUrl());
        userModel.setDescription(user.getDescription());
        userModel.setPhone(user.getPhone());
        userModel.setSalary(user.getSalary());
        userModel.setMonthlyIncome(user.getMonthlyIncome());
        userModel.setWork(user.getWork());
        userModel.setInvestmentGoal(user.getInvestmentGoal());
        userRepository.save(userModel);
    }

    public void deleteUser(String slug) {
        User user = userRepository.findBySlug(slug)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }

}
