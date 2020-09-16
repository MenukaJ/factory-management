package com.itp.factory.management.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCategory is a Querydsl query type for Category
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCategory extends EntityPathBase<Category> {

    private static final long serialVersionUID = 357868983L;

    public static final QCategory category = new QCategory("category");

    public final com.itp.factory.management.core.QBaseEntity _super = new com.itp.factory.management.core.QBaseEntity(this);

    public final DateTimePath<java.sql.Timestamp> createdDate = createDateTime("createdDate", java.sql.Timestamp.class);

    public final StringPath createdUser = createString("createdUser");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final DateTimePath<java.sql.Timestamp> modifiedDate = createDateTime("modifiedDate", java.sql.Timestamp.class);

    public final StringPath modifiedUser = createString("modifiedUser");

    public final StringPath name = createString("name");

    public final EnumPath<com.itp.factory.management.enums.CommonStatus> status = createEnum("status", com.itp.factory.management.enums.CommonStatus.class);

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QCategory(String variable) {
        super(Category.class, forVariable(variable));
    }

    public QCategory(Path<? extends Category> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCategory(PathMetadata metadata) {
        super(Category.class, metadata);
    }

}

