package main.compileError.typeErrors;

import main.compileError.CompileError;

public class Mismatch extends CompileError {
    public Mismatch(int line){
        this.line = line;
    }
    public String getErrorMessage(){ return "Line:" + this.line + "-> Operands of the operator must be the same type."; }

}
