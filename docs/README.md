# Nut User Guide

Nut is a lightweight chatbot task manager that helps you track todos, deadlines, and events using simple text commands.

<style>
  :root {
    --nut-bg: #fbf8f3;
    --nut-bg-soft: #f4eee6;
    --nut-accent: #8a6541;
    --nut-accent-dark: #6e4e33;
    --nut-card: rgba(255, 251, 245, 0.9);
    --nut-text: #2f2a24;
    --nut-border: #d9cfc2;
  }

  body,
  .main-content,
  .markdown-body {
    background: linear-gradient(to bottom, var(--nut-bg), var(--nut-bg-soft)) !important;
    color: var(--nut-text) !important;
  }

  .markdown-body h1,
  .markdown-body h2,
  .markdown-body h3 {
    color: var(--nut-accent-dark) !important;
  }

  .markdown-body code {
    color: #fff8f0 !important;
    background-color: var(--nut-accent) !important;
    border: 1px solid var(--nut-accent-dark) !important;
    border-radius: 6px;
    padding: 2px 6px;
  }

  .markdown-body table {
    background: var(--nut-card) !important;
    border: 1px solid var(--nut-border) !important;
  }

  .markdown-body th,
  .markdown-body td {
    border: 1px solid var(--nut-border) !important;
  }
</style>

<img src="Ui.png" alt="Nut UI Screenshot" width="75%">

## Features

### Command format notes

- Commands should be entered in lowercase (e.g., <span style="background:#8A6541;color:#FFF8F0;padding:2px 8px;border-radius:6px;font-family:monospace;">list</span>, <span style="background:#E8DCCF;color:#5E432E;padding:2px 8px;border-radius:6px;font-family:monospace;border:1px solid #B89F86;">todo read book</span>).
- `TASK_NUMBER` refers to the number shown in the `list` output.
- Use `dd/MM/yyyy` or `dd/MM/yyyy HHmm` for deadlines.
- <span style="background:#8A6541;color:#FFF8F0;padding:2px 8px;border-radius:6px;font-family:monospace;">yes</span>/<span style="background:#E8DCCF;color:#5E432E;padding:2px 8px;border-radius:6px;font-family:monospace;border:1px solid #B89F86;">y</span> and <span style="background:#8A6541;color:#FFF8F0;padding:2px 8px;border-radius:6px;font-family:monospace;">no</span>/<span style="background:#E8DCCF;color:#5E432E;padding:2px 8px;border-radius:6px;font-family:monospace;border:1px solid #B89F86;">n</span> are only used when Nut asks you to confirm a duplicate task.
- `find` matches keywords case-insensitively (e.g., `find report` matches `Report`).

### Viewing help: <span style="background:#8A6541;color:#FFF8F0;padding:2px 8px;border-radius:6px;font-family:monospace;">help</span>

Shows the in-app command rundown.

Format: <span style="background:#E8DCCF;color:#5E432E;padding:2px 8px;border-radius:6px;font-family:monospace;border:1px solid #B89F86;">help</span>

---

### Greeting Nut: <span style="background:#8A6541;color:#FFF8F0;padding:2px 8px;border-radius:6px;font-family:monospace;">hi</span>, <span style="background:#8A6541;color:#FFF8F0;padding:2px 8px;border-radius:6px;font-family:monospace;">hello</span>, <span style="background:#8A6541;color:#FFF8F0;padding:2px 8px;border-radius:6px;font-family:monospace;">hey</span>

Sends a greeting and gets a friendly reply.

Format: <span style="background:#E8DCCF;color:#5E432E;padding:2px 8px;border-radius:6px;font-family:monospace;border:1px solid #B89F86;">hi</span> or <span style="background:#E8DCCF;color:#5E432E;padding:2px 8px;border-radius:6px;font-family:monospace;border:1px solid #B89F86;">hello</span> or <span style="background:#E8DCCF;color:#5E432E;padding:2px 8px;border-radius:6px;font-family:monospace;border:1px solid #B89F86;">hey</span>

---

### Adding a todo: <span style="background:#8A6541;color:#FFF8F0;padding:2px 8px;border-radius:6px;font-family:monospace;">todo</span>

Adds a simple todo task.

