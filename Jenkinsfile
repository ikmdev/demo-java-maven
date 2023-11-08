@Library("titan-library") _

pipeline {

    agent any

    tools {
        jdk "java-21"
        maven "default"
        git "git"
    }

    options {
        buildDiscarder logRotator( 
                    daysToKeepStr: '16', 
                    numToKeepStr: '10'
            )
    }

    stages {

        stage('Setup') {
            
            steps {
                sh """
                printenv
                """
            }
        }

        // GitLab_API_Token

        stage('Release') {
            
            steps {
                script {
                    withCredentials([string(credentialsId: 'GitLab_API_Token', variable: 'token')]) {
                        echo "${token}"
                    }
                }
            }
        }

        
        stage('Cleanup Workspace') {
            
            steps {
                cleanWs()
                sh """
                echo "${env.BRANCH_NAME}"
                echo "${currentBuild.buildCauses}"
                """
            }
        }
    }

}