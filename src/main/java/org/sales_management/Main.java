package org.sales_management;

import org.sales_management.entity.ProductTypeEntity;
import org.sales_management.entity.ShopEntity;
import org.sales_management.service.InventoryService;
import org.sales_management.service.ProductTypeService;
import org.sales_management.service.ShopService;

public class Main {
    public static void main(String[] args) {
        InventoryService inventoryService = new InventoryService();
        ProductTypeService productTypeService = new ProductTypeService();
        ProductTypeEntity product = productTypeService.getById(1L);
        ShopService shopService = new ShopService();
        ShopEntity shop = shopService.getById(1L);
//        System.out.println(inventoryService.shareProducts(product,shop,2).getName());
    }
}