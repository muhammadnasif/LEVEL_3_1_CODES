.MODEL SMALL

.STACK 100H

.DATA
    CR EQU 0DH
    LF EQU 0AH
    
    NUM_1 DW 0
    RESULT_16 DW 0
    RESULT_DIGIT DW 0
    CX_TEMP DW 0
.CODE


NUMBER_INPUT PROC

    MOV CX,2
    
INPUT:
    
    MOV AH,1
    INT 21H
    
    CMP AL,13
    JZ END_NUMBER_INPUT
    
    
    SUB AL,48D
    MOV BH,AL       ;INPUT NUMBER IN->BH
    XOR AX,AX       ;CLEAR REGISTER
    MOV AX,10D      ;AX = 10
    MUL NUM_1       ;AX = AX*NUM_1 = (10)*NUM_1
    MOV BL,BH
    XOR BH,BH
    ADD AX,BX       ;AX = AX+BH
    
    MOV NUM_1,AX
 
    LOOP INPUT
    
END_NUMBER_INPUT:
    RET                
NUMBER_INPUT ENDP




PRINT_RESULT PROC
OUTPUT_RES:
    
    MOV DX,0
    MOV AX,0
    
    MOV AX,RESULT_16    ;AX = NUM_1
    MOV BX,10       
    DIV BX
    
    MOV RESULT_16,AX
    PUSH DX
    INC RESULT_DIGIT
    CMP RESULT_16,0B
    JZ  PRINT_RES
    
    JMP OUTPUT_RES
    
PRINT_RES:
    MOV CX,RESULT_DIGIT            ;INITIALIZING COUNTER_DIGIT BEFORE CALLING PRINT_RESULT
    MOV AH,2
    JMP FOR_LOOP_RESULT

FOR_LOOP_RESULT:    
    POP DX
    ADD DX,48
    INT 21H
    LOOP FOR_LOOP_RESULT    
     
    
    RET 
PRINT_RESULT ENDP






FIB PROC
    PUSH BP
    MOV BP,SP
    
    
;ESCAPE CASE if(x<=1) return x
    CMP WORD PTR[BP+4],1     ; AX>1
    JG END_IF                ; yes, recursive case
    MOV AX,[BP+4]            ; result = 1,0
    
    JMP RETURN       
    
;fib(n) = fib(n-1) + fib(n-2)

END_IF:
;compute fib(n-1)
    MOV CX,[BP+4]           ; get n
    DEC CX                  ; n-1
    PUSH CX                 ; save n-1
    PUSH CX
    ;DEC CX
    ;PUSH CX
    CALL FIB                ; result_1 in AX
    POP CX
    PUSH AX
    DEC CX                  ; n-2
    PUSH CX                 ; save n-2
    CALL FIB                ; result_2 in AX
    
    POP BX
    ADD AX,BX    
    
    
RETURN:
    POP BP    
    RET 2
FIB ENDP



MAIN PROC
    MOV AX,@DATA
    MOV DS,AX
    
    CALL NUMBER_INPUT
       
    MOV CX,NUM_1
    
    MOV NUM_1,0
    
    MOV AH,2
    MOV DL,CR
    INT 21H
    MOV DL,LF
    INT 21H

    MOV AX,0
    MOV DX,0
    
PRINT:
    
    MOV CX_TEMP,CX
    
    PUSH NUM_1
    CALL FIB
    ;DEC NUM_1
    INC NUM_1
    
    
    MOV RESULT_16,AX
    MOV RESULT_DIGIT,0
    CALL PRINT_RESULT
    
    
    MOV CX,CX_TEMP
    
    CMP CX,1
    JNZ COMMA
    JMP NEXT

COMMA:
    MOV DX,','
    INT 21H
    MOV DX,' '
    INT 21H
NEXT:    
    LOOP PRINT
    
END:    
    MOV AH,4CH
    INT 21H
    
MAIN ENDP
    END MAIN