# Challenge - Yape App
# Autor: John Alejandro Garcia Arias

## Screenshots

## Secciones

* [Estructura del proyecto](#estructura-del-proyecto)
* [Por qué Multi-Module](#por-qué-multi-module?)
* [Pruebas Unitarias](#pruebas-unitarias)

### Estructura del proyecto

`app`: Módulo app. Este módulo contiene:

1. Tan simple y soñada que asi se debe mantener. Conoce todos los módulos wiring que componen el grafo de depedencias.

`:features`: Módulo de Kotlin. Este módulo está pensado para alojar todos los features relacionados con el proyecto.

1. `:feature-name`: Módulo Kotlin que contiene lo siguiente:
   -`:feature-name-api`: Módulo que expone todos los componentes públicos que se requieren para orquestar este feature.
   -`:feature-name-impl`: Módulo que contiene la implementación del flujo del home. En esta capa, se implementó MVVM. Este módulo tiene la responsabilidad de exponer sus propias dependencias al grafo de dagger.
   -`:feature-name-wiring-impl`: Módulo que expone la forma en que dagger obtendrá todas las dependencias del módulo api.

2. `:recipe-details`: Módulo Android, Unidad de negocio / módulo de Detalles del item.

`:libs`: Módulo de Kotlin. Este módulo está pensado para alojar todos los componentes de negocio, componentes de red y de persistencia. Está compuesto por lo siguiente:

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

5. "**Separación entre framework y lenguaje**": Es posible crear módulos específicos para Android y
   para Kotlin. Esto finalmente nos permitirá disminuir el acoplamiento con el framework.

### Pruebas unitarias

Todos los componentes esenciales tienen pruebas unitarias:

- ViewModels
- UseCases
- Repositories
- Mappers