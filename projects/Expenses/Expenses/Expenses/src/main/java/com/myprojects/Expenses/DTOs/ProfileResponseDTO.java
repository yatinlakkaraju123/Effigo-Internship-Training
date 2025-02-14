package com.myprojects.Expenses.DTOs;

import lombok.Data;

@Data
public class ProfileResponseDTO {
    private long profileId;
    private String firstName;
    private String lastName;
    private byte[] profilePic;
    private String email;
    private String username;
    private String phoneNumber;
    private String address;
    private boolean activatedProfile;
}
