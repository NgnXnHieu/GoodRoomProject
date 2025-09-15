@echo off
REM Thiết lập biến môi trường JAVA_HOME
set JAVA_HOME=C:\Program Files\Java\jdk-17

REM Di chuyển đến thư mục dự án
cd /d "C:\Users\vuvan\Downloads\homestay\homestay\BE\JAVANC_N12"

REM Chạy ứng dụng Spring Boot
mvnw spring-boot:run

pause