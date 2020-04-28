

## CRUD REST API 


### Development

Starter project with angular and spring boot.

## Installation

### Prerequisites
Installation : `node 10x, java 8+, git , angular cli 9x`

```sh
$ yum -y install epel-release
$ curl -sL https://rpm.nodesource.com/setup_10.x | bash - #node version 10.x
$ yum -y install java maven git vim testng-javadoc jq gcc-c++ make nodejs
$ npm install -g @angular/cli@9.0.1
```

Check major versions (can be outdated):
```sh
$ node -v
$ v10.16.3
$ npm -version
$ 6.12.1
$ ng version
$ 9.0.1
```



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
FE port  `4200`

API port `8080`


### Vagrant
Vagrant config is located in project root folder

```sh
vagrant up
```
Take a look at `config.vm.provider`. And adjust according to your machine settings.
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
