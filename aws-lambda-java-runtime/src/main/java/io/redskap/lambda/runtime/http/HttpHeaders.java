package io.redskap.lambda.runtime.http;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class HttpHeaders {
    private Map<String, List<String>> headerMap;

    public HttpHeaders(Map<String, List<String>> headerMap) {
        this.headerMap = headerMap;
    }

    public Map<String, List<String>> getHeaderMap() {
        return Collections.unmodifiableMap(headerMap);
    }

    public String getSingleValueHeader(String key) {
        List<String> headerValues = headerMap.get(key);
        if (headerValues != null && headerValues.size() > 0) {
            return headerValues.iterator().next();
        }
        return null;
    }
}
