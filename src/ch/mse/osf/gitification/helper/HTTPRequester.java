package ch.mse.osf.gitification.helper;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
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

public class HTTPRequester implements ResponseHandler<String>  {
	private static final Logger log = LoggerFactory
			.getLogger(HTTPRequester.class);

	static PoolingClientConnectionManager mgr = new PoolingClientConnectionManager();
	static HttpClient client = new DefaultHttpClient(mgr);

	public static String post(String url, String content)
			throws ClientProtocolException, IOException {
		HttpPost postRequest = new HttpPost(url);
		StringEntity entity = new StringEntity(content);
		entity.setContentType("application/json");
		postRequest.setEntity(entity);

		String blu = client.execute(postRequest, new HTTPRequester());	
		log.debug(blu);
		return blu;
	}

	public static String get(String url) throws ClientProtocolException,
			IOException {
		HttpGet getRequest = new HttpGet(url);
		getRequest.addHeader("accept", "application/json");
		String responseBody = client.execute(getRequest, new HTTPRequester());
		return responseBody;
	}

	public String handleResponse(HttpResponse response)
			throws IOException {
		StatusLine statusLine = response.getStatusLine();
		HttpEntity entity = response.getEntity();
		String content = EntityUtils.toString(entity);
		if (statusLine.getStatusCode() >= 300) {
			EntityUtils.consume(entity);
			throw new HttpResponseException(statusLine.getStatusCode(),
					statusLine.getReasonPhrase() + " source:" + content);
		}
		return entity == null ? null : content;
	}
}
