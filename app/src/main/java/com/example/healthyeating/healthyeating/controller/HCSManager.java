package com.example.healthyeating.healthyeating.controller;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.example.healthyeating.healthyeating.R;
import com.example.healthyeating.healthyeating.entity.HCSProducts;
import com.example.healthyeating.healthyeating.entity.HCSProductsStorage;
import com.example.healthyeating.healthyeating.interfaces.DAO;
import com.example.healthyeating.healthyeating.interfaces.IFileReader;
import com.example.healthyeating.healthyeating.utilities.ReadCSVImpl;

import java.io.InputStream;
import java.util.ArrayList;

public class HCSManager {
    private IFileReader fileReader;
    private DAO<HCSProducts> hcsProductsDAO;

    private Context context;
    private int sortFilter = 0; //0 = A-Z, 1 = Z-A
    private String catType = "";
    private int catSortFilter;
    //private String hcsFileName = "HCSProductData";
    InputStream in;


    public HCSManager() {

        hcsProductsDAO = new HCSProductsStorage();

    }


    public void initHCSProductList(Context c) {
        ArrayList<String[]> hcsResult;

        //Array List for storing products from selected category
        //ArrayList<String[]> selectedCatProducts = new ArrayList<String[]>();
        //HCSProducts.hcsProductsArr = new ArrayList<HCSProducts>();

        context = c;

        //Read local storage
        fileReader = new ReadCSVImpl();

        hcsResult = fileReader.readFile(context, "" + R.raw.hcs);

        for (int i = 0; i < hcsResult.size(); i++) {

            String[] row = hcsResult.get(i);

            String category = row[0];
            String comName = row[1];
            String prodName = row[2];
            String brandName = row[3];
            String prodWeight = row[4];

            HCSProducts pro = new HCSProducts(category, prodName.toUpperCase(), prodWeight.toUpperCase(), brandName.toUpperCase(), comName.toUpperCase());

            hcsProductsDAO.add(0,pro);



            //HCSProducts.hcsProductsArr.add(new HCSProducts(category, prodName, prodWeight, brandName, comName));
            //selectedCatProducts.add(hcsResult.get(i));

            //For checking of category (First column of CSV file)
            //   String[] cols = hcsResult.get(i);

            //cols[0] belongs to Category column. "If" statement to check if that row belongs to the selected category
            // if(cols[0] == catType)
            // {

            //}


            //createSelectedHCSList(selectedCatProducts);
            //}

        }

    }

    /*

    public ArrayList<HCSProducts> getAllHCSList() {
        ArrayList<HCSProducts> hcsList = new ArrayList<HCSProducts>();
        for (HCSProducts listHCS : getProductList()) {
             hcsList.add(listHCS);
        }
        return hcsList;
    }

*/
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

        for(int i = 0; i<hcsResult.size(); i++)
        {
            String[] rows = hcsResult.get(i);

            //For checking of category (First column of CSV file)
            String[] cols = hcsResult.get(i);

            //cols[0] belongs to Category column. "If" statement to check if that row belongs to the selected category
            if(cols[0] == catType)
            {
                selectedCatProducts.add(rows);
            }

            //createSelectedHCSList(selectedCatProducts.get(i), catType);
        }


    }

/*

    public void createSelectedHCSList(ArrayList<String[]> hcsData) {
        String category = "";
        String product_name = "";
        String product_weight = "";
        String brand_name = "";
        String company_name = "";

        for(int i=0; i<hcsData.size(); i++)
        {
            String[] cols = hcsData.get(i);

            category = cols[0];
            product_name = cols[2];
            product_weight = cols[4];
            brand_name = cols[3];
            company_name = cols[1];

            HCSProducts pro = new HCSProducts(category, product_name, product_weight, brand_name, company_name);
            hcsProductsDAO.add(0, pro);
        }
    }
*/

    public ArrayList<HCSProducts> getProductList() {


        ArrayList<HCSProducts> hcsList = hcsProductsDAO.getList(0,sortFilter,catType);

        return hcsList;
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