package com.example.spring.spring.repository;

import com.example.spring.spring.dao.UserTb;
import com.example.spring.spring.domain.QUserTb;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
public class UserTbRepository{

    private final JPAQueryFactory queryFactory;

    public UserTbRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public UserTb LoginCheck(String id, String pw) {

        QUserTb quserTb = QUserTb.userTb;
        return queryFactory
                .selectFrom(quserTb)
                .where(quserTb.id.eq(id))
                .where(quserTb.pw.eq(pw))
                .fetchFirst();
    }

}
