#### Exercise 3.1.1: Divide the following C++ program:

```
float limitedSquare(x) float x {
    /* returns x-squared, but never more than 100 */
    return (x<=10.0||x>=10.0)?100:x*x;
}
```

#### into appropriate lexemes, using the discussion of Section 3.1.2 as a guide. Which lexemes should get associated lexical values? What should those values be? 

```
<float>
<id, "limitedSquare">
<(>
<id, "x">
<)>
<float>
<id, "x">
<{>
<return>
<(>
<id, "x">
<comparison, "<=">
<number, 10.0>
<op, "||">
<id, "x">
<comparison, ">=">
<number, 10.0>
<)>
<op, "?">
<number, 100>
<op, ":">
<id, x>
<op, "*">
<id, x>
<}>
```