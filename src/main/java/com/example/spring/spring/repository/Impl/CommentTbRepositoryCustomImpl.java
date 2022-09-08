package com.example.spring.spring.repository.Impl;

import com.example.spring.spring.dao.CommentTb;
import com.example.spring.spring.dao.CommunityTb;
import com.example.spring.spring.domain.QCommentTb;
import com.example.spring.spring.domain.QCommunityTb;
import com.example.spring.spring.repository.CommentTbRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.transaction.Transactional;
import java.util.List;

public class CommentTbRepositoryCustomImpl extends QuerydslRepositorySupport implements CommentTbRepositoryCustom {


    @Autowired
    JPAQueryFactory query;

    public CommentTbRepositoryCustomImpl() {
        super(CommentTb.class);
    }


    @Override
    public List<CommentTb> getCommentList(int Community_id){

        QCommentTb qCommentTb = QCommentTb.comments;

        return query
                .selectFrom(qCommentTb)
                .where(qCommentTb.comment_id.eq(Community_id))
                .fetch();

    }

    @Override
    @Transactional
    public void deleteByCommunityId(int comment_id){

        QCommentTb qCommentTb = QCommentTb.comments;

        query.delete(qCommentTb).where(qCommentTb.comment_id.eq(comment_id)).execute();
    }

}
