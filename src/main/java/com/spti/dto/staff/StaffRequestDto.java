package com.spti.dto.staff;

public class StaffRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private String experience;
    private String status;
    private Integer roleId;
    private int branch;

    public StaffRequestDto() {}

    public StaffRequestDto(String firstName, String lastName, String email, String password, 
                           String phoneNumber, String address, String experience, 
                           String status, Integer roleId, int branch) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.experience = experience;
        this.status = status;
        this.roleId = roleId;
        this.branch = branch;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getAddress() { return address; }
    public String getExperience() { return experience; }
    public String getStatus() { return status; }
    public Integer getRoleId() { return roleId; }
    public int getBranch() { return branch; }

    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setAddress(String address) { this.address = address; }
    public void setExperience(String experience) { this.experience = experience; }
    public void setStatus(String status) { this.status = status; }
    public void setRoleId(Integer roleId) { this.roleId = roleId; }
    public void setBranch(int branch) { this.branch = branch; }
}