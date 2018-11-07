package com.example.healthyeating.healthyeating.interfaces;

import com.example.healthyeating.healthyeating.entity.HCSProducts;

import java.util.ArrayList;

//This interface will allow communication between HCSProductUI Fragments and MainActivity

public interface IHCSListener {

    /*
    void onCatButtonPressed(String catName);
    void onCatSearchSubmit(String search);
    void onCatSpinnerChange(int catIndex);
    void onSortSpinnerChange(int sortIndex);
    */

    ArrayList<HCSProducts> getHCSByCategory(String categoryChosen);
    void onProductListClicked(String name);
    void submitSearch(String query);
    void onSortSpinnerChange(int sortIndex);
    void onCatSpinnerChange(int sortIndex);
    ArrayList<HCSProducts> getAllHCSList(int sortType);
}
