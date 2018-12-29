Written code must be translated into a form which can be executed by a computer. The translator is basically, the compiler. Compilers convert a stream of input characters to machine-readable instructions. They mostly consist of two parts: the front-end, and back-end.

Historically, the front-end deals with converting the stream of characters tokens, and taking those tokens and constructing a parse tree. The back-end  then does the conversion of the parse tree to machine-readable instructions.

There is a lot of motivation to studying how compiilers work. Creating a compiler touches many areas of Computer Science: programming languages, machine architecture, language theory, algorithms, and software engineering.

### 1.1 - Language Processors

A compiler does the following: take the *source language* and translate it to a program in another language, or target. 

```
source program -> [ COMPILER ] -> target program
```

The target program can be an executable program with machine instructions targeted at a particular platform, or another set of instructions in another programming language. 

If the target program is an executable, then the program can read in user inputs and generate output. 

Interpreters are a different type of processor for programming languages. They take in the program source code itself, along with user input and produce an output dynamically.

Interpreters are typically slower than compilers, but may allow an easier debugging experience. The disadvantage is that interpreters are slower than compilers, because compilers actually convert the program code into machine code targeted for the physical platform of the computer.

### 1.2 - The Structure of a Compiler

There are two phases of analyses that the compiler performs: *analysis* and *synthesis*. They can also be thought of as the front-end and back-end of 
the compiler.

Analysis breaks up the source program into pieces and validates grammatical structure on them. This structure is then used to create an intermediate representation of the program. 

It is during the analysis stage that syntax is verified, and the symbol table is created. Upon completion of analysis, the symbol table is fed to the synthesis stage.

The *synthesis* stage constructs the desired target program from the intermediate representation and information from the symbol table. This is the back-end of the compiler.

#### Phases of compilation:

Front-End

```
character stream -> [ LEXICAL ANALYZER ] -> token stream -> [ SYNTAX ANALYZER ] -> syntax tree 
    -> [ SEMANTIC ANALYZER ] -> syntax tree -> [ INTERMEDIATE CODE GENERATOR ] -> intermediate representation
```

Back-End

```
intermediate representation (with symbol table) -> [ MACHINE-INDEPENDENT CODE OPTIMIZER ] 
    -> intermediate representation -> [ CODE GENERATOR ] -> target-machine code -> [ MACHINE-DEPENDENT CODE OPTIMIZER ] -> target-machine code
```

Lexical analysis, or scanning is the first phase of the compiler. It translates the stream of input into lexemes. These lexemes can be generated into tokens. 

Tokens adhere to some mapping/format. For example, `<tokenType, id>`.

```
position = initial + rate * 60
```

Can be translated to:

```
<id, 1> <=> <id, 2> <+> <id, 3> <*> <number, 4>
```

Syntax analysis, the second stage, is synonymous with parsing.

Parsing is the problem of taking a string of terminals and figuring out how to derive it from the start symbol of the grammar.

A grammar that generates two or more different parse trees is said to be ambiguous.

The semantic analyzer uses the syntax tree, and the symbol table created by the syntax analysis phase to check for valid semantic consistency. Does the syntax tree produce a valid statement within the language? This is the semantic analyzer's job.

The semantic analyzer also performs type checking if the target language is statically typed. 

Symbol-table management is an essential function of the compiler. The compiler records the variable names used in the source program to collect information about various attributes of each name. Some of these attributes include: type, scope and variable and procedure names.

After all this, intermediate representation is generated and is fed through various stages within the compiler for optimization and code generation. A note about optimizers is that the optimizers are not guaranteed to generate optimized code. It will heuristically do the best it can to generate the most performant code for that target machine platform.

### 1.3 - The Evolution of Programming Languages

Computers in the 1940s, were first only programmed by having the programmer write programs in binary. A string of 0s and 1s were the norm to create programs for early electronic computers. This was slow, tedious, error prone, and difficult to maintain.

Assembly language was developed in the 1950s to get aroud the forementioned difficulty. The instructions of assembly language were mnemonic representations of machine instructions. 

Macro instructions were eventually developed to allow for shorthand coding for frequently used sequences of machine instructions. 

Fortran was an early high-level programming langauge developed in the late 1950s. It was targeted for scientific computation. Cobol, was the analogous language for business data processing, and Lisp for symbolic computation. 

In this modern era, there are thousands of programming languages. Programming languages can be classified in various ways, but a popular way is by categorizing them into *generations*.

* First generation langauges are machine languages. (Binary)
* Second generation languages are assembly languages. (x86 assembly, 6502, etc.)
* Third generation languages are high-level langauges. (C, C++, Java, etc.)
* Fourth-generation languages are languages that domain-specific languages for specific applications. (SQL, Postscript, etc.)
* Fifth-generation languates are applied to logic and constraint languages. (Prolog, OPS5, etc.)

Programming langauges can be classied either as *imperative*, or *declarative.*. Imperative languages allow for programs to be written in such a way where the instructions are executed sequentially. This is the "how"-manner. With a declarative language, the instructions which are executed are explictly defined. This is the "what" manner.

Programming languages today are mostly targeted for the von Neumann computational model. 

### 1.4 - The Science of Building a Compiler

The purpose of a compiler is for it to be able to accept the source program which conforms to a specific grammar in which it was designed to understand. The compiler must then translate the source program into the target program to be executed in machine.

There are a few fundamental models in which we must understand to properly study compilers. 

* Finite-state machines
* Regular expressions
* Context-free grammars

### 1.5 - Applications of Compiler Technology

Knowing about compilers can be handy for other aspects of computer science. 

* Implementation of High-Level Programming Languages
* Parallelism
* Memory Hierarchies
* Designing new Computer Architectures
* Creating domain-specific interpreters
* Tooling for Productivity

### 1.6 - Programming Language Basics

*Static scope*, or lexical scope means that it is possible to determine the scope of a declaration by looking only at the program. With *dynamic scoping*, the descision on  which variable to use is determined at run-time.  

For example:

```
...
int i;  /* global i */
...
void f(...) {
    int i;  /* local i */
    ...
    i = 3;  /* use of local i */
}
...

x = i + 1; /* use of global i */
```

The above shows an example of static scoping within a C-like langauge. The scope is managed through the use of declaration blocks. 

In this case, the function declaration declares a new scope. We have two `i` integer variables which can be used. However, a reference for an `i` is limited to the closest declaration within the scope. 

In this case, within the function, it is the local declaration. Out of the function, the global.

Static scoping is usually, and relatively easy to understand. A less common policy implemented by programming languages is dynamic scoping. 

Criteria which determines whether a language has dynamic scoping is on the following rule:

*Use of a named variable referes to the declaration of that variable in the most recently called, but not yet terminated procedure with such a declaration.*

Perl, a scripting language, and other shell-type scripting languages can be dynamically scoped. 

If static scope refers to space, then dynamic scope refers to time.

