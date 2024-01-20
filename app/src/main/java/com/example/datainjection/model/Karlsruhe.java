package com.example.datainjection.model;

import static androidx.room.ForeignKey.CASCADE;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.datainjection.BR;

import java.util.Objects;


@Entity(tableName = "my_table", foreignKeys = @ForeignKey(entity = Category.class,
        parentColumns = "id",childColumns = "category_id", onDelete = CASCADE))

public class Karlsruhe extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "karl_id")
    private int karlId;

    @ColumnInfo(name = "karl_name")
    private String karlName;

    @ColumnInfo(name = "unit_price")
    private String unitPrice;

    @ColumnInfo(name = "category_id")
    private int categoryId;


    @Ignore
    public Karlsruhe() {
    }

    public Karlsruhe(int karlId, String karlName, String unitPrice, int categoryId) {
        this.karlId = karlId;
        this.karlName = karlName;
        this.unitPrice = unitPrice;
        this.categoryId = categoryId;
    }


    @Bindable
    public int getKarlId() {
        return karlId;
    }

    public void setKarlId(int karlId) {
        this.karlId = karlId;
        notifyPropertyChanged(BR.karlId);

    }

    @Bindable
    public String getKarlName() {
        return karlName;
    }

    public void setKarlName(String karlName) {
        this.karlName = karlName;
        notifyPropertyChanged(BR.karlName);
    }

    @Bindable
    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
        notifyPropertyChanged(BR.unitPrice);
    }

    @Bindable
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
        notifyPropertyChanged(BR.categoryId);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Karlsruhe karlsruhe = (Karlsruhe) o;
        return  karlId == karlsruhe.karlId
                && categoryId == karlsruhe.categoryId
                && karlName.equals(karlsruhe.karlName)
                && unitPrice.equals(karlsruhe.unitPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(karlId, karlName, unitPrice, categoryId);
    }
}
