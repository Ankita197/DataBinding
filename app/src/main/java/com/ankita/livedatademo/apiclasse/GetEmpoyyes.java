package com.ankita.livedatademo.apiclasse;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

class GetEmpoyyes {

    @SerializedName("status")
    public String status;

    @SerializedName("data")
    public List<Datum> datumlist;

    public class Datum {
        @SerializedName("id")
        public String id;
        @SerializedName("employee_name")
        public String name;
        @SerializedName(" employee_salary")
        public String salary;
        @SerializedName("employee_age")
        public String age;


    }
}
