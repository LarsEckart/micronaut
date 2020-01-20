# Inbox

## Problems

* bank transfer should not work yet since micronaut:
    By default when saving an entity with a method like save(MyEntity) a SQL INSERT is always performed since Micronaut Data has no way to know whether the entity is associated to a particular session.
    If you wish to update an entity you should instead either use update(MyEntity) or even better define an appropriate update method to update only the data you want to update, for example:


## Features

* use jooq

* use postgresql instead

* no separate user for flyway (create/update schema+readwrite) and app
https://aws.amazon.com/blogs/database/managing-postgresql-users-and-roles/

## Refactoring

* try out DTOs like here: [http://www.stevenwaterman.uk/2020/01/03/rethinking-the-java-dto.html]