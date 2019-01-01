**Exercise 3.1.1: Divide the following C++ program:**

```
float limitedSquare(x) float x {
    /* returns x-squared, but never more than 100 */
    return (x<=10.0||x>=10.0)?100:x*x;
}
```

**into appropriate lexemes, using the discussion of Section 3.1.2 as a guide. Which lexemes should get associated lexical values? What should those values be?**

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

**Exercise 3.1.2: Tagged languages like HTML or XML are different from conventional programming languages in that the punctuation (tags) are either very numerous (as in HTML), or user-definable set (as in XML). Further, tags can often have parameters. Suggest how to divide the following HTML document:**


```
Here is a photo of <B>my house</B>:
<P><IMG SRC = "house.gif"><BR>
See <A HREF = "morePix.html">More Pictures</A> if you liked that one.<P>
```

**into appropriate lexemes. Which lexemes should get associated lexical values, and what should those values be?**

```
<literal, "Here is a photo of">
<tagStart, "B">
<literal, "my house">
<tagEnd, "B">
<literal, ":">
<tagStart, "P">
<tag, "IMG">
<tagAttribute, "SRC">
<=>
<attributeValue, ""house.gif"">
<tag, "BR">
<literal, "See">
<tagStart, "A">
<tagAttribute, "HREF">
<=>
<attributeValue, ""morePix.html"">
<literal, "More Pictures">
<tagEnd, "A">
<literal, "if you liked that one.">
<tagEnd, "P">
```
