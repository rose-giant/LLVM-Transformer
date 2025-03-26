@.str = private constant [11 x i8] c"okay baby\0A\00"

define i32 @main() {
    ; Print the string
    %1 = call i32 (ptr, ...) @printf(ptr @.str)
    
    ret i32 0
}

declare i32 @printf(ptr, ...)