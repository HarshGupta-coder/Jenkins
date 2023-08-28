pipeline {
    agent any // Use any available agent to run the pipeline
    
    stages {
        stage('Deploy') {
            steps {
                // Clone the repository and checkout the 'master' branch
                git branch: "master",
                    url: "http://git.stratos.xfusioncorp.com/sarah/web.git"
                
                // Copy the files to the remote server using scp
                sh "scp * natasha@ststor01:/var/www/html"
            }
        }
        
        stage('Test') {
            steps {
                script {
                    try {
                        // Perform a curl request to test the website
                        sh "curl -f http://stlb01:8091"
                    } catch (Exception e) {
                        currentBuild.result = 'FAILURE'
                        error("Website is not accessible or test failed.")
                    }
                }
            }
        }
    }
}
