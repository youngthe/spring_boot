package com.example.spring.spring.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "COMMENTS")
public class CommentTb {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "community_id")
    private long community_id;

    @Column(name = "comment")
    private String comment;

    @Column(name = "writer")
    private String writer;

    @Column(name = "Date")
    private String Date;

    public long getCommunity_id() {
        return community_id;
    }

    public void setCommunity_id(long community_id) {
        this.community_id = community_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        this.Date = date;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
