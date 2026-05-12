# LaserAge — Agent Instructions

A 2D space shooter written in Scala 3, using Java Swing for rendering and input.

## Architecture

```
src/main/scala/
  Main.scala          # Entry point; accepts optional start-wave argument
  engine/             # Game loop (Game), state container (GameModel), shared types (Direction)
  entities/           # Player, bullets/, enemies/, Explosion, Powerup
  rendering/          # GameCanvas (Swing panel), GameRenderer (draw logic)
  input/              # InputHandler (raw key/mouse events), GameController (translates to game actions)
  config/             # GameConfig (screen dimensions, constants), Wave / Waves (level definitions)
  audio/              # SoundManager, Sounds
```

Key relationships:
- `Game` owns the game loop and wires together `GameModel`, `GameController`, `InputHandler`, and `GameCanvas`.
- `GameModel` is the single source of truth for mutable game state (enemies, bullets, explosions, powerups).
- `Enemy` is an abstract class; concrete types live in `entities/enemies/`.
- Constants (screen size, player bounds) live in `GameConfig` — update there, not inline.

## Build and Test

### bash

```bash
# Compile
sbt compile

# Run (optionally pass a start wave number)
sbt run
sbt "run 3"

# Run tests
sbt test

# Continuous compilation
sbt ~compile
```

### Windows cmd

```bat
REM Compile
sbt compile

REM Run (optionally pass a start wave number)
sbt run
sbt "run 3"

REM Run tests
sbt test

REM Continuous compilation
sbt ~compile
```

Requires **Java 11+** and **sbt**. Scala version is **3.3.7** (LTS). Test framework is **MUnit**.

## Code Style

Follow standard Scala 3 conventions:
- Prefer `val` over `var`; mutable state is intentional and confined to `GameModel`.
- Use Scala 3 syntax: braceless `if`/`for`/`match`, `enum`, extension methods where appropriate.
- Name classes in `UpperCamelCase`, values and methods in `lowerCamelCase`, constants in `ALL_CAPS` (matching the existing `GameConfig` style).
- Favour pure functions; side effects (rendering, audio, I/O) belong in the `rendering/` and `audio/` packages.
- Keep `GameModel` free of rendering or input concerns — it exposes state, not behaviour.

## Conventions

- New entity types should extend the appropriate abstract class (`Enemy`, `Bullet`) and be placed in the matching `entities/` subdirectory.
- Wave definitions belong in `config/Waves.scala`, not inline in game logic.
- Do not add dependencies without discussion; the project intentionally has no runtime dependencies beyond the JDK.
