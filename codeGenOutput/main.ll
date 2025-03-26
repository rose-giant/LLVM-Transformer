		
		@.fmt = private constant [4 x i8] c"%d\0A\00"
		declare i32 @printf(i8*, ...)
		define i32 @main() {
		
		
		%a = alloca i32
		store i32 90, i32* %a%fmt_ptr0 = getelementptr [4 x i8], [4 x i8]* @.fmt, i32 0, i32 0
		call i32 (i8*, ...) @printf(i8* %fmt_ptr0, i32 12)
		ret i32 0
		}
