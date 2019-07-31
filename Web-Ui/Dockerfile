#STEP 1
FROM node:latest as node
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build --prod

#STEP 2
FROM nginx:alpine
COPY --from=node /app/dist/Web-Ui /usr/share/nginx/html

CMD ng serve --host 0.0.0.0

