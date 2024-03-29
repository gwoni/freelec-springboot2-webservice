#!/usr/bin/env bash

# 쉬고 있는 profile 찾기: real1이 사용중이면 real2가 쉬고 있고, 반대면 real1이 쉬고 있음
function find_idle_profile()
{

    #현재 엔진엑스가 바라보고 있는 스프링부트가 정상적으로 수행중인지 확인합니다.
    #응답값을 HttpStatus로 받습니다.
    #정상이면 200, 오류이면 400~503 사이로 발생하니 400이상은 모두 예외로 보고 real2 를 profile로 사용합니다
    RESPONSE_CODE=$(curl -s -o /dev/null -w "%{http_code}" http://localhost/profile)

    if [ ${RESPONSE_CODE} -ge 400 ] # 400 보다 크면 (즉, 40x/50x 에러 모두 포함)
    then
        CURRENT_PROFILE=real2
    else
        CURRENT_PROFILE=$(curl -s http://localhost/profile)
    fi

    if [ ${CURRENT_PROFILE} == real1 ]
    then
      IDLE_PROFILE=real2 #엔진엑스와 연결되지 않은 profile
    else
      IDLE_PROFILE=real1
    fi

    echo "${IDLE_PROFILE}" #제일 마지막에 echo로 출력후 클라이언트에서 그 값을 잡아 ($find_idle_profile)) 사용합니다.
}

# 쉬고 있는 profile의 port 찾기
function find_idle_port()
{
    IDLE_PROFILE=$(find_idle_profile)

    if [ ${IDLE_PROFILE} == real1 ]
    then
      echo "8081"
    else
      echo "8082"
    fi
}