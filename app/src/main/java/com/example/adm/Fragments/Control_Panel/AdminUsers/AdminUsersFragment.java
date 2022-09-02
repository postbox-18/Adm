package com.example.adm.Fragments.Control_Panel.AdminUsers;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adm.Classes.CheckPhoneNumber;
import com.example.adm.R;
import com.example.adm.ViewModel.GetViewModel;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminUsersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminUsersFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private RecyclerView recyclerview_phone_number;
    private List<CheckPhoneNumber> checkPhoneNumbers=new ArrayList<>();
    private AdapterAdminUsersPhonenumbers adapterAdminUsersPhonenumbers;
    private GetViewModel getViewModel;
    private String TAG="AdminUsersFragment";


    public AdminUsersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdminUsersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdminUsersFragment newInstance(String param1, String param2) {
        AdminUsersFragment fragment = new AdminUsersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_admin_users, container, false);
        getViewModel = new ViewModelProvider(getActivity()).get(GetViewModel.class);


         recyclerview_phone_number=view.findViewById(R.id.recyclerview_phone_number);
        recyclerview_phone_number.setHasFixedSize(true);
        recyclerview_phone_number.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));


        getViewModel.getAdminUsersMapLiveDAta().observe(getViewLifecycleOwner(), new Observer<LinkedHashMap<String, List<AdminUsersLists>>>() {
            @Override
            public void onChanged(LinkedHashMap<String, List<AdminUsersLists>> stringListLinkedHashMap) {
                Set<String> stringSet = stringListLinkedHashMap.keySet();
                List<String> aList = new ArrayList<String>(stringSet.size());
                for (String x : stringSet)
                    aList.add(x);
                checkPhoneNumbers=new ArrayList<>();

                for(int i=0;i<aList.size();i++)
                {
                    CheckPhoneNumber checkPhoneNumber=new CheckPhoneNumber(
                            aList.get(i)
                    );
                    checkPhoneNumbers.add(checkPhoneNumber);
                }
                adapterAdminUsersPhonenumbers=new AdapterAdminUsersPhonenumbers(getContext(),getViewModel,checkPhoneNumbers,stringListLinkedHashMap);
                recyclerview_phone_number.setAdapter(adapterAdminUsersPhonenumbers);
            }
        });
       



        return view;

    }
}