# 베이스 이미지 설정 (여기서는 OpenJDK 17 사용)
FROM openjdk:17-jdk-slim

# 작업 디렉토리 설정
WORKDIR /app

# 호스트의 JAR 파일을 컨테이너로 복사
COPY build/libs/*.jar app.jar

# 포트 설정 (Spring Boot 애플리케이션이 사용하는 포트에 맞게 수정)
EXPOSE 8080

# 컨테이너 실행시 실행할 명령어 설정
CMD ["java", "-jar", "app.jar"]
