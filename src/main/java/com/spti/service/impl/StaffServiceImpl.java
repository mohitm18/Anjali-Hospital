package com.spti.service.impl;

import com.spti.dto.staff.StaffRequestDto;
import com.spti.dto.staff.StaffResponseDto;
import com.spti.entity.*;
import com.spti.dao.*;
import com.spti.service.StaffService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private LoginDao loginRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public StaffResponseDto onboardStaff(StaffRequestDto request) {

        if (staffRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists!");
        }

        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));
        Branch branch = branchRepository.findById(request.getBranch())
                .orElseThrow(() -> new RuntimeException("Branch not found"));

        String plainPassword = request.getPassword();

        String encodedPassword = passwordEncoder.encode(plainPassword);

        Staff staff = new Staff();
        staff.setFirstName(request.getFirstName());
        staff.setLastName(request.getLastName());
        staff.setEmail(request.getEmail());
        staff.setPassword(encodedPassword);
        staff.setRole(role);
        staff.setExperience(request.getExperience());
        staff.setPhoneNumber(request.getPhoneNumber());
        staff.setAddress(request.getAddress());
        staff.setBranch(branch);
        staff.setStatus("ACTIVE");

        Staff savedStaff = staffRepository.save(staff);

        Login login = new Login();
        login.setUsername(savedStaff.getEmail());
        login.setPassword(encodedPassword);
        login.setRole(role.getName());
        login.setStaff(savedStaff);
        loginRepository.save(login);

        StaffResponseDto response = new StaffResponseDto();
        response.setId(savedStaff.getId());
        response.setFirstName(savedStaff.getFirstName());
        response.setEmail(savedStaff.getEmail());
        response.setGeneratedPassword(plainPassword);

        return response;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public List<StaffResponseDto> getAllStaff() {
        List<Staff> staffEntities = staffRepository.findAll();

        return staffEntities.stream().map(staff -> {
            StaffResponseDto dto = new StaffResponseDto();
            dto.setId(staff.getId());
            dto.setFirstName(staff.getFirstName());
            dto.setLastName(staff.getLastName());
            dto.setEmail(staff.getEmail());
            dto.setPhoneNumber(staff.getPhoneNumber());
            dto.setAddress(staff.getAddress());
            dto.setExperience(staff.getExperience());
            dto.setStatus(staff.getStatus());
            dto.setRole(staff.getRole().getName());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public StaffResponseDto getStaffById(Long id) {

        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Staff not found with id: " + id));
        return convertToResponseDto(staff);
    }

    private StaffResponseDto convertToResponseDto(Staff staff) {
        StaffResponseDto dto = new StaffResponseDto();

        dto.setId(staff.getId());
        dto.setFirstName(staff.getFirstName());
        dto.setLastName(staff.getLastName());
        dto.setEmail(staff.getEmail());
        dto.setPhoneNumber(staff.getPhoneNumber());
        dto.setAddress(staff.getAddress());
        dto.setExperience(staff.getExperience());
        dto.setStatus(staff.getStatus());
        dto.setGeneratedPassword(staff.getPassword());

        if (staff.getRole() != null) {
            dto.setRoleId(staff.getRole().getId());
            dto.setRole(staff.getRole().getName());
        }

        if (staff.getBranch() != null) {
            dto.setBranch(staff.getBranch().getId());
        }

        return dto;
    }

    // @Override
    // public void updateStaff(Long id, StaffRequestDto request) {
    // Staff staff = staffRepository.findById(id)
    // .orElseThrow(() -> new RuntimeException("Staff not found"));

    // // Existing staff che details update kara
    // staff.setFirstName(request.getFirstName());
    // staff.setLastName(request.getLastName());
    // staff.setPhoneNumber(request.getPhoneNumber());
    // staff.setEmail(request.getEmail());
    // staff.setAddress(request.getAddress());
    // staff.setStatus(request.getStatus());
    // Role role = new Role();
    // role.setId(request.getRoleId());
    // staff.setRole(role);
    // staff.setExperience(request.getExperience());
    // staff.setPassword(request.getPassword()); // Jar password update karaycha
    // asel tar

    // // Jar password badlaycha asel tar
    // if (request.getPassword() != null && !request.getPassword().isEmpty()) {
    // staff.setPassword(request.getPassword());
    // }

    // staffRepository.save(staff);
    // }

    @Override
    public void deleteStaff(Long id) {
        staffRepository.deleteById(id);
    }

    @Override
    public void updateStaff(Long id, StaffRequestDto request) {

        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Staff not found"));

        staff.setFirstName(request.getFirstName());
        staff.setLastName(request.getLastName());
        staff.setPhoneNumber(request.getPhoneNumber());
        staff.setEmail(request.getEmail());
        staff.setAddress(request.getAddress());
        staff.setStatus(request.getStatus());
        staff.setExperience(request.getExperience());

        if (request.getRoleId() != null) {
            Role role = new Role();
            role.setId(request.getRoleId());
            staff.setRole(role);
        }

        String newPassword = request.getPassword();
        boolean isPasswordUpdated = (newPassword != null && !newPassword.trim().isEmpty());

        if (isPasswordUpdated) {

            staff.setPassword(newPassword);
        }

        Staff savedStaff = staffRepository.save(staff);

        Optional<Login> loginOpt = loginRepository.findByStaff(savedStaff);

        if (loginOpt.isPresent()) {
            Login login = loginOpt.get();
            login.setUsername(request.getEmail());
            if (isPasswordUpdated) {
                login.setPassword(newPassword);
            }

            if (savedStaff.getRole() != null) {
                login.setRole(savedStaff.getRole().getName());
            }

            loginRepository.save(login);
        }
    }
}