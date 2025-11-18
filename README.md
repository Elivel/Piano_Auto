# 🎹 Piano Automatización - Selenium + Maven + JUnit4

Proyecto de **pruebas automatizadas** para tocar secuencias de notas en un piano web usando **Selenium WebDriver**, **Maven** y **JUnit4**.

---

## 📋 Descripción

Este proyecto automatiza la interacción con un piano web (https://www.musicca.com/es/piano) para ejecutar tres escenarios de prueba:

- **Escenario 1**: Toca el "Himno de la Alegría" (15 notas)
- **Escenario 2**: Repite la secuencia dos veces (30 notas)
- **Escenario 3**: Toca la secuencia completa (45 notas)

Cada test abre un navegador Chrome, navega a la página del piano, y simula clicks en las teclas usando selectores CSS.

---

## 🛠️ Tecnologías

| Herramienta | Versión | Propósito |
|-------------|---------|----------|
| **Selenium WebDriver** | 4.19.0 | Automatización de navegador |
| **Maven** | 3.x | Gestión de dependencias y build |
| **JUnit4** | 4.13.2 | Framework de pruebas |
| **WebDriverManager** | 5.4.1 | Gestión automática de chromedriver |
| **Java** | 17 | Lenguaje de programación |
| **Chrome** | 142+ | Navegador para testing |

---

## 📁 Estructura del Proyecto

```
Piano_Auto/
├── pom.xml                              # Configuración Maven
├── README.md                            # Este archivo
├── serenety.conf                        # Config antigua (legacy)
│
├── src/
│   ├── main/java/com/fisagrp/piano/
│   │   ├── model/
│   │   │   ├── Nota.java               # Enum con notas DO, RE, MI, FA, SOL, LA, SI
│   │   │   └── SecuenciaHimno.java     # Secuencias de notas para los 3 escenarios
│   │   ├── ui/
│   │   │   └── PianoPage.java          # PageObject con selectores CSS
│   │   └── task/
│   │       ├── AbrirPiano.java         # Task para abrir URL del piano
│   │       ├── TocarNota.java          # Task para hacer click en una nota
│   │       └── TocarSecuencia.java     # Task para tocar una lista de notas
│   │
│   └── test/java/
│       ├── com/fisagrp/piano/features/
│       │   ├── Escenario1HimnoAlegriaTest.java      # Test: Himno (15 notas)
│       │   ├── Escenario2RepetirSecuenciaTest.java  # Test: Repetida (30 notas)
│       │   └── Escenario3HimnoCompletoTest.java     # Test: Completo (45 notas)
│       │
│       └── features/
│           └── Escenario1HimnoAlegriaTest_SIMPLE.java # Test alternativo
│
└── target/                              # Generado por Maven (ignorar)
    ├── classes/                         # Bytecode compilado
    ├── test-classes/                    # Bytecode de tests
    ├── surefire-reports/                # Reportes XML/TXT de tests
    └── site/
        └── surefire-report.html         # Reporte HTML legible ⭐
```

---

## 🚀 Inicio Rápido

### 1️⃣ Requisitos Previos

- **Java 17+** instalado
- **Maven 3.6+** instalado
- **Chrome 142+** instalado (WebDriverManager lo descarga automáticamente)

### 2️⃣ Ejecutar Todos los Tests

```bash
mvn clean test
```

**Resultado esperado:**
- ✅ Escenario1: ~26 segundos (15 notas)
- ✅ Escenario2: ~27 segundos (30 notas)
- ✅ Escenario3: ~113 segundos (45 notas)

### 3️⃣ Ejecutar un Test Específico

```bash
mvn -Dtest=Escenario1HimnoAlegriaTest test
mvn -Dtest=Escenario2RepetirSecuenciaTest test
mvn -Dtest=Escenario3HimnoCompletoTest test
```

### 4️⃣ Ver Reporte HTML

```bash
mvn surefire-report:report
start target/site/surefire-report.html
```

---

## 📊 Componentes Principales

### 🎼 Nota.java
Enum que define las 7 notas musicales (DO, RE, MI, FA, SOL, LA, SI)

### 🎵 SecuenciaHimno.java
Proporciona las tres secuencias de prueba mediante métodos estáticos

### 🖱️ PianoPage.java
PageObject Pattern que encapsula:
- Selectores CSS para cada tecla
- Métodos `abrirPagina()` y `tocarNota(Nota)`
- Manejo robusto de excepciones (clicks interceptados)
- Fallback a JavaScript click si Actions API falla

### 🎯 Tasks
Abstraen acciones complejas:
- `AbrirPiano` → Abre la página del piano
- `TocarNota` → Toca una sola nota
- `TocarSecuencia` → Toca una lista de notas en secuencia

### 🧪 Tests
Cada test JUnit4:
1. Abre navegador Chrome (con WebDriverManager)
2. Navega al piano
3. Toca la secuencia correspondiente
4. Imprime resultado
5. Cierra el navegador

---

## 📝 Comandos Útiles

```bash
# Limpiar y compilar
mvn clean compile

# Ejecutar todos los tests
mvn test

# Ejecutar un test específico
mvn -Dtest=Escenario1HimnoAlegriaTest test

# Generar reporte HTML
mvn surefire-report:report

# Ver salida detallada (debug)
mvn -X test

# Limpiar target/
mvn clean
```

---

## 🚦 Estado del Proyecto

| Escenario | Estado | Tiempo | Notas |
|-----------|--------|--------|-------|
| Escenario1 (15 notas) | ✅ PASS | ~26s | Estable |
| Escenario2 (30 notas) | ✅ PASS | ~27s | Estable |
| Escenario3 (45 notas) | ✅ PASS | ~113s | Más largo |

---

## 🔍 Selectores CSS de Teclas

Cada nota se identifica por `data-note` en la página del piano:

| Nota | Selector | Octava |
|------|----------|--------|
| DO   | `[data-note='1c']` | Octava 1 |
| RE   | `[data-note='1d']` | Octava 1 |
| MI   | `[data-note='1e']` | Octava 1 |
| FA   | `[data-note='1f']` | Octava 1 |
| SOL  | `[data-note='1g']` | Octava 1 |
| LA   | `[data-note='3a']` | Octava 3 |
| SI   | `[data-note='3b']` | Octava 3 |

---

## 📈 Reportes

### Reporte XML
Ubicación: `target/surefire-reports/TEST-*.xml`
- Datos brutos de ejecución
- Tiempos
- Stacktraces de errores

### Reporte HTML
Ubicación: `target/site/surefire-report.html`
- Interfaz visual
- Gráficos de éxito/fallo
- Detalles de cada test

---

## 🔧 Troubleshooting

### "UnreachableBrowserException"
**Causa**: Navegador se cierra tras ~140 segundos
**Solución**: Reducir `Thread.sleep()` en `PianoPage.java` (actualmente 50ms)

### "ElementClickInterceptedException"
**Causa**: Elemento superpone la tecla
**Solución**: Ya implementado fallback a JavaScript click

### "Unable to find CDP implementation matching 142"
**Causa**: Chrome 142 no mapeado en Selenium 4.19.0
**Solución**: Es solo un warning, se puede ignorar

---

## 📚 Referencias

- [Selenium Documentation](https://www.selenium.dev/documentation/)
- [Maven Documentation](https://maven.apache.org/)
- [JUnit 4](https://junit.org/junit4/)
- [WebDriverManager](https://bonigarcia.dev/webdrivermanager/)
- [Piano Web](https://www.musicca.com/es/piano)

---

## 🎵 Stack Técnico Resumido

✅ **Selenium 4.19.0** - Automatización de navegador
✅ **Maven 3.x** - Gestión de dependencias y build
✅ **JUnit4** - Framework de pruebas
✅ **WebDriverManager** - Driver automático
✅ **PageObject Pattern** - Estructura profesional
✅ **Manejo de excepciones** - Clicks robustos
✅ **Reportes HTML** - Surefire Report Plugin

---

## 👨‍💻 Autor

Proyecto de automatización - Elivel (rama: test_uno)

---

¡Listo para extender y automatizar más escenarios! 🚀
