package revenue.example.revenue.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import revenue.example.revenue.dto.UserDTO;
import revenue.example.revenue.services.CloudinaryService;
import revenue.example.revenue.services.UserService;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;
    private final CloudinaryService cloudinaryService;

    // @GetMapping("/")
    // public ResponseEntity<List<UserDTO>> getAll() {
    // List<UserDTO> users = userService.getUsers();
    // return ResponseEntity.ok(users);
    // }
    @PostMapping("/")
    public ResponseEntity<Void> createUser(UserDTO userDTO) {
        userService.storeUser(userDTO);
        return ResponseEntity.ok().build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getBySlug(@PathVariable("slug") String slug) {
        UserDTO user = userService.getUserBySlug(slug);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file,
            @PathVariable("id") @RequestBody String id) {
        cloudinaryService.uploadFile(file, id);
        return ResponseEntity.ok().build();

    }
}
