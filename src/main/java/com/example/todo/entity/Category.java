package com.example.todo.entity;

public enum Category {
    JOB("仕事"),CERTIFICATION("資格"),PRIVATE("プライベート");

    private final String label;

    Category(String label) {this.label = label;}

    public String getLabel() {return label;}
}
