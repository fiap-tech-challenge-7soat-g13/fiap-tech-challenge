# FIAP Tech Challenge | Pós Tech

## Propósito
Fornecer um sistema para gestão de clientes, produtos e pedidos para uma lanchonete.

## Stack utilizada
* PostgreSQL
* Java
* Docker

## Pré-requisitos
* Ter o Docker instalado para criação e execução das imagens

## Execução
Para realizar o build e iniciar a aplicação basta executar o comando abaixo na raiz do projeto:
```bash
docker-compose up
```

## Documentação da API REST
Uma vez que a aplicação já esteja em execução, a documentação da API REST pode ser acessada através da seguinte URL:  
http://localhost:8080/swagger-ui/index.html

## Execução local no Minikube
1. Instale o Minikube em sua máquina, seguindo as instruções do site oficial:
https://minikube.sigs.k8s.io/docs/start
2. Inicie o Minikube:
```bash
minikube start
```
3. Habilite o metric-server no Minikube, para que o autoscale possa funcionar corretamente:
```bash
minikube addons enable metrics-server
```
4. Instale o Helm, seguindo as instruções do site oficial: https://helm.sh/docs/intro/install/
5. Utilizando o Helm, instale o PostgreSQL no Kubernetes:
```bash
helm install database oci://registry-1.docker.io/bitnamicharts/postgresql --set auth.database=taste_food --set auth.password=postgres
```
6. Dentro do diretório do projeto, aplique todos os arquivos do Kubernetes:
```bash
kubectl apply -f k8s
```
7. Descubra o IP do Minikube, que precisamos para acessar a aplicação:
```bash
minikube ip
```
8. Se tudo ocorrer normalmente, a aplicação já poderá ser acessada através do IP exibido, na porta 30000.
9. Instale o K6, seguindo as instruções do site oficial: https://grafana.com/docs/k6/latest/set-up/install-k6/
10. Execute o teste de carga na aplicação e será possível observar o autoscale em ação:
```bash
# - O IP utilizado no comando deve ser o IP do Minikube, obtido através dos comandos anteriores.
# - Os parâmetros 'vus' e 'duration' podem ser ajustados de acordo com a carga desejada no teste.
k6 run -e HOSTNAME=192.168.49.2:30000 --vus 100 --duration 30s script.js
```

## Autores
* Cristiano de Barros
* Graziela Göedert de Souza