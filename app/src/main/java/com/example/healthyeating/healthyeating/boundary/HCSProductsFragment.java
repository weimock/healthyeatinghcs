package com.example.healthyeating.healthyeating.boundary;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.healthyeating.healthyeating.R;
import com.example.healthyeating.healthyeating.entity.HCSProducts;
import com.example.healthyeating.healthyeating.interfaces.IHCSListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HCSProductsFragment extends Fragment {


    private ArrayList<HCSProducts> hscProducts;
    private IHCSListener hcsListener; //Link Interface for interaction with main activity

    //selected__category
    private int spinnerCatValue = 0;
    private int spinnerSortValue = 0;
    private TextView catTypeView;
    private String catChosen;
    private String sortChosen;
    private ListView hcsListView;

    //hcsproducts
    private Spinner sortSpinner;
    private Spinner catSpinner;
    private SearchView hcsSearchView;
    private Button btn_search ,btn_meatPoultry, btn_seafood, btn_eggs, btn_dairy, btn_cereals, btn_fruitsVeggie,btn_legumesNutsSeeds, btn_oil, btn_crips, btn_iceCream, btn_beverages, btn_SaucesSoupsRecipesMixes, btn_misc ;



    public HCSProductsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_hcsproducts, container, false);

        // Bind the layout elements to variables
        catSpinner = (Spinner) v.findViewById(R.id.hcsCatSpinner);
        sortSpinner = (Spinner) v.findViewById(R.id.hcsSortSpinner);

        catTypeView = (TextView) v.findViewById(R.id.hcsCatTypeView);
        hcsListView = (ListView) v.findViewById(R.id.hcsListView);

        hcsSearchView = (SearchView) v.findViewById(R.id.HCSMainSearchView);

        btn_meatPoultry = (Button) v.findViewById(R.id.meatButton);
        btn_seafood = (Button) v.findViewById(R.id.seafoodButton);
        btn_eggs = (Button) v.findViewById(R.id.eggButton);
        btn_dairy = (Button) v.findViewById(R.id.dairyButton);
        btn_cereals = (Button) v.findViewById(R.id.cerealButton);
        btn_fruitsVeggie = (Button) v.findViewById(R.id.fruitsVegButton);
        btn_legumesNutsSeeds = (Button) v.findViewById(R.id.legumesButton);
        btn_oil = (Button) v.findViewById(R.id.oilButton);
        btn_crips = (Button) v.findViewById(R.id.crispsButton);
        btn_iceCream = (Button) v.findViewById(R.id.icecreamButton);
        btn_beverages = (Button) v.findViewById(R.id.beverageButton);
        btn_SaucesSoupsRecipesMixes = (Button) v.findViewById(R.id.sauceButton);
        btn_misc = (Button) v.findViewById(R.id.miscButton);


/*
        hcsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                // Get the information about clicked location
                HCSProducts clickedProduct = (HCSProducts) hcsListView.getItemAtPosition(pos);
                hcsListener.onProductListItemClicked(clickedProduct.getProductName(),spinnerCatValue);
            }
        });

        //set spinner
        String[] categoryArray = {"All Categories", "Meat and Poultry", "Seafood"};
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>((Context) hcsListener,
                android.R.layout.simple_spinner_item, categoryArray);
        categoryAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        catSpinner.setAdapter(categoryAdapter);

        // get input from dropdown menu and display favourites depending on chosen category
        catChosen = "All Categories";
        catSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                catChosen = parent.getItemAtPosition(position).toString();
                refreshListView(catChosen, hcsListView);
                catTypeView.setText(catChosen);
                spinnerCatValue = position;
            }

            public void onNothingSelected(AdapterView<?> parent){}
        });
*/

///////////
        btn_meatPoultry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //suibian(v); //working

                hcsListener.onMeatPoultry();

            }
        });


        return v;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IHCSListener) {
            hcsListener = (IHCSListener) context;
        } else {
            throw new RuntimeException(context.toString());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
    /*
    // refreshes the list view with favourites
    public void refreshListView(String catChosen, ListView hcsListView) {
        ArrayList<HCSProducts> displayedList = hcsListener.getHCSByCategory(catChosen);
        CustomListAdapter HCSAdapter = new CustomListAdapter((Context) hcsListener, R.layout.activity_hcs__selected__category, displayedList);
        hcsListView.setAdapter(HCSAdapter);
    }

    // custom adapter for complex views in hcs tab
    private class CustomListAdapter extends ArrayAdapter<HCSProducts> {
        private int layout;
        private List<HCSProducts> hcsList;
        private CustomListAdapter(Context context, int resource, List<HCSProducts> hcsList) {
            super(context, resource, hcsList);
            this.hcsList = hcsList;
            layout = resource;
        }

        // build list item view
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder mainViewholder = null;
            if(convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);
                ViewHolder viewHolder = new ViewHolder();

                // establish links to layout elements
                viewHolder.productName = (TextView) convertView.findViewById(R.id.eateries_list_item_name);


                convertView.setTag(viewHolder);
            }

            mainViewholder = (ViewHolder) convertView.getTag();



            // set variable text into text views
            mainViewholder.productName.setText(getItem(position).getProductName());

            return convertView;



        }
    }

    public class ViewHolder {
        TextView productName;    // product name

    }





    /*
    //working fragment
    public void suibian(View v){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_layout, MainActivity.favouriteFragment);
        transaction.addToBackStack(null);
        transaction.commit();
       // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,fragment).commit();
    }*/


    /*
    //public void onAttach(Context context)
    {


    }

    //public void onDetach()
    {


    }

    //public void displaySelectedCatProducts(String catName)
    {


    }

    // public void displaySelectedProductDetails(String brandName, String productName)
    {


    }
    */
}