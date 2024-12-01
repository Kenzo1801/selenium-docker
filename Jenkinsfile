pipeline{

    agent any

    stages{
        stage('Build Jar'){
            steps{
                bat "mvn clean package -DskipTests"
            }
        }

        stage('Build Image'){
            steps{
                bat "docker build -t=katuo1801/jenkins-docker:latest ."
            }            
        }

        stage('Push Image'){
            environment{
                DOCKER_HUB = credentials('dockerhub-credentials')
            }
            steps{
                bat 'echo ${DOCKER_HUB_PSW} | docker login -u ${DOCKER_HUB_USR} --password-stdin'
                bat "docker push katuo1801/jenkins-docker:latest"
                bat "docker push katuo1801/jenkins-docker:${env.BUILD_NUMBER}"
            }            
        }
    }
    
    post{
        always{
            bat "docker logout"
        }
    }
}