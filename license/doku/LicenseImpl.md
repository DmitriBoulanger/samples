
# Licensing System for a Software Product #


Table of Contents

    Introduction
    Types of Algorithms
    Hardware Serial Key
    The Scheme of the Licensing System
    The Folders Structure
    Bibliography List
    History 

## Introduction ##

cryptography methods, which can be used while implementing the licensing system pros and cons of these methods and select the possible ones for using in the application
implementation of the simplest licensing system, which guaranties the protection from cracking even if a hacker knows the source code of an algorithm.

## Types of Algorithms ##

The cryptographic algorithm, also called **cipher**, is a mathematical function used for encryption and decryption. Usually, these are two interconnected functions: one is used for encryption, another is for decryption.

If the reliability of the algorithm is based on keeping the algorithm itself in secret, then this algorithm is limited. Limited algorithms do not correspond to the nowadays standards and represent only the *historical* value.

The modern cryptography solves these problems with the help of the **key**. The key can be of any value selected from a wide range of values. The set of possible keys is called the **key space**.

There are two main types of algorithms based on the key usage: **symmetric-key algorithm** and **algorithm with the public key**.

**Symmetric-key algorithms**, sometimes called the conditional algorithms, are the algorithms where the encryption key can be calculated by the decryption key and vice versa. The encryption and decryption keys are the same in most symmetric-key algorithms. These algorithms, also called one-key or secret-key algorithms, require that the sender and the recipient reconcile the used key before the beginning of the secure message exchange. The safety of the symmetric-key algorithms is defined by the key. The key disclosure means that anyone can encrypt and decrypt messages. The key must be kept in secret as long as the transmitted messages should be secret.

**Algorithms with the public key**, also called asymmetric-key algorithms, are developed in a way that the key used for the encryption differs from that for decryption. Moreover, the decryption key can't be calculated by the encryption key (at least, during the reasonable period of time). These algorithms are called “algorithms with the public key” because the encryption key can be open: anyone can use the encryption key for the encryption of the message but only one concrete person can decrypt the message with the corresponding decryption key. In these systems, the encryption key is often called the public key and the decryption key is called the private key. The private key is sometimes called the secret key.

It is obvious that the only type of the algorithm which suits us is the algorithm with the public key because we have to store the key in the program for the authentication of the entered serial key. When choosing these algorithms, we have the guarantee that the intruder, having the public key and the source code of the algorithm, won't be able to make the key generator and create serial keys for another program copies.

There are many computer algorithms. The following three algorithms are most frequently used:

-     **DES**  (*Data Encryption Standard*) is the most popular computer encryption algorithm. It is the American and international standard. It is the symmetric-key algorithm where one and the same key is used for encryption and decryption.

-     **RSA** (*Rivest, Shamir and Adleman*) is the most popular algorithm with the public key. It is used both for the encryption and for digital signature.

-     **DSA*** (D*igital Signature Algorithm*, is used as the part of the Digital Signature Standard) is another algorithm with the public key. It is used only for the digital signature and can’t be used for the encryption.

*DES does not suit because it is the symmetric-key algorithm*.

Two algorithms are left: RSA and DSA. It is easy to choose between them if we look at the structure of the work of these algorithms.

***RSA*** uses the public key for the creation of the *cipher* text from the source text. We don't need it as it is supposed that we create and send keys and they will be decrypted and compared with the source value on the client side. As it was mentioned above, we can use only the public key on the client side in order not to compromise the licensing system.

Using the source text, ***DSA*** calculates the hash code and then “decrypts” it using the private key and receives the required serial key. It “encrypts” the received value on the client side and receives the hash code. Then it calculates the hash code from the source text in the usual way and compares two values. If these values coincide, then the serial key is valid.

![Alt text](svg2raster.png?raw=true "Optional Title")


