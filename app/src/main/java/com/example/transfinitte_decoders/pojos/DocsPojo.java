package com.example.transfinitte_decoders.pojos;

import java.util.List;

public class DocsPojo {
    String Department;
    List<Docs> docs;

    public DocsPojo(String department, List<Docs> docs) {
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

    public void setDocs(List<Docs> docs) {
        this.docs = docs;
    }

    public DocsPojo() {
    }
}
