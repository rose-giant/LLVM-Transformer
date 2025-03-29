		@.str0 = private constant [6 x i8] c"shit\0A\00"
		@.fmt_int = private constant [4 x i8] c"%d\0A\00"  ; Format string for printing integers
		declare i32 @printf(i8*, ...)
		declare i32 @puts(i8*)
		define i32 @main() {
		
		%b = alloca i1
		store i1 1, i1* %b
		%val0 = load i1, i1* %b
		br i1 %val0, label %thenBlock, label %elseBlock
		
		thenBlock:
		
		br label %endIf
		
		elseBlock:
		
		%fmt_ptr2 = getelementptr [6 x i8], [6 x i8]* @.str0, i32 0, i32 0
		
		call i32 @puts(i8* %fmt_ptr2)
		br label %endIf
		
		endIf:
		
		ret i32 0
		}
