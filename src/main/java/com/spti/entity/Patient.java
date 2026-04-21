package com.spti.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "patients")
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "patient_id")
	private Long id;

	@NotBlank(message = "First name is mandatory")
	@Size(max = 50, message = "First name can be at most 50 characters long")
	@Column(name = "first_name")
	private String firstName;

	@NotBlank(message = "Last name is mandatory")
	@Size(max = 50, message = "Last name can be at most 50 characters long")
	@Column(name = "last_name")
	private String lastName;

	@NotBlank(message = "Gender is mandatory")
	// @Pattern(regexp = "Male|Female|Other", message = "Gender must be Male,Female,
	// or Other")
	@Column(name = "gender")
	private String gender;

	@Size(max = 255, message = "Address can be at most 255 characters long")
	@Column(name = "address")
	private String address;

	@NotNull(message = "Age is mandatory")
	@Min(value = 0, message = "Age must be greater than or equal to 0")
	@Max(value = 100, message = "Age must be less than or equal to 120")
	@Column(name = "age")
	private Integer age;

    @NotBlank(message = "Phone number is mandatory")
    @Size(min = 10, max = 10, message = "Only 10 digits allowed")
    @Pattern(regexp = "^[0-9]+$", message = "Phone number must contain digits only")
    @Column(name = "phone_number")
    private String phoneNumber;

	@NotBlank(message = "Email is mandatory")
	@Email(message = "Email should be valid")
	@Column(name = "email")
	private String email;

	@ManyToOne
	@JoinColumn(name = "branch")
	private Branch branch;
}
