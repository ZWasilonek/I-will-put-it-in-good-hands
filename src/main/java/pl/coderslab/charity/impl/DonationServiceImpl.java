package pl.coderslab.charity.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.impl.generic.GenericServiceImpl;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.service.DonationService;

import java.util.List;

@Service
public class DonationServiceImpl extends GenericServiceImpl<Donation, DonationRepository> implements DonationService<Donation> {

    @Autowired
    public DonationServiceImpl(DonationRepository repository) {
        super(repository);
    }

    public Integer getQuantitySumFromAll() {
        return repository.getQuantitySumFromAll();
    }

    @Override
    public Integer getQuantitySumFromUserId(Long userId) {
        return repository.getQuantitySumFromUserId(userId);
    }

    @Override
    public List<Donation> getDonationsByUserId(Long userId) {
        return repository.getDonationsByUserId(userId);
    }
}