................................
bool RsaVerifyVector(const std::string & publicKeyStrHex, 
	const std::string& source, const std::vector<char>& sign)
    {
        CryptoPP::HexDecoder decoder;
        decoder.Put( (byte*)publicKeyStrHex.c_str(), publicKeyStrHex.size() );
        decoder.MessageEnd();

        CryptoPP::RSA::PublicKey publicKey;
        publicKey.Load( decoder );

        // Verifier object
        CryptoPP::RSASS<CryptoPP::PSS, CryptoPP::SHA1>::Verifier verifier( publicKey );

        std::vector<char> rawSignature;
        std::string signStr(utils::GetBeginOf(sign), sign.size());
        utils::FromHexString(utils::string2wstring(signStr), &rawSignature);
        // Verify
        const char * pData = utils::GetBeginOf(source);
        return verifier.VerifyMessage( (const byte*) pData,
            source.size(), (const byte*) utils::GetBeginOf(rawSignature), 
		rawSignature.size() );
    }
………………………………………………
void RsaSignVector(const std::string & privateKeyStrHex, 
	const std::vector<char> & vec, std::string & sign)
    {
        // Pseudo Random Number Generator
        CryptoPP::AutoSeededRandomPool rng;
        // Generate Parameters
        CryptoPP::InvertibleRSAFunction params;
        params.GenerateRandomWithKeySize( rng, 1536 );

        CryptoPP::HexDecoder decoder;
        decoder.Put( (byte*)privateKeyStrHex.c_str(), privateKeyStrHex.size() );
        decoder.MessageEnd();

        CryptoPP::RSA::PrivateKey privateKey; // Private
        privateKey.Load( decoder );


        CryptoPP::RSASS<CryptoPP::PSS, CryptoPP::SHA1>::Signer signer( privateKey );

        size_t length = signer.MaxSignatureLength();
        CryptoPP::SecByteBlock signature( length );

        // Sign message
        signer.SignMessage( rng, (const byte*) utils::GetBeginOf(vec),
            vec.size(), signature );

        sign  = utils::wstring2string
	(utils::ToHexString<byte>(signature, signature.size()));
    }
………………………………………
void RsaGenerateStringKeys(std::string & publicKeyStr, std::string & privateKeyStr)
    {
        // Pseudo Random Number Generator
        CryptoPP::AutoSeededRandomPool rng;

        // Generate Parameters
        CryptoPP::InvertibleRSAFunction params;
        params.GenerateRandomWithKeySize( rng, 1536 );

        CryptoPP::RSA::PrivateKey privateKey( params );
        CryptoPP::RSA::PublicKey publicKey( params );

        CryptoPP::HexEncoder encoder;

        // save public Key
        encoder.Attach( new CryptoPP::StringSink( publicKeyStr ) );
        publicKey.Save( encoder );

        // save private Key
        encoder.Attach( new CryptoPP::StringSink( privateKeyStr ) );
        privateKey.Save( encoder );
    }
………………………………

Hardware Serial Key

