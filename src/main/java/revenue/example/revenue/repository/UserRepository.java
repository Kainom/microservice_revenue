package revenue.example.revenue.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import revenue.example.revenue.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);

    Optional <User> findBySlug(String slug);

    User findByCpf(String cpf);

}
