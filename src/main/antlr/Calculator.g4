grammar Calculator;

start
    : expression
    | assignment
    ;

/*
 * The order in which expressions are evaluated
 * is determined by the order in which possible
 * matching rules are defined.
 * Here, numbers are dealt with first, then parentheses
 * and so on.
 *
 * Multiplication and division are on the
 * same precedence level, so they are grouped.
 * The same goes for addition and subtraction.
 *
 * Labels (e.g. "# Parentheses") are added to each rule
 * to provide context to which rule is being parsed.
 * This is can be used in a Listener or Visitor
 * to allow for separate control over Listener or Visitor actions.
 *
 * Likewise, inner labels (e.g. "left=expression")
 * can be added to child nodes of the rule.
 * This makes them identifiable in a
 * Listener's or Visitor's parsing of the rule,
 * allowing for even more fine-grained control.
 */
expression
    returns [Double retval]
    : NUMBER                                               # Number
    | VARIABLE                                             # Variable
    | '(' inner=expression ')'                             # Parentheses
    | left=expression operator=POW right=expression        # Power
    | left=expression operator=MOD right=expression        # Modulo
    | left=expression operator=(MUL|DIV) right=expression  # MultiplicationOrDivision
    | left=expression operator=(ADD|SUB) right=expression  # AdditionOrSubtraction
    ;

assignment
    : left=VARIABLE EQUAL right=expression                 # Assign
    ;

/*
 * Tokens (terminal)
 */
POW: '^' ;
MOD: '%' ;
MUL: '*' ;
DIV: '/' ;
ADD: '+' ;
SUB: '-' ;
EQUAL: '=' ;
//NUMBER: '-'?[0-9]+ ;
NUMBER: [0-9]+ ;
VARIABLE: ([a-z] | [A-Z]) ([a-z] | [A-Z] | [0-9])* ;
WHITESPACE: [ \r\n\t]+ -> skip ;