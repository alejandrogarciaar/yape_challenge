# Challenge - Yape App
# Autor: John Alejandro Garcia Arias

## Screenshots
<img width="354" alt="Screenshot 2023-11-20 at 8 49 14 PM" src="https://github.com/alejandrogarciaar/yape_challenge/assets/8558947/3af1445b-32e9-4c87-ba8f-1506a1f02f00">
<img width="354" alt="Screenshot 2023-11-20 at 8 48 45 PM" src="https://github.com/alejandrogarciaar/yape_challenge/assets/8558947/14f0aabd-ecc4-41ed-87ff-b428e7e89676">
<img width="354" alt="Screenshot 2023-11-20 at 8 48 40 PM" src="https://github.com/alejandrogarciaar/yape_challenge/assets/8558947/12ca7cd1-4cc0-457d-a6d2-0587dd2cb6bc">

## Secciones
* [Estructura del proyecto](#estructura-del-proyecto)
* [Por qué Multi-Module](#por-qué-multi-module)
* [Pruebas Unitarias](#pruebas-unitarias)

### Estructura del proyecto

`app`: Tan simple y soñada que asi se debe mantener. Aplicación base, que conoce todos los módulos wiring que componen el grafo de depedencias. Gracias a este tipo de arquitectura es posible tener app demos para probar determinados flujos. Si el proyecto crece, es posible disminuir los tiempos de compilación agregando apps demo con un grafo reducido.

`:features`: Módulo de Kotlin. Este módulo está pensado para alojar todos los features relacionados con el proyecto.

`:feature-name`: Módulo Kotlin que contiene unidades de negocio:
1. `:feature-name-api`: Sin dependencias. Módulo que expone todos los componentes públicos que se requieren para orquestar este feature. Es importante recalcar que la navegación entre features es posible gracias a las interfaces que se exponen en este módulo. Cada feature deberá implementar exclusivamente este módulo para acceder a la interfaz y realizar la acción.
2. `:feature-name-impl`: Módulo que contiene la implementación concreta del feature. En esta capa, se puede aplicar patrones de diseño como MVVM. Este módulo no depende de detalles de implementación externos, por lo cual define y contribuye con la construcción del grafo de dependencias. Podríamos aplicar patrón repository y otros patrones mas que permitan basarnos en abstracciones y no en detalles concretos.

<img width="271" alt="Screenshot 2023-11-21 at 3 20 07 PM" src="https://github.com/alejandrogarciaar/yape_challenge/assets/8558947/8222ea94-e75b-4de4-a836-5b82b46eff41">

4. `:feature-name-wiring-impl`: Módulo sumamente importante que expone de forma transitiva el submódulo impl y api. Este módulo debe definir el como se construyen todas las abstracciones del módulo api. Cada app demo deberá implementar este módulo para componer el grafo de dagger y habilitar la navegación entre features.

Nota: Ningun feature debe conocer detalles de implementación de otro feature. Deben permanecer estrictamente desacoplados y respetar los entry point establecidos. Esta inversión de control permite tener aplicaciones altamente escalables e incrementar la velocidad del equipo, sin correr el riesgo de afectar flujos externos.

# Screenshot de la estructura por feature

<img width="279" alt="Screenshot 2023-11-21 at 1 20 53 PM" src="https://github.com/alejandrogarciaar/yape_challenge/assets/8558947/ec67679f-ed05-4236-b1af-9362c44f6777">

Ejemplo de acceso a la navegación del feature home al feature recipe detail

<img width="566" alt="Screenshot 2023-11-21 at 3 14 53 PM" src="https://github.com/alejandrogarciaar/yape_challenge/assets/8558947/a423921f-bd34-4dfa-b7c6-baf83b01d92f">

Solo es necesario conocer la interfaz, a traves de inyección de dependencias, la app base ya sabe como orquestar la navegación (no el feature)

`:libs`: Módulo de Kotlin. Este módulo está pensado para alojar todos los componentes transversales de negocio, como excepciones comunes, estilos base, widgets, entre otros.

<img width="207" alt="Screenshot 2023-11-21 at 3 18 56 PM" src="https://github.com/alejandrogarciaar/yape_challenge/assets/8558947/135a589e-3309-4863-91c7-b51ff0048877">

### Por qué Multi-Module?

1. "**Velocidad de compilación**": Todos nos hemos enfrentado a esas super Apps donde toda la logica
   de negocio, red y persistencia están alojados en el módulo de `:app`. Esta mala práctica, ademas
   de acoplar considerablemente todo los componentes del proyecto, incrementa los tiempos de
   compilación. Si el proyecto estuviera fundamentado en Multi-Module, cuando modificamos un módulo
   específico, Gradle solo compilará el módulo / módulos que fueron afectados, no todo el proyecto.

2. "**Responsabilidad única**": Cada módulo debe enforcarse en lo que realmente le corresponde. Al
   momento de seperar las "preocupaciones" proveeremos mayor cohesión y bajo acoplamiento.

3. "**Reutilización del código**": El código podrá reutilizarse en muchos flujos de negocio.

4. "**Testeable**": Los módulos de alto nivel no dependerán de módulos de bajo nivel. Nuestro código
   está basado en abstracciones faciles de mockear.

### Pruebas unitarias

Todos los componentes esenciales tienen pruebas unitarias:

- ViewModels
- UseCases
- Repositories
- Mappers

Por lo general siempre utilizo las secciones Given, When, Then para dar claridad y entendimiento del comportamiento esperado de la prueba.

<img width="738" alt="Screenshot 2023-11-21 at 3 25 27 PM" src="https://github.com/alejandrogarciaar/yape_challenge/assets/8558947/639e633f-a15e-4991-be69-1abaa7dd07cc">

