#!/usr/bin/env bash

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH) #현재 stop.sh가 속해있는 경로를 찾습니다. 하단의 코드와 같이 profile.sh의 경로를 찾기 위해 사용됩니다.
source ${ABSDIR}/profile.sh #해당 부분으로 stop,sh에서도 profile.sh의 여러 function을 사용할수 있습니다.

IDLE_PORT=$(find_idle_port)

#echo "> $IDLE_PORT 에서 구동중인 애플리케이션 pid 확인"
#IDLE_PID=$(lsof -ti tcp:${IDLE_PORT})

if [ ${IDLE_PORT} == 8081 ]
then
  IDLE_BLUEGREAN=blue
else
  IDLE_BLUEGREAN=grean
fi

EXISTS_BLUE=$(docker ps | grep -w ${IDLE_BLUEGREAN})

if [ -z "$EXISTS_BLUE" ]; then
        docker stop $IDLE_BLUEGREAN
        docker rm $IDLE_BLUEGREAN
        docker rmi $IDLE_BLUEGREAN
        sleep 5
else
        echo "> 현재 구동중인 Docker가 없으므로 종료하지 않습니다."
fi

#if [ -z ${IDLE_PID} ]
#then
#  echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
#else
#  echo "> kill -15 $IDLE_PID"
#  kill -15 ${IDLE_PID}
#  sleep 5
#fi