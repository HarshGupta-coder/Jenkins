pipeline {
    agent {
        label 'stapp02' // Agent label where the pipeline will run
    }
    
    stages {
        stage('Build') {
            steps {
                script {
                    // Clone the repository
                    git url: 'http://git.stratos.xfusioncorp.com/sarah/mr_job.git'
                    
                    // Build the Docker image
                    sh 'docker build -t stregi01.stratos.xfusioncorp.com:5000/nginx:latest .'
                    
                    // Push the Docker image to the registry
                    sh 'docker push stregi01.stratos.xfusioncorp.com:5000/nginx:latest'
                }
            }
        }
        
        stage('Deploy') {
            steps {
                // Run the Docker container with the built image
                sh "docker run -d -p 8080:80 --name nginx-app stregi01.stratos.xfusioncorp.com:5000/nginx:latest"
            }
        }
    }
}
