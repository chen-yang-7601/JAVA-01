package com.yhh.nio;

import java.io.IOException;
import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;

/**
 *  模拟python Requests，好用的类
 * @author yhh
 *
 */
public class Requests {
	
	
	public static String get(String url) throws IOException {
		try (final CloseableHttpClient httpclient = HttpClients.createDefault()) {
			final HttpGet httpget = new HttpGet(url);
			final HttpClientResponseHandler<String> responseHandler = new HttpClientResponseHandler<String>() {
				
				@Override
				public String handleResponse(final ClassicHttpResponse response) throws IOException{
					final int status = response.getCode();
					if (status >= HttpStatus.SC_SUCCESS && status < HttpStatus.SC_REDIRECTION) {
						final HttpEntity entity = response.getEntity();
						try {
							return entity!=null?EntityUtils.toString(entity):null;
						}catch(final ParseException ex) {
							throw new ClientProtocolException(ex);
						}
					}else {
						throw new ClientProtocolException("Unexpected response status: " + status);
					}
				}
			};
			
			// responseBody
			return httpclient.execute(httpget, responseHandler);
		}
	}

	public static void main(String[] args) {
		String url1  ="http://127.0.0.1:8801";
		String url2  ="http://127.0.0.1:8802";
		String url3  ="http://127.0.0.1:8803";
		try {
			System.out.println("connecting url1:"+ url1 +" .....");
			System.out.println("and we get：");
			System.out.println(Requests.get(url1));
			System.out.println("connecting url2:"+ url2 +" .....");
			System.out.println("and we get：");
			System.out.println(Requests.get(url2));
			System.out.println("connecting url3:"+ url3 +" .....");
			System.out.println("and we get：");
			System.out.println(Requests.get(url3));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
