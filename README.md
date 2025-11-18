# Studentdetails - Simple Spring Boot app

This is a minimal Spring Boot application with a Student model and a StudentController exposing a simple POST API to create students.

Run (with Maven):

```bash
mvn -DskipTests package
java -jar target\studentdetails-0.0.1-SNAPSHOT.jar
```

POST API

- URL: http://localhost:8080/api/students
- Method: POST
- Body (JSON): { "name": "Alice", "age": 21 }

cURL (copy this into Postman > Import > Raw text):

```bash
curl -X POST "http://localhost:8080/api/students" -H "Content-Type: application/json" -d "{\"name\":\"Alice\",\"age\":21}"
```

The POST returns the created student including an auto-generated `id`.


# Using the included Maven wrapper

I added two lightweight Maven wrapper scripts to this project so you can build without installing Maven system-wide:

- `mvnw.cmd` — Windows (cmd.exe / PowerShell)
- `mvnw` — Unix-like shells

On first run the wrapper will download Apache Maven into the `.mvn` folder. Make sure you have internet access. On Windows the script uses PowerShell to download and extract the Maven zip.

Windows (cmd.exe):

```bash
cd /d C:\Sudha_Projects\Studentdetails
mvnw.cmd -DskipTests package
java -jar target\studentdetails-0.0.1-SNAPSHOT.jar
```

Or explicitly (from any folder):
```bash
mvn -f C:\Sudha_Projects\Studentdetails\pom.xml -DskipTests package
```

Unix / WSL / Git Bash:

```bash
cd /d/C/Sudha_Projects/Studentdetails  # adapt path for your shell
./mvnw -DskipTests package
java -jar target/studentdetails-0.0.1-SNAPSHOT.jar
```

Notes:
- First run will download Maven (requires curl/wget/unzip on Unix, PowerShell on Windows).
- If download fails, install Maven manually or run from an IDE that provides Maven.

![img.png](img.png)

![img_1.png](img_1.png)

![img_2.png](img_2.png)
