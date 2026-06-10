package com.spti.controller;

import com.spti.dto.staff.StaffRequestDto;
import com.spti.dto.staff.StaffResponseDto;
import com.spti.entity.Role;
import com.spti.service.StaffService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private StaffService staffService;

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = staffService.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    @PostMapping("/staff/onboard")
    public ResponseEntity<StaffResponseDto> onboardStaff(@RequestBody StaffRequestDto request) {
        StaffResponseDto response = staffService.onboardStaff(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/staff/all")
    public ResponseEntity<List<StaffResponseDto>> getAllStaff() {
        List<StaffResponseDto> list = staffService.getAllStaff();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/staff/{id}")
    public ResponseEntity<StaffResponseDto> getStaffById(@PathVariable Long id) {
        StaffResponseDto staff = staffService.getStaffById(id);
        return ResponseEntity.ok(staff);
    }

    // 5. Staff update karnyathi
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateStaff(@PathVariable Long id, @RequestBody StaffRequestDto request) {
        staffService.updateStaff(id, request);
        return ResponseEntity.ok("Staff Updated Successfully");
    }

    // 6. Staff delete karnyathi
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStaff(@PathVariable Long id) {
        staffService.deleteStaff(id);
        return ResponseEntity.ok("Staff Deleted Successfully");
    }


}