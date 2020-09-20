package com.artsgard.athenticationtest.externalservice;

import com.artsgard.athenticationtest.dto.ResultDTO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.LoggerFactory;

/**
 *
 * @author artsgard
 */
public class AuthenticationService {
    
    public enum ServerState {
        SERVER_ERROR, NO_ERROR
    }

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    public static ResultDTO getAthentiatioResult(
            String url,
            String contentType,
            String firstFieldName,
            String firstFieldContent,
            String secondFieldName,
            String secondFieldContent) throws JSONException {
        
        BufferedReader br = null;
        StringBuilder sb = null;
        ResultDTO dtoResult = new ResultDTO();
        HttpURLConnection con = null;
        System.out.println("test url: " + url);
        try {
            con = getConnection(url, contentType);
            
            //JSONObject result = new JSONObject(sb.toString());
            String jsonInputString = "{\"" + firstFieldName + "\": \"" + firstFieldContent 
                    + "\", \"" + secondFieldName + "\": \"" + secondFieldContent + "\"}";
            System.out.println("json to be send: " + jsonInputString);

            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            InputStream str;
            
            int statusCode = con.getResponseCode();
            dtoResult.setStatusCode(statusCode);
            
            if (statusCode >= 200 && statusCode < 400) {
                str = con.getInputStream();
                dtoResult.setServerErrorResponse(ServerState.NO_ERROR.toString());
            } else {
                str = con.getErrorStream();
                String responseString = readErrorStream(con.getErrorStream());
                dtoResult.setServerErrorResponse(responseString);
                dtoResult.setServerResponse(ServerState.SERVER_ERROR.toString());
                System.out.println("responce error string: " + responseString);
            }

            br = new BufferedReader(new InputStreamReader((con.getInputStream())));
            String output;
            sb = new StringBuilder();
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }
            
            dtoResult.setServerResponse(sb.toString());
            System.out.println("dtoResult: " + dtoResult.toString());
        } catch (IOException ex) {
            System.err.println("Server IOException: " + ex);
            logger.error("Server IOException: " + ex);
        } finally {
            con.disconnect();
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                System.err.println("Server IOException: " + ex);
                logger.error("Server IOException: " + ex);
            }
        }
        return dtoResult;
    }

    private static String readErrorStream(final InputStream inputStream) throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        final StringBuilder responseString = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            responseString.append(line);
        }
        bufferedReader.close();
        return responseString.toString();
    }

    private static HttpURLConnection getConnection(String url, String contentType) {
        try {
            URL serverAddress = new URL(url);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("some proxy here", 8080));
            HttpURLConnection con;
            try {
                con = (HttpURLConnection) serverAddress.openConnection(); //openConnection(proxy)
                con.setReadTimeout(15000);
                con.setConnectTimeout(15000);
                con.setRequestMethod("POST");
                con.setDoInput(true);
                con.setDoOutput(true);
                con.setRequestProperty("Content-Type", contentType); //"application/json; charset=UTF-8"
                con.setRequestProperty("Accept", "application/json");
                con.addRequestProperty("User-Agent", "Mozilla");
                con.connect();          
            } catch (IOException ex) {
                System.err.println("Server IOException: " + ex);
                logger.error("Server IOException: " + ex);
                return null;
            }
            return con;
        } catch (MalformedURLException ex) {
            System.err.println("Server MalformedURLException: " + ex);
            logger.error("Server MalformedURLException: " + ex);
            return null;
        }
    }
}
