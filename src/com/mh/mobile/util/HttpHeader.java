package com.mh.mobile.util;

/**
 * 
 * @author Jason Yao
 *
 */
public class HttpHeader {

	/**
	 * HTTP Header key "Content-Type"
	 */
	public static final String CONTENT_TYPE = "Content-Type";
	/**
	 * HTTP Header key "Authorization"
	 */
	public static final String AUTHORIZATION = "Authorization";

	/**
	 * HTTP Header key name
	 */
	private final String key;
	/**
	 * HTTP Header key value
	 */
	private final String value;

	/**
	 * List of content type
	 */
	public enum ContentType {
		APPLICATION_JSON("application/json"), 
		APPLICATION_XML("application/xml"), 
		TEXT_PLAIN("text/plain"), 
		TEXT_HTML("text/html"),
		FORM("application/x-www-form-urlencoded");

		/**
		 * The actual content type value
		 */
		private String contentType;

		/**
		 * Constructor
		 * @param contentType
		 */
		private ContentType(final String contentType) {
			this.contentType = contentType;
		}

		/**
		 * Get the actual content type value
		 * @return
		 */
		public String getContentType() {
			return this.contentType;
		}
	}

	/**
	 * Constructor
	 * 
	 * @param key
	 * @param value
	 */
	public HttpHeader(final String key, final String value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * Get HTTP Header key
	 * 
	 * @return
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Get HTTP Header key value
	 * 
	 * @return
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * Get the header list that contains Content-Type:application/json
	 * @return
	 */
	public static HttpHeader[] getJsonHeader() {
		return new HttpHeader[]{new HttpHeader(CONTENT_TYPE, ContentType.APPLICATION_JSON.getContentType())};
	}
	
	/**
	 * Get the header list that contains Content-Type:application/xml
	 * @return
	 */
	public static HttpHeader[] getXmlHeader() {
		return new HttpHeader[]{new HttpHeader(CONTENT_TYPE, ContentType.APPLICATION_XML.getContentType())};
	}

	/**
	 * Get the header list that contains Content-Type:application/x-www-form-urlencoded
	 * @return
	 */
	public static HttpHeader[] getFormHeader() {
		return new HttpHeader[]{new HttpHeader(CONTENT_TYPE, ContentType.FORM.getContentType())};
	}

}

