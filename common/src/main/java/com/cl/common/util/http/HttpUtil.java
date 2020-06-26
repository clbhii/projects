package com.cl.common.util.http;

import com.cl.common.util.common.StringUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * @author cl
 * @since 2017年8月10日
 */

public class HttpUtil {

    private HttpUtil() {

    }

	public static String post(String url, Map<String, String> headMap, Map<String, String> paramsMap) {
		HttpResponse httpResponse = doPost(url, headMap, paramsMap);
		String body = null;
		try {
			HttpEntity respEntity = httpResponse.getEntity();
			body = EntityUtils.toString(respEntity);
			return body;
		} catch (Exception e) {
			throw new RuntimeException("访问http失败:" + url, e);
		}
	}
    public static HttpResponse doPost(String url, Map<String, String> headMap, Map<String, String> paramsMap) {
        return doPost(url, null, null, headMap, paramsMap);
    }

    public static HttpResponse doPost(String url, String proxyIp, Integer proxyPort, Map<String, String> headMap, Map<String, String> paramsMap) {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        // head
        if (headMap != null) {
            for (Entry<String, String> entry : headMap.entrySet()) {
                post.addHeader(entry.getKey(), entry.getValue());
            }
        }
        // params
        List<NameValuePair> params = paramsMap.entrySet().stream().map(entry -> new BasicNameValuePair(entry.getKey(), entry.getValue())).collect(Collectors.toList());

        HttpEntity entity = new UrlEncodedFormEntity(params, Charset.defaultCharset());
        post.setEntity(entity);

        if(StringUtil.isEmpty(proxyIp)) {
            // 设置代理访问和超时处理
            HttpHost proxy = new HttpHost(proxyIp, proxyPort);
            RequestConfig config = RequestConfig.custom().setProxy(proxy).setConnectTimeout(1000).
                    setSocketTimeout(1000).build();
            post.setConfig(config);
        }
        // 发送请求
        HttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(post);
            return httpResponse;
        } catch (Exception e) {
            throw new RuntimeException("访问http失败:" + url, e);
        }
    }
    public static String get(String url, Map<String, String> headMap, Map<String, String> paramsMap) {
        return get(url, null, null, headMap, paramsMap);
    }

    public static String get(String url, String proxyIp, Integer proxyPort, Map<String, String> headMap, Map<String, String> paramsMap) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //params
        StringBuilder urlBuilder = new StringBuilder(url);
        if (paramsMap != null) {
            urlBuilder.append("?");
            try{
                for (Entry<String, String> entry : paramsMap.entrySet()) {
                    urlBuilder.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), "UTF-8")).append("&");
                }
            }catch (Exception e) {
                throw new RuntimeException("url编码失败:" + urlBuilder.toString(), e);
            }
            if (paramsMap.size() > 0) {
                urlBuilder.deleteCharAt(urlBuilder.length()-1);
            }
        }
        HttpGet get = new HttpGet(urlBuilder.toString());
        if(StringUtil.notEmpty(proxyIp)) {
            //代理相当于原地址先访问代理，再有代理访问目标地址
            // 设置代理访问和超时处理
            HttpHost proxy = new HttpHost(proxyIp, proxyPort);
            RequestConfig config = RequestConfig.custom().setProxy(proxy).setConnectTimeout(15000).
                    setSocketTimeout(15000).build();
            get.setConfig(config);
        }
        // head
        if (headMap != null) {
            for (Entry<String, String> entry : headMap.entrySet()) {
                get.addHeader(entry.getKey(), entry.getValue());
            }
        }
        // 发送请求
        HttpResponse httpResponse = null;
        String body = null;
        try {
            httpResponse = httpClient.execute(get);
            HttpEntity respEntity = httpResponse.getEntity();
            body = EntityUtils.toString(respEntity);
        } catch (Exception e) {
            throw new RuntimeException("访问http失败:" + url, e);
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return body;
    }


    public static String put(String url, Map<String, String> headMap, Map<String, String> paramsMap) {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPut put = new HttpPut(url);
        // head
        if (headMap != null) {
            for (Entry<String, String> entry : headMap.entrySet()) {
                put.addHeader(entry.getKey(), entry.getValue());
            }
        }
        // params
        List<NameValuePair> params = paramsMap.entrySet().stream().map(entry -> new BasicNameValuePair(entry.getKey(), entry.getValue())).collect(Collectors.toList());

        HttpEntity entity = new UrlEncodedFormEntity(params, Charset.defaultCharset());
        put.setEntity(entity);

        // 发送请求
        HttpResponse httpResponse = null;
        String body = null;
        try {
            httpResponse = httpClient.execute(put);
            HttpEntity respEntity = httpResponse.getEntity();
            body = EntityUtils.toString(respEntity);
        } catch (Exception e) {
            throw new RuntimeException("访问http失败:" + url, e);
        }

        return body;
    }
    
    
    public static void main(String[] args) {
    	String url = "http://www.xicidaili.com/nn/1";
        HashMap<String, String> headMap = new HashMap<>();
        HashMap<String, String> dataMap = new HashMap<>();
        headMap.put("Host", "www.xicidaili.com");
        headMap.put("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 " +
                "(KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
//        String html = HttpUtil.get(url, headMap, dataMap);
        String html = HttpUtil.get(url, "111.229.224.145", 8118, headMap, dataMap);
    	System.out.println(html);
    }
}
