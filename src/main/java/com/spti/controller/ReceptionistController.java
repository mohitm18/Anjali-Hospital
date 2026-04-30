package com.spti.controller;

import com.spti.constants.MessageConstants;
import com.spti.dto.staff.StaffRequestDto;
import com.spti.dto.staff.StaffResponseDto;
import com.spti.service.ReceptionistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/receptionist")
public class ReceptionistController {
    @Autowired
    private ReceptionistService receptionistService;

    @PostMapping("/addStaff")
    public ResponseEntity<String> addReceptionist(@Valid @RequestBody StaffRequestDto dto) {
        boolean isAdded = receptionistService.addReceptionist(dto);
        if(isAdded)
            return ResponseEntity.status(HttpStatus.CREATED).body(MessageConstants.ADD_STAFF_SUCCESS_MESSAGE);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MessageConstants.ADD_STAFF_ERROR_MESSAGE);
    }
    @PostMapping("/updateReceptionist")
    public ResponseEntity<String> updateReceptionist(@Valid @RequestBody StaffRequestDto dto){
        boolean isAdded = receptionistService.updateReceptionist(dto);
        if(isAdded)
            return ResponseEntity.status(HttpStatus.CREATED).body(MessageConstants.UPDATE_STAFF_SUCCESS_MESSAGE);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MessageConstants.UPDATE_STAFF_ERROR_MESSAGE);
    }
    @GetMapping("/getStaffById/{id}")
    public StaffResponseDto getStaffById(@PathVariable Long id){
        return receptionistService.getStaffById(id);
    }
    @PostMapping("/updateStaff")
    public ResponseEntity<String> updateStaff( @Valid @RequestBody StaffRequestDto dto ) {
        boolean isAdded = receptionistService.updateStaff( dto );
        if ( isAdded )
            return ResponseEntity.status( HttpStatus.CREATED ).body( MessageConstants.UPDATE_STAFF_SUCCESS_MESSAGE );
        else
            return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( MessageConstants.UPDATE_STAFF_ERROR_MESSAGE );
    }
//    @PatchMapping("/updateReceptionistFields/{id}")
//    public ResponseEntity<String> updateField(@PathVariable Long id, @RequestBody StaffUpdateFieldsRequestDto dto) {
//        return ResponseEntity.ok(receptionistService.updateField(id, dto));
//    }

    @DeleteMapping("/deleteStaff/{id}")
    public ResponseEntity<String> deleteStaff(@PathVariable Long id){
        boolean isDeleted = receptionistService.deleteStaff(id);
        if(isDeleted)
            return ResponseEntity.status(HttpStatus.CREATED).body(MessageConstants.DELETE_STAFF_SUCCESS_MESSAGE);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MessageConstants.DELETE_STAFF_ERROR_MESSAGE);
    }

    @GetMapping("/getReceptionistByUsername/{username}")
    public StaffResponseDto getReceptionistByUsername(@PathVariable String username){
        return receptionistService.getReceptionistByUsername(username);
    }
//    @GetMapping( "/staffs/{branchId}" )
//	public ResponseEntity<List<StaffResponseDto>> getStaffByBranch(@PathVariable int branchId ) {
//		return ResponseEntity.ok( receptionistService.getStaffByBranch(branchId));
//
//	}

    @GetMapping("/staffs")
    public ResponseEntity<List<StaffResponseDto>> getAllStaff(){
        return ResponseEntity.ok( receptionistService.getAllStaff());
    }
}
