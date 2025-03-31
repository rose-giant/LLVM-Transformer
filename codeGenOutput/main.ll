		@.str1 = private constant [5 x i8] c"hi2\0A\00"
		@.str0 = private constant [5 x i8] c"hi1\0A\00"
		@.fmt_int = private constant [4 x i8] c"%d\0A\00"  ; Format string for printing integers
		declare i32 @printf(i8*, ...)
		declare i32 @puts(i8*)
		define i32 @main() {
		
		%b = alloca i1
		store i1 1, i1* %b
		%val0 = load i1, i1* %b
		br i1 %val0, label %thenBlock, label %elseBlock
		
		thenBlock:
		
		%fmt_ptr1 = getelementptr [5 x i8], [5 x i8]* @.str0, i32 0, i32 0
		
		call i32 @puts(i8* %fmt_ptr1)
		br label %endIf
		
		elseBlock:
		
		%fmt_ptr3 = getelementptr [5 x i8], [5 x i8]* @.str1, i32 0, i32 0
		
		call i32 @puts(i8* %fmt_ptr3)
		br label %endIf
		
		endIf:
		
		ret i32 0
		}
		
		define void @foo() {
		entry:
		}
