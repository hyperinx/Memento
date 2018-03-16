package morrison.com.memento.Additional;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Michael on 11.11.2017.
 */

public class HttpParser {
    String finalHttpData = "";
    BufferedWriter bufferedWriter ;
    OutputStream outputStream ;
    BufferedReader bufferedReader ;
    StringBuilder tempStringBuilder = new StringBuilder();
    URL url;

    public String postRequest(HashMap<String, String> Data, String httpUrlHolder) {

        try {
            url = new URL(httpUrlHolder);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(14000);
            httpURLConnection.setConnectTimeout(14000);
            httpURLConnection.setRequestMethod("POST");

            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            outputStream = httpURLConnection.getOutputStream();

            bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            bufferedWriter.write(finalDataParsing(Data));
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                bufferedReader = new BufferedReader(
                        new InputStreamReader(
                                httpURLConnection.getInputStream()
                        )
                );

                StringBuilder tmpBuilder = new StringBuilder();

               while((finalHttpData = bufferedReader.readLine()) != null){

                   tmpBuilder.append(finalHttpData);
               }
               finalHttpData = tmpBuilder.toString();
            }
            else {
                finalHttpData = "Can't establish connection!";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return finalHttpData;
    }

    public String finalDataParsing(HashMap<String,String> data) throws UnsupportedEncodingException {

        for(Map.Entry<String,String> entryMap : data.entrySet()){

            tempStringBuilder.append("&");
            tempStringBuilder.append(URLEncoder.encode(entryMap.getKey(), "UTF-8"));
            tempStringBuilder.append("=");
            tempStringBuilder.append(URLEncoder.encode(entryMap.getValue(), "UTF-8"));

        }
        return tempStringBuilder.toString();
    }

}