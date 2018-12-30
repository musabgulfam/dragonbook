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

*Depth-first tree traversal* is the most commonly used method of tree traversal when it comes to the topic of compilers. There are several methods to perform a depth-first traversal:

1. In-order traversal. Visit the left child, root, and right child.
2. Pre-order traversal. Visit the root, left child, and right child.
3. Post-order traversal. Visit the left child, right child, and root.

Synonymously, we can also consider pre-order traversal as *performing the action* is done before we visit the node, and post-order traversal as the action being done after visiting the node. 

*Semantic actions* are program fragments embedded within production bodies. 

```
rest -> + term {print('+')} rest
```

### 2.4 - Parsing

For any context-free grammar, there is a parser that takes at most `O(n^3)` time to parse a string of `n` terminals. This is slow. Therefore, the grammar must be designed  to be parsed quickly. It is possible to parse linear time. To do this requires the parser implementation to do a left-to-right scan over the input, and perform a look-ahead of 1 terminal at a time while constructing the parse tree.

There are two classes of parse methods: (1) top-down, and (2) bottom-up.

For top-down parsers, the construction of the parse tree starts at the root and proceeds down towards the leaves. 

The pattern is like so:

1. At node N, labeled with a nonterminal A, select one of the productins for A and construct children at N for the symbols in the production body.
2. Find the next node at which the subtree is to be constructed, typically the leftmost unexpanded nonterminal of the tree.

For most grammars, you can do this with a single left-to-right scan of the string. 

The case is opposite for bottom up. Leaves are constructed first and move up to construct the root. 

Top-down parsers are usually much easier to construct, but bottom-up parsers can handle more classes of grammars.

Let's look at top-down parsers in more detail.

**Lookahead** - The current terminal being scanned in the input is the *lookahead* symbol. The initial lookahead is the first terminal of the input string.

For example:

Given the grammar:

```
stmt -> expr ;
    | if ( expr ) stmt
    | for ( optexpr ; optexpr ; optexpr ) stmt
    | other

optexpr -> ϵ
    | expr
```

```
for ( ; expr ; expr ) other
```

The `for` terminal would be the initial lookahead. We have a parse tree pointer, and lookahead symbol pointer to perform recursive descent parsing.

Recursive descent parsing uses a set of recursive procedures to process the input. 

Uses as set of procedures in that a single procedure is associated with each nonterminal of a grammar. A common method is predictive parsing.

In predictive parsing, the lookahead symbol unambiguously determines the flow of control. 

Basic example;

```
stmt -> for (optexpr ; optexpr ; optexpr ) stmt
```

The recursive descent parser would have a procedure to match this production with the following logic:

```
match('for'); match('('); 
optexpr(); match(';'); optexpr(); match(';'); optexpr(); 
match(')'); stmt();
```

Going back to the original grammar, we find that the first set of stmt: 

```
FIRST(stmt) = { expr, if, for, other }
```

We can design a predictive parser to function similar to:

```
void stmt() {
    switch(lookahead) {
        case expr:
            match(expr); match(';'); break;
        case if:
            match(if); match('('); match(expr); match(')'); stmt();
            break;
        case for:
            match(for); match('(');
            optexpr(); match(';'); optexpr(); match(';'); optexpr();
            match(')'); stmt(); break;
        case other:
            match(other); break;
        default:
            report("syntax error");
    }
}

void optexpr() {
    if(lookahead == expr) match(expr);
}

void match(terminal t) {
    if(lookahead == t) lookahead = nextTerminal;
    else report("syntax error");
}
```

Again, a predictive parser is a program that will execute a procedure for a nonterminal it encounters if it is a nonterminal. If it encounters a nonterminal, the following are performed:

* Given the nonterminal A, the predictive parser decides which production from A to use by examining the lookahead symbol. If α is the production body, and is not ϵ, and  the lookahead symbol is in FIRST(α), the lookahead symbol is processed.
* The procedure called will mimic the body of the chosen production. Each of the symbols within the production body are executed from left-to-right. When nonterminals are encountered, a procedure will be called for that nonterminal. In effect, this moves the lookahead symbol further.

The process completes successfully if the input is consumed and matched. Otherwise, a syntax error shall be reported.

What is left recursion? If your production looks like this:

```
expr -> expr + term
```

Then it is left recursive. It will then give the possibility in that the recursive descent parser will loop forever. 

The leftmost symbol of the body is the same as the nonterminal. This could lead to infinite recursive calls. Instead, we can rewrite the production to eliminate  
this infinite recursion. Use the form `A -> Aα | β`. 

So let's try and eliminate the infinite recursion by rewriting the `expr -> expr + term` production to fit the above.

```
expr -> expr + term

A = expr
α = + term
β = term
```

Written as `A -> Aα | β`, we have:

```
expr -> expr + term | term
```

Alternatively, we can convert it to right-recursive where R is a new production.

```
A -> βR 
R -> αR | ϵ

A = expr
α = + term
β = term
R = expr'

expr -> term expr' 
expr' -> + term expr' | ϵ
```

Visually, left recursion represents a tree growing deep on the left side. While, right-recursion represents a tree growing deep on the right side.

```

             /|
            / |
           /  |
          /___|

          Left Recursion

            |\
            | \
            |  \
            |___\
            
            Right Recursion
```

### 2.5 - A Translator for Simple Expressions

### 2.6 - Lexical Analysis