
package com.example.softexperttast.data.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cars {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("erroe")
    @Expose
    private ErrorModel error = null;

    public ErrorModel getError() {
        return this.error;
    }

    public void setError(ErrorModel error) {
        this.error = error;
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Cars() {
    }

    /**
     * 
     * @param data
     * @param status
     */
    public Cars(Integer status, List<Datum> data) {
        super();
        this.status = status;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

}
