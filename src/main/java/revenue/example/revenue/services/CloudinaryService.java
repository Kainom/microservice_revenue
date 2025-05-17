package revenue.example.revenue.services;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import io.github.cdimascio.dotenv.Dotenv;
import revenue.example.revenue.model.User;
import revenue.example.revenue.repository.UserRepository;

@Service
public class CloudinaryService {
    private final Cloudinary cloudinary;
    private final UserRepository userRepository;

    public CloudinaryService(UserRepository userRepository) {
        Dotenv dotenv = Dotenv.load();
        String cloudinaryUrl = dotenv.get("CLOUDINARY_URL");
        this.cloudinary = new Cloudinary(cloudinaryUrl);
        this.userRepository = userRepository;
    }

    public void uploadFile(MultipartFile multipartFile, String id) {
        File file;
        Optional<User> user = userRepository.findById(id);
        user.orElseThrow(() -> new RuntimeException("User not found"));

        try {
            file = File.createTempFile("upload", multipartFile.getOriginalFilename());

            multipartFile.transferTo(file);

            Map<?, ?> result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
            String url = result.get("secure_url").toString();
            user.get().setImageUrl(url);
            userRepository.save(user.get());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}