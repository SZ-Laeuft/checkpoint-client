package org.szlaeuft.checkpoint.helpers;

import org.szlaeuft.checkpoint.Application;
import org.szlaeuft.checkpoint.managers.StateManager;

public class NFCHelper {
    public String state;
    public String uid;
    public String response;
    public String extras;


    public String getState() {
        return state;
    }

    public String getUid() {
        return uid;
    }

    public String getResponse() {
        return response;
    }

    public String getExtras() {
        return extras;
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

    public void setExtras(String extras) {
        this.extras = extras;
    }

    @Override
    public String toString() {
        return "got this from nfcclient [state=" + state + ", uid=" + uid + ", response=" + response + ", extras=" + extras + "]";
    }

//    public void execute(){
//        stateManager.setCurrentState(this.state,new MessageHelper("test"));
//    }
//
//    public void setStateManager(StateManager stateManager) {
//        this.stateManager = stateManager;
//    }
}
