package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.coderslab.charity.entity.Donation;

import java.util.Set;

public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query(value = "SELECT SUM(bags_quantity) FROM donations;", nativeQuery = true)
    Integer getTotalBags();

    @Query(value = "SELECT SUM(bags_quantity) FROM user_donations ud JOIN donations ON ud.donation_id = donations.id WHERE user_id=:userId", nativeQuery = true)
    Integer getQuantitySumFromUserId(@Param("userId") Long userId);

    @Query(value = "SELECT id, bags_quantity, shipping_address_id, institution_id FROM donations d straight_join user_donations on d.id = user_donations.donation_id WHERE user_id=:userId", nativeQuery = true)
    Set<Donation> getDonationsByUserId(@Param("userId") Long userId);

}