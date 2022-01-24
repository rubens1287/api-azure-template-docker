# Download imagem do ambiente
# docker run --rm -ti --name zalenium -d -p 4444:4444 -e PULL_SELENIUM_IMAGE=true -v /var/run/docker.sock:/var/run/docker.sock -v /tmp/videos:/home/seluser/videos --privileged dosel/zalenium start
#Contruir imagem
#    docker build -t back-end -f ./Dockerfile .
#Rodar os testes
#    docker run -v "$PWD/target:/usr/target" back-end mvn test -Denv=qa
FROM adoptopenjdk/maven-openjdk11
WORKDIR /usr
COPY . /usr
ENV TZ=America/Sao_Paulo
RUN mvn dependency:go-offline -B