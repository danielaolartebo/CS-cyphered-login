package cypher_login.back_jwt.Auth;

import cypher_login.back_jwt.Jwt.JwtService;
import cypher_login.back_jwt.User.Role;
import cypher_login.back_jwt.User.User;
import cypher_login.back_jwt.User.UserRepository;
import cypher_login.back_jwt.User.UserService;
import cypher_login.back_jwt.utils.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        // Fetch the user by username

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        // Validate the password with salt and hash
        boolean isPasswordValid = PasswordUtil.validatePassword(request.getPassword(), user.getSalt(), user.getPassword());
        if (!isPasswordValid) {
            throw new RuntimeException("Invalid username or password");
        }

        /*// Authenticate the user using AuthenticationManager (optional if password already validated)
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );*/

        // Update last login timestamp
        userService.updateLastLogin(user.getUsername());

        // Generate JWT
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest request) {
        // Get the role from the request (default to USER if not provided or invalid)
        String salt = PasswordUtil.generateSalt(); // Gen salt for password hashing
        String hashedPassword = PasswordUtil.hashPassword(request.getPassword(), salt); // hash the password
        Role role = Role.fromString(request.getRole());  // Role is expected to be a string (admin or user)

        User user = User.builder()
                .username(request.getUsername())
                .salt(salt)
                .password(hashedPassword)
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .role(role)  // Set the role dynamically
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}

