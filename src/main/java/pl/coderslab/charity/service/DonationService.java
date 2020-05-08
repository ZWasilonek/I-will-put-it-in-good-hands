package pl.coderslab.charity.service;

import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.service.generic.GenericService;

import java.util.List;

public interface DonationService<T> extends GenericService<T> {

    Integer getQuantitySumFromAll();
    Integer getQuantitySumFromUserId(Long userId);
    List<Donation> getDonationsByUserId(Long userId);

}
