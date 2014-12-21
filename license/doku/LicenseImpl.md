
# Absract Licensing Architecture #

Table of Contents

    Basic Issues
    Cryptography Algorithms in Licensing Systems
    Hardware Serial Key
    Architecture of a Licensing System

## Basic Issues ##

    Cryptography methods to be used in implementation of a licensing system
    Pros and cons of these methods
    Choice of a cryptography
    Protection even if a hacker knows the source code

## Cryptography Algorithms in Licensing Systems ##

The cryptographic algorithm, also called **cipher**, performs encryption and decryption. It has two interconnected functions: one is used for encryption, another is for decryption. If an algorithm relies on keeping itself in secret, then it is limited. Such algorithms have only a *historical* value. Actual approach use a **key**. There are two main types of algorithms based on the key usage: 

- **symmetric-key algorithm** 
- **algorithm with the public key**

**Symmetric-key algorithms** (*conditional algorithms*), are the algorithms where the encryption key can be calculated by the decryption key and vice versa. The encryption and decryption keys are the same in most symmetric-key algorithms. These algorithms, also called *one-key* or *secret-key* algorithms, require that the sender and the recipient reconcile the used key before the starting secure message exchange. The safety of the symmetric-key algorithms is defined by the key. The key disclosure means that anyone can encrypt and decrypt messages. The key must be kept in secret as long as the transmitted messages should be secret.

**Algorithms with the public key** (*asymmetric-key algorithms*), are developed in a way that the key used for the encryption differs from that for decryption. Moreover, the decryption key can't be calculated by the encryption key (at least, during the reasonable period of time). These algorithms are called “algorithms with the public key” because the encryption key can be open: anyone can use the encryption key for the encryption of the message but only one concrete person can decrypt the message with the corresponding decryption key. The encryption key is called the public key and the decryption key is called the private key (secret key).

A licensing system typically use a cryptographic algorithm with a public key. There are the following important reasons:

 - the key has to be stored in the program for the authentication of the entered **serial key**
-  an intruder having the public key and the source code of the algorithm, won't be able to make the key generator and create other serial keys for another program copies

The below cryptographic algorithms are available and most frequently used:
 
-      **DES**  (*Data Encryption Standard*) is the most popular computer encryption algorithm. It is the American and international standard. It is the symmetric-key algorithm where one and the same key is used for encryption and decryption
       
-      **RSA** (*Rivest, Shamir and Adleman*) is the most popular algorithm with the public key. It is used both for the encryption and for digital signature.

-      **DSA** (*Digital Signature Algorithm*, part of the Digital Signature Standard) is another algorithm with the public key. It is used only for the digital signature and can’t be used for the encryption.

*DES does not suit because it is the symmetric-key algorithm*.

Two algorithms are left: RSA and DSA. It is easy to choose between them if we look at the structure of the work of these algorithms.

**RSA** uses the public key for the creation of the *cipher* text from the source text. We don't need it as it is supposed that we create and send keys and they will be decrypted and compared with the source value on the client side. As it was mentioned above, we can use only the public key on the client side in order not to compromise the licensing system.

**DSA** calculates the hash code of the source text, and then “decrypts” it using the private key and receives the required *serial key*. It “encrypts” the received value on the client side and receives the hash code. Then it calculates the hash code from the source text in the usual way and compares two values. If these values coincide, then the serial key is valid.

## Authenticating the License File ##

A digital signature is produced from the data being verified by generating a hash-code from the source data, and encrypting this hash with a private key. The hash code will be unique to the source data if even one bit in the source data changes, the resulting hashcode will be quite different. (The "strength" of any hashing algorithm is indicated by how much the hash-code changes with the smallest possible edit of the source data).

Verification of the data is achieved by using the public key to check if the digital signature (the encrypted hash) still matches the data that is being verified. If any part of the data is changed, then the digital signature will not match.

This means that you can verify that the license file the system is using definitely came from you, (the sellers of the software) without having to give the application access to the private key. If you were to use symmetrical encryption to encrypt the license-terms (so they could not be seen or changed), then the application itself would need access to the encryption key, in order to decrypt the file. The key could be found within the application EXE and extracted, allowing pirates to generate their own license files.

Asymmetrical encryption still requires that the application have access to the public key, but knowing the public key will not help anyone trying to hack the system. However, the danger lies in hacker being able to replace the public key with one of their own making. This would require them to get into the product executables and alter the sequence of bytes that defines the public-key (whether it is a string-literal or a resource within the product executables). Sgning the executables itself is one potential way of trying to stop this: modification of the executable will generate a different signature, and the application can be instructed not to open. (This is how the click-once manifests work, in fact, using click-once to deploy your application makes it (almost) impossible to modify that application without regenerating the click-once manifest file.) 

![Sign and verify](sign_verify.png?raw=true "Sign and verify")

## Hardware Serial Key ##

The *unique identifier of the computer* is required. It is used to ensure that our *serial key* is used on the computer where a license have been granted. There are many methods to get it.

## Message Authentication Code (MAC) ##
 
## License Terms File ##

A license file specifies the licensee (the user's name, address, contact details, etc.), the product being licensed, and the start and end dates of that license. The software asks for the license file on first use, copies it into a known location, then validates that the terms of the license are valid (correct software version, within the applicable date-range, etc.).

The trick here is to ensure that the license file that is supplied is authentic, if that part is covered, this is an excellent system for licensing: the license terms can be as simple or complex as you like (i.e. blanket authorization, or partial (certain features enabled)), they can have start and end dates, be restricted to particular applications or even restricted to particular users. The user isn't forced to type in a long and complex serial-number, and having the users name and contact details embedded within the terms makes it less likely that users will voluntarily share the license file. 
 
## Architecture of a Licensing System ##

![Basic licensing system](basic_licensing.jpeg?raw=true "Basic licensing system")

## Used articles ##

[Implementation of the Licensing System for a Software Product](http://www.codeproject.com/Articles/99499/Implementation-of-the-Licensing-System-for-a-Softw)

[RSA License Protection](http://www.codeproject.com/Articles/203840/RSA-License-Protection)

[Licensing Using Symmetric and Asymmetric Cryptography](http://www.drdobbs.com/licensing-using-symmetric-and-asymmetric/184401687)
 
 
 


