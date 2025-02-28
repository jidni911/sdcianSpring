package com.jidnivai.sdcian.sdcian.enums;

public enum OrderStatus {
    PENDING(0),
    PROCESSING(1),
    OUT_FOR_DELIVERY(2),
    COMPLETED(3),
    REJECTED(4),
    CANCELLED(5),
    REFUSED(6);

    private final int value;

    OrderStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
