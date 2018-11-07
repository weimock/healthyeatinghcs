package com.example.healthyeating.healthyeating.entity;

import com.example.healthyeating.healthyeating.interfaces.DAO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class HCSProductsStorage implements DAO<HCSProducts>
{
    private ArrayList<HCSProducts> listOfHCSProducts = new ArrayList<HCSProducts>();
    private int product_sortFilter = -1;


    public HCSProductsStorage() {
    }


    public ArrayList<HCSProducts> sortProductList(ArrayList<HCSProducts> pro) {
       /*
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
*/
        return pro;
    }


    @Override
    //Retrieve HCS Products by ID
    public HCSProducts retrieveByID(int id) {
        /*
        for (int i = 0; i < listOfHCSProducts.size(); i++) {
            if (listOfHCSProducts.get(i).getID() == id) {
                return listOfHCSProducts.get(i);
            }
        }
        */
        return null;
    }

    //Retrieve HCS Products by Name
    public ArrayList<HCSProducts> retrieveByName(String name, int sort, String catType) {
        /*
        ArrayList<HCSProducts> results = new ArrayList<HCSProducts>();
        if (sort != product_sortFilter) {
            product_sortFilter = sort;
            listOfHCSProducts = sortProductList(listOfHCSProducts);
        }

        //name = name.toLowerCase().replace("-", " ");
        for (int i = 0; i < listOfHCSProducts.size(); i++) {
            if (listOfHCSProducts.get(i).getCategory().equals(catType)) {
                String concat = listOfHCSProducts.get(i).getProductName() + " " + listOfHCSProducts.get(i).getProductWeight() + " " + listOfHCSProducts.get(i).getBrandName() + " " + listOfHCSProducts.get(i).getCompanyName();
                concat = concat.toLowerCase();

                results.add(listOfHCSProducts.get(i));

            }
        }
*/
        //return results;
        return null;
    }

    public ArrayList<HCSProducts> getList(int index, int sort, String catType) {
        ArrayList<HCSProducts> hscList = new ArrayList<>();



            for (int i = 0; i < listOfHCSProducts.size(); i++)
            {
                hscList.add(listOfHCSProducts.get(i));
            }


        return hscList;
/*
        if (index == 0) {
            if (sort != product_sortFilter) {
                product_sortFilter = sort;
                listOfHCSProducts = sortProductList(listOfHCSProducts);
            }

            for (int i = 0; i < listOfHCSProducts.size(); i++) {
                HCSProducts rows = listOfHCSProducts.get(i);

                if (listOfHCSProducts.get(i).getCategory().equals(catType)) {
                    result.add(listOfHCSProducts.get(i));
                }
            }

        }
*/


    }

    @Override
    public boolean add(int index, HCSProducts hcsProducts) {
        listOfHCSProducts.add(hcsProducts);
        return true;
    }

    @Override
    public boolean delete(int index, HCSProducts hcsProducts) {
        return false;
    }

    @Override
    public void update(HCSProducts hcsProducts, String[] params) {

    }


}