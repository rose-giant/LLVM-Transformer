@.str = private constant [5 x i8] c"baby\00"  ; String constant for "baby"
@.fmt_str = private constant [4 x i8] c"%s\0A\00"  ; Format string for printing strings
@.fmt_int = private constant [4 x i8] c"%d\0A\00"  ; Format string for printing integers

declare i32 @printf(i8*, ...)

define i32 @main() {
    %a = alloca i32          ; Allocate space for int a
    %c = alloca i8*          ; Allocate space for string c

    store i32 90, i32* %a    ; a = 90

    ; Get pointer to the first element of @.str
    %c_ptr = getelementptr [5 x i8], [5 x i8]* @.str, i32 0, i32 0
    store i8* %c_ptr, i8** %c  ; Store the pointer in %c

    ; Print string c
    %c_val = load i8*, i8** %c
    %fmt_str_ptr = getelementptr [4 x i8], [4 x i8]* @.fmt_str, i32 0, i32 0
    call i32 (i8*, ...) @printf(i8* %fmt_str_ptr, i8* %c_val)

    ; Print integer a
    %a_val = load i32, i32* %a
    %fmt_int_ptr = getelementptr [4 x i8], [4 x i8]* @.fmt_int, i32 0, i32 0
    call i32 (i8*, ...) @printf(i8* %fmt_int_ptr, i32 %a_val)

    ; Print integer 12
    call i32 (i8*, ...) @printf(i8* %fmt_int_ptr, i32 12)

    ret i32 0
}