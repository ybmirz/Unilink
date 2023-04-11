package com.example.unilink.Models;

import com.example.unilink.Models.Interests.Category;
import com.example.unilink.Models.Interests.Interest;
import com.google.firebase.Timestamp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;

public class UnilinkUser implements Serializable {
    private final String userID;
    private final Timestamp timeCreated;
    public List<String> ConnectedUIDs;
    private PriorityQueue<Category> categories;
    private String bio;
    private String pfpURL;
    private String pfbURL;
    private Date birthdate;
    public UnilinkUser(String userID) {
        this.userID = userID;
        this.bio = null;
        this.pfpURL = null;
        this.pfbURL = null;
        this.categories = new PriorityQueue<>(Comparator.comparingInt(Category::getPriorityLevel).reversed());
        this.ConnectedUIDs = new ArrayList<>();
        this.timeCreated = Timestamp.now();
        this.birthdate = null;
    }

    public PriorityQueue<Category> getCategories() {
        return categories;
    }

    public String getBio() {
        return bio;
    }
    public String getUserID() {return userID;}
    public String getPfpURL() {return pfpURL;}
    public String getPfbURL() {return pfbURL;}

    public void setBio(String bio) {
        this.bio = bio;
    }
    public void setProfilePicture(String url) {this.pfpURL = url;}
    public void setProfileBanner(String url) {this.pfbURL = url;}
    public void setBirthdate(Date date) {this.birthdate = date;}

    public void addChosenInterest(Interest interest) {
        for (Category category : categories) {
            if (category.getName() == interest.getCategory().getName()) {
                category.addInterest(interest);
                return;
            }
        }
        Category newCategory = new Category(0, interest.getCategory().getName());
        newCategory.addInterest(interest);
        categories.add(newCategory);
    }

    public List<Interest> getChosenInterests() {
        List<Interest> chosenInterest = new ArrayList<>();
        for (Category category : categories) {
            chosenInterest.addAll(category.getInterests().values());
        }
        return chosenInterest;
    }

    public List<Interest> getTop3HighestInterest(){
        List<Interest> interests = new ArrayList<>();
        for (Category category : categories) {
            interests.addAll(category.getInterests().values());
        }
        return interests.subList(0, Math.min(3, interests.size()));
    }

    public Category getHighestPriorityCategory() {
        return this.categories.peek();
    }

    public void addConnectedUser(String userID) {
        ConnectedUIDs.add(userID);
    }

    public List<String> getConnectedUIDs() {
        return ConnectedUIDs;
    }
}
