cd ..
git pull
mvn clean package
docker build -t techforge/springbootdemo:1.0.0-SNAPSHOT .
echo 'success'