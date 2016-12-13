package Trans.Trans.config;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import Trans.Trans.AppUserBean;
import Trans.Trans.util.HttpRequestUtil;
import Trans.Trans.util.UtilTool;
import net.sf.json.JSONObject;

@Configuration
public class AppServerConfig {

	private static final Logger logger = LoggerFactory.getLogger(AppServerConfig.class);

	// appserver id
	@Value("${appserver.appid}")
	String appServerID;

	// appserver key
	@Value("${appserver.appkey}")
	String appServerKey;

	// appserver key
	@Value("${appserver.env}")
	String appServerEnv;

	// 网易云信连接超时设置
	@Value("${appserver.timeout}")
	int appServerTimeout;

	// 网易云信连接超时设置
	@Value("${appserver.retry}")
	boolean appServerRetry;

	//关注
	public String followeeRobot(String sessiontoken,String followeeids){
		
		String result="";
		String url = "https://api.siocloud.sioeye.cn/functions/batch_add_followee";
		// 设置请求的参数
		List<BasicNameValuePair> data = new ArrayList<BasicNameValuePair>();
		data.add(new BasicNameValuePair("followeeids", followeeids));

		JSONObject exeResult = postAppHttpRequest(url, sessiontoken, data);
		if (exeResult != null) {
			exeResult.remove("url");
			exeResult.remove("headers");
			exeResult.remove("data");
			
			result=exeResult.get("success").toString();
		}
		return result;
	}
	// 上传头像地址
	public AppUserBean updateAvatar(String sessiontoken,String uploadfile) {
		AppUserBean result = new AppUserBean();
		try{
		String url = "https://api.siocloud.sioeye.cn/functions/avatar_upload";
		// 设置请求的参数
//		List<BasicNameValuePair> data = new ArrayList<BasicNameValuePair>();
//		data.add(new BasicNameValuePair("upload", upload));
		
		JSONObject exeResult = postAppHttpRequestFile(url, sessiontoken, uploadfile);
		if (exeResult != null) {
			result = (AppUserBean) JSONObject.toBean(exeResult, AppUserBean.class);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	// 更新用户昵称
	public AppUserBean updateNickname(String nickname,String sessiontoken) {
		AppUserBean result = new AppUserBean();
		try{
		String url = "https://api.siocloud.sioeye.cn/functions/userinfo_update";
		// 设置请求的参数
		List<BasicNameValuePair> data = new ArrayList<BasicNameValuePair>();
		data.add(new BasicNameValuePair("nickname", nickname));

		JSONObject exeResult = postAppHttpRequest(url, sessiontoken, data);
		if (exeResult != null) {
			exeResult.remove("url");
			exeResult.remove("headers");
			exeResult.remove("data");
			result = (AppUserBean) JSONObject.toBean(exeResult, AppUserBean.class);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 调用网易云信接口通用类
	 * 
	 * @param url
	 * @param nvps
	 * @return
	 */
	private JSONObject postAppHttpRequest(String url, String headerExtSessionToken, List<BasicNameValuePair> data) {

		// 设置请求的参数
		List<BasicNameValuePair> headers = new ArrayList<BasicNameValuePair>();
		headers.add(new BasicNameValuePair("X_Sioeye_App_Id", appServerID));

		long time = System.currentTimeMillis();
		String md5key = UtilTool.getMD5(appServerKey + time+"");
		System.out.println(md5key + "," + time);
		headers.add(new BasicNameValuePair("x_sioeye_app_sign_key", md5key + "," + time));
		headers.add(new BasicNameValuePair("X_Sioeye_App_Production", appServerEnv));
		if (headerExtSessionToken != null) {
			headers.add(new BasicNameValuePair("X_Sioeye_Sessiontoken", headerExtSessionToken));
		}
		headers.add(new BasicNameValuePair("Content-Type", "application/x-www-form-urlencoded;charset=utf-8"));

		JSONObject jsonResult = HttpRequestUtil.postHttpsRequest(url, headers, data, appServerTimeout, appServerRetry);

		jsonResult.element("url", url);
		jsonResult.element("headers", headers);
		jsonResult.element("data", data);
		return jsonResult;
	}

	
	
	/**
	 * 调用网易云信接口通用类
	 * 
	 * @param url
	 * @param nvps
	 * @return
	 */
	private JSONObject postAppHttpRequestFile(String url, String headerExtSessionToken, String uploadfile) {

		// 设置请求的参数
//		List<BasicNameValuePair> headers = new ArrayList<BasicNameValuePair>();
//		headers.add(new BasicNameValuePair("X_Sioeye_App_Id", appServerID));

		long time = System.currentTimeMillis();
		String md5key = UtilTool.getMD5(appServerKey + time+"");
		System.out.println(md5key + "," + time);
//		headers.add(new BasicNameValuePair("x_sioeye_app_sign_key", md5key + "," + time));
//		headers.add(new BasicNameValuePair("X_Sioeye_App_Production", appServerEnv));
//		if (headerExtSessionToken != null) {
//			headers.add(new BasicNameValuePair("X_Sioeye_Sessiontoken", headerExtSessionToken));
//		}
//		headers.add(new BasicNameValuePair("Content-Type", "application/x-www-form-urlencoded;charset=utf-8"));

		JSONObject jsonResult = HttpRequestUtil.postHttpsRequestFile(url, appServerID,md5key + "," + time,appServerEnv,
				headerExtSessionToken,uploadfile, appServerTimeout, appServerRetry);

		return jsonResult;
	}
}
