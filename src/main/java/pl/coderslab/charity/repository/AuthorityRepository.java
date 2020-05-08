package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.entity.Authority;
import pl.coderslab.charity.entity.generic.AuthorityType;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    Authority findByName(AuthorityType name);

}
