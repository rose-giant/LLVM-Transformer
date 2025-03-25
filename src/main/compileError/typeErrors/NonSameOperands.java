package main.compileError.typeErrors;

import main.compileError.CompileError;

public class NonSameOperands extends CompileError {
    public NonSameOperands(int line){
        this.line = line;
    }
    public String getErrorMessage(){return "Line:" + this.line + "-> operands of operator must be the same";}

}
