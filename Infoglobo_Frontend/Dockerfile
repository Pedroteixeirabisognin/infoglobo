FROM node:lts-alpine

#Diretório raiz do projeto no container
WORKDIR /web

#Copia os arquivos de package do node para a pasta raiz do container.
COPY package*.json ./

#Roda o npm install com o package.json do container 
RUN npm install

#Copia todo o conteudo da pasta raiz do projeto para o container.
COPY . .

#Abre a porta utilizada pelo container.
EXPOSE 3000

#Roda a aplicação dentro do container.
CMD ["npm","run","dev"]


#Para montar a imagem pelo docker use "docker build -t nomecontainer/dockervue ."
#Para rodar a aplicação "docker run -p 3000:3000 -d nomeContainer/dockerVue"
#"docker ps" para listar os containers utilizados 
#"docker stop CONTAINER_ID" para parar o container