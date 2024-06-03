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
        }
        stage("Build image") {
            steps {
                script {
                    myapp = docker.build("choiaerim/hangahanga:${env.BUILD_ID}")
                }
            }
        }
        stage("Push image") {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'hangahanga') {
                            myapp.push("latest")
                            myapp.push("${env.BUILD_ID}")
                    }
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
