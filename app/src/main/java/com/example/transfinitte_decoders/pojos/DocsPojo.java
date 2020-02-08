package com.example.transfinitte_decoders.pojos;

import java.util.ArrayList;
import java.util.List;

public class DocsPojo {
    String Department;
    ArrayList<Docs> docs;

    public DocsPojo(String department, ArrayList<Docs> docs) {
        Department = department;
        this.docs = docs;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public List<Docs> getDocs() {
        return docs;
    }

    public void setDocs(ArrayList<Docs> docs) {
        this.docs = docs;
    }

    public DocsPojo() {
        docs = new ArrayList<>();
    }
}
