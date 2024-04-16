package com.example.kattehjemmesideprojektet.Model;

public class Cat {
    private Long catId;
    private String race;
    private String name;
    private int age;
    private double weight;
    private int owner;


    public Cat() {
    }

    public Cat(Long catId, String race, String name, int age, double weight, int owner) {
        this.catId = catId;
        this.race = race;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.owner = owner;
    }

    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }
}