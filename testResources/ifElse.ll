@.true_str = private constant [5 x i8] c"fuck\00"
@.false_str = private constant [5 x i8] c"shit\00"

declare i32 @printf(i8*, ...)

define i32 @main() {
entry:
    %b = alloca i1
    store i1 1, i1* %b  ; b = true

    %b_val = load i1, i1* %b
    br i1 %b_val, label %thenBlock, label %elseBlock  ; if (b) branch

thenBlock:
    %fmt_true = getelementptr [5 x i8], [5 x i8]* @.true_str, i32 0, i32 0
    call i32 (i8*, ...) @printf(i8* %fmt_true)  ; print("fuck")
    br label %endIf

elseBlock:
    %fmt_false = getelementptr [5 x i8], [5 x i8]* @.false_str, i32 0, i32 0
    call i32 (i8*, ...) @printf(i8* %fmt_false)  ; print("shit")
    br label %endIf

endIf:
    ret i32 0
}