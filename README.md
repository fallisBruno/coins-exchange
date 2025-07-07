# coins-exchange
Coins Exchange project

## Before Running...

If you need to change coins amount, just go to *resources > coins.json* and you will see the following default values:
```
{
    "0.25": 100,
    "0.10": 100,
    "0.05": 100,
    "0.01": 100
}
```
Right side is the coin value and left side is coin amount. The application runs it generically and will deal with any value, once you follow the specified format.

## How To Run

1) Clone the project to your local machine:
```
git clone https://github.com/fallisBruno/coins-exchange.git
```
2) Open a terminal and access the project folder:
```
cd coins-exchange
```
3) Paste the following command and run:
```
mvnw.cmd spring-boot:run
```