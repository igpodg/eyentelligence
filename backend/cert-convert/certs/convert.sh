#!/bin/bash

openssl pkcs12 -export -passout pass:springboot -out ca.p12 -inkey ca.key -in ca.crt
openssl pkcs12 -export -passout pass:springboot -out server.p12 -inkey server.key -in server.crt
