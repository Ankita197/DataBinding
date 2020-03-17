package com.ankita.livedatademo.apiclasse;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CreateResponse {
    @SerializedName("status")
    public String status;

    @SerializedName("data")
    public Datum datum;

    public class Datum {
        @SerializedName("name")
        public String name;
        @SerializedName("salary")
        public String salary;
        @SerializedName("age")
        public String age;
        @SerializedName("id")
        public int id;

    }
}
