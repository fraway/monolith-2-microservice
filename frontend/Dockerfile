FROM node:16-alpine AS builder

WORKDIR /build

COPY package.json package-lock.json ./

RUN npm i --force


COPY . .

RUN npm run build


FROM nginx:alpine

WORKDIR /app

COPY --from=builder /build/dist/frontend /usr/share/nginx/html