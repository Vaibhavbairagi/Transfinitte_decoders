package com.example.transfinitte_decoders.pojos;

import java.util.ArrayList;

public class DepartmentsPojo {
    String docid;
    private ArrayList<String> Departments;

    public DepartmentsPojo(String docid, ArrayList<String> Departments) {
        this.docid = docid;
        this.Departments = Departments;
    }
    public DepartmentsPojo(String docid) {
        this.docid = docid;
        Departments= new ArrayList<>();
    }
    public DepartmentsPojo(){
        docid="PURANA";
        Departments= new ArrayList<>();
        Departments.add("");
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public ArrayList<String> getDepartments() {
        return Departments;
    }

    public void setDepartments(ArrayList<String> departments) {
        Departments = departments;
    }
}
