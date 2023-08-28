pipeline {
    agent {
        label 'app02' // Agent label where the pipeline will run
    }
    
    stages {
        stage('Build') {
            steps {
                // Clone the repository and checkout the 'master' branch
                git branch: "master",
                    url: "http://git.stratos.xfusioncorp.com/sarah/web.git"
                
                // Build the Docker image
                sh 'docker build -t stregi01.stratos.xfusioncorp.com:5000/nginx:latest .'
                
                // Push the Docker image to the registry
                sh 'docker push stregi01.stratos.xfusioncorp.com:5000/nginx:latest'
            }
        }
    }
}
