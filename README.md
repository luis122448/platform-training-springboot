![Logo del Projecto](./resources/logo.png)

# Despliegue del Backend de Platform Training

Repositorio con el código fuente y los scripts necesarios para el despliegue del servidor de API REST del proyecto Platform Training.

## Características
- Implementa doble capa de seguridad con Spring Security y JWT.
- API RESTFULL con autenticación JWT y Hateoas.
- Gestión de dependencias con Gradle.
- Automatización del despliegue con Docker y Docker-Compose.

## Repositorios Relacionados

### Repositorio Actual
- [Platform-Training-SpringBoot](https://github.com/luis122448/platform-training-springboot)

### Repositorios Relacionados

Repositorio referido al FRONTEND de la aplicación.
- [Platform-Training-Angular](https://github.com/luis122448/platform-training-angular)

## Configuración del Entorno

1. **Clonar el Repositorio**
    ```bash
        git clone https://github.com/luis122448/platform-training-springboot.git
    ```

2. **Ingresar al directorio del proyecto**

    ```bash
        cd platform-training-springboot
    ```

3. **Ejecutar el script de instalación**

    ```bash
        sudo bash dev-install.sh
    ```

## Despliegue en Producción

Para el despliegue en producción se ha utilizado Docker y Docker Compose, puede revisar el archivo docker-compose.yml para conocer los detalles de la configuración.
Asimismo no se olvide de modificar las variables de entono, en asi archivo .env

1. **Ejecutar el script de despliegue**

    ```bash
        sudo bash deploy.sh
    ```

## Contribuciones
Las contribuciones son bienvenidas. Siéntete libre de mejorar este proyecto, agregar nuevas características o corregir problemas identificados. Para contribuir, crea un Pull Request o abre un Issue.

## Licencia
Este proyecto está bajo la licencia MIT License.
