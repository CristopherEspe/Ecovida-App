export const environment = {
    api: {
      urls: {
        serviceUsers: '${GATEWAY_ENDPOINT:-http://localhost:8080}/user-service',
        serviceProducts: '${GATEWAY_ENDPOINT:-http://localhost:8080}/product-service',
        serviceOrders: '${GATEWAY_ENDPOINT:-http://localhost:8080}/order-service',
        serviceInventory: '${GATEWAY_ENDPOINT:-http://localhost:8080}/inventory-service',
        serviceCourses: '${GATEWAY_ENDPOINT:-http://localhost:8080}/courses-service',
      }
    },
    keycloak: {
      config: {
        url: '${KEYCLOAK_URL:-http://localhost:8090/auth}',
        realm: '${KEYCLOAK_REALM:-SpringBootKeycloak}',
        clientId: '${KEYCLOAK_CLIENT_ID:-EcovidaClient}',
      }
    }
  };
