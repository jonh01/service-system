#!/bin/bash
# wait-for-it.sh

# Faz o script sair imediatamente se algum comando falhar.
set -e
# Armazena o primeiro argumento como host.
host="$1"
# O segundo como porta
shift
port="$1"
# Armazena todos os argumentos restantes como o comando a ser executado
shift
cmd="$@"

# Utiliza o netcat (nc) para verificar se o serviço no host e porta está disponível. Continua tentando até que esteja disponível
until nc -z "$host" "$port"; do
  >&2 echo "Postgres is unavailable - sleeping"
  #Espera 1 segundo entre as tentativas
  sleep 1
done

>&2 echo "Postgres is up - executing command"
# Executa o comando passado como argumento após o serviço estar disponível
exec $cmd
