package com.example.healthyeating.healthyeating.boundary;


import android.content.Context;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
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


    //private ArrayList<HCSProducts> hscProducts;

    private static int spinnerCatValue = 0;
    private static int spinnerSortValue = 0;
    private TextView catTypeView;
    private String catChosen;
    private String sortChosen;
    private ListView hcsListView;
    private Spinner sortSpinner;
    private Spinner catSpinner;
    private SearchView hcsSearchView;
    private ConstraintLayout relativeLayout;

    private IHCSListener hcsListener; //Link Interface for interaction with main activity


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

        hcsSearchView = (SearchView) v.findViewById(R.id.hcsSearchView);
        hcsSearchView.setQuery("", false);
        hcsSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                if (hcsListener != null)
                    hcsListener.submitSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (hcsListener != null)
                    hcsListener.submitSearch(newText);
                return false;
            }
        });


        //Dropdown list for sorting

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(),
                R.array.sort_hcs_array, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> catAdapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(),
                R.array.sort_cat_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        catAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        sortSpinner.setAdapter(adapter);
        catSpinner.setAdapter(catAdapter);

        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (hcsListener != null) {
                    hcsListener.onSortSpinnerChange(pos);
                }
                spinnerSortValue = pos;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        catSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (hcsListener != null) {
                    hcsListener.onSortSpinnerChange(pos);
                }
                spinnerSortValue = pos;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        setSortSpinnerValue(0);
        setCatSpinnerValue(0);
        return v;
    }

    public void setSortSpinnerValue(int index) {
        spinnerSortValue = index;

        sortSpinner.setSelection(index, true);
        if (hcsListener != null)
            hcsListener.onSortSpinnerChange(index);

    }

    public void setCatSpinnerValue(int index) {
        spinnerCatValue = index;

        sortSpinner.setSelection(index, true);
        if (hcsListener != null)
            hcsListener.onSortSpinnerChange(index);

    }

    public int getSortSpinnerValue() {
        return sortSpinner.getSelectedItemPosition();
    }

    public int getCatSpinnerValue() {
        return catSpinner.getSelectedItemPosition();
    }


    public void setSearchBoxText(String s){
        hcsSearchView.setQuery(s,true);
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

}