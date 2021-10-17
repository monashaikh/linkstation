
# Linkstation

Spring Boot application to find the best suitable linkstation for a device at a given point(x,y)

## Author
 [Mona Hanif Shaikh](https://github.com/monashaikh)

  
## Development setup

Technical requirement ↓
``` 
Maven: apache-maven-3.6.3
Java : jdk1.8.0_231
```


-  Clone the repository (You can use any IDE as per your choice, like Intellij, Ecllipse)
    `git clone https://github.com/monashaikh/linkstation.git`

## Run the Application
- Execute ` mvn clean install` in the terminal of application root directory (/linkstation) or you can also use the IDE to build/run the application
- If the above step succeeds, application jar file(*linkstation-0.0.1-SNAPSHOT.jar*) must have been created in the application `/target` directory.
- To run the application via terminal, execute any of the below commands-
    ```
    mvn spring-boot:run
    -------------OR-------------------
    java -jar <path to app directory>/target/linkstation-0.0.1-SNAPSHOT.jar
    ```
## Run tests
- `mvn test`
## Important links
- The app should be up at port 8080 (http://localhost:8080/)
- Swagger ui : http://localhost:8080/api/swagger-ui.html
- Api documentation : http://localhost:8080/api/v2/api-docs
- H2 database : http://localhost:8080/api/h2
    - To connect to H2 database, use below settings after clicking on above h2 link **(No password required)**
        ```
        JDBC URL:	jdbc:h2:mem:linkstation
        User Name:	sa
        ```
    - Click 'Connect' button.



## API Reference

#### Find the best link station for the given point(xCoordinate,yCoordinate)

```http
  POST /api/get-best-link-station
```

|  Request      | Type     | Description                |
| :--------     | :------- | :------------------------- |
| `xCoordinate` | `Integer` | **Required**.             |
| `yCoordinate` | `Integer` | **Required**.             |

Example : 

`curl -X POST "http://localhost:8080/api/get-best-link-station" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"xCoordinate\": 18, \"yCoordinate\": 18}"`

OR

Request Body: 
```
{
  "xCoordinate": 18,
  "yCoordinate": 18
}
```

Response :

`Best link station for point 18,18 is 20,20 with power 4.715728752538098`