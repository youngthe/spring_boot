package com.example.spring.spring.repository;


import com.example.spring.spring.dao.CommentTb;

import java.util.List;

public interface CommentTbRepositoryCustom {

    public List<CommentTb> getCommentList(int Community_id);
    public void deleteByCommunityId(int Community_id);
}
