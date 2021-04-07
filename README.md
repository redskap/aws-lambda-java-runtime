# AWS Lambda Java Runtime
The library has been created to have a production ready 
implementation of the [AWS Lambda Runtime API](https://docs.aws.amazon.com/lambda/latest/dg/runtimes-api.html) in Java, and to use it as
a foundation for creating GraalVM native images.

[The corresponding article can be found on my blog](https://arnoldgalovics.com/tackling-java-cold-startup-times-on-aws-lambda-with-graalvm/).

The full documentation can be found [here](https://redskap.github.io/aws-lambda-java-runtime/).

# Samples
There are 2 sample applications available at the moment:
* Simple lambda function with returning the request body in the response (samples/aws-lambda-java-native)
* Lambda function with writing into DynamoDB (samples/aws-lambda-java-dynamodb-native)

# Building the runtime
```bash
$ ./gradlew clean build
```

# License
```text
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
