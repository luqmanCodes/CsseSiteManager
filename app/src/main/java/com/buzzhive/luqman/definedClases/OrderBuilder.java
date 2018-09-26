package com.buzzhive.luqman.definedClases;

public class OrderBuilder {
    private PurchaseOrder purchaseOrder;
    public OrderBuilder addSiteManager(String siteManagerId) {
        this.purchaseOrder.setSiteManagerId(siteManagerId);
        return this;
    }
    public OrderBuilder addItem(Item item) {
        this.purchaseOrder.addItem(item);
        return this;
    }
    public OrderBuilder removeItem(int arrayId) {
        this.purchaseOrder.removeItem(arrayId);
        return this;
    }
    public PurchaseOrder buildPurchaseOrderAsObject() throws Exception {

        if(this.purchaseOrder.getSiteManagerId() != null && this.purchaseOrder.getItems().size() != 0)
            return this.purchaseOrder;
        else {
            throw new Exception("Invalid");
        }
    }
}
