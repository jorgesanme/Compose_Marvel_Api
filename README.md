# This proyect 

Una app construida con Kotlin y **Jetpack Compose** para consumir la API de [Marvel Comics](https://developer.marvel.com/)

## DESCRIPTION
Se desea demostrar los conocimientos en la creación de apps que consuman datos desde un backEnd. Para ello se ha diseñado una **Arquitectura Clean** usando los patrones **SOLID**.
Con respecto al patron de presentación, se ha optado por el  **MVVM**  y las vistas en **JETPACK COMPOSE**.

### Pagination & Single source of Truth
Esta app descarga un listado de personajes de comics desde la Api. La respuesta de la misma es un listado de 20 personajes que se almacena en base de datos local (**Room**).  
Desde de la base de datos se optiene el listado y se limita a solo 5 personajes, para que se pueda mostrar correctamente en un Horizontal Pager. Al finalizar la muestra de esos 5 personajes, se vuelven a cargar otros tantos desde la DDBB.
Cuando se alcanza el limite de persojes existentes en la DDBB, se vuelve a solicitar a la API otro conjunto de personajes. Reiniciando posteriormente el ciclo de presentación, pero con el primer nuevo personaje descargado.

Un detalle a tener en cuenta, cada uno de los personajes puede ser marcado como ***Favorito*** o asignarle un ***Nickname***. Estos valores se guardan en la DDBB.


## PROYECT DESCRIPTION

El proyecto se construye con una arquitectura por capas. Las mismas estan localizadas en diferentes modulos:
* App
* data
* domain
* useCases

### Dependency Injection

Para suministrar la injeción de dependencias, se ha obtado por usar [Hilt](https://dagger.dev/hilt/).  



### Navigation

Se configura un conjunto de objetos o data class serializables, que conformarán los detinos de la navegacion. En este caso: 
* MainScreen -> object
* DetailsScreen -> data class (val itemId)

Estos detinos son asignados a los composables que contiene el NavHost(). De esta forma se consigue una navegación fluida y controlable a base de lambdas.

El usuario puede alternar entre los distintos personajes, usando el swip (deslizar lateral), las flechas (botones) de navegación o el indicador de pagina (Pager Circle Indicator).  


## IMAGEN
| Permisos | Cargo de Datos | Detalles |
| --- | --- | --- |
|<img src="https://github.com/jorgesanme/Compose_Marvel_Api/blob/main/images/open.gif" width="160" height="350" />|<img src="https://github.com/jorgesanme/Compose_Marvel_Api/blob/main/images/segundo.gif" width="160" height="350" />|
<img src="https://github.com/jorgesanme/Compose_Marvel_Api/blob/main/images/detalles.gif" width="160" height="350" />|
<img src="https://github.com/jorgesanme/Compose_Marvel_Api/blob/main/images/reload.gif" width="160" height="350" />|

---


