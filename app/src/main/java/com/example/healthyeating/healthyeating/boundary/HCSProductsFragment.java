package com.example.healthyeating.healthyeating.boundary;


import android.content.Context;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.util.Log;
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

    private static final int sortType = 0;

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

        //HCSListView(spinnerSortValue, hcsListView);

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
        catSpinner.setAdapter(catAdapter);
        sortSpinner.setAdapter(adapter);

        catSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (hcsListener != null) {
                    hcsListener.onCatSpinnerChange(pos);
                }
                spinnerCatValue = pos;
                HCSListView(pos, hcsListView);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (hcsListener != null) {
                    hcsListener.onSortSpinnerChange(pos);
                }
                spinnerSortValue = pos;
                HCSListView(pos, hcsListView);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //setCatSpinnerValue(0);
        //setSortSpinnerValue(0);



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

        catSpinner.setSelection(index, true);
        if (hcsListener != null)
            hcsListener.onCatSpinnerChange(index);

    }

    public int getSortSpinnerValue() {
        return sortSpinner.getSelectedItemPosition();
    }

    public int getCatSpinnerValue() {
        return catSpinner.getSelectedItemPosition();
    }


    public void setSearchBoxText(String s) {
        hcsSearchView.setQuery(s, true);
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



    public void HCSListView(int sortType, ListView hcsView) {
        //sortType = 0;
        ArrayList<HCSProducts> displayedHCSList = hcsListener.getAllHCSList(sortType);
        CustomHCSListAdapter hcsAdapter = new CustomHCSListAdapter((Context) hcsListener, R.layout.list_item_hcs, displayedHCSList);
        hcsView.setAdapter(hcsAdapter);
    }

    private class CustomHCSListAdapter extends ArrayAdapter<HCSProducts> {
        private int layout;
        private List<HCSProducts> hcsList;

        private CustomHCSListAdapter(Context context, int resource, List<HCSProducts> hcsList) {
            super(context, resource, hcsList);
            this.hcsList = hcsList;
            layout = resource;
        }
        /**
         * This method is for the building the list view for the HCS List View
         */
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHCSHolder mainViewHCSHolder = null;
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);
                ViewHCSHolder viewHCSHolder = new ViewHCSHolder();

                // establish links to layout elements
                viewHCSHolder.prodName = (TextView) convertView.findViewById(R.id.hcs_list_item_name);
                viewHCSHolder.prodDetails = (TextView) convertView.findViewById(R.id.hcs_list_item_details);

                convertView.setTag(viewHCSHolder);
            }

            mainViewHCSHolder = (ViewHCSHolder) convertView.getTag();

            HCSProducts prod = hcsList.get(position);
            // set variable text into text views
            mainViewHCSHolder.prodName.setText(prod.getProductName());
            mainViewHCSHolder.prodDetails.setText("Brand Name: " + getItem(position).getBrandName() + "\n" + "Weight: " + getItem(position).getProductWeight() + "\n" + "Company Name: " + getItem(position).getCompanyName() +"\n"+ "Category: " + getItem(position).getCategory());



            return convertView;
        }


        /**
         * This class is for the View that is used to display HCS list view
         */
        public class ViewHCSHolder {
            TextView prodName;    // Products's name
            TextView prodDetails; // Products's brand name, weight and company name
        }



    }
}