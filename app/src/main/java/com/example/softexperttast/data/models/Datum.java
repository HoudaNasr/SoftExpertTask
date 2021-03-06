
package com.example.softexperttast.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("brand")
    @Expose
    private String brand;
    @SerializedName("constractionYear")
    @Expose
    private String constractionYear;
    @SerializedName("isUsed")
    @Expose
    private Boolean isUsed;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Datum() {
    }

    /**
     * 
     * @param imageUrl
     * @param id
     * @param constractionYear
     * @param brand
     * @param isUsed
     */
    public Datum(Integer id, String brand, String constractionYear, Boolean isUsed, String imageUrl) {
        super();
        this.id = id;
        this.brand = brand;
        this.constractionYear = constractionYear;
        this.isUsed = isUsed;
        this.imageUrl = imageUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getConstractionYear() {
        return constractionYear;
    }

    public void setConstractionYear(String constractionYear) {
        this.constractionYear = constractionYear;
    }

    public Boolean getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Boolean isUsed) {
        this.isUsed = isUsed;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
