package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO user_authority VALUES (:user_id, :authority_id);", nativeQuery = true)
    void insertUserRole(@Param("user_id") Long userId, @Param("authority_id") Long authorityId);

}