We need to have the unique identifier of the computer in order to make sure that our serial key is used on the computer where we granted a license. There are many methods of its obtaining. But we chose HardDisk Serial Key for this case. It has a rather readable form and small size, but the probability of collisions is very low.
 

  BOOL CSmartReader::ReadSMARTInfo(BYTE ucDriveIndex)
  {
    HANDLE hDevice=NULL;
    WCHAR wTempDriveName[MAX_PATH]={0};
    BOOL bRet=FALSE;
    DWORD dwRet=0;

    wsprintf(wTempDriveName,L"\\\\.\\PHYSICALDRIVE%d",ucDriveIndex);
    hDevice=CreateFile(wTempDriveName,GENERIC_READ|GENERIC_WRITE,FILE_SHARE_READ|
	FILE_SHARE_WRITE,NULL,OPEN_EXISTING,FILE_ATTRIBUTE_SYSTEM,NULL);
    if(hDevice!=INVALID_HANDLE_VALUE)
    {
        bRet=DeviceIoControl(hDevice,SMART_GET_VERSION,NULL,0,
	&m_stDrivesInfo[ucDriveIndex].m_stGVIP,sizeof(GETVERSIONINPARAMS),&dwRet,NULL);
        if(bRet)
        {			
            if((m_stDrivesInfo[ucDriveIndex].m_stGVIP.fCapabilities & 
					CAP_SMART_CMD)==CAP_SMART_CMD)
            {
                if(IsSmartEnabled(hDevice,ucDriveIndex))
                {
                    bRet=CollectDriveInfo(hDevice,ucDriveIndex);
                    bRet=ReadSMARTAttributes(hDevice,ucDriveIndex);
                }
            }
        }
        CloseHandle(hDevice);
    }
    return bRet;
  }

  BOOL CSmartReader::CollectDriveInfo(HANDLE hDevice,UCHAR ucDriveIndex)
  {
    BOOL bRet = FALSE;
    SENDCMDINPARAMS stCIP = {0};
    DWORD dwRet = 0;
    char szOutput[OUT_BUFFER_SIZE] = {0};

    stCIP.cBufferSize=IDENTIFY_BUFFER_SIZE;
    stCIP.bDriveNumber =ucDriveIndex;
    stCIP.irDriveRegs.bFeaturesReg=0;
    stCIP.irDriveRegs.bSectorCountReg = 1;
    stCIP.irDriveRegs.bSectorNumberReg = 1;
    stCIP.irDriveRegs.bCylLowReg = 0;
    stCIP.irDriveRegs.bCylHighReg = 0;
    stCIP.irDriveRegs.bDriveHeadReg = DRIVE_HEAD_REG;
    stCIP.irDriveRegs.bCommandReg = ID_CMD;

    bRet=DeviceIoControl(hDevice,SMART_RCV_DRIVE_DATA,&stCIP,
	sizeof(stCIP),szOutput,OUT_BUFFER_SIZE,&dwRet,NULL);
    if(bRet)
    {
        CopyMemory(&m_stDrivesInfo[ucDriveIndex].m_stInfo, 
	szOutput+16, sizeof(ST_IDSECTOR));
        ConvertString(m_stDrivesInfo[ucDriveIndex].m_stInfo.sSerialNumber, 20);
    }
    else
    {
        dwRet=GetLastError();
    }
    return bRet;
}

As can be seen, we receive the information about hard drives, including the sSerialKey, which was mentioned above.

So, we have the algorithms of signing, verification, and acquiring of the disk serial number.
The Scheme of the Licensing System

We can see the common scheme of the work of the licensing system.
schema.png

The LicenseManager source code is the following:

    std::string publicKey;
    std::string privateKey;

    std::vector<char> vecPrivate;   

    utils::SaveResToVector(L"PRIVATE_KEY", IDR_FILE_PRIVATE_KEY, &vecPrivate );

    //this is an example of how to generate public and private keys
    //you need to save them in .rc files or somewhere else after they are first generated
    if (vecPrivate.empty())
    {
        utils::RsaGenerateStringKeys(publicKey, privateKey);
        std::vector<char> vecPublic(publicKey.begin(), publicKey.end());
        vecPrivate.assign(privateKey.begin(), privateKey.end());
        utils::SaveVectorToFile(L"keys\\private.bin", vecPrivate);
        utils::SaveVectorToFile(L"keys\\public.bin", vecPublic);
    }
    else
    {
        privateKey.assign(vecPrivate.begin(), vecPrivate.end());
    }
    
    std::string sign;
    
    std::vector<char> smallFile;
    std::string serialKey;

    //Input the hardware serial key, that your client application will give you
    std::cout << "Input your serial key here : ";
    std::cin >> serialKey;
    
    //make it vector and sign using utils
    std::vector<char> hardwareSerialKey(serialKey.begin(), serialKey.end());
    utils::RsaSignVector(privateKey, hardwareSerialKey, sign);

    //save signed value to the file and send the file to user
    std::vector<char> vec(sign.begin(), sign.end());
    utils::SaveVectorToFile(L"License.dat", vec);

    std::cout << "Your license.dat file was saved in program's directory" << std::endl;
    std::cout << "Press any key...";
    std::cin.get();

    return 0;
……………………………

The client side looks like the following:
Collapse | Copy Code

………………………………………
std::string publicKey;
    std::vector<char> vecPublic;
    utils::SaveResToVector(L"PUBLIC_KEY", IDR_FILE_PUBLIC_KEY, &vecPublic);
    if (vecPublic.empty())
    {
        std::cout << "Cant find public key\nPress Any key..." << std::endl;
        std::cin.get();
        return 0;
    }
    publicKey.assign(vecPublic.begin(), vecPublic.end());
    CSmartReader reader;
    
    ST_IDSECTOR *pSectorInfo = NULL;
    
    reader.ReadSMARTValuesForAllDrives();
    
    pSectorInfo=&reader.m_stDrivesInfo[0].m_stInfo;
    
    std::string str(pSectorInfo->sSerialNumber);

    std::stringstream trimmer;
    trimmer << str;
    trimmer >> str;

    std::cout << "Your hardware Serial Key is : " << str << std::endl;
    
    try
    {
        std::vector<char> vec;
        const std::wstring fileName = L"License.dat";
        utils::LoadFileToVector(fileName, vec);

        if (utils::RsaVerifyVector(publicKey, str, vec))
        {
            std::cout << "License is valid. Continue working" << std::endl;
        }
        else
        {
            std::cout << "License is invalid. Program is being closed" << std::endl;
        }
    }
    catch(const std::logic_error& ex)
    {
        std::cout << "You do not have license.dat file installed. 
			Please put it in program dir." << std::endl;
    }

    std::cout << "Press any key...";
    std::cin.get();
……………………………………………………

The Folders Structure

    .\ClientTest – The client side
    .\DigitalSignature – The License Manager side
    .\Utils – Utilities and wrappers for the Crypto++® Library
    .\ cryptopp560 – Crypto++® Library

Bibliography List

    C.M. Adams and H. Mailer, "Security Related Comments Regarding McEliece's Public-Key Cryptosystem" Advances in Cryptology CRYPTO '87 Proceedings, Springer-Verlag, 1988, pp. 224-230.
    Cylink Corporation, Cylink Corporation vs. RSA Data Security, Inc., Civil Action No. C94-02332-CW, United States District Court for the Northern District of California, 30 Jun 1994.
    G.I. Davida, "Chosen Signature Cryptanalysis of the RSA iMITJ Public Key Cryptosystem," Technical Report TR-CS-82-2, Department of EECS, University of Wis- consin, 1982.
    I.M. DeLaurentis, "A Further Weakness in the Common Modulus Protocol for the RSA Cryptosystem," Cryptologia, v. 8, n. 3, Jul 1984, pp. 253-259.
    Schneier, Bruce. Applied Cryptography, Second Edition, John Wiley & Sons, 1996. ISBN 0-471-11709-9.

History

    5th August, 2010: Initial post

License

This article, along with any associated source code and files, is licensed under The Code Project Open License (CPOL)
Share

    email

About the Authors
Apriorit Inc
Apriorit Inc.
Ukraine Ukraine
ApriorIT is a Software Research and Development company that works in advanced knowledge-intensive scopes.
 
Company offers integrated research&development services for the software projects in such directions as Corporate Security, Remote Control, Mobile Development, Embedded Systems, Virtualization, Drivers and others.
 
Official site http://www.apriorit.com
Group type: Organisation

31 members

Follow on   LinkedIn LinkedIn

Sergii Bratus
Software Developer Apriorit Inc.
Ukraine Ukraine
No Biography provided

Comments and Discussions
 
You must Sign In to use this message board.
Search Comments  
	
Profile popups    Spacing   Noise   Layout   Per page    
		First Prev Next
Question	Visual Studio 2005 Pin	member	Member 11269799	30-Nov-14 13:41 
	
I can't compile it with Visual Studio 2005. I wan't to use Hardware Serial function. I use Windows XP.
Sign In·View Thread·Permalink	
Question	Windows 7 Compatibality Issues Pin	member	Rajan Paudel	17-Sep-12 22:23 
	
This is great article. I tried this in windows XP and it works well.
Does this code works well in windows 7?
What should I do to use this code in windows 7?
Sign In·View Thread·Permalink	
General	My vote of 5 Pin	member	gndnet	11-Sep-12 1:23 
	
great
Sign In·View Thread·Permalink	
General	My vote of 5 Pin	member	gndnet	18-Jul-12 1:02 
	
thanks
Sign In·View Thread·Permalink	
General	My vote of 5 Pin	member	gndnet	17-Jul-12 16:35 
	
super
Sign In·View Thread·Permalink	
Question	Possible hack for this implementation Pin	member	Nizam11	15-Feb-12 1:21 
	
Hi Sergii. Thanks for this informative article. Really appreciate it. I would like to find out if is it possible to hack the software implementing this algorithm by shipping the software with the public key replaced with a copy of the hacker's self-generated public key. That way, when a user sends the harddrive serial key to the hacker, he can generate the license.dat file using his private key since the public key shipped with the software to the user is the public key pair generated by the hacker. Am I right to say so? This is partly due to the fact that your code load the public key from a file. As far as I know the public key should be at least hard-coded in the software right?
Sign In·View Thread·Permalink	
General	Question about self-decryption Pin	member	yasihunter	13-Apr-11 4:28 
	
First, let me appreciate your idea and helpful source code about the “Implementation of The Licensing System for a Software Product”.
I want to ask for your help and guidance about a problem that I am facing in developing software which needs self-decryption through runtime. I want to keep my private key secure within the source code. What is the best solution to keep this key secure and out of rich? I think, if I want to keep the key in resource, it would be extractable by hackers. What is your solution?
 
thanks
Sign In·View Thread·Permalink	
General	Re: Question about self-decryption Pin	member	Sergii Bratus	14-Apr-11 0:00 
General	My vote of 5 Pin	member	gndnet	8-Jan-11 21:45 
	
5
Sign In·View Thread·Permalink	
General	Multiple application [modified] Pin	member	aldo hexosa	6-Oct-10 0:41 
	
How to implement this licensing system if i have 2 application? I need different license key for each application.
Thanks for your great article

modified on Wednesday, October 6, 2010 5:47 AM

Sign In·View Thread·Permalink	
General	My vote of 5 Pin	member	vcop25	4-Oct-10 6:27 
	
Excellent
Sign In·View Thread·Permalink	
Question	how to avoid if(A==B)? Pin	member	NewttleLi	1-Sep-10 21:50 
	
Maybe cracker use code to jump it,how to avoid?Thanks.
Sign In·View Thread·Permalink	
Answer	Re: how to avoid if(A==B)? Pin	member	Chris Losinger	17-Jan-12 4:59 
Question	why is 1536? Pin	member	ftai	24-Aug-10 21:48 
	
good work! but I don't know why is 1536
Sign In·View Thread·Permalink	
General	My vote of 5 Pin	member	Member 4320844	15-Aug-10 12:17 
	
strong article
Sign In·View Thread·Permalink	
General	Timed License Pin	member	JeffBilkey	11-Aug-10 15:33 
	
Have you given any thought to a Timed License such as one the lasts for seven days. I believe you would need to hide something on the computer with the start or expirey date. However with the later versions of windows it is getting more and more difficult to hide a file or something in the registry, that can't be easily found and deleted.
Sign In·View Thread·Permalink	
General	Suggestions.... Pin	member	VaKa	11-Aug-10 10:06 
	
