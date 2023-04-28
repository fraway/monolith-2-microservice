FROM node:16-alpine

WORKDIR /build

COPY . .

RUN npm ci

RUN npm run build


FROM nginx:alpine

WORKDIR /app

COPY --from=0 /build/dist/fronted /usr/share/nginx/html