# Etapa de construção da api
FROM openjdk:17-alpine AS apijdk

# Porta padrão do Tomcat
EXPOSE 8080

#Porta padrão do POSTGRESQL
EXPOSE 5432

# Criar diretório de trabalho para o api
RUN mkdir -p /app/api

#muda para o diretorio
WORKDIR /app/api

# Copiar código-fonte da api
COPY api .

# Construir o JAR da aplicação
RUN ./mvnw package

# Copiar JDK para dentro do container
RUN mkdir -p /app/jdk && cp -r $JAVA_HOME/* /app/jdk

# Verificar e substituir o link simbólico do cacerts se necessário
RUN if test -L /app/jdk/lib/security/cacerts ; then \
  caminho_original=$(realpath $JAVA_HOME/lib/security/cacerts) && \
  rm /app/jdk/lib/security/cacerts && \
  cp $caminho_original /app/jdk/lib/security && \
  ls -l /app/jdk/lib/security/cacerts ; \
fi

# Etapa de inicialização do PostgreSQL
FROM postgres:alpine

# Variáveis de ambiente para inicialização do PostgreSQL
ENV POSTGRES_DB postgres
ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD postgres123

# Criar diretório e copiar scripts de inicialização do banco de dados
RUN mkdir -p /docker-entrypoint-initdb.d
WORKDIR /docker-entrypoint-initdb.d
COPY scripts .

# Criar diretório para a aplicação e copiar script de espera
RUN mkdir -p /app/jdk

WORKDIR /app


# Copiar JAR e JDK da etapa anterior
COPY --from=apijdk /app/api/target/*.jar /app/

COPY --from=apijdk /app/jdk /app/jdk
RUN chmod +x /app/jdk/bin/*