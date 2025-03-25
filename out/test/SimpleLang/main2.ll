 @.fmt = private constant [4 x i8] c"%d\0A\00"

 declare i32 @printf(i8*, ...)

 define i32 @main() {
     ; Call printf to print 22
     %fmt_ptr = getelementptr [4 x i8], [4 x i8]* @.fmt, i32 0, i32 0
     call i32 (i8*, ...) @printf(i8* %fmt_ptr, i32 22)
     ret i32 0
 }