# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure("2") do |config|
  config.vm.box = "bento/centos-8"
  config.vm.network "forwarded_port", guest: 4567, host: 80
  # config.vm.network "private_network", type: "dhcp"
  config.vm.synced_folder ".", "/vagrant"
  
  config.vm.provider "virtualbox" do |vb|
    # Display the VirtualBox GUI when booting the machine
    vb.gui = false
  
    # Customize the amount of memory on the VM:
    vb.memory = "1024"
  end
  
  config.vm.provision "shell", inline: <<-SHELL
    # install java
    dnf install -y java-11-openjdk dos2unix
	
	# Ensure that the run script is in unix format
	dos2unix /vagrant/run
	
	# Install maven
	
	
	# install maven
	cd /usr/local/src
    wget http://apache.mirrors.hoobly.com/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz
	tar -xf apache-maven-3.6.3-bin.tar.gz
	mv apache-maven-3.6.3 /usr/local/maven/
	echo "# Apache Maven Environment Variables
# MAVEN_HOME for Maven 1 - M2_HOME for Maven 2
export M2_HOME=/usr/local/maven
export PATH=\${M2_HOME}/bin:\${PATH}" > /etc/profile.d/maven.sh
  SHELL
end
