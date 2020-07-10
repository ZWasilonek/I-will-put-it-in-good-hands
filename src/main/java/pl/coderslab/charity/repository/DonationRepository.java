package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.coderslab.charity.entity.Donation;

import java.util.Set;

public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query(value = "SELECT SUM(bags_quantity) FROM donations;", nativeQuery = true)
    Integer getTotalBags();

    @Query(value = "SELECT SUM(bags_quantity) FROM donations AS d straight_join users AS u ON d.user_id = u.id WHERE user_id=:userId", nativeQuery = true)
    Integer getQuantitySumFromUserId(@Param("userId") Long userId);

    @Query(value = "SELECT d.id, d.bags_quantity, d.institution_id, d.shipping_address_id, d.user_id FROM donations AS d straight_join users AS u ON d.user_id = u.id WHERE user_id=:userId", nativeQuery = true)
    Set<Donation> getDonationsByUserId(@Param("userId") Long userId);

}