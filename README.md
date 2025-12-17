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

PS C:\Suji_Workspace\studentdetails> docker image ls
REPOSITORY   TAG       IMAGE ID   CREATED   SIZE
PS C:\Suji_Workspace\studentdetails> docker load -i C:\Suji_Workspace\studentdetails\studentdetails-0.0.1-SNAPSHOT.tar
Loaded image: ponnagantisuji/studentdetails:0.0.1-SNAPSHOT
PS C:\Suji_Workspace\studentdetails> docker image ls                                                                  
REPOSITORY                      TAG              IMAGE ID       CREATED        SIZE
ponnagantisuji/studentdetails   0.0.1-SNAPSHOT   0fb9e1d105c4   55 years ago   411MB
PS C:\Suji_Workspace\studentdetails> 

PS C:\Suji_Workspace\studentdetails> docker stop studentdetails
studentdetails
PS C:\Suji_Workspace\studentdetails> docker rm studentdetails
studentdetails
PS C:\Suji_Workspace\studentdetails> docker run -d -p 8081:8081 --name studentdetails ponnagantisuji/studentdetails:0.0.1-SNAPSHOT
537db1ee67898150ef84affeee13a2b479be1385fc1cfa5d1d099488f36e54d9
PS C:\Suji_Workspace\studentdetails> docker ps
CONTAINER ID   IMAGE                                          COMMAND                  CREATED              STATUS              PORTS                                         NAMES
537db1ee6789   ponnagantisuji/studentdetails:0.0.1-SNAPSHOT   "java -cp @/app/jib-…"   About a minute ago   Up About a minute   0.0.0.0:8081->8081/tcp, [::]:8081->8081/tcp   studentdetails
PS C:\Suji_Workspace\studentdetails> docker logs studentdetails

PS C:\Suji_Workspace\studentdetails> kubectl config get-clusters
NAME
docker-desktop
PS C:\Suji_Workspace\studentdetails> kubectl config get-contexts
CURRENT   NAME             CLUSTER          AUTHINFO         NAMESPACE
*         docker-desktop   docker-desktop   docker-desktop

PS C:\Suji_Workspace\studentdetails\kube> kubectl apply -f studentdetails.yaml
namespace/suji-ns created
deployment.apps/studentdetails-deployment created
service/studentdetails-service created

PS C:\Suji_Workspace\studentdetails\kube> kubectl get pods -n suji-ns
NAME                                         READY   STATUS    RESTARTS   AGE
studentdetails-deployment-6989dc9dbc-kjjsk   1/1     Running   0          13s
studentdetails-deployment-6989dc9dbc-nm2nt   1/1     Running   0          13s
PS C:\Suji_Workspace\studentdetails\kube> kubectl get svc -n suji-ns
NAME                     TYPE       CLUSTER-IP      EXTERNAL-IP   PORT(S)          AGE
studentdetails-service   NodePort   10.96.174.125   <none>        8081:30081/TCP   13s

PS C:\Suji_Workspace\studentdetails\kube> kubectl logs -f studentdetails-deployment-6989dc9dbc-kjjsk -n suji-ns

.   ____          _            __ _ _
/\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
\\/  ___)| |_)| | | | | || (_| |  ) ) ) )


PS C:\Suji_Workspace\studentdetails\kube> kubectl config set-context --current --namespace=suji-ns
Context "docker-desktop" modified.
PS C:\Suji_Workspace\studentdetails\kube>
PS C:\Suji_Workspace\studentdetails\kube> kubectl get pods
NAME                                         READY   STATUS    RESTARTS   AGE
studentdetails-deployment-6989dc9dbc-kjjsk   1/1     Running   0          2m11s
studentdetails-deployment-6989dc9dbc-nm2nt   1/1     Running   0          2m11s
PS C:\Suji_Workspace\studentdetails\kube> kubectl get svc -n suji-ns
NAME                     TYPE       CLUSTER-IP      EXTERNAL-IP   PORT(S)          AGE
studentdetails-service   NodePort   10.96.174.125   <none>        8081:30081/TCP   2m52s
PS C:\Suji_Workspace\studentdetails\kube>
PS C:\Suji_Workspace\studentdetails\kube> kubectl get nodes -o wide
NAME                    STATUS   ROLES           AGE   VERSION   INTERNAL-IP   EXTERNAL-IP   OS-IMAGE                         KERNEL-VERSION                     CONTAINER-RUNTIME
desktop-control-plane   Ready    control-plane   13m   v1.31.1   172.18.0.2    <none>        Debian GNU/Linux 12 (bookworm)   6.6.87.2-microsoft-standard-WSL2   containerd://1.7.18

PS C:\Suji_Workspace\studentdetails\kube> kubectl port-forward svc/studentdetails-service 8081:8081 -n suji-ns
Forwarding from 127.0.0.1:8081 -> 8081
Forwarding from [::1]:8081 -> 8081
Handling connection for 8081
Handling connection for 8081
you have to keep port forward running to maintain the tunnel and keep window open.

---------------------------------------------------------------------------------------

adding some lines