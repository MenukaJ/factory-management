package com.itp.factory.management.core;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseException is a Querydsl query type for BaseException
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QBaseException extends EntityPathBase<BaseException> {

    private static final long serialVersionUID = 543022912L;

    public static final QBaseException baseException = new QBaseException("baseException");

    public final StringPath errorCode = createString("errorCode");

    public final DateTimePath<java.util.Date> errorDate = createDateTime("errorDate", java.util.Date.class);

    public final StringPath errorDescription = createString("errorDescription");

    public final StringPath errorShortDescription = createString("errorShortDescription");

    public final EnumPath<org.springframework.http.HttpStatus> httpStatus = createEnum("httpStatus", org.springframework.http.HttpStatus.class);

    public final BooleanPath severity = createBoolean("severity");

    public QBaseException(String variable) {
        super(BaseException.class, forVariable(variable));
    }

    public QBaseException(Path<? extends BaseException> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseException(PathMetadata metadata) {
        super(BaseException.class, metadata);
    }

}

