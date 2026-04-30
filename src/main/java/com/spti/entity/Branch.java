package com.spti.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "branch" )
@Entity
public class Branch {
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "id" )
	private int id;
	@Column( name = "name" )
	private String name;
	@Column( name = "address" )
	private String address;
    @OneToMany(mappedBy = "branch")
    private List<Staff> staffs;
}
