
# Absract Licensing Architecture #

Table of Contents

    Basic Issues
    Cryptography Algorithms
    Hardware Serial Key
    Architecture of a Licensing System

## Basic Issues ##

    Cryptography methods to be used in implementation of a licensing system
    Pros and cons of these methods
    Choice of a cryptography
    Protection even if a hacker knows the source code

## Cryptography Algorithms ##

The cryptographic algorithm, also called **cipher**, is a mathematical function used for encryption and decryption. It has two interconnected functions: one is used for encryption, another is for decryption. If an algorithm is based on keeping itself in secret, then this algorithm is limited. Such algorithms have only a *historical* value. Actual approach use a **key** that can be selected from a wide range of values. The set of possible keys is called the **key space**. There are two main types of algorithms based on the key usage: 

- **symmetric-key algorithm** 
- **algorithm with the public key**

**Symmetric-key algorithms** (*conditional algorithms*), are the algorithms where the encryption key can be calculated by the decryption key and vice versa. The encryption and decryption keys are the same in most symmetric-key algorithms. These algorithms, also called *one-key* or *secret-key* algorithms, require that the sender and the recipient reconcile the used key before the beginning of the secure message exchange. The safety of the symmetric-key algorithms is defined by the key. The key disclosure means that anyone can encrypt and decrypt messages. The key must be kept in secret as long as the transmitted messages should be secret.

**Algorithms with the public key** (*asymmetric-key algorithms*), are developed in a way that the key used for the encryption differs from that for decryption. Moreover, the decryption key can't be calculated by the encryption key (at least, during the reasonable period of time). These algorithms are called “algorithms with the public key” because the encryption key can be open: anyone can use the encryption key for the encryption of the message but only one concrete person can decrypt the message with the corresponding decryption key. The encryption key is called the public key and the decryption key is called the private key (secret key).

A licensing system should use a cryptographic algorithm with a public key because:

 - the key has to be stored in the program for the authentication of the entered **serial key**
-  an intruder having the public key and the source code of the algorithm, won't be able to make the key generator and create other serial keys for another program copies

The below cryptographic algorithms are avialble and most frequently used:

    **DES**  (*Data Encryption Standard*) is the most popular computer encryption algorithm. It is the American and international standard. It is the symmetric-key algorithm where one and the same key is used for encryption and decryption.

    **RSA** (*Rivest, Shamir and Adleman*) is the most popular algorithm with the public key. It is used both for the encryption and for digital signature.

    **DSA*** (*Digital Signature Algorithm*, part of the Digital Signature Standard) is another algorithm with the public key. It is used only for the digital signature and can’t be used for the encryption.

*DES does not suit because it is the symmetric-key algorithm*.

Two algorithms are left: RSA and DSA. It is easy to choose between them if we look at the structure of the work of these algorithms.

***RSA*** uses the public key for the creation of the *cipher* text from the source text. We don't need it as it is supposed that we create and send keys and they will be decrypted and compared with the source value on the client side. As it was mentioned above, we can use only the public key on the client side in order not to compromise the licensing system.

***DSA*** calculates the hash code of the source text, and then “decrypts” it using the private key and receives the required *serial key*. It “encrypts” the received value on the client side and receives the hash code. Then it calculates the hash code from the source text in the usual way and compares two values. If these values coincide, then the serial key is valid.

![Sign and verify](sign_verify.png?raw=true "Sign and verify")

## Hardware Serial Key ##

The *unique identifier of the computer* is required. It is used to ensure that our *serial key* is used on the computer where a license have been granted. There are many methods to get it.
 
## Architecture of a Licensing System ##

![Basic licensing system](basic_licensing.png?raw=true "Basic licensing system")

## Used references ##

[Implementation of the Licensing System for a Software Product](http://www.codeproject.com/Articles/99499/Implementation-of-the-Licensing-System-for-a-Softw)
 
 
 


