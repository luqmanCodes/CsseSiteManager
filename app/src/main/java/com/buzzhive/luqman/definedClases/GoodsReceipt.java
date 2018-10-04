/**
 * GoodsReceipt Table Model
 */
package com.buzzhive.luqman.definedClases;

public class GoodsReceipt {

	private int id;
	private int purchaseOrderId;
	private int supplierId;
	private String status;
	private String goodsReceiptComment;
	private String issueDate;
	
	public int getId() {
		return id;
	}
	public int getPurchaseOrderId() {
		return purchaseOrderId;
	}
	public int getSupplierId() {
		return supplierId;
	}
	public String getStatus() {
		return status;
	}
	public String getGoodsReceiptComment() {
		return goodsReceiptComment;
	}

	public void setId(int id) {
		this.id = id;
	}
	public void setPurchaseOrderId(int purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setGoodsReceiptComment(String goodsReceiptComment) {
		this.goodsReceiptComment = goodsReceiptComment;
	}

	
}
