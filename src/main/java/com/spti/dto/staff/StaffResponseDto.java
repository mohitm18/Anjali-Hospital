package com.spti.dto.staff;

public class StaffResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String status;
    private String address;
    private String experience;
    private String phoneNumber;
    private String generatedPassword;
    private Integer roleId;    // <-- COMMENT KADHUN TAKA
    private String role;
    private Integer branch;
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getBranch() {
        return branch;
    }

    public void setBranch(Integer branch) {
        this.branch = branch;
    }

    public StaffResponseDto() {}

    // Getters
    public Long getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
  //  public String getRoleName() { return roleName; }
    public String getStatus() { return status; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setEmail(String email) { this.email = email; }
//public void setRoleName(String roleName) { this.roleName = roleName; }
    public void setStatus(String status) { this.status = status; }

    public String getGeneratedPassword() {
        return generatedPassword;
    }

    public void setGeneratedPassword(String generatedPassword) {
        this.generatedPassword = generatedPassword;
    }

    // public Integer getRoleId() {
    //     return roleId;
    // }

    // public void setRoleId(Integer roleId) {
    //     this.roleId = roleId;
    // }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}