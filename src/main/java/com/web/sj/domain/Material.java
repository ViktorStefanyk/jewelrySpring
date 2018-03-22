package com.web.sj.domain;

public class Material {
	
	public Integer materialId;
	public String materialName;
	public String materialLink;
	public Integer materialQuantity;
	
	public Material() {
		super();
	}
	public Integer getMaterialId() {
		return materialId;
	}
	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getMaterialLink() {
		return materialLink;
	}
	public void setMaterialLink(String materialLink) {
		this.materialLink = materialLink;
	}
	public Integer getMaterialQuantity() {
		return materialQuantity;
	}
	public void setMaterialQuantity(Integer materialQuantity) {
		this.materialQuantity = materialQuantity;
	}
	
	

}
