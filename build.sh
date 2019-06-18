mvn clean package
docker build -f docker/Dockerfile -t techforge/springbootdemo:1.0.0-SNAPSHOT .
echo 'success'
