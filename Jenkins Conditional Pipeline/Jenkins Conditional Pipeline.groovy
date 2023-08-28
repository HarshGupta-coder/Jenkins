pipeline {
    agent {
        label 'ststor01' // Agent label where the pipeline will run
    }
    
    stages {
        stage('Deploy') {
            when {
                expression {
                    params.BRANCH == 'master' || params.BRANCH == 'feature'
                }
            }
            steps {
                script {
                    def repositoryPath = '/var/www/html/'
                    
                    if (params.BRANCH == 'master') {
                        // Clone the repository and checkout the 'master' branch
                        git branch: "master",
                            url: "http://git.stratos.xfusioncorp.com/sarah/web_app.git"
                    } else if (params.BRANCH == 'feature') {
                        // Clone the repository and checkout the 'feature' branch
                        git branch: "feature",
                            url: "http://git.stratos.xfusioncorp.com/sarah/web_app.git"
                    }
                    
                    // Copy the build artifacts to the web server directory
                    sh "cp -r /var/www/html/workspace/xfusion-webapp-job/* /var/www/html/"
                }
            }
        }
    }
}
