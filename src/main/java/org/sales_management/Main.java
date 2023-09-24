package org.sales_management;

import org.sales_management.entity.PersonEntity;
import org.sales_management.entity.ProductEntity;
import org.sales_management.entity.ShopEntity;
import org.sales_management.service.InventoryService;
import org.sales_management.service.PersonService;
import org.sales_management.service.ProductService;
import org.sales_management.service.ShopService;

public class Main {
    public static void main(String[] args) {
        InventoryService inventoryService = new InventoryService();
        ProductService productService = new ProductService();
        ProductEntity product = productService.getById(1L);
        ShopService shopService = new ShopService();
        ShopEntity shop = shopService.getById(1L);
//        System.out.println(inventoryService.shareProducts(product,shop,2).getName());
    }
}