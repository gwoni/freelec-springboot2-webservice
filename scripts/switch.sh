#!/usr/bin/env bash

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh

function switch_proxy() {
    IDLE_PORT=$(find_idle_port)

    echo "> 전환할 Port: $IDLE_PORT"
    echo "> Port 전환"

    #하나의 문장을 만들어  파이프라인(|)으로 넘겨주기 위해 echo를 사용합니다.
    #엔진엑스가 변경할 프록시 주소를 생성합니다. (") 를 사용해야 $service_url을 그대로 인식합니다.
    # | 앞에서 넘겨준 문장을 service-url.inc에 덮어씁니다.
    echo "set \$service_url http://127.0.0.1:${IDLE_PORT};" | sudo tee /etc/nginx/conf.d/service-url.inc

    echo "> 엔진엑스 Reload"
    sudo service nginx reload

    #restart와 다르게 끊김 없이 다시 불러옵니다.
    #다만 중요한 설정들은 반영이되지 않으므로 restart를 사용해야합니다.
    #여기선 외부의 설정파일인 service-url을 다시 불러오는 거라 reload로 가능합니다.
}