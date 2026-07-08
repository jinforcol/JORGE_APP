# Stage 1: Build
FROM node:22-alpine AS builder

WORKDIR /app

# Copiar archivos de dependencias
COPY package*.json ./

# Instalar dependencias
RUN npm install

# Copiar código fuente
COPY . .

# Compilar la aplicación
RUN npm run build

# Stage 2: Runtime (Nginx para servir)
FROM nginx:alpine

# Copiar configuración de Nginx
COPY nginx.conf /etc/nginx/nginx.conf

# Copiar archivos compilados del stage anterior
COPY --from=builder /app/dist/AseguradoraFront/browser /usr/share/nginx/html

# Exponer puerto
EXPOSE 80

# Comando para ejecutar
CMD ["nginx", "-g", "daemon off;"]
