package com.example.datainjection;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.example.datainjection.model.Karlsruhe;


import java.util.ArrayList;

public class CallBackKaan extends DiffUtil.Callback {

    ArrayList<Karlsruhe> oldKarl;
    ArrayList<Karlsruhe> newKarl;

    public CallBackKaan(ArrayList<Karlsruhe> oldKarl, ArrayList<Karlsruhe> newKarl) {
        this.oldKarl = oldKarl;
        this.newKarl = newKarl;
    }

    @Override
    public int getOldListSize() {
        return oldKarl==null ? 0 : oldKarl.size();
    }

    @Override
    public int getNewListSize() {
        return newKarl==null ? 0 :  newKarl.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldKarl.get(oldItemPosition).getKarlId() ==
                newKarl.get(newItemPosition).getKarlId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldKarl.get(oldItemPosition).equals( newKarl.get(newItemPosition));
    }


    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
