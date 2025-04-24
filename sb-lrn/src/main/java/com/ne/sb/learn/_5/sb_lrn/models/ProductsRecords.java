package com.ne.sb.learn._5.sb_lrn.models;

import java.util.List;

public class ProductsRecords {
    public record ReviewsItem(String date, String reviewerName, String reviewerEmail, Integer rating, String comment) { }

    public record Dimensions(Float depth, Float width, Float height) { }

    public record Meta(String createdAt, String qrCode, String barcode, String updatedAt) { }

    public record Products(List<String> images, String thumbnail, Integer minimumOrderQuantity, Integer rating, String returnPolicy,
                               String description, Integer weight, String warrantyInformation, String title, List<String> tags,
                               Float discountPercentage, List<ReviewsItem> reviews, Float price, Meta meta, String shippingInformation,
                               Integer id, String availabilityStatus, String category, Integer stock, String sku, String brand,
                               Dimensions dimensions) { }
}
