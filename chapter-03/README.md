### Lexical Analysis

* Regular expressions
* Deterministic automata
* Non-deterministic automata

### 3.1 - The Role of the Lexical Analyzer

Lexical analysis - First phase of the compiler.

The main task is to read input of character of the source program, and group them into lexemes. The lexemes are then produced as output, a series of tokens. 

The tokens are sent to the parser for syntax analysis.

Lexical analyzer can also interact with the symbol table. 

In general, it is common that a parser interacts with the lexical analyzer by calling some sort of function exposed by the lexical analyzer such as `getNextToken()`. Then returned result is a token from the lexical analyzer.

The parser will repeatedly do this until there is no more input from the source program.

![Interactions between lexical analyzer and parser](assets/lexical-analyzer-and-parser-interaction.png)

The lexical analyzer can also keep track of the number of `\n` characters sen so that it can associate a line number with each error message. 

Macro expansion can also be performed by the lexical analyzer. 

Lexical analyzers are divided into a cascade of 2 processes:

a) Scanning - consists of simple processes that do not require tokenization of the input. (Deletion of comments, compaction of consecutive whitespace characters into 1, etc)
b) Lexical analysis - Produces tokens from the output of the scanner.

Lexical Analysis and parsing are usually separate phases for various reasons:

1. Separating lexical analysis, and implementing it in a lexical analyzer keeps both the lexical analyzer and parser simple. Each will have its own special task to worry about, and thus simpler and easier-to-understand designs.
2. Compilier efficiencies. We can spend more time to optimize various parts of the lexical analyzer, not having to worry about avoiding touching the parsing logic.
3. Compiler portability. Input-device-specific peculiarities can be restricted to the lexical analyzer.

Tokens, Patters, and Lexemes. What's the difference?

* A **token** is a pair consisting of a token name and an optional attribute value. The token name is an abstract symbol representing a kind of lexical unit -- keywords, identifier, etc.
* A **pattern** is the form of what the lexemes of a token can take. For example, a pattern for an identifier would be a lexeme that begins with a letter, followed by a series of letters and numbers.
* A **lexeme** is a sequence of characters in the source program which matches a pattern for a token. 

Example:

```
printf("Total = %d\n", score);
```

`printf`, `score` = lexemes with pattern matching to *id*.

`"Total = %d\n"` is a lexeme matching *literal*.

Usually, the lexical analyzer not only returns just the token type to the parser, but also the token attribute value as it is useful for other operations and/or for manipulation of the symbol table.

### 3.2 - Input Buffering
