package com.example.healthyeating.healthyeating.controller;


import com.example.healthyeating.healthyeating.entity.HCSProducts;

public class SingletonManager {

    private static LocationsManager lm;
    private static HCSManager hm;

    private SingletonManager(){
        //Prevent creation of instance via constructor
    }

    public static LocationsManager getLocationManagerInstance() {
        if(lm==null)
            lm = new LocationsManager();
        return lm;
    }

    public static HCSManager getHCSManagerInstance() {
        if(hm==null)
            hm = new HCSManager();
        return hm;
    }



}
