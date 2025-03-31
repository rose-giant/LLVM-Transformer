FuncDec foo() {
    int x;          // Declare an integer variable x
    x = 3;          // Assign value 3 to x
    print(x);
}

main() {
    boolean b;
    b = true;
    foo();

    if (b)
        print("hi1");
    else
        print("hi2");
}
