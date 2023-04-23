# LOGJSON

```json
{
  "timestamp":"2023-04-23T21:58:09.908Z",
  "thread":"http-nio-8080-exec-2",
  "level":"INFO",
  "logger":"org.example.log.json.config.CustomOncePerRequestFilter",
  "sessionId":"153d2222-2671-4747-9dca-22a4795ed64f",
  "message":{"request":null,"method":"GET","response":{"cpf":"CPF","cnpj":"CNPJ"},
  "time":44,"uri":"/test","status":200},
  "service":"LogJson"
}
```

### JsonTemplateLayout: locationInfoEnabled="true"

```json
"class": {
"$resolver": "source",
"field": "className"
},
"method": {
"$resolver": "source",
"field": "methodName"
},
"line": {
"$resolver": "source",
"field": "lineNumber"
}
```


[Documentation](https://logging.apache.org/log4j/2.x/manual/json-template-layout.html)

