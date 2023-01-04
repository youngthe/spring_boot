package com.example.spring.spring.dao;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserTb is a Querydsl query type for UserTb
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserTb extends EntityPathBase<UserTb> {

    private static final long serialVersionUID = -1824826042L;

    public static final QUserTb userTb = new QUserTb("userTb");

    public final StringPath id = createString("id");

    public final NumberPath<Integer> num = createNumber("num", Integer.class);

    public final StringPath pw = createString("pw");

    public QUserTb(String variable) {
        super(UserTb.class, forVariable(variable));
    }

    public QUserTb(Path<? extends UserTb> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserTb(PathMetadata metadata) {
        super(UserTb.class, metadata);
    }

}

