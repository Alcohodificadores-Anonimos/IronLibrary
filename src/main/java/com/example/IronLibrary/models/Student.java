package com.example.IronLibrary.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Student {

    @Id
    private String usn;
    private String name;
    @OneToMany(mappedBy = "issueStudent", fetch = FetchType.EAGER)
    private List<Issue> issueList;

    public Student() {
    }

    public Student(String usn, String data) {
        this.usn = usn;
        this.name = data;
    }

    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Issue> getissueList() {
        return issueList;
    }

    public void setissueList(List<Issue> issueList) {
        this.issueList = issueList;
    }

}
