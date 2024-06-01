pipeline {
    agent any

    environment {
        CONTAINER_NAME = "hangahanga-app-container"
    }

    stages {
        stage('Checkout') {
            steps {
                // 소스 코드 체크아웃
                checkout scm
            }
        }

        stage('Build and Run Container') {
            when {
                // 브랜치별로 실행 조건을 지정
                expression { BRANCH_NAME == 'main' }
            }
            steps {
                script {
                    // Docker 이미지 빌드 및 컨테이너 실행
                    sh './gradlew build -x test'
                    sh 'docker build -t hangahanga-app .'
                    sh "docker run -p 8081:8080 -d --name $CONTAINER_NAME hangahanga-app"
                }
            }
        }
    }

    post {
        success {
            // 빌드 성공 시 실행되는 작업
            echo 'Build successful! Container is running.'
        }
        failure {
            // 빌드 실패 시 실행되는 작업
            echo 'Build failed. Notify the team.'
        }
    }
}
