package com.example.adm.Fragments.Control_Panel.AdminUsers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adm.Classes.CheckPhoneNumber;
import com.example.adm.Classes.MyLog;
import com.example.adm.R;
import com.example.adm.ViewModel.GetViewModel;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class AdapterAdminUsersPhonenumbers extends RecyclerView.Adapter<AdapterAdminUsersPhonenumbers.ViewHolder> {
    private List<CheckPhoneNumber> checkPhoneNumberList=new ArrayList<>();
    private List<AdminUsersLists> adminUsersLists=new ArrayList<>();
    private Context context;
    private String TAG = "AdapterPhoneNumberControlPanel";
    private GetViewModel getViewModel;
    private AdapterAdminUsers adapterAdminUsers;

    private LinkedHashMap<String, List<AdminUsersLists>> adminUsersMap = new LinkedHashMap<>();


    public AdapterAdminUsersPhonenumbers(Context context, GetViewModel getViewModel, List<CheckPhoneNumber> checkPhoneNumberList, LinkedHashMap<String, List<AdminUsersLists>> adminUsersMap) {
        this.context = context;
        this.getViewModel = getViewModel;
        this.checkPhoneNumberList = checkPhoneNumberList;
        this.adminUsersMap = adminUsersMap;
    }

    @NonNull
    @Override
    public AdapterAdminUsersPhonenumbers.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.adminusersphonenumbercontrolpaneladapter_cardview, parent, false);
        return new AdapterAdminUsersPhonenumbers.ViewHolder(view);
        //return view;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAdminUsersPhonenumbers.ViewHolder holder, int position) {
        final CheckPhoneNumber item1 = checkPhoneNumberList.get(position);
        //holder.phone_number.setText(item1.getPhone_number());

        adminUsersLists=adminUsersMap.get(item1.getPhone_number());
        adapterAdminUsers=new AdapterAdminUsers(context,getViewModel,adminUsersLists,item1.getPhone_number(),adminUsersMap);
        holder.recyclerView_adminUsers.setHasFixedSize(true);
        holder.recyclerView_adminUsers.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        holder.recyclerView_adminUsers.setAdapter(adapterAdminUsers);





    }


    @Override
    public int getItemCount() {
        MyLog.e(TAG, "item>>49>>" + checkPhoneNumberList.size());
        return checkPhoneNumberList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView phone_number;
        private RecyclerView recyclerView_adminUsers;
        ;

        public ViewHolder(View view) {
            super(view);
            phone_number = view.findViewById(R.id.phone_number);
            recyclerView_adminUsers = view.findViewById(R.id.recyclerView_adminUsers);


        }
    }
}

