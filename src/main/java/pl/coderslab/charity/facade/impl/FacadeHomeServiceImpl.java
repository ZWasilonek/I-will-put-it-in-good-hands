package pl.coderslab.charity.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import pl.coderslab.charity.dto.DonationDTO;
import pl.coderslab.charity.dto.InstitutionDTO;
import pl.coderslab.charity.dto.UserDTO;
import pl.coderslab.charity.facade.FacadeHomeService;
import pl.coderslab.charity.mapper.DonationMapper;
import pl.coderslab.charity.mapper.InstitutionMapper;
import pl.coderslab.charity.mapper.UserMapper;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.SecurityService;
import pl.coderslab.charity.service.UserService;
import java.util.Set;

@Service
public class FacadeHomeServiceImpl implements FacadeHomeService {

    private final UserService userService;
    private final SecurityService securityService;
    private final InstitutionService institutionService;
    private final DonationService donationService;
    private final InstitutionMapper institutionMapper;
    private final DonationMapper donationMapper;
    private final UserMapper userMapper;

    @Autowired
    public FacadeHomeServiceImpl(UserService userService, SecurityService securityService,
                                 InstitutionService institutionService, DonationService donationService,
                                 InstitutionMapper institutionMapper, DonationMapper donationMapper,
                                 UserMapper userMapper) {
        this.userService = userService;
        this.securityService = securityService;
        this.institutionService = institutionService;
        this.donationService = donationService;
        this.institutionMapper = institutionMapper;
        this.donationMapper = donationMapper;
        this.userMapper = userMapper;
    }

    @Override
    public Set<InstitutionDTO> findAllInstitutions() {
        return institutionMapper.setInstitutionsToDTO(institutionService.findAll());
    }

    @Override
    public Integer getTotalBags() {
        return donationService.getTotalBagsQuantity();
    }

    @Override
    public Set<DonationDTO> findAllDonations() {
        return donationMapper.setDonationsToDTO(donationService.findAll());
    }

    @Override
    public void register(UserDTO userDTO, BindingResult bindingResult) {
        userService.saveUser(userMapper.mapToEntity(userDTO));
        securityService.autoLogin(userDTO.getEmail(), userDTO.getConfirmPassword());
    }

}