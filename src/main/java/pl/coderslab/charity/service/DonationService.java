package pl.coderslab.charity.service;

import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.service.generic.GenericService;

import java.util.Set;

public interface DonationService extends GenericService<Donation> {

    Integer getTotalBagsQuantity();
    Integer getQuantitySumFromUserId(Long userId);
    Set<Donation> getDonationsByUserId(Long userId);

}