Very interesting topic. But with good start we have bad finish... or didnt get it at all Laugh | :laugh:
 
I think, it will be more useful explain what you do but not paste here blocks of uncommented code.
 
For example, only from function headers I know that you use RSA Smile | :) (or i miss something Cool | :cool: )
 
What coder should do to change you code if he (or she Rose | [Rose] ) want deal with, mmm, "login name" in corporate network or whatever...?
 
Good start anyway Thumbs Up | :thumbsup:
 
---------------------------------------
Aaaah, my pure english Confused | :confused:
Sign In·View Thread·Permalink	
Question	Need more explanation Pin	member	PatLeCat	10-Aug-10 0:18 
	
I like the topic very much and I thought you started well with explaning at the beginning. Unfortunately you stop explaining once the 1st line of code appears. Sigh | :sigh:
You could for example explain the steps in your nice looking scheme diagram and also add more explanation to the code and why you did what you did. After all, an expert in the field doesn't need to read your article, us novices do Cool | :cool:
 
How to protect the client AND the license manager from being hacked to easily would also be an important chapter to add - methinks.
Sign In·View Thread·Permalink	5.00/5 (2 votes)
General	My vote of 3 Pin	member	PatLeCat	10-Aug-10 0:14 
	
Excellent topic but I would need a lot more explanation to give it a 5.
Sign In·View Thread·Permalink	
General	Good start. Pin	member	John M. Drescher	8-Aug-10 5:29 
	
However I would caution the readers that this is only a start. Any successful licencing system has to protect the client application from tampering otherwise it would take 5 minutes to remove the licence check for even a novice hacker.
John

Sign In·View Thread·Permalink	5.00/5 (2 votes)
General	Re: Good start. Pin	member	S.H.Bouwhuis	9-Aug-10 23:22 
General	Re: Good start. Pin	member	VaKa	11-Aug-10 10:08 
General	Excellent Article! -- My vote of 5 Pin	member	Sharjith	6-Aug-10 12:17 
	
Great, Well explained article on licensing. This kind of knowledge is seldom exposed by professionals to the common world. Thumbs Up | :thumbsup:
RegardsSmile | :)
N. Sharjith

Sign In·View Thread·Permalink	
General	great work Pin	member	mounte@cognimatics	6-Aug-10 3:52 
	
Great work explaining a basic licensing scheme.
Do you have any suggestions on revoking a license, protect it from spoofing (if a user can spoof the hardware information from an authorized user he could use the same license key)?
Sign In·View Thread·Permalink	
General	My vote of 5 Pin	member	emilio_grv	6-Aug-10 3:18 
	
Finally a simple explanation on what goes on with signed data
Sign In·View Thread·Permalink	
General	COOL! Pin	member	LoveVc	5-Aug-10 16:58 
	
very cool!
 
but can't run under win7!
Sign In·View Thread·Permalink	
General	Re: COOL! Pin	member	rmarion	6-Aug-10 8:13 
General	Re: COOL! Pin	member	LoveVc	8-Aug-10 15:49 
General	Re: COOL! Pin	member	Sergii Bratus	11-Aug-10 2:57 
Last Visit: 31-Dec-99 19:00     Last Update: 20-Dec-14 20:16	Refresh	1

General General    News News    Suggestion Suggestion    Question Question    Bug Bug    Answer Answer    Joke Joke    Rant Rant    Admin Admin   
	
Info
First Posted 	5 Aug 2010
Views 	59,963
Downloads 	7,633
Bookmarked 	232 times
Permalink | Advertise | Privacy | Terms of Use | Mobile
Web02 | 2.8.141220.1 | Last Updated 5 Aug 2010
Sprache auswählen​▼
Article Copyright 2010 by Apriorit Inc, Sergii Bratus
Everything else Copyright © CodeProject, 1999-2014
Layout: fixed | fluid

