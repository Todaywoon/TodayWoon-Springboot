FROM openjdk:17-jdk-slim

# 작업 디렉토리 설정
WORKDIR /app

# 소스 코드 복사
COPY . .

# Gradle 빌드 수행
# RUN ./gradlew build -x test

# JAR 파일 복사
ARG JAR_FILE_PATH=build/libs/*.jar
COPY ${JAR_FILE_PATH} app.jar

# 애플리케이션 실행
ENTRYPOINT ["sudo","java","--Duser.timezone=Asia/Seoul", "-jar","app.jar"]