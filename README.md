# Nut Task Manager

Nut is a Java task manager with a chat-style interface (GUI) and command parsing for common task operations.

## Features
- Add tasks: `todo`, `deadline`, `event`
- List tasks
- Find tasks by keyword
- Mark / unmark tasks
- Delete tasks
- Duplicate task confirmation flow (`yes` / `no`)
- Help command: `help`
- Persistent storage in `nut.txt`

## Prerequisites
- JDK 17
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
(If your run config points to GUI by default, run `nut.Nut` directly from IDE for CLI mode.)

## Command Reference
- `todo <task>`
  - Example: `todo read chapter 3`
- `deadline <task> /by <dd/MM/yyyy HHmm>`
  - Example: `deadline submit report /by 15/09/2026 1800`
- `deadline <task> /by <dd/MM/yyyy>`
  - Example: `deadline submit report /by 15/09/2026`
- `event <task> /from <start> /to <end>`
  - Example: `event project sync /from Mon 2pm /to Mon 3pm`
- `list`
- `find <keyword>`
- `mark <task number>`
- `unmark <task number>`
- `delete <task number>`
- `help`
- `bye`

## Duplicate Confirmation
When adding a task with the same normalized name as an existing task, Nut asks for confirmation:
- `yes` / `y` to proceed
- `no` / `n` to cancel

## Storage
Tasks are saved to `nut.txt` in the project root so task data persists across sessions.

## Test
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
