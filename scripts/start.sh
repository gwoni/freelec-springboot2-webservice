#!/usr/bin/env bash

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh

APPPAHT=/home/ec2-user/app
REPOSITORY=/home/ec2-user/app/step3
PROJECT_NAME=freelec-springboot2-webservice

echo "> Build 파일 복사"
echo "> cp $REPOSITORY/zip/*.jar $REPOSITORY/"

cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "> 새 어플리케이션 배포"
JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> JAR Name: $JAR_NAME"

echo "> $JAR_NAME 에 실행권한 추가"

chmod +x $JAR_NAME

echo "> $JAR_NAME 실행"

IDLE_PROFILE=$(find_idle_profile)

echo "> $JAR_NAME 를 profile=$IDLE_PROFILE 로 실행합니다."

if [ ${$IDLE_PROFILE} == real1 ]
then
  cd $APPPAHT
  sudo docker build -f Dockerfile_blue -t blue .
  sudo docker run -d --name blue -p 8081:8081 blue:latest

else
  cd $APPPAHT
  sudo docker build -f Dockerfile_grean -t grean .
  sudo docker run -d --name grean -p 8082:8082 grean:latest

fi

#nohup java -jar \
#    -Dspring.config.location=classpath:/application.properties,classpath:/application-$IDLE_PROFILE.properties,/home/ec2-user/app/application-oauth.properties,/home/ec2-user/app/application-real-db.properties \
#    -Dspring.profiles.active=$IDLE_PROFILE \
#    $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &

    #기본적으로 deploy.sh와 유사
    #IDLE_PROFILE을 통해 properties 파일을 가져오고 (application-$IDLE_PROFILE.properties),active profile을 지정하는 것 ( -Dspring.profiles.activ=$IDLE_PROFILE)이 다른점