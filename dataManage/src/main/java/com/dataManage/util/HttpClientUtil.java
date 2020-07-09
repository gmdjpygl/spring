package com.dataManage.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {
	public static String doHttpPost(String url, String json) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String msg = "";
		try {
			HttpPost post = new HttpPost(url);
			// 设置发送消息的参数
			StringEntity entity;
			entity = new StringEntity(json, Charset.forName("utf-8"));
			// 解决中文乱码的问题
			entity.setContentType("application/json; charset=UTF-8");
			entity.setContentEncoding("utf-8");
			post.setEntity(entity);
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(1000 * 10)// 创建连接的最长时间，单位是毫秒
					.setConnectionRequestTimeout(1000 * 10)// 设置获取连接的最长时间，单位毫秒
					.setSocketTimeout(1000 * 10)// 设置数据传输的最长时间，单位毫秒
					.build();
			post.setConfig(requestConfig);
			CloseableHttpResponse httpResponse = httpclient.execute(post);
			HttpEntity e = httpResponse.getEntity();
			if (e != null) {
				msg = EntityUtils.toString(e, "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return msg;
	}
	public static String doHttpsPost(String url, String json) {
		CloseableHttpClient httpclient = getClient();
		String msg = "";
		try {
			HttpPost post = new HttpPost(url);
			// 设置发送消息的参数
			StringEntity entity;
			entity = new StringEntity(json, Charset.forName("utf-8"));
			// 解决中文乱码的问题
			entity.setContentType("application/json; charset=UTF-8");
			entity.setContentEncoding("utf-8");
			post.setEntity(entity);
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(1000 * 10)// 创建连接的最长时间，单位是毫秒
					.setConnectionRequestTimeout(1000 * 10)// 设置获取连接的最长时间，单位毫秒
					.setSocketTimeout(1000 * 10)// 设置数据传输的最长时间，单位毫秒
					.build();
			post.setConfig(requestConfig);
			CloseableHttpResponse httpResponse = httpclient.execute(post);
			HttpEntity e = httpResponse.getEntity();
			if (e != null) {
				msg = EntityUtils.toString(e, "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return msg;
	}

	public static CloseableHttpClient getClient() {
		SSLContext sslContext = null;
		try {
			sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					return true;
				}
			}).build();
		} catch (Exception ex) {
				ex.printStackTrace();
		}
		HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		return httpClient;
	}
}