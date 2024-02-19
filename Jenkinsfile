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
        GITLAB_CREDS_ID     = 'vault-gitlab-user-pat'

        TAG_VERSION          = "1.0.1"
    }


    options {
        buildDiscarder logRotator( 
                    daysToKeepStr: '16', 
                    numToKeepStr: '10'
            )
    }

    parameters {
        string(name: 'feature_branch', defaultValue: '', description: 'Name of branch without feature/ prefix')
    }

    stages {

        stage('Setup') {
            
            steps {
                sh """
                printenv
                """
            }
        }

        stage('Branch') {
            
            steps {
                sh """
                echo Feature ${params.feature_branch}
                """
            }
        }

        // stage('Release') {
            
        //     steps {
        //         script {
        //             withCredentials([usernamePassword(credentialsId: GITLAB_CREDS_ID, passwordVariable: 'token', usernameVariable: 'user')]) {
        //                 echo "GitLab User ${user}"
        //                 def releaseByTagUrl = GITLAB_RELEASE_API + "/v" + TAG_VERSION
        //                 echo "GitLab API Release by Tag URL: ${releaseByTagUrl}"

        //                 def response = sh(
        //                     script: """
        //                     curl -L \
        //                         -H "PRIVATE-TOKEN: ${token}" \
        //                         ${releaseByTagUrl}
        //                     """, 
        //                     returnStdout: true
        //                 ).trim()
        //                 echo "Done request"

        //                 def jsonResponse = readJSON text: response
        //                 echo "${jsonResponse}"

        //                 if (jsonResponse['message'] && jsonResponse['message'].contains('Not Found')) {
        //                     echo "This release does not exist yet"

        //                     def releaseVer = "${TAG_VERSION}"
        //                     def tag = "v${releaseVer}"

        //                     def data = "{\"name\": \"Release ${releaseVer}\",\"tag_name\": \"${tag}\", \"description\": \"Release ${releaseVer} from tag ${tag}\" }"

        //                     def postResponse = sh(
        //                         script: """
        //                         curl -L \
        //                             -H "PRIVATE-TOKEN: ${token}" \
        //                             -H "Content-Type: application/json" \
        //                             -X POST \
        //                             --data '${data}' \
        //                             ${GITLAB_RELEASE_API}
        //                         """, 
        //                         returnStdout: true
        //                     ).trim()

        //                     echo "Done attempting to create Release"

        //                     def jsonPostResponse = readJSON text: postResponse
        //                     echo "${jsonPostResponse}"

        //                 } else {
        //                     error("Unexpected result")
        //                 }

        //             }
        //         }
        //     }
        // }

        
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