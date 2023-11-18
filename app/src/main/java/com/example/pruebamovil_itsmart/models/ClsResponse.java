package com.example.pruebamovil_itsmart.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

public class ClsResponse implements Parcelable {
    private List<ClsCount> total;
    private List<ClsClientes> clientes;

    //    --------------------------------------------------------------------
    protected ClsResponse(Parcel in) {
        clientes = in.createTypedArrayList(ClsClientes.CREATOR);
    }

    public static final Creator<ClsResponse> CREATOR = new Creator<ClsResponse>() {
        @Override
        public ClsResponse createFromParcel(Parcel in) {
            return new ClsResponse(in);
        }

        @Override
        public ClsResponse[] newArray(int size) {
            return new ClsResponse[size];
        }
    };
    //    --------------------------------------------------------------------
    public ClsResponse(List<ClsCount> total, List<ClsClientes> clientes) {
        this.total = total;
        this.clientes = clientes;
    }
    public List<ClsCount> getTotal() {
        return total;
    }

    public void setTotal(List<ClsCount> total) {
        this.total = total;
    }

    public List<ClsClientes> getClientes() {
        return clientes;
    }

    public void setClientes(List<ClsClientes> clientes) {
        this.clientes = clientes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeTypedList(clientes);
    }
}
