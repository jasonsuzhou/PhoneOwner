package com.mh.mobile.bean;

public class PhoneOwner {
	/**
	 * 号码段
	 */
	private String section;
	/**
	 * 运营商
	 */
	private String vendor;
	/**
	 * 省份
	 */
	private String province;
	/**
	 * 城市
	 */
	private String city;
	/**
	 * 邮编
	 */
	private String zipCode;
	/**
	 * 区号
	 */
	private String areaCode;

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		if (vendor != null) {
			if (vendor.contains("联通")) {
				this.vendor = "中国联通";
			} else if (vendor.contains("移动")) {
				this.vendor = "中国移动";
			} else if (vendor.contains("电信")) {
				this.vendor = "中国电信";
			} else {
				this.vendor = vendor;
			}
		}
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

}
