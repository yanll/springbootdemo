FROM java:8
MAINTAINER springbootdemo "yanll"
ENV app_name springbootdemo
#RUN wget http://repos.fedorapeople.org/repos/dchen/apache-maven/epel-apache-maven.repo -O /etc/yum.repos.d/epel-apache-maven.repo
RUN yum -y install git
#RUN yum -y install apache-maven
RUN mkdir -m 755 -p /apps/
ADD springbootdemo/target/springbootdemo.jar /apps/springbootdemo.jar

ENTRYPOINT /apps/springbootdemo/bin/start.sh
