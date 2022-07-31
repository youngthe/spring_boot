package com.example.spring.spring.repository.Impl;

import com.example.spring.spring.dao.CommunityTb;
import com.example.spring.spring.domain.QCommunityTb;
import com.example.spring.spring.repository.CommunityTbRepositoryCustom;
import com.example.spring.spring.repository.UserTbRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class CommunityTbRepositoryCustomImpl extends QuerydslRepositorySupport implements CommunityTbRepositoryCustom {


    @Autowired
    JPAQueryFactory query;

    public CommunityTbRepositoryCustomImpl() {
        super(CommunityTb.class);
    }

    @Override
    public List<CommunityTb> getCommunity() {

        QCommunityTb qCommunityTb = QCommunityTb.CommunityTb;

        return query
                .selectFrom(qCommunityTb)
                .fetch();
    }

    @Override
    public CommunityTb getCommunityById(long id){

        QCommunityTb qCommunityTb = QCommunityTb.CommunityTb;

        return query
                .selectFrom(qCommunityTb)
                .where(qCommunityTb.id.eq(id))
                .fetchFirst();

    }

}
