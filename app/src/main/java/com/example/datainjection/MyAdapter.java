package com.example.datainjection;

import android.content.ClipData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datainjection.model.Karlsruhe;
import com.example.datainjection.databinding.ItemlistBinding;
import java.util.ArrayList;



public class MyAdapter extends RecyclerView.Adapter<MyAdapter.KarlViewHolder>{

    private OnItemClickListener listener;
    private ArrayList<Karlsruhe> karlsruheArrayList= new ArrayList<>();

    @NonNull
    @Override
    public KarlViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemlistBinding itemlistBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.itemlist,
                parent,
                false);

        return new KarlViewHolder (itemlistBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull KarlViewHolder holder, int position) {
        Karlsruhe karlsruhe = karlsruheArrayList.get(position);
        holder.listItemBinding.setKarlsruhe(karlsruhe);

    }



    @Override
    public int getItemCount() {
        return null!=karlsruheArrayList?karlsruheArrayList.size():0;
    }


    class KarlViewHolder extends RecyclerView.ViewHolder{
        private ItemlistBinding listItemBinding;


        public KarlViewHolder(@NonNull ItemlistBinding listItemBinding) {
            super(listItemBinding.getRoot());
            this.listItemBinding = listItemBinding;

            listItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int clickedPosition = getAdapterPosition();

                    if (listener!= null && clickedPosition != RecyclerView.NO_POSITION){
                        listener.onItemClick(karlsruheArrayList.get(clickedPosition));
                    }
                }
            });




        }
    }

    public interface OnItemClickListener{
        void onItemClick(Karlsruhe karlsruhe);
    }

    public void setListener(OnItemClickListener listener){
        this.listener = listener;
    }


    public void setKarl (ArrayList<Karlsruhe> newKarl) {

        final DiffUtil.DiffResult result = DiffUtil.calculateDiff
                (new CallBackKaan(karlsruheArrayList, newKarl),false);

        karlsruheArrayList = newKarl;
        result.dispatchUpdatesTo(MyAdapter.this);

    }
}
