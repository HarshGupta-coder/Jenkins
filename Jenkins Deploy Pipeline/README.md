# Jenkins-GitHub Integration for Apache Service Management

This repository contains the solution I implemented to automate the management of the Apache service on multiple app servers using Jenkins and GitHub integration.

## Task Overview

The objective of this project was to develop a system that automatically restarts the Apache service on all app servers within the Stratos Datacenter upon successful deployments. The solution utilizes Jenkins chained builds and downstream jobs for efficient orchestration.

## Project Details

### 1. Jenkins Job for Deployment (nautilus-app-deployment)

- Created a Jenkins job named *nautilus-app-deployment*.
- Configured the job to pull changes from the *master* branch of the *web* repository on the *Storage server*.
- Utilized the "Poll SCM" feature to automatically detect changes triggered by developer commits.
- Specified the shared directory path */var/www/html* to propagate changes across all app servers.

### 2. Service Management Job (manage-services)

- Developed a downstream job, *manage-services*, dependent on the *nautilus-app-deployment* job.
- Set the downstream job to trigger exclusively upon the stability of the upstream job (*nautilus-app-deployment*).
- Implemented tasks to restart the *httpd* service on all app servers post-deployment, ensuring consistent service updates.
- Ensured service restarts were contingent upon successful deployments, preventing disruptions.

## Usage

To replicate this project's functionality in your environment, follow these steps:

1. Clone this repository to your local machine.
2. Set up Jenkins with the required plugins for Git integration.
3. Configure Jenkins jobs based on the provided project details.
4. Customize job configurations as needed for your specific environment.
5. Run the jobs and observe the automated Apache service management.


