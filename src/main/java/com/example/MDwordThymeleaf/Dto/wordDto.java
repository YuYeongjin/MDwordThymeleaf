package com.example.MDwordThymeleaf.Dto;


import lombok.Data;

@Data
public class wordDto {
    private int id;

    private String korean;

    private String english;

    public wordDto(String korean, String english){
        this.english=english;
        this.korean=korean;
    }

    public wordDto() {

    }

    public wordDto(int id, String korean, String english) {
        this.id=id;
        this.english=english;
        this.korean=korean;
    }
}
