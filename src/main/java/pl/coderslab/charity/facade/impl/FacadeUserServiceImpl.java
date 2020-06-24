package pl.coderslab.charity.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.dto.DonationDTO;
import pl.coderslab.charity.dto.UserDTO;
import pl.coderslab.charity.facade.FacadeUserService;
import pl.coderslab.charity.mapper.DonationMapper;
import pl.coderslab.charity.mapper.UserMapper;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.UserService;

import java.util.Set;

@Service
public class FacadeUserServiceImpl implements FacadeUserService {

    private final UserService userService;
    private final DonationService donationService;
    private final UserMapper userMapper;
    private final DonationMapper donationMapper;

    @Autowired
    public FacadeUserServiceImpl(UserService userService, DonationService donationService, UserMapper userMapper, DonationMapper donationMapper) {
        this.userService = userService;
        this.donationService = donationService;
        this.userMapper = userMapper;
        this.donationMapper = donationMapper;
    }

    @Override
    public UserDTO findUserByEmail(String userEmail) {
        return userMapper.mapToDTO(userService.findByEmail(userEmail));
    }

    @Override
    public Integer getBagsSumByUserId(Long id) {
        return donationService.getQuantitySumFromUserId(id);
    }

    @Override
    public Set<DonationDTO> getAllDonationsByUserId(Long id) {
        return donationMapper.setDonationsToDTO(donationService.getDonationsByUserId(id));
    }

    @Override
    public UserDTO create(UserDTO dto) {
        return userMapper.mapToDTO(userService.create(userMapper.mapToEntity(dto)));
    }

    @Override
    public UserDTO update(UserDTO dto) {
        return userMapper.mapToDTO(userService.update(userMapper.mapToEntity(dto)));
    }

    @Override
    public Set<UserDTO> findAll() {
        return userMapper.setUsersToDTO(userService.findAll());
    }

    @Override
    public Set<UserDTO> findAll(int pageNo, int limit) {
        return null;
    }

    @Override
    public boolean remove(UserDTO dto) {
        return userService.remove(userMapper.mapToEntity(dto));
    }

    @Override
    public boolean removeById(Long id) {
        return userService.removeById(id);
    }

    @Override
    public UserDTO findById(Long id) {
        return userMapper.mapToDTO(userService.findById(id));
    }

}