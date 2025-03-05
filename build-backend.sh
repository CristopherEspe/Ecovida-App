#!/bin/bash

# Guarda el directorio actual
rootpath=$(pwd)

# Navega a la carpeta 'backend'
cd "$rootpath/backend"

# Establece las rutas de los servicios
ecovida-inventory-service_path="$PWD/ecovida-inventory-service"
ecovida-order-service_path="$PWD/ecovida-order-service"
ecovida-product-service_path="$PWD/ecovida-product-service"
ecovida-user-service_path="$PWD/ecovida-user-service"
eureka-server_path="$PWD/eureka-server"
gateway-service_path="$PWD/gateway-service"
msvc-auth_path="$PWD/msvc-auth"

echo "Building backend services"

echo "Building eureka server"
cd "$eureka-server_path"
./build.sh

echo "Building gateway service"
cd "$gateway-service_path"
./build.sh

echo "Building inventory service"
cd "$ecovida-inventory-service_path"
./build.sh

echo "Building order service"
cd "$ecovida-order-service_path"
./build.sh

echo "Building product service"
cd "$ecovida-product-service_path"
./build.sh

echo "Building user service"
cd "$ecovida-user-service_path"
./build.sh

echo "Building auth service"
cd "$auth_service_path"
./build.sh

echo "Backend services build completed"

# Regresa al directorio original
cd "$rootpath"
