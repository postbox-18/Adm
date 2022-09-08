package com.example.adm.Fragments.Notification;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.adm.Classes.MyLog;
import com.example.adm.Fragments.Orders.BottomSheet.Classes.OrderDishLists;
import com.example.adm.Fragments.Orders.BottomSheet.Classes.OrderLists;
import com.example.adm.Fragments.Orders.BottomSheet.ViewCartAdapterDate;
import com.example.adm.Fragments.Orders.Classes.SelectedDateList;
import com.example.adm.Fragments.Orders.Classes.Username;
import com.example.adm.R;
import com.example.adm.ViewModel.GetViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotificationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private GetViewModel getViewModel;
    private RecyclerView recyclerview_notify_list;
    private List<NotifyList> notifyLists = new ArrayList<>();
    private AdaptersNotify adaptersNotify;

    //bottom sheet view
    private RecyclerView recyclerview_date_view;
    private TextView func_title, user_name;


    //order hash map
    //order map
    private LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, List<OrderDishLists>>>>>>> orderMap = new LinkedHashMap<>();
    //func map
    private LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, List<OrderDishLists>>>>>> orderFunc_Map = new LinkedHashMap<>();
    //Date map
    private LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, List<OrderDishLists>>>>> orderDateMap = new LinkedHashMap<>();
    //Session map
    private LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, List<OrderDishLists>>>> orderSessionMap = new LinkedHashMap<>();
    //Header map
    private LinkedHashMap<String, LinkedHashMap<String, List<OrderDishLists>>> orderHeaderMap = new LinkedHashMap<>();
    //Item map
    private LinkedHashMap<String, List<OrderDishLists>> orderItemMap = new LinkedHashMap<>();
    //order list
    private List<OrderLists> o_orderLists = new ArrayList<>();
    //user name list
    private List<Username> o_usernames=new ArrayList<>();
    //date list
    private List<SelectedDateList> o_dateLists=new ArrayList<>();
    private String TAG="NotificationFragment";

    public NotificationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotificationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NotificationFragment newInstance(String param1, String param2) {
        NotificationFragment fragment = new NotificationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getViewModel = new ViewModelProvider(getActivity()).get(GetViewModel.class);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        recyclerview_notify_list = view.findViewById(R.id.recyclerview_notify_list);
        recyclerview_notify_list.setHasFixedSize(true);
        recyclerview_notify_list.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));


        //get notify list
        getViewModel.getNotifyListsMutableData().observe(getViewLifecycleOwner(), new Observer<List<NotifyList>>() {
            @Override
            public void onChanged(List<NotifyList> notifyLists1) {
                notifyLists = notifyLists1;
                adaptersNotify = new AdaptersNotify(getContext(), getViewModel, notifyLists);
                recyclerview_notify_list.setAdapter(adaptersNotify);
            }
        });


        //Bottom sheet
        BottomSheetDialog bottomSheet = new BottomSheetDialog(requireContext());
        View bottom_view = LayoutInflater.from(getContext()).inflate(R.layout.bottom_sheet_order_details, null);
        func_title = bottom_view.findViewById(R.id.func_title);
        user_name = bottom_view.findViewById(R.id.user_name);
        recyclerview_date_view = bottom_view.findViewById(R.id.recyclerview_date_view);
        recyclerview_date_view.setHasFixedSize(true);
        recyclerview_date_view.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));


        //get order map
        getViewModel.getOrderMapMutableLiveData().observe(getViewLifecycleOwner(), new Observer<LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, List<OrderDishLists>>>>>>>>() {
            @Override
            public void onChanged(LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, List<OrderDishLists>>>>>>> stringLinkedHashMapLinkedHashMap) {
                orderMap = new LinkedHashMap<>(stringLinkedHashMapLinkedHashMap);
            }
        });


        //get selected Notification deatils
        getViewModel.getNotifyarrayLiveData().observe(getViewLifecycleOwner(), new Observer<NotifyList>() {
            @Override
            public void onChanged(NotifyList notifyList) {
                func_title.setText(notifyList.getFunction());
                user_name.setText(notifyList.getUsername());


                orderFunc_Map=new LinkedHashMap<>(orderMap).get(notifyList.getUsername()+"-"+notifyList.getPhone_number());


                orderDateMap=new LinkedHashMap<>(orderFunc_Map).get(notifyList.getFunction());
                o_dateLists=new ArrayList<>();
                SelectedDateList list=new SelectedDateList(
                        notifyList.getDate()
                );
                o_dateLists.add(list);


                ViewCartAdapterDate viewCartAdapterDate=new ViewCartAdapterDate(getContext(),getViewModel,orderDateMap,o_dateLists,notifyList.getUsername(),notifyList.getFunction());
                recyclerview_date_view.setAdapter(viewCartAdapterDate);
                bottomSheet.setContentView(bottom_view);
                bottomSheet.show();

            }
        });


        return view;
    }
}