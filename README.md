# 🔐 Auth API - Spring Boot JWT Authentication

API de autenticación desarrollada con **Spring Boot** que implementa un sistema completo de **login, registro y refresh token usando JWT**.

Este proyecto demuestra una arquitectura backend modular con autenticación segura basada en **JSON Web Tokens** y manejo de **refresh tokens almacenados en base de datos**.

---

# 🚀 Tecnologías utilizadas

* Java 17+
* Spring Boot
* Spring Security
* JWT (JSON Web Token)
* Spring Data JPA
* Hibernate
* Lombok
* Maven
* Base de datos SQL (MySQL / PostgreSQL / H2)

---

# 📌 Características

* Registro de usuarios
* Login con generación de JWT
* Refresh Token para renovar sesiones
* Protección de endpoints con Spring Security
* Arquitectura modular por dominio
* Validación de usuarios desde base de datos

---

# 🏗 Arquitectura del proyecto

```
com.jara.authapi
│
├── config
│   └── SecurityConfig
│
├── auth
│   ├── controller
│   │   └── AuthController
│   ├── service
│   │   ├── AuthService
│   │   └── AuthServiceImpl
│   └── dto
│       ├── LoginRequest
│       ├── LoginResponse
│       ├── RegisterRequest
│       ├── RefreshTokenRequest
│       └── RefreshTokenResponse
│
├── security
│   ├── JwtAuthenticationFilter
│   ├── JwtService
│   └── CustomUserDetailsService
│
├── user
│   ├── model
│   │   ├── User
│   │   └── Role
│   └── repository
│       └── UserRepository
│
├── token
│   ├── model
│   │   └── RefreshToken
│   ├── repository
│   │   └── RefreshTokenRepository
│   └── service
│       └── RefreshTokenService
│
└── AuthapiApplication
```

---

# 🔐 Flujo de autenticación

1️⃣ Usuario se registra.

```
POST /auth/register
```

Se crea un nuevo usuario en la base de datos con contraseña encriptada.

---

2️⃣ Usuario inicia sesión.

```
POST /auth/login
```

Si las credenciales son válidas se generan:

* Access Token (JWT)
* Refresh Token

Ejemplo de respuesta:

```
{
  "accessToken": "jwt_token",
  "refreshToken": "uuid_token"
}
```

---

3️⃣ Acceso a endpoints protegidos

Para acceder a endpoints protegidos se debe enviar el token en el header:

```
Authorization: Bearer ACCESS_TOKEN
```

Ejemplo:

```
GET /api/saludo
```

---

4️⃣ Renovar sesión

Cuando el access token expira se puede obtener uno nuevo usando el refresh token.

```
POST /auth/refresh
```

Body:

```
{
  "refreshToken": "TOKEN"
}
```

Respuesta:

```
{
  "accessToken": "nuevo_token"
}
```

---

# 📬 Endpoints principales

| Método | Endpoint       | Descripción                  |
| ------ | -------------- | ---------------------------- |
| POST   | /auth/register | Registrar usuario            |
| POST   | /auth/login    | Login de usuario             |
| POST   | /auth/refresh  | Generar nuevo access token   |
| GET    | /api/saludo    | Endpoint protegido de prueba |

---

# ▶️ Cómo ejecutar el proyecto

### 1 Clonar repositorio

```
git clone https://github.com/tuusuario/auth-api.git
```

### 2 Entrar al proyecto

```
cd auth-api
```

### 3 Ejecutar aplicación

```
mvn spring-boot:run
```

La API se ejecutará en:

```
http://localhost:8080
```

---

# 🧪 Probar la API

Se recomienda usar:

* Postman
* Insomnia
* Thunder Client

Flujo recomendado:

1. Registrar usuario
2. Hacer login
3. Usar access token para acceder a endpoints protegidos
4. Usar refresh token para renovar sesión

---

# 📚 Aprendizajes del proyecto

Este proyecto demuestra:

* Implementación de JWT con Spring Security
* Manejo de Refresh Tokens
* Autenticación stateless
* Arquitectura modular backend
* Seguridad en APIs REST

---

# 📄 Licencia

Proyecto de práctica para aprendizaje de **Spring Boot y seguridad en APIs REST**.
