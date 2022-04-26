# Simple event-oriented Spring Boot MVC microservices on "ping-pong" game example
There are 2 microservices (**ping-service** and **pong-service**) with next settings:
1) Url of services is: http://localhost:${service-port}/${service-url-root}
/${single-controller-request-mapping};
2) User can start or stop game (interaction between microservices) with sending empty
POST-request on ${url}/start and ${url}/stop url;
3) When response is gotten from another service, **ping-service** print only "Ping"
and **pong-service** print only "Pong" on console.

# Preparing environment

1) Install [Docker](https://www.docker.com/get-started/)
2) Install [Kubectl](https://kubernetes.io/docs/reference/kubectl/) and 
[Minikube](https://kubernetes.io/docs/tasks/tools/) (optional)

## Docker
1. Download sources (code & artefacts for assembly) from this repository:
* git clone https://github.com/IlnurNasybullin/TestSpringBootPingPongMicroservicesWithDocker.git
2. Open terminal and set location to root of this project
3. Build docker-compose file:
* docker-compose build
4. Start built images:
* docker-compose up
5. Servers are started, you can play:)

## Minikube
1. Start minikube with docker driver:
* minikube start --vm-driver=docker
2. Deploy all services:
* kubectl apply -f ping-pong-deployment.yml
3. Show started [pod](https://kubernetes.io/docs/concepts/workloads/pods/) names:
* kubectl get pod
4. Expose port for one of those servers:
* kubectl port-forward ${pod name} 9000:9000
5. Port is exposed, you can interact with server in localhost 
(for example, with [Postman API](https://www.postman.com/))
