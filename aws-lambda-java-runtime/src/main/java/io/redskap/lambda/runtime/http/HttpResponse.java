package io.redskap.lambda.runtime.http;

public class HttpResponse {
    private int statusCode;
    private HttpHeaders headers;
    private String body;

    public HttpResponse(int statusCode, HttpHeaders headers, String body) {
        this.statusCode = statusCode;
        this.headers = headers;
        this.body = body;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }
}
