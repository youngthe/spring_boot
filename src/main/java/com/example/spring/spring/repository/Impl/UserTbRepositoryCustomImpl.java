package com.example.spring.spring.repository.Impl;

import com.example.spring.spring.dao.UserTb;
import com.example.spring.spring.domain.QUserTb;
import com.example.spring.spring.repository.UserTbRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class UserTbRepositoryCustomImpl extends QuerydslRepositorySupport implements UserTbRepositoryCustom {

    @Autowired
    JPAQueryFactory query;

    public UserTbRepositoryCustomImpl() {
        super(UserTb.class);
    }

    public boolean LoginCheck(UserTb userTb){

        QUserTb qusertb = QUserTb.user;

        if(query.selectFrom(qusertb)
                .where(qusertb.id.eq(userTb.getId()))
                .where(qusertb.pw.eq(userTb.getPw()))
                .fetch().isEmpty()){

            return false;
        }else{
            return true;
        }

    }
}
