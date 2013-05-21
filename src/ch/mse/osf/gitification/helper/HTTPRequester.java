package ch.mse.osf.gitification.helper;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HTTPRequester {
	private static final Logger log = LoggerFactory.getLogger(HTTPRequester.class);
	
	static PoolingClientConnectionManager mgr = new PoolingClientConnectionManager();
	static HttpClient client = new DefaultHttpClient(mgr);
	
	public static String post(String url, String content)
			throws ClientProtocolException, IOException {
		HttpPost postRequest = new HttpPost(url);
		StringEntity entity = new StringEntity(content);
		entity.setContentType("application/json");
		postRequest.setEntity(entity);

		HttpResponse responseBody = client.execute(postRequest);
		String blu = EntityUtils.toString(responseBody.getEntity());
		log.debug(responseBody.getStatusLine().toString());
		log.debug(blu);
		return blu;
	}
	
	public static String get(String url) throws ClientProtocolException, IOException {
		HttpGet getRequest = new HttpGet(url);
		getRequest.addHeader("accept", "application/json");
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String responseBody = client.execute(getRequest, responseHandler);
		return responseBody;
	}
}
