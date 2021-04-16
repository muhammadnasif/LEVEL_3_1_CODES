.MODEL SMALL

.STACK 100H

.DATA
    CR EQU 0DH
    LF EQU 0AH
    
    BR DB CR,LF, '$'
    SPACE DB ' $'
    VALID_PASS DB CR,LF, 'Valid Password$'
    INVALID_PASS DB CR,LF, 'Invalid Password$'
    
    COUNT_CAP DB 0        
    COUNT_SMALL DB 0
    COUNT_DIGIT DB 0
    
.CODE

MAIN PROC
    MOV AX,@DATA
    MOV DS,AX
    
    MOV AH,1
REPEAT_ACTUAL:
    INT 21H
    
    CMP AL,21H
    JL VALIDITY_CHECK
    CMP AL,7EH
    JG VALIDITY_CHECK
 
SMALL_CHECK: 
    CMP AL,'a'
    JL CAPITAL_CHECK 
    CMP AL,'z'
    JG CAPITAL_CHECK
    INC COUNT_SMALL
     
     
CAPITAL_CHECK:    
    CMP AL,'A'
    JL DIGIT_CHECK
    CMP AL,'Z'
    JG DIGIT_CHECK
    INC COUNT_CAP



DIGIT_CHECK: 
    CMP AL,'0'
    JL LOOP_REPEAT
    CMP AL,'9'
    JG LOOP_REPEAT
    INC COUNT_DIGIT
    

LOOP_REPEAT:
    CMP AL,' '
    JNE REPEAT_ACTUAL   
    
        

VALIDITY_CHECK:
    CMP COUNT_CAP,0D
    JE INVALID
    CMP COUNT_SMALL,0D
    JE INVALID
    CMP COUNT_DIGIT,0D
    JE INVALID
    JMP VALID


    
REPEAT_FALSE:
    INT 21H
    CMP AL,' '
    JNE REPEAT_FALSE
    JMP INVALID

       
VALID:
    LEA DX,VALID_PASS
    MOV AH,9
    INT 21H
    JMP END
    
INVALID:
    LEA DX,INVALID_PASS
    MOV AH,9
    INT 21H
    JMP END
     
END:          
    MOV AH,4CH
    INT 21H
    
     
MAIN ENDP
    END MAIN


