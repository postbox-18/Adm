package com.example.adm.Fragments.Control_Panel.AdminUsers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adm.Classes.CheckPhoneNumber;
import com.example.adm.Classes.MyLog;
import com.example.adm.R;
import com.example.adm.ViewModel.GetViewModel;

import java.util.ArrayList;
import java.util.List;

public class AdapterAdminUsers extends RecyclerView.Adapter<AdapterAdminUsers.ViewHolder> {
    private List<AdminUsersLists> adminUsersLists = new ArrayList<>();

    private Context context;
    private String TAG = "AdapterPhoneNumberControlPanel";
    private GetViewModel getViewModel;


    public AdapterAdminUsers(Context context, GetViewModel getViewModel, List<AdminUsersLists> adminUsersLists) {
        this.context = context;
        this.getViewModel = getViewModel;
        this.adminUsersLists = adminUsersLists;
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


        //holder.switchView.setChecked(false);


        /*holder.switchView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked())
                {
                    MyLog.e(TAG, "switch>>get enabled"+b );
                    holder.phone_number.setTextColor(context.getResources().getColor(R.color.colorSecondary));
                }
                else
                {
                    MyLog.e(TAG, "switch>>get not enabled" +b);
                    holder.phone_number.setTextColor(context.getResources().getColor(R.color.light_gray));

                }
                //selectedHeaderMap.get(header_title).get(position).setSelected(String.valueOf(b));
                getViewModel.updatePhoneNumberItem(phone_number, checkPhoneNumberList.get(position).getPhone_number(),String.valueOf(b), null);



            }
        });*/
    }


    @Override
    public int getItemCount() {
        MyLog.e(TAG, "item>>49>>" + adminUsersLists.size());
        return adminUsersLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView profile;
        private TextView user_name, email, phone_number;

        public ViewHolder(View view) {
            super(view);
            profile = view.findViewById(R.id.profile);
            user_name = view.findViewById(R.id.user_name);
            email = view.findViewById(R.id.email);
            phone_number = view.findViewById(R.id.phone_number);


        }
    }
}

