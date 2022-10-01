FROM node

WORKDIR /usr/src/app

COPY ./dashboard/package*.json ./

RUN npm i -y

COPY ./dashboard .

EXPOSE 3000

CMD [ "npm", "run", "dev"]
