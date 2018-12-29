#### Exercise 1.1.1: What is the difference between a compiler and an inerpreter?

The easiest way to think about the difference between the two is that the compiler converts your source input, into a target. This target is a new entity of its own, and is executable by the machine. On the other hand, an interpreter performs translating source input to machine instructions dynamically (on the fly).  

Knowing this, it makes it clear that interpreted programs execute much slower than compiled programs. 

#### Exercise 1.1.2: What are the advantages of (a) a compiler over an interpreter and (b) an interpreter over a compiler?

A program that has been created by a compiler will almost execute faster over an interpeter. If the compiler has many stages that deal with optimizing the intermediate output of the source, before delivering the final target program, it can do all of the processing then, before the initial execution of the program. This allows for the program to be more optimized for runtime -- although optimizations are not guaranteed.

An interpreted program will run slower as it will be executed statement by statement from an input. The advantages it does bring is that interpreters can be built for various platforms. This allows a wider range of machine architectures to execute the program. Interpreted programs may also be easier to debug as there is not compilation stages to run through. Making changes can be faster in that the programmer can modify the program and feed it through the interpreter for execution right away.

#### Exercise 1.1.3: What advantages are there to a language-processing system in which the compiler produces assembly language rather than machine language?

Producing assembly language first allows for relocatable code. This means that the relocatable code can be linked together with other files before being converted to machine code to execute.

#### Exercise 1.1.4: A compiler that translates a high-level language into another high-level language is called a source-to-source translator. What advantages are there to using C as a target language for a compiler?

C is a compiled language. Meaning that it will create programs that can be executed directly on the machine platform, rather than an interpreter.

If your program is needing a performance boost for example, and happens to be written in an interpreted language, you can pass this input program 
to a source-to-source compiler to convert it to C without having to do a full rewrite.

#### Exercise 1.1.5: Describe some of the tasks that an assembler needs to perform.

The assembler will have to link against object file together and compute the offsets in memory for code to be relocatable in memory. After that, it will convert this code to binary for execution on the target platform.

#### Exercise 1.3.1: Indicate which of the following terms:

a) imperative
b) declarative
c) von Neumann
d) object-oriented
e) functional
f) third-generation
g) fourth-generation
h) scripting

apply to which of the following languages:

* C
* C++
* Cobol
* Fortran
* Java
* Lisp
* ML
* Perl
* Python
* VB

Answers: 
* C: a, c, f
* C++: a, d, f
* Cobol: a, c, f
* Fortran: a, c, f
* Java: a, c, d, f
* Lisp: b, c, e, f
* ML: b, c, e, f
* Perl: a, c, f, h
* Python: a, c, d, f, h
* VB: a, c, d, f

#### Exercise 1.6.1: For the block-structured C code of Fig. 1.13(a), indicate the values assigned to w, x, y, and z.

w = 13, x = 11, y = 13, z = 11

#### Exercise 1.6.2: Repeat Exercise 1.6.1 for the code of Fig. 1.13(b).

w = 9, x = 7, y = 13, z = 11

#### Exercise 1.6.3: For the block-structured code of Fig. 1.14, assuming the usual static scoping of declarations, give the scope for each of the twelve declarations.

* B1
    * w - B1, B3, B4
    * x - B1, B2, B3, B4
    * y - B1, B5
    * z - B1, B2, B5
* B2
    * x - B2, B3
    * z - B2
* B3
    * w - B3
    * x - B3
* B4
    * w - B4
    * x - B4
* B5
    * y - B5
    * z - B5

#### Exercise 1.6.4: What is printed by the following C code?

```
3
2
```

