package com.example.healthyeating.healthyeating.controller;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.example.healthyeating.healthyeating.R;
import com.example.healthyeating.healthyeating.entity.HCSProducts;
import com.example.healthyeating.healthyeating.entity.HCSProductsStorage;
import com.example.healthyeating.healthyeating.interfaces.DAO;
import com.example.healthyeating.healthyeating.interfaces.IFileReader;
import com.example.healthyeating.healthyeating.utilities.ReadCSVImpl;

import java.util.ArrayList;

public class HCSManager extends ArrayAdapter<HCSProducts> {
    private IFileReader fileReader;
    private DAO<HCSProducts> hcsProductsDAO;

    private Context context;
    private int sortFilter = 0; //0 = A-Z, 1 = Z-A
    private String catType;
    private int catSortFilter;
    //private String hcsFileName = "HCSProductData";

    public HCSManager(Context context, int textViewResourceID) {
        super(context, textViewResourceID);
        hcsProductsDAO = new HCSProductsStorage();


    }

    public HCSProducts getCategory(int ID) {
        return hcsProductsDAO.retrieveByID(ID);
    }

    public void setCatType(String catType) {
        this.catType = catType;
    }

    public String getCatType() {
        return catType;
    }

    //Loading the screen with Products from the selected category

    public void SelectedHCSList(ArrayList<String[]> hcsResult, String catType) {
        //Array List for storing products from selected category
        ArrayList<String[]> selectedCatProducts = new ArrayList<String[]>();

        //Read data from CSV
        fileReader = new ReadCSVImpl();

        hcsResult = fileReader.readFile(context, "" + R.raw.hcs);

        for (int i = 0; i < hcsResult.size(); i++) {
            String[] rows = hcsResult.get(i);

            //For checking of category (First column of CSV file)
            //row[0] belongs to Category column. "If" statement to check if that row belongs to the selected category
            if (rows[0] == catType) {
                selectedCatProducts.add(rows);
            }

            createSelectedHCSList(selectedCatProducts);
        }

    }

    public void createSelectedHCSList(ArrayList<String[]> hcsData) {
        String category = "";
        String product_name = "";
        double product_weight = 0.0;
        String brand_name = "";
        String company_name = "";

        for (int i = 0; i < hcsData.size(); i++) {
            HCSProducts prod = new HCSProducts();
            String[] cols = hcsData.get(i);

            prod.setCategory(cols[0]);
            prod.setProductName(cols[2]);
            prod.setProductWeight(Double.parseDouble(cols[4]));
            prod.setBrandName(cols[3]);
            prod.setCompanyName(cols[1]);

            //hcsProductsDAO.add(0, pro);
            this.add(prod);
        }
    }

    public ArrayList<HCSProducts> getProductList() {

        ArrayList<HCSProducts> hcsProduct = hcsProductsDAO.getList(0, sortFilter, catType);

        return hcsProduct;
    }


    public void setSortFilter(int sortFilter) {
        this.sortFilter = sortFilter;
    }

    public void setCatSortFilter(int catSortFilter) {
        this.catSortFilter = catSortFilter;
    }


    //For searching of HCS Products
    public ArrayList<HCSProducts> searchProducts(String name) {
        ArrayList<HCSProducts> prodList = hcsProductsDAO.retrieveByName(name, sortFilter, catType);

        return prodList;
    }

    /*
    public ArrayList<HCSProducts> displayProductsDetails(String name, String catType)
    {

    return null;
    }

*/


}