package cypher_login.back_jwt.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Admin methods
    public List<String> getAllUsernames() {
        return userRepository.findAll().stream()
                .map(User::getUsername)
                .collect(Collectors.toList());
    }

    public String deleteUserById(Integer id) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            // Check if the user is admin before allowing deletion
            User user = userOpt.get();
            if (user.getRole() == Role.ADMIN) {
                return "Cannot delete the admin user.";
            }
            userRepository.deleteById(id);
            return "User deleted successfully with id: " + id;
        }
        return "User not found";
    }

    public String resetPasswordForUser(String username) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // Set an empty password (effectively "blank")
            user.setPassword(passwordEncoder.encode(""));
            userRepository.save(user);
            return "Password reset successfully for user: " + username;
        }
        return "User not found";
    }

    // User methods (updated to interact with lastLogin)
    public String getLastLogin(String username) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        return userOpt.map(user -> "Last login: " + user.getLastLogin()).orElse("User not found");
    }

    public String changePassword(String username, String newPassword) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return "Password changed successfully.";
        }
        return "User not found";
    }

    // Method to update the last login time
    public void updateLastLogin(String username) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setLastLogin(LocalDateTime.now());
            userRepository.save(user);
        }
    }
}


