declare dc_infra=docker-compose-db.yaml
declare dc_app=docker-compose-app.yaml

# 애플리케이션 빌드
function build_api() {
    cd bookmarker-api
    ./gradlew clean build -x test
    cd ..
}

# 데이터베이스 인프라 시작
function start_infra() {
     echo "Start DB Docker Container"
     docker-compose -f ${dc_infra} up -d
     docker-compose -f ${dc_infra} logs -f
}

# 데이터베이스 인프라 중지
function stop_infra() {
    echo "Stopping DB docker container"
    docker-compose -f ${dc_infra} stop
    docker-compose -f ${dc_infra} rm -f
}

# 모든 서비스 시작
function start() {
    build_api
    echo "Starting DB and App container"
    docker-compose -f ${dc_infra} -f ${dc_app} up -d
    docker-compose -f ${dc_infra} -f ${dc_app} logs -f
}

# 모든 서비스 중지
function stop() {
    echo "Stopping DB and App container"
    docker-compose -f ${dc_infra} -f ${dc_app} stop
    docker-compose -f ${dc_infra} -f ${dc_app} rm -f
}

# 모든 서비스 재시작
function restart() {
    stop
    sleep 3
    start
}

# 기본 action
action="start"
# 종료문 코드
if [[ "$#" != "0" ]]; then
    action=$@
fi

# action 변수의 값을 평가하여 실행
eval ${action}
