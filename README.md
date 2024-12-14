# runeterra-streams
Apache Kafka-based infrastructure to manage and analyze League of Legends character data, enabling real-time processing of changes, statistics, and insights for players and esports analysts.

Un sistema avanzado basado en Apache Kafka diseñado para recopilar, procesar y analizar datos de los personajes del juego League of Legends. Este proyecto tiene como objetivo centralizar el manejo de cambios, estadísticas y eventos relacionados con los campeones, brindando una plataforma eficiente para la manipulación de datos en tiempo real y la generación de insights.

## Características principales:
### Recopilación de Datos:
Ingesta de información en tiempo real sobre cambios en habilidades, objetos, roles y estadísticas de los campeones provenientes de diversas fuentes, como la API oficial de Riot Games o bases de datos personalizadas.

### Procesamiento Distribuido:
Utilización de Kafka Streams para procesar los datos en múltiples etapas, incluyendo transformación, agregación y correlación de eventos relevantes.

### Gestión de Cambios:
Registro histórico de las modificaciones aplicadas a los campeones (buffs, nerfs, reworks) para realizar análisis de impacto en el rendimiento y la jugabilidad.

### Análisis de Estadísticas:
Generación de métricas clave sobre el rendimiento de los campeones, como tasas de victoria, selección y veto en diferentes regiones y niveles de habilidad.

### Interfaz de Consulta:
Provisión de endpoints y dashboards que permitan a jugadores, desarrolladores o analistas acceder de forma sencilla a las estadísticas y tendencias.

### Escalabilidad y Tolerancia a Fallos:
Diseño de la infraestructura para manejar grandes volúmenes de datos y garantizar la disponibilidad del sistema incluso ante picos de tráfico.

## Beneficios del Proyecto:
- Facilitar la toma de decisiones basadas en datos para diseñadores de juegos, jugadores competitivos y analistas de esports.
- Proveer un sistema robusto para la generación de contenido estadístico y predictivo.
- Centralizar y simplificar el acceso a datos relevantes en tiempo real, manteniendo la integridad y consistencia de la información.