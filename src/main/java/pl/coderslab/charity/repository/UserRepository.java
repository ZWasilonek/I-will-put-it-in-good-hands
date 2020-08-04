package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.coderslab.charity.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT u.id, email, first_name, is_enabled, last_name, password FROM users AS u WHERE u.email=:email AND u.is_enabled=true", nativeQuery = true)
    User findByEmail(@Param("email") String email);

}