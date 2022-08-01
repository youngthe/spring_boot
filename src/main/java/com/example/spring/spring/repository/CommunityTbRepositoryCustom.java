package com.example.spring.spring.repository;

import com.example.spring.spring.dao.CommunityTb;

import java.util.List;

public interface CommunityTbRepositoryCustom {

    public List<CommunityTb> getCommunity();
    public CommunityTb getCommunityById(int Community_id);
    public void hit_Community(CommunityTb communityTb);
}


