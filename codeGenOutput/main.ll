		define i32 @main() {
		
		    ret i32 0
		}
		
		ldc 123
		istore 1
		iload 1
		getstatic java/lang/System/out Ljava/io/PrintStream;
		swap
		invokevirtual java/io/PrintStream/println(I)V
		ldc "Simba"
		getstatic java/lang/System/out Ljava/io/PrintStream;
		swap
		invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
		ret
