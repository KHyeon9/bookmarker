echo "K8s + Cluster Initializing .."

# kind를 사용하여 클러스터 생성 (0-kind-config.yaml 설정 사용)
kind create cluster --name bookmarker --config 0-kind-config.yaml

echo "\n--------------------------------------------------------\n"

echo "NGINX Ingress installing ..."

# NGINX Ingress Controller 설치 (kind 환경용)
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/refs/heads/main/deploy/static/provider/kind/deploy.yaml

echo "\n--------------------------------------------------------\n"

echo "NGINX Ingress Reading...."

# 10초 대기 (Ingress Controller가 생성되는 동안 기다림)
sleep 10

# Ingress Controller pod가 준비될 때까지 대기
kubectl wait --namespace ingress-nginx \
  --for=condition=ready pod \
  --selector=app.kubernetes.io/component=controller \
  --timeout=90s

echo "\n"

echo "NGINX Final ..."