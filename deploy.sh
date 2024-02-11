#!/bin/bash

# Creando directorio para certificados SSL
mkdir -p key

# Creando archivo .env
touch .env

# Archivo de propiedades
archivo="./src/main/resources/application-pdn.properties"

# Reemplazar los valores
sed -i "s/\${POSTGRES_HOST}/$POSTGRES_HOST/g" "$archivo"
sed -i "s/\${POSTGRES_DATABASE}/$POSTGRES_DATABASE/g" "$archivo"
sed -i "s/\${POSTGRES_USERNAME}/$POSTGRES_USERNAME/g" "$archivo"
sed -i "s/\${POSTGRES_PASSWORD}/$POSTGRES_PASSWORD/g" "$archivo"

echo "Valores reemplazados correctamente en $archivo"
