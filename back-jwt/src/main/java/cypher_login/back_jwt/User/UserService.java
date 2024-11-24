package cypher_login.back_jwt.User;

import cypher_login.back_jwt.utils.PasswordUtil;
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

    public String deleteUserByUsername(String username) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            // Check if the user is admin before allowing deletion
            User user = userOpt.get();
            if (user.getRole() == Role.ADMIN) {
                return "Cannot delete the admin user.";
            }
            userRepository.deleteById(user.getId());
            return "User deleted successfully with id: " + user.getId();
        }
        return "User not found";
    }

    public String resetPasswordForUser(String username) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // Set an empty password (effectively "blank")
            if (user.getRole() == Role.ADMIN) {
                return "Cannot reset admin password.";
            }
            String salt = PasswordUtil.generateSalt(); // Gen salt for password hashing
            String hashedPassword = PasswordUtil.hashPassword("", salt);// hash the password
            user.setSalt(salt);
            user.setPassword(hashedPassword);
            userRepository.save(user);
            return "Password reset successfully for user: " + user.getUsername();
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
            String salt = PasswordUtil.generateSalt(); // Gen salt for password hashing
            String hashedPassword = PasswordUtil.hashPassword(newPassword, salt); // hash the password
            user.setSalt(salt);
            user.setPassword(hashedPassword);
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


