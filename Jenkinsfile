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
                bat "docker build -t=katuo1801/jenkins-docker ."
            }            
        }
        stage('Push Image'){
            steps{
                bat "docker push katuo1801/jenkins-docker"
            }            
        }
    }
}