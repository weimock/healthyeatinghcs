package com.example.healthyeating.healthyeating.entity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.healthyeating.healthyeating.interfaces.DAO;

import java.util.ArrayList;
import java.util.List;

public class HCSProducts{

    private int ID;
    private String category;
    private String productName;
    private double productWeight;
    private String brandName;
    private String companyName;

    //private List hcsList = new ArrayList();
/*
    public HCSProducts(String category, String productName, double productWeight, String brandName, String companyName)
    {
        this.category = category;
        this.productName = productName;
        this.productWeight = productWeight;
        this.brandName = brandName;
        this.companyName = companyName;

    }
    */

    public HCSProducts()
    {


    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProductName(){
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public double getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(double productWeight) {
        this.productWeight = productWeight;
    }

    public int getID()
    {
        return ID;
    }

    public void setID()
    {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "HCSProducts{" +
                "id=" + ID +
                ", category='" + category + '\'' +
                ", companyName='" + companyName + '\'' +
                ", productName='" + productName + '\'' +
                ", brandName='" + brandName + '\'' +
                ", productWeight='" + productWeight + '\'' +
                '}';
    }




    /*
    static class ItemViewHolder
    {
        TextView category;
        TextView companyName;
        TextView productName;
        TextView brandName;
        TextView productWeight;
    }

    public HCSProducts(Context context, int textViewResourceId)
    {
        super(context, textViewResourceId);

    }

    public void add(String[] object)
    {
        hcsList.add(object);
        super.add(object);

    }

    public int getCount()
    {
        return this.hcsList.size();
    }

    public String[] getItem(int index)
    {
        return (String[]) this.hcsList.get(index);
    }

    /*
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View row = convertView;
        ItemViewHolder viewHolder;

        if(row == null)
        {
            LayoutInflater inflater = (LayoutInflater) this.getContext();
        }
    }

    /*
    public HCSProducts(String category, String companyName, String productName, String brandName, double productWeight) {
        this.category = category;
        this.companyName = companyName;
        this.productName = productName;
        this.brandName = brandName;
        this.productWeight = productWeight;

    }
*/



}

