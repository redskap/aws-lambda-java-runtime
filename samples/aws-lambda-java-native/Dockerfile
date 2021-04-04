FROM gradle:6.8.3-jdk11 as builder
COPY --chown=gradle:gradle . /home/application
WORKDIR /home/application
RUN gradle clean shadowJar --no-daemon

FROM amazon/aws-lambda-provided:al2.2021.03.22.18 as graalvm
ENV LANG=en_US.UTF-8
RUN yum install -y gcc gcc-c++ libc6-dev zlib1g-dev curl bash zlib zlib-devel zip tar gzip
ENV GRAAL_VERSION 21.0.0.2
ENV JDK_VERSION java11
ENV GRAAL_FILENAME graalvm-ce-${JDK_VERSION}-linux-amd64-${GRAAL_VERSION}.tar.gz
RUN curl -4 -L https://github.com/graalvm/graalvm-ce-builds/releases/download/vm-${GRAAL_VERSION}/${GRAAL_FILENAME} -o /tmp/${GRAAL_FILENAME}
RUN tar -zxf /tmp/${GRAAL_FILENAME} -C /tmp \
    && mv /tmp/graalvm-ce-${JDK_VERSION}-${GRAAL_VERSION} /usr/lib/graalvm
RUN rm -rf /tmp/*
CMD ["/usr/lib/graalvm/bin/native-image"]

FROM graalvm
COPY --from=builder /home/application/ /home/application/
WORKDIR /home/application
ENV BUILT_JAR_NAME=aws-lambda-java-native-0.0.1-SNAPSHOT-all
RUN /usr/lib/graalvm/bin/gu install native-image
RUN /usr/lib/graalvm/bin/native-image --verbose -jar build/libs/${BUILT_JAR_NAME}.jar
RUN mv ${BUILT_JAR_NAME} function
RUN chmod 777 function
RUN zip -j function.zip bootstrap function
ENTRYPOINT ["/home/application/function"]