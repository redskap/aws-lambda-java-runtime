# Troubleshooting
## The Lambda function is reporting Runtime.InvalidEntrypoint
### Symptom
```text
{
  "errorMessage": "RequestId: 8ba2e00d-c988-4304-a84e-6bd8801c510f Error: fork/exec /var/task/bootstrap: no such file or directory",
  "errorType": "Runtime.InvalidEntrypoint"
}

START RequestId: 8ba2e00d-c988-4304-a84e-6bd8801c510f Version: $LATEST
END RequestId: 8ba2e00d-c988-4304-a84e-6bd8801c510f
REPORT RequestId: 8ba2e00d-c988-4304-a84e-6bd8801c510f	Duration: 0.69 ms	Billed Duration: 1 ms	Memory Size: 128 MB	Max Memory Used: 5 MB	
RequestId: 8ba2e00d-c988-4304-a84e-6bd8801c510f Error: fork/exec /var/task/bootstrap: no such file or directory
Runtime.InvalidEntrypoint
```
### Solution
The `boostrap` file has CRLF instead of LF. Convert the line endings, and re-upload the package.

## Unable to create Proxy class
### Symptom
```text
This indicates you are using an old version (< 4.5.8) of Apache http client. It is recommended to use http client version >= 4.5.9 to avoid the breaking change introduced in apache client 4.5.7 and the latency in exception handling. See https://github.com/aws/aws-sdk-java/issues/1919 for more information 
Exception in thread "main" com.oracle.svm.core.jdk.UnsupportedFeatureError: Proxy class defined by interfaces [interface org.apache.http.conn.ConnectionRequest, interface com.amazonaws.http.conn.Wrapped] not found. Generating proxy classes at runtime is not supported. Proxy classes need to be defined at image build time by specifying the list of interfaces that they implement. To define proxy classes use -H:DynamicProxyConfigurationFiles=<comma-separated-config-files> and -H:DynamicProxyConfigurationResources=<comma-separated-config-resources> options.
	at com.oracle.svm.core.util.VMError.unsupportedFeature(VMError.java:87)
	at com.oracle.svm.reflect.proxy.DynamicProxySupport.getProxyClass(DynamicProxySupport.java:113)
	at java.lang.reflect.Proxy.getProxyConstructor(Proxy.java:66)
	at java.lang.reflect.Proxy.newProxyInstance(Proxy.java:1006)
	at com.amazonaws.http.conn.ClientConnectionRequestFactory.wrap(ClientConnectionRequestFactory.java:45)
	at com.amazonaws.http.conn.ClientConnectionManagerFactory$Handler.invoke(ClientConnectionManagerFactory.java:78)
	at com.amazonaws.http.conn.$Proxy148.requestConnection(Unknown Source)
	at org.apache.http.impl.execchain.MainClientExec.execute(MainClientExec.java:176)
	at org.apache.http.impl.execchain.ProtocolExec.execute(ProtocolExec.java:186)
	at org.apache.http.impl.client.InternalHttpClient.doExecute(InternalHttpClient.java:185)
	at org.apache.http.impl.client.CloseableHttpClient.execute(CloseableHttpClient.java:83)
	at org.apache.http.impl.client.CloseableHttpClient.execute(CloseableHttpClient.java:56)
	at com.amazonaws.http.apache.client.impl.SdkHttpClient.execute(SdkHttpClient.java:72)
	at com.amazonaws.http.AmazonHttpClient$RequestExecutor.executeOneRequest(AmazonHttpClient.java:1331)
	at com.amazonaws.http.AmazonHttpClient$RequestExecutor.executeHelper(AmazonHttpClient.java:1145)
	at com.amazonaws.http.AmazonHttpClient$RequestExecutor.doExecute(AmazonHttpClient.java:802)
	at com.amazonaws.http.AmazonHttpClient$RequestExecutor.executeWithTimer(AmazonHttpClient.java:770)
	at com.amazonaws.http.AmazonHttpClient$RequestExecutor.execute(AmazonHttpClient.java:744)
	at com.amazonaws.http.AmazonHttpClient$RequestExecutor.access$500(AmazonHttpClient.java:704)
	at com.amazonaws.http.AmazonHttpClient$RequestExecutionBuilderImpl.execute(AmazonHttpClient.java:686)
	at com.amazonaws.http.AmazonHttpClient.execute(AmazonHttpClient.java:550)
	at com.amazonaws.http.AmazonHttpClient.execute(AmazonHttpClient.java:530)
	at com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient.doInvoke(AmazonDynamoDBClient.java:6214)
	at com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient.invoke(AmazonDynamoDBClient.java:6181)
	at com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient.executePutItem(AmazonDynamoDBClient.java:3793)
	at com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient.putItem(AmazonDynamoDBClient.java:3757)
	at com.arnoldgalovics.blog.app.TestRequestHandler.handleRequest(TestRequestHandler.java:24)
	at com.arnoldgalovics.blog.app.TestRequestHandler.handleRequest(TestRequestHandler.java:14)
	at io.redskap.lambda.runtime.internal.RequestHandlerInvoker.internalInvokeHandler(RequestHandlerInvoker.java:20)
	at io.redskap.lambda.runtime.internal.RequestHandlerInvoker.invokeHandler(RequestHandlerInvoker.java:10)
	at io.redskap.lambda.runtime.LambdaRuntime.run(LambdaRuntime.java:37)
	at com.arnoldgalovics.blog.app.JavaApp.main(JavaApp.java:21)
END RequestId: 1fa87a52-03db-478f-9b7e-92ce62d445cd
REPORT RequestId: 1fa87a52-03db-478f-9b7e-92ce62d445cd	Duration: 169.66 ms	Billed Duration: 181 ms	Memory Size: 131 MB	Max Memory Used: 32 MB	Init Duration: 11.10 ms	
RequestId: 1fa87a52-03db-478f-9b7e-92ce62d445cd Error: Runtime exited with error: exit status 1
Runtime.ExitError
```
### Solution
When building with GraalVM, the classes are pre-compiled into the executable. In case of interface-based runtime proxies,
GraalVM should know which interfaces will be implemented by the proxy class. 

This can be configured via the `proxy-config.json` file within `META-INF/native-image/...` folder.

Example configuration for the AWS DynamoDB SDK:
```json
[
  ["org.apache.http.conn.HttpClientConnectionManager", "org.apache.http.pool.ConnPoolControl", "com.amazonaws.http.conn.Wrapped"],
  ["org.apache.http.conn.ConnectionRequest", "com.amazonaws.http.conn.Wrapped"]
]
```

## ClassNotFoundException during execution
### Symptom
```text
START RequestId: 9a97e354-1089-47cf-8d47-1c48edd1e7d1 Version: $LATEST
com.amazonaws.SdkClientException: Unable to calculate a request signature: Unable to calculate a request signature: No installed provider supports this key: javax.crypto.spec.SecretKeySpec
	at com.amazonaws.auth.AbstractAWSSigner.sign(AbstractAWSSigner.java:109)
	at com.amazonaws.auth.AWS4Signer.newSigningKey(AWS4Signer.java:639)
	at com.amazonaws.auth.AWS4Signer.deriveSigningKey(AWS4Signer.java:404)
	at com.amazonaws.auth.AWS4Signer.sign(AWS4Signer.java:253)
	at com.amazonaws.http.AmazonHttpClient$RequestExecutor.executeOneRequest(AmazonHttpClient.java:1305)
	at com.amazonaws.http.AmazonHttpClient$RequestExecutor.executeHelper(AmazonHttpClient.java:1145)
	at com.amazonaws.http.AmazonHttpClient$RequestExecutor.doExecute(AmazonHttpClient.java:802)
	at com.amazonaws.http.AmazonHttpClient$RequestExecutor.executeWithTimer(AmazonHttpClient.java:770)
	at com.amazonaws.http.AmazonHttpClient$RequestExecutor.execute(AmazonHttpClient.java:744)
	at com.amazonaws.http.AmazonHttpClient$RequestExecutor.access$500(AmazonHttpClient.java:704)
	at com.amazonaws.http.AmazonHttpClient$RequestExecutionBuilderImpl.execute(AmazonHttpClient.java:686)
	at com.amazonaws.http.AmazonHttpClient.execute(AmazonHttpClient.java:550)
	at com.amazonaws.http.AmazonHttpClient.execute(AmazonHttpClient.java:530)
	at com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient.doInvoke(AmazonDynamoDBClient.java:6214)
	at com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient.invoke(AmazonDynamoDBClient.java:6181)
	at com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient.executePutItem(AmazonDynamoDBClient.java:3793)
	at com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient.putItem(AmazonDynamoDBClient.java:3757)
	at com.arnoldgalovics.blog.app.TestRequestHandler.handleRequest(TestRequestHandler.java:24)
	at com.arnoldgalovics.blog.app.TestRequestHandler.handleRequest(TestRequestHandler.java:14)
	at io.redskap.lambda.runtime.internal.RequestHandlerInvoker.internalInvokeHandler(RequestHandlerInvoker.java:20)
	at io.redskap.lambda.runtime.internal.RequestHandlerInvoker.invokeHandler(RequestHandlerInvoker.java:10)
	at io.redskap.lambda.runtime.LambdaRuntime.run(LambdaRuntime.java:37)
	at com.arnoldgalovics.blog.app.JavaApp.main(JavaApp.java:21)
Caused by: com.amazonaws.SdkClientException: Unable to calculate a request signature: No installed provider supports this key: javax.crypto.spec.SecretKeySpec
	at com.amazonaws.auth.AbstractAWSSigner.sign(AbstractAWSSigner.java:132)
	at com.amazonaws.auth.AbstractAWSSigner.sign(AbstractAWSSigner.java:105)
	... 22 more
Caused by: java.security.InvalidKeyException: No installed provider supports this key: javax.crypto.spec.SecretKeySpec
	at javax.crypto.Mac.chooseProvider(Mac.java:392)
	at javax.crypto.Mac.init(Mac.java:435)
	at com.amazonaws.auth.AbstractAWSSigner.sign(AbstractAWSSigner.java:127)
	... 23 more
Caused by: java.security.NoSuchAlgorithmException: class configured for Mac (provider: SunJCE) cannot be found.
	at java.security.Provider$Service.getImplClass(Provider.java:1933)
	at java.security.Provider$Service.newInstance(Provider.java:1894)
	at javax.crypto.Mac.chooseProvider(Mac.java:365)
	... 25 more
Caused by: java.lang.ClassNotFoundException: com.sun.crypto.provider.HmacCore$HmacSHA256
	at com.oracle.svm.core.hub.ClassForNameSupport.forName(ClassForNameSupport.java:60)
	at java.lang.Class.forName(DynamicHub.java:1247)
	at java.security.Provider$Service.getImplClass(Provider.java:1918)
	... 27 more
END RequestId: 9a97e354-1089-47cf-8d47-1c48edd1e7d1
REPORT RequestId: 9a97e354-1089-47cf-8d47-1c48edd1e7d1	Duration: 169.33 ms	Billed Duration: 183 ms	Memory Size: 131 MB	Max Memory Used: 33 MB	Init Duration: 13.63 ms	
```
### Solution
In case of the application using any type of reflection to access a class, GraalVM will not know about it
during AoT compilation. It needs to be told what these classes are in a file called `reflect-config.json` within the
`META-INF/native-image/...` folder.

Example configuration:
```json
[
  ...
  {
    "name": "com.sun.crypto.provider.HmacCore$HmacSHA256",
    "allDeclaredFields": true,
    "allDeclaredConstructors": true,
    "allDeclaredMethods": true,
    "allDeclaredClasses": true
  },
  ...
]
```