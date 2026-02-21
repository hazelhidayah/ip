# Nut Task Manager

A Java task manager with a chat-style interface (GUI) and command-driven workflow.

> [!TIP]
> Type `help` anytime to view the supported commands in-app.

## Table of Contents
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Run](#run)
- [Command Reference](#command-reference)
- [Duplicate Confirmation](#duplicate-confirmation)
- [Storage](#storage)
- [Testing](#testing)
- [Project Structure](#project-structure)

## Features
- Add tasks: `todo`, `deadline`, `event`
- List tasks
- Find tasks by keyword
- Mark and unmark tasks
- Delete tasks
- Duplicate task confirmation flow (`yes` / `no`)
- Help command: `help`
- Persistent storage in `nut.txt`

## Prerequisites
- JDK `17`
- Gradle (or use the included wrapper `./gradlew`)

## Run
### GUI mode
```bash
./gradlew run
```

### CLI mode
```bash
./gradlew -q run --args=''
```

> [!NOTE]
> If your run config opens GUI by default, run `nut.Nut` directly from your IDE for CLI mode.

## Build Distribution JAR
Create a single runnable JAR (output: `build/libs/ip.jar`):
```bash
./gradlew clean shadowJar
```

## Command Reference

### Create tasks
- `todo <task>`
  - Example: `todo read chapter 3`
- `deadline <task> /by <dd/MM/yyyy HHmm>`
  - Example: `deadline submit report /by 15/09/2026 1800`
- `deadline <task> /by <dd/MM/yyyy>`
  - Example: `deadline submit report /by 15/09/2026`
- `event <task> /from <start> /to <end>`
  - Example: `event project sync /from Mon 2pm /to Mon 3pm`

### Manage tasks
- `list`
- `find <keyword>`
- `mark <task number>`
- `unmark <task number>`
- `delete <task number>`

### Utility
- `help`
- `bye`

## Duplicate Confirmation
When adding a task with the same normalized name as an existing task, Nut asks for confirmation:
- `yes` or `y` to proceed
- `no` or `n` to cancel

## Storage
Tasks are stored in `nut.txt` at the project root, so data persists across sessions.

## Testing
Run all tests:
```bash
./gradlew test
```

## Project Structure
- `src/main/java/nut` - app logic
- `src/main/java/nut/parser` - command parsing
- `src/main/java/nut/command` - command implementations
- `src/main/java/nut/task` - task models
- `src/main/resources` - FXML, CSS, images, fonts
- `src/test/java` - unit tests
