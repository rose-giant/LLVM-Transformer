		@.str0 = private constant [5 x i8] c"baby\00"
		@.fmt_int = private constant [4 x i8] c"%d\0A\00"  ; Format string for printing integers
		declare i32 @printf(i8*, ...)
		define i32 @main() {
		
		%a = alloca i32
		store i32 90, i32* %a
		%c = alloca i8*
		%c_ptr0 = getelementptr [5 x i8], [5 x i8]* @.str0, i32 0, i32 0
		store i8* %c_ptr0, i8** %c
		%val0 = load i8*, i8** %c
		call i32 (i8*, ...) @printf(i8* %val0)
		%val1 = load i32, i32* %a
		%fmt_ptr1 = getelementptr [4 x i8], [4 x i8]* @.fmt_int, i32 0, i32 0
		call i32 (i8*, ...) @printf(i8* %fmt_ptr1, i32 %val1)
		ret i32 0
		}
