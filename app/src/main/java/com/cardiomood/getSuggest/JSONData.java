package com.cardiomood.getSuggest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by apple on 9/19/17.
 */

public class JSONData {

    @SerializedName("device")
    @Expose
    private String device;

    @SerializedName("options")
    @Expose
    private String options;

    @SerializedName("requests")
    @Expose
    private List<Request> requests = null;

    public JSONData(String device, String options, List<Request> requests) {
        this.device = device;
        this.options = options;
        this.requests = requests;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    @Override
    public String toString() {
        return "{" +
                "\"device\": \"" + device + "\"" +
                ",\n \"options\": \"" + options + "\"" +
                ",\n \"requests\":" + requests +
                '}';
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }
}

