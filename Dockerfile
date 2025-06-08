FROM --platform=linux/amd64 selenium/standalone-chrome:114.0-20250606

USER root

RUN apt update && \
    apt install -y git maven vim

#CMD mvn test -DsuiteXmlFile="./src/test/resources/Alpha_Regression.xml"

#USER seluser
# install all maven deps
#RUN cd /tmp && git clone https://github.com/LiyWu/HWSeleniumframework.git && \
  #  cd HWSeleniumframework && \
 #   mvn dependency:resolve
