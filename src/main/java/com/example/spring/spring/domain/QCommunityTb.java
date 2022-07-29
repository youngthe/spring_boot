package com.example.spring.spring.domain;

import com.example.spring.spring.dao.CommunityTb;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.StringPath;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

public class QCommunityTb extends EntityPathBase<CommunityTb> {
    public static final QCommunityTb CommunityTb = new QCommunityTb("Community");
    public final StringPath id = createString("id");
    public final StringPath title = createString("title");
    public final StringPath content = createString("content");
    public final StringPath file_name = createString("file_name");
    public final StringPath writer = createString("writer");
    public final StringPath date = createString("date");
    public final StringPath hits = createString("hits");


    public QCommunityTb(String variable) {

        super(CommunityTb.class, forVariable(variable));

    }

}

