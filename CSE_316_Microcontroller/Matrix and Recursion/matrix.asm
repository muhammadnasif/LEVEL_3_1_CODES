                            .MODEL SMALL
 
.STACK 100H
 
.DATA
    CR EQU 0DH
    LF EQU 0AH

    MAT_1 DB 0,0
          DB 0,0
 
    MAT_2 DB 0,0
          DB 0,0
 
    C_1 DW 0
    C_2 DW 0
    CX_TEMP DW 0
    VAL DB 0
    DIGIT DB 0
    
    MAT_RESULT DB 0,0
               DB 0,0 
    
    
 
.CODE
 
 
MAT_INPUT PROC
    MOV AH,1
    MOV CX,4
    MOV BX,0
 
INPUT_LOOP:
    MOV AH,1
    INT 21H
    MOV BL,AL
    SUB BL,48
    MOV [SI],BL
    INC SI
    
    MOV AH,2
    MOV DL,' '
    INT 21H
    
    CMP CX,3
    JZ NEWLINE_INPUT
 
 
    LOOP INPUT_LOOP
    
    JMP END_INPUT

NEWLINE_INPUT:
    MOV DL,CR
    INT 21H
    MOV DL,LF
    INT 21H
    MOV AH,1
    LOOP INPUT_LOOP

END_INPUT:
    
    MOV DL,CR
    INT 21H
    MOV DL,LF
    INT 21H
    
    
    RET
MAT_INPUT ENDP
 
 
 
 
NUMBER_PRINT PROC
    
DIGIT_CALCULATE:
    
    MOV AL,VAL
    MOV BX,10
    DIV BL
    
    INC DIGIT
    MOV CL,DIGIT
    PUSH AX
    MOV VAL,AL
    CMP AL,0
    JZ PRINT_NUM
    MOV AX,0
    JMP DIGIT_CALCULATE
    
PRINT_NUM:
    MOV AH,2
    POP DX
    MOV VAL,DH
    MOV DX,0
    MOV DL,VAL
    ADD DL,48
    
    INT 21H
    LOOP PRINT_NUM

    
    RET
NUMBER_PRINT ENDP 
 
 
 
 
 

MAIN PROC
    MOV AX,@DATA
    MOV DS,AX 
 
    MOV SI, OFFSET MAT_1
    CALL MAT_INPUT
 
    MOV SI, OFFSET MAT_2
    CALL MAT_INPUT
 
    MOV CX,4

    MOV C_1,0
    MOV C_2,0
 
LOOP_ADD_REVISED:
    MOV SI, OFFSET MAT_1
    ADD SI,C_1
    MOV AL,[SI]
    MOV SI, OFFSET MAT_2
    ADD SI,C_2
    MOV BL,[SI]
 
    ADD AL,BL
 
    MOV SI,OFFSET MAT_RESULT
    ADD SI,C_1
    MOV [SI],AL
 
    INC C_1
    INC C_2
 
    LOOP LOOP_ADD_REVISED
 
    MOV SI, OFFSET MAT_RESULT

    MOV CX,4
    MOV AH,2
    
    MOV DL,CR
    INT 21H
    MOV DL,LF
    INT 21H    
LOOP_PRINT:

    MOV AH,2
    MOV DL,[SI]
    MOV VAL,DL
    MOV CX_TEMP, CX
    MOV AX,0
    MOV CX,0
    MOV DIGIT,0
    CALL NUMBER_PRINT
    MOV CX,CX_TEMP

    INC SI 
    
    CMP CX,3
    JZ NEWLINE
    
    MOV DL,' '
    INT 21H
        
    LOOP LOOP_PRINT 
    JMP END
 
 
NEWLINE:
    MOV DL,CR
    INT 21H
    MOV DL,LF
    INT 21H
    LOOP LOOP_PRINT
 
END:          
    MOV AH,4CH
    INT 21H
 
MAIN ENDP
    END MAIN