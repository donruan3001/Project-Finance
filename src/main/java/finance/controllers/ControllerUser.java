package finance.controllers;

import finance.dto.user.UserProfileDTO;
import finance.dto.user.UserUpdateDTO;
import finance.services.ServiceUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class ControllerUser {
    
    @Autowired
    private ServiceUser serviceUser;
    
    @GetMapping("/{userId}/profile")
    public ResponseEntity<UserProfileDTO> getUserProfile(@PathVariable Long userId) {
        var profile = serviceUser.getUserProfile(userId);
        return ResponseEntity.ok(profile);
    }
    
    @PutMapping("/{userId}/profile")
    public ResponseEntity<UserProfileDTO> updateUserProfile(
            @PathVariable Long userId,
            @Valid @RequestBody UserUpdateDTO data) {
        var profile = serviceUser.updateUserProfile(userId, data);
        return ResponseEntity.ok(profile);
    }
    
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        serviceUser.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}