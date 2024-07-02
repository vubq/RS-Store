package com.vubq.rsstore.enums;

public enum EOrderStatus {
    HANGING_INVOICE, // Hóa đơn treo
    WAIT_FOR_CONFIRMATION, //Chờ xác nhận
    PREPARING_GOODS, // Đang chuẩn bị hàng
    SHIPPED, // Đã gửi hàng
    DELIVERING, // Đang giao
    DELIVERED, // Đã giao
    SUCCESS, // Thành công
    CANCELLED // Hủy
}
