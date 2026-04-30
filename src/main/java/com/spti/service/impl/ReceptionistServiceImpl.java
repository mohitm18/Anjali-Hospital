package com.spti.service.impl;

import com.spti.dao.BranchDao;
import com.spti.dao.LoginDao;
import com.spti.dao.StaffRepository;
import com.spti.dto.staff.StaffRequestDto;
import com.spti.dto.staff.StaffResponseDto;
import com.spti.dto.staff.StaffUpdateFieldsRequestDto;
import com.spti.entity.Branch;
import com.spti.entity.Login;
import com.spti.entity.Staff;
import com.spti.mapper.LoginMapper;
import com.spti.mapper.staff.StaffMapper;
import com.spti.service.ReceptionistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReceptionistServiceImpl implements ReceptionistService {
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private StaffMapper staffMapper;
    @Autowired
    private LoginDao loginDao;
    @Autowired
    private LoginMapper loginMapper;
    @Autowired
    private BranchDao branchDao;

    @Override
    public boolean addReceptionist(StaffRequestDto dto) {

        try {
            // Convert DTO to Staff entity
            Staff staff = staffMapper.toEntity(dto);
            Optional<Branch> branchOpt = branchDao.findById(dto.getBranch()); //get branch id from dto and find branch in db
            if (branchOpt.isPresent()) { //check branch exist
                staff.setBranch(branchOpt.get());  // set branch object in staff
            }
            staffRepository.save(staff);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

@Override
public boolean updateReceptionist(StaffRequestDto dto) {
    try {
        Staff staff = staffRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Staff not found"));
        // update staff
        staff.setFirstName(dto.getFirstName());
        staff.setLastName(dto.getLastName());
        staff.setRole(dto.getRole());
        staff.setUsername(dto.getUsername());
        staff.setPhoneNumber(dto.getPhoneNumber());
        staff.setAddress(dto.getAddress());
        staff.setExperience(dto.getExperience());
        staff.setStatus(dto.getStatus());
        Optional<Branch> branchOpt = branchDao.findById(dto.getBranch());
        if (branchOpt.isPresent()) {
            staff.setBranch(branchOpt.get());
        }
        // login update
        if (staff.getLogin() != null) {
            Login login = staff.getLogin();
            login.setUsername(dto.getUsername());
            login.setRole(dto.getRole());
        }
        staffRepository.save(staff);
        return true;
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}

    @Override
    public boolean deleteStaff(Long id) {
        try{
            if (staffRepository.existsById(id)){
                staffRepository.deleteById(id);
                return true;
            }
        } catch (Exception e) {
          e.printStackTrace();
        }
        return false;
    }

    @Override
    public StaffResponseDto getStaffById(Long id) {
        Optional<Staff> staffOpt = staffRepository.findById(id);
        if(staffOpt.isPresent()){
            StaffResponseDto responseDto = staffMapper.toDto(staffOpt.get());
            return responseDto;
        }
        return null;
    }

    @Override
    public String updateField(Long id, StaffUpdateFieldsRequestDto dto) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Staff not found"));
        staffMapper.updateFieldOfStaff(dto, staff);
        staffRepository.save(staff);
        return null;
    }

    @Override
    public StaffResponseDto getReceptionistByUsername(String username) {
        Optional<Staff> staffOpt = staffRepository.findByUsername(username);
        if(staffOpt.isPresent()) {
            return staffMapper.toDto(staffOpt.get());
        }
        return null;
    }

    @Override
    public List<StaffResponseDto> getAllStaff() {
        //fetch all staff from db
        List<Staff> staffs = (List<Staff>) staffRepository.findAll();

        //convert entity to dto
        return staffMapper.toList(staffs);
    }

    @Override
    public boolean updateStaff(StaffRequestDto dto) {
        try {
            Staff staff = staffMapper.toEntity( dto );
            Optional<Branch> opt = branchDao.findById( dto.getBranch() );
            if ( opt.isPresent() ) {
                staff.setBranch( opt.get() );
            }

            staffRepository.save( staff );
            return true;
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return false;
    }

//    @Override
//	public List<StaffResponseDto> getStaffByBranch(int branchId) {
//		Optional<Branch> opt = branchDao.findById( branchId );
//        if (opt.isEmpty()) {
//            throw new RuntimeException("Branch not found");
//        }
//			List<Staff> staffs =staffRepository.findByBranch(opt.get());
//			return staffMapper.toList(staffs);
//		}
	}
