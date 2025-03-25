@.fmt1 = private constant [7 x i8] c"simba\0A\00"  ; '\0A' is newline
@.fmt2 = private constant [4 x i8] c"%d\0A\00"    ; '%d' followed by newline

declare i32 @printf(i8*, ...)

define i32 @main() {
    ; Print "simba" with a newline
    %fmt1_ptr = getelementptr [7 x i8], [7 x i8]* @.fmt1, i32 0, i32 0
    call i32 (i8*, ...) @printf(i8* %fmt1_ptr)

    ; Print "123" with a newline
    %fmt2_ptr = getelementptr [4 x i8], [4 x i8]* @.fmt2, i32 0, i32 0
    call i32 (i8*, ...) @printf(i8* %fmt2_ptr, i32 123)

    ret i32 0
}