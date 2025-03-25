package main.ast.nodes.type;

import main.ast.nodes.Node;

public abstract class Type extends Node {
    public boolean sameType(Type other){
        return (this.getClass().equals(other.getClass())) && !((this instanceof NonType) || (other instanceof NonType));
    }
}
