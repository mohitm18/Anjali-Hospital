package com.spti.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spti.entity.Staff;
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

	@Autowired
    private org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder passwordEncoder;

	@Override
	public void add( LoginRequestDto loginDto ) {
		Login log = loginmapper.toLogin_entity( loginDto );
		loginDao.save( log );
	}

	// @Override
	// public LoginResponceDto login( LoginRequestDto dto ) {
	// 	Login login = loginDao.findByUsername( dto.getUsername() );
	// 	Login pass = loginDao.findByPassword( dto.getPassword() );
		
	// 	if ( login != null ) {
			
	// 		if (pass != null) {
	// 		LoginResponceDto temp = loginmapper.toEntity( login );
	// 		temp.setStatus( "Active" );
	// 		temp.setName( "Sonam Kothari" );
	// 		temp.setBranchId( 1 );
	// 		temp.setBranchName( "Bhadgaon" );
	// 		return temp;
	// 	}}
	// 	return null;
	// }

	@Override
    public LoginResponceDto login( LoginRequestDto dto ) {
        // 1. Fakt Username ne record shodha
        Login login = loginDao.findByUsername( dto.getUsername() );
        
        if ( login != null ) {
            if (passwordEncoder.matches(dto.getPassword(), login.getPassword())) {
                
                LoginResponceDto temp = loginmapper.toEntity( login );
                temp.setStatus( "Active" );
                if (login.getStaff() != null) {
					temp.setName( login.getStaff().getFirstName() + " " + login.getStaff().getLastName());}
                temp.setBranchId( 1 );
                temp.setBranchName( "Bhadgaon" );
                return temp;
            }
        }
        return null;
    }

}
