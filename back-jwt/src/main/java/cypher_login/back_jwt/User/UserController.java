package cypher_login.back_jwt.User;

import cypher_login.back_jwt.Auth.ChangePasswordRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/update-password")
    public ResponseEntity<String> updatePassword(@RequestBody ChangePasswordRequest request) {
        // Ensure that you are getting the username of the authenticated user.
        // For this example, I assume the username is passed in the request.

        String username = request.getUsername();  // Assuming the request contains the username.
        String currentPassword = request.getCurrentPassword();
        String newPassword = request.getNewPassword();

        // Call the UserService's changePassword method to update the password.
        String result = userService.changePassword(username, newPassword);

        if ("Password changed successfully.".equals(result)) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/last-login")
    public ResponseEntity<String> getLastLoginTime(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(user.getLastLogin());
    }

    // Endpoint for the admin to retrieve all usernames
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/usernames")
    public ResponseEntity<List<String>> getAllUsernames() {
        return ResponseEntity.ok(userService.getAllUsernames());
    }

    // Endpoint for the admin to delete a user by ID
    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        userService.deleteUserByUsername(username);
        return ResponseEntity.ok("User deleted successfully.");
    }

    // Endpoint for the admin to clear a user's password
    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/{username}/clear-password")
    public ResponseEntity<String> clearUserPassword(@PathVariable String username) {
        userService.resetPasswordForUser(username);
        return ResponseEntity.ok("Password cleared successfully.");
    }





}

