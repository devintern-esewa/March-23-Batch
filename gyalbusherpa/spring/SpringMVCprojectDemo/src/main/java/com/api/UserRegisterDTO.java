package com.api;

import com.annotation.Don;

public class UserRegisterDTO {
    private String name;
    private String userName;
    private char[] password;
    private String countryName;
    private String[] hobbies;
    private String gender;
//    @Don(lower = 20 , upper = 40, message = "age should be between 20 and 40")
    @Don
    private Integer age;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    private CommunicationDTO communicationDTO;

    public CommunicationDTO getCommunicationDTO() {
        return communicationDTO;
    }

    public void setCommunicationDTO(CommunicationDTO communicationDTO) {
        this.communicationDTO = communicationDTO;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String[] getHobbies() {
        return hobbies;
    }

    public void setHobbies(String[] hobbies) {
        this.hobbies = hobbies;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }
}
