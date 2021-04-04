package com.arnoldgalovics.blog.app;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class IdentityRequestHandler implements RequestHandler<String, String> {
    @Override
    public String handleRequest(String input, Context context) {
        return input;
    }
}
