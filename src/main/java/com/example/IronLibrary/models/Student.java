package com.example.IronLibrary.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Student {

    @Id
    private String usn;
    private String name;
    @OneToOne(mappedBy = "issueStudent")
    private Issue issueStudentId;

    public Student() {
    }

    public Student(String usn, String data) {
        this.usn = usn;
        this.name = data;
    }

    public Student(String usn, String data, Issue issueStudentId) {
        this.usn = usn;
        this.name = data;
        this.issueStudentId = issueStudentId;
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

    public Issue getIssueStudentId() {
        return issueStudentId;
    }

    public void setIssueStudentId(Issue issueStudentId) {
        this.issueStudentId = issueStudentId;
    }

}
