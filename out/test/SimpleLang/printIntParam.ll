@.fmt = private constant [4 x i8] c"%d\0A\00"  ; Format string for printing integers
declare i32 @printf(i8*, ...)   ; Declare printf function

define i32 @main() {
    %a = alloca i32        ; Allocate space for integer a
    store i32 90, i32* %a  ; a = 90

    ; Print integer a
    %a_val = load i32, i32* %a
    %fmt_ptr = getelementptr [4 x i8], [4 x i8]* @.fmt, i32 0, i32 0
    call i32 (i8*, ...) @printf(i8* %fmt_ptr, i32 %a_val)

    ret i32 0
}