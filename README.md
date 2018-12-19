#micronaut on heroku


##shut down

heroku ps:scale web=0

##scale up

heroku ps:scale web=1

##get dyno info

heroku ps

##deploy

git push heroku master

##try

heroku open
