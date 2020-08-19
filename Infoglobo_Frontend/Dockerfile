FROM node:lts-alpine
WORKDIR /web
COPY package.json ./
RUN npm install
EXPOSE 3000
CMD ["npm","run","dev"]