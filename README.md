# Piano_Auto

Automatización de escenarios con Selenium utilizando definiciones en YAML. El objetivo es permitir que los tres escenarios descritos en el PDF se codifiquen sin modificar el código, sólo editando el archivo de configuración.

## Estructura
- `src/`: código fuente del runner y utilidades.
- `config/scenarios.yaml`: define los pasos de cada escenario (ajusta los valores según el PDF).
- `tests/`: pruebas unitarias para validar la carga y ejecución de pasos.
- `requirements.txt`: dependencias mínimas para ejecutar el proyecto.

## Instalación
```bash
python -m venv .venv
source .venv/bin/activate
pip install -r requirements.txt
```

## Cómo ejecutar los escenarios
1. Actualiza `config/scenarios.yaml` con los tres flujos definidos en el PDF (URL, selectores y aserciones).
2. Ejecuta el runner en modo headless:
```bash
python -m src.scenario_runner --scenarios config/scenarios.yaml
```
3. Si necesitas ver el navegador, agrega `--no-headless`.

## Convertir el PDF en YAML
- Cada escenario debe tener un `name` y una lista de `steps`.
- Acciones soportadas: `get`, `click`, `type`, `assert_title_contains`, `assert_url_contains`, `assert_text_present`.
- Ejemplo de paso:
```yaml
- action: click
  by: css
  target: "button.enviar"
```

## Notas
- El proyecto usa Selenium con Chrome. Asegúrate de tener Chrome/Chromium instalado en la máquina donde se ejecuten los escenarios.
- El archivo `config/scenarios.yaml` contiene tres escenarios de ejemplo que puedes usar como base.
