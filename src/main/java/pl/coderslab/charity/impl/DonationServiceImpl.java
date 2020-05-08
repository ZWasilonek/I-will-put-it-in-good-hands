package pl.coderslab.charity.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.impl.generic.GenericServiceImpl;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.service.DonationService;

@Service
public class DonationServiceImpl extends GenericServiceImpl<Donation, DonationRepository> implements DonationService<Donation> {

    @Autowired
    public DonationServiceImpl(DonationRepository repository) {
        super(repository);
    }

    public Integer getQuantitySumFromAll() {
        return repository.getQuantitySumFromAll();
    }
}
