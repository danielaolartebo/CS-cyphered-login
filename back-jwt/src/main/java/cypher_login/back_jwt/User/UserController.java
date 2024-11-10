package cypher_login.back_jwt.User;

import cypher_login.back_jwt.Auth.ChangePasswordRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        // You can also add additional logic here to validate the current password.

        // Call the UserService's changePassword method to update the password.
        String result = userService.changePassword(username, newPassword);

        if ("Password changed successfully.".equals(result)) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
    }



}

