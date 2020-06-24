package pl.coderslab.charity.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.dto.CategoryDTO;
import pl.coderslab.charity.dto.DonationDTO;
import pl.coderslab.charity.dto.InstitutionDTO;
import pl.coderslab.charity.dto.UserDTO;
import pl.coderslab.charity.facade.FacadeDonationService;
import pl.coderslab.charity.mapper.CategoryMapper;
import pl.coderslab.charity.mapper.DonationMapper;
import pl.coderslab.charity.mapper.InstitutionMapper;
import pl.coderslab.charity.mapper.UserMapper;
import pl.coderslab.charity.service.CategoryService;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.UserService;

import java.beans.PropertyEditorSupport;
import java.util.Set;

@Service
public class FacadeDonationServiceImpl extends PropertyEditorSupport implements FacadeDonationService {

    private final DonationService donationService;
    private final InstitutionService institutionService;
    private final CategoryService categoryService;
    private final UserService userService;
    private final DonationMapper donationMapper;
    private final CategoryMapper categoryMapper;
    private final InstitutionMapper institutionMapper;
    private final UserMapper userMapper;

    @Autowired
    public FacadeDonationServiceImpl(DonationService donationService, InstitutionService institutionService,
                                     CategoryService categoryService, UserService userService,
                                     DonationMapper donationMapper, CategoryMapper categoryMapper,
                                     InstitutionMapper institutionMapper, UserMapper userMapper) {
        this.donationService = donationService;
        this.institutionService = institutionService;
        this.categoryService = categoryService;
        this.userService = userService;
        this.donationMapper = donationMapper;
        this.categoryMapper = categoryMapper;
        this.institutionMapper = institutionMapper;
        this.userMapper = userMapper;
    }

    @Override
    public DonationDTO create(DonationDTO dto) {
        return donationMapper.mapToDTO(donationService.create(donationMapper.mapToEntity(dto)));
    }

    @Override
    public DonationDTO update(DonationDTO dto) {
        return donationMapper.mapToDTO(donationService.update(donationMapper.mapToEntity(dto)));
    }

    @Override
    public Set<DonationDTO> findAll() {
        return donationMapper.setDonationsToDTO(donationService.findAll());
    }

    @Override
    public Set<DonationDTO> findAll(int pageNo, int limit) {
        return null;
    }

    @Override
    public boolean remove(DonationDTO dto) {
        return donationService.remove(donationMapper.mapToEntity(dto));
    }

    @Override
    public boolean removeById(Long id) {
        return donationService.removeById(id);
    }

    @Override
    public DonationDTO findById(Long id) {
        return donationMapper.mapToDTO(donationService.findById(id));
    }

    @Override
    public Set<CategoryDTO> getAllCategories() {
        return categoryMapper.setCategoriesToDTO(categoryService.findAll());
    }

    @Override
    public Set<InstitutionDTO> getAllInstitutions() {
        return institutionMapper.setInstitutionsToDTO(institutionService.findAll());
    }

    @Override
    public UserDTO findUserByEmail(String userEmail) {
        return userMapper.mapToDTO(userService.findByEmail(userEmail));
    }

}