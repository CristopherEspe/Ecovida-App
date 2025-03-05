@echo off
pushd %~dp0
set rootpath=%CD%
popd

cd %rootpath%\backend
set ecovida-inventory-service_path=%CD%\ecovida-inventory-service
set ecovida-order-service_path=%CD%\ecovida-order-service
set ecovida-product-service_path=%CD%\ecovida-product-service
set ecovida-user-service_path=%CD%\ecovida-user-service
set eureka-server_path=%CD%\eureka-server
set gateway-service_path=%CD%\gateway-service
set msvc-auth_path=%CD%\msvc-auth


chmod +x %ecovida-inventory-service_path%\build.cmd
chmod +x %ecovida-order-service_path%\build.cmd
chmod +x %ecovida-product-service_path%\build.cmd
chmod +x %ecovida-user-service_path%\build.cmd
chmod +x %eureka-server_path%\build.cmd
chmod +x %gateway-service_path%\build.cmd
chmod +x %msvc-auth_path%\build.cmd

echo "Building backend services"

echo "Building inventory service"
cd %ecovida-inventory-service_path%
@Call build.cmd

echo "Building order service"
cd %ecovida-order-service_path%
@Call build.cmd

echo "Building product service"
cd %ecovida-product-service_path%
@Call build.cmd

echo "Building user service"
cd %ecovida-user-service_path%
@Call build.cmd

echo "Building eureka server"
cd %eureka-server_path%
@Call build.cmd

echo "Building gateway service"
cd %gateway-service_path%
@Call build.cmd

echo "Building auth service"
cd %msvc-auth_path%
@Call build.cmd

echo "Backend services build completed"
cd %rootpath%