FROM httpd:2.4.65

# change to root user to allow package installations
USER 0

# install apache module auth openidc
RUN apt-get update && \ 
	rm -rf /usr/lib/apache2/modules && \
	rm -rf /var/lib/apt/lists/* && \
	echo "alias l='ls -al --color'" >> /root/.bashrc

# enable already installed modules we need
RUN sed -i \
		-e 's/^#\(Include .*httpd-ssl.conf\)/\1/' \
		-e 's/^#\(LoadModule .*mod_ssl.so\)/\1/' \
		-e 's/^#\(LoadModule .*mod_socache_shmcb.so\)/\1/' \
		-e 's/^#\(LoadModule .*mod_proxy.so\)/\1/' \
		-e 's/^#\(LoadModule .*mod_proxy_http.so\)/\1/' \
		-e 's/^#\(LoadModule .*mod_rewrite.so\)/\1/' \
		conf/httpd.conf

# create empty vhost config file and include it to main config
RUN echo '\nIncludeOptional conf/vhosts/*.conf' >> /usr/local/apache2/conf/httpd.conf
	
# change back user
USER 1001