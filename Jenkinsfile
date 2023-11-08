@Library("titan-library") _

pipeline {

    agent any

    tools {
        jdk "java-21"
        maven "default"
        git "git"
    }

    environment {
        TRUNK_BRANCH_NAME   = 'main'
        REPO_NAME           = "${GIT_URL.split('/')[4].split('\\.')[0]}"
        BRANCH_NAME         = "${GIT_BRANCH.startsWith('origin/') ? GIT_BRANCH['origin/'.length()..-1] : GIT_BRANCH}"

        GITLAB_OWNER        = "${GIT_URL.split('/')[3]}"
        GITLAB_REPO         = "https://gitlab.tinkarbuild.com/${GITLAB_OWNER}/${REPO_NAME}.git"
        GITLAB_RELEASE_API  = "https://gitlab.tinkarbuild.com/api/v4/projects/${GITLAB_OWNER}%2F${REPO_NAME}/releases"
        GITLAB_CREDS_ID     = 'gitlab-jenkins-username-pat'
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
                    withCredentials([usernamePassword(credentialsId: GITLAB_CREDS_ID, passwordVariable: 'token', usernameVariable: 'user')]) {
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