# JyCurrencyConverter
A Rest Api using Kotlin libs: 

- [Javalin](https://javalin.io/): Web framework for Kotlin/Java which supports WebSockets, HTTP2 and async requests.
- [Koin](https://insert-koin.io/): Dependency injection framework for Kotlin.
- [Exposed](https://github.com/JetBrains/Exposed): ORM framework for Kotlin.
- [HikariCP](https://www.baeldung.com/hikaricp): JDBC connection pooling framework.
- [H2](https://www.h2database.com/html/main.html): A SQL database
- [SLF4J](http://www.slf4j.org/): Simple logging facade framework at deployment time.

### Requirements
You must have Git, Java and Gradle configured.

### Cloning repository
```
$ git clone https://github.com/yurisnm/JyCurrencyConverter.git && cd JyCurrencyConverter
```

### Run Tests on local machine
```
$ gradle stage
```

### Run Application locally

#### Start server
```
$ gradle run
```

## About

The application can basically:
- [POST] Register a transaction.
```
POST http://localhost:7000/transaction - do a transaction
body example: {
	"userId": "user01",
	"originCurrency": "USD",
	"originValue": 5,
	"destinyCurrency": "BRL"
}
```
- [GET] List all transactions.
```
GET http://localhost:7000/transaction - returns the list of all transactions made
```
- [GET] List all transactions by user.
```
GET http://localhost:7000/transaction/:userId - returns the list of all transactions made by a userId
```
