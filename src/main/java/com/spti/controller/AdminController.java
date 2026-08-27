package com.spti.controller;

import com.spti.dto.staff.ChangePasswordDto;
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

    // frontEnd validation for email exists
    @GetMapping("/staff/check-email")
    public ResponseEntity<Boolean> isEmailExists(@RequestParam String email) {
        try {
            boolean exists = staffService.isEmailExists(email);
            return ResponseEntity.ok(exists);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/staff/check-phone")
    public ResponseEntity<Boolean> isPhoneNumberExists(@RequestParam String phoneNumber) {
        try {
            boolean exists = staffService.isPhoneNoExists(phoneNumber);
            return ResponseEntity.ok(exists);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/staff/change-password/{id}")
    public ResponseEntity<String> changePassword(
            @PathVariable Long id,
            @RequestBody ChangePasswordDto dto) {

        try {

            staffService.changePassword(id, dto);

            return ResponseEntity.ok("Password Updated Successfully");

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());

        }
    }
}