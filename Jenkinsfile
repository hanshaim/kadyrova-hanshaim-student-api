
pipeline {
    agent any

    tools {
        jdk 'JDK17'
    }

    options {
        skipDefaultCheckout(true)
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
                echo 'Student: Kadyrova Hanshaim'
            }
        }

        stage('Build') {
            steps {
                sh 'chmod +x gradlew'
                sh './gradlew clean assemble --no-daemon'
            }
        }

        stage('Test') {
            steps {
                sh './gradlew test --no-daemon'
            }
            post {
                always {
                    junit allowEmptyResults: true,
                        testResults: 'build/test-results/test/*.xml'
                }
            }
        }

        stage('Docker Build') {
            when {
                expression { return false }
            }
            steps {
                sh 'docker build -t kadyrova-hanshaim-student-api:v1.0-kadyrova-hanshaim .'
            }
        }

        stage('Verify') {
            steps {
                sh 'test -f build/libs/*.jar'
                echo 'Kadyrova Hanshaim: Java build verified'
                echo 'Docker verification pending'
            }
        }
    }
}

