# Introduction
Serverless computing is getting more and more popular. One of the cloud providers supporting
a service like this is AWS, and the corresponding AWS Lambda service.

Java is a great language to write scalable and maintainable applications. However, for 
serverless computing it was not the best choice because of slow cold-starts. Until now.

With this customized runtime, you can speed up your Java Lambda functions. Instead of
spinning up a full JVM in AWS Lambda, it's possible to use GraalVM and compile a standalone
native executable and run it without any additional overhead.

## Quickstart
For now, the quickstart can be found on my blog: [Tackling Java cold startup times on AWS Lambda with GraalVM](https://arnoldgalovics.com/tackling-java-cold-startup-times-on-aws-lambda-with-graalvm/).