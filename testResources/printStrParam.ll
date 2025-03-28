@.str = private constant [6 x i8] c"baby\0A\00"  ; Define string constant "baby\n"

declare i32 @printf(i8*, ...)   ; Declare printf function

define i32 @main() {
    %c = alloca i8*        ; Allocate space for string pointer
    %c_ptr = getelementptr [6 x i8], [6 x i8]* @.str, i32 0, i32 0  ; Get pointer to the first character
    store i8* %c_ptr, i8** %c  ; Store the string pointer

    ; Print the string
    %c_val = load i8*, i8** %c
    call i32 (i8*, ...) @printf(i8* %c_val)

    ret i32 0
}