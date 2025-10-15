FROM eclipse-temurin:21-jdk-jammy
# 작업 디렉토리 설정
WORKDIR /app
# build/libs에 있는 모든 jar파일을 app.jar로 복사
COPY build/libs/*.jar app.jar
# 컨테이너 내부 포트 8080을 외부에 노출
EXPOSE 8080
# 컨테이너에서 실행
ENTRYPOINT ["java", "-jar", "app.jar"]