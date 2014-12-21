Copyright (c) 2013 David Stites, Patrik Dufresne and others.
http://git.patrikdufresne.com/common/license.git

LICENSING
=========

This program is free software; you can redistribute it and/or modify
it under the terms of Apache License 2.0
	
You should have received a copy of the Apache License 2.0
along with this program in the file named "LICENSE".

This package is based on the following article:
http://blog.afewguyscoding.com/2012/02/licensing-module-java/

Generate your keys
==================

Create the private key (containing informationto create the public key).

  $ openssl genrsa -out privkey.pem 2048
  $ openssl pkcs8 -topk8 -in privkey.pem -inform PEM -nocrypt -outform DER -out privkey.der
 
Extract the public key, fur publishing.
  $ openssl rsa -in privkey.pem -out pubkey.der -pubout -outform DER