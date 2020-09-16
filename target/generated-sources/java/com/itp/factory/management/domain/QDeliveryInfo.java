package com.itp.factory.management.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDeliveryInfo is a Querydsl query type for DeliveryInfo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDeliveryInfo extends EntityPathBase<DeliveryInfo> {

    private static final long serialVersionUID = 2045624059L;

    public static final QDeliveryInfo deliveryInfo = new QDeliveryInfo("deliveryInfo");

    public final NumberPath<Double> amount = createNumber("amount", Double.class);

    public final DateTimePath<java.sql.Timestamp> deliveryDate = createDateTime("deliveryDate", java.sql.Timestamp.class);

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath loadingPlace = createString("loadingPlace");

    public final StringPath materials = createString("materials");

    public final StringPath name = createString("name");

    public final NumberPath<Double> price = createNumber("price", Double.class);

    public final NumberPath<Integer> qty = createNumber("qty", Integer.class);

    public final NumberPath<Double> rate = createNumber("rate", Double.class);

    public final StringPath remarks = createString("remarks");

    public final NumberPath<Long> sid = createNumber("sid", Long.class);

    public final StringPath vehicleNo = createString("vehicleNo");

    public QDeliveryInfo(String variable) {
        super(DeliveryInfo.class, forVariable(variable));
    }

    public QDeliveryInfo(Path<? extends DeliveryInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDeliveryInfo(PathMetadata metadata) {
        super(DeliveryInfo.class, metadata);
    }

}

