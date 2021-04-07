(window.webpackJsonp=window.webpackJsonp||[]).push([[9],{365:function(t,a,e){"use strict";e.r(a);var n=e(45),o=Object(n.a)({},(function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("ContentSlotsDistributor",{attrs:{"slot-key":t.$parent.slotKey}},[e("h1",{attrs:{id:"troubleshooting"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#troubleshooting"}},[t._v("#")]),t._v(" Troubleshooting")]),t._v(" "),e("h2",{attrs:{id:"the-lambda-function-is-reporting-runtime-invalidentrypoint"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#the-lambda-function-is-reporting-runtime-invalidentrypoint"}},[t._v("#")]),t._v(" The Lambda function is reporting Runtime.InvalidEntrypoint")]),t._v(" "),e("h3",{attrs:{id:"symptom"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#symptom"}},[t._v("#")]),t._v(" Symptom")]),t._v(" "),e("div",{staticClass:"language-text extra-class"},[e("pre",{pre:!0,attrs:{class:"language-text"}},[e("code",[t._v('{\n  "errorMessage": "RequestId: 8ba2e00d-c988-4304-a84e-6bd8801c510f Error: fork/exec /var/task/bootstrap: no such file or directory",\n  "errorType": "Runtime.InvalidEntrypoint"\n}\n\nSTART RequestId: 8ba2e00d-c988-4304-a84e-6bd8801c510f Version: $LATEST\nEND RequestId: 8ba2e00d-c988-4304-a84e-6bd8801c510f\nREPORT RequestId: 8ba2e00d-c988-4304-a84e-6bd8801c510f\tDuration: 0.69 ms\tBilled Duration: 1 ms\tMemory Size: 128 MB\tMax Memory Used: 5 MB\t\nRequestId: 8ba2e00d-c988-4304-a84e-6bd8801c510f Error: fork/exec /var/task/bootstrap: no such file or directory\nRuntime.InvalidEntrypoint\n')])])]),e("h3",{attrs:{id:"solution"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#solution"}},[t._v("#")]),t._v(" Solution")]),t._v(" "),e("p",[t._v("The "),e("code",[t._v("boostrap")]),t._v(" file has CRLF instead of LF. Convert the line endings, and re-upload the package.")]),t._v(" "),e("h2",{attrs:{id:"unable-to-create-proxy-class"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#unable-to-create-proxy-class"}},[t._v("#")]),t._v(" Unable to create Proxy class")]),t._v(" "),e("h3",{attrs:{id:"symptom-2"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#symptom-2"}},[t._v("#")]),t._v(" Symptom")]),t._v(" "),e("div",{staticClass:"language-text extra-class"},[e("pre",{pre:!0,attrs:{class:"language-text"}},[e("code",[t._v('This indicates you are using an old version (< 4.5.8) of Apache http client. It is recommended to use http client version >= 4.5.9 to avoid the breaking change introduced in apache client 4.5.7 and the latency in exception handling. See https://github.com/aws/aws-sdk-java/issues/1919 for more information \nException in thread "main" com.oracle.svm.core.jdk.UnsupportedFeatureError: Proxy class defined by interfaces [interface org.apache.http.conn.ConnectionRequest, interface com.amazonaws.http.conn.Wrapped] not found. Generating proxy classes at runtime is not supported. Proxy classes need to be defined at image build time by specifying the list of interfaces that they implement. To define proxy classes use -H:DynamicProxyConfigurationFiles=<comma-separated-config-files> and -H:DynamicProxyConfigurationResources=<comma-separated-config-resources> options.\n\tat com.oracle.svm.core.util.VMError.unsupportedFeature(VMError.java:87)\n\tat com.oracle.svm.reflect.proxy.DynamicProxySupport.getProxyClass(DynamicProxySupport.java:113)\n\tat java.lang.reflect.Proxy.getProxyConstructor(Proxy.java:66)\n\tat java.lang.reflect.Proxy.newProxyInstance(Proxy.java:1006)\n\tat com.amazonaws.http.conn.ClientConnectionRequestFactory.wrap(ClientConnectionRequestFactory.java:45)\n\tat com.amazonaws.http.conn.ClientConnectionManagerFactory$Handler.invoke(ClientConnectionManagerFactory.java:78)\n\tat com.amazonaws.http.conn.$Proxy148.requestConnection(Unknown Source)\n\tat org.apache.http.impl.execchain.MainClientExec.execute(MainClientExec.java:176)\n\tat org.apache.http.impl.execchain.ProtocolExec.execute(ProtocolExec.java:186)\n\tat org.apache.http.impl.client.InternalHttpClient.doExecute(InternalHttpClient.java:185)\n\tat org.apache.http.impl.client.CloseableHttpClient.execute(CloseableHttpClient.java:83)\n\tat org.apache.http.impl.client.CloseableHttpClient.execute(CloseableHttpClient.java:56)\n\tat com.amazonaws.http.apache.client.impl.SdkHttpClient.execute(SdkHttpClient.java:72)\n\tat com.amazonaws.http.AmazonHttpClient$RequestExecutor.executeOneRequest(AmazonHttpClient.java:1331)\n\tat com.amazonaws.http.AmazonHttpClient$RequestExecutor.executeHelper(AmazonHttpClient.java:1145)\n\tat com.amazonaws.http.AmazonHttpClient$RequestExecutor.doExecute(AmazonHttpClient.java:802)\n\tat com.amazonaws.http.AmazonHttpClient$RequestExecutor.executeWithTimer(AmazonHttpClient.java:770)\n\tat com.amazonaws.http.AmazonHttpClient$RequestExecutor.execute(AmazonHttpClient.java:744)\n\tat com.amazonaws.http.AmazonHttpClient$RequestExecutor.access$500(AmazonHttpClient.java:704)\n\tat com.amazonaws.http.AmazonHttpClient$RequestExecutionBuilderImpl.execute(AmazonHttpClient.java:686)\n\tat com.amazonaws.http.AmazonHttpClient.execute(AmazonHttpClient.java:550)\n\tat com.amazonaws.http.AmazonHttpClient.execute(AmazonHttpClient.java:530)\n\tat com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient.doInvoke(AmazonDynamoDBClient.java:6214)\n\tat com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient.invoke(AmazonDynamoDBClient.java:6181)\n\tat com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient.executePutItem(AmazonDynamoDBClient.java:3793)\n\tat com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient.putItem(AmazonDynamoDBClient.java:3757)\n\tat com.arnoldgalovics.blog.app.TestRequestHandler.handleRequest(TestRequestHandler.java:24)\n\tat com.arnoldgalovics.blog.app.TestRequestHandler.handleRequest(TestRequestHandler.java:14)\n\tat io.redskap.lambda.runtime.internal.RequestHandlerInvoker.internalInvokeHandler(RequestHandlerInvoker.java:20)\n\tat io.redskap.lambda.runtime.internal.RequestHandlerInvoker.invokeHandler(RequestHandlerInvoker.java:10)\n\tat io.redskap.lambda.runtime.LambdaRuntime.run(LambdaRuntime.java:37)\n\tat com.arnoldgalovics.blog.app.JavaApp.main(JavaApp.java:21)\nEND RequestId: 1fa87a52-03db-478f-9b7e-92ce62d445cd\nREPORT RequestId: 1fa87a52-03db-478f-9b7e-92ce62d445cd\tDuration: 169.66 ms\tBilled Duration: 181 ms\tMemory Size: 131 MB\tMax Memory Used: 32 MB\tInit Duration: 11.10 ms\t\nRequestId: 1fa87a52-03db-478f-9b7e-92ce62d445cd Error: Runtime exited with error: exit status 1\nRuntime.ExitError\n')])])]),e("h3",{attrs:{id:"solution-2"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#solution-2"}},[t._v("#")]),t._v(" Solution")]),t._v(" "),e("p",[t._v("When building with GraalVM, the classes are pre-compiled into the executable. In case of interface-based runtime proxies,\nGraalVM should know which interfaces will be implemented by the proxy class.")]),t._v(" "),e("p",[t._v("This can be configured via the "),e("code",[t._v("proxy-config.json")]),t._v(" file within "),e("code",[t._v("META-INF/native-image/...")]),t._v(" folder.")]),t._v(" "),e("p",[t._v("Example configuration for the AWS DynamoDB SDK:")]),t._v(" "),e("div",{staticClass:"language-json extra-class"},[e("pre",{pre:!0,attrs:{class:"language-json"}},[e("code",[e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("[")]),t._v("\n  "),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("[")]),e("span",{pre:!0,attrs:{class:"token string"}},[t._v('"org.apache.http.conn.HttpClientConnectionManager"')]),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token string"}},[t._v('"org.apache.http.pool.ConnPoolControl"')]),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token string"}},[t._v('"com.amazonaws.http.conn.Wrapped"')]),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("]")]),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v("\n  "),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("[")]),e("span",{pre:!0,attrs:{class:"token string"}},[t._v('"org.apache.http.conn.ConnectionRequest"')]),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token string"}},[t._v('"com.amazonaws.http.conn.Wrapped"')]),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("]")]),t._v("\n"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("]")]),t._v("\n")])])]),e("h2",{attrs:{id:"classnotfoundexception-during-execution"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#classnotfoundexception-during-execution"}},[t._v("#")]),t._v(" ClassNotFoundException during execution")]),t._v(" "),e("h3",{attrs:{id:"symptom-3"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#symptom-3"}},[t._v("#")]),t._v(" Symptom")]),t._v(" "),e("div",{staticClass:"language-text extra-class"},[e("pre",{pre:!0,attrs:{class:"language-text"}},[e("code",[t._v("START RequestId: 9a97e354-1089-47cf-8d47-1c48edd1e7d1 Version: $LATEST\ncom.amazonaws.SdkClientException: Unable to calculate a request signature: Unable to calculate a request signature: No installed provider supports this key: javax.crypto.spec.SecretKeySpec\n\tat com.amazonaws.auth.AbstractAWSSigner.sign(AbstractAWSSigner.java:109)\n\tat com.amazonaws.auth.AWS4Signer.newSigningKey(AWS4Signer.java:639)\n\tat com.amazonaws.auth.AWS4Signer.deriveSigningKey(AWS4Signer.java:404)\n\tat com.amazonaws.auth.AWS4Signer.sign(AWS4Signer.java:253)\n\tat com.amazonaws.http.AmazonHttpClient$RequestExecutor.executeOneRequest(AmazonHttpClient.java:1305)\n\tat com.amazonaws.http.AmazonHttpClient$RequestExecutor.executeHelper(AmazonHttpClient.java:1145)\n\tat com.amazonaws.http.AmazonHttpClient$RequestExecutor.doExecute(AmazonHttpClient.java:802)\n\tat com.amazonaws.http.AmazonHttpClient$RequestExecutor.executeWithTimer(AmazonHttpClient.java:770)\n\tat com.amazonaws.http.AmazonHttpClient$RequestExecutor.execute(AmazonHttpClient.java:744)\n\tat com.amazonaws.http.AmazonHttpClient$RequestExecutor.access$500(AmazonHttpClient.java:704)\n\tat com.amazonaws.http.AmazonHttpClient$RequestExecutionBuilderImpl.execute(AmazonHttpClient.java:686)\n\tat com.amazonaws.http.AmazonHttpClient.execute(AmazonHttpClient.java:550)\n\tat com.amazonaws.http.AmazonHttpClient.execute(AmazonHttpClient.java:530)\n\tat com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient.doInvoke(AmazonDynamoDBClient.java:6214)\n\tat com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient.invoke(AmazonDynamoDBClient.java:6181)\n\tat com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient.executePutItem(AmazonDynamoDBClient.java:3793)\n\tat com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient.putItem(AmazonDynamoDBClient.java:3757)\n\tat com.arnoldgalovics.blog.app.TestRequestHandler.handleRequest(TestRequestHandler.java:24)\n\tat com.arnoldgalovics.blog.app.TestRequestHandler.handleRequest(TestRequestHandler.java:14)\n\tat io.redskap.lambda.runtime.internal.RequestHandlerInvoker.internalInvokeHandler(RequestHandlerInvoker.java:20)\n\tat io.redskap.lambda.runtime.internal.RequestHandlerInvoker.invokeHandler(RequestHandlerInvoker.java:10)\n\tat io.redskap.lambda.runtime.LambdaRuntime.run(LambdaRuntime.java:37)\n\tat com.arnoldgalovics.blog.app.JavaApp.main(JavaApp.java:21)\nCaused by: com.amazonaws.SdkClientException: Unable to calculate a request signature: No installed provider supports this key: javax.crypto.spec.SecretKeySpec\n\tat com.amazonaws.auth.AbstractAWSSigner.sign(AbstractAWSSigner.java:132)\n\tat com.amazonaws.auth.AbstractAWSSigner.sign(AbstractAWSSigner.java:105)\n\t... 22 more\nCaused by: java.security.InvalidKeyException: No installed provider supports this key: javax.crypto.spec.SecretKeySpec\n\tat javax.crypto.Mac.chooseProvider(Mac.java:392)\n\tat javax.crypto.Mac.init(Mac.java:435)\n\tat com.amazonaws.auth.AbstractAWSSigner.sign(AbstractAWSSigner.java:127)\n\t... 23 more\nCaused by: java.security.NoSuchAlgorithmException: class configured for Mac (provider: SunJCE) cannot be found.\n\tat java.security.Provider$Service.getImplClass(Provider.java:1933)\n\tat java.security.Provider$Service.newInstance(Provider.java:1894)\n\tat javax.crypto.Mac.chooseProvider(Mac.java:365)\n\t... 25 more\nCaused by: java.lang.ClassNotFoundException: com.sun.crypto.provider.HmacCore$HmacSHA256\n\tat com.oracle.svm.core.hub.ClassForNameSupport.forName(ClassForNameSupport.java:60)\n\tat java.lang.Class.forName(DynamicHub.java:1247)\n\tat java.security.Provider$Service.getImplClass(Provider.java:1918)\n\t... 27 more\nEND RequestId: 9a97e354-1089-47cf-8d47-1c48edd1e7d1\nREPORT RequestId: 9a97e354-1089-47cf-8d47-1c48edd1e7d1\tDuration: 169.33 ms\tBilled Duration: 183 ms\tMemory Size: 131 MB\tMax Memory Used: 33 MB\tInit Duration: 13.63 ms\t\n")])])]),e("h3",{attrs:{id:"solution-3"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#solution-3"}},[t._v("#")]),t._v(" Solution")]),t._v(" "),e("p",[t._v("In case of the application using any type of reflection to access a class, GraalVM will not know about it\nduring AoT compilation. It needs to be told what these classes are in a file called "),e("code",[t._v("reflect-config.json")]),t._v(" within the\n"),e("code",[t._v("META-INF/native-image/...")]),t._v(" folder.")]),t._v(" "),e("p",[t._v("Example configuration:")]),t._v(" "),e("div",{staticClass:"language-json extra-class"},[e("pre",{pre:!0,attrs:{class:"language-json"}},[e("code",[e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("[")]),t._v("\n  ...\n  "),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("\n    "),e("span",{pre:!0,attrs:{class:"token property"}},[t._v('"name"')]),e("span",{pre:!0,attrs:{class:"token operator"}},[t._v(":")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token string"}},[t._v('"com.sun.crypto.provider.HmacCore$HmacSHA256"')]),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v("\n    "),e("span",{pre:!0,attrs:{class:"token property"}},[t._v('"allDeclaredFields"')]),e("span",{pre:!0,attrs:{class:"token operator"}},[t._v(":")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token boolean"}},[t._v("true")]),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v("\n    "),e("span",{pre:!0,attrs:{class:"token property"}},[t._v('"allDeclaredConstructors"')]),e("span",{pre:!0,attrs:{class:"token operator"}},[t._v(":")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token boolean"}},[t._v("true")]),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v("\n    "),e("span",{pre:!0,attrs:{class:"token property"}},[t._v('"allDeclaredMethods"')]),e("span",{pre:!0,attrs:{class:"token operator"}},[t._v(":")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token boolean"}},[t._v("true")]),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v("\n    "),e("span",{pre:!0,attrs:{class:"token property"}},[t._v('"allDeclaredClasses"')]),e("span",{pre:!0,attrs:{class:"token operator"}},[t._v(":")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token boolean"}},[t._v("true")]),t._v("\n  "),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v("\n  ...\n"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("]")]),t._v("\n")])])])])}),[],!1,null,null,null);a.default=o.exports}}]);