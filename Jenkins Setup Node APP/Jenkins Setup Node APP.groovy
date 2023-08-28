pipeline {
    agent {
        label 'stapp01' // Agent label where the pipeline will run
    }
    
    stages {
        stage('Build') {
            steps {
                // Clone the repository and checkout the 'master' branch
                git branch: "master",
                    url: "http://git.stratos.xfusioncorp.com/sarah/web.git"
                
                // Build the Docker image
                sh 'docker build -t stregi01.stratos.xfusioncorp.com:5000/node-app:latest .'
                
                // Push the Docker image to the registry
                sh 'docker push stregi01.stratos.xfusioncorp.com:5000/node-app:latest'
            }
        }
        
        stage('Deploy') {
            steps {
                // Run the Docker container with the built image
                sh "docker run -d --name node-app -p 8080:8080 stregi01.stratos.xfusioncorp.com:5000/node-app:latest"
            }
        }
    }
}
