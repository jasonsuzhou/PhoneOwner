package com.mh.mobile.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * 
 * @author Jason Yao
 *
 */
public final class HttpClient {

	public static final int DEF_CONN_TIMEOUT = 30000;
	public static final int DEF_READ_TIMEOUT = 30000;

	/**
	 * Constructor
	 */
	private HttpClient() {
	}

	/**
	 * Send POST GET Request using HTTPS without http header
	 * 
	 * @param requestUrl
	 *            - url must start with https://
	 * @param data
	 *            - will write to the request input stream
	 * @return
	 */
	public static String sendPostRquestSSL(final String requestUrl, final String data) {
		return sendPostRquestSSL(requestUrl, data, null);
	}

	/**
	 * Send HTTP POST Request using HTTPS with HTTP header defined
	 * 
	 * @param requestUrl
	 *            - url must start with https://
	 * @param data
	 *            - will write to the request input stream
	 * @param headers
	 *            - {@link HttpHeader}
	 * @return
	 */
	public static String sendPostRquestSSL(final String requestUrl, final String data, final HttpHeader[] headers) {
		StringBuilder sb = new StringBuilder("");
		InputStream inputStream = null;
		BufferedReader bufferedReader = null;
		InputStreamReader inputStreamReader = null;
		HttpsURLConnection urlConnection = null;
		try {
			URL url = new URL(requestUrl);
			urlConnection = (HttpsURLConnection) url.openConnection();
			urlConnection.setConnectTimeout(DEF_CONN_TIMEOUT);
			urlConnection.setReadTimeout(DEF_READ_TIMEOUT);
			urlConnection.setDoOutput(true);
			urlConnection.setDoInput(true);
			urlConnection.setUseCaches(false);
			urlConnection.setRequestMethod("POST");
			if (headers != null) {
				for (HttpHeader header : headers) {
					urlConnection.addRequestProperty(header.getKey(), header.getValue());
				}
			}
			OutputStream outputStream = urlConnection.getOutputStream();
			OutputStreamWriter writer = new OutputStreamWriter(outputStream);
			writer.write(data);
			writer.flush();
			writer.close();
			int responseCode = urlConnection.getResponseCode();
			if (200 == responseCode) {
				inputStream = urlConnection.getInputStream();
			} else {
				inputStream = urlConnection.getErrorStream();
			}
			inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
			bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				sb.append(str);
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (inputStreamReader != null) {
					inputStreamReader.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (urlConnection != null) {
				urlConnection.disconnect();
			}
		}
		return "";
	}

	/**
	 * Send HTTP POST Request using HTTPS with HTTP header defined
	 * 
	 * @param requestUrl
	 *            - url must start with http://
	 * @param data
	 *            - will write to the request input stream
	 * @param headers
	 *            - {@link HttpHeader}
	 * @return
	 */
	public static String sendPostRquest(final String requestUrl, final String data, final HttpHeader[] headers) {
		StringBuilder sb = new StringBuilder("");
		InputStream inputStream = null;
		BufferedReader bufferedReader = null;
		InputStreamReader inputStreamReader = null;
		HttpURLConnection urlConnection = null;
		try {
			URL url = new URL(requestUrl);
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setConnectTimeout(DEF_CONN_TIMEOUT);
			urlConnection.setReadTimeout(DEF_READ_TIMEOUT);
			urlConnection.setDoOutput(true);
			urlConnection.setDoInput(true);
			urlConnection.setUseCaches(false);
			urlConnection.setRequestMethod("POST");
			if (headers != null) {
				for (HttpHeader header : headers) {
					urlConnection.addRequestProperty(header.getKey(), header.getValue());
				}
			}
			OutputStream outputStream = urlConnection.getOutputStream();
			OutputStreamWriter writer = new OutputStreamWriter(outputStream);
			writer.write(data);
			writer.flush();
			writer.close();
			int responseCode = urlConnection.getResponseCode();
			if (200 == responseCode) {
				inputStream = urlConnection.getInputStream();
			} else {
				inputStream = urlConnection.getErrorStream();
			}
			inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
			bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				sb.append(str);
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (inputStreamReader != null) {
					inputStreamReader.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (urlConnection != null) {
				urlConnection.disconnect();
			}
		}
		return "";
	}

	public static String sendPostRquest(final String requestUrl, final String data, final HttpHeader[] headers,
			String encoding) {
		StringBuilder sb = new StringBuilder("");
		InputStream inputStream = null;
		BufferedReader bufferedReader = null;
		InputStreamReader inputStreamReader = null;
		HttpURLConnection urlConnection = null;
		try {
			URL url = new URL(requestUrl);
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setConnectTimeout(DEF_CONN_TIMEOUT);
			urlConnection.setReadTimeout(DEF_READ_TIMEOUT);
			urlConnection.setDoOutput(true);
			urlConnection.setDoInput(true);
			urlConnection.setUseCaches(false);
			urlConnection.setRequestMethod("POST");
			if (headers != null) {
				for (HttpHeader header : headers) {
					urlConnection.addRequestProperty(header.getKey(), header.getValue());
				}
			}
			OutputStream outputStream = urlConnection.getOutputStream();
			OutputStreamWriter writer = new OutputStreamWriter(outputStream);
			writer.write(data);
			writer.flush();
			writer.close();
			int responseCode = urlConnection.getResponseCode();
			if (200 == responseCode) {
				inputStream = urlConnection.getInputStream();
			} else {
				inputStream = urlConnection.getErrorStream();
			}
			inputStreamReader = new InputStreamReader(inputStream, encoding);
			bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				sb.append(str);
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (inputStreamReader != null) {
					inputStreamReader.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (urlConnection != null) {
				urlConnection.disconnect();
			}
		}
		return "";
	}

}