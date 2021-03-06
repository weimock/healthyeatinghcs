package com.example.healthyeating.healthyeating.entity;

import com.example.healthyeating.healthyeating.interfaces.DAO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class HCSProductsStorage implements DAO<HCSProducts>
{
    private ArrayList<HCSProducts> listOfHCSProducts = new ArrayList<HCSProducts>();
    private int product_sortFilter = -1; //0 = A-Z, 1 = Z-A


    public HCSProductsStorage() {
    }

    public ArrayList<HCSProducts> sortProductList(ArrayList<HCSProducts> pro) {
        Collections.sort(pro, new Comparator<HCSProducts>() {
            @Override
            public int compare(HCSProducts o1, HCSProducts o2) {
                if (product_sortFilter == 0) {
                    return o1.getProductName().compareTo(o2.getProductName());
                } else if (product_sortFilter == 1) {
                    return o2.getProductName().compareTo(o1.getProductName());
                } else {
                    return o1.getProductName().compareTo(o2.getProductName());
                }
            }
        });
        return pro;
    }

    //Retrieve HCS Products by Name
    public ArrayList<HCSProducts> retrieveByName(String name, int sort, String catType) {

        ArrayList<HCSProducts> hcsResult = new ArrayList<HCSProducts>();

        name = name.toUpperCase();

        for(int i = 0; i< listOfHCSProducts.size(); i++){
            if((listOfHCSProducts.get(i).getProductName().contains(name) || listOfHCSProducts.get(i).getBrandName().contains(name)) && listOfHCSProducts.get(i).getCategory().equals(catType)) {
                hcsResult.add(listOfHCSProducts.get(i));
            }
            else if((listOfHCSProducts.get(i).getProductName().contains(name) || listOfHCSProducts.get(i).getBrandName().contains(name)) && catType.equals("")) {
                hcsResult.add(listOfHCSProducts.get(i));
            }
        }
        return hcsResult;
    }

    public ArrayList<HCSProducts> getList(int index, int sort, String catType) {
        ArrayList<HCSProducts> hcsList = new ArrayList<>();
        if (sort != product_sortFilter) {
            product_sortFilter = sort;
            listOfHCSProducts = sortProductList(listOfHCSProducts);
        }

        for (int i = 0; i < listOfHCSProducts.size(); i++){
            if (listOfHCSProducts.get(i).getCategory().equals(catType)) {
                hcsList.add(listOfHCSProducts.get(i));
            }
            else if(catType.equals(""))
                hcsList.add(listOfHCSProducts.get(i));
        }

        return hcsList;
    }




    @Override
    public boolean add(int index, HCSProducts hcsProducts) {
        listOfHCSProducts.add(hcsProducts);
        return true;
    }


    @Override
    public HCSProducts retrieveByID(int id) {
        return null;
    }


    @Override
    public boolean delete(int index, HCSProducts hcsProducts) {
        return false;
    }

    @Override
    public void update(HCSProducts hcsProducts, String[] params) {
    }


}