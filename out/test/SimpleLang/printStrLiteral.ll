@.str = private constant [6 x i8] c"Simba\00"

declare i32 @puts(i8*)

define i32 @main() {
    %str_ptr = getelementptr [6 x i8], [6 x i8]* @.str, i32 0, i32 0
    call i32 @puts(i8* %str_ptr)
    ret i32 0
}