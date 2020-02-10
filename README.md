

## CRUD REST API 


### Development

The application consists of a frontend and a backend. Both can be started separately. The frontend is  [Angular 9](https://angular.io/)  based and the backend is based on  [Spring Boot](https://spring.io/projects/spring-boot).

## Installation

### Prerequisites
Ensure you have installed : `node 10x, java 8+, git , angular cli 9x`

```sh
$ yum -y install epel-release
$ curl -sL https://rpm.nodesource.com/setup_10.x | bash - #node version 10.x
$ yum -y install java maven git vim testng-javadoc jq gcc-c++ make nodejs
$ npm install -g @angular/cli@9.0.1
```

Check major version has same number:
```sh
$ node -v
$ v10.16.3
$ npm -version
$ 6.12.1
$ ng version
$ 9.0.1
```

> Clone this repo   `git clone https://github.com/aliilyas/springboot-angular-crud.git`
> 


```sh
$ cd springboot-angular-crud
#run tests
$ ./gradlew test 
#run api
$ ./gradlew bootRun
#run webapp
$ cd src/main/webapp
$ npm i
$ ng serve 
```
Frontend port  `4200`

Backend port `8080`


### Vagrant
Vagrant config is located in project root folder

```sh
vagrant up
```
Please take a look at `config.vm.provider`. And adjust according to your machine settings.
```sh
config.vm.provider "virtualbox" do |v|  
  v.memory = 4096  
  v.cpus = 4  
end
```
Verify the deployment by navigating to your server address in your preferred browser.

```sh
127.0.0.1:4200
```
