package com.example.adm.Fragments.Orders;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adm.Classes.SessionList;
import com.example.adm.Fragments.Orders.BottomSheet.OrderItemLists;
import com.example.adm.Fragments.Orders.BottomSheet.SelectedHeader;
import com.example.adm.Fragments.Orders.BottomSheet.ViewCartAdapter;

import com.example.adm.Fragments.Orders.BottomSheet.ViewCartAdapterHeader;
import com.example.adm.R;
import com.example.adm.ViewModel.GetViewModel;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ViewCartAdapterSession extends RecyclerView.Adapter<ViewCartAdapterSession.ViewHolder> {
    private Context context;
    private List<SessionList> sessionLists = new ArrayList<>();
    private String TAG = "ViewCartAdapterSession";
    private GetViewModel getViewModel;


    public ViewCartAdapterSession(Context context, GetViewModel getViewModel, List<SessionList> sessionLists) {
        this.context=context;
        this.getViewModel=getViewModel;
        this.sessionLists=sessionLists;
    }



    @NonNull
    @Override
    public ViewCartAdapterSession.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.bottom_sheet_order_session, parent, false);
        return new ViewCartAdapterSession.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewCartAdapterSession.ViewHolder holder, int position) {
        final SessionList list=sessionLists.get(position);
        holder.session_title.setText(list.getSession_title());

        //get selected header list
        getViewModel.getSelectedHeadersMutableLiveData().observe((LifecycleOwner) context, new Observer<List<SelectedHeader>>() {
            @Override
            public void onChanged(List<SelectedHeader> selectedHeaders) {
                holder.recyclerview_order_item_details.setHasFixedSize(true);
                holder.recyclerview_order_item_details.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                ViewCartAdapterHeader viewCartAdapter=new ViewCartAdapterHeader(context,getViewModel,selectedHeaders);
                holder.recyclerview_order_item_details.setAdapter(viewCartAdapter);
            }
        });



    }


    @Override
    public int getItemCount() {
        return sessionLists.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView session_title;
        private RecyclerView recyclerview_order_item_details;


        public ViewHolder(View view) {
            super(view);
            recyclerview_order_item_details = view.findViewById(R.id.recyclerview_order_item_details);
            session_title = view.findViewById(R.id.session_title);

        }
    }
}
