		
		@.fmt = private constant [4 x i8] c"%d\0A\00"
		declare i32 @printf(i8*, ...)
		define i32 @main() {
		
		
		%a = alloca i32
		store i32 123, i32* %a
		%a = alloca i32
		%val0 = load i32, i32* %a
		call i32 (i8*, ...) @printf(i8* getelementptr ([3 x i8], [3 x i8]* @.fmt, i32 0, i32 0), i32 %val0)ret i32 0
		}
