package io.redskap.lambda.runtime.http;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpClient {

    public HttpResponse get(String urlString) {
        HttpResponse output;
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            output = parseResponse(conn);
        } catch (IOException e) {
            throw new RuntimeException("Error while sending GET request", e);
        }

        return output;
    }

    public HttpResponse post(String urlString, String body) {
        HttpResponse output;
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            try (OutputStream os = conn.getOutputStream();
                 OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
                osw.write(body);
                osw.flush();
            }
            conn.connect();
            output = parseResponse(conn);
        } catch (IOException e) {
            throw new RuntimeException("Error while sending POST request", e);
        }

        return output;
    }

    private HttpResponse parseResponse(HttpURLConnection conn) throws IOException {
        HashMap<String, List<String>> headersMap = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : conn.getHeaderFields().entrySet()) {
            headersMap.put(entry.getKey(), entry.getValue());
        }
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return new HttpResponse(conn.getResponseCode(), new HttpHeaders(headersMap), result.toString());
    }

}
