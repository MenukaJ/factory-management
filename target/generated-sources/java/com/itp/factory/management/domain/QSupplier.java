package com.itp.factory.management.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSupplier is a Querydsl query type for Supplier
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSupplier extends EntityPathBase<Supplier> {

    private static final long serialVersionUID = -1355947387L;

    public static final QSupplier supplier = new QSupplier("supplier");

    public final StringPath address = createString("address");

    public final StringPath contact = createString("contact");

    public final DateTimePath<java.sql.Timestamp> date = createDateTime("date", java.sql.Timestamp.class);

    public final ListPath<DeliveryInfo, QDeliveryInfo> deliveryInfo = this.<DeliveryInfo, QDeliveryInfo>createList("deliveryInfo", DeliveryInfo.class, QDeliveryInfo.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QSupplier(String variable) {
        super(Supplier.class, forVariable(variable));
    }

    public QSupplier(Path<? extends Supplier> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSupplier(PathMetadata metadata) {
        super(Supplier.class, metadata);
    }

}

