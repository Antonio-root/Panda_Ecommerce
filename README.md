# Catálogo Web con Spring Boot

## Descripción
Un proyecto para gestionar un catálogo de productos utilizando Spring Boot, Java y MySQL.

## Requisitos
- JDK 23
- Maven
- MySQL 8.0

## Estructura de Archivos
ecommerce-app/
│
├── src/            
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── tienda/
│   │   │           ├── EcommerceApplication.java
│   │   │           ├── controller/
│   │   │           │   ├── ProductController.java
│   │   │           │   ├── CartController.java
│   │   │           │   └── UserController.java
│   │   │           ├── model/
│   │   │           │   ├── Product.java
│   │   │           │   ├── Cart.java
│   │   │           │   └── User.java
│   │   │           ├── repository/
│   │   │           │   ├── ProductRepository.java
│   │   │           │   ├── CartRepository.java
│   │   │           │   └── UserRepository.java
│   │   │           └── service/
│   │   │               ├── ProductService.java
│   │   │               ├── CartService.java
│   │   │               └── UserService.java
│   │   └── resources/
│   │       ├── static/
│   │       │   ├── css/
│   │       │   ├── images/
│   │       │   └── js/
│   │       ├── templates/
│   │       │   ├── index.html
│   │       │   ├── product-list.html
│   │       │   └── product-details.html
│   │       └── application.properties
└── pom.xml (o build.gradle)

## Instalación
1. Clona el repositorio:
   ```bash
   git clone https://github.com/Antonio-root/Panda_Ecommerce.git