Format: <span style="background:#E8DCCF;color:#5E432E;padding:2px 8px;border-radius:6px;font-family:monospace;border:1px solid #B89F86;">todo DESCRIPTION</span>

Examples:
- <span style="background:#E8DCCF;color:#5E432E;padding:2px 8px;border-radius:6px;font-family:monospace;border:1px solid #B89F86;">todo borrow book</span>
- <span style="background:#E8DCCF;color:#5E432E;padding:2px 8px;border-radius:6px;font-family:monospace;border:1px solid #B89F86;">todo submit reflection</span>

---

### Adding a deadline: <span style="background:#8A6541;color:#FFF8F0;padding:2px 8px;border-radius:6px;font-family:monospace;">deadline</span>

Adds a deadline task.

Format:
- <span style="background:#E8DCCF;color:#5E432E;padding:2px 8px;border-radius:6px;font-family:monospace;border:1px solid #B89F86;">deadline DESCRIPTION /by dd/MM/yyyy</span>
- <span style="background:#E8DCCF;color:#5E432E;padding:2px 8px;border-radius:6px;font-family:monospace;border:1px solid #B89F86;">deadline DESCRIPTION /by dd/MM/yyyy HHmm</span>

Examples:
- <span style="background:#E8DCCF;color:#5E432E;padding:2px 8px;border-radius:6px;font-family:monospace;border:1px solid #B89F86;">deadline return library book /by 03/12/2026</span>
- <span style="background:#E8DCCF;color:#5E432E;padding:2px 8px;border-radius:6px;font-family:monospace;border:1px solid #B89F86;">deadline submit report /by 15/09/2026 1800</span>

---

### Adding an event: <span style="background:#8A6541;color:#FFF8F0;padding:2px 8px;border-radius:6px;font-family:monospace;">event</span>

Adds an event task with start and end fields.

Format: <span style="background:#E8DCCF;color:#5E432E;padding:2px 8px;border-radius:6px;font-family:monospace;border:1px solid #B89F86;">event DESCRIPTION /from START /to END</span>

Examples:
- <span style="background:#E8DCCF;color:#5E432E;padding:2px 8px;border-radius:6px;font-family:monospace;border:1px solid #B89F86;">event project meeting /from Mon 2pm /to Mon 4pm</span>
- <span style="background:#E8DCCF;color:#5E432E;padding:2px 8px;border-radius:6px;font-family:monospace;border:1px solid #B89F86;">event hackathon /from 9am /to 6pm</span>

---

### Listing tasks: <span style="background:#8A6541;color:#FFF8F0;padding:2px 8px;border-radius:6px;font-family:monospace;">list</span>

Shows all tasks in your task list.

Format: <span style="background:#E8DCCF;color:#5E432E;padding:2px 8px;border-radius:6px;font-family:monospace;border:1px solid #B89F86;">list</span>

---

### Finding tasks: <span style="background:#8A6541;color:#FFF8F0;padding:2px 8px;border-radius:6px;font-family:monospace;">find</span>

Shows tasks whose descriptions contain the given keyword.
Keyword matching is case-insensitive.

Format: <span style="background:#E8DCCF;color:#5E432E;padding:2px 8px;border-radius:6px;font-family:monospace;border:1px solid #B89F86;">find KEYWORD</span>

Examples:
- <span style="background:#E8DCCF;color:#5E432E;padding:2px 8px;border-radius:6px;font-family:monospace;border:1px solid #B89F86;">find report</span>
- <span style="background:#E8DCCF;color:#5E432E;padding:2px 8px;border-radius:6px;font-family:monospace;border:1px solid #B89F86;">find book</span>

---

### Marking a task as done: <span style="background:#8A6541;color:#FFF8F0;padding:2px 8px;border-radius:6px;font-family:monospace;">mark</span>

Marks the specified task as completed.

Format: <span style="background:#E8DCCF;color:#5E432E;padding:2px 8px;border-radius:6px;font-family:monospace;border:1px solid #B89F86;">mark TASK_NUMBER</span>

Example: <span style="background:#E8DCCF;color:#5E432E;padding:2px 8px;border-radius:6px;font-family:monospace;border:1px solid #B89F86;">mark 2</span>

---

### Unmarking a task: <span style="background:#8A6541;color:#FFF8F0;padding:2px 8px;border-radius:6px;font-family:monospace;">unmark</span>

