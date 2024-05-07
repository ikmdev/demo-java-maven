#!groovy
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
        choice(name: 'action', choices: ['start','resume','finish'], description: 'Build Action. Start = starts new feature, Resume = resumes feature after finish, Finish = ends feature. Finish/Resume - feature branch must already exist.')
        string(name: 'feature_branch', defaultValue: '', description: 'Name of branch without feature/ prefix. Must follow ABC-123_description format. ABC = JIRA prefix.')
    }

    stages {

        stage('Setup') {
            steps {
                sh """
                printenv
                echo Action ${params.action} with ${params.feature_branch}
                """

                script {
                    pomModel = readMavenPom(file: 'pom.xml')
                    pomVersion = pomModel.getVersion()
                    isSnapshot = pomVersion.contains("-SNAPSHOT")
                    releaseVersion = pomVersion.split("-")[0]

                    feature_name = feature_branch.replaceAll("/s", "_")
                }
            }
        }

        stage('Start Branch') {
            when {
                branch 'main'
                expression { return params.action == 'start' }
                expression { return params.feature_branch }
            }
            
            steps {
                withCredentials([gitUsernamePassword(credentialsId: GITLAB_CREDS_ID, gitToolName: 'git')]) {
                    sh """
                    git pull -p
                    git checkout -b feature/${feature_name}
                    mvn versions:set -DnewVersion=${releaseVersion}-${feature_name}-SNAPSHOT -DgenerateBackupPoms=false
                    git commit -am "Update feature version to ${releaseVersion}-${feature_name}-SNAPSHOT"
                    git push -u origin feature/${feature_name}
                    """
                }
            }
        }

        stage('Resume Branch') {
            when {
                expression { return params.action == 'resume' }
            }
            
            steps {
                sh """
                echo Resume Branch ${params.feature_branch}
                """
            }
        }

        stage('End Branch') {
            when {
                expression { return params.action == 'finish' }
            }
            
            steps {
                sh """
                echo End Branch ${params.feature_branch}
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
