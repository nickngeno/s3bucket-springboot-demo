# Simple Spring Boot Application for Image Storage in AWS S3 Bucket and MySQL Database

This is a simple Spring Boot application that demonstrates how to store images in an AWS S3 bucket and save the URL in a MySQL database. The application consists of the following components:

- **Spring Boot**: A lightweight framework for building Java-based web applications.
- **AWS S3**: Amazon Web Services Simple Storage Service, a scalable object storage service.
- **MySQL**: A popular open-source relational database management system.

## Prerequisites

Before running the application, make sure you have the following prerequisites:

- Java Development Kit (JDK) 8 or later installed on your system.
- Apache Maven installed on your system.
- An AWS account with an S3 bucket created.
- A MySQL database server running.

## Getting Started

1. Clone the repository:

```
git clone https://github.com/your-username/your-repository.git
```

2. Navigate to the project directory:

```
cd your-repository
```

3. Build the project using Maven:

```
mvn clean install
```

4. Configure the `application.properties` file with your AWS S3 and MySQL database credentials:

```
# AWS S3 configuration
aws.region=your-aws-region
aws.access-key=your-aws-access-key-id
aws.secret-key=your-aws-secret-access-key
aws.bucket-name=your-s3-bucket-name

# MySQL database configuration
spring.datasource.url=jdbc:mysql://your-mysql-server:3306/your-database-name
spring.datasource.username=your-mysql-username
spring.datasource.password=your-mysql-password
```

5. Run the application:

```
mvn spring-boot:run
```

The application will start, and you can use the provided REST endpoints to upload images to the S3 bucket and save the URL in the MySQL database.

## REST Endpoints

The application provides the following REST endpoints:

- `POST /save-image`: Upload an image to the S3 bucket and save the URL in the MySQL database.

## Conclusion

This simple Spring Boot application demonstrates how to store images in an AWS S3 bucket and save the URL in a MySQL database. It can be used as a starting point for building more complex applications that require image storage and retrieval.

## Happy Coding!🚀
