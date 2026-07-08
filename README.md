# 🐳 Guía de Instalación - JORGE_APP

## Requisitos Previos

Tus compañeros necesitan tener instalado:
- **Docker Desktop**: [Descargar aquí](https://www.docker.com/products/docker-desktop)
  - ✅ Incluye Docker y Docker Compose

Además, para este proyecto no es necesario instalar Node.js, npm o Java en la máquina local si van a trabajar con Docker.

Verifica que funcionan:
```bash
docker --version
docker compose version
```

---

## 🚀 Instalación en 3 Pasos

### 1️⃣ Clonar el repositorio

```bash
git clone https://github.com/tu-usuario/JORGE_APP.git
cd JORGE_APP
```

### 2️⃣ Ejecutar Docker Compose para desarrollo

Para trabajar en frontend y backend sin instalar Node.js en tu máquina, usa el flujo de desarrollo con Docker:

```bash
# Primera vez:
docker compose up --build -d backend frontend-dev

# Luego cada vez que quieras levantarlo de nuevo:
docker compose up -d backend frontend-dev
```

Si solo quieres levantar la versión de producción del frontend:

```bash
docker compose up -d backend frontend
```

Si quieres detener los contenedores sin borrarlos:

```bash
docker compose stop
```

Si quieres detenerlos y remover redes/containers:

```bash
docker compose down
```

Espera a que los servicios queden listos. En desarrollo:

```text
✓ backend listo en http://localhost:9090
✓ frontend de desarrollo listo en http://localhost:4200
```

### 3️⃣ Acceder a la aplicación

Para desarrollo full-stack:
- **Frontend** (Angular en desarrollo): http://localhost:4200
- **Backend** (Spring Boot): http://localhost:9090

Para la versión de producción del frontend:
- **Frontend** (Nginx): http://localhost

¡Listo! ✅

---

## 📋 Comandos Útiles

### Ver todo en primer plano (con logs)
```bash
docker-compose up
```

### Ejecutar en segundo plano
```bash
docker-compose up -d
```

### Ver logs en tiempo real
```bash
docker-compose logs -f
```

### Ver logs de un servicio específico
```bash
docker-compose logs -f backend
docker-compose logs -f frontend
```

### Detener la aplicación
```bash
docker-compose down
```

### Detener Y limpiar volúmenes (incluye base de datos)
```bash
docker-compose down -v
```

### Reconstruir después de cambios en código
```bash
docker-compose up --build
```

### Ver contenedores corriendo
```bash
docker ps
```

### Acceder a un contenedor
```bash
docker compose exec backend sh
docker compose exec frontend-dev sh
```

### Desarrollo frontend y backend con Docker
Si no quieren instalar Node.js en su máquina, este es el flujo recomendado:

```bash
docker compose up --build -d backend frontend-dev
```

Luego, para trabajar dentro del contenedor del frontend:

```bash
docker compose exec frontend-dev sh
```

Dentro del contenedor ejecutar:

```bash
ng g s services/policy
# o
ng g c component/policy
# o
ng g interface models/policy
```

También se puede generar directamente sin entrar al contenedor:

```bash
docker compose run --rm frontend-dev sh -lc "ng g s services/policy"
```

El servicio `frontend-dev` monta la carpeta del proyecto en `/app` y permite trabajar con Angular CLI sin instalar dependencias locales.

---

## 🏗️ Arquitectura

```
┌─────────────────────────────────────────┐
│          JORGE_APP (Docker)             │
├─────────────────────────────────────────┤
│                                         │
│  ┌──────────────────┐  ┌──────────────┐ │
│  │  Frontend        │  │   Backend    │ │
│  │  Angular 22      │  │ Spring Boot  │ │
│  │  + Nginx         │  │ Java 17      │ │
│  │  Puerto 80       │  │ Puerto 9090  │ │
│  └──────────────────┘  └──────────────┘ │
│                                         │
└─────────────────────────────────────────┘
```

### Backend
- **Tecnología**: Spring Boot 4.1.0 + Java 17
- **Base de datos**: H2 (en memoria)
- **Puerto**: 9090
- **Endpoints**:
  - `http://localhost:9090/api/policies` - Políticas
  - `http://localhost:9090/h2-console` - Consola H2

### Frontend
- **Tecnología**: Angular 22 + Node.js 22
- **Servidor**: Nginx (optimizado para producción)
- **Puerto**: 80
- **URL**: http://localhost

---

## 🔗 Conectar Frontend con Backend

Si necesitas hacer llamadas HTTP del frontend al backend, usa:

```typescript
// En lugar de hardcodear localhost:
http.get('http://localhost:9090/api/...')

// Usa en Docker:
http.get('http://backend:9090/api/...')

// O mejor aún, configura una variable de entorno:
const apiUrl = environment.production 
  ? 'http://backend:9090' 
  : 'http://localhost:9090';
```

---

## 🐛 Solución de Problemas

### ❌ "Puerto 80 ya está en uso"
```bash
# Cambiar en docker-compose.yml
# De:  "80:80"
# A:   "8080:80"

docker-compose up
```

Luego accede a http://localhost:8080

### ❌ "No puede conectar backend con frontend"
- Asegúrate de usar `http://backend:9090` (no `localhost`)
- Verifica que ambos contenedores estén corriendo: `docker ps`

### ❌ "Los cambios de código no se ven"
```bash
docker-compose down
docker-compose up --build
```

### ❌ "Permission denied" (en Mac/Linux)
```bash
sudo docker-compose up
# O agrega tu usuario al grupo docker
sudo usermod -aG docker $USER
```

### ❌ Limpiar todo y empezar de cero
```bash
docker-compose down -v
docker system prune -a
docker-compose up --build
```

---

## 💾 Persistencia de Datos

⚠️ **Importante**: Con la configuración actual, la base de datos H2 se pierde cuando paras Docker.

Para persistencia, agrega un volumen en `docker-compose.yml`:

```yaml
services:
  backend:
    # ... resto de config
    volumes:
      - db-data:/app/data

volumes:
  db-data:
```

Y actualiza `application.properties`:
```properties
spring.datasource.url=jdbc:h2:file:/app/data/aseguradora
```

---

## 📝 Estructura de Archivos

```
JORGE_APP/
├── docker-compose.yml          # Configuración de servicios
├── README.md                   # Este archivo
│
├── aseguradora/                # Backend
│   ├── Dockerfile              # Imagen del backend
│   ├── .dockerignore           # Archivos excluidos
│   ├── pom.xml                 # Dependencias Maven
│   └── src/                    # Código fuente
│
└── aseguradoraFrondEnd/
    └── AseguradoraFront/       # Frontend
        ├── Dockerfile          # Imagen del frontend
        ├── .dockerignore       # Archivos excluidos
        ├── nginx.conf          # Configuración web
        ├── package.json        # Dependencias npm
        └── src/                # Código fuente Angular
```

---

## 🔐 Consideraciones de Seguridad

Para **desarrollo local**: ✅ Está bien así

Para **producción**, considera:
- ✅ Agregar HTTPS/SSL
- ✅ Configurar CORS adecuadamente
- ✅ Usar variables de entorno secretas
- ✅ Usar base de datos externa (PostgreSQL, MySQL)
- ✅ Agregar autenticación y autorización
- ✅ Configurar límites de recursos (CPU, memoria)

---

## 📞 Necesitas Ayuda?

1. **Verifica que Docker esté corriendo** en tu máquina
2. **Revisa los logs**: `docker-compose logs`
3. **Intenta limpiar todo**: `docker-compose down -v && docker-compose up --build`
4. **Contacta al equipo** si persiste el problema

---

## ✨ Resumen Rápido

| Acción | Comando |
|--------|---------|
| Instalar | `git clone ... && docker-compose up` |
| Ver logs | `docker-compose logs -f` |
| Parar | `docker-compose down` |
| Reconstruir | `docker-compose up --build` |
| Limpiar todo | `docker-compose down -v && docker system prune -a` |

¡Happy coding! 🚀
