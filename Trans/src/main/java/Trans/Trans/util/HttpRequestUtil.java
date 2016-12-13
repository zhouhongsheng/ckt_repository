package Trans.Trans.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

public class HttpRequestUtil {

	private static final Logger logger = LoggerFactory.getLogger(HttpRequestUtil.class);

	/**
	 * 通用post http request
	 * 
	 * @param url
	 * @param timeout
	 * @param headers
	 * @param data
	 * @return
	 */
	public static JSONObject postHttpRequest(String url, List<BasicNameValuePair> headers,
			List<BasicNameValuePair> data, int timeout, boolean retry) {

		JSONObject jsonobject = null;

		HttpRequestRetryHandler httpRequestRetryHandler = null;
		if (retry) {
			httpRequestRetryHandler = new HttpRequestRetryHandler() {
				public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
					if (executionCount >= 3) {// 如果已经重试了3次，就放弃
						return false;
					}
					if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试
						return true;
					}
					if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常
						return false;
					}
					if (exception instanceof InterruptedIOException) {// 超时
						return false;
					}
					if (exception instanceof UnknownHostException) {// 目标服务器不可达
						return false;
					}
					if (exception instanceof ConnectTimeoutException) {// 连接被拒绝
						return false;
					}
					if (exception instanceof SSLException) {// SSL握手异常
						return false;
					}

					HttpClientContext clientContext = HttpClientContext.adapt(context);
					HttpRequest request = clientContext.getRequest();
					// 如果请求是幂等的，就再次尝试
					if (!(request instanceof HttpEntityEnclosingRequest)) {
						return true;
					}
					return false;
				}
			};

		}

		RequestConfig neteaseRequestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout)
				.setConnectionRequestTimeout(timeout).build();

		CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(neteaseRequestConfig)
				.setRetryHandler(httpRequestRetryHandler).build();

		HttpPost httpPost = new HttpPost(url);

		for (BasicNameValuePair oneHeader : headers) {
			httpPost.addHeader(oneHeader.getName(), oneHeader.getValue());
		}

		// 执行请求
		CloseableHttpResponse response = null;
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(data, "utf-8"));
			response = httpClient.execute(httpPost);
			String result = EntityUtils.toString(response.getEntity(), "utf-8");
			logger.info("http post invoke succeeded! url: {}, header :{},  data : {} + result :{}", url, headers, data,
					result);
			jsonobject = JSONObject.fromObject(result);
		} catch (Exception e) {
			logger.error("http post invoke failed! url: {}, header :{},  data : {} + error :{}", url, headers, data,
					e.toString());
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					logger.error(e.toString());
				}
			}
		}

		return jsonobject;

	}

	/**
	 * 通用post https request
	 * 
	 * @param url
	 * @param timeout
	 * @param headers
	 * @param data
	 * @return
	 */
	public static JSONObject postHttpsRequest(String url, List<BasicNameValuePair> headers,
			List<BasicNameValuePair> data, int timeout, boolean retry) {

		JSONObject jsonobject = null;

		HttpRequestRetryHandler httpRequestRetryHandler = null;
		if (retry) {
			httpRequestRetryHandler = new HttpRequestRetryHandler() {
				public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
					if (executionCount >= 3) {// 如果已经重试了3次，就放弃
						return false;
					}
					if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试
						return true;
					}
					if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常
						return false;
					}
					if (exception instanceof InterruptedIOException) {// 超时
						return false;
					}
					if (exception instanceof UnknownHostException) {// 目标服务器不可达
						return false;
					}
					if (exception instanceof ConnectTimeoutException) {// 连接被拒绝
						return false;
					}
					if (exception instanceof SSLException) {// SSL握手异常
						return false;
					}

					HttpClientContext clientContext = HttpClientContext.adapt(context);
					HttpRequest request = clientContext.getRequest();
					// 如果请求是幂等的，就再次尝试
					if (!(request instanceof HttpEntityEnclosingRequest)) {
						return true;
					}
					return false;
				}
			};

		}

		HttpPost httpPost = new HttpPost(url);

		for (BasicNameValuePair oneHeader : headers) {
			httpPost.addHeader(oneHeader.getName(), oneHeader.getValue());
		}

		// 执行请求
		CloseableHttpResponse response = null;
		try {

			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout)
					.setConnectionRequestTimeout(timeout).build();
			// trustAllHosts();

			CloseableHttpClient httpClient = HttpClients.custom().setSSLContext(createSSLContext())
					.setDefaultRequestConfig(requestConfig).setRetryHandler(httpRequestRetryHandler).build();

			httpPost.setEntity(new UrlEncodedFormEntity(data, "utf-8"));

			response = httpClient.execute(httpPost);
			String result = EntityUtils.toString(response.getEntity(), "utf-8");

			logger.info("http post invoke succeeded! url: {}, header :{},  data : {} + result :{}", url, headers, data,
					result);
			jsonobject = JSONObject.fromObject(result);
		} catch (Exception e) {
			logger.error("http post invoke failed! url: {}, header :{},  data : {} + error :{}", url, headers, data,
					e.toString());
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					logger.error(e.toString());
				}
			}
		}

		return jsonobject;

	}

	/**
	 * 创建SSL安全连接
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	private static SSLContext createSSLContext() throws NoSuchAlgorithmException, KeyManagementException {
		SSLContext ctx = SSLContext.getInstance("SSL");
		// Implementation of a trust manager for X509 certificates
		X509TrustManager tm = new X509TrustManager() {

			public void checkClientTrusted(X509Certificate[] xcs, String authType) throws CertificateException {
				// System.out.println("checkClientTrusted");
				// do nothing, check pass
			}

			public void checkServerTrusted(X509Certificate[] xcs, String authType) throws CertificateException {
				// System.out.println("checkServerTrusted");
				// do nothing, check pass
			}

			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		};
		ctx.init(null, new TrustManager[] { tm }, null);

		return ctx;
	}

	/**
	 * 创建SSL安全连接
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 *//*
		 * private static SSLSocketFactory createSSLConnSocketFactory() throws
		 * NoSuchAlgorithmException, KeyManagementException { SSLContext ctx =
		 * SSLContext.getInstance("SSL"); // Implementation of a trust manager
		 * for X509 certificates X509TrustManager tm = new X509TrustManager() {
		 * 
		 * public void checkClientTrusted(X509Certificate[] xcs, String
		 * authType) throws CertificateException {
		 * System.out.println("checkClientTrusted"); }
		 * 
		 * public void checkServerTrusted(X509Certificate[] xcs, String
		 * authType) throws CertificateException {
		 * System.out.println("checkServerTrusted"); }
		 * 
		 * public X509Certificate[] getAcceptedIssuers() { return null; } };
		 * ctx.init(null, new TrustManager[] { tm }, null); SSLSocketFactory ssf
		 * = new SSLSocketFactory(ctx,
		 * SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		 * 
		 * 
		 * 
		 * return ssf; }
		 */
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private final static String CR_LF = "\r\n";
	private final static String TWO_DASHES = "--";
	public static boolean HttpsDebug = true;

	public static JSONObject postHttpsRequestFile(String urlP, String X_Sioeye_App_Id, String x_sioeye_app_sign_key,
			String X_Sioeye_App_Production, String X_Sioeye_Sessiontoken, String uploadfile, int timeout,
			boolean retry) {

		// List<Object> list=new ArrayList<Object>();
		String BOUNDARY = setBOUNDARY();
		String result = null;
		InputStream inptStream = null;
		InputStream errorStream = null;
		OutputStream outputStream = null;
		FileInputStream fStream = null;
		URL url = null;
		// long Stime=0,Etime=0;
		// int resposeOK=-1;
		int resposeCode = -1;
		HttpsURLConnection httpsURLConnection = null;
		JSONObject jsonObject = null;
		// exception
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		try {
			url = new URL(urlP);
			httpsURLConnection = (HttpsURLConnection) url.openConnection();
			httpsURLConnection.setConnectTimeout(timeout); // 璁剧疆杩炴帴瓒呮椂鏃堕棿
			httpsURLConnection.setReadTimeout(timeout);
			httpsURLConnection.setDoInput(true); // 鎵撳紑杈撳叆娴侊紝浠ヤ究浠庢湇鍔″櫒鑾峰彇鏁版嵁
			httpsURLConnection.setDoOutput(true); // 鎵撳紑杈撳嚭娴侊紝浠ヤ究鍚戞湇鍔″櫒鎻愪氦鏁版嵁
			httpsURLConnection.setRequestMethod("POST"); // 璁剧疆浠ost鏂瑰紡鎻愪氦鏁版嵁
			httpsURLConnection.setUseCaches(false); // 浣跨敤Post鏂瑰紡涓嶈兘浣跨敤缂撳瓨
			// SSLContext
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext;
			sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// SSLSocketFactory
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			httpsURLConnection.setSSLSocketFactory(ssf);
			httpsURLConnection.setRequestProperty("X_Sioeye_App_Id", X_Sioeye_App_Id);
			httpsURLConnection.setRequestProperty("x_sioeye_app_sign_key", x_sioeye_app_sign_key);
			httpsURLConnection.setRequestProperty("X_Sioeye_App_Production", X_Sioeye_App_Production);
			if (X_Sioeye_Sessiontoken != null) {
				httpsURLConnection.setRequestProperty("X_sioeye_sessiontoken", X_Sioeye_Sessiontoken);
			}
			httpsURLConnection.setRequestProperty("Content-Type",
					"multipart/form-data; boundary=" + BOUNDARY + "; charset=UTF-8");
			// if(com.main.ServerTestClient.paramsBean.getStart().equalsIgnoreCase("UI")){
			// com.main.ServerTestClient.mainui.getlblAPI().setText("API :
			// "+api);
			// }
			// Stime=new Date().getTime();//寮?濮嬫椂闂?
			// 鑾峰緱杈撳嚭娴侊紝鍚戞湇鍔″櫒鍐欏叆鏁版嵁
			outputStream = httpsURLConnection.getOutputStream();
			StringBuffer form = new StringBuffer();
			form.append(TWO_DASHES + BOUNDARY + CR_LF);
			form.append(formdateMethod());

			form.append(CR_LF);
			outputStream.write(form.toString().getBytes("UTF-8"));
			fStream = new FileInputStream(uploadfile);
			byte[] buffer = new byte[1024];
			int length = -1;
			while ((length = fStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, length);
			}
			outputStream.write(CR_LF.getBytes("UTF-8"));
			outputStream.write((TWO_DASHES + BOUNDARY + TWO_DASHES + CR_LF).getBytes("UTF-8"));
			outputStream.flush();
			resposeCode = httpsURLConnection.getResponseCode(); // 鑾峰緱鏈嶅姟鍣ㄧ殑鍝嶅簲鐮?
			// Etime=new Date().getTime();//缁撴潫鏃堕棿
			String encode = httpsURLConnection.getContentEncoding();
			if (resposeCode == HttpsURLConnection.HTTP_OK) {
				// resposeOK=1;
				inptStream = httpsURLConnection.getInputStream();
				// 澶勭悊鏈嶅姟鍣ㄧ殑鍝嶅簲缁撴灉
				result = dealResponseResult(inptStream, encode);
				// resposetimeout
				// boolean isexclude=false;
				// String[]
				// exclued=com.main.ServerTestClient.paramsBean.getResposetimeoutExclude().split(",");
				// for(String str:exclued){
				// if(str.equals(api)){
				// isexclude=true;
				// break;
				// }
				// }
				// if(!isexclude&&Etime-Stime>com.main.ServerTestClient.paramsBean.getResposetimeout()){
				// jsonObject= new
				// JSONObject("{\"success\":\"error1\",\"value\":\"ResposeTimeout\"}");
				// }else{
				// jsonObject= new JSONObject(result);
				// }
			} else {
				// resposeOK=0;
				errorStream = httpsURLConnection.getErrorStream();
				result = dealResponseResult(errorStream, encode);
			}
			jsonObject = JSONObject.fromObject(result);
			httpsURLConnection.disconnect();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			logger.error("Exception", e);
			e.printStackTrace(pw);
			result = sw.toString();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			logger.error("Exception", e);
			e.printStackTrace(pw);
			result = sw.toString();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			logger.error("Exception", e);
			e.printStackTrace(pw);
			result = sw.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("Exception", e);
			e.printStackTrace(pw);
			result = sw.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			logger.error("Exception", e);
			e.printStackTrace(pw);
			result = sw.toString();
		} finally {
			try {
				if (inptStream != null) {
					inptStream.close();
				}
				if (outputStream != null) {
					outputStream.close();
				}
				if (fStream != null) {
					fStream.close();
				}
				if (errorStream != null) {
					errorStream.close();
				}
				if (sw != null) {
					sw.close();
				}
				if (pw != null) {
					pw.close();
				}
			} catch (IOException e) {
				logger.error("Exception", e);
			}

		}
		return jsonObject;

		// if(HttpsDebug){
		// if(resposeOK==-1){//exception
		// logger.warn("API="+api+",type=sendFormData"+",get info from server
		// failed with exception!");
		// }else if(resposeOK==1){//ok
		// logger.info("API="+api+",Resposetime="+(Etime-Stime)+"ms,Code="+resposeCode+",type=sendFormData");
		// logger.info("API="+api+",result="+result);
		// }else if(resposeOK==0){//http fail
		// logger.warn("API="+api+",Resposetime="+(Etime-Stime)+"ms,ErrorCode="+resposeCode+",type=sendFormData");
		// logger.warn("API="+api+",result="+result);
		// }
		// }
		// list.add(jsonObject);
		// list.add(result);
		// list.add((Etime-Stime)+"");
		// list.add(resposeCode);

		// String result = EntityUtils.toString(response.getEntity(), "utf-8");
		//
		// logger.info("http post invoke succeeded! url: {}, header :{}, data :
		// {} + result :{}", url, headers, "filename:"+file.getName(), result);
		// jsonobject = JSONObject.fromObject(result);
		// } catch (Exception e) {
		// logger.error("http post invoke failed! url: {}, header :{}, data : {}
		// + error :{}", url, headers, "filename:"+file.getName(),
		// e.toString());
		// } finally {
		// if (response != null) {
		// try {
		// response.close();
		// } catch (IOException e) {
		// logger.error(e.toString());
		// }
		// }
		// }
		//
		// return jsonobject;
	}

	public static String setBOUNDARY() {
		char[] MULTIPART_CHARS = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		StringBuilder buffer = new StringBuilder();
		Random rand = new Random();
		int count = rand.nextInt(11) + 30; // a random size from 30 to 40
		for (int i = 0; i < count; i++) {
			buffer.append(MULTIPART_CHARS[rand.nextInt(MULTIPART_CHARS.length)]);
		}
		String BOUNDARY = buffer.toString();
		logger.info("BOUNDARY= " + BOUNDARY);
		return BOUNDARY;
	}

	public static String dealResponseResult(InputStream inputStream, String encode) {
		if (inputStream != null) {
			String resultData = null; // 瀛樺偍澶勭悊缁撴灉
			GZIPInputStream gZIPInputStream = null;
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			byte[] data = new byte[1024];
			int len = 0;
			try {
				if (encode != null && encode.equals("gzip")) {
					gZIPInputStream = new GZIPInputStream(inputStream);// gzip
					while ((len = gZIPInputStream.read(data)) != -1) {
						byteArrayOutputStream.write(data, 0, len);
					}
				} else {
					while ((len = inputStream.read(data)) != -1) {
						byteArrayOutputStream.write(data, 0, len);
					}
				}
			} catch (IOException e) {
				logger.error("Exception", e);
			} finally {
				try {
					if (byteArrayOutputStream != null) {
						byteArrayOutputStream.close();
					}
					if (gZIPInputStream != null) {
						gZIPInputStream.close();
					}
				} catch (IOException e) {
					logger.error("Exception", e);
				}
			}
			resultData = new String(byteArrayOutputStream.toByteArray());
			try {
				byteArrayOutputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("Exception", e);
			}
			return resultData;
		} else {
			return null;
		}
	}

	// 淇′换璇佷功
	public static class MyX509TrustManager implements X509TrustManager {
		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}
	}

	private static String formdateMethod() {
		StringBuffer formdata = new StringBuffer();
		formdata.append("Content-Disposition:form-data;name=\"upload\";filename=\"Portrait.jpg\"" + "\r\n");
		formdata.append("Content-Type:image/jpeg; charset=UTF-8" + "\r\n");
		formdata.append("Content-Transfer-Encoding: binary" + "\r\n");
		return formdata.toString();
	}
}
