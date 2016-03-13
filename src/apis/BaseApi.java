package apis;

import java.io.IOException;

import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.savagelook.android.UrlJsonAsyncTask;

abstract public class BaseApi  extends UrlJsonAsyncTask {
	public static Integer METHOD_GET=1;
	public static Integer METHOD_PUT=2;
	public static Integer METHOD_POST=3;
	public static Integer METHOD_DELETE=4;
	
	abstract public void formulateData(Context context, JSONObject json, HttpPost post);
	abstract public void processResponse(Context context, JSONObject json);
	abstract public String getUrl();
	abstract public Integer getMethod();
	abstract public void catchHttpResponseException(HttpResponseException e, JSONObject json) throws JSONException;
	abstract public void catchIOException(IOException e, JSONObject json) throws JSONException;
	abstract public void catchJSONException(JSONException e, JSONObject json);
	abstract public JSONObject convertResponseToJson(String response) throws JSONException;
	abstract public String getContextString();
	abstract public void onProcessResponseSuccess(Context context);
	abstract public void onProcessResponseError(Context context);
//--------
	public BaseApi(Context context) {
		super(context);
	}

	@Override
	protected JSONObject doInBackground(String... urls) {
		switch(getMethod())
		{
		case 1://METHOD_GET
			return doInBackgroundGet(urls);
		case 2://METHOD_PUT
			return doInBackgroundPut(urls);
		case 3://METHOD_POST
			return doInBackgroundPost(urls);
		case 4://METHOD_DELETE
			return doInBackgroundDelete(urls);
		default: return null;
		}
	}
	private JSONObject doInBackgroundDelete(String... urls)
	{
		DefaultHttpClient client = new DefaultHttpClient();
		HttpDelete post = new HttpDelete(getUrl());
		JSONObject json = new JSONObject();
		String response = null;

		try {
			try {
				// setup the returned values beforehand,
				// in case something goes wrong
				json.put("success", false);
				json.put("error", "Error connecting to server.");

				ResponseHandler<String> responseHandler = new BasicResponseHandler();
				response = client.execute(post, responseHandler);
				if(response==null)response="null";
				Log.i(getContextString()+" response",response);
				json = convertResponseToJson(response);
			} catch (HttpResponseException e) {
				catchHttpResponseException(e, json);
			} catch (IOException e) {
				catchIOException(e, json);
			}
		} catch (JSONException e) {
			catchJSONException(e, json);
		}

		return json;
	}
	private JSONObject doInBackgroundPut(String... urls)
	{
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPut post = new HttpPut(getUrl());
		JSONObject json = new JSONObject();
		String response = null;

		try {
			try {
				// setup the returned values beforehand,
				// in case something goes wrong
				json.put("success", false);
				json.put("error", "Error connecting to server.");

				ResponseHandler<String> responseHandler = new BasicResponseHandler();
				response = client.execute(post, responseHandler);
				Log.i(getContextString()+" response",response);
				json = convertResponseToJson(response);
			} catch (HttpResponseException e) {
				catchHttpResponseException(e, json);
			} catch (IOException e) {
				catchIOException(e, json);
			}
		} catch (JSONException e) {
			catchJSONException(e, json);
		}

		return json;
	}
	private JSONObject doInBackgroundGet(String... urls)
	{
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet post = new HttpGet(getUrl());
		JSONObject json = new JSONObject();
		String response = null;

		try {
			try {
				// setup the returned values beforehand,
				// in case something goes wrong
				json.put("success", false);
				json.put("error", "Error connecting to server.");

				ResponseHandler<String> responseHandler = new BasicResponseHandler();
				response = client.execute(post, responseHandler);
				Log.i(getContextString()+" response",response);
				json = convertResponseToJson(response);
			} catch (HttpResponseException e) {
				catchHttpResponseException(e, json);
			} catch (IOException e) {
				catchIOException(e, json);
			}
		} catch (JSONException e) {
			catchJSONException(e, json);
		}

		return json;
	}
	private JSONObject doInBackgroundPost(String... urls)
	{
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(getUrl());
		JSONObject json = new JSONObject();
		String response = null;

		try {
			try {
				// setup the returned values beforehand,
				// in case something goes wrong
				json.put("success", false);
				json.put("error", "Error connecting to server.");

				formulateData(context, json, post);
				
				ResponseHandler<String> responseHandler = new BasicResponseHandler();
				response = client.execute(post, responseHandler);
				Log.i(getContextString()+" response",response);
				json = convertResponseToJson(response);
			} catch (HttpResponseException e) {
				catchHttpResponseException(e, json);
			} catch (IOException e) {
				catchIOException(e, json);
			}
		} catch (JSONException e) {
			catchJSONException(e, json);
		}

		return json;
	}

	@Override
	protected void onPostExecute(JSONObject json) {
		processResponse(context, json);
        super.onPostExecute(json);
	}
	
}
