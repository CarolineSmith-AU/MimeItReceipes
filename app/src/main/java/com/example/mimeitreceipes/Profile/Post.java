package com.example.mimeitreceipes.Profile;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Post {

    private String imageUrl;
    private String restaurant, dish, description, tagsString;
    private List<String> tagsList;
    private String append;

    Post(String imageUrlIn, String appendIn, String restaurant, String dish, String description, String tagsString) {
        this.imageUrl = imageUrlIn;
        this.restaurant = restaurant;
        this.dish = dish;
        this.description = description;
        this.tagsString = tagsString;
        this.append = appendIn;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAppend() {
        return append;
    }

    public void setAppend(String append) {
        this.append = append;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImage(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTagsString() {
        return tagsString;
    }

    public void setTagsString(String tagsString) {
        this.tagsString = tagsString;
    }

    public List<String> getTagsList() {
        return tagsList;
    }

    public void setTagsList(List<String> tagsList) {
        this.tagsList = tagsList;
    }

    /**
     * Method that writes tags to the file Tags.txt.txt
     * @throws IOException - throws exception if error with file occurs
     */
    public void writeToFile() throws IOException {
        FileWriter fw = new FileWriter("Tags.txt");
    }

    /**
     * Reads tags from Tags.txt.txt (using comma delimeter) and stores them in List<String>.
     * @return - returns list of strings.
     */
    public List<String> readFromFile() {
        return null;
    }
}
