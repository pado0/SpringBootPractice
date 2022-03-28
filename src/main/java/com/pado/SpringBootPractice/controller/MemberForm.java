package com.pado.SpringBootPractice.controller;

public class MemberForm {
    public String getName() {

        // createMemberForm.html의 name과 매칭되어 들어올 것
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}
