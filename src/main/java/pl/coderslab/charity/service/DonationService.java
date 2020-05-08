package pl.coderslab.charity.service;

import pl.coderslab.charity.service.generic.GenericService;

public interface DonationService<T> extends GenericService<T> {

    Integer getQuantitySumFromAll();
}
