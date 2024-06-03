pipeline {
    agent any

    environment {
        CONTAINER_NAME = "hangahanga-app-container"
    }

     stages {
            stage("Checkout code") {
                steps {
                    checkout scm
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
}
