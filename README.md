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
- Duplicate task confirmation flow (`yes`/`y`, `no`/`n`)
- Greeting commands: `hi`, `hello`, `hey`
- Help command: `help`
- Graceful exit after `bye` (3-second delay)
- Automatic persistence in `nut.txt`

## Quick Start Example
```text
todo submit tutorial
deadline submit report /by 15/09/2026 1800
list
mark 2
bye
```

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
./gradlew classes
java -cp build/classes/java/main nut.Nut
```

### Run built JAR (GUI)
```bash
java -jar build/libs/ip.jar
```

## Build Distribution JAR
Create a single runnable JAR (output: `build/libs/ip.jar`):
```bash
./gradlew clean shadowJar
```

## Command Reference
### Command notes
- Use lowercase command keywords and delimiters: `todo`, `deadline`, `event`, `mark`, `unmark`, `delete`, `/by`, `/from`, `/to`
- `find` keyword matching is case-insensitive
- During duplicate confirmation, only `yes`/`y` or `no`/`n` is accepted until resolved

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
- `hi`, `hello`, `hey`
- `help`
- `bye` (shows goodbye response, then exits after 3 seconds)

## Duplicate Confirmation
When adding a task with the same normalized name as an existing task, Nut asks for confirmation:
- `yes` or `y` to proceed
- `no` or `n` to cancel

## Storage
Tasks are auto-saved to `nut.txt` at the project root after each command.

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
