pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'helloworld:latest'
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout the code from version control
                git 'https://your-repo-url.git'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Build the Docker image
                    docker.build("${env.DOCKER_IMAGE}").inside {
                        sh 'docker build -t helloworld .'
                    }
                }
            }
        }

        stage('Run Docker Container') {
            steps {
                script {
                    // Run the Docker container
                    sh 'docker run --rm helloworld'
                }
            }
        }
    }

    post {
        always {
            // Clean up Docker resources
            script {
                sh 'docker rmi -f helloworld'
            }
        }
    }
}
