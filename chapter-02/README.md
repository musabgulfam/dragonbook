A context-free grammar can be used to help guide the translation of programs. 

Two forms of intermediate code representation is common: *abstract syntax tree* (AST), and *three-address instruction*.

In an AST, the syntax is represented in a hierarchical structure. In three-address instruction code, an instruction takes the form of `x = y op z`, where x, y, z are addresses, and op is the operand.

A three-address instruction carries out at most one operation -- a computation, comparison, or a branch.

### 2.2 - Syntax Definition

*Context-free grammar* is used to specify the syntax of the language. A grammar naturally describes the hierarchical structure of most programming language constructs.

For example, an if-else statement in Java:

```
if ( expression ) statement else statement
```

Can take on the form of:

```
stmt -> if ( expr ) stmt else stmt
```

The form above is said to be a *production*. In a production, lexical elements like keywords ('if', 'else', '(', ')') are said to be *terminals*. Variables like *expr* and *stmt* are *nonterminals*.

A context-free grammar has 4 components:

1. Set of terminal symbols referred to as tokens.
2. Set of non-terminals called syntactic variables.
3. Set of productions which are a sequence of terminals and/or non-terminals.
4. A start symbol.

A string of zero terminals, ϵ, is also called the empty string.

A grammar derives strings by beginning with the start symbol and repeatedly replacing a nonterminal by the body of a production for that nonterminal. 

Parsing is the problem of taking a string of terminals and figuring out how to derive it from the start symbol of the grammar.

A parse tree has the following properties:

1. Root is labeled by the start symbol.
2. Each leaf is labeled by either a terminal, or by ϵ.
3. Each interior node is labeled by a non-terminal.

```
A -> X1 X2 X3 ... Xn
```

Translates to:

![Parse Tree Example](assets/parse-tree-1.png)

A grammar is said to be *ambiguous* if a string can be derived by more than one parse tree.

### 2.3 - Syntax-Directed Translation

Two concepts related to syntax-directed translation:

1. Attributes - This is any quantity associated with the programming construct. They can be data types, number of instructions in the generated code, or the location of the first instruction.
2. (Syntax-directed) translation schemes - A translation scheme is a notation for attaching program fragments to the productions of a grammar. The program fragments are executed when the production is used during syntax analysis.

There are 3 ways to traverse a parse tree in a depth-first manner. Of course, they are your standard tree traversal algorithms: preorder, postorder and inorder.

Synthesized attributes is the idea of associating an attribute/quantity with programming constructs. A syntax-directed definition associates a given grammar symbol with a set of attributes, and with each production, a set of *semantic rules* for computing the values and the attributes associated with the symbols appearing in the production.

A semantic rule is evaluated at the appropriate node for each parse tree. An attribute is said to be synthesized if its value at a parse-tree node N is determined from attribute values at the children of N and at N itself. 

What makes a synthesized attribute special is that they have the property in that they can be evaluated during a single bottom-up traversal of a parse tree.

