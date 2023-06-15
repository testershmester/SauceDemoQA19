pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M3"
    }

    parameters {
        gitParameter branchFilter: 'origin/(.*)', defaultValue: 'master', description: 'Select branch', name: 'BRANCH', type: 'PT_BRANCH'
        credentials credentialType: 'com.cloudbees.plugins.credentials.impl.UsernamePasswordCredentialsImpl', defaultValue: '03ff2eb1-1d26-49cd-a6ed-3997381d0e62', name: 'STANDARD_USER_CREDS', required: true
    }

    stages {
        stage('Build') {
            steps {
                withCredentials([usernamePassword(credentialsId: "${params.STANDARD_USER_CREDS}", passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]) {
                    // Get some code from a GitHub repository
                    git branch: "${params.BRANCH}", url: 'https://github.com/testershmester/SauceDemoQA19.git'

                    bat 'docker build -t my-maven-project .'

                    // Run Maven on a Unix agent.
                    // sh "mvn -Dmaven.test.failure.ignore=true clean package"

                    // To run Maven on a Windows agent, use
                    bat "mvn clean test -DsuiteXmlFile=src/test/resources/smoke.xml"
                }
            }
        }
//         stage('Reports') {
//             steps {
//                 script {
//                     allure([
//                         includeProperties: false,
//                         jdk: '',
//                         properties: [],
//                         reportBuildPolicy: 'ALWAYS',
//                         results: [[path: 'target/allure-results']]
//                     ])
//                 }
//             }
//         }
    }
}
