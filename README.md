# cornfield-simulator

SMS based cornfield simulator.

## Configure
Copy `config.tml` to `corn.yml`, add your Twilio credentials, and modify as you
see fit. Then, point your Twilio 'Request URL' to http://<hostname>:<port>/sms
and set request type to 'GET'.

## Build
```
$ mvn package
```

## Run
```
$ java -jar target/cornfield-simulator-0.1.jar server config.yml
```

## License

Copyright 2015 Cornfield Simulators, LLC

Licensed under the GPLv3: http://www.gnu.org/licenses/gpl-3.0.html
