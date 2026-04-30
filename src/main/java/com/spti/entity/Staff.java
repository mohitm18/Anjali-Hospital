package com.spti.entity;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "staff")
@Setter
@Getter
public class Staff {
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private Long id;
	@Column( name = "first_name" )
	private String firstName;
	@Column( name = "last_name" )
	private String lastName;
	@Column( name = "role" )
	private String role;
	@Column( name = "email" )
	private String username;
	@Column( name = "phone_number" )
	private String phoneNumber;
	@Column( name = "address" )
	private String address;
	@Column( name = "experience" )
	private String experience;
	@Column( name = "status" )
	private String status;
    @ManyToOne
    @JoinColumn(name = "branch")
    private Branch branch;
    @OneToOne( mappedBy  = "staff")
    private Login login;
}
