package pl.coderslab.charity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.ShippingAddress;
import pl.coderslab.charity.service.impl.generic.GenericServiceImpl;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.service.DonationService;
import java.util.Set;

@Service
public class DonationServiceImpl extends GenericServiceImpl<Donation, DonationRepository> implements DonationService {

    @Autowired
    public DonationServiceImpl(DonationRepository repository) {
        super(repository);
    }

    public Integer getTotalBagsQuantity() {
        return repository.getTotalBags();
    }

    @Override
    public Integer getQuantitySumFromUserId(Long userId) {
        return repository.getQuantitySumFromUserId(userId);
    }

    @Override
    public Set<Donation> getDonationsByUserId(Long userId) {
        return repository.getDonationsByUserId(userId);
    }

    @Override
    public Donation create(Donation entity) {
        ShippingAddress shippingAddress = entity.getShippingAddress();
        shippingAddress.setStreet(shippingAddress.getStreet().trim());
        shippingAddress.setCity(shippingAddress.getCity().trim());
        shippingAddress.setZipCode(shippingAddress.getZipCode().trim());
        shippingAddress.setPhoneNumber(shippingAddress.getPhoneNumber().trim());
        shippingAddress.setPickUpComment(shippingAddress.getPickUpComment().trim());
        entity.setShippingAddress(shippingAddress);
        return repository.save(entity);
    }
}