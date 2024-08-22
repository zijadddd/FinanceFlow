# FinanceFlow

## Description

This web application is the solution to the Rookie Boot Camp Internship assignment at <b>ProductDock</b>. It was developed using Angular, SpringBoot and MySQL. This application allows users to easily manage their finances, track expenses and income and perform transactions with the accounts they have.

## Characteristics

<ul>
  <li><b>Financial records: </b>It enables monitoring of income and expenses in a simple and transparent way.</li>
  <li><b>Simulation of transactions: </b>It enables users to execute payments and withdrawals, specifying the reason for the transaction as well as the type of expense.</li>
  <li><b>Currency tracking made easy: </b>The user can change the default currency of their account, making it easier to track balances across other accounts in their preferred currency.</li>
  <li><b>Account creation: </b>The user has the flexibility to add accounts as needed, specifying the currency and balance for each account.</li>
  <li><b>A beautiful and intuitive user interface.</b></li>
  <li><b>Everything is made from scratch.</b></li>
</ul>

## Technologies

<ul>
  <li>Angular</li>
  <li>SpringBoot</li>
  <li>MySQL</li>
</ul>

## Installation

1. Clone this repository:

```bash
git clone https://github.com/zijadddd/FinanceFlow.git
```

2. Before running the backend server, the MySQL database needs to be configured. This involves installing MySQL on the local machine. Once MySQL is installed, navigate to the financeflow-be/src/resources folder within the FinanceFlow directory that was recently cloned and open the application.yml file. In this file, the URL of the database should be specified under the datasource. Additionally, you need to specify the username and password under the datasource configuration.

```yml
spring:
  application:
    name: FinanceFlow
  datasource:
    url: jdbc:mysql://localhost:3306/financeflow?createDatabaseIfNotExist=true
    username: root
  jpa:
    properties:
      hibernate:
        tool:
          schema:
            Action: create
    hibernate:
      ddl-auto: update
```

3. Run backend server. First, navigate to the financeflow-be folder within the FinanceFlow directory. Then, open the terminal and execute the following command.

```bash
mvn spring-boot:run
```

<b>IMPORTANT: The backend application will also automatically create the database, tables, and relationships between them.</b><br> 4. Run frontend application. First, navigate to the financeflow-fe folder within the FinanceFlow directory. Then, open the terminal and execute the following commands.

```bash
npm install
ng serve
```

After these steps, application is ready to use.

## Endpoints

- **GET** `http://localhost:8080/api/defaultaccount`: Returns information about the default currency code and the sum of balances.
- **POST** `http://localhost:8080/api/defaultaccount`: Changes the default currency code.
- **DELETE** `http://localhost:8080/api/defaultaccount`: Deletes all accounts and transactions.
- **GET** `http://localhost:8080/api/accounts`: Returns all accounts from the database.
- **POST** `http://localhost:8080/api/accounts`: Creates a new account.
- **POST** `http://localhost:8080/api/transactions`: Commits a new transaction.
- **GET** `http://localhost:8080/api/transactions`: Returns all transactions from the database.
- **GET** `http://localhost:8080/api/transactions/{id}`: Returns all transactions for a specific account.

## Pages

- `http://localhost:4200/`: Accounts page
- `http://localhost:4200/transactions`: Transactions page
- `http://localhost:4200/settings`: Settings page
- `http://localhost:4200/*`: Page not found
