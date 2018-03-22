package com.web.sj.domain;

public class Image {
	
	public String id;
	public String link;
	public String imageIdentificationInDB;
	public String deletehash;
	
	public Image() {
		super();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getImageIdentificationInDB() {
		return imageIdentificationInDB;
	}
	public void setImageIdentificationInDB(String imageIdentificationInDB) {
		this.imageIdentificationInDB = imageIdentificationInDB;
	}
	public String getDeletehash() {
		return deletehash;
	}
	public void setDeletehash(String deletehash) {
		this.deletehash = deletehash;
	}
	
	
}
