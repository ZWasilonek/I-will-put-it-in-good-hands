package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.coderslab.charity.entity.Donation;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query(value = "SELECT SUM(quantity) FROM donations;", nativeQuery = true)
    Integer getQuantitySumFromAll();

    @Query(value = "SELECT SUM(quantity) FROM user_donations ud JOIN donations ON ud.donation_id = donations.id WHERE user_id=:userId", nativeQuery = true)
    Integer getQuantitySumFromUserId(@Param("userId") Long userId);

    @Query(value = "SELECT id, city, pick_up_comment, pick_up_date, pick_up_time, quantity, street, zip_code, institution_id FROM donations d straight_join user_donations on d.id = user_donations.donation_id WHERE user_id=:userId", nativeQuery = true)
    List<Donation> getDonationsByUserId(@Param("userId") Long userId);
}
