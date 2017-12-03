##Cari Parking - User Management Service

Provide API to create user with role admin or surveyor

### Requirement :

1. Java JDK 8
2. MySQL
3. Keyloak

### Prerequisite - MySQL :

1. Create database `usermanagement` in MySQL
2. Username `usermanagement`
3. Password `welcome1`

### Prerequisite - Keycloak :

1. Create realm `cari-parkir`
2. Create clients `backend-api`
3. Set Enabled become `true`
4. Set Standard Flow Enabled become `true`
5. Set Direct Access Grants Enabled become `true`
6. Set Valid Redirect URIs become `*`
7. Create Roles `admin` and `surveyor`
8. Check your username admin in application.yaml
9. Check your password admin in application.yaml

### How to run :

1. `cd cari-parkir-service-usermanagement/`
2. Run using maven `mvn spring-boot:run`
