#!/bin/bash

# Espera até que o PostgreSQL esteja disponível na porta 5432
until pg_isready -h localhost -p 5432; do
  echo "Aguardando PostgreSQL iniciar..."
  sleep 2
done

# Executa o comando passado (inicialização da aplicação Java)
exec "$@"