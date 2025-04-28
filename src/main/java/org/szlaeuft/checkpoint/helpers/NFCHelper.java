package org.szlaeuft.checkpoint.helpers;

public class NFCHelper {
    public String state;
    public String uid;
    public String response;

    public String getState() {
        return state;
    }

    public String getUid() {
        return uid;
    }

    public String getResponse() {
        return response;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
