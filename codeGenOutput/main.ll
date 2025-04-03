		@.str7 = private constant [8 x i8] c"bitch2\0A\00"
		@.str6 = private constant [8 x i8] c"bitch1\0A\00"
		@.str5 = private constant [8 x i8] c"bitch2\0A\00"
		@.str4 = private constant [8 x i8] c"bitch1\0A\00"
		@.str3 = private constant [5 x i8] c"hi2\0A\00"
		@.str2 = private constant [5 x i8] c"hi1\0A\00"
		@.str1 = private constant [5 x i8] c"hi2\0A\00"
		@.str0 = private constant [5 x i8] c"hi1\0A\00"
		@.fmt_int = private constant [4 x i8] c"%d\0A\00"
		declare i32 @printf(i8*, ...)
		declare i32 @puts(i8*)
		define i32 @main() {
		
		call void @foo(i32 1,i1 false)
		
		%b = alloca i1
		store i1 1, i1* %b
		%val1 = load i1, i1* %b
		br i1 %val1, label %thenBlock, label %elseBlock
		
		thenBlock:
		
		%fmt_ptr5 = getelementptr [5 x i8], [5 x i8]* @.str2, i32 0, i32 0
		
		call i32 @puts(i8* %fmt_ptr5)
		br label %endIf
		
		elseBlock:
		
		%fmt_ptr7 = getelementptr [5 x i8], [5 x i8]* @.str3, i32 0, i32 0
		
		call i32 @puts(i8* %fmt_ptr7)
		br label %endIf
		endIf:
		
		ret i32 0
		}
		
		define void @foo(i32 %x, i1 %z) {
		entry:
		%a = alloca i1
		store i1 1, i1* %a
		%val3 = load i1, i1* %a
		br i1 %val3, label %thenBlock, label %elseBlock
		
		thenBlock:
		
		%fmt_ptr13 = getelementptr [8 x i8], [8 x i8]* @.str6, i32 0, i32 0
		
		call i32 @puts(i8* %fmt_ptr13)
		br label %endIf
		
		elseBlock:
		
		%fmt_ptr15 = getelementptr [8 x i8], [8 x i8]* @.str7, i32 0, i32 0
		
		call i32 @puts(i8* %fmt_ptr15)
		br label %endIf
		endIf:
		
		%fmt_ptr17 = getelementptr [4 x i8], [4 x i8]* @.fmt_int, i32 0, i32 0
		call i32 (i8*, ...) @printf(i8* %fmt_ptr17, i32 2)
		ret void;
		}
