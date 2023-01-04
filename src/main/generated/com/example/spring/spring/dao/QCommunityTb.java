package com.example.spring.spring.dao;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCommunityTb is a Querydsl query type for CommunityTb
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCommunityTb extends EntityPathBase<CommunityTb> {

    private static final long serialVersionUID = -1586283862L;

    public static final QCommunityTb communityTb = new QCommunityTb("communityTb");

    public final StringPath content = createString("content");

    public final StringPath date = createString("date");

    public final StringPath file_name = createString("file_name");

    public final NumberPath<Integer> hits = createNumber("hits", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath title = createString("title");

    public final StringPath writer = createString("writer");

    public QCommunityTb(String variable) {
        super(CommunityTb.class, forVariable(variable));
    }

    public QCommunityTb(Path<? extends CommunityTb> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCommunityTb(PathMetadata metadata) {
        super(CommunityTb.class, metadata);
    }

}

