# my micronaut playground

continuously deployed to heroku by travis.


## directly interact with the web app through heroku cli

### shut down

heroku ps:scale web=0

### scale up

heroku ps:scale web=1

### get dyno info

heroku ps

### deploy

git push heroku master

### try

heroku open

## database stuff for local development, do before running app/tests

docker pull postgres:12.1
mkdir -p $HOME/docker/volumes/postgres
docker run --rm --name pg-docker -e POSTGRES_PASSWORD=docker -p 5432:5432 -v $HOME/docker/volumes/postgres:/var/lib/postgresql/data postgres:12.1

(new window now since other window shows postgres  logs)
export PGPASSWORD=docker

docker exec -it pg-docker psql -U postgres postgres

