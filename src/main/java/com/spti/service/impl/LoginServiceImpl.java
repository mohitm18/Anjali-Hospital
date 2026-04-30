package com.spti.service.impl;

import com.spti.entity.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spti.dao.LoginDao;
import com.spti.dto.LoginRequestDto;
import com.spti.dto.LoginResponceDto;
import com.spti.entity.Login;
import com.spti.mapper.LoginMapper;
import com.spti.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginMapper loginmapper;

	@Autowired
	private LoginDao loginDao;

	@Override
	public void add( LoginRequestDto loginDto ) {
		Login log = loginmapper.toLogin_entity( loginDto );
		loginDao.save( log );
	}

//	@Override
//	public LoginResponceDto login( LoginRequestDto dto ) {
//		Login login = loginDao.findByUsername( dto.getUsername() );
//        if(login == null){
//            return null;
//        }
//        if(!login.getPassword().equals(dto.getPassword())){
//            return null;
//        }
//        Staff staff = login.getStaff();
//        LoginResponceDto temp = new LoginResponceDto();
//        temp.setLoginId((long) login.getId());
//        temp.setUsername(login.getUsername());
//        temp.setName(staff.getFirstName() + " " + staff.getLastName());
//        temp.setRole(staff.getRole());
//        temp.setBranchId(staff.getBranch().getId());
//        temp.setBranchName(staff.getBranch().getName());
//        temp.setStatus("Active");
//
////		Login pass = loginDao.findByPassword( dto.getPassword() );
////
////		if ( login != null ) {
////
////			if (pass != null) {
////			LoginResponceDto temp = loginmapper.toEntity( login );
////			temp.setStatus( "Active" );
////			temp.setName( "Sonam Kothari" );
////			temp.setBranchId( 1 );
////			temp.setBranchName( "Bhadgao" );
////			return temp;
////		}}
//		return temp;
//	}
@Override
public LoginResponceDto login(LoginRequestDto dto) {

    Login login = loginDao.findByUsername(dto.getUsername());
    if (login == null) {
        return null;
    }
    if (!login.getPassword().equals(dto.getPassword())) {
        return null;
    }
    Staff staff = login.getStaff();
    if (staff == null) {
        throw new RuntimeException("Staff not mapped");
    }
    LoginResponceDto res = new LoginResponceDto();
    res.setId(staff.getId());
    res.setLoginId(login.getId());
    res.setUsername(login.getUsername());
    res.setFirstName(staff.getFirstName());
    res.setLastName(staff.getLastName());
    res.setRole(staff.getRole());
    res.setStatus(staff.getStatus());
    res.setPhoneNumber(staff.getPhoneNumber());
    res.setAddress(staff.getAddress());
    res.setExperience(staff.getExperience());

    if (staff.getBranch() != null) {
        res.setBranchId(staff.getBranch().getId());
        res.setBranchName(staff.getBranch().getName());
    }

    return res;
}

}
