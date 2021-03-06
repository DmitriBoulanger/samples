
# Abstract Licensing Architecture #

    Cryptography Algorithms in Licensing Systems
    Authenticating the License File and License Terms
    Hardware Serial Key
    Architecture of a Licensing System
	Used articles

## Cryptography Algorithms in Licensing Systems ##

The cryptographic algorithm, also called **cipher**, performs encryption and decryption. It has two interconnected functions: one is used for encryption, another is for decryption. If an algorithm relies on keeping itself in secret, then it only has a *historical* value. Actual approaches use **keys**. There are two main types of such algorithms: 

- **symmetric-key algorithm** 
- **algorithm with the public key**

**Symmetric-key algorithms** (*conditional algorithms*), are the algorithms where the encryption key can be calculated by the decryption key and vice versa. The encryption and decryption keys are the same in most symmetric-key algorithms. These algorithms, also called *one-key* or *secret-key* algorithms, require that the sender and the recipient reconcile the used key before the starting secure message exchange. The safety of the symmetric-key algorithms is defined by the key. The key disclosure means that anyone can encrypt and decrypt messages. The key must be kept in secret as long as the transmitted messages should be secret.

**Algorithms with the public key** (*asymmetric-key algorithms*), are developed in a way that the key used for the encryption differs from that for decryption. Moreover, the decryption key can't be calculated by the encryption key (at least, during the reasonable period of time). These algorithms are called “algorithms with the public key” because the encryption key can be open: anyone can use the encryption key for the encryption of the message but only one concrete person can decrypt the message with the corresponding decryption key. The encryption key is called the public key and the decryption key is called the private key (secret key).

A licensing system typically use a cryptographic algorithm with a public key. There are the following two important reasons:

- the key has to be stored in the program at the client side for the authentication of the entered **license serial key**. A license verification has to be done at the client side without having access to the private key. If a symmetrical encryption is used to encrypt the license-terms in the serial key (so they could not be seen or changed), then the client-application itself needs access to the encryption key in order to decrypt the file. The key could be found within the product executables and extracted, allowing hackers to generate their own license files.

- a hacker only having the public key and the corresponding code of the algorithm, won't be able to build a key generator and create other serial keys (licenses) for another program copies. Asymmetrical encryption still requires that the client have access to the public key, but knowing the public key doesn't not help anyone trying to hack the licensing system. However, the **danger** lies in hacker being able to replace the public key with one of their own making. This requires him to get into the product executables and alter the sequence of bytes that defines the public-key (whether it is a string-literal or a resource).

The below cryptographic algorithms are most frequently used:
 
- **DES**  (*Data Encryption Standard*) is the most popular encryption algorithm. It is the American and international standard. It is the *symmetric-key* algorithm where the same key is used for encryption and decryption
       
- **RSA** (*Rivest, Shamir and Adleman*) is the most popular algorithm with the public key. It is used both for the encryption and for digital signatures

- **DSA** (*Digital Signature Algorithm*, part of the Digital Signature Standard) is another algorithm with the public key. It isused for the digital signature and for the encryption as well.

Sine DES is a symmetric-key algorithm it cannot be used in licensing systems. So only the two algorithms are acceptable for licensing: RSA and DSA

**RSA** uses the public key for the creation of the *cipher* text from the source text. Note that it is supposed that a license manager creates and sends keys to the client that decrypts and compares with them with the source value. As it was mentioned above, only the public key can be used on the client side in order not to compromise the licensing system.

**DSA** calculates the hash code of the source text, and then “decrypts” it using the private key and receives the required *serial key*. It “encrypts” the received value on the client side and receives the hash code. Then it calculates the hash code from the source text in the usual way and compares two values. If these values coincide, then the serial key is valid.

## Authenticating the License File and License Terms ##

A digital signature is produced from the data being verified by generating a hash-code from the source data, and encrypting this hash with a private key. The hash code will be unique to the source data if even one bit in the source data changes, the resulting hash-code will be quite different. (The "strength" of any hashing algorithm is indicated by how much the hash-code changes with the smallest possible edit of the source data).

![Sign and verify](sign_verify.png?raw=true "Sign and verify using DSA")

At the client side verification of the data is done by using the public key to check if the digital signature (the encrypted hash) still matches the data that is being verified. If any part of the data is changed, then the digital signature doesn't not match.

A license file specifies the licensee (the user's name, address, contact details, etc.), the product being licensed, and the start and end dates of that license. The **client** asks for the license file on first use, copies it into a known location (database, then validates that the terms of the license are valid (correct software version, within the applicable date-range, etc.) 

The main point here is to ensure that the license file that is still **authentic**. If it is, then there is an excellent licensing system: the license terms can be as simple or complex (i.e. blanket authorization, or only certain features enabled), they can have start and end dates, be restricted to particular applications, computers or even restricted to particular users. 

## Hardware Serial Key ##

The *unique identifier of the computer* is required. It is used to ensure that the *serial key*  (license) is *ONLY* used on the computer where a license has been granted. There are many methods to get such a unique fingerprint.

![Finger-print of a hardware system](fingerprint.png?raw=true "Finger-print of a hardware system")

The main point here is that which features and/or parameters of the computer ad/or network are captured in the finger-print. Negative pattern include some terrible “unique” identifiers of a computer:

- **IP address** is absolutely useless as a unique identifier because it can be shared by multiple computers and it can (and does) change often
  
- **MAC address** isn't a terrible unique identifier when used in tandem with several other components in the computer, but by itself it's nearly as useless as the IP address

Poorly designed fingerprints are failing to handle customers fixing and improving their computers. Customers add RAM, replace hard drives, and change their computers in other ways.
As a result, they suddenly becoming “de-activated”. The licensing solution should handle these normal user behaviors without forcing the customer to request a re-activation on their slightly altered computer.  For instance, if a customer adds more RAM or swaps out a hard drive they won't have to reactivate. The correct solution is fuzzy matching. It means that even when users change some minor components of their computers their licenses remain activated
 
![Fuzzy fingerprint of a hardware system](fuzzy-fingerprint.png?raw=true "Fuzzy fingerprint of a hardware system")


## Architecture of a Licensing System ##

![Basic licensing system](basic_licensing.jpeg?raw=true "Basic licensing system")

An actual deployment of the above licensing system at the **customer** (client) side requires the following. First of all, the customer (client) receives a copy of the product and **initial licensing data** as well. These data includes at least the *public key*. This public key is generated at the product owner side (using the license-manager server) and it is unique (customer-specific). Next, the following steps should be done:
  
- the customer using the available licensing modules gets the **hardware serial key**. Next, using this finger-print it creates the **registration request**. Finally, it sends the request to the license-manager server (at the product owner side). In this way the customer registers the particular computer (or network) where the license has been granted. 

![Registration of a license](activate1.png?raw=true "Registration of a license")

- the license-manager server accepts the customer request and generates the **license** that is ONLY valid for computers satisfying the *hardware serial key* (finger-print) in the request. Next, the license-manager server sends the *license* (cryptographically signed block) to the customer (client)

![Receiving of a license](activate2.png?raw=true "Receiving of a license")

- the customer (client) installs the received *license* submitting the *hardware serial key* (finger-print)
  
- only after the installation of the license the product-copy is operational. 

The cryptographically signed block is permanently used by the product to check whether the customer is still allowed to use product or not. At least  it is used to check if the current computer's “fingerprint” and product key have been activated. More specifically, it is checked that the computer is activated by comparing the computer's fingerprint to the cryptographic signature. This is done without being connected to the license-manager server. Since the activation block has been created using asymmetric encryption, no one can generate a fake activation block

There are the following open issues:

1. A special protocol is needed if the customer wants to move the product to another computer or network. Therefore, customers should be allowed to de-activate the current license using the license-manager server and activate it again at the new location 

2. License terms can include special constraints to limit usage of some operations, e.g. number of files that has already been processed by the product. How to track this? For instance, if the customer moves the product to another location with a new finger-print, the usage values should be re-installed at the new product location

## Used articles ##

[(2010) Implementation of the Licensing System for a Software Product](http://www.codeproject.com/Articles/99499/Implementation-of-the-Licensing-System-for-a-Softw)

[(2011) RSA License Protection](http://www.codeproject.com/Articles/203840/RSA-License-Protection)

[(2014) Creating a license system based on asymmetric encryption (RSA or ECDSA)](http://crypto.stackexchange.com/questions/16483/creating-a-license-system-based-on-asymmetric-encryption-rsa-or-ecdsa)

[(2014) How hardware-locked licensing works](http://wyday.com/limelm/features/why/#hardware-locked-descr)

[(2003) Licensing Using Symmetric and Asymmetric Cryptography](http://www.drdobbs.com/licensing-using-symmetric-and-asymmetric/184401687)
 
 
 


