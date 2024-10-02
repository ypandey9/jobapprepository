package com.embarkx.FirstSpring.review;

import com.embarkx.FirstSpring.company.Company;
import jakarta.persistence.*;

@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String title;
    private String description;
    private double rating;

    @ManyToOne
    private Company company;

    public Review(Long id, String title, String description, double rating, Company company) {
        Id = id;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.company = company;
    }

    public Review() {
    }

//    public Review(String title, String description, double rating) {
//        this.title = title;
//        this.description = description;
//        this.rating = rating;
//    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
