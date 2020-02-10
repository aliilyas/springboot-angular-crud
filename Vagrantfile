# -*- mode: ruby -*-
# vi: set ft=ruby :

# All Vagrant configuration is done below. The "2" in Vagrant.configure
# configures the configuration version (we support older styles for
# backwards compatibility). Please don't change it unless you know what
# you're doing.
Vagrant.configure("2") do |config|
  config.vm.box = "centos/7"
  config.vm.network "forwarded_port", guest: 8080, host: 8080
  config.vm.network "forwarded_port", guest: 4200, host: 4200
  config.vm.provider "virtualbox" do |v|
    v.memory = 4096
    v.cpus = 4
  end

  config.vm.provision "shell", inline: <<-SHELL
    yum -y install epel-release
    curl -sL https://rpm.nodesource.com/setup_10.x | bash -
    yum -y install java maven git vim testng-javadoc jq gcc-c++ make nodejs
    npm install -g @angular/cli@9.0.1
    node -v
    ng v
    git clone https://github.com/aliilyas/springboot-angular-crud.git
    echo cloning finished
    cd springboot-angular-crud
    chmod +x gradlew
    echo Building sources
    ./gradlew bootJar
    nohup java -jar build/libs/embi-0.0.1-SNAPSHOT.jar > log.out 2>&1 &
    echo JAVA SERVER PID - $!
    cd src/main/webapp
    npm i
    ng serve --port 4200 --host 0.0.0.0Java
  SHELL
end
