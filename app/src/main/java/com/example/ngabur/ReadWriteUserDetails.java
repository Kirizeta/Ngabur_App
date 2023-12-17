package com.example.ngabur;

public class ReadWriteUserDetails {
    public String name, mobile, email, dob, photo, member;

    public ReadWriteUserDetails(String textName, String textMobile, String textDob, String textEmail, String textPhoto, String textMember){
        this.name = textName;
        this.mobile = textMobile;
        this.dob = textDob;
        this.email = textEmail;
        this.photo = textPhoto;
        this.member = textMember;
    }

    public ReadWriteUserDetails(){

    }
}
