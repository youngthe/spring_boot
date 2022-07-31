package com.example.spring.spring.repository;

import com.example.spring.spring.dao.CommunityTb;

import java.util.List;

public interface CommunityTbRepositoryCustom {

    public List<CommunityTb> getCommunity();

    public CommunityTb getCommunity_detail(long num);

}