Marks the specified task as not completed.

Format: <span style="background:#E8DCCF;color:#5E432E;padding:2px 8px;border-radius:6px;font-family:monospace;border:1px solid #B89F86;">unmark TASK_NUMBER</span>

Example: <span style="background:#E8DCCF;color:#5E432E;padding:2px 8px;border-radius:6px;font-family:monospace;border:1px solid #B89F86;">unmark 2</span>

---

### Deleting a task: <span style="background:#8A6541;color:#FFF8F0;padding:2px 8px;border-radius:6px;font-family:monospace;">delete</span>

Deletes the specified task.

Format: <span style="background:#E8DCCF;color:#5E432E;padding:2px 8px;border-radius:6px;font-family:monospace;border:1px solid #B89F86;">delete TASK_NUMBER</span>

Example: <span style="background:#E8DCCF;color:#5E432E;padding:2px 8px;border-radius:6px;font-family:monospace;border:1px solid #B89F86;">delete 3</span>

---

### Exiting the app: <span style="background:#8A6541;color:#FFF8F0;padding:2px 8px;border-radius:6px;font-family:monospace;">bye</span>

Closes Nut after showing the goodbye message and waiting 3 seconds (ends the CLI session or closes the GUI window).

Format: <span style="background:#E8DCCF;color:#5E432E;padding:2px 8px;border-radius:6px;font-family:monospace;border:1px solid #B89F86;">bye</span>

---

### Duplicate confirmation: <span style="background:#8A6541;color:#FFF8F0;padding:2px 8px;border-radius:6px;font-family:monospace;">yes</span> / <span style="background:#8A6541;color:#FFF8F0;padding:2px 8px;border-radius:6px;font-family:monospace;">no</span>

If you add a task with a duplicate name, Nut will ask if you still want to add it.

Reply with:
- <span style="background:#E8DCCF;color:#5E432E;padding:2px 8px;border-radius:6px;font-family:monospace;border:1px solid #B89F86;">yes</span> or <span style="background:#E8DCCF;color:#5E432E;padding:2px 8px;border-radius:6px;font-family:monospace;border:1px solid #B89F86;">y</span> to add anyway
- <span style="background:#E8DCCF;color:#5E432E;padding:2px 8px;border-radius:6px;font-family:monospace;border:1px solid #B89F86;">no</span> or <span style="background:#E8DCCF;color:#5E432E;padding:2px 8px;border-radius:6px;font-family:monospace;border:1px solid #B89F86;">n</span> to cancel

## Saving data

Nut saves your tasks automatically after each command to:

- `nut.txt` (project root)

No manual save command is required.

## Command summary

| Action | Format |
| --- | --- |
| Help | `help` |
| Greet | `hi` / `hello` / `hey` |
| Add todo | `todo DESCRIPTION` |
| Add deadline | `deadline DESCRIPTION /by dd/MM/yyyy` |
| Add deadline with time | `deadline DESCRIPTION /by dd/MM/yyyy HHmm` |
| Add event | `event DESCRIPTION /from START /to END` |
| List tasks | `list` |
| Find tasks | `find KEYWORD` (case-insensitive) |
| Mark task | `mark TASK_NUMBER` |
| Unmark task | `unmark TASK_NUMBER` |
| Delete task | `delete TASK_NUMBER` |
| Confirm duplicate | `yes` / `y` |
| Cancel duplicate | `no` / `n` |
| Exit | `bye` (exits after 3 seconds) |

## FAQ

**Q: Where are my tasks stored?**  
A: In `nut.txt` at the project root.

**Q: Can I edit `nut.txt` manually?**  
A: You can, but invalid formatting may cause loading issues. Back it up first.

**Q: Why did Nut ask for `yes`/`no` after I added a task?**  
A: Nut detected a duplicate task name and is asking for confirmation.

**Q: Which date format is valid for `deadline`?**  
A: Use `dd/MM/yyyy` or `dd/MM/yyyy HHmm` (24-hour time).

**Q: Is `find` case-sensitive?**  
A: No. `find report` and `find Report` return the same matches.

**Q: Why doesn't Nut close immediately after `bye`?**  
A: Nut shows the goodbye message first, waits 3 seconds, then exits.
