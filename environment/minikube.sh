#!/usr/bin/env bash

#Minikube requires Virtualbox to work.
curl -Lo kubectl http://storage.googleapis.com/kubernetes-release/release/v1.5.1/bin/darwin/amd64/kubectl && chmod +x kubectl && sudo mv kubectl /usr/local/bin/
curl -Lo minikube https://storage.googleapis.com/minikube/releases/latest/minikube-darwin-amd64 && chmod +x minikube && sudo mv minikube /usr/local/bin/

