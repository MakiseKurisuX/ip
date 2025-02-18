# Helios ChatBot User Guide

Helios is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, ChatBot can help you manage your tasks more efficiently than traditional GUI apps.

## Quick Start

### Prerequisites

Ensure you have **Java 17 or above** installed on your computer.

1. **Download the latest .jar file**.
2. Copy the file to your desired folder.
3. Open a command terminal, navigate (`cd`) into the folder where you placed the jar file.
4. Run the application using the command:
   ```sh
   java -jar chatbot.jar
   ```
5. A GUI should appear within a few seconds.
6. Type a command in the command box and press Enter to execute it.

### Example Commands
- `list` : Lists all tasks.
- `mark INDEX` : Marks the task at the given index as completed.
- `unmark INDEX` : Unmarks the task at the given index.
- `add todo DESCRIPTION` : Adds a ToDo task with the given description.
- `add deadline DESCRIPTION /by TIME` : Adds a Deadline task with a specified deadline.
- `add event DESCRIPTION /from START /to END` : Adds an Event task with a start and end time.
- `delete INDEX` : Deletes the task at the specified index.
- `find KEYWORD` : Finds tasks containing the given keyword.
- `sort` : Sorts the tasks chronologically.
- `bye` : Helios Bot gives a goodbye message.

Refer to the **Features** section below for details on each command.

## Features

### Viewing Help: `help`
Displays a message explaining how to access the help page.

**Format:**
```
help
```

### Adding a Task: `add`
Adds a new task to the task list.

**Format:**
```
add todo DESCRIPTION
add deadline DESCRIPTION /by TIME
add event DESCRIPTION /from START /to END
```

**Examples:**
```
add todo Read Book
add deadline Submit Assignment /by Monday 5pm
add event Team Meeting /from Monday 3pm /to Monday 5pm
```

### Listing All Tasks: `list`
Shows a list of all tasks.

**Format:**
```
list
```

### Marking a Task as Done: `mark`
Marks the specified task as done.

**Format:**
```
mark INDEX
```

**Examples:**
```
mark 1
```

### Unmarking a Task: `unmark`
Marks the specified task as not done.

**Format:**
```
unmark INDEX
```

**Examples:**
```
unmark 1
```

### Finding Tasks: `find`
Finds tasks whose names contain any of the given keywords.

**Format:**
```
find KEYWORD [MORE_KEYWORDS]
```

**Examples:**
```
find book
find assignment meeting
```

### Deleting a Task: `delete`
Deletes the specified task.

**Format:**
```
delete INDEX
```

**Examples:**
```
delete 3
```

### Sorting Tasks: `sort`
Sorts tasks chronologically.

**Format:**
```
sort
```

### Exiting the Program: `bye`
Helios Bot gives a goodbye message.

**Format:**
```
bye
```

## Saving Data
- Tasks are **automatically saved** to the hard disk after every change.
- The data is stored in `src/main/data/helios.txt`.

## Editing the Data File
- The data is saved as a **text file**. 
- **Caution:** If you modify the file incorrectly, ChatBot may fail to load the data properly.

## FAQ
### **Q: How do I transfer my data to another computer?**
A: Install the application on the other computer and copy over the `helios.txt` data file.

## Command Summary

| **Action**  | **Format, Examples**  |
|------------|----------------------|
| **Add**    | `add todo DESCRIPTION`  |
|            | `add deadline DESCRIPTION /by TIME`  |
|            | `add event DESCRIPTION /from START /to END`  |
| **Mark**   | `mark INDEX` (e.g., `mark 1`)  |
| **Unmark** | `unmark INDEX` (e.g., `unmark 1`)  |
| **Delete** | `delete INDEX` (e.g., `delete 3`)  |
| **Find**   | `find KEYWORD [MORE_KEYWORDS]` (e.g., `find book`)  |
| **Sort**   | `sort`  |
| **List**   | `list`  |
| **Bye**    | `bye`  |

For any further questions or issues, open an issue in the repository. Enjoy managing your tasks with Helios!

