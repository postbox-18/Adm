package com.example.adm.Fragments.Control_Panel.AdminUsers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adm.Classes.CheckPhoneNumber;
import com.example.adm.Classes.MyLog;
import com.example.adm.R;
import com.example.adm.ViewModel.GetViewModel;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class AdapterAdminUsers extends RecyclerView.Adapter<AdapterAdminUsers.ViewHolder> {
    private List<AdminUsersLists> adminUsersLists = new ArrayList<>();

    private Context context;
    private String TAG = "AdapterPhoneNumberControlPanel";
    private GetViewModel getViewModel;
    private String phone_number;
    private LinkedHashMap<String, List<AdminUsersLists>> adminUsersMap = new LinkedHashMap<>();



    public AdapterAdminUsers(Context context, GetViewModel getViewModel, List<AdminUsersLists> adminUsersLists, String phone_number, LinkedHashMap<String, List<AdminUsersLists>> adminUsersMap) {
        this.context = context;
        this.getViewModel = getViewModel;
        this.phone_number = phone_number;
        this.adminUsersLists = adminUsersLists;
        this.adminUsersMap = adminUsersMap;
    }

    @NonNull
    @Override
    public AdapterAdminUsers.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.adminuserscontrolpaneladapter_cardview, parent, false);
        return new AdapterAdminUsers.ViewHolder(view);
        //return view;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAdminUsers.ViewHolder holder, int position) {
        final AdminUsersLists detailsList = adminUsersLists.get(position);
        holder.user_name.setText(detailsList.getUser_name());
        holder.email.setText(detailsList.getEmail());
        holder.phone_number.setText(detailsList.getPhone_number());

        if (detailsList.getPrimary().equals("true")) {
            holder.switchView.setChecked(true);
            holder.user_name.setTextColor(context.getResources().getColor(R.color.black));
            holder.email.setTextColor(context.getResources().getColor(R.color.black));
            holder.phone_number.setTextColor(context.getResources().getColor(R.color.black));
            holder.profile.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_chef));
        } else if (detailsList.getPrimary().equals("false")) {
            holder.user_name.setTextColor(context.getResources().getColor(R.color.text_silver));
            holder.email.setTextColor(context.getResources().getColor(R.color.text_silver));
            holder.profile.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_chef_silver));
            holder.phone_number.setTextColor(context.getResources().getColor(R.color.text_silver));
            holder.switchView.setChecked(false);
        }


        holder.switchView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    MyLog.e(TAG, "switch>>get enabled" + b);

                } else {
                    MyLog.e(TAG, "switch>>get not enabled" + b);


                }

                detailsList.setPrimary(String.valueOf(b));
                MyLog.e(TAG,"updatedSwitch>>AdminUsers>>"+new GsonBuilder().setPrettyPrinting().create().toJson(adminUsersLists));
                MyLog.e(TAG,"updatedSwitch>>adminUsersMap>>"+new GsonBuilder().setPrettyPrinting().create().toJson(adminUsersMap));

                getViewModel.UpdatedMasterAdminAccess(phone_number,detailsList,b);


            }
        });

    }


    @Override
    public int getItemCount() {
        MyLog.e(TAG, "item>>49>>" + adminUsersLists.size());
        return adminUsersLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView profile;
        private SwitchCompat switchView;
        private TextView user_name, email, phone_number;

        public ViewHolder(View view) {
            super(view);
            profile = view.findViewById(R.id.profile);
            user_name = view.findViewById(R.id.user_name);
            email = view.findViewById(R.id.email);
            phone_number = view.findViewById(R.id.phone_number);
            switchView = view.findViewById(R.id.switchView);


        }
    }
}

