FROM java:8
MAINTAINER techforge
ENV app_name springbootdemo



RUN echo 'start install yum'
RUN wget http://yum.baseurl.org/download/3.4/yum-3.4.3.tar.gz && \
    tar xvf yum-3.4.3.tar.gz && \
    cd yum-3.4.3  && \
#    yummain.py install yum  && \
    rm -rf yum-3.4.3.tar.gz


#RUN wget http://repos.fedorapeople.org/repos/dchen/apache-maven/epel-apache-maven.repo -O /etc/yum.repos.d/epel-apache-maven.repo
#RUN yum -y install git
#RUN yum -y install apache-maven
ADD ./target/springbootdemo.jar /apps/springbootdemo.jar

#ENTRYPOINT /apps/springbootdemo/bin/start.sh